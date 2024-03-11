/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.Grupo_migracaoDAO;
import geral.Dados;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadastrarGrupo extends javax.swing.JFrame {

    private Grupo_migracaoDAO grupo_migracaoDAO;
    private Dados dados;
    private MenuMigracao mm;

    public CadastrarGrupo(Dados dados, MenuMigracao mm) {
        this.mm = mm;
        initComponents();
        this.setResizable(false);
        this.dados = dados;
        this.grupo_migracaoDAO = new Grupo_migracaoDAO(dados);
        mm.setEnabled(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centralizarTela();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { 
                mm.setEnabled(true);
                dispose();
            }
        });
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomec = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(35, 100, 119));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cadastrar Grupo:");

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
                    .addComponent(nomec)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomecMouseClicked

    }//GEN-LAST:event_nomecMouseClicked

    private void nomecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomecActionPerformed

    }//GEN-LAST:event_nomecActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean valido = true;
        try {
            String nome = nomec.getText();

            if (nome.length() > 100 && valido == true) {
                JOptionPane.showMessageDialog(null, "O nome só pode ter 100 caracteres!");
                valido = false;
            }

            if (valido == true) {
                ResultSet grupor;

                grupor = grupo_migracaoDAO.retornaListaGrupos();

                while (grupor.next()) {
                    if (grupor.getString("nome").equals(nome) && valido == true) {
                        JOptionPane.showMessageDialog(null, "Já existe um grupo com esse nome!");
                        valido = false;

                    }
                }
            }

            if (nomec.getText() == null || nomec.getText().trim().equals("") || nomec.getText().equals("Informe um nome..")) {
                JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
                valido = false;
            }

            if (valido == true) {
                grupo_migracaoDAO.inserirGrupo(nome);
                JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");
                dispose();
                mm.dispose();
                new MenuMigracao(dados).setVisible(true);
            }

        } catch (SQLException ex) {

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nomec;
    // End of variables declaration//GEN-END:variables
}
