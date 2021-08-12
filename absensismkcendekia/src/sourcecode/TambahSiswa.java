/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcecode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author chani
 */
public class TambahSiswa extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi k;
    
    public TambahSiswa() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }
   
    public void simpandata(){
        String jeniskelamin = null;
        if(rb1.isSelected()){
            jeniskelamin = "perempuan";
        } else if (rb2.isSelected()){
            jeniskelamin = "Laki-Laki";
        }
        
        try {
            if (txt_nis.getText().equals("") || txt_nk.getText().equals("") 
                    || txt_nama.getText().equals("") || txt_alamat.getText().equals("") || txt_email.getText().equals(" ") 
                    || txt_notlp.getText().equals(" ")|| txt_walikelas.getText().equals(" ")|| txt_noortu.getText().equals(" ")
                    || txt_namaortu.getText().equals(" ")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                String simpan = "insert into siswa values ('"+txt_nis.getText()
                        +"','"+txt_nk.getText()
                        +"','"+txt_nama.getText()
                        +"','"+txt_alamat.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_notlp.getText()
                        +"','"+txt_email.getText()
                        +"','"+txt_walikelas.getText()
                        +"','"+txt_namaortu.getText()
                        +"','"+txt_noortu.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Berhasil disimpan!");
                this.setVisible(false);
                DataSiswa dtsiswa=new DataSiswa();
                dtsiswa.setVisible(true);
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Data Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
           hapuslayar();
           //System.out.println(e);
        }
    }
    
    private void hapuslayar(){
        txt_nis.setText(" ");
        txt_nk.setText(" ");
        txt_nama.setText(" ");
        txt_alamat.setText(" ");
        txt_buttonJK.clearSelection();
        txt_notlp.setText("");
        txt_email.setText("");
        txt_walikelas.setText("");
        txt_namaortu.setText("");
        txt_noortu.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_buttonJK = new javax.swing.ButtonGroup();
        panelsiswa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nis = new javax.swing.JTextField();
        txt_nk = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_notlp = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_walikelas = new javax.swing.JTextField();
        txt_namaortu = new javax.swing.JTextField();
        txt_noortu = new javax.swing.JTextField();
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
        button_simpan = new javax.swing.JButton();
        button_kembali = new javax.swing.JButton();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelsiswa.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setText("TAMBAH SISWA");

        txt_nis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nisActionPerformed(evt);
            }
        });

        txt_nk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nkActionPerformed(evt);
            }
        });

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        txt_notlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_notlpActionPerformed(evt);
            }
        });

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        txt_walikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_walikelasActionPerformed(evt);
            }
        });

        txt_namaortu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaortuActionPerformed(evt);
            }
        });

        txt_noortu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noortuActionPerformed(evt);
            }
        });

        jLabel2.setText("NIS");

        jLabel3.setText("NK");

        jLabel4.setText("Nama");

        jLabel5.setText("Alamat");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("No.Tlp");

        jLabel8.setText("Email");

        jLabel9.setText("Walikelas");

        jLabel10.setText("Nama Ortu");

        jLabel11.setText("No.Ortu");

        button_simpan.setText("Simpan Data");
        button_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_simpanActionPerformed(evt);
            }
        });

        button_kembali.setText("Kembali");
        button_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_kembaliActionPerformed(evt);
            }
        });

        txt_buttonJK.add(rb1);
        rb1.setText("Perempuan");
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });

        txt_buttonJK.add(rb2);
        rb2.setText("Laki-Laki");

        javax.swing.GroupLayout panelsiswaLayout = new javax.swing.GroupLayout(panelsiswa);
        panelsiswa.setLayout(panelsiswaLayout);
        panelsiswaLayout.setHorizontalGroup(
            panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsiswaLayout.createSequentialGroup()
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsiswaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(46, 46, 46)
                        .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_noortu)
                            .addComponent(txt_namaortu)
                            .addComponent(txt_walikelas)
                            .addComponent(txt_email)
                            .addComponent(txt_notlp)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_nama)
                            .addComponent(txt_nk)
                            .addComponent(txt_nis)
                            .addGroup(panelsiswaLayout.createSequentialGroup()
                                .addComponent(rb1)
                                .addGap(18, 18, 18)
                                .addComponent(rb2)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelsiswaLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(button_simpan)
                        .addGap(46, 46, 46)
                        .addComponent(button_kembali)
                        .addGap(0, 208, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelsiswaLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelsiswaLayout.setVerticalGroup(
            panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsiswaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsiswaLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rb1)
                    .addComponent(rb2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_notlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_walikelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_namaortu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_noortu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(panelsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_simpan)
                    .addComponent(button_kembali))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nisActionPerformed

    private void txt_nkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nkActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_notlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notlpActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_walikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_walikelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_walikelasActionPerformed

    private void txt_namaortuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaortuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaortuActionPerformed

    private void txt_noortuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noortuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noortuActionPerformed

    private void button_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_kembaliActionPerformed
        this.setVisible(false);
        DataSiswa dtsiswa=new DataSiswa();
        dtsiswa.setVisible(true);
    }//GEN-LAST:event_button_kembaliActionPerformed

    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb1ActionPerformed

    private void button_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_simpanActionPerformed
        // TODO add your handling code here:
        this.simpandata();
    }//GEN-LAST:event_button_simpanActionPerformed

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
            java.util.logging.Logger.getLogger(TambahSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_kembali;
    private javax.swing.JButton button_simpan;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelsiswa;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.ButtonGroup txt_buttonJK;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_namaortu;
    private javax.swing.JTextField txt_nis;
    private javax.swing.JTextField txt_nk;
    private javax.swing.JTextField txt_noortu;
    private javax.swing.JTextField txt_notlp;
    private javax.swing.JTextField txt_walikelas;
    // End of variables declaration//GEN-END:variables
}
