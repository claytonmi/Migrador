package view;

import geral.ConexaoMIGRACAO;
import geral.Dados;
import geral.Dados_conexao;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.MigracaoMODEL;

public class Conexao extends javax.swing.JFrame {

    private Dados dados;
    private MigracaoMODEL migracao;
    private MenuMigracao mm;

    public Conexao(Dados dados, MenuMigracao mm) {
        initComponents();
        this.setResizable(false);
        this.mm = mm;
        centralizarTela();
        mm.setEnabled(false);
        this.dados = dados;
        this.migracao = this.dados.getMigracao();
        carregarEspecificacoesOrigem();
        carregarEspecificacoesDestino();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mm.dispose();
                dispose();
                mm.setEnabled(true);
                mm.setVisible(true);
            }
        });

    }

    public void carregarEspecificacoesOrigem() {
        host1.setText("localhost");
        if (migracao.getId_origem() == 1) {
            porta1.setText("5432");
            origem.setText(origem.getText() + " PostgreSQL");

        } else if(migracao.getId_origem() == 2) {
            porta1.setText("3306");
            origem.setText(origem.getText() + " MySQL");
        } else if(migracao.getId_origem() == 3){
            porta1.setText("1433");
            origem.setText(origem.getText() + " SQL Server");
        } else {
            porta1.setText("1521");
            origem.setText(origem.getText() + " Oracle");
        }
    }

    public void carregarEspecificacoesDestino() {
        host2.setText("localhost");
        if (migracao.getId_destino()== 1) {
            porta2.setText("5432");
            destino.setText(destino.getText() + " PostgreSQL");
        } else if(migracao.getId_destino() == 2) {
            porta2.setText("3306");
            destino.setText(destino.getText() + " MySQL");
        } else if(migracao.getId_destino() == 3){
            porta2.setText("1433");
            destino.setText(destino.getText() + " SQL Server");
        } else {
            porta2.setText("1521");
            destino.setText(destino.getText() + " Oracle");
        }
    }

    public void centralizarTela() {
        Dimension dim = this.getToolkit().getScreenSize();
        int x = (int) (dim.getWidth() - this.getSize().getWidth()) / 2;
        int y = (int) (dim.getHeight() - this.getSize().getHeight()) / 2;
        this.setLocation(x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        username1 = new javax.swing.JTextField();
        porta1 = new javax.swing.JTextField();
        senha1 = new javax.swing.JPasswordField();
        host1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        origem = new javax.swing.JLabel();
        senha2 = new javax.swing.JPasswordField();
        username2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        host2 = new javax.swing.JTextField();
        porta2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        database1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        database2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        username1.setToolTipText("");
        username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username1ActionPerformed(evt);
            }
        });

        porta1.setText("5432");
        porta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porta1ActionPerformed(evt);
            }
        });

        host1.setText("localhost");

        jLabel2.setText("Host:");

        jLabel3.setText("Porta:");

        jLabel4.setText("Username:");

        jLabel5.setText("Senha:");

        origem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        origem.setText("Origem:");

        username2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Senha:");

        jLabel7.setText("Username:");

        host2.setText("localhost");

        porta2.setText("5432");
        porta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porta2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Porta:");

        jLabel9.setText("Host:");

        destino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        destino.setText("Destino:");

        database1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                database1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Nome do banco:");

        database2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                database2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Nome do banco:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(184, 184, 184)
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(123, 123, 123))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(username2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(senha2))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(porta2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel5))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(senha1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel10)
                                    .addComponent(origem)
                                    .addComponent(destino)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(host1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(porta1)))
                                    .addComponent(database1)
                                    .addComponent(database2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 27, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(origem)
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(database1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(porta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(host1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(porta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(host2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void porta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porta1ActionPerformed

    private void username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username1ActionPerformed

    private void username2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username2ActionPerformed

    private void porta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porta2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean valido = true;
        Connection origem = null;
        Connection destino = null;
        try {
            ConexaoMIGRACAO cm = new ConexaoMIGRACAO(this.dados);
            origem = cm.conectar(database1.getText(), host1.getText(), porta1.getText(), username1.getText(), senha1.getText(), this.dados.getMigracao().getId_origem(), "Origem");

            if (origem == null) {
                valido = false;
            }

            if (valido == true) {
                destino = cm.conectar(database2.getText(), host2.getText(), porta2.getText(), username2.getText(), senha2.getText(), this.dados.getMigracao().getId_destino(), "Destino");
            }

            if (destino == null) {
                valido = false;
            }

            if (valido == true) {
                Dados_conexao dc = new Dados_conexao(database1.getText(), database2.getText(), host1.getText(), host2.getText(), username1.getText(), username2.getText(), senha1.getText(), senha2.getText(), porta1.getText(), porta2.getText());
                this.dados.setDados_conexao(dc);
                this.dados.setConexaoORIGEM(origem);
                this.dados.setConexaoDESTINO(destino);
                mm.dispose();
                dispose();
                Migracao m = new Migracao(this.dados);
                m.setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void database1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_database1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_database1ActionPerformed

    private void database2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_database2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_database2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField database1;
    private javax.swing.JTextField database2;
    private javax.swing.JLabel destino;
    private javax.swing.JTextField host1;
    private javax.swing.JTextField host2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel origem;
    private javax.swing.JTextField porta1;
    private javax.swing.JTextField porta2;
    private javax.swing.JPasswordField senha1;
    private javax.swing.JPasswordField senha2;
    private javax.swing.JTextField username1;
    private javax.swing.JTextField username2;
    // End of variables declaration//GEN-END:variables
}
