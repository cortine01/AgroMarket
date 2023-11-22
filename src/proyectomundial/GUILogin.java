/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectomundial;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author dylan
 */
public class GUILogin extends javax.swing.JFrame {

    /**
     * Creates new form GUILogin
     */
    public GUILogin() {
        initComponents();
        pintarLogo();
        pintarFondo();
        pintarLabel();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(460, 340));
        setResizable(false);
        setSize(new java.awt.Dimension(450, 300));
        getContentPane().setLayout(null);
        getContentPane().add(logo);
        logo.setBounds(16, 41, 200, 200);

        jLabel1.setText("jLabel2");
        jLabel1.setMinimumSize(new java.awt.Dimension(110, 36));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 16));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 20, 110, 36);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(234, 59, 200, 30);

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 110, 110, 36);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(230, 150, 200, 30);

        jLabel3.setText("jLabel1");
        jLabel3.setMinimumSize(new java.awt.Dimension(130, 38));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(260, 190, 140, 38);
        getContentPane().add(back);
        back.setBounds(0, 0, 450, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUILogin().setVisible(true);
            }
        });
    }
private void pintarLogo() {
        logo.setOpaque(false);
        logo.setPreferredSize((new java.awt.Dimension(150, 150)));
        logo.setSize(200,200);
        logo.setIcon(new ImageIcon(getClass().getResource("/resources/agro_market.png")));
    }
private void pintarFondo() {
    back.setOpaque(false);
    back.setPreferredSize((new java.awt.Dimension(150, 150)));
    back.setSize(450,300);   
    back.setIcon(new ImageIcon(getClass().getResource("/resources/background.jpg")));
    }
private void pintarLabel() {
    jLabel1.setOpaque(false);
    jLabel1.setPreferredSize((new java.awt.Dimension(150, 150)));
    jLabel1.setSize(110,36);   
    jLabel1.setIcon(new ImageIcon(getClass().getResource("/resources/label1.jpg")));
    jLabel2.setOpaque(false);
    jLabel2.setPreferredSize((new java.awt.Dimension(150, 150)));
    jLabel2.setSize(110,36);   
    jLabel2.setIcon(new ImageIcon(getClass().getResource("/resources/label2.jpg")));
    jLabel3.setOpaque(false);
    jLabel3.setPreferredSize((new java.awt.Dimension(150, 150)));
    jLabel3.setSize(130,36);   
    jLabel3.setIcon(new ImageIcon(getClass().getResource("/resources/label3.jpg")));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
