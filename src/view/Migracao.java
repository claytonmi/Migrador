/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.Tabela_vinculoDAO;
import geral.Dados;
import geral.ExecutarMigracao;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matheus
 */
public class Migracao extends javax.swing.JFrame {

    private Dados dados;
    private DefaultTableModel jt1;
    private Tabela_vinculoDAO tabela_vinculoDAO;
    private DefaultComboBoxModel modelo1;
    private String vinculo_atual;

    public Migracao(Dados dados) throws SQLException {
        this.dados = dados;
        this.tabela_vinculoDAO = new Tabela_vinculoDAO(dados);
        this.jProgresso = new JProgressBar();
        //design
        centralizarTela();
        initComponents();
        this.setResizable(false);

        //combobox
        this.modelo1 = (DefaultComboBoxModel) cbVinculo.getModel();

        //tabelas
        this.jt1 = (DefaultTableModel) resultado.getModel();
        resultado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //dados_conexao
        carregarDadosConexao();
        carregarCbVinculo();

        //desabilitar
        if (cbVinculo.getSelectedItem().equals("Nenhum vínculo selecionado!")) {
            sql.enable(false);
            jCarregar.setEnabled(false);
            jSalvar.setEnabled(false);
            jVincular_coluna.setEnabled(false);
            jExecutar.setEnabled(false);
        }
    }

    public void carregarCbVinculo() throws SQLException {
        String vinculo_selected = this.dados.getVinculo_atual();
        this.modelo1.removeAllElements();
        ResultSet rs = this.tabela_vinculoDAO.retornaListaVinculoTabela();
        this.modelo1.addElement("Nenhum vínculo selecionado!");
        while (rs.next()) {
            this.modelo1.addElement(rs.getString("nome_vinculo"));
        }
        if (vinculo_selected != null) {
            modelo1.setSelectedItem(vinculo_selected);
        }

    }

    public void carregarTabelaResultado(ResultSet rs) throws SQLException {
        jt1.setNumRows(0);
        ResultSetMetaData rsmd = rs.getMetaData();
        Vector colunas = new Vector();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            colunas.add(rsmd.getColumnName(i));
            jt1.setColumnIdentifiers(colunas);
        }

        while (rs.next()) {
            Vector linhas = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                linhas.add(rs.getObject(rsmd.getColumnName(i)));
            }
            jt1.addRow(linhas);
        }

    }

    public void carregarDadosConexao() {
        host1.setText(this.dados.getDados_conexao().getHost1());
        host2.setText(this.dados.getDados_conexao().getHost2());
        porta1.setText(this.dados.getDados_conexao().getPorta1());
        porta2.setText(this.dados.getDados_conexao().getPorta2());
        database1.setText(this.dados.getDados_conexao().getDatabase1());
        database2.setText(this.dados.getDados_conexao().getDatabase2());
    }

    public void centralizarTela() {
        Dimension dim = this.getToolkit().getScreenSize();
        int x = (int) (dim.getWidth() - this.getSize().getWidth()) / 4;
        int y = (int) (dim.getHeight() - this.getSize().getHeight()) / 4;
        this.setLocation(x, y - 100);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        origem = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        database1 = new javax.swing.JTextField();
        host1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        porta1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        database2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        host2 = new javax.swing.JTextField();
        porta2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        sql = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultado = new javax.swing.JTable();
        cbVinculo = new javax.swing.JComboBox<>();
        jCarregar = new javax.swing.JButton();
        jTabelas = new javax.swing.JButton();
        jSalvar = new javax.swing.JButton();
        jVincular_coluna = new javax.swing.JButton();
        jExecutar = new javax.swing.JButton();
        jProgresso = new javax.swing.JProgressBar();
        LabelContagemInicial = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LabelContagemFinal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        origem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        origem.setText("Origem:");

        jLabel10.setText("Nome do banco:");

        database1.setText("BancoMIGRADOR");
        database1.setEnabled(false);
        database1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                database1ActionPerformed(evt);
            }
        });

        host1.setEnabled(false);

        jLabel2.setText("Host:");

        porta1.setText("5432");
        porta1.setEnabled(false);
        porta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porta1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Porta:");

        jLabel11.setText("Nome do banco:");

        destino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        destino.setText("Destino:");

        database2.setText("BancoMIGRADOR");
        database2.setEnabled(false);
        database2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                database2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Host:");

        host2.setText("localhost");
        host2.setEnabled(false);

        porta2.setText("5432");
        porta2.setEnabled(false);
        porta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porta2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Porta:");

        sql.setColumns(20);
        sql.setRows(5);
        jScrollPane1.setViewportView(sql);

        jTabbedPane1.addTab("SQL", jScrollPane1);

        resultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(resultado);

        jTabbedPane1.addTab("Visualizar", jScrollPane3);

        cbVinculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVinculoItemStateChanged(evt);
            }
        });

        jCarregar.setText("Carregar");
        jCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCarregarActionPerformed(evt);
            }
        });

        jTabelas.setText("Vincular Tabelas");
        jTabelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTabelasActionPerformed(evt);
            }
        });

        jSalvar.setText("Salvar");
        jSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalvarActionPerformed(evt);
            }
        });

        jVincular_coluna.setText("Vincular Colunas");
        jVincular_coluna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVincular_colunaActionPerformed(evt);
            }
        });

        jExecutar.setText("Executar");
        jExecutar.setToolTipText("");
        jExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExecutarActionPerformed(evt);
            }
        });

        jProgresso.setBackground(new java.awt.Color(255, 255, 255));

        LabelContagemInicial.setText("0 ");

        jLabel4.setText("DADOS CARREGADOS DE");

        LabelContagemFinal.setText("0");

        jLabel6.setText("LINHAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCarregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabelas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jExecutar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(cbVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jVincular_coluna)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(origem))
                                .addGap(210, 210, 210))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(database1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(host1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(porta1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(destino)
                            .addComponent(jLabel11)
                            .addComponent(database2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(porta2))))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgresso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(LabelContagemInicial)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(LabelContagemFinal)
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(destino)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(database2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(host1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(origem)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(database1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVincular_coluna))
                .addGap(12, 12, 12)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCarregar)
                    .addComponent(jSalvar)
                    .addComponent(jTabelas)
                    .addComponent(jExecutar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelContagemInicial)
                    .addComponent(jLabel4)
                    .addComponent(LabelContagemFinal)
                    .addComponent(jLabel6))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void database1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_database1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_database1ActionPerformed

    private void porta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porta1ActionPerformed

    private void database2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_database2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_database2ActionPerformed

    private void porta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porta2ActionPerformed

    private void jCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCarregarActionPerformed
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql_carregar = sql.getText();
            stmt = this.dados.getConexaoORIGEM().prepareStatement(sql_carregar);
            rs = stmt.executeQuery();
            carregarTabelaResultado(rs);
            JOptionPane.showMessageDialog(null, "Clique na aba 'VISUALIZAR' para ver as informações carregadas!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jCarregarActionPerformed

    private void jTabelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTabelasActionPerformed

        try {
            VincularTabela vt = new VincularTabela(this.dados, this);
            vt.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(Migracao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTabelasActionPerformed

    private void cbVinculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVinculoItemStateChanged
        try {
            this.jt1.setNumRows(0);
            this.vinculo_atual = (String) cbVinculo.getSelectedItem();
            this.dados.setVinculo_atual(this.vinculo_atual);
            if (this.vinculo_atual.equals("Nenhum vínculo selecionado!")) {
                sql.enable(false);
                jCarregar.setEnabled(false);
                jSalvar.setEnabled(false);
                jVincular_coluna.setEnabled(false);
                jExecutar.setEnabled(false);
            } else {
                sql.enable(true);
                jCarregar.setEnabled(true);
                jSalvar.setEnabled(true);
                jVincular_coluna.setEnabled(true);
                jExecutar.setEnabled(true);
                this.dados.setVinculo_atual(vinculo_atual);
                ResultSet rs = tabela_vinculoDAO.retornaListaVinculoTabelaPorNome(this.vinculo_atual);

                String testoSQL = "";
                String testoOrigem = "";
                while (rs.next()) {
                    testoSQL = rs.getString("script");   
                    testoOrigem = rs.getString("origem");   
                }                
                if(testoSQL == null){
                    sql.setText("Select * from "+testoOrigem);                    
                }else{                    
                    sql.setText(testoSQL);                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Migracao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbVinculoItemStateChanged

    private void jSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalvarActionPerformed
        try {
            tabela_vinculoDAO.salvarScript(sql.getText(), this.vinculo_atual);
            JOptionPane.showMessageDialog(null, "O script foi salvo com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(Migracao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSalvarActionPerformed

    private void jVincular_colunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVincular_colunaActionPerformed

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = this.dados.getConexaoORIGEM().prepareStatement(sql.getText());
            rs = stmt.executeQuery();

            VincularColuna vc;
            vc = new VincularColuna(this.dados, this);
            this.dados.setVinculo_atual(cbVinculo.getItemAt(cbVinculo.getSelectedIndex()));
            vc.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "O script salvo no vínculo apresenta erros, clique em carregar para verificar!");
        }
    }//GEN-LAST:event_jVincular_colunaActionPerformed

    private void jExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExecutarActionPerformed

        try {
            PreparedStatement stmt = null;
            int cont = 0;
            ResultSet rs = null;
            stmt = this.dados.getConexaoORIGEM().prepareStatement(sql.getText());
            rs = stmt.executeQuery();
            try {
                ExecutarMigracao em;
                em = new ExecutarMigracao(dados);
                ArrayList<String> linhas_migracao = em.executarMigracao();
                jProgresso.setMinimum(0);
                jProgresso.setMaximum(linhas_migracao.size());
                LabelContagemFinal.setText(Integer.toString(linhas_migracao.size()));
                for(String linha:linhas_migracao){
                    PreparedStatement stmt2 = null;
                    ResultSet rs2 = null;
                    stmt2 = this.dados.getConexaoDESTINO().prepareStatement(linha);
                    stmt2.executeUpdate();  
                    cont++;
                    LabelContagemInicial.setText(Integer.toString(cont));                    
                    jProgresso.setValue(cont);
                }
                
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O script salvo no vínculo apresenta erros, clique em carregar para verificar!");
        }

    }//GEN-LAST:event_jExecutarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelContagemFinal;
    private javax.swing.JLabel LabelContagemInicial;
    private javax.swing.JComboBox<String> cbVinculo;
    private javax.swing.JTextField database1;
    private javax.swing.JTextField database2;
    private javax.swing.JLabel destino;
    private javax.swing.JTextField host1;
    private javax.swing.JTextField host2;
    private javax.swing.JButton jCarregar;
    private javax.swing.JButton jExecutar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgresso;
    private javax.swing.JButton jSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jTabelas;
    private javax.swing.JButton jVincular_coluna;
    private javax.swing.JLabel origem;
    private javax.swing.JTextField porta1;
    private javax.swing.JTextField porta2;
    private javax.swing.JTable resultado;
    private javax.swing.JTextArea sql;
    // End of variables declaration//GEN-END:variables

}
