package view;

import dao.Grupo_migracaoDAO;
import dao.MigracaoDAO;
import geral.Dados;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.MigracaoMODEL;

public class MenuMigracao extends javax.swing.JFrame {

    //construção
    private Dados dados;
    //daos
    private MigracaoDAO migracaoDAO;
    private Grupo_migracaoDAO grupo_migracaoDAO;
    //componentes
    private DefaultComboBoxModel modelo1;
    private DefaultComboBoxModel modelo2;
    private DefaultTableModel jt1;
    //carregamento de dados
    private ResultSet rs;

    public MenuMigracao(Dados dados) throws SQLException {
        this.dados = dados;

        //instanciarDAO
        this.migracaoDAO = new MigracaoDAO(this.dados);
        this.grupo_migracaoDAO = new Grupo_migracaoDAO(this.dados);

        //inicializar
        initComponents();
        this.setResizable(false);
        centralizarTela();

        //componentes ComboBOX
        this.modelo1 = (DefaultComboBoxModel) jGruposBox1.getModel();
        this.modelo2 = (DefaultComboBoxModel) jGruposBox2.getModel();
        inicializarGrupoBox1();

        //inicializar Tabela
        jMigracaoT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jt1 = (DefaultTableModel) jMigracaoT.getModel();

        carregarTabelaMigracao();

    }

    public void carregarTabelaMigracao() throws SQLException {
        jt1.setNumRows(0);
        String item_selecionado = "";
        try {
            item_selecionado = this.modelo1.getSelectedItem().toString();
        } catch (NullPointerException ne) {
            item_selecionado = "Geral";
        }
        this.rs = migracaoDAO.retornaListaMigracaoPorIdGrupo(grupo_migracaoDAO.retornaIdGrupoPorNome(item_selecionado));
        while (rs.next()) {
            Object[] linha = {rs.getString("nome")};
            this.jt1.addRow(linha);
        }
    }

    public void inicializarGrupoBox2() throws SQLException {
        this.modelo2.removeAllElements();
        ResultSet gb = null;
        gb = grupo_migracaoDAO.retornaListaGrupos();

        while (gb.next()) {
            modelo2.addElement(gb.getString("nome"));
        }
    }

    public void centralizarTela() {
        Dimension dim = this.getToolkit().getScreenSize();
        int x = (int) (dim.getWidth() - this.getSize().getWidth()) / 2;
        int y = (int) (dim.getHeight() - this.getSize().getHeight()) / 2;
        this.setLocation(x, y);
    }

    public void inicializarGrupoBox1() throws SQLException {
        this.modelo1.removeAllElements();
        ResultSet gb = null;
        gb = grupo_migracaoDAO.retornaListaGrupos();

        while (gb.next()) {
            modelo1.addElement(gb.getString("nome"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPainelMenu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jGruposBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMigracaoT = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSelecionar = new javax.swing.JButton();
        jAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaotxt = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jGruposBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        nometxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idtxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        jPainelMenu.setBackground(new java.awt.Color(35, 100, 119));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Menu Migração");

        jGruposBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhum grupo selecionado" }));
        jGruposBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jGruposBox1ItemStateChanged(evt);
            }
        });

        jMigracaoT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecione uma migração"
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
        jMigracaoT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMigracaoTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMigracaoTMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMigracaoTMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jMigracaoT);

        jButton1.setText("Criar Grupo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Criar Migração");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Selecione um grupo para facilitar sua busca:");

        javax.swing.GroupLayout jPainelMenuLayout = new javax.swing.GroupLayout(jPainelMenu);
        jPainelMenu.setLayout(jPainelMenuLayout);
        jPainelMenuLayout.setHorizontalGroup(
            jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPainelMenuLayout.createSequentialGroup()
                        .addGroup(jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPainelMenuLayout.createSequentialGroup()
                                .addGroup(jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(jGruposBox1, 0, 299, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPainelMenuLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPainelMenuLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPainelMenuLayout.setVerticalGroup(
            jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jGruposBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jSelecionar.setText("Iniciar Migração");
        jSelecionar.setEnabled(false);
        jSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelecionarActionPerformed(evt);
            }
        });

        jAlterar.setText("Alterar");
        jAlterar.setEnabled(false);
        jAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlterarActionPerformed(evt);
            }
        });

        descricaotxt.setColumns(20);
        descricaotxt.setRows(5);
        descricaotxt.setEnabled(false);
        jScrollPane1.setViewportView(descricaotxt);

        jLabel4.setText("Descrição:");

        jGruposBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhum grupo selecionado", "Item 2", "Item 3", "Item 4" }));
        jGruposBox2.setEnabled(false);

        jLabel3.setText("Grupo:");

        nometxt.setText("Selecione uma migração");
        nometxt.setEnabled(false);

        jLabel2.setText("Nome:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Alterar Migração:");

        idtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPainelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idtxt))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSelecionar))
                    .addComponent(jGruposBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nometxt)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPainelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idtxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nometxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jGruposBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAlterar)
                    .addComponent(jSelecionar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jGruposBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jGruposBox1ItemStateChanged
        try {
            carregarTabelaMigracao();
        } catch (SQLException ex) {
            Logger.getLogger(MenuMigracao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ne) {

        }
    }//GEN-LAST:event_jGruposBox1ItemStateChanged

    private void jMigracaoTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMigracaoTMouseClicked
        String nome_migracao = "";
        nome_migracao = (String) jt1.getValueAt(jMigracaoT.getSelectedRow(), 0);

        try {
            ResultSet migracaor = migracaoDAO.retornaListaMigracaoPorNome(nome_migracao);
            while (migracaor.next()) {
                int id = migracaor.getInt("id");
                String nome = migracaor.getString("nome");
                String grupo = grupo_migracaoDAO.retornaNomeGrupoPorId(migracaor.getInt("id_grupo"));
                String descricao = migracaor.getString("descricao");
                int origem = migracaor.getInt("id_origem");
                int destino = migracaor.getInt("id_destino");
                nometxt.setText(nome);
                descricaotxt.setText(descricao);
                inicializarGrupoBox2();
                modelo2.setSelectedItem(grupo);
                jAlterar.setEnabled(true);
                jSelecionar.setEnabled(true);
                idtxt.setText(Integer.toString(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuMigracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMigracaoTMouseClicked

    private void jAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlterarActionPerformed
        if (jAlterar.getText().equals("Alterar")) {
            nometxt.setEnabled(true);
            descricaotxt.setEnabled(true);
            jGruposBox2.setEnabled(true);
            jSelecionar.setEnabled(false);
            jAlterar.setText("Salvar");
        } else {
            try {
                migracaoDAO.alterarMigracao(nometxt.getText(), descricaotxt.getText(), grupo_migracaoDAO.retornaIdGrupoPorNome(modelo2.getSelectedItem().toString()), Integer.parseInt(idtxt.getText()));
                nometxt.setEnabled(false);
                descricaotxt.setEnabled(false);
                jGruposBox2.setEnabled(false);
                jSelecionar.setEnabled(true);
                
                jAlterar.setText("Alterar");
                carregarTabelaMigracao();
                jGruposBox1.setSelectedItem(modelo2.getSelectedItem().toString());
            } catch (SQLException ex) {
                Logger.getLogger(MenuMigracao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jAlterarActionPerformed

    private void jMigracaoTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMigracaoTMouseEntered

    }//GEN-LAST:event_jMigracaoTMouseEntered

    private void jMigracaoTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMigracaoTMousePressed

    }//GEN-LAST:event_jMigracaoTMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            CadastrarMigracao cm = new CadastrarMigracao(this.dados, this);
            cm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MenuMigracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CadastrarGrupo cg = new CadastrarGrupo(this.dados, this);
        cg.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelecionarActionPerformed
        try {
            ResultSet migracaor = migracaoDAO.retornaListaMigracaoPorNome(nometxt.getText());
            MigracaoMODEL m = null;
            
            while(migracaor.next()){
                MigracaoMODEL mt = new MigracaoMODEL(migracaor.getInt("id"),migracaor.getString("nome"),
                migracaor.getString("descricao"),migracaor.getInt("id_grupo"),migracaor.getInt("id_origem"),
                migracaor.getInt("id_destino"));
                m = mt;
            }
            this.dados.setMigracao(m);
            Conexao cx = new Conexao(this.dados,this);
            cx.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MenuMigracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descricaotxt;
    private javax.swing.JLabel idtxt;
    private javax.swing.JButton jAlterar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jGruposBox1;
    private javax.swing.JComboBox<String> jGruposBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTable jMigracaoT;
    private javax.swing.JPanel jPainelMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSelecionar;
    private javax.swing.JTextField nometxt;
    // End of variables declaration//GEN-END:variables
}
