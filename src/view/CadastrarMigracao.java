package view;

import dao.Grupo_migracaoDAO;
import dao.MigracaoDAO;
import dao.SgbdDAO;
import geral.Dados;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class CadastrarMigracao extends javax.swing.JFrame {

    private Dados dados;
    private DefaultComboBoxModel modelo1;
    private MenuMigracao mm;
    private Grupo_migracaoDAO grupo_migracaoDAO;
    private MigracaoDAO migracaoDAO;
    private SgbdDAO sgbdDAO;

    public CadastrarMigracao(Dados dados, MenuMigracao mm) throws SQLException {
        //construção
        this.dados = dados;
        this.mm = mm;
        mm.setEnabled(false);
        //inicializar tela
        initComponents();
        this.setResizable(false);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        centralizarTela();

        //instanciarDAO
        this.grupo_migracaoDAO = new Grupo_migracaoDAO(this.dados);
        this.sgbdDAO = new SgbdDAO(this.dados);
        this.migracaoDAO = new MigracaoDAO(this.dados);

        //componentes ComboBOX
        this.modelo1 = (DefaultComboBoxModel) grupoc.getModel();
        inicializarGrupoc();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mm.dispose();
                dispose();
                mm.setEnabled(true);
                mm.setVisible(true);
            }
        });

    }

    public void centralizarTela() {
        Dimension dim = this.getToolkit().getScreenSize();
        int x = (int) (dim.getWidth() - this.getSize().getWidth()) / 2;
        int y = (int) (dim.getHeight() - this.getSize().getHeight()) / 2;
        this.setLocation(x, y);
    }

    public void inicializarGrupoc() throws SQLException {
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomec = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        grupoc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        origemc = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        destinoc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoc = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cadastrar Migração:");

        jLabel2.setText("Nome:");

        nomec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomecMouseClicked(evt);
            }
        });
        nomec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomecActionPerformed(evt);
            }
        });

        jLabel3.setText("Grupo:");

        grupoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Origem:");

        origemc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PostgreSQL", "MySQL", "SQL Server", "Oracle" }));

        jLabel5.setText("Destino");

        destinoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PostgreSQL", "MySQL", "SQL Server", "Oracle" }));

        jLabel6.setText("Descrição:");

        descricaoc.setColumns(20);
        descricaoc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        descricaoc.setRows(5);
        descricaoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descricaocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(descricaoc);

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nomec, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(grupoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(origemc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(destinoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6)
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grupoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origemc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //variaveis
            boolean valido = true;
            String nome = nomec.getText();
            int id_grupo = grupo_migracaoDAO.retornaIdGrupoPorNome(grupoc.getSelectedItem().toString());
            int id_origem = sgbdDAO.retornaIdPorNome(origemc.getSelectedItem().toString());
            int id_destino = sgbdDAO.retornaIdPorNome(destinoc.getSelectedItem().toString());
            String descricao = descricaoc.getText();

            //validar nome e descrição
            if (nome.length() > 100 && valido == true) {
                JOptionPane.showMessageDialog(null, "O nome só pode ter 100 caracteres!");
                valido = false;
            }

            if (nomec.getText() == null || nomec.getText().trim().equals("") || nomec.getText().equals("Informe um nome..")) {
                JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
                valido = false;
            }

            if (descricao.length() > 1000 && valido == true) {
                JOptionPane.showMessageDialog(null, "A descrição só pode ter 1000 caracteres!");
                valido = false;
            }
            if (valido == true) {
                ResultSet migracaor = migracaoDAO.retornaListaMigracao();

                while (migracaor.next()) {
                    if (migracaor.getString("nome").equals(nome) && valido == true) {
                        JOptionPane.showMessageDialog(null, "Já existe uma migração com esse nome!");
                        valido = false;

                    }
                }
            }

            System.out.println(valido);

            if (valido == true) {
                this.migracaoDAO.inserirMigracao(nome, descricao, id_grupo, id_origem, id_destino);
                JOptionPane.showMessageDialog(null, "Migração cadastrada com sucesso!");
                dispose();
                mm.dispose();
                new MenuMigracao(this.dados).setVisible(true);
            }

        } catch (SQLException ex) {
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomecActionPerformed

    }//GEN-LAST:event_nomecActionPerformed

    private void nomecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomecMouseClicked

    }//GEN-LAST:event_nomecMouseClicked

    private void descricaocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descricaocMouseClicked

    }//GEN-LAST:event_descricaocMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descricaoc;
    private javax.swing.JComboBox<String> destinoc;
    private javax.swing.JComboBox<String> grupoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomec;
    private javax.swing.JComboBox<String> origemc;
    // End of variables declaration//GEN-END:variables
}
