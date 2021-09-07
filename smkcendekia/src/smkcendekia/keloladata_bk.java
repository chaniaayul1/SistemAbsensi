/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PC
 */
public class keloladata_bk extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql, leveluser;
    koneksi k;
    DefaultTableModel model;
    
    public keloladata_bk() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        resetpanel();
        //setExtendedState(MAXIMIZED_BOTH);
        this.toFront();
        settable();
        LayerPane.removeAll();
        Background.removeAll();
        Background.repaint();
        Background.revalidate();
    }
    
     //======SISWA======//
    //FUNGSI ATAU PROSEDUR MENAMPILKAN DATA SISWA PADA TABEL
    public void tampilsiswa(){        
        model = new DefaultTableModel ( );
        tabel_siswa.setModel(model);
        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("NK");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");

         try{
           stat = con.createStatement( );
           sql  = "Select * from siswa";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
            
             tabel_siswa.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //PROSEDUR MENGHAPUS DATA SISWA PADA TABEL
    public void deletedatasiswa(){
        model = new DefaultTableModel ( );
        tabel_siswa.setModel(model);
        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("NK");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM siswa WHERE nis='"+txt_searchsiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
            while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Siswa Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE SISWA
    public void tampilprofilesiswa(){
        try {
            stat = con.createStatement( );
            sql  = "SELECT * FROM siswa WHERE nis='"+Session.getnissiswa()+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[10];
                obj[0] =rs.getString("nis");
                txt_nissiswa.setText((String) obj[0]);
                obj[1] =rs.getString("nk");
                txt_nksiswa.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namasiswa.setText((String) obj[2]);
                obj[3] =rs.getString("alamat");
                txt_alamatsiswa.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_gendersiswa.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpsiswa.setText((String) obj[5]);
                obj[6] =rs.getString("email");
                txt_emailsiswa.setText((String) obj[6]);
                obj[7] =rs.getString("nip");
                txt_walassiswa.setText((String) obj[7]);
                obj[8] =rs.getString("namaortu");
                txt_ortusiswa.setText((String) obj[8]);
                obj[9] =rs.getString("noortu");
                txt_noortusiswa.setText((String) obj[9]); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Siswa Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE SISWA
    public void editprofilesiswa(boolean check){
        if (check==true){
            txt_nissiswa.setEnabled(true);
            txt_nksiswa.setEnabled(true);
            txt_namasiswa.setEnabled(true);
            txt_alamatsiswa.setEnabled(true);
            txt_gendersiswa.setEnabled(true);
            txt_notelpsiswa.setEnabled(true);
            txt_emailsiswa.setEnabled(true);
            txt_walassiswa.setEnabled(true);
            txt_ortusiswa.setEnabled(true);
            txt_noortusiswa.setEnabled(true);
        }
        else{
            txt_nissiswa.setEnabled(false);
            txt_nksiswa.setEnabled(false);
            txt_namasiswa.setEnabled(false);
            txt_alamatsiswa.setEnabled(false);
            txt_gendersiswa.setEnabled(false);
            txt_notelpsiswa.setEnabled(false);
            txt_emailsiswa.setEnabled(false);
            txt_walassiswa.setEnabled(false);
            txt_ortusiswa.setEnabled(false);
            txt_noortusiswa.setEnabled(false);
        }
    }
    
    public void simpanprofilesiswa(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE siswa set   nis='"+txt_nissiswa.getText()+"', "
                                                                        + "nk='"+txt_nksiswa.getText()+"', "
                                                                        + "nama='"+txt_namasiswa.getText()+"', "
                                                                        + "alamat='"+txt_alamatsiswa.getText()+"', "
                                                                        + "jeniskelamin='"+txt_gendersiswa.getText()+"', "
                                                                        + "notlp='"+txt_notelpsiswa.getText()+"', "
                                                                        + "email='"+txt_emailsiswa.getText()+"', "
                                                                        + "nip='"+txt_walassiswa.getText()+"', "
                                                                        + "namaortu='"+txt_ortusiswa.getText()+"', "
                                                                        + "noortu='"+txt_noortusiswa.getText()+"' "
                                                                        + "WHERE nis='"+txt_nissiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
            
            while(rs.next ()){
                Object[ ] obj = new Object[10];
                obj[0] =rs.getString("nis");
                txt_nissiswa.setText((String) obj[0]);
                obj[1] =rs.getString("nk");
                txt_nksiswa.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namasiswa.setText((String) obj[2]);
                obj[3] =rs.getString("alamat");
                txt_alamatsiswa.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_gendersiswa.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpsiswa.setText((String) obj[5]);
                obj[6] =rs.getString("email");
                txt_emailsiswa.setText((String) obj[6]);
                obj[7] =rs.getString("nip");
                txt_walassiswa.setText((String) obj[7]);
                obj[8] =rs.getString("namaortu");
                txt_ortusiswa.setText((String) obj[8]);
                obj[9] =rs.getString("noortu");
                txt_noortusiswa.setText((String) obj[9]); 
            }
            JOptionPane.showMessageDialog(null, ("Data Siswa Berhasil di Update"), 
            "Data Profile Siswa", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Siswa Gagal di Update"), 
            "Data Profile Siswa Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR MENCARI DATA SISWA PADA TABEL
    public void querysearchsiswa(){
        int row = tabel_siswa.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from siswa WHERE nis='"+txt_searchsiswa.getText()+"'";
                   
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void querysearchkliksiswa(){
        int i = tabel_siswa.getSelectedRow();
        if(i>-1){
            txt_searchsiswa.setText(model.getValueAt(i, 1).toString());
            Session.setnissiswa(txt_searchsiswa.getText());
        }
    }
        
    //======GURU======//
    //VOID MENAMPILKAN DATA GURU PADA TABEL
    public void tampilguru(){        
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("NK");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");

         try{
           stat = con.createStatement( );
           sql  = "Select * from guru";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nip");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
            
             tabel_guru.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //PROSEDUR MENGHAPUS DATA GURU PADA TABEL
    public void deletedataguru(){
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("NK");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM guru WHERE nip='"+txt_searchguru.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
            while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nip");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Guru Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE GURU
    public void tampilprofileguru(){
        try {
            if (saveadm!=null)
            {
                stat = con.createStatement( );
                sql  = "SELECT * FROM guru WHERE nip='"+Session.getnipguru()+"'";
            
                rs   = stat.executeQuery(sql);
                while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] =rs.getString("nip");
                    txt_nipguru.setText((String) obj[0]);
                    obj[1] =rs.getString("nk");
                    txt_nkguru.setText((String) obj[1]);
                    obj[2] =rs.getString("nama");
                    txt_namaguru.setText((String) obj[2]);
                    obj[3] =rs.getString("email");
                    txt_emailguru.setText((String) obj[3]);
                    obj[4] =rs.getString("jeniskelamin");
                    txt_jkguru.setText((String) obj[4]);
                    obj[5] =rs.getString("notlp");
                    txt_noteleponguru.setText((String) obj[5]);
                    obj[6] =rs.getString("alamat");
                    txt_alamatguru.setText((String) obj[6]);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Guru Gagal", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE GURU
    public void editprofileguru(boolean check){
        if (check==true){
            txt_nipguru.setEnabled(true);
            txt_nkguru.setEnabled(true);
            txt_namaguru.setEnabled(true);
            txt_emailguru.setEnabled(true);
            txt_jkguru.setEnabled(true);
            txt_noteleponguru.setEnabled(true);
            txt_alamatguru.setEnabled(true);
        }
        else{
            txt_nipguru.setEnabled(false);
            txt_nkguru.setEnabled(false);
            txt_namaguru.setEnabled(false);
            txt_emailguru.setEnabled(false);
            txt_jkguru.setEnabled(false);
            txt_noteleponguru.setEnabled(false);
            txt_alamatguru.setEnabled(false);
        }
    }
    
    // VOID SIMPAN GURU
    public void simpanprofileguru(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE guru set   nip='"+txt_nipguru.getText()+"', "
                                                                        + "nk='"+txt_nkguru.getText()+"', "
                                                                        + "nama='"+txt_namaguru.getText()+"', "
                                                                        + "email='"+txt_emailguru.getText()+"', "
                                                                        + "jeniskelamin='"+txt_jkguru.getText()+"', "
                                                                        + "notlp='"+txt_noteleponguru.getText()+"', "
                                                                        + "alamat='"+txt_alamatguru.getText()+"'"
                                                                        + "WHERE nip='"+txt_nipguru.getText()+"'");
            rs   = stat.executeQuery(sql);
            
            while(rs.next ()){
                Object[ ] obj = new Object[10];
                obj[0] =rs.getString("nip");
                txt_nipguru.setText((String) obj[0]);
                obj[1] =rs.getString("nk");
                txt_nkguru.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namaguru.setText((String) obj[2]);
                obj[3] =rs.getString("email");
                txt_emailguru.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_jkguru.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_noteleponguru.setText((String) obj[5]);
                obj[6] =rs.getString("alamat");
                txt_alamatguru.setText((String) obj[6]); 
            }
            JOptionPane.showMessageDialog(null, ("Data Guru Berhasil di Update"), 
            "Data Profile Guru", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Guru Gagal di Update"), 
            "Data Profile Guru Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR MENCARI DATA GURU PADA TABEL
    public void querysearchguru(){
        int row = tabel_guru.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from guru WHERE nip='"+txt_searchguru.getText()+"'";
                   
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nip");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void querysearchklikguru(){
        int i = tabel_guru.getSelectedRow();
        if(i>-1){
            txt_searchguru.setText(model.getValueAt(i, 1).toString());
            Session.setnipguru(txt_searchguru.getText());
        }
    }
    

    //======ADMIN======//
    //VOID MENAMPILKAN DATA ADMIN PADA TABEL
    public void tampiladmin(){        
        model = new DefaultTableModel ( );
        tabel_admin.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Admin");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Status");
        
         try{
           stat = con.createStatement( );
           sql  = "Select * from admin";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idadmin");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("username");
                obj[5] = rs.getString("password");
                String getlevel = rs.getString("level");
                if ("0".equals(getlevel))
                {
                    obj[6]="Guru BK";
                }
                else if("1".equals(getlevel))
                {
                    obj[6]="Bagian IT";
                }
                else
                {
                    obj[6]="Kepala Sekolah";
                }
                model.addRow(obj);
                no++;
            }
            
             tabel_admin.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }

    //PROSEDUR MENGHAPUS DATA ADMIN PADA TABEL
    public void deletedataadmin(){
        model = new DefaultTableModel();
        tabel_admin.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Admin");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Status");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM admin WHERE idadmin='"+saveadm.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
            while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idadmin");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("username");
                obj[5] = rs.getString("password");
                String getlevel = rs.getString("level");
                if ("0".equals(getlevel))
                {
                    obj[6]="Guru BK";
                }
                else if("1".equals(getlevel))
                {
                    obj[6]="Bagian IT";
                }
                else
                {
                    obj[6]="Kepala Sekolah";
                }
                model.addRow(obj);
                no++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Admin Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE GURU
    public void tampilprofileadmin(){
        try {
            Session.setnipguru(null);
            if(saveadm.getText()!=null){
                stat = con.createStatement( );
                sql  = "SELECT * FROM guru WHERE nip='"+Session.getnipadmin()+"'";
            
                rs   = stat.executeQuery(sql);
                while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] =rs.getString("nip");
                    txt_nipguru.setText((String) obj[0]);
                    obj[1] =rs.getString("nk");
                    txt_nkguru.setText((String) obj[1]);
                    obj[2] =rs.getString("nama");
                    txt_namaguru.setText((String) obj[2]);
                    obj[3] =rs.getString("email");
                    txt_emailguru.setText((String) obj[3]);
                    obj[4] =rs.getString("jeniskelamin");
                    txt_jkguru.setText((String) obj[4]);
                    obj[5] =rs.getString("notlp");
                    txt_noteleponguru.setText((String) obj[5]);
                    obj[6] =rs.getString("alamat");
                    txt_alamatguru.setText((String) obj[6]);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Profile Admin Gagal", JOptionPane.INFORMATION_MESSAGE);
            }    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Guru Gagal", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    //PROSEDUR MENCARI DATA ADMIN PADA TABEL
    public void querysearchadmin(){
        int row = tabel_admin.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from admin WHERE nip='"+txt_searchadmin.getText()+"'";
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idadmin");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("username");
                obj[5] = rs.getString("password");
                String getlevel = rs.getString("level");
                if ("0".equals(getlevel))
                {
                    obj[6]="Guru BK";
                }
                else if("1".equals(getlevel))
                {
                    obj[6]="Bagian IT";
                }
                else
                {
                    obj[6]="Kepala Sekolah";
                }
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void querysearchklikadmin(){
        int i = tabel_admin.getSelectedRow();
        if(i>-1){
            saveadm.setText(model.getValueAt(i, 1).toString());
            txt_searchadmin.setText(model.getValueAt(i, 2).toString());
            Session.setnipadmin(txt_searchadmin.getText());
        }
    }
    
    // Kelola DATA TAMBAH SISWA 
     private void hapuslayar(){
        txt_nis.setText(" ");
        txt_nk.setText(" ");
        txt_nama.setText(" ");
        txt_alamat.setText(" ");
        txt_buttonJK.clearSelection();
        txt_tlp.setText("");
        txt_email.setText("");
        txt_walikelas.setText("");
        txt_namaortu.setText("");
        txt_noortu.setText("");
    }
     
    // KELOLA DAFTAR USER
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
    
     private void hapuslayarregis()
    {
        txt_regisnip.setText("");
        txt_regisnama.setText("");
        txt_regisusername.setText("");
        txt_regispassword.setText("");
    }
    
      public void addregistrasi()
    {
        try {
            if (txt_id.getText().equals("") || txt_regisusername.getText().equals("") 
                    || txt_regisnama.getText().equals("") || txt_regisnip.getText().equals("") || txt_regispassword.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                String simpan = "insert into admin(idadmin,nip,nama,username,password,level) values ('"+txt_id.getText()+"','"+txt_regisnip.getText()+"','"+txt_regisnama.getText()+"','"+txt_regisusername.getText()+"','"+txt_regispassword.getText()+"','"+leveluser+"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Registrasi Anda Berhasil!");
                
            }
        } catch (HeadlessException | SQLException e) {
           JOptionPane.showMessageDialog(this, "Periksa kembali mengenai NIP atau Akun yang akan dibuat sudah ada","Pesan",JOptionPane.WARNING_MESSAGE);
           hapuslayarregis();
        }
    }
      
    
    //PERIPHERAL
    public void switchpanel(JLayeredPane panel){
        LayerPane.removeAll();
        LayerPane.add(panel);
        LayerPane.repaint();
        LayerPane.revalidate();
    }

    //reset button
    public void resetbutton(){
        panelbutton.add(btn_dashboard);
        panelbutton.add(btn_datasiswa);
        panelbutton.add(btn_dataguru);
        panelbutton.add(btn_datakelas);
        panelbutton.add(btn_dataadmin);
        panelbutton.add(btn_dataabsensi);
        panelbutton.add(btn_lapabsen);
        panelbutton.add(btn_logout);
        panelbutton.repaint();
        panelbutton.revalidate();
    }
    
    //settable
    public void settable(){
        //SETTING TRANSPARASI SCROLLPANE TABEL SISWA
        ((DefaultTableCellRenderer)tabel_siswa.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_siswa.setGridColor(Color.BLACK);
        tabel_siswa.setForeground(Color.BLACK);
        tabsiswa.setOpaque(false);
        tabsiswa.getViewport().setOpaque(false);
        tabsiswa.setBorder(null);
        tabsiswa.setViewportBorder(null);
        tabel_siswa.setShowGrid(true);
        
        //SETTING TRANPARANSI SCROLLPANE TABEL GURU
        ((DefaultTableCellRenderer)tabel_guru.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_guru.setGridColor(Color.BLACK);
        tabel_guru.setForeground(Color.BLACK);
        tabguru.setOpaque(false);
        tabguru.getViewport().setOpaque(false);
        tabguru.setBorder(null);
        tabguru.setViewportBorder(null);
        tabel_guru.setShowGrid(true);
        
        //SETTING TRANSPARASI SCROLLPANE NILAI
        ((DefaultTableCellRenderer)tabel_admin.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_admin.setGridColor(Color.BLACK);
        tabel_admin.setForeground(Color.BLACK);
        tabadmin.setOpaque(false);
        tabadmin.getViewport().setOpaque(false);
        tabadmin.setBorder(null);
        tabadmin.setViewportBorder(null);
        tabel_admin.setShowGrid(true);
    }
  
    //RESET ALL PANEL
    public void resetpanel(){
        datasiswa.setVisible(false);
        dataguru.setVisible(false);
        datakelas.setVisible(false);
        dataabsensi.setVisible(false);
        datalapabsensi.setVisible(false);
        profilesiswa.setVisible(false);
        dataadmin.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_buttonJK = new javax.swing.ButtonGroup();
        txt_buttonjktambahguru = new javax.swing.ButtonGroup();
        PanelUtama = new javax.swing.JPanel();
        panelbutton = new javax.swing.JPanel();
        btn_dashboard = new javax.swing.JPanel();
        lbdashboard = new javax.swing.JLabel();
        icondashboard = new javax.swing.JLabel();
        btn_datasiswa = new javax.swing.JPanel();
        lbsiswa = new javax.swing.JLabel();
        iconsiswa = new javax.swing.JLabel();
        btn_dataguru = new javax.swing.JPanel();
        lbguru = new javax.swing.JLabel();
        iconguru = new javax.swing.JLabel();
        btn_datakelas = new javax.swing.JPanel();
        lbkelas = new javax.swing.JLabel();
        iconkelas = new javax.swing.JLabel();
        btn_dataadmin = new javax.swing.JPanel();
        lbkelas1 = new javax.swing.JLabel();
        iconadmin = new javax.swing.JLabel();
        btn_dataabsensi = new javax.swing.JPanel();
        lbabsen = new javax.swing.JLabel();
        iconabsen = new javax.swing.JLabel();
        btn_lapabsen = new javax.swing.JPanel();
        lblapabsen = new javax.swing.JLabel();
        iconlapabsen = new javax.swing.JLabel();
        btn_logout = new javax.swing.JPanel();
        lblogout = new javax.swing.JLabel();
        iconlogout = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        LayerPane = new javax.swing.JLayeredPane();
        datasiswa = new javax.swing.JLayeredPane();
        panelsiswa = new javax.swing.JPanel();
        txt_searchsiswa = new javax.swing.JTextField();
        btn_searchsiswa = new javax.swing.JButton();
        btn_lihatsiswa = new javax.swing.JButton();
        btn_hapussiswa = new javax.swing.JButton();
        tabsiswa = new javax.swing.JScrollPane();
        tabel_siswa = new javax.swing.JTable();
        btn_refreshdatasiswa = new javax.swing.JButton();
        btn_tambahsiswa = new javax.swing.JButton();
        bgsiswa = new javax.swing.JLabel();
        profilesiswa = new javax.swing.JLayeredPane();
        panelprofilesiswa = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txt_rfidsiswa = new javax.swing.JTextField();
        txt_nissiswa = new javax.swing.JTextField();
        txt_nksiswa = new javax.swing.JTextField();
        txt_namasiswa = new javax.swing.JTextField();
        txt_alamatsiswa = new javax.swing.JTextField();
        txt_gendersiswa = new javax.swing.JTextField();
        txt_notelpsiswa = new javax.swing.JTextField();
        txt_emailsiswa = new javax.swing.JTextField();
        txt_walassiswa = new javax.swing.JTextField();
        txt_ortusiswa = new javax.swing.JTextField();
        txt_noortusiswa = new javax.swing.JTextField();
        btn_editprofilesiswa = new javax.swing.JButton();
        btn_simpanprofilesiswa = new javax.swing.JButton();
        btn_kembaliprofilesiswa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bgprofilesiswa = new javax.swing.JLabel();
        dataguru = new javax.swing.JLayeredPane();
        panelguru = new javax.swing.JPanel();
        txt_searchguru = new javax.swing.JTextField();
        btn_searchguru = new javax.swing.JButton();
        tabguru = new javax.swing.JScrollPane();
        tabel_guru = new javax.swing.JTable();
        btn_lihatguru = new javax.swing.JButton();
        brn_hapusguru = new javax.swing.JButton();
        btn_refreshdataguru = new javax.swing.JButton();
        btn_tambahguru = new javax.swing.JButton();
        bgguru = new javax.swing.JLabel();
        profileguru = new javax.swing.JLayeredPane();
        panelprofileguru = new javax.swing.JPanel();
        lb_gambar = new javax.swing.JLabel();
        lb_nk = new javax.swing.JLabel();
        txt_noteleponguru = new javax.swing.JTextField();
        txt_nipguru = new javax.swing.JTextField();
        txt_nkguru = new javax.swing.JTextField();
        txt_namaguru = new javax.swing.JTextField();
        lb_nama = new javax.swing.JLabel();
        lb_email = new javax.swing.JLabel();
        lb_titik1 = new javax.swing.JLabel();
        lb_notelepon = new javax.swing.JLabel();
        txt_emailguru = new javax.swing.JTextField();
        lb_alamat = new javax.swing.JLabel();
        lb_jk = new javax.swing.JLabel();
        txt_alamatguru = new javax.swing.JTextField();
        txt_jkguru = new javax.swing.JTextField();
        lb_nip = new javax.swing.JLabel();
        lb_titik2 = new javax.swing.JLabel();
        lb_titik3 = new javax.swing.JLabel();
        lb_titik4 = new javax.swing.JLabel();
        lb_titik5 = new javax.swing.JLabel();
        lb_titik6 = new javax.swing.JLabel();
        lb_titik7 = new javax.swing.JLabel();
        btn_simpanprofileguru = new javax.swing.JButton();
        btn_editprofileguru = new javax.swing.JButton();
        btn_kembaliprofileguru = new javax.swing.JButton();
        bgprofileguru = new javax.swing.JLabel();
        datakelas = new javax.swing.JLayeredPane();
        panelkelas = new javax.swing.JPanel();
        bgkelas = new javax.swing.JLabel();
        dataadmin = new javax.swing.JLayeredPane();
        paneladmin = new javax.swing.JPanel();
        txt_searchadmin = new javax.swing.JTextField();
        btn_lihatadmin = new javax.swing.JButton();
        btn_editadmin = new javax.swing.JButton();
        btn_registrasi = new javax.swing.JButton();
        btn_hapusadmin = new javax.swing.JButton();
        tabadmin = new javax.swing.JScrollPane();
        tabel_admin = new javax.swing.JTable();
        bgadmin = new javax.swing.JLabel();
        saveadm = new javax.swing.JLabel();
        dataabsensi = new javax.swing.JLayeredPane();
        panelabsensi = new javax.swing.JPanel();
        bgabsensi = new javax.swing.JLabel();
        datalapabsensi = new javax.swing.JLayeredPane();
        panellapabsensi = new javax.swing.JPanel();
        bglapabsensi = new javax.swing.JLabel();
        form_siswa = new javax.swing.JLayeredPane();
        panelformsiswa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_nis = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_nk = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_walikelas = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_tlp = new javax.swing.JTextField();
        txt_noortu = new javax.swing.JTextField();
        txt_RFID = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        txt_namaortu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btn_simpantambahsiswa = new javax.swing.JButton();
        bgtambahsiswa = new javax.swing.JLabel();
        form_guru = new javax.swing.JLayeredPane();
        panelformguru = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txt_nktambahguru = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txt_namatambahguru = new javax.swing.JTextField();
        txt_emailtambahguru = new javax.swing.JTextField();
        txt_alamattambahguru = new javax.swing.JTextField();
        txt_tlptambahguru = new javax.swing.JTextField();
        txt_niptambahguru = new javax.swing.JTextField();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        btn_simpantambahguru = new javax.swing.JButton();
        bgtambahguru = new javax.swing.JLabel();
        form_registrasi = new javax.swing.JLayeredPane();
        panelformregistrasi = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txt_regisnip = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txt_regisnama = new javax.swing.JTextField();
        txt_regisusername = new javax.swing.JTextField();
        txt_regispassword = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        btn_daftar = new javax.swing.JButton();
        txt_level = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        bgtambahguru1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelUtama.setOpaque(false);
        PanelUtama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelbutton.setBackground(new java.awt.Color(37, 112, 183));
        panelbutton.setOpaque(false);
        panelbutton.setLayout(null);

        btn_dashboard.setBackground(new java.awt.Color(37, 112, 183));
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dashboardMousePressed(evt);
            }
        });
        btn_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbdashboard.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbdashboard.setForeground(new java.awt.Color(255, 255, 255));
        lbdashboard.setText("Dashboard");
        btn_dashboard.add(lbdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        icondashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icondashboard.png"))); // NOI18N
        btn_dashboard.add(icondashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_dashboard);
        btn_dashboard.setBounds(0, 0, 260, 52);

        btn_datasiswa.setBackground(new java.awt.Color(37, 112, 183));
        btn_datasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_datasiswaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_datasiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_datasiswaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_datasiswaMousePressed(evt);
            }
        });
        btn_datasiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbsiswa.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbsiswa.setForeground(new java.awt.Color(255, 255, 255));
        lbsiswa.setText("Data Siswa");
        btn_datasiswa.add(lbsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconsiswa.png"))); // NOI18N
        btn_datasiswa.add(iconsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_datasiswa);
        btn_datasiswa.setBounds(0, 54, 260, 53);

        btn_dataguru.setBackground(new java.awt.Color(37, 112, 183));
        btn_dataguru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataguruMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dataguruMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dataguruMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dataguruMousePressed(evt);
            }
        });
        btn_dataguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbguru.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbguru.setForeground(new java.awt.Color(255, 255, 255));
        lbguru.setText("Data Guru");
        btn_dataguru.add(lbguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconguru.png"))); // NOI18N
        btn_dataguru.add(iconguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_dataguru);
        btn_dataguru.setBounds(0, 109, 260, 53);

        btn_datakelas.setBackground(new java.awt.Color(37, 112, 183));
        btn_datakelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_datakelasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_datakelasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_datakelasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_datakelasMousePressed(evt);
            }
        });
        btn_datakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbkelas.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbkelas.setForeground(new java.awt.Color(255, 255, 255));
        lbkelas.setText("Data Kelas");
        btn_datakelas.add(lbkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconkelas.png"))); // NOI18N
        btn_datakelas.add(iconkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_datakelas);
        btn_datakelas.setBounds(0, 164, 260, 53);

        btn_dataadmin.setBackground(new java.awt.Color(37, 112, 183));
        btn_dataadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataadminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dataadminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dataadminMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dataadminMousePressed(evt);
            }
        });
        btn_dataadmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbkelas1.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbkelas1.setForeground(new java.awt.Color(255, 255, 255));
        lbkelas1.setText("Data Admin");
        btn_dataadmin.add(lbkelas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconadmin.png"))); // NOI18N
        btn_dataadmin.add(iconadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_dataadmin);
        btn_dataadmin.setBounds(0, 219, 260, 53);

        btn_dataabsensi.setBackground(new java.awt.Color(37, 112, 183));
        btn_dataabsensi.setPreferredSize(new java.awt.Dimension(161, 50));
        btn_dataabsensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataabsensiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dataabsensiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dataabsensiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dataabsensiMousePressed(evt);
            }
        });
        btn_dataabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbabsen.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lbabsen.setForeground(new java.awt.Color(255, 255, 255));
        lbabsen.setText("Data Absen");
        btn_dataabsensi.add(lbabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconabsensi.png"))); // NOI18N
        btn_dataabsensi.add(iconabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_dataabsensi);
        btn_dataabsensi.setBounds(0, 274, 260, 53);

        btn_lapabsen.setBackground(new java.awt.Color(37, 112, 183));
        btn_lapabsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lapabsenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_lapabsenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_lapabsenMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_lapabsenMousePressed(evt);
            }
        });
        btn_lapabsen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblapabsen.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lblapabsen.setForeground(new java.awt.Color(255, 255, 255));
        lblapabsen.setText("Data Laporan Absen");
        btn_lapabsen.add(lblapabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconlapabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconlapabsensi.png"))); // NOI18N
        btn_lapabsen.add(iconlapabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, -1, -1));

        panelbutton.add(btn_lapabsen);
        btn_lapabsen.setBounds(0, 329, 260, 53);

        btn_logout.setBackground(new java.awt.Color(37, 112, 183));
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_logoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_logoutMousePressed(evt);
            }
        });
        btn_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblogout.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lblogout.setForeground(new java.awt.Color(255, 255, 255));
        lblogout.setText("Logout");
        btn_logout.add(lblogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconlogout.png"))); // NOI18N
        btn_logout.add(iconlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_logout);
        btn_logout.setBounds(0, 385, 260, 53);

        PanelUtama.add(panelbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 260, 658));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(39, 113, 184));
        username.setText("INI USERNAME");
        PanelUtama.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 30, 190, 40));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/listmenu.png"))); // NOI18N
        PanelUtama.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        LayerPane.setEnabled(false);
        LayerPane.setFocusable(false);
        LayerPane.setOpaque(true);
        LayerPane.setLayout(new java.awt.CardLayout());

        datasiswa.setEnabled(false);
        datasiswa.setMinimumSize(new java.awt.Dimension(1366, 768));
        datasiswa.setOpaque(true);
        datasiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsiswa.setLayout(null);
        panelsiswa.add(txt_searchsiswa);
        txt_searchsiswa.setBounds(190, 110, 710, 30);

        btn_searchsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchsiswa.setText("Search");
        btn_searchsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchsiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_searchsiswa);
        btn_searchsiswa.setBounds(910, 110, 100, 30);

        btn_lihatsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatsiswa.setText("Lihat Data");
        btn_lihatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatsiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_lihatsiswa);
        btn_lihatsiswa.setBounds(770, 560, 120, 40);

        btn_hapussiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapussiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_hapussiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/delete.png"))); // NOI18N
        btn_hapussiswa.setText("Hapus");
        btn_hapussiswa.setIconTextGap(18);
        btn_hapussiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapussiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_hapussiswa);
        btn_hapussiswa.setBounds(930, 560, 120, 40);

        tabel_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "NIS", "NK", "Jenis Kelamin", "Nama"
            }
        ));
        tabel_siswa.setRowHeight(20);
        tabel_siswa.setRowMargin(2);
        tabel_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_siswaMouseClicked(evt);
            }
        });
        tabsiswa.setViewportView(tabel_siswa);

        panelsiswa.add(tabsiswa);
        tabsiswa.setBounds(50, 160, 1010, 380);

        btn_refreshdatasiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshdatasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshdatasiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_refreshdatasiswa);
        btn_refreshdatasiswa.setBounds(1020, 110, 40, 30);

        btn_tambahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambahsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_tambahsiswa.setText("Add Siswa");
        btn_tambahsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahsiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_tambahsiswa);
        btn_tambahsiswa.setBounds(50, 110, 130, 30);

        bgsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelsiswa.png"))); // NOI18N
        panelsiswa.add(bgsiswa);
        bgsiswa.setBounds(0, 0, 1107, 658);

        datasiswa.add(panelsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datasiswa, "card2");

        profilesiswa.setOpaque(true);
        profilesiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofilesiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/graduated1.png"))); // NOI18N
        jLabel16.setText("jLabel16");
        panelprofilesiswa.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 310, 270));

        txt_rfidsiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_rfidsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_rfidsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_rfidsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 550, 30));

        txt_nissiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nissiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nissiswa.setEnabled(false);
        panelprofilesiswa.add(txt_nissiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 550, 30));

        txt_nksiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nksiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nksiswa.setEnabled(false);
        panelprofilesiswa.add(txt_nksiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 181, 550, 30));

        txt_namasiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_namasiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_namasiswa.setEnabled(false);
        panelprofilesiswa.add(txt_namasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 222, 550, 30));

        txt_alamatsiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_alamatsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_alamatsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_alamatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 265, 550, 60));

        txt_gendersiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_gendersiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_gendersiswa.setEnabled(false);
        panelprofilesiswa.add(txt_gendersiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 335, 550, 30));

        txt_notelpsiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_notelpsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_notelpsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_notelpsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 376, 550, 30));

        txt_emailsiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_emailsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_emailsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_emailsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 417, 550, 30));

        txt_walassiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_walassiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_walassiswa.setEnabled(false);
        panelprofilesiswa.add(txt_walassiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 457, 550, 30));

        txt_ortusiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_ortusiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_ortusiswa.setEnabled(false);
        panelprofilesiswa.add(txt_ortusiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 497, 550, 30));

        txt_noortusiswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_noortusiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_noortusiswa.setEnabled(false);
        panelprofilesiswa.add(txt_noortusiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 538, 550, 30));

        btn_editprofilesiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_editprofilesiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editprofilesiswa.setText("Edit");
        btn_editprofilesiswa.setIconTextGap(18);
        btn_editprofilesiswa.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_editprofilesiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editprofilesiswaActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_editprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 590, 120, 40));

        btn_simpanprofilesiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanprofilesiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofilesiswa.setText("Simpan");
        btn_simpanprofilesiswa.setIconTextGap(10);
        btn_simpanprofilesiswa.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_simpanprofilesiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofilesiswaActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_simpanprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 590, 130, 40));

        btn_kembaliprofilesiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofilesiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back-button.png"))); // NOI18N
        btn_kembaliprofilesiswa.setText("Kembali");
        btn_kembaliprofilesiswa.setIconTextGap(18);
        btn_kembaliprofilesiswa.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_kembaliprofilesiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofilesiswaActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_kembaliprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 590, 140, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jButton1.setText("Riwayat Absen");
        panelprofilesiswa.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 590, 120, 40));

        bgprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofilesiswa2.png"))); // NOI18N
        panelprofilesiswa.add(bgprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profilesiswa.add(panelprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profilesiswa, "card4");

        dataguru.setOpaque(true);
        dataguru.setPreferredSize(new java.awt.Dimension(1107, 658));
        dataguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelguru.add(txt_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 690, 30));

        btn_searchguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchguru.setText("Search");
        btn_searchguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 100, 30));

        tabel_guru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "NIP", "NK", "Nama", "Jenis Kelamin"
            }
        ));
        tabel_guru.setRowHeight(20);
        tabel_guru.setRowMargin(2);
        tabel_guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_guruMouseClicked(evt);
            }
        });
        tabguru.setViewportView(tabel_guru);

        panelguru.add(tabguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 1020, 370));

        btn_lihatguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatguru.setText("Lihat Data");
        btn_lihatguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_lihatguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 140, 40));

        brn_hapusguru.setBackground(new java.awt.Color(255, 255, 255));
        brn_hapusguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        brn_hapusguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/delete.png"))); // NOI18N
        brn_hapusguru.setText("Hapus");
        brn_hapusguru.setIconTextGap(18);
        brn_hapusguru.setMargin(new java.awt.Insets(1, 1, 1, 10));
        brn_hapusguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_hapusguruActionPerformed(evt);
            }
        });
        panelguru.add(brn_hapusguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 570, 140, 40));

        btn_refreshdataguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshdataguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
        panelguru.add(btn_refreshdataguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 100, 40, 30));

        btn_tambahguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambahguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_tambahguru.setText("Add Guru");
        btn_tambahguru.setIconTextGap(18);
        btn_tambahguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_tambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 150, 30));

        bgguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelguru.png"))); // NOI18N
        panelguru.add(bgguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataguru.add(panelguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataguru, "card3");

        profileguru.setOpaque(true);
        profileguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofileguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_gambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/teacher.png"))); // NOI18N
        lb_gambar.setText("jLabel15");
        panelprofileguru.add(lb_gambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 310, 270));

        lb_nk.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nk.setForeground(new java.awt.Color(0, 51, 204));
        lb_nk.setText("NK");
        panelprofileguru.add(lb_nk, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 50, 30));

        txt_noteleponguru.setEnabled(false);
        panelprofileguru.add(txt_noteleponguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 530, 30));

        txt_nipguru.setEnabled(false);
        panelprofileguru.add(txt_nipguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 530, 30));

        txt_nkguru.setEnabled(false);
        panelprofileguru.add(txt_nkguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 530, 30));

        txt_namaguru.setEnabled(false);
        panelprofileguru.add(txt_namaguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 530, 30));

        lb_nama.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nama.setForeground(new java.awt.Color(0, 51, 204));
        lb_nama.setText("Nama");
        panelprofileguru.add(lb_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 90, 30));

        lb_email.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_email.setForeground(new java.awt.Color(0, 51, 204));
        lb_email.setText("E-mail");
        panelprofileguru.add(lb_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 60, 30));

        lb_titik1.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik1.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik1.setText(":");
        panelprofileguru.add(lb_titik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 20, 30));

        lb_notelepon.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_notelepon.setForeground(new java.awt.Color(0, 51, 204));
        lb_notelepon.setText("No.Telepon");
        panelprofileguru.add(lb_notelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 120, 30));

        txt_emailguru.setEnabled(false);
        panelprofileguru.add(txt_emailguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 530, 30));

        lb_alamat.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_alamat.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamat.setText("Alamat");
        panelprofileguru.add(lb_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, 130, 30));

        lb_jk.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jk.setForeground(new java.awt.Color(0, 51, 204));
        lb_jk.setText("Jenis Kelamin");
        panelprofileguru.add(lb_jk, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 130, 30));

        txt_alamatguru.setEnabled(false);
        panelprofileguru.add(txt_alamatguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 530, 80));

        txt_jkguru.setEnabled(false);
        panelprofileguru.add(txt_jkguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 530, 30));

        lb_nip.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nip.setForeground(new java.awt.Color(0, 51, 204));
        lb_nip.setText("NIP");
        panelprofileguru.add(lb_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 50, 30));

        lb_titik2.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik2.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik2.setText(":");
        panelprofileguru.add(lb_titik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 20, 30));

        lb_titik3.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik3.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik3.setText(":");
        panelprofileguru.add(lb_titik3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 20, 30));

        lb_titik4.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik4.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik4.setText(":");
        panelprofileguru.add(lb_titik4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 20, 30));

        lb_titik5.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik5.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik5.setText(":");
        panelprofileguru.add(lb_titik5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 20, 30));

        lb_titik6.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik6.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik6.setText(":");
        panelprofileguru.add(lb_titik6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 20, 30));

        lb_titik7.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik7.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik7.setText(":");
        panelprofileguru.add(lb_titik7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 20, 30));

        btn_simpanprofileguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanprofileguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofileguru.setText("Simpan");
        btn_simpanprofileguru.setIconTextGap(18);
        btn_simpanprofileguru.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_simpanprofileguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofileguruActionPerformed(evt);
            }
        });
        panelprofileguru.add(btn_simpanprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 560, 140, 40));

        btn_editprofileguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_editprofileguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editprofileguru.setText("Edit");
        btn_editprofileguru.setIconTextGap(18);
        btn_editprofileguru.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_editprofileguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editprofileguruActionPerformed(evt);
            }
        });
        panelprofileguru.add(btn_editprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 560, 140, 40));

        btn_kembaliprofileguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofileguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back-button.png"))); // NOI18N
        btn_kembaliprofileguru.setText("Kembali");
        btn_kembaliprofileguru.setIconTextGap(18);
        btn_kembaliprofileguru.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_kembaliprofileguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofileguruActionPerformed(evt);
            }
        });
        panelprofileguru.add(btn_kembaliprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 560, 150, 40));

        bgprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofileguru.png"))); // NOI18N
        panelprofileguru.add(bgprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profileguru.add(panelprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profileguru, "card4");

        datakelas.setOpaque(true);
        datakelas.setPreferredSize(new java.awt.Dimension(1107, 658));
        datakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelkelas.png"))); // NOI18N
        panelkelas.add(bgkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datakelas.add(panelkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datakelas, "card4");

        dataadmin.setOpaque(true);
        dataadmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paneladmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_searchadmin.setBackground(new java.awt.Color(240, 240, 240));
        txt_searchadmin.setFont(new java.awt.Font("Roboto Slab Thin", 1, 14)); // NOI18N
        txt_searchadmin.setBorder(null);
        paneladmin.add(txt_searchadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 270, 30));

        btn_lihatadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatadmin.setText("Lihat Profile");
        btn_lihatadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatadminActionPerformed(evt);
            }
        });
        paneladmin.add(btn_lihatadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 130, 40));

        btn_editadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_editadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editadmin.setText("Edit");
        btn_editadmin.setIconTextGap(18);
        btn_editadmin.setMargin(new java.awt.Insets(1, 1, 1, 14));
        paneladmin.add(btn_editadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 570, 120, 40));

        btn_registrasi.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrasi.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_registrasi.setText("Tambah Admin");
        btn_registrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrasiActionPerformed(evt);
            }
        });
        paneladmin.add(btn_registrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 140, 30));

        btn_hapusadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapusadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_hapusadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/delete.png"))); // NOI18N
        btn_hapusadmin.setText("Hapus");
        btn_hapusadmin.setIconTextGap(18);
        btn_hapusadmin.setMargin(new java.awt.Insets(1, 1, 1, 14));
        btn_hapusadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusadminMouseClicked(evt);
            }
        });
        paneladmin.add(btn_hapusadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 130, 40));

        tabadmin.setBackground(new java.awt.Color(255, 255, 255));
        tabadmin.setBorder(null);

        tabel_admin.setForeground(new java.awt.Color(255, 255, 255));
        tabel_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID Admin", "NIP", "Nama", "Username", "Password", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_admin.setFocusable(false);
        tabel_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_adminMouseClicked(evt);
            }
        });
        tabadmin.setViewportView(tabel_admin);

        paneladmin.add(tabadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 1010, 390));

        bgadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneladmin.png"))); // NOI18N
        paneladmin.add(bgadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        paneladmin.add(saveadm, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, -1, -1));

        dataadmin.add(paneladmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataadmin, "card4");

        dataabsensi.setOpaque(true);
        dataabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        dataabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelabsensi.png"))); // NOI18N
        panelabsensi.add(bgabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataabsensi.add(panelabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataabsensi, "card4");

        datalapabsensi.setOpaque(true);
        datalapabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        datalapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panellapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bglapabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panellapabsensi.png"))); // NOI18N
        panellapabsensi.add(bglapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datalapabsensi.add(panellapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datalapabsensi, "card4");

        form_siswa.setOpaque(true);
        form_siswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("NIS");
        panelformsiswa.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("NK");
        panelformsiswa.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Nama");
        panelformsiswa.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Alamat");
        panelformsiswa.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Jenis Kelamin");
        panelformsiswa.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jLabel6.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("No.Tlpn");
        panelformsiswa.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        jLabel7.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("E-Mail");
        panelformsiswa.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, 20));

        jLabel8.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Wali kelas");
        panelformsiswa.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 365, -1, -1));

        jLabel9.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("No. Orang tua");
        panelformsiswa.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 526, -1, -1));

        jLabel12.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText(":");
        panelformsiswa.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 135, -1, -1));
        panelformsiswa.add(txt_nis, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 840, 30));

        jLabel13.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setText("RFID");
        panelformsiswa.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel14.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText(":");
        panelformsiswa.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 174, -1, -1));

        jLabel15.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText(":");
        panelformsiswa.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 214, -1, -1));

        jLabel17.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 204));
        jLabel17.setText(":");
        panelformsiswa.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jLabel18.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setText(":");
        panelformsiswa.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, -1, -1));

        jLabel19.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setText(":");
        panelformsiswa.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 363, -1, -1));

        jLabel20.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText(":");
        panelformsiswa.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 406, -1, 20));

        jLabel21.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 204));
        jLabel21.setText(":");
        panelformsiswa.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 445, -1, -1));

        jLabel22.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 204));
        jLabel22.setText(":");
        panelformsiswa.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 525, -1, -1));

        jLabel23.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 204));
        jLabel23.setText(":");
        panelformsiswa.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 95, -1, -1));

        txt_nk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nkActionPerformed(evt);
            }
        });
        panelformsiswa.add(txt_nk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 840, 30));
        panelformsiswa.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 840, 30));
        panelformsiswa.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 840, 70));
        panelformsiswa.add(txt_walikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 840, 30));
        panelformsiswa.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 840, 30));
        panelformsiswa.add(txt_tlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 840, 30));
        panelformsiswa.add(txt_noortu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, 840, 30));
        panelformsiswa.add(txt_RFID, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 840, 30));

        rb1.setBackground(new java.awt.Color(255, 255, 255));
        rb1.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb1.setText("Perempuan");
        panelformsiswa.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        rb2.setBackground(new java.awt.Color(255, 255, 255));
        rb2.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb2.setText("Laki-Laki");
        panelformsiswa.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, -1));
        panelformsiswa.add(txt_namaortu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 840, 30));

        jLabel10.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Nama Orang tua");
        panelformsiswa.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 485, -1, -1));

        jLabel24.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText(":");
        panelformsiswa.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 485, -1, -1));

        btn_simpantambahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpantambahsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpantambahsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpantambahsiswa.setText("Simpan");
        btn_simpantambahsiswa.setIconTextGap(18);
        btn_simpantambahsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpantambahsiswaActionPerformed(evt);
            }
        });
        panelformsiswa.add(btn_simpantambahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 140, 35));

        bgtambahsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahsiswa.png"))); // NOI18N
        panelformsiswa.add(bgtambahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_siswa.add(panelformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_siswa, "card4");

        form_guru.setOpaque(true);
        form_guru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 51, 204));
        jLabel46.setText("NK");
        panelformguru.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        jLabel47.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 51, 204));
        jLabel47.setText("Nama");
        panelformguru.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jLabel48.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 51, 204));
        jLabel48.setText("E-mail");
        panelformguru.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel49.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 51, 204));
        jLabel49.setText("Alamat");
        panelformguru.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        jLabel50.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 51, 204));
        jLabel50.setText("Jenis Kelamin");
        panelformguru.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        jLabel53.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 51, 204));
        jLabel53.setText("No.Telepon");
        panelformguru.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jLabel55.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 51, 204));
        jLabel55.setText(":");
        panelformguru.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));
        panelformguru.add(txt_nktambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 840, 30));

        jLabel56.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 51, 204));
        jLabel56.setText("NIP");
        panelformguru.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel57.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 51, 204));
        jLabel57.setText(":");
        panelformguru.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jLabel58.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 51, 204));
        jLabel58.setText(":");
        panelformguru.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        jLabel59.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 51, 204));
        jLabel59.setText(":");
        panelformguru.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, -1, -1));

        jLabel60.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 51, 204));
        jLabel60.setText(":");
        panelformguru.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        jLabel61.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 51, 204));
        jLabel61.setText(":");
        panelformguru.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        jLabel65.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 51, 204));
        jLabel65.setText(":");
        panelformguru.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        txt_namatambahguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namatambahguruActionPerformed(evt);
            }
        });
        panelformguru.add(txt_namatambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));
        panelformguru.add(txt_emailtambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 840, 30));
        panelformguru.add(txt_alamattambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 840, 70));
        panelformguru.add(txt_tlptambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 840, 30));
        panelformguru.add(txt_niptambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 840, 30));

        rb3.setBackground(new java.awt.Color(255, 255, 255));
        rb3.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb3.setText("Perempuan");
        panelformguru.add(rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        rb4.setBackground(new java.awt.Color(255, 255, 255));
        rb4.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb4.setText("Laki-Laki");
        panelformguru.add(rb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        btn_simpantambahguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpantambahguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpantambahguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpantambahguru.setText("Simpan");
        btn_simpantambahguru.setIconTextGap(18);
        btn_simpantambahguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpantambahguruActionPerformed(evt);
            }
        });
        panelformguru.add(btn_simpantambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 140, 35));

        bgtambahguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahguru.png"))); // NOI18N
        panelformguru.add(bgtambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_guru.add(panelformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_guru, "card4");

        form_registrasi.setOpaque(true);
        form_registrasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformregistrasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 51, 204));
        jLabel51.setText("NIP");
        panelformregistrasi.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        jLabel52.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 51, 204));
        jLabel52.setText("Nama");
        panelformregistrasi.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jLabel54.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 51, 204));
        jLabel54.setText("Username");
        panelformregistrasi.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel62.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 51, 204));
        jLabel62.setText("Status");
        panelformregistrasi.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jLabel64.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 51, 204));
        jLabel64.setText("Password");
        panelformregistrasi.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel66.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 51, 204));
        jLabel66.setText(":");
        panelformregistrasi.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 195, -1, -1));
        panelformregistrasi.add(txt_regisnip, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 840, 30));

        jLabel67.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 204));
        jLabel67.setText("ID");
        panelformregistrasi.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel68.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 204));
        jLabel68.setText(":");
        panelformregistrasi.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 235, -1, -1));

        jLabel69.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 51, 204));
        jLabel69.setText(":");
        panelformregistrasi.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 274, -1, -1));

        jLabel70.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 51, 204));
        jLabel70.setText(":");
        panelformregistrasi.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        jLabel72.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 51, 204));
        jLabel72.setText(":");
        panelformregistrasi.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 315, -1, -1));

        jLabel73.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 51, 204));
        jLabel73.setText(":");
        panelformregistrasi.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 153, -1, -1));

        txt_regisnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_regisnamaActionPerformed(evt);
            }
        });
        panelformregistrasi.add(txt_regisnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));
        panelformregistrasi.add(txt_regisusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 840, 30));
        panelformregistrasi.add(txt_regispassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 840, 30));
        panelformregistrasi.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 840, 30));

        btn_daftar.setBackground(new java.awt.Color(255, 255, 255));
        btn_daftar.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_daftar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_daftar.setText("Daftar");
        btn_daftar.setIconTextGap(18);
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        panelformregistrasi.add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 580, 140, 35));

        txt_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guru BK", "Bagian IT", "Kepala Sekolah" }));
        txt_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_levelActionPerformed(evt);
            }
        });
        panelformregistrasi.add(txt_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 840, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back-button.png"))); // NOI18N
        jButton2.setText("Kembali");
        jButton2.setIconTextGap(18);
        jButton2.setMargin(new java.awt.Insets(1, 1, 1, 14));
        panelformregistrasi.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 140, 35));

        bgtambahguru1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahguru.png"))); // NOI18N
        panelformregistrasi.add(bgtambahguru1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_registrasi.add(panelformregistrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_registrasi, "card4");

        PanelUtama.add(LayerPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 110, 1107, 658));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_datasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMouseClicked
        switchpanel(datasiswa);
        tampilsiswa();
    }//GEN-LAST:event_btn_datasiswaMouseClicked

    private void btn_dataguruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseClicked
         switchpanel(dataguru);
         tampilguru();
    }//GEN-LAST:event_btn_dataguruMouseClicked

    private void btn_datakelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseClicked
         switchpanel(datakelas);
    }//GEN-LAST:event_btn_datakelasMouseClicked

    private void btn_dataabsensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseClicked
         switchpanel(dataabsensi);
    }//GEN-LAST:event_btn_dataabsensiMouseClicked

    private void btn_lapabsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapabsenMouseClicked
         switchpanel(datalapabsensi);
    }//GEN-LAST:event_btn_lapabsenMouseClicked

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        btn_dashboard.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dashboardMouseEntered

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
        btn_dashboard.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMousePressed
        btn_dashboard.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dashboardMousePressed

    private void btn_datasiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMouseEntered
        btn_datasiswa.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_datasiswaMouseEntered

    private void btn_datasiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMouseExited
        btn_datasiswa.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_datasiswaMouseExited

    private void btn_datasiswaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMousePressed
        btn_datasiswa.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_datasiswaMousePressed

    private void btn_dataguruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseEntered
        btn_dataguru.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataguruMouseEntered

    private void btn_dataguruMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseExited
        btn_dataguru.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataguruMouseExited

    private void btn_dataguruMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMousePressed
        btn_dataguru.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataguruMousePressed

    private void btn_datakelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseEntered
        btn_datakelas.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_datakelasMouseEntered

    private void btn_datakelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseExited
        btn_datakelas.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_datakelasMouseExited

    private void btn_datakelasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMousePressed
        btn_datakelas.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_datakelasMousePressed

    private void btn_dataabsensiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseEntered
        btn_dataabsensi.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataabsensiMouseEntered

    private void btn_dataabsensiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseExited
        btn_dataabsensi.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataabsensiMouseExited

    private void btn_dataabsensiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMousePressed
        btn_dataabsensi.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataabsensiMousePressed

    private void btn_lapabsenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapabsenMouseEntered
        btn_lapabsen.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_lapabsenMouseEntered

    private void btn_lapabsenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapabsenMouseExited
        btn_lapabsen.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_lapabsenMouseExited

    private void btn_lapabsenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapabsenMousePressed
        btn_lapabsen.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_lapabsenMousePressed

    private void btn_searchsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchsiswaActionPerformed
         this.querysearchsiswa();
    }//GEN-LAST:event_btn_searchsiswaActionPerformed

    private void btn_lihatsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatsiswaActionPerformed
        if (txt_searchsiswa.getText().equals(Session.getnissiswa())){
            Session.setnissiswa(txt_searchsiswa.getText());
            switchpanel(profilesiswa);
            this.tampilprofilesiswa();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Siswa", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatsiswaActionPerformed

    private void btn_hapussiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapussiswaActionPerformed
        this.deletedatasiswa();
    }//GEN-LAST:event_btn_hapussiswaActionPerformed

    private void brn_hapusguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_hapusguruActionPerformed
        this.deletedataguru();
    }//GEN-LAST:event_brn_hapusguruActionPerformed

    private void btn_searchguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchguruActionPerformed
        this.querysearchguru();
    }//GEN-LAST:event_btn_searchguruActionPerformed

    private void btn_dataadminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseEntered
        btn_dataadmin.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataadminMouseEntered

    private void btn_dataadminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseExited
        btn_dataadmin.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataadminMouseExited

    private void btn_dataadminMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMousePressed
        btn_dataadmin.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataadminMousePressed

    private void btn_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMousePressed
        btn_logout.setBackground(new Color(88,163,234));
        this.setVisible(false);
        Session.setusername(null);
        Session.setnipguru(null);
        Session.setnipadmin(null);
        Session.setnissiswa(null);
        login lgn=new login();
        lgn.setVisible(true);
    }//GEN-LAST:event_btn_logoutMousePressed

    private void btn_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseExited
        btn_logout.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_logoutMouseExited

    private void btn_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseEntered
        // TODO add your handling code here:
        btn_logout.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_logoutMouseEntered

    private void tabel_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_siswaMouseClicked
        this.querysearchkliksiswa();
    }//GEN-LAST:event_tabel_siswaMouseClicked

    private void tabel_guruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_guruMouseClicked
        this.querysearchklikguru();
    }//GEN-LAST:event_tabel_guruMouseClicked

    private void tabel_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_adminMouseClicked
        this.querysearchklikadmin();
    }//GEN-LAST:event_tabel_adminMouseClicked

    private void btn_lihatadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatadminActionPerformed
        if (txt_searchadmin.getText().equals(Session.getnipadmin())){
            Session.setnipadmin(txt_searchadmin.getText());
            switchpanel(profileguru);
            this.tampilprofileadmin();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Admin", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatadminActionPerformed

    private void btn_registrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrasiActionPerformed
        switchpanel(form_registrasi);
    }//GEN-LAST:event_btn_registrasiActionPerformed

    private void btn_hapusadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusadminMouseClicked
        this.deletedataadmin();
    }//GEN-LAST:event_btn_hapusadminMouseClicked

    private void btn_dataadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseClicked
        switchpanel(dataadmin);
        tampiladmin();
    }//GEN-LAST:event_btn_dataadminMouseClicked

    private void btn_editprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofilesiswaActionPerformed
        this.editprofilesiswa(true);
    }//GEN-LAST:event_btn_editprofilesiswaActionPerformed

    private void btn_kembaliprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofilesiswaActionPerformed
        switchpanel(datasiswa);
        this.editprofilesiswa(false);
        this.tampilsiswa();
    }//GEN-LAST:event_btn_kembaliprofilesiswaActionPerformed

    private void btn_simpanprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofilesiswaActionPerformed
        this.simpanprofilesiswa();
        this.editprofilesiswa(false);
    }//GEN-LAST:event_btn_simpanprofilesiswaActionPerformed

    private void btn_lihatguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatguruActionPerformed
        if (txt_searchguru.getText().equals(Session.getnipguru())){ 
            Session.setnipguru(txt_searchguru.getText());
            switchpanel(profileguru);
            this.tampilprofileguru();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Guru", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatguruActionPerformed

    private void btn_editprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofileguruActionPerformed
        // TODO add your handling code here:
        this.editprofileguru(true);
    }//GEN-LAST:event_btn_editprofileguruActionPerformed

    private void btn_kembaliprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofileguruActionPerformed
        // TODO add your handling code here:
        if(Session.getnipguru()==null){
            Session.setnipadmin(null);
            switchpanel(dataadmin);
            this.tampiladmin();
        }
        else{
            Session.setnipguru(null);
            switchpanel(dataguru);
            this.editprofileguru(false);
            this.tampilguru();
        }
    }//GEN-LAST:event_btn_kembaliprofileguruActionPerformed

    private void btn_simpanprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofileguruActionPerformed
        // TODO add your handling code here:
        this.simpanprofileguru();
        this.editprofileguru(false);
    }//GEN-LAST:event_btn_simpanprofileguruActionPerformed

    private void btn_refreshdatasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshdatasiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_refreshdatasiswaActionPerformed

    private void txt_nkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nkActionPerformed

    private void btn_simpantambahsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpantambahsiswaActionPerformed
        // TODO add your handling code here:
            String jeniskelamin = null;
        if(rb1.isSelected()){
            jeniskelamin = "perempuan";
        } else if (rb2.isSelected()){
            jeniskelamin = "Laki-Laki";
        }
        
        try {
            if (txt_nis.getText().equals("") || txt_nk.getText().equals("") 
                    || txt_nama.getText().equals("") || txt_alamat.getText().equals("") || txt_email.getText().equals(" ") 
                    || txt_tlp.getText().equals(" ")|| txt_walikelas.getText().equals(" ")|| txt_noortu.getText().equals(" ")
                    || txt_namaortu.getText().equals(" ")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                //Digunakan untuk memanggil JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/smkcendekia", "root", "");
                stat = con.createStatement();
                String simpan = "insert into siswa values ('"+txt_nis.getText()
                        +"','"+txt_nk.getText()
                        +"','"+txt_nama.getText()
                        +"','"+txt_alamat.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_tlp.getText()
                        +"','"+txt_email.getText()
                        +"','"+txt_walikelas.getText()
                        +"','"+txt_namaortu.getText()
                        +"','"+txt_noortu.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Berhasil disimpan!");
                hapuslayar();
                switchpanel(datasiswa);
                tampilsiswa();
                
//                Login login = new Login();
//                login.setVisible(true);
            }
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(this, "Data Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
//           hapuslayar();
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_simpantambahsiswaActionPerformed

    private void btn_tambahsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahsiswaActionPerformed
        // TODO add your handling code here:
        switchpanel(form_siswa);
    }//GEN-LAST:event_btn_tambahsiswaActionPerformed

    private void txt_namatambahguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namatambahguruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namatambahguruActionPerformed

    private void btn_simpantambahguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpantambahguruActionPerformed
        // TODO add your handling code here:
        String jeniskelamin = null;
        if(rb3.isSelected()){
            jeniskelamin = "perempuan";
        } else if (rb4.isSelected()){
            jeniskelamin = "Laki-Laki";
        }
        
        try {
            if (txt_niptambahguru.getText().equals("") || txt_nktambahguru.getText().equals("") 
                    || txt_namatambahguru.getText().equals("") || txt_emailtambahguru.getText().equals(" ") 
                    || txt_tlptambahguru.getText().equals(" ")|| txt_alamattambahguru.getText().equals(" ")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                //Digunakan untuk memanggil JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/smkcendekia", "root", "");
                stat = con.createStatement();
                String simpan = "insert into guru values ('"+txt_niptambahguru.getText()
                        +"','"+txt_nktambahguru.getText()
                        +"','"+txt_namatambahguru.getText()
                        +"','"+txt_emailtambahguru.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_tlptambahguru.getText()
                        +"','"+txt_alamattambahguru.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Guru Berhasil disimpan!");
                switchpanel(dataguru);
                tampilguru();
//                Login login = new Login();
//                login.setVisible(true);
            }
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(this, "Data Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
//           hapuslayar();
            System.out.println(e);
        }                
    }//GEN-LAST:event_btn_simpantambahguruActionPerformed

    private void btn_tambahguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahguruActionPerformed
        // TODO add your handling code here:
        switchpanel(form_guru);
    }//GEN-LAST:event_btn_tambahguruActionPerformed

    private void txt_regisnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_regisnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_regisnamaActionPerformed

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        // TODO add your handling code here:
        this.addregistrasi();
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void txt_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_levelActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(keloladata_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(keloladata_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(keloladata_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(keloladata_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new keloladata_bk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    public javax.swing.JLayeredPane LayerPane;
    private javax.swing.JPanel PanelUtama;
    private javax.swing.JLabel bgabsensi;
    private javax.swing.JLabel bgadmin;
    private javax.swing.JLabel bgguru;
    private javax.swing.JLabel bgkelas;
    private javax.swing.JLabel bglapabsensi;
    private javax.swing.JLabel bgprofileguru;
    private javax.swing.JLabel bgprofilesiswa;
    private javax.swing.JLabel bgsiswa;
    private javax.swing.JLabel bgtambahguru;
    private javax.swing.JLabel bgtambahguru1;
    private javax.swing.JLabel bgtambahsiswa;
    private javax.swing.JButton brn_hapusguru;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JPanel btn_dataabsensi;
    private javax.swing.JPanel btn_dataadmin;
    private javax.swing.JPanel btn_dataguru;
    private javax.swing.JPanel btn_datakelas;
    private javax.swing.JPanel btn_datasiswa;
    private javax.swing.JButton btn_editadmin;
    private javax.swing.JButton btn_editprofileguru;
    private javax.swing.JButton btn_editprofilesiswa;
    private javax.swing.JButton btn_hapusadmin;
    private javax.swing.JButton btn_hapussiswa;
    private javax.swing.JButton btn_kembaliprofileguru;
    private javax.swing.JButton btn_kembaliprofilesiswa;
    private javax.swing.JPanel btn_lapabsen;
    private javax.swing.JButton btn_lihatadmin;
    private javax.swing.JButton btn_lihatguru;
    private javax.swing.JButton btn_lihatsiswa;
    private javax.swing.JPanel btn_logout;
    private javax.swing.JButton btn_refreshdataguru;
    private javax.swing.JButton btn_refreshdatasiswa;
    private javax.swing.JButton btn_registrasi;
    private javax.swing.JButton btn_searchguru;
    private javax.swing.JButton btn_searchsiswa;
    private javax.swing.JButton btn_simpanprofileguru;
    private javax.swing.JButton btn_simpanprofilesiswa;
    private javax.swing.JButton btn_simpantambahguru;
    private javax.swing.JButton btn_simpantambahsiswa;
    private javax.swing.JButton btn_tambahguru;
    private javax.swing.JButton btn_tambahsiswa;
    private javax.swing.JLayeredPane dataabsensi;
    private javax.swing.JLayeredPane dataadmin;
    private javax.swing.JLayeredPane dataguru;
    private javax.swing.JLayeredPane datakelas;
    private javax.swing.JLayeredPane datalapabsensi;
    private javax.swing.JLayeredPane datasiswa;
    private javax.swing.JLayeredPane form_guru;
    private javax.swing.JLayeredPane form_registrasi;
    private javax.swing.JLayeredPane form_siswa;
    private javax.swing.JLabel iconabsen;
    private javax.swing.JLabel iconadmin;
    private javax.swing.JLabel icondashboard;
    private javax.swing.JLabel iconguru;
    private javax.swing.JLabel iconkelas;
    private javax.swing.JLabel iconlapabsen;
    private javax.swing.JLabel iconlogout;
    private javax.swing.JLabel iconsiswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lb_alamat;
    private javax.swing.JLabel lb_email;
    private javax.swing.JLabel lb_gambar;
    private javax.swing.JLabel lb_jk;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_nip;
    private javax.swing.JLabel lb_nk;
    private javax.swing.JLabel lb_notelepon;
    private javax.swing.JLabel lb_titik1;
    private javax.swing.JLabel lb_titik2;
    private javax.swing.JLabel lb_titik3;
    private javax.swing.JLabel lb_titik4;
    private javax.swing.JLabel lb_titik5;
    private javax.swing.JLabel lb_titik6;
    private javax.swing.JLabel lb_titik7;
    private javax.swing.JLabel lbabsen;
    private javax.swing.JLabel lbdashboard;
    private javax.swing.JLabel lbguru;
    private javax.swing.JLabel lbkelas;
    private javax.swing.JLabel lbkelas1;
    private javax.swing.JLabel lblapabsen;
    private javax.swing.JLabel lblogout;
    private javax.swing.JLabel lbsiswa;
    private javax.swing.JPanel panelabsensi;
    private javax.swing.JPanel paneladmin;
    private javax.swing.JPanel panelbutton;
    private javax.swing.JPanel panelformguru;
    private javax.swing.JPanel panelformregistrasi;
    private javax.swing.JPanel panelformsiswa;
    private javax.swing.JPanel panelguru;
    private javax.swing.JPanel panelkelas;
    private javax.swing.JPanel panellapabsensi;
    private javax.swing.JPanel panelprofileguru;
    private javax.swing.JPanel panelprofilesiswa;
    private javax.swing.JPanel panelsiswa;
    private javax.swing.JLayeredPane profileguru;
    private javax.swing.JLayeredPane profilesiswa;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JLabel saveadm;
    private javax.swing.JScrollPane tabadmin;
    private javax.swing.JTable tabel_admin;
    private javax.swing.JTable tabel_guru;
    private javax.swing.JTable tabel_siswa;
    private javax.swing.JScrollPane tabguru;
    private javax.swing.JScrollPane tabsiswa;
    private javax.swing.JTextField txt_RFID;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_alamatguru;
    private javax.swing.JTextField txt_alamatsiswa;
    private javax.swing.JTextField txt_alamattambahguru;
    private javax.swing.ButtonGroup txt_buttonJK;
    private javax.swing.ButtonGroup txt_buttonjktambahguru;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_emailguru;
    private javax.swing.JTextField txt_emailsiswa;
    private javax.swing.JTextField txt_emailtambahguru;
    private javax.swing.JTextField txt_gendersiswa;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_jkguru;
    private javax.swing.JComboBox<String> txt_level;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_namaguru;
    private javax.swing.JTextField txt_namaortu;
    private javax.swing.JTextField txt_namasiswa;
    private javax.swing.JTextField txt_namatambahguru;
    private javax.swing.JTextField txt_nipguru;
    private javax.swing.JTextField txt_niptambahguru;
    private javax.swing.JTextField txt_nis;
    private javax.swing.JTextField txt_nissiswa;
    private javax.swing.JTextField txt_nk;
    private javax.swing.JTextField txt_nkguru;
    private javax.swing.JTextField txt_nksiswa;
    private javax.swing.JTextField txt_nktambahguru;
    private javax.swing.JTextField txt_noortu;
    private javax.swing.JTextField txt_noortusiswa;
    private javax.swing.JTextField txt_noteleponguru;
    private javax.swing.JTextField txt_notelpsiswa;
    private javax.swing.JTextField txt_ortusiswa;
    private javax.swing.JTextField txt_regisnama;
    private javax.swing.JTextField txt_regisnip;
    private javax.swing.JTextField txt_regispassword;
    private javax.swing.JTextField txt_regisusername;
    private javax.swing.JTextField txt_rfidsiswa;
    private javax.swing.JTextField txt_searchadmin;
    private javax.swing.JTextField txt_searchguru;
    private javax.swing.JTextField txt_searchsiswa;
    private javax.swing.JTextField txt_tlp;
    private javax.swing.JTextField txt_tlptambahguru;
    private javax.swing.JTextField txt_walassiswa;
    private javax.swing.JTextField txt_walikelas;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
