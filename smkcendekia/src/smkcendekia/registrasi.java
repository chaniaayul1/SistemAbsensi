/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author chani
 */
public class registrasi extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql,leveluser;
    koneksi k;
    /**
     * Creates new form registrasi
     */
    public registrasi() 
    {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }
    
    public void addregistrasi()
    {
        try {
            if (txt_id.getText().equals("") || txt_username.getText().equals("") 
                    || txt_nama.getText().equals("") || txt_nip.getText().equals("") || txt_password.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                String simpan = "insert into admin(idadmin,nip,nama,username,password,level) values ('"+txt_id.getText()+"','"+txt_nip.getText()+"','"+txt_nama.getText()+"','"+txt_username.getText()+"','"+txt_password.getText()+"','"+leveluser+"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Registrasi Anda Berhasil!");
                this.setVisible(false);
                data_admin da=new data_admin();
                da.setVisible(true);
            }
        } catch (HeadlessException | SQLException e) {
           JOptionPane.showMessageDialog(this, "Periksa kembali mengenai NIP atau Akun yang akan dibuat sudah ada","Pesan",JOptionPane.WARNING_MESSAGE);
           hapuslayar();
        }
    }
    
    public void Autonumber()
    {
       try {
            sql="select * from admin order by idadmin desc";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            if (rs.next()) {
                String noid = rs.getString("idadmin").substring(3);
                String AN = "" + (Integer.parseInt(noid) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "00";}
                else if(AN.length()==2)
                {Nol = "0";}
                else if(AN.length()==3)
                {Nol = "";}
               txt_id.setText("ADM" + Nol + AN);
            } else {
               txt_id.setText("ADM001");
            }

           }catch(NumberFormatException | SQLException e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    
    private void hapuslayar()
    {
        txt_nip.setText("");
        txt_nama.setText("");
        txt_username.setText("");
        txt_password.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelRegistrasi = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        lbl_id = new javax.swing.JLabel();
        txt_nip = new javax.swing.JTextField();
        lb_nip = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        lb_password = new javax.swing.JLabel();
        txt_level = new javax.swing.JComboBox<>();
        btn_back = new javax.swing.JButton();
        btn_daftar = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelRegistrasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_id.setEnabled(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 259, -1));

        lbl_id.setText("ID");
        PanelRegistrasi.add(lbl_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, -1, -1));

        txt_nip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nipActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 259, -1));

        lb_nip.setText("NIP");
        PanelRegistrasi.add(lb_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, -1, -1));

        lb_nama.setText("Nama");
        PanelRegistrasi.add(lb_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, -1, -1));

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 259, -1));

        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 259, -1));

        lb_username.setText("Username");
        PanelRegistrasi.add(lb_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, -1, -1));

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 259, -1));

        lb_password.setText("Password");
        PanelRegistrasi.add(lb_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, -1, -1));

        txt_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guru BK", "Bagian IT", "Kepala Sekolah" }));
        txt_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_levelActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(txt_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 259, -1));

        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, -1, -1));

        btn_daftar.setText("Daftar");
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        PanelRegistrasi.add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 530, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/registrasi.png"))); // NOI18N
        PanelRegistrasi.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(PanelRegistrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_nipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nipActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_levelActionPerformed
        String value = txt_level.getSelectedItem().toString();
        if ("Guru BK".equals(value))
        {
            leveluser="0";
        }
        else if ("Bagian IT".equals(value))
        {
            leveluser="1";
        }
        else
        {
            leveluser="2";
        }
    }//GEN-LAST:event_txt_levelActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        data_admin da=new data_admin();
        da.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        // TODO add your handling code here:
        this.addregistrasi();
    }//GEN-LAST:event_btn_daftarActionPerformed

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
            java.util.logging.Logger.getLogger(registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel PanelRegistrasi;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_nip;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_username;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JTextField txt_id;
    private javax.swing.JComboBox<String> txt_level;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nip;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
