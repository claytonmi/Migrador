/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import dao.Coluna_vinculoDAO;
import dao.MySQLDAO;
import dao.PostgresDAO;
import dao.SqlServerDAO;
import dao.Tabela_vinculoDAO;
import geral.Dados;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Tabela_vinculoMODEL;
import org.postgresql.PGConnection;

public class VincularColuna extends javax.swing.JFrame {

    private DefaultTableModel modelo1;
    private DefaultTableModel modelo2;
    private DefaultTableModel modelo3;
    private String origem;
    private String destino;
    private Dados dados;
    private Tabela_vinculoDAO tabela_vinculoDAO;
    private Coluna_vinculoDAO coluna_vinculoDAO;
    private Migracao m;
    private ResultSet rs;
    private PostgresDAO postgresDAO;
    private MySQLDAO mysqlDAO;
    private SqlServerDAO SqlServerDAO;
    private ResultSet rs_viculoatual;
    private PreparedStatement stmt;
    private Tabela_vinculoMODEL tabela_vinculo;

    public VincularColuna(Dados dados, Migracao m) throws SQLException {
        initComponents();
        this.setResizable(false);
        centralizarTela();
        this.dados = dados;
        this.postgresDAO = new PostgresDAO(dados);
        this.mysqlDAO = new MySQLDAO(dados);
        this.SqlServerDAO = new SqlServerDAO(dados);
        this.coluna_vinculoDAO = new Coluna_vinculoDAO(dados);
        this.tabela_vinculoDAO = new Tabela_vinculoDAO(dados);
        this.rs_viculoatual = tabela_vinculoDAO.retornaListaVinculoTabelaPorNome(this.dados.getVinculo_atual());
        while (this.rs_viculoatual.next()) {
            this.tabela_vinculo = new Tabela_vinculoMODEL(rs_viculoatual.getInt("id"), rs_viculoatual.getString("origem"), rs_viculoatual.getString("destino"), rs_viculoatual.getString("script"), rs_viculoatual.getInt("id_migracao"), rs_viculoatual.getString("nome_vinculo"));
        }
        this.modelo1 = (DefaultTableModel) jt1.getModel();
        this.modelo2 = (DefaultTableModel) jt2.getModel();
        this.modelo3 = (DefaultTableModel) jt3.getModel();
        jt1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt1.setAutoCreateRowSorter(true);
        jt2.setAutoCreateRowSorter(true);
        jt3.setAutoCreateRowSorter(true);

        //carregar tabelas
        carregarTabelaOrigem();
        carregarTabelaDestino();
        carregarTabelaVinculos();

        this.m = m;
        m.setEnabled(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                m.dispose();
                dispose();
                try {
                    new Migracao(dados).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VincularTabela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void centralizarTela() {
        Dimension dim = this.getToolkit().getScreenSize();
        int x = (int) (dim.getWidth() - this.getSize().getWidth()) / 2;
        int y = (int) (dim.getHeight() - this.getSize().getHeight()) / 2;
        this.setLocation(x, y);
    }

    public void carregarTabelaDestino() throws SQLException {
        Connection con = this.dados.getConexaoDESTINO();
        Properties po = this.dados.getConexaoDESTINO().getClientInfo();
        modelo2.setNumRows(0);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        
        String pos = po.toString();

        if (con instanceof PGConnection) {
            rs = this.postgresDAO.retornaColunasPorTabela(this.tabela_vinculo.getDestino());
            while (rs.next()) {
                boolean passou = true;
                rs2 = this.coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
                while (rs2.next()) {
                    if (rs.getString("column_name").equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("column_name"), rs.getString("data_type")};
                    modelo2.addRow(linha);
                }
            }
        } else if(pos.equals("{ApplicationName=Mysql JDBC Driver}")){

            rs = this.mysqlDAO.retornaColunasPorTabela(this.tabela_vinculo.getDestino());
            while (rs.next()) {
                boolean passou = true;
                rs2 = this.coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
                while (rs2.next()) {
                    if (rs.getString("Field").equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("Field"), rs.getString("Type")};
                    modelo2.addRow(linha);
                }
            }
        }else if(con instanceof SQLServerConnection) {                    
            rs = this.SqlServerDAO.retornaColunasPorTabela(this.tabela_vinculo.getDestino());                    
            while (rs.next()) {
                boolean passou = true;
                rs2 = this.coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
                while (rs2.next()) {
                    if (rs.getString("column_name").equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("column_name"), rs.getString("DATA_TYPE")};
                    modelo2.addRow(linha);
                }
            }
        } else {
            
        }
    }

    private void carregarTabelaVinculos() throws SQLException {
        modelo3.setNumRows(0);
        ResultSet rs = null;
        rs = this.coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
        while (rs.next()) {
            Object[] linha = {rs.getString("nome_vinculo")};
            modelo3.addRow(linha);
        }

    }

    public void carregarTabelaOrigem() throws SQLException {
        modelo1.setNumRows(0);
        stmt = this.dados.getConexaoORIGEM().prepareStatement(this.tabela_vinculo.getScript());
        rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            Object[] linha = {rsmd.getColumnName(i)};
            modelo1.addRow(linha);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jt3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        jt1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt1);

        jt2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Destino", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jt2);
        if (jt2.getColumnModel().getColumnCount() > 0) {
            jt2.getColumnModel().getColumn(0).setPreferredWidth(150);
            jt2.getColumnModel().getColumn(1).setPreferredWidth(106);
        }

        jt3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vinculos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jt3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jt3);

        jButton1.setText("Adicionar Vinculo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remover Vinculo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt1MouseClicked
        jt3.clearSelection();
    }//GEN-LAST:event_jt1MouseClicked

    private void jt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt2MouseClicked
        jt3.clearSelection();
    }//GEN-LAST:event_jt2MouseClicked

    private void jt3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt3MouseClicked
        jt1.clearSelection();
        jt2.clearSelection();
    }//GEN-LAST:event_jt3MouseClicked

    private void jt3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt3MousePressed
        jt1.clearSelection();
        jt2.clearSelection();
    }//GEN-LAST:event_jt3MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jt1.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Você precisa selecionar uma coluna de origem!");
            } else {
                if (jt2.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Você precisa selecionar uma coluna de destino!");
                } else {

                    coluna_vinculoDAO.inserirVinculoColuna((String) jt1.getValueAt(jt1.getSelectedRow(), 0), (String) jt2.getValueAt(jt2.getSelectedRow(), 0), this.tabela_vinculo.getId(), (String) jt2.getValueAt(jt2.getSelectedRow(), 1));
                    carregarTabelaDestino();
                    carregarTabelaVinculos();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(VincularTabela.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jt3.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um vínculo para remover!");
        } else {
            try {
                coluna_vinculoDAO.removerVinculoColuna((String) jt3.getValueAt(jt3.getSelectedRow(), 0));
                carregarTabelaDestino();
                carregarTabelaVinculos();
            } catch (SQLException ex) {
                Logger.getLogger(VincularColuna.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jt1;
    private javax.swing.JTable jt2;
    private javax.swing.JTable jt3;
    // End of variables declaration//GEN-END:variables

}
