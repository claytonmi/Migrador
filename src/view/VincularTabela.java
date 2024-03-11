package view;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import dao.Tabela_vinculoDAO;
import geral.Dados;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.postgresql.PGConnection;

public class VincularTabela extends javax.swing.JFrame {

    private DefaultTableModel modelo1;
    private DefaultTableModel modelo2;
    private DefaultTableModel modelo3;
    private String origem;
    private String destino;
    private Dados dados;
    private Tabela_vinculoDAO tabela_vinculoDAO;
    private Migracao m;

    public VincularTabela(Dados dados, Migracao m) throws SQLException {
        initComponents();
        this.setResizable(false);
        centralizarTela();
        //inicializar tabelas
        this.dados = dados;
        this.m = m;
        m.setEnabled(false);
        this.tabela_vinculoDAO = new Tabela_vinculoDAO(dados);
        this.modelo1 = (DefaultTableModel) jt1.getModel();
        this.modelo2 = (DefaultTableModel) jt2.getModel();
        this.modelo3 = (DefaultTableModel) jt3.getModel();
        jt1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carregarTabelaOrigem();
        carregarTabelaDestino();
        carregarTabelaVinculos();

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

    public void carregarTabelaOrigem() throws SQLException {
        Connection com = this.dados.getConexaoORIGEM();
        Properties po = this.dados.getConexaoORIGEM().getClientInfo();
        PreparedStatement stmt;
        ResultSet rs;
        String SQL;
        modelo1.setNumRows(0);

        String pos = po.toString();

        if (com instanceof PGConnection) {
            SQL = "select tablename from pg_tables where schemaname not in ('pg_catalog','information_schema') order by tablename";
            stmt = this.dados.getConexaoORIGEM().prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] linha = {rs.getString("tablename")};
                modelo1.addRow(linha);
            }
        } else if (pos.equals("{ApplicationName=MySQL JDBC Driver}")){
            SQL = "show tables;";
            stmt = this.dados.getConexaoORIGEM().prepareStatement(SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] linha = {rs.getString("Tables_in_" + this.dados.getDados_conexao().getDatabase1())};
                modelo1.addRow(linha);
            }
        }else if (com instanceof SQLServerConnection){      
            com.setCatalog(this.dados.getDados_conexao().getDatabase1());
            SQL = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE'";
            stmt = this.dados.getConexaoORIGEM().prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] linha = {rs.getString("table_name")};
                modelo1.addRow(linha);
            }
        } else {
            
        }
        jt1.setAutoCreateRowSorter(true);
    }

    public void carregarTabelaDestino() throws SQLException {
        Connection com = this.dados.getConexaoDESTINO();
        Properties pd = this.dados.getConexaoDESTINO().getClientInfo();
        PreparedStatement stmt;
        ResultSet rs;
        ResultSet rs2;
        String SQL;
        modelo2.setNumRows(0);

        String pds = pd.toString();

        if (com instanceof PGConnection) {
            SQL = "select tablename from pg_tables where schemaname not in ('pg_catalog','information_schema') order by tablename";
            stmt = this.dados.getConexaoDESTINO().prepareStatement(SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                boolean passou = true;
                rs2 = tabela_vinculoDAO.retornaListaVinculoTabela();
                while (rs2.next()) {
                    if (rs.getString("tablename").equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("tablename")};
                    modelo2.addRow(linha);
                }
            }
        } else if (pds.equals("{ApplicationName=MySQL JDBC Driver}")){
            SQL = "show tables;";
            stmt = this.dados.getConexaoDESTINO().prepareStatement(SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                boolean passou = true;
                rs2 = tabela_vinculoDAO.retornaListaVinculoTabela();
                while (rs2.next()) {
                    System.out.println(rs.getString("Tables_in_" + this.dados.getDados_conexao().getDatabase2()).equals(rs2.getString("destino")));
                    if (rs.getString("Tables_in_" + this.dados.getDados_conexao().getDatabase2()).equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("Tables_in_" + this.dados.getDados_conexao().getDatabase2())};
                    modelo2.addRow(linha);
                }
            }
        }else if (com instanceof SQLServerConnection){
            com.setCatalog(this.dados.getDados_conexao().getDatabase2());
            SQL = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE'";
            stmt = this.dados.getConexaoDESTINO().prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                boolean passou = true;
                rs2 = tabela_vinculoDAO.retornaListaVinculoTabela();
                while (rs2.next()) {
                    if (rs.getString("table_name").equals(rs2.getString("destino"))) {
                        passou = false;
                    }
                }
                if (passou == true) {
                    Object[] linha = {rs.getString("table_name")};
                    modelo2.addRow(linha);
                }              
            }
        }else{
            
        }
    }

    public void carregarTabelaVinculos() throws SQLException {
        modelo3.setNumRows(0);

        ResultSet rs = tabela_vinculoDAO.retornaListaVinculoTabela();

        while (rs.next()) {
            Object[] linha = {rs.getString("origem") + "->" + rs.getString("destino")};
            modelo3.addRow(linha);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jt3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        jLabel2.setText("jLabel2");

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
                "Destino"
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
        jt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jt2);

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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (jt3.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Você precisa selecionar um vínculo para remover!");
            } else {
                tabela_vinculoDAO.removerVinculoTabela((String) jt3.getValueAt(jt3.getSelectedRow(), 0));

                carregarTabelaDestino();

                carregarTabelaVinculos();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VincularTabela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jt1.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Você precisa selecionar uma tabela de origem!");
            } else {
                if (jt2.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Você precisa selecionar uma tabela de destino!");
                } else {

                    tabela_vinculoDAO.inserirVinculoTabela((String) jt1.getValueAt(jt1.getSelectedRow(), 0), (String) jt2.getValueAt(jt2.getSelectedRow(), 0));
                    carregarTabelaDestino();
                    carregarTabelaVinculos();

                }

            }
        } catch (SQLException ex) {           
            Logger.getLogger(VincularTabela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jt3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt3MouseClicked
        jt1.clearSelection();
        jt2.clearSelection();
    }//GEN-LAST:event_jt3MouseClicked

    private void jt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt1MouseClicked
        jt3.clearSelection();
    }//GEN-LAST:event_jt1MouseClicked

    private void jt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt2MouseClicked
        jt3.clearSelection();
    }//GEN-LAST:event_jt2MouseClicked

    private void jt3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt3MousePressed
        jt1.clearSelection();
        jt2.clearSelection();
    }//GEN-LAST:event_jt3MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable jt1;
    private javax.swing.JTable jt2;
    private javax.swing.JTable jt3;
    // End of variables declaration//GEN-END:variables

}
