/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author chani
 */
public class profiladmin extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    /**
     * Creates new form profiladmin
     */
    public profiladmin() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_nipprofadmin = new javax.swing.JTextField();
        txt_namaprofadmin = new javax.swing.JTextField();
        txt_usernameprofadmin = new javax.swing.JTextField();
        btn_simpanprofadmin = new javax.swing.JButton();
        txt_tulisulangprofadmin = new javax.swing.JPasswordField();
        txt_passlamaprofadmin = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        txt_passbaruprofadmin = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel1.setText("<html> \t<b>Tulis<b> \tulang<b> \tpassword<b>  baru<b> </html ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 100, -1));

        jLabel2.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel2.setText(":");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel3.setText("Nama");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel5.setText("Password Lama");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel6.setText("Password Baru");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel7.setText("NIP");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel8.setText(":");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel9.setText(":");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        jLabel10.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel10.setText(":");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jLabel11.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel11.setText(":");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        jLabel12.setFont(new java.awt.Font("Noto Serif", 0, 12)); // NOI18N
        jLabel12.setText(":");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, -1));
        jPanel1.add(txt_nipprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 510, 30));

        txt_namaprofadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaprofadminActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namaprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 510, 30));
        jPanel1.add(txt_usernameprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 510, 30));

        btn_simpanprofadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofadmin.setText("Simpan");
        btn_simpanprofadmin.setIconTextGap(18);
        btn_simpanprofadmin.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_simpanprofadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofadminActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpanprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 120, 40));

        txt_tulisulangprofadmin.setText("jPasswordField1");
        jPanel1.add(txt_tulisulangprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 510, 30));

        txt_passlamaprofadmin.setText("jPasswordField1");
        jPanel1.add(txt_passlamaprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 510, 30));

        jPasswordField3.setText("jPasswordField1");
        jPanel1.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 510, 30));

        txt_passbaruprofadmin.setText("jPasswordField1");
        jPanel1.add(txt_passbaruprofadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 510, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaprofadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaprofadminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaprofadminActionPerformed

    private void btn_simpanprofadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofadminActionPerformed
        // TODO add your handling code here:
         if(txt_nipprofadmin.getText().equals("")||txt_namaprofadmin.getText().equals("")||txt_usernameprofadmin.getText().equals("")||
        txt_passlamaprofadmin.getText().equals("")||txt_passbaruprofadmin.getText().equals("")||txt_tulisulangprofadmin.getText().equals("")){
             JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong", "Lupa Password", JOptionPane.ERROR_MESSAGE);
        }
         else{
             if(txt_passbaruprofadmin.getText().equals(txt_tulisulangprofadmin.getText())){
                    Object[] obj=new Object[3];
                try{
                    sql = "SELECT * FROM admin WHERE username='"+txt_usernameprofadmin.getText()+"' AND nip='"+txt_nipprofadmin.getText()+"' AND nama='"+txt_namaprofadmin.getText()+"' "
                            + "AND status='Aktif'";
                    rs = stat.executeQuery(sql);
                    while(rs.next()){
                        obj[0]=rs.getString("nip");
                        obj[1]=rs.getString("nama");
                        obj[2]=rs.getString("username");
                        System.out.println(obj[0]);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(lupapassword.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             }
         }
    }//GEN-LAST:event_btn_simpanprofadminActionPerformed

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
            java.util.logging.Logger.getLogger(profiladmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profiladmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profiladmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profiladmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profiladmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_simpanprofadmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JTextField txt_namaprofadmin;
    private javax.swing.JTextField txt_nipprofadmin;
    private javax.swing.JPasswordField txt_passbaruprofadmin;
    private javax.swing.JPasswordField txt_passlamaprofadmin;
    private javax.swing.JPasswordField txt_tulisulangprofadmin;
    private javax.swing.JTextField txt_usernameprofadmin;
    // End of variables declaration//GEN-END:variables
}