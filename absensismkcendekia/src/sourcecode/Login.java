/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcecode;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Login extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    /**
     * Creates new form homelogin2
     */
    public Login() {
       initComponents();
       koneksi DB = new koneksi();
       DB.config();
       con = DB.con;
       stat = DB.stm;
    }

    public void proseslogin(){
      try {
            sql = "SELECT * FROM admin WHERE username='"+txt_username.getText()+"' AND password='"+txt_password.getText()+"'";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                if(txt_username.getText().equals(rs.getString("username")) && txt_password.getText().equals(rs.getString("password"))){
                        JOptionPane.showMessageDialog(null,("Selamat Datang ") + rs.getString("nama"),
                                "Login Dosen Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        DataSiswa dtsiswa=new DataSiswa();
                        dtsiswa.setVisible(true);
                   
                }
            } else {
                    JOptionPane.showMessageDialog(null, ("Maaf, Username atau Password Salah, Silahkan Coba Lagi!"), 
                        "Login Gagal", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLogin = new javax.swing.JPanel();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        Button_Login = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelLogin.setMinimumSize(new java.awt.Dimension(1366, 768));
        PanelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(244, 243, 244));
        txt_username.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txt_username.setForeground(new java.awt.Color(153, 153, 153));
        txt_username.setBorder(null);
        txt_username.setDoubleBuffered(true);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        PanelLogin.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 315, 420, 60));

        txt_password.setBackground(new java.awt.Color(244, 243, 244));
        txt_password.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txt_password.setForeground(new java.awt.Color(153, 153, 153));
        txt_password.setToolTipText("");
        txt_password.setBorder(null);
        PanelLogin.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 413, 420, 60));

        Button_Login.setBorder(null);
        Button_Login.setBorderPainted(false);
        Button_Login.setContentAreaFilled(false);
        Button_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LoginActionPerformed(evt);
            }
        });
        PanelLogin.add(Button_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 550, 385, 55));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGIN.png"))); // NOI18N
        PanelLogin.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void Button_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LoginActionPerformed
        this.proseslogin();
    }//GEN-LAST:event_Button_LoginActionPerformed

    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton Button_Login;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
