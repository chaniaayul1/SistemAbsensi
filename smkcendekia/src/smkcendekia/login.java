/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author chani
 */
public class login extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql,user,nip;
    Preferences preference;
    boolean rememberPreferences;
    
    
    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }
    public void rememberMe(){
        preference = Preferences.userNodeForPackage(this.getClass());
        rememberPreferences = preference.getBoolean("rememberMe", Boolean.valueOf(""));
        if (rememberPreferences){
            txt_username.setText(preference.get("username",""));
            txt_password.setText(preference.get("password", ""));
        }
    }
    public void proseslogin(){
      String userlog,level;
      PreparedStatement ps;
      try {
            sql = "SELECT * FROM admin WHERE BINARY username='"+txt_username.getText()+"' AND BINARY password=MD5('"+txt_password.getText()+"') AND status='Aktif'";
            rs = stat.executeQuery(sql);
            String usernm = txt_username.getText();
            //ps = con.prepareStatement("SELECT * FROM admin where username='"+txt_username.getText()+"' and password = MD5('"+txt_password.getText()+"')";
            if (rs.next()) {
                if(remember.isSelected() && !rememberPreferences){
                    preference.put("username",txt_username.getText());
                    preference.put("password",txt_password.getText());
                    preference.putBoolean("rememberMe",true);
                } else if (!remember.isSelected() && rememberPreferences){
                    preference.put("Username","");
                    preference.put("password","");
                    preference.putBoolean("rememberMe",false);
                }
                
                if (null == rs.getString("level")) {
                    //
                } else switch (rs.getString("level")) {
                    case "0": 
                        //Level 0 untuk Guru BK
                        JOptionPane.showMessageDialog(null,("Selamat Datang ") + rs.getString("nama"),
                                "Login Guru BK Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        user = rs.getString("username");
                        nip = rs.getString("nip");
                        Session.seteditadmin(nip);
                        Session.setusername(user);
                        dashboard dash=new dashboard();
                        dash.setVisible(true);
                        //keloladata_bk kdbk = new keloladata_bk();
                        //kdbk.username.setText(usernm);
                        //kdbk.setVisible(true);
                        Session.setname(rs.getString("nama"));
                        //kdbk.lb_uname1.setText(Session.getname());
                        dispose();
                        //
                        break;
                    case "1":
                        //Level 1 untuk Bagian IT
                        JOptionPane.showMessageDialog(null,("Selamat Datang ") + rs.getString("nama"),
                                "Login Bagian IT Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        //
                        keloladata_it kdit = new keloladata_it();
                        kdit.username.setText(usernm);
                        kdit.setVisible(true);
                        user = rs.getString("username");
                        Session.setusername(user);
                        dispose();
                        break;
                    case "2":
                        //Level 2 untuk Kepsek
                        JOptionPane.showMessageDialog(null,("Selamat Datang ") + rs.getString("nama"),
                                "Login Kepala Sekolah Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        //
//                        registrasi regist3 = new registrasi();
//                        regist3.setVisible(true);
                        user = rs.getString("username");
                        Session.setusername(user);
                        dispose();
                        break;    
                    default:
                        break;          
                }
            } else {
                    JOptionPane.showMessageDialog(null, ("Maaf, Username atau Password Salah, Silahkan Coba Lagi!"), 
                        "Login Gagal", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_login = new javax.swing.JPanel();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        remember = new javax.swing.JCheckBox();
        lb_lupapass = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel_login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(244, 243, 244));
        txt_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_username.setBorder(null);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        panel_login.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 313, 430, 62));

        txt_password.setBackground(new java.awt.Color(244, 243, 244));
        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setBorder(null);
        panel_login.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 430, 62));

        btn_login.setBackground(new java.awt.Color(255, 255, 255));
        btn_login.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 18)); // NOI18N
        btn_login.setText("LOGIN");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_loginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_loginMousePressed(evt);
            }
        });
        panel_login.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 520, 40));

        remember.setBackground(new java.awt.Color(255, 255, 255));
        remember.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        remember.setText("Remind Me Later");
        panel_login.add(remember, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, -1));

        lb_lupapass.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        lb_lupapass.setText("Lupa Password ?");
        lb_lupapass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_lupapassMouseClicked(evt);
            }
        });
        panel_login.add(lb_lupapass, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 580, 110, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel2.setText("Belum Punya Akun?");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        panel_login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 580, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        panel_login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void btn_loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseEntered
        // TODO add your handling code here:
         btn_login.setBackground(new Color(46,32,198));  
    }//GEN-LAST:event_btn_loginMouseEntered

    private void btn_loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseExited
        // TODO add your handling code here:
        btn_login.setBackground(new Color(53,40,189));
    }//GEN-LAST:event_btn_loginMouseExited

    private void btn_loginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMousePressed
        // TODO add your handling code here:
        btn_login.setBackground(new Color(46,32,198));
        this.proseslogin();
    }//GEN-LAST:event_btn_loginMousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Hubungi Guru BK untu Menjadi Admin");
    }//GEN-LAST:event_jLabel2MouseClicked

    private void lb_lupapassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_lupapassMouseClicked
        lupapassword lp=new lupapassword();
        lp.setVisible(true);
        lp.setLocationRelativeTo(null);
    }//GEN-LAST:event_lb_lupapassMouseClicked

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lb_lupapass;
    private javax.swing.JPanel panel_login;
    private javax.swing.JCheckBox remember;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
