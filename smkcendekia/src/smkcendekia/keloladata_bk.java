/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author PC
 */
public class keloladata_bk extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs,rs1,rs2;
    String sql, leveluser,leveluser2,bfrfid,wlskelas,wlsprofkelas,usernameadm,idabsen;
    String gendersiswa,wlssiswa;
    String lapabsen;
    int status=0;
    int leveluser3;
    koneksi k;
    DefaultTableModel model;
    
    //KONSTRUKTOR
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
    
    //==================================SISWA=================================//
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
           sql  = "Select siswa.* from siswa";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("NK");
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
            this.deletedatarfid();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Siswa Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void deletedatarfid(){
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM rfid WHERE nis='"+txt_searchsiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Siswa Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //PROSEDUR VALIDASI HAPUS DATA SISWA PADA TABEL
    public void validasideletedatasiswa(){
        int jwbn=JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Hapus Data Siswa",JOptionPane.YES_NO_OPTION);
        if (jwbn==JOptionPane.YES_OPTION){
            this.deletedatasiswa();
            this.tampilsiswa();
            txt_searchsiswa.setText("");
        }
        else if(jwbn==JOptionPane.NO_OPTION){
            
        }
    }
    
    //PROSEDUR MENAMBAH DATA SISWA PADA TABEL
    public void tambahdatasiswa(){
        String jeniskelamin = null;
        if(rb1.isSelected()){
            jeniskelamin = "Perempuan";
        } else if (rb2.isSelected()){
            jeniskelamin = "Laki-Laki";
        }
        this.Autonumberlapabsen();
        try {
            if (txt_rfidformsiswa.getText().equals("") || txt_nkformsiswa.getText().equals("")  || txt_nisformsiswa.getText().equals("") 
                    || txt_namaformsiswa.getText().equals("") || txt_alamatformsiswa.getText().equals("") || txt_emailformsiswa.getText().equals("") 
                    || txt_idwalasformsiswa.getText().equals("")  || txt_telpformsiswa.getText().equals("")||txt_noortuformsiswa.getText().equals("")
                    || txt_nortuformsiswa.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            } else {
                stat = con.createStatement( );
                
                String simpan = "insert into siswa values ('"+txt_nisformsiswa.getText()
                        +"','"+txt_rfidformsiswa.getText()
                        +"','"+txt_nkformsiswa.getText()
                        +"','"+wlssiswa
                        +"','"+txt_namaformsiswa.getText()
                        +"','"+txt_alamatformsiswa.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_emailformsiswa.getText()
                        +"','"+txt_telpformsiswa.getText()
                        +"','"+txt_nortuformsiswa.getText()
                        +"','"+txt_noortuformsiswa.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                this.tambahlapabsensiswa();
                JOptionPane.showMessageDialog(this,"Data Berhasil disimpan!");
                hapuslayar();
                wlssiswa=null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ("Data Siswa Gagal ditambahkan"), 
            "Tambah Data Siswa", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e);
        }
    }
    
    public void tambahlapabsensiswa() throws SQLException{
       stat = con.createStatement( );                
       String simpan = "insert into lapabsen values ('"+lapabsen
                        +"','"+wlssiswa
                        +"','"+txt_nisformsiswa.getText()
                        +"','"+txt_nkformsiswa.getText()
                        +"','"+txt_namaformsiswa.getText()
                        +"','"+status
                        +"','"+status
                        +"','"+status
                        +"','"+status
                        +"','"+status
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
    }
    
    public void walastambahsiswa(){
        try{
           Object[ ] obj = new Object[1];
           stat = con.createStatement( );
           sql  = "Select idwalikelas from kelas where nk='"+txt_nkformsiswa.getText()+"'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){ 
                obj[0] = rs.getString("idwalikelas");
                wlssiswa=(String) obj[0];
            }
           if (obj[0]==null){
               JOptionPane.showMessageDialog(this, "Data Kelas Tidak Ditemukan","Pesan",JOptionPane.ERROR_MESSAGE);
           }
        } catch (SQLException e) {
      }
    }
    
    //ALL ABOUT RFID
    public void rfidlogging(){
        
        
        try{
               stat = con.createStatement( );
               sql  = "select * from rfidlog order by no asc ";
               rs   = stat.executeQuery(sql);
               while(rs.next ()){
                    Object[ ] obj = new Object[1];
                    obj[0] =rs.getString("idrfid");
                    txt_rfidformsiswa.setText((String) obj[0]);
                    txt_rfidsiswa.setText((String) obj[0]);
                }
            } catch (SQLException e) {
                System.out.println(e);
        }
    }
    
    public void rfidloggingformsiswa(){
        Object[ ] obj1 = new Object[1];
        try{
               stat = con.createStatement( );
               sql  = "select idrfid from rfid";
               rs   = stat.executeQuery(sql);
               while(rs.next ()){
                    obj1[0] =rs.getString("idrfid");
                    System.out.println(obj1[0]);
                    if (txt_rfidformsiswa.getText().equals(obj1[0])){
                        JOptionPane.showMessageDialog(this, "RFID Sudah digunakan","Pesan",JOptionPane.ERROR_MESSAGE);
                        txt_rfidformsiswa.setText("");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
        }
    }
    
    public void addrfid(String rfidid,String nis,String nama){
        if (txt_namaformsiswa.getText().equals("")||txt_nisformsiswa.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Isi Nama dan NIS untuk registrasi RFID","Pesan",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                stat = con.createStatement( );
                String simpan = "insert into rfid values ('"+rfidid+"','"+nis+"','"+nama+"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
            } catch (Exception e) {
            }
        }
    }
    
    public void updaterfid(){
        this.rfidlogging();
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE rfid set   idrfid='"+txt_rfidsiswa.getText()+"', "
                                                                        + "nis='"+txt_nissiswa.getText()+"', "
                                                                        + "nama='"+txt_namasiswa.getText()+"' "
                                                                        + "WHERE nis='"+txt_searchsiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[11];
                obj[0] =rs.getString("idrfid");
                bfrfid=(String) obj[0];
             }
            
            JOptionPane.showMessageDialog(null, ("Data RFID berhasil di update"), 
            "Data RFID Siswa", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data RFID sudah dimiliki oleh orang lain","Pesan",JOptionPane.ERROR_MESSAGE);
            this.tampilprofilesiswa();
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE SISWA
    public void tampilprofilesiswa(){
        try {
            stat = con.createStatement( );
            sql  = "SELECT s.*,w.nama from siswa as s inner join walikelas as w ON s.idwalikelas=w.idwalikelas where s.nis='"+Session.getnissiswa()+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[11];
                obj[0] =rs.getString("idrfid");
                txt_rfidsiswa.setText((String) obj[0]);
                obj[1] =rs.getString("nis");
                txt_nissiswa.setText((String) obj[1]);
                obj[2] =rs.getString("nk");
                txt_nksiswa.setText((String) obj[2]);
                obj[3] =rs.getString("nama");
                txt_namasiswa.setText((String) obj[3]);
                obj[4] =rs.getString("alamat");
                txt_alamatsiswa.setText((String) obj[4]);
                obj[5] =rs.getString("jeniskelamin");
                txt_gendersiswa.setText((String) obj[5]);
                obj[6] =rs.getString("notlp");
                txt_notelpsiswa.setText((String) obj[6]);
                obj[7] =rs.getString("email");
                txt_emailsiswa.setText((String) obj[7]);
                obj[8] =rs.getString("w.nama");
                txt_walassiswa.setText((String) obj[8]);
                obj[9] =rs.getString("namaortu");
                txt_ortusiswa.setText((String) obj[9]);
                obj[10] =rs.getString("noortu");
                txt_noortusiswa.setText((String) obj[10]); 
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Siswa Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void tampilriwayatabsensiswa(){
        model = new DefaultTableModel ( );
        tabel_riwayatsiswa.setModel(model);
        lb_nisriwayatsiswa.setText(Session.getnissiswa());
        lb_namariwayatsiswa.setText(txt_namasiswa.getText());
        lb_nkriwayatsiswa.setText(txt_nksiswa.getText());
        model.addColumn("No");
        model.addColumn("Tanggal");
        model.addColumn("Jam");
        model.addColumn("Status");  

         try{
           stat = con.createStatement( );
           sql  = "Select * from absen where nis='"+Session.getnissiswa()+"'";
           rs   = stat.executeQuery(sql);
           int no = 1;
           while(rs.next ()){
                Object[ ] obj = new Object[4];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("tanggal");
                obj[2] = rs.getString("jam");
                obj[3] = rs.getString("status");
                model.addRow(obj);
                no++;
            }
            this.chartriwayatsiswa();
            tabel_absen.setModel(model);
                    
        } catch (SQLException e) {
            System.out.println(e);
      } 
    }
    
    //CHART RIWAYAT SISWA
    public void chartriwayatsiswa(){
        int hadir = 0,sakit = 0,izin = 0,alpha = 0,terlambat = 0;
        try{
           stat = con.createStatement( );
           sql  = "Select * from lapabsen where nis='"+lb_nisriwayatsiswa.getText()+"' and nk='"+lb_nkriwayatsiswa.getText()+"'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                hadir = rs.getInt("hadir");
                sakit = rs.getInt("sakit");
                izin = rs.getInt("izin");
                alpha = rs.getInt("alpha");
                terlambat =rs.getInt("terlambat");
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
        DefaultPieDataset piedata=new DefaultPieDataset();
        piedata.setValue("Hadir", hadir);
        piedata.setValue("Sakit", sakit);
        piedata.setValue("Izin", izin);
        piedata.setValue("Alpha",alpha);
        piedata.setValue("Terlambat", terlambat);
        JFreeChart chart=ChartFactory.createPieChart(" ", piedata,true,false,false);
        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        final PiePlot plot1 = (PiePlot) chart.getPlot();
        plot1.setLabelGenerator(labelGenerator);
        PiePlot piePlot=(PiePlot) chart.getPlot();
        chart.getPlot().setBackgroundPaint( Color.white);
        chart.getPlot().setOutlinePaint(null);
        piePlot.setSectionPaint("Hadir",Color.green);
        piePlot.setSectionPaint("Sakit",Color.yellow);
        piePlot.setSectionPaint("Izin",Color.blue);
        piePlot.setSectionPaint("Alpha",Color.red);
        piePlot.setSectionPaint("Terlambat",Color.orange);
        ChartPanel barChartPanel=new ChartPanel(chart);
        panel_chartrs.removeAll();
        panel_chartrs.add(barChartPanel);
        panel_chartrs.validate();
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE SISWA
    public void editprofilesiswa(boolean check){
        if (check==true){
            btn_scanprofilesiswa.setEnabled(true);
            txt_nissiswa.setEnabled(true);
            txt_namasiswa.setEnabled(true);
            txt_alamatsiswa.setEnabled(true);
            txt_notelpsiswa.setEnabled(true);
            txt_emailsiswa.setEnabled(true);
            txt_ortusiswa.setEnabled(true);
            txt_noortusiswa.setEnabled(true);
            txt_gendersiswa.setVisible(false);
            txt_gendersiswa.setEnabled(false);
            cb_gendersiswa.setVisible(true);
            cb_gendersiswa.setEnabled(true);
        }
        else{
            btn_scanprofilesiswa.setEnabled(false);
            txt_rfidsiswa.setEnabled(false);
            txt_nissiswa.setEnabled(false);
            txt_nksiswa.setEnabled(false);
            txt_namasiswa.setEnabled(false);
            txt_alamatsiswa.setEnabled(false);
            txt_notelpsiswa.setEnabled(false);
            txt_emailsiswa.setEnabled(false);
            txt_walassiswa.setEnabled(false);
            txt_ortusiswa.setEnabled(false);
            txt_noortusiswa.setEnabled(false);
            txt_gendersiswa.setVisible(true);
            txt_gendersiswa.setEnabled(false);
            cb_gendersiswa.setVisible(false);
            cb_gendersiswa.setEnabled(false);
        }
    }
    
    public void simpanprofilesiswa(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE rfid set   idrfid='"+txt_rfidsiswa.getText()+"', "
                                                                        + "nis='"+txt_nissiswa.getText()+"', "
                                                                        + "nama='"+txt_namasiswa.getText()+"' "
                                                                        + "WHERE nis='"+txt_searchsiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
        }
        catch (SQLException ex) {            
        }
        this.updatejkprofilesiswa();
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE siswa set   nis='"+txt_nissiswa.getText()+"', "                                                                       
                                                                        + "nama='"+txt_namasiswa.getText()+"', "
                                                                        + "alamat='"+txt_alamatsiswa.getText()+"', "
                                                                        + "jeniskelamin='"+gendersiswa+"', "
                                                                        + "email='"+txt_emailsiswa.getText()+"', "
                                                                        + "notlp='"+txt_notelpsiswa.getText()+"', "
                                                                        + "namaortu='"+txt_ortusiswa.getText()+"', "        
                                                                        + "noortu='"+txt_noortusiswa.getText()+"'"
                                                                        + "WHERE nis='"+txt_searchsiswa.getText()+"'");
            rs   = stat.executeQuery(sql);
            JOptionPane.showMessageDialog(null, ("Data Siswa Berhasil di Update"), 
            "Data Profile Siswa", JOptionPane.INFORMATION_MESSAGE);
            this.tampilprofilesiswa();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Siswa Gagal di Update"), 
            "Data Profile Siswa Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void updatejkprofilesiswa(){
        String value = cb_gendersiswa.getSelectedItem().toString();
        gendersiswa=value;
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
    
    public void querynamawalassiswa(){
        try {
            stat = con.createStatement( );
            sql  = "Select nama from walikelas where idwalikelas='"+wlssiswa+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){ 
                txt_idwalasformsiswa.setText(rs.getString("nama"));
            }            
        } catch (SQLException ex) {  
            System.out.println(ex);
        }
    }
    
    public void queryidwalassiswa(){
        try {
            stat = con.createStatement( );
            sql  = "Select idwalikelas from walikelas where nama='"+txt_idwalasformsiswa.getText()+"'";
            rs   = stat.executeQuery(sql);
            wlssiswa = rs.getString("idwalikelas");
            
        } catch (SQLException ex) {   
        }
    }
    
    public void searchriwayatsiswa(){
        int row = tabel_riwayatsiswa.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "SELECT * FROM absen WHERE (tanggal BETWEEN '"+txt_tanggalriwayatsiswa.getText()+"' AND '"+txt_tanggal2riwayatsiswa.getText()+"') AND nis='"+Session.getnissiswa()+"'";
                   
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[4];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("tanggal");
                obj[2] = rs.getString("jam");
                obj[3] = rs.getString("status");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Autonumberlapabsen(){
       try {
            sql="select * from lapabsen order by idlapabsen desc";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            if (rs.next()) {
                String noid = rs.getString("idlapabsen").substring(7);
                String AN = "" + (Integer.parseInt(noid) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000000";}
                else if(AN.length()==2)
                {Nol = "00000";}
                else if(AN.length()==3)
                {Nol = "0000";}
                else if(AN.length()==4)
                {Nol = "0000";}
                else if(AN.length()==5)
                {Nol = "000";}
                else if(AN.length()==6)
                {Nol = "00";}
                else if(AN.length()==7)
                {Nol = "0";}
                else if(AN.length()==8)
                {Nol = "";}
               lapabsen="LAP" + Nol + AN;
            } else {
               lapabsen="LAP0000001";
            }

           }catch(NumberFormatException | SQLException e){
           JOptionPane.showMessageDialog(null, e);
           }
    }

    //================================GURU====================================//
    //VOID MENAMPILKAN DATA WALAS PADA TABEL
    public void tampilguru(){        
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Jenis Kelamin");

         try{
           stat = con.createStatement( );
           sql  = "Select * from guru";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nip");
                obj[2] = rs.getString("nama");
                obj[3] = rs.getString("jabatan");
                obj[4] = rs.getString("jeniskelamin");
                model.addRow(obj);
                no++;
            }
            
             tabel_guru.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //VOID MENAMPILKAN DATA GURU PADA TABEL
    public void deletedataguru(){
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Jenis Kelamin");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM guru WHERE nip='"+txt_searchguru.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
            while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nip");
                obj[2] = rs.getString("nama");
                obj[3] = rs.getString("jabatan");
                obj[4] = rs.getString("jeniskelamin");
                model.addRow(obj);
                no++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Guru Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //VOID MENAMBAHKAN DATA GURU PADA TABEL
    public void tambahdataguru(){
        String jeniskelamin = null;
        if(rb5.isSelected()){
            jeniskelamin = "perempuan";
            rb6.setEnabled(false);
        } else if (rb6.isSelected()){
            jeniskelamin = "Laki-Laki";
            rb5.setEnabled(false);
        }
        
        try {
            if (txt_nipformguru.getText().equals("") || txt_jabatanformguru.getText().equals("") 
                    || txt_namaformguru.getText().equals("") || txt_emailformguru.getText().equals(" ") 
                    || txt_tlpformguru.getText().equals(" ")|| txt_alamatformguru.getText().equals(" ")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            } else {
                //Digunakan untuk memanggil JDBC driver
                stat = con.createStatement();
                String simpan = "insert into guru values ('"+txt_nipformguru.getText()
                        +"','"+txt_jabatanformguru.getText()
                        +"','"+txt_namaformguru.getText()
                        +"','"+txt_emailformguru.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_tlpformguru.getText()
                        +"','"+txt_alamatformguru.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Guru Berhasil disimpan!");
                this.hapuslayar();
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, ("Data Guru Gagal ditambahkan"), 
            "Tambah Data Guru", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e);
        }
    }
    
    public void tampilprofileguru(){
        try {
            System.out.println(Session.getnipguru());
            stat = con.createStatement( );
            sql  = "SELECT * FROM guru WHERE nip='"+Session.getnipguru()+"'";
            
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] =rs.getString("nip");
                txt_nipprofileguru.setText((String) obj[0]);
                obj[1] =rs.getString("jabatan");
                txt_jabatanprofileguru.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namaprofileguru.setText((String) obj[2]);
                obj[3] =rs.getString("email");
                txt_emailprofileguru.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_jkprofileguru.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpprofileguru.setText((String) obj[5]);
                obj[6] =rs.getString("alamat");
                txt_alamatprofileguru.setText((String) obj[6]);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Guru Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        } 
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE GURU
    public void editprofileguru(boolean check){
        if (check==true){
            txt_nipprofileguru.setEnabled(true);
            txt_jabatanprofileguru.setEnabled(true);
            txt_namaprofileguru.setEnabled(true);
            txt_emailprofileguru.setEnabled(true);
            txt_jkprofileguru.setEnabled(true);
            txt_notelpprofileguru.setEnabled(true);
            txt_alamatprofileguru.setEnabled(true);
        }
        else{
            txt_nipprofileguru.setEnabled(false);
            txt_jabatanprofileguru.setEnabled(false);
            txt_namaprofileguru.setEnabled(false);
            txt_emailprofileguru.setEnabled(false);
            txt_jkprofileguru.setEnabled(false);
            txt_notelpprofileguru.setEnabled(false);
            txt_alamatprofileguru.setEnabled(false);
        }
    }
    
    public void simpanprofileguru(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE guru set   nip='"+txt_nipprofileguru.getText()+"', "
                                                                        + "jabatan='"+txt_jabatanprofileguru.getText()+"', "
                                                                        + "nama='"+txt_namaprofileguru.getText()+"', "
                                                                        + "email='"+txt_emailprofileguru.getText()+"', "
                                                                        + "jeniskelamin='"+txt_jkprofileguru.getText()+"', "
                                                                        + "notlp='"+txt_notelpprofileguru.getText()+"', "
                                                                        + "alamat='"+txt_alamatprofileguru.getText()+"'"
                                                                        + "WHERE nip='"+txt_searchguru.getText()+"'");
            rs   = stat.executeQuery(sql);
            
            while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] =rs.getString("nip");
                txt_nipprofileguru.setText((String) obj[0]);
                obj[1] =rs.getString("jabatan");
                txt_jabatanprofileguru.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namaprofileguru.setText((String) obj[2]);
                obj[3] =rs.getString("email");
                txt_emailprofileguru.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_jkprofileguru.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpprofileguru.setText((String) obj[5]);
                obj[6] =rs.getString("alamat");
                txt_alamatprofileguru.setText((String) obj[6]); 
            }
            JOptionPane.showMessageDialog(null, ("Data Guru Berhasil di Update"), 
            "Data Profile Guru", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Guru Gagal di Update"), 
            "Data Profile Guru", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    //VOID MENAMPILKAN DATA NIP GURU PADA SEARCH
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
                obj[2] = rs.getString("nama");
                obj[3] = rs.getString("jabatan");
                obj[4] = rs.getString("jeniskelamin");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //VOID MENAMPILKAN DATA NIP GURU PADA SEARCH VIA TABEL
    public void querysearchklikguru(){
        int i = tabel_guru.getSelectedRow();
        if(i>-1){
            txt_searchguru.setText(model.getValueAt(i, 1).toString());
            Session.setnipguru(txt_searchguru.getText());
        }
    }
    
    //================================WALAS===================================//
    //VOID MENAMPILKAN DATA WALAS PADA TABEL
    public void tampilwalas(){        
        model = new DefaultTableModel ( );
        tabel_walikelas.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Walikelas");
        model.addColumn("NIP");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");

         try{
           stat = con.createStatement( );
           sql  = "Select * from walikelas";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idwalikelas");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
            
             tabel_walikelas.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //PROSEDUR MENGHAPUS DATA WALAS PADA TABEL
    public void deletedatawalas(){
        model = new DefaultTableModel ( );
        tabel_walikelas.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Walikelas");
        model.addColumn("NIP");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM walikelas WHERE idwalikelas='"+txt_searchwalikelas.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idwalikelas");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Walikelas Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tampilnkprofilewalas(){
        txt_nkprofilewalikelas.setText("Tidak Terdaftar");
        Object[ ] obj = new Object[1];
        try {
            stat = con.createStatement( );
            sql  = "SELECT nk FROM kelas WHERE idwalikelas='"+Session.getnipwalas()+"'";
            
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                obj[0] =rs.getString("nk");
                txt_nkprofilewalikelas.setText((String) obj[0]);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Wali Kelas Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        } 
    }
    //PROSEDUR VALIDASI HAPUS DATA WALAS PADA TABEL
    public void validasideletedatawalas(){
        int jwbn=JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Hapus Data Wali Kelas",JOptionPane.YES_NO_OPTION);
        if (jwbn==JOptionPane.YES_OPTION){
            this.deletedatawalas();
            this.tampilwalas();
            txt_searchwalikelas.setText("");
        }
        else if(jwbn==JOptionPane.NO_OPTION){
            
        }
    }
    
    //PROSEDUR MENAMBAH DATA WALAS PADA TABEL
    public void tambahdatawalas(){
        String jeniskelamin = null;
        if(rb3.isSelected()){
            jeniskelamin = "Perempuan";
        } else if (rb4.isSelected()){
            jeniskelamin = "Laki-Laki";
        }
        
        try {
            if (txt_idformwalikelas.getText().equals("")||txt_nipformwalikelas.getText().equals("") || 
                    txt_namaformwalikelas.getText().equals("") || txt_emailformwalikelas.getText().equals("") 
                    || txt_tlpformwalikelas.getText().equals("")|| txt_alamatformwalikelas.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            } else {
                //Digunakan untuk memanggil JDBC driver
                stat = con.createStatement();
                String simpan = "insert into walikelas values ('"+txt_idformwalikelas.getText()
                        +"','"+txt_nipformwalikelas.getText()
                        +"','"+txt_namaformwalikelas.getText()
                        +"','"+txt_emailformwalikelas.getText()
                        +"','"+jeniskelamin
                        +"','"+txt_tlpformwalikelas.getText()
                        +"','"+txt_alamatformwalikelas.getText()
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Wali Kelas Berhasil disimpan!");
                this.hapuslayar();
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, ("Data Wali Kelas Gagal ditambahkan/NIP Tidak Terdaftar"), 
            "Tambah Data Walikelas", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE WALIKELAS
    public void tampilprofilewalas(){
        try {
            stat = con.createStatement( );
            sql  = "SELECT * FROM walikelas WHERE idwalikelas='"+Session.getnipwalas()+"'";
            
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] =rs.getString("idwalikelas");
                txt_idprofilewalikelas.setText((String) obj[0]);
                obj[1] =rs.getString("nip");
                txt_nipprofilewalikelas.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namaprofilewalikelas.setText((String) obj[2]);
                obj[3] =rs.getString("email");
                txt_emailprofilewalikelas.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_jkprofilewalikelas.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpprofilewalikelas.setText((String) obj[5]);
                obj[6] =rs.getString("alamat");
                txt_alamatprofilewalikelas.setText((String) obj[6]);
            }
            this.tampilnkprofilewalas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Lihat Profile Wali Kelas Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        } 
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE WALAS
    public void editprofilewalas(boolean check){
        if (check==true){
            txt_namaprofilewalikelas.setEnabled(true);
            txt_emailprofilewalikelas.setEnabled(true);
            txt_jkprofilewalikelas.setEnabled(true);
            txt_notelpprofilewalikelas.setEnabled(true);
            txt_alamatprofilewalikelas.setEnabled(true);
        }
        else{
            txt_idprofilewalikelas.setEnabled(false);
            txt_nipprofilewalikelas.setEnabled(false);
            txt_namaprofilewalikelas.setEnabled(false);
            txt_emailprofilewalikelas.setEnabled(false);
            txt_jkprofilewalikelas.setEnabled(false);
            txt_notelpprofilewalikelas.setEnabled(false);
            txt_alamatprofilewalikelas.setEnabled(false);
        }
    }
    
    // VOID SIMPAN GURU
    public void simpanprofilewalas(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE walikelas set   idwalikelas='"+txt_idprofilewalikelas.getText()+"', "
                                                                        + "nip='"+txt_nipprofilewalikelas.getText()+"', "
                                                                        + "nama='"+txt_namaprofilewalikelas.getText()+"', "
                                                                        + "email='"+txt_emailprofilewalikelas.getText()+"', "
                                                                        + "jeniskelamin='"+txt_jkprofilewalikelas.getText()+"', "
                                                                        + "notlp='"+txt_notelpprofilewalikelas.getText()+"', "
                                                                        + "alamat='"+txt_alamatprofilewalikelas.getText()+"'"
                                                                        + "WHERE idwalikelas='"+txt_searchwalikelas.getText()+"'");
            rs   = stat.executeQuery(sql);
            this.simpanprofilewalas2();
            while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] =rs.getString("idwalikelas");
                txt_idprofilewalikelas.setText((String) obj[0]);
                obj[1] =rs.getString("nip");
                txt_nipprofilewalikelas.setText((String) obj[1]);
                obj[2] =rs.getString("nama");
                txt_namaprofilewalikelas.setText((String) obj[2]);
                obj[3] =rs.getString("email");
                txt_emailprofilewalikelas.setText((String) obj[3]);
                obj[4] =rs.getString("jeniskelamin");
                txt_jkprofilewalikelas.setText((String) obj[4]);
                obj[5] =rs.getString("notlp");
                txt_notelpprofilewalikelas.setText((String) obj[5]);
                obj[6] =rs.getString("alamat");
                txt_alamatprofilewalikelas.setText((String) obj[6]); 
            }
            JOptionPane.showMessageDialog(null, ("Data Walikelas Berhasil di Update"), 
            "Data Profile Walikelas", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Walikelas Gagal di Update"), 
            "Data Profile walikelas Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void simpanprofilewalas2(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE guru set nama='"+txt_namaprofilewalikelas.getText()+"', "
                                                                        + "email='"+txt_emailprofilewalikelas.getText()+"', "
                                                                        + "jeniskelamin='"+txt_jkprofilewalikelas.getText()+"', "
                                                                        + "notlp='"+txt_notelpprofilewalikelas.getText()+"', "
                                                                        + "alamat='"+txt_alamatprofilewalikelas.getText()+"'"
                                                                        + "WHERE nip='"+txt_nipprofilewalikelas.getText()+"'");
            rs   = stat.executeQuery(sql);

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Guru Gagal di Update"), 
            "Data Profile Guru", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void querydataguruwalas(){
        try {
            Object[ ] obj = new Object[5];
            stat = con.createStatement( );
            sql  = "SELECT * FROM guru WHERE nip='"+txt_nipformwalikelas.getText()+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                
                obj[0] =rs.getString("nama");
                txt_namaformwalikelas.setText((String) obj[0]);
                obj[1] =rs.getString("email");
                txt_emailformwalikelas.setText((String) obj[1]);
                if ("Perempuan".equals(rs.getString("jeniskelamin"))){
                    rb3.setSelected(true);
                    rb4.setSelected(false);
                }
                else{
                    rb4.setSelected(true);
                    rb3.setSelected(false);
                }
                obj[3] =rs.getString("notlp");
                txt_tlpformwalikelas.setText((String) obj[3]);
                obj[4] =rs.getString("alamat");
                txt_alamatformwalikelas.setText((String) obj[4]);
            }
            if (obj[0]==null){
               JOptionPane.showMessageDialog(this, "Data Guru Tidak Ditemukan","Pesan",JOptionPane.ERROR_MESSAGE);
               this.hapuslayar();
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Guru Tidak Ditemukan", 
            "Check NIP Gagal", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    //PROSEDUR MENCARI DATA WALAS PADA TABEL
    public void querysearchwalas(){
        int row = tabel_walikelas.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from walikelas WHERE idwalikelas='"+txt_searchwalikelas.getText()+"'";
                   
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idwalikelas");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("jeniskelamin");
                obj[4] = rs.getString("nama");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void querysearchklikwalas(){
        int i = tabel_walikelas.getSelectedRow();
        if(i>-1){
            txt_searchwalikelas.setText(model.getValueAt(i, 1).toString());
            Session.setnipwalas(txt_searchwalikelas.getText());
        }
    }
        
    //================================KELAS===================================//
    //PROSEDUR MENAMPILKAN DATA KELAS DI TABEL
    public void tampilkelas(){        
        model = new DefaultTableModel ( );
        
        tabel_kelas.setModel(model);
        model.addColumn("No");
        model.addColumn("NK");
        model.addColumn("Wali Kelas");
        model.addColumn("Laki-Laki");
        model.addColumn("Perempuan");
        model.addColumn("Jumlah Siswa");
         try{
           stat = con.createStatement( );
          
          /*sql = "SELECT siswa.nk,kelas.idwalikelas,walikelas.nama,"
                  + "SUM(CASE WHEN siswa.jeniskelamin='Laki-Laki' THEN 1 ELSE 0 END) AS Pria, "
                  + "SUM(CASE WHEN siswa.jeniskelamin='Perempuan' THEN 1 ELSE 0 END) AS Wanita, "
                  + "COUNT(CASE WHEN siswa.jeniskelamin THEN 1 ELSE 0 END) AS JumlahSiswa "
                  + "FROM siswa join kelas,walikelas WHERE siswa.nk=kelas.nk GROUP BY siswa.jeniskelamin"; */
          sql="SELECT k.*, w.nama FROM kelas AS k INNER JOIN walikelas AS w ON k.idwalikelas = w.idwalikelas";
          rs   = stat.executeQuery(sql);
           int no =1;
          while(rs.next ()){
                Object[ ] obj = new Object[6];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nk");
                obj[2] = rs.getString("nama");
                obj[3] = rs.getString("jl");
                obj[4] = rs.getString("jp");
                obj[5] = rs.getString("js");
                model.addRow(obj);
                no++;
            }
       
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //PROSEDUR MENAMBAH DATA WALAS PADA TABEL
    public void tambahdatakelas(){
        try {
            if (txt_nkformkelas.getText().equals("") || cb_walasformkelas.getSelectedItem().equals("") ||
                    txt_tingkatanformkelas.getText().equals("") || txt_jurusanformkelas.getText().equals("") 
                    || txt_namaformkelas.getText().equals("") || txt_taformkelas.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            } else {
                //Digunakan untuk memanggil JDBC driver
                stat = con.createStatement();
                this.getwalaskelas();
                String simpan = "insert into kelas values ('"+txt_nkformkelas.getText()
                        +"','"+wlskelas
                        +"','"+txt_namaformkelas.getText()
                        +"','"+txt_tingkatanformkelas.getText()
                        +"','"+txt_jurusanformkelas.getText()
                        +"','"+cb_smtformkelas.getSelectedItem().toString()
                        +"','"+txt_taformkelas.getText()
                        +"','"+0
                        +"','"+0
                        +"','"+0
                        +"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
                JOptionPane.showMessageDialog(this,"Data Kelas Berhasil disimpan!");
                this.hapuslayar();
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, ("Data Kelas Gagal ditambahkan"), 
          "Tambah Data Kelas", JOptionPane.ERROR_MESSAGE);
System.out.println(e);
        }
    }
    
    public void updatewalasprofilekelas(){
        cb_walasformkelas.removeAllItems();
        cb_walasformkelas.addItem("--pilih--");
        try {
            stat = con.createStatement( );
            String sql = "select * from walikelas";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                cb_walasformkelas.addItem(rs.getString("nama"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deletedatakelas(){
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM kelas WHERE nk='"+txt_searchkelas.getText()+"'");
            rs   = stat.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data kelas Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void validasideletedatakelas(){
        int jwbn=JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Hapus Data Kelas",JOptionPane.YES_NO_OPTION);
        if (jwbn==JOptionPane.YES_OPTION){
            this.deletedatakelas();
            this.tampilkelas();
            txt_searchkelas.setText("");
        }
        else if(jwbn==JOptionPane.NO_OPTION){
            
        }
    }
    
    //PROSEDUR MENAMPILKAN DATA PROFILE KELAS
    public void tampilprofilekelas(){
        try {
            stat = con.createStatement( );
            sql  = "SELECT kelas.*, walikelas.nama from kelas INNER join walikelas WHERE kelas.nk='"+Session.getnkkelas()+"' AND kelas.idwalikelas = walikelas.idwalikelas";
            saveadmkelas.setText(sql);
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[8];
                obj[0] =rs.getString("nk");
                txt_nkprofilekelas.setText((String) obj[0]);
                obj[1] =rs.getString("angkatan");
                txt_angkatanprofilekelas.setText((String) obj[1]);
                obj[2] =rs.getString("jurusan");
                txt_jurusanprofilekelas.setText((String) obj[2]);
                obj[3] =rs.getString("namakelas");
                txt_namaprofilekelas.setText((String) obj[3]);
                obj[4] =rs.getString("nama");
                txt_walasprofilekelas.setText((String) obj[4]);
                obj[5] =rs.getString("js");
                txt_jmlprofilekelas.setText((String) obj[5]);
                obj[6] =rs.getString("semester");
                txt_smtprofilekelas.setText((String) obj[6]);
                obj[7] =rs.getString("tahunajaran");
                txt_taprofilekelas.setText((String) obj[7]);
            }
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
//            "Lihat Profile Wali Kelas Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        } 
    }
    
    //PROSEDUR EDIT DAN SIMPAN PROFILE KELAS
    public void editprofilekelas(boolean check){
        if (check==true){
            txt_nkprofilekelas.setEnabled(true);
            txt_angkatanprofilekelas.setEnabled(true);
            txt_jurusanprofilekelas.setEnabled(true);
            txt_namaprofilekelas.setEnabled(true);
            txt_jmlprofilekelas.setEnabled(false);
            txt_walasprofilekelas.setEnabled(false);
            txt_walasprofilekelas.setVisible(false);
            cb_walasprofilekelas.setVisible(true);
            cb_walasprofilekelas.setEnabled(true);
            txt_smtprofilekelas.setEnabled(false);
            txt_smtprofilekelas.setVisible(false);
            cb_smtprofilekelas.setVisible(true);
            cb_smtprofilekelas.setEnabled(true);
            txt_taprofilekelas.setEnabled(true);
        }
        else{
            txt_nkprofilekelas.setEnabled(false);
            txt_walasprofilekelas.setVisible(true);
            txt_walasprofilekelas.setEnabled(false);
            cb_walasprofilekelas.setVisible(false);
            cb_walasprofilekelas.setEnabled(false);
            txt_nkprofilekelas.setEnabled(false);
            txt_angkatanprofilekelas.setEnabled(false);
            txt_jurusanprofilekelas.setEnabled(false);
            txt_namaprofilekelas.setEnabled(false);
            txt_jmlprofilekelas.setEnabled(false);
            txt_walasprofilekelas.setEnabled(false);
            txt_smtprofilekelas.setEnabled(false);
            txt_smtprofilekelas.setVisible(true);
            cb_smtprofilekelas.setVisible(false);
            cb_smtprofilekelas.setEnabled(false);
            txt_taprofilekelas.setEnabled(false);
        }
    }
    
    public void simpanprofilekelas(){
         try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            this.convernamawalaskelas();
            con.createStatement().executeUpdate("UPDATE kelas set   nk='"+txt_nkprofilekelas.getText()+"', "
                                                                        + "idwalikelas='"+wlsprofkelas+"', "
                                                                        + "angkatan='"+txt_angkatanprofilekelas.getText()+"', "
                                                                        + "jurusan='"+txt_jurusanprofilekelas.getText()+"', "
                                                                        + "namakelas='"+txt_namaprofilekelas.getText()+"', "
                                                                        + "semester='"+cb_smtprofilekelas.getSelectedItem().toString()+"', "
                                                                        + "tahunajaran='"+txt_taprofilekelas.getText()+"' "
                                                                        + "WHERE nk='"+Session.getnkkelas()+"'");
            rs   = stat.executeQuery(sql);
            
            this.tampilprofilekelas();
            
            JOptionPane.showMessageDialog(null, ("Data Kelas Berhasil di Update"), 
            "Data Profile Kelas", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Kelas Gagal di Update"), 
            "Data Profile Kelas Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void tampilanggotakelas(){
        lb_nkak.setText(txt_nkprofilekelas.getText());
        lb_iwak.setText(txt_walasprofilekelas.getText());
        model = new DefaultTableModel ( );
        tabel_anggotakelas.setModel(model);
        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");

        try{
           stat = con.createStatement( );
           sql  = "Select * from siswa where nk='"+Session.getnkkelas()+"'";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[4];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("nama");
                obj[3] = rs.getString("jeniskelamin");
                model.addRow(obj);
                no++;
            }
            this.chartanggotakelas();
            tabel_anggotakelas.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    public void chartanggotakelas(){
        int hadir = 0,sakit = 0,izin = 0,alpha = 0,terlambat = 0;
        try{
           stat = con.createStatement( );
           sql  = "Select * from lapabsen where nk='"+lb_nkak.getText()+"'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                hadir = rs.getInt("hadir")+hadir;
                sakit = rs.getInt("sakit")+sakit;
                izin = rs.getInt("izin")+izin;
                alpha = rs.getInt("alpha")+alpha;
                terlambat =rs.getInt("terlambat")+terlambat;
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
        DefaultPieDataset piedata=new DefaultPieDataset();
        piedata.setValue("Hadir", hadir);
        piedata.setValue("Sakit", sakit);
        piedata.setValue("Izin", izin);
        piedata.setValue("Alpha",alpha);
        piedata.setValue("Terlambat", terlambat);
        JFreeChart chart=ChartFactory.createPieChart("Persentase Kehadiran Siswa per Kelas", piedata,true,false,false);
        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        final PiePlot plot1 = (PiePlot) chart.getPlot();
        plot1.setLabelGenerator(labelGenerator);
        PiePlot piePlot=(PiePlot) chart.getPlot();
        chart.getPlot().setBackgroundPaint( Color.white);
        chart.getPlot().setOutlinePaint(null);
        piePlot.setSectionPaint("Hadir",Color.green);
        piePlot.setSectionPaint("Sakit",Color.yellow);
        piePlot.setSectionPaint("Izin",Color.blue);
        piePlot.setSectionPaint("Alpha",Color.red);
        piePlot.setSectionPaint("Terlambat",Color.orange);
        ChartPanel barChartPanel=new ChartPanel(chart);
        panel_chartanggotakelas.removeAll();
        panel_chartanggotakelas.add(barChartPanel);
        panel_chartanggotakelas.validate();
    }
    
    public void convernamawalaskelas() throws SQLException{
        String value = cb_walasprofilekelas.getSelectedItem().toString();
            sql  = "Select idwalikelas from walikelas WHERE nama='"+value+"'";
            rs   = stat.executeQuery(sql);
             while(rs.next ()){
                wlsprofkelas=(rs.getString("idwalikelas"));
            }
    }
    
    public void getwalasprofilekelas(){
        cb_walasprofilekelas.removeAllItems();
        cb_walasprofilekelas.addItem("--pilih--");
        try {
            stat = con.createStatement( );
            String sql = "select idwalikelas,nama from walikelas";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                cb_walasprofilekelas.addItem(rs.getString("nama"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void getwalaskelas(){
        String value = cb_walasformkelas.getSelectedItem().toString();
        try {
            stat = con.createStatement( );
            sql  = "Select idwalikelas from walikelas WHERE nama='"+value+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("idwalikelas");
                wlskelas=(String) obj[0];
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    public void querysearchanggotakelas(){
        int row = tabel_anggotakelas.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        if(txt_searchnisanggotakelas.getText().equals("")){
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Pesan", JOptionPane.ERROR_MESSAGE);
            this.tampilanggotakelas();
        }
        else{
            try{
               stat = con.createStatement( );
               sql  = "Select * from siswa WHERE nk='"+lb_nkak.getText()+"' AND nis='"+txt_searchnisanggotakelas.getText()+"'";
               rs   = stat.executeQuery(sql);

               int no=1;
               while(rs.next ()){
                    Object[ ] obj = new Object[4];
                    obj[0] = Integer.toString(no);
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nama");
                    obj[3] = rs.getString("jeniskelamin");
                    model.addRow(obj);
                    no++;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
            "Pesan", JOptionPane.ERROR_MESSAGE);
                this.tampilanggotakelas();
            }
        }
    }
    
    public void querysearchkelas(){
        int row = tabel_kelas.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from kelas WHERE nk='"+txt_searchkelas.getText()+"'";
                   
           rs   = stat.executeQuery(sql);

           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("nk");
                obj[2] = rs.getString("namakelas");
                obj[3] = rs.getString("angkatan");
                obj[4] = rs.getString("jurusan");
                model.addRow(obj);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(keloladata_bk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void querysearchklikkelas(){
        int i = tabel_kelas.getSelectedRow();
        if(i>-1){ 
            txt_searchkelas.setText(model.getValueAt(i, 1).toString());
            Session.setnkkelas(txt_searchkelas.getText());
        }
    }
    //================================ADMIN===================================//
    //VOID MENAMPILKAN DATA ADMIN PADA TABEL
    public void tampiladmin(){        
        model = new DefaultTableModel ( );
        tabel_admin.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Admin");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Status");
        
         try{
           stat = con.createStatement( );
           sql  = "Select * from admin";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[6];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idadmin");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("username");
                String getlevel = rs.getString("level");
                if ("0".equals(getlevel))
                {
                    obj[5]="Guru BK";
                }
                else if("1".equals(getlevel))
                {
                    obj[5]="Bagian IT";
                }
                else
                {
                    obj[5]="Kepala Sekolah";
                }
                model.addRow(obj);
                no++;
            }
            
             tabel_admin.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //HAPUS DATA ADMIN
    public void deletedataadmin(){
        model = new DefaultTableModel();
        tabel_admin.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Admin");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Status");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM admin WHERE idadmin='"+saveadm.getText()+"'");
            rs   = stat.executeQuery(sql);
            int no=1;
            this.tampiladmin();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data tidak ditemukan dan tidak dapat dihapus"), 
            "Delete Data Admin Gagal", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void validasideletedataadmin(){
        try{
           stat = con.createStatement( );
           sql  = "Select username from admin where nip='"+Session.getnipadmin()+"'";
           rs   = stat.executeQuery(sql);
           int no=1;
           while(rs.next ()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("username");
                usernameadm=(String) obj[0];
            }
        } catch (SQLException e) {
            System.out.println(e);
            
      }
        if (usernameadm.equals(username.getText())){
            JOptionPane.showMessageDialog(null, ("Akun Sedang Di Gunakan"), 
            "Pesan", JOptionPane.ERROR_MESSAGE);
        }
        else{
             int jwbn=JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Hapus Data Admin",JOptionPane.YES_NO_OPTION);
                if (jwbn==JOptionPane.YES_OPTION){
                this.deletedataadmin();
                this.tampiladmin();
                txt_searchadmin.setText("");
                saveadm.setText("");
            }  
        }    
    }
    
    //PROSEDUR TAMBAH DATA ADMIN
    public void tambahdataadmin(){
        this.leveladmin();
        try {
            if (txt_idregadmin.getText().equals("") || txt_usernameregadmin.getText().equals("") 
                    || txt_namaregadmin.getText().equals("") || txt_nipregadmin.getText().equals("") || txt_passwordregadmin.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong","Pesan",JOptionPane.ERROR_MESSAGE);
            hapuslayar();
            } else {
                String simpan = "insert into admin(idadmin,nip,nama,username,password,level) values ('"+txt_idregadmin.getText()+"',"
                        + "'"+txt_nipregadmin.getText()+"',"
                        + "'"+txt_namaregadmin.getText()+"',"
                        + "'"+txt_usernameregadmin.getText()+"',"
                        + "'"+txt_passwordregadmin.getText()+"',"
                        + "'"+leveluser+"')";
                stat = con.createStatement();
                int SA =stat.executeUpdate(simpan);
               // saveadm.setText("");
                JOptionPane.showMessageDialog(this,"Registrasi Anda Berhasil!");
            }
        } catch (HeadlessException | SQLException e) {
           JOptionPane.showMessageDialog(this, "Periksa kembali mengenai NIP atau Akun yang akan dibuat sudah ada","Pesan",JOptionPane.WARNING_MESSAGE);
           hapuslayar();
        }
    }
    
    public void querydataguruadmin(){
        try {
            Object[ ] obj = new Object[1];
            stat = con.createStatement( );
            sql  = "SELECT * FROM guru WHERE nip='"+txt_nipregadmin.getText()+"'";
            rs   = stat.executeQuery(sql);
            while(rs.next ()){
                
                obj[0] =rs.getString("nama");
                txt_namaregadmin.setText((String) obj[0]);
            }
            if (obj[0]==null){
               JOptionPane.showMessageDialog(this, "Data Guru Tidak Ditemukan","Pesan",JOptionPane.ERROR_MESSAGE);
               this.hapuslayar();
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Guru Tidak Ditemukan", 
            "Check NIP Gagal", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void leveladmin(){
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
    }
    
    //UPDATE DATA ADMIN
    public void simpandataadmin(){
        try {
            // TODO add your handling code here:
            stat = con.createStatement( );
            this.convertstatusprofileadmin();
            con.createStatement().executeUpdate("UPDATE admin set   nip='"+txt_searchadmin.getText()+"', "
                                                                        + "idadmin='"+txt_idadminprofileadmin.getText()+"', "
                                                                        + "nama='"+txt_namaprofileadmin.getText()+"', "
                                                                        + "username='"+txt_usernameprofileadmin.getText()+"', "
                                                                        + "password='"+txt_passprofileadmin.getText()+"', "
                                                                         + "level='"+leveluser3+"' "
                                                                        + "WHERE nip='"+Session.getnipadmin()+"'");
            rs   = stat.executeQuery(sql);
            
            this.tampilprofileadmin();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Admin Gagal di Update"), 
            "Data Profile Admin Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public void checkusernameprofileadmin(){
        if(txt_nipprofileadmin.getText().equals(Session.geteditadmin()))
        {
            int jwbn=JOptionPane.showConfirmDialog(null, "Benarkah anda ingin merubah username diri sendiri? (Relogin)", "Update Data Admin",JOptionPane.YES_NO_OPTION);
            if (jwbn==JOptionPane.YES_OPTION){
                this.simpandataadmin();
                this.logout();
            }
        }
        else{
            this.simpandataadmin();
            JOptionPane.showMessageDialog(null, ("Data Admin Berhasil di Update"), 
            "Data Profile Admin", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void convertstatusprofileadmin(){
        String value = cb_statusprofileadmin.getSelectedItem().toString();
        if(value=="Guru BK"){
            leveluser3=0;
        }
        else if(value=="Bagian IT"){
            leveluser3=1;
        }
        else{
            leveluser3=2;
        }
    }
    
    public void checkpassprofileadmin(){
        if(btn_checkpassprofileadmin.isSelected()){
            txt_passprofileadmin.setVisible(false);
            txt_pass2profileadmin.setVisible(true);
            txt_pass2profileadmin.setEnabled(false);
            txt_usernameprofileadmin.setEnabled(false);
            txt_statusprofileadmin.setEnabled(false);
        }
        else{
            txt_passprofileadmin.setVisible(true);
            txt_passprofileadmin.setEnabled(false);
            txt_pass2profileadmin.setVisible(false);
        }
    }
    
    public void editprofileadmin(boolean check){
        if (check==true){
            txt_namaprofileadmin.setEnabled(false);
            txt_nipprofileadmin.setEnabled(false);
            txt_namaprofileadmin.setEnabled(false);
            txt_usernameprofileadmin.setEnabled(true);
            txt_passprofileadmin.setEnabled(true);
            txt_statusprofileadmin.setEnabled(true);
            txt_pass2profileadmin.setVisible(false);
            txt_passprofileadmin.setVisible(true);
            if("Guru BK".equals(txt_statusprofileadmin.getText())){
                txt_statusprofileadmin.setEnabled(false);
                txt_statusprofileadmin.setVisible(true);
                cb_statusprofileadmin.setVisible(false);
                cb_statusprofileadmin.setEnabled(false);
            }
            else{
                txt_statusprofileadmin.setEnabled(false);
                txt_statusprofileadmin.setVisible(false);
                cb_statusprofileadmin.setVisible(true);
                cb_statusprofileadmin.setEnabled(true);
            }
        }
        else{
            txt_namaprofileadmin.setEnabled(false);
            txt_nipprofileadmin.setEnabled(false);
            txt_namaprofileadmin.setEnabled(false);
            txt_usernameprofileadmin.setEnabled(false);
            txt_passprofileadmin.setEnabled(false);
            txt_statusprofileadmin.setEnabled(false);
            txt_statusprofileadmin.setEnabled(false);
            txt_statusprofileadmin.setVisible(true);
            cb_statusprofileadmin.setVisible(false);
            cb_statusprofileadmin.setEnabled(false);
        }
    }   
    
    //PROSEDUR PROFILE ADMIN
    public void tampilprofileadmin(){
        try {
            if(saveadm.getText()!=null){
                stat = con.createStatement( );
                sql  = "SELECT * FROM admin WHERE nip='"+Session.getnipadmin()+"'";
            
                rs   = stat.executeQuery(sql);
                while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] =rs.getString("idadmin");
                    txt_idadminprofileadmin.setText((String) obj[0]);
                    obj[1] =rs.getString("nip");
                    txt_nipprofileadmin.setText((String) obj[1]);
                    obj[2] =rs.getString("nama");
                    txt_namaprofileadmin.setText((String) obj[2]);
                    obj[3] =rs.getString("username");
                    txt_usernameprofileadmin.setText((String) obj[3]);
                    obj[4] =rs.getString("password");
                    txt_passprofileadmin.setText((String) obj[4]);
                    obj[5] =rs.getString("password");
                    txt_pass2profileadmin.setText((String) obj[5]);
                    obj[6] =rs.getString("level");
                    leveluser2=(String) obj[6];    
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

    public void convertstatusadmin(){
        if (leveluser2.equals("0"))
        {
            txt_statusprofileadmin.setText("Guru BK");
        }
        else if(leveluser2.equals("1"))
        {
            txt_statusprofileadmin.setText("Bagian IT");
        }
        else{
            txt_statusprofileadmin.setText("Kepala Sekolah");
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
                Object[ ] obj = new Object[6];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idadmin");
                obj[2] = rs.getString("nip");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("username");
                String getlevel = rs.getString("level");
                if ("0".equals(getlevel))
                {
                    obj[5]="Guru BK";
                }
                else if("1".equals(getlevel))
                {
                    obj[5]="Bagian IT";
                }
                else
                {
                    obj[5]="Kepala Sekolah";
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
    
    //================================DATA ABSEN===================================//
    //PROSEDUR MENAMPILKAN DATA ABSEN DI TABEL
    public void tampilabsen(){        
        model = new DefaultTableModel ( );
        tabel_absen.setModel(model);
        model.addColumn("idabsen");
        model.addColumn("NIS");
        model.addColumn("NK");
        model.addColumn("Nama");
        model.addColumn("Tanggal");
        model.addColumn("Jam");  
        model.addColumn("Status");

         try{
           stat = con.createStatement( );
           sql  = "Select * from absen";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                Object[ ] obj = new Object[7];
                obj[0] = rs.getString("idabsen");
                obj[1] = rs.getString("nis");
                obj[2] = rs.getString("nk");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("tanggal");
                obj[5] = rs.getString("jam");
                obj[6] = rs.getString("status");
                model.addRow(obj);
            }
            
             tabel_absen.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
     public void updatestatusabsen(){
       try {

            stat = con.createStatement( );
            con.createStatement().executeUpdate("UPDATE absen set status='"+cb_statusdataabsen.getSelectedItem()+"' WHERE idabsen='"+idabsen+"'");
            System.out.println(idabsen);
            rs   = stat.executeQuery(sql);
            
            this.tampilabsen();
            
            JOptionPane.showMessageDialog(null, ("Update berhasil"), 
            "Update Status", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("Data Gagal di Update"), 
            "Data Profile Kelas Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    //PROSEDUR MENCARI DATA ABSEN PADA TABEL BY NIS
    public void querysearchabsen(){
        //QUERY TANGGAL PERTAMA
        if(txt_searchdataabsen.getText().equals("") && txt_searchtgl2dataabsen.getText().equals("")){
            int row = tabel_absen.getRowCount();
            for(int a=0;a<row;a++){
                model.removeRow(0);
            }
            txt_searchtgl2dataabsen.setText(txt_searchtgldataabsen.getText());
            try{
               stat = con.createStatement( );
               sql  = "Select * from absen WHERE tanggal='"+txt_searchtgldataabsen.getText()+"'";
               rs   = stat.executeQuery(sql);

               while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] = rs.getString("idabsen");
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nk");
                    obj[3] = rs.getString("nama");
                    obj[4] = rs.getString("tanggal");
                    obj[5] = rs.getString("jam");
                    obj[6] = rs.getString("status");
                    model.addRow(obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(txt_searchtgl2dataabsen.getText().equals("")){
            int row = tabel_absen.getRowCount();
            for(int a=0;a<row;a++){
                model.removeRow(0);
            }
            txt_searchtgl2dataabsen.setText(txt_searchtgldataabsen.getText());
            try{
               stat = con.createStatement( );
               sql  = "Select * from absen WHERE nis='"+txt_searchdataabsen.getText()+"' AND tanggal='"+txt_searchtgldataabsen.getText()+"'";
               rs   = stat.executeQuery(sql);

               while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] = rs.getString("idabsen");
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nk");
                    obj[3] = rs.getString("nama");
                    obj[4] = rs.getString("tanggal");
                    obj[5] = rs.getString("jam");
                    obj[6] = rs.getString("status");
                    model.addRow(obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //QUERY BERDASARKAN NIS
        else if(txt_searchtgldataabsen.getText().equals("") && txt_searchtgl2dataabsen.getText().equals("")){
            int row = tabel_absen.getRowCount();
            for(int a=0;a<row;a++){
                model.removeRow(0);
            }

            try{
               stat = con.createStatement( );
               sql  = "Select * from absen WHERE nis='"+txt_searchdataabsen.getText()+"'";
               rs   = stat.executeQuery(sql);

               while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] = rs.getString("idabsen");
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nk");
                    obj[3] = rs.getString("nama");
                    obj[4] = rs.getString("tanggal");
                    obj[5] = rs.getString("jam");
                    obj[6] = rs.getString("status");
                    model.addRow(obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }  
        }
        //QUERY TANGGAL PERTAMA DAN AKHIR TANPA NIS
        else if(txt_searchtgldataabsen.getText()!=null && txt_searchtgl2dataabsen.getText()!=null && txt_searchdataabsen.getText().equals("")){
            int row = tabel_absen.getRowCount();
            for(int a=0;a<row;a++){
                model.removeRow(0);
            }

            try{
               stat = con.createStatement( );
               sql  = "SELECT * FROM absen WHERE (tanggal BETWEEN '"+txt_searchtgldataabsen.getText()+"' AND '"+txt_searchtgl2dataabsen.getText()+"')";
               rs   = stat.executeQuery(sql);

               while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] = rs.getString("idabsen");
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nk");
                    obj[3] = rs.getString("nama");
                    obj[4] = rs.getString("tanggal");
                    obj[5] = rs.getString("jam");
                    obj[6] = rs.getString("status");
                    model.addRow(obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }  
        }
        //QUERY FULL
        else{
            int row = tabel_absen.getRowCount();
            for(int a=0;a<row;a++){
                model.removeRow(0);
            }

            try{
               stat = con.createStatement( );
               sql  = "SELECT * FROM absen WHERE nis='"+txt_searchdataabsen.getText()+"' AND (tanggal BETWEEN '"+txt_searchtgldataabsen.getText()+"' AND '"+txt_searchtgl2dataabsen.getText()+"')";
               rs   = stat.executeQuery(sql);

               while(rs.next ()){
                    Object[ ] obj = new Object[7];
                    obj[0] = rs.getString("idabsen");
                    obj[1] = rs.getString("nis");
                    obj[2] = rs.getString("nk");
                    obj[3] = rs.getString("nama");
                    obj[4] = rs.getString("tanggal");
                    obj[5] = rs.getString("jam");
                    obj[6] = rs.getString("status");
                    model.addRow(obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ("Data tidak ditemukan"), 
                "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
     
    public void querysearchklikabsen(){
        int i = tabel_absen.getSelectedRow();
        if(i>-1){
            txt_searchdataabsen.setText(model.getValueAt(i, 1).toString());
            lb_idabsen.setText(model.getValueAt(i, 0).toString());
            idabsen=lb_idabsen.getText();
            txt_searchtgldataabsen.setText(model.getValueAt(i, 4).toString());
        }
    }
    
    //========================== DATA LAP ABSEN ==============================//
    public void tampillapabsen(){
        model = new DefaultTableModel ( );
        
        tabel_lapabsen.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Laporan");
        model.addColumn("Wali Kelas");
        model.addColumn("NIS");
        model.addColumn("NK");
        model.addColumn("Nama");
        model.addColumn("Hadir");
        model.addColumn("Sakit");
        model.addColumn("Izin");
        model.addColumn("Alpha");
        model.addColumn("Terlambat");
         try{
          stat = con.createStatement( );
          
          sql="SELECT la.*, w.nama FROM lapabsen AS la INNER JOIN walikelas AS w ON la.idwalikelas = w.idwalikelas";
          rs   = stat.executeQuery(sql);
           int no =1;
          while(rs.next ()){
                Object[ ] obj = new Object[11];
                obj[0] = Integer.toString(no);
                obj[1] = rs.getString("idlapabsen");
                obj[2] = rs.getString("w.nama");
                obj[3] = rs.getString("nis");
                obj[4] = rs.getString("nk");
                obj[5] = rs.getString("nama");
                obj[6] = rs.getString("hadir");
                obj[7] = rs.getString("sakit");
                obj[8] = rs.getString("izin");
                obj[9] = rs.getString("alpha");
                obj[10] = rs.getString("terlambat");
                model.addRow(obj);
                if (tabel_lapabsen.getColumnModel().getColumnCount() > 0) {
                    tabel_lapabsen.getColumnModel().getColumn(0).setMinWidth(45);
                    tabel_lapabsen.getColumnModel().getColumn(0).setMaxWidth(45);
                    tabel_lapabsen.getColumnModel().getColumn(1).setMinWidth(125);
                    tabel_lapabsen.getColumnModel().getColumn(1).setMaxWidth(125);
                    tabel_lapabsen.getColumnModel().getColumn(2).setMinWidth(150);
                    tabel_lapabsen.getColumnModel().getColumn(2).setMaxWidth(150);
                    tabel_lapabsen.getColumnModel().getColumn(3).setMinWidth(100);
                    tabel_lapabsen.getColumnModel().getColumn(3).setMaxWidth(100);
                    tabel_lapabsen.getColumnModel().getColumn(4).setMinWidth(100);
                    tabel_lapabsen.getColumnModel().getColumn(4).setMaxWidth(100);
                    tabel_lapabsen.getColumnModel().getColumn(5).setMinWidth(200);
                    tabel_lapabsen.getColumnModel().getColumn(5).setMaxWidth(200);
                    tabel_lapabsen.getColumnModel().getColumn(6).setMinWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(6).setMaxWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(7).setMinWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(7).setMaxWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(8).setMinWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(8).setMaxWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(9).setMinWidth(53);
                    tabel_lapabsen.getColumnModel().getColumn(9).setMaxWidth(50);
                    tabel_lapabsen.getColumnModel().getColumn(10).setMinWidth(80);
                    tabel_lapabsen.getColumnModel().getColumn(10).setMaxWidth(80);
                }
                no++;
            }
       
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    //==============================PERIPHERAL================================//
    //SWITCH PANEL
    public void switchpanel(JLayeredPane panel){
        LayerPane.removeAll();
        LayerPane.add(panel);
        LayerPane.repaint();
        LayerPane.revalidate();
    }

    //RESET FORM
    private void hapuslayar(){
        //siswa   
        txt_rfidformsiswa.setText("");txt_nisformsiswa.setText("");
        txt_nkformsiswa.setText("");txt_namaformsiswa.setText("");
        txt_alamatformsiswa.setText("");txt_buttonJKsiswa.clearSelection();
        rb1.setSelected(false);rb2.setSelected(false);
        txt_telpformsiswa.setText("");txt_idwalasformsiswa.setText("");
        txt_emailformsiswa.setText("");txt_nortuformsiswa.setText("");
        txt_noortuformsiswa.setText("");
        
        //guru
        txt_nipformguru.setText("");txt_jabatanformguru.setText("");
        txt_namaformguru.setText("");txt_emailformguru.setText("");
        txt_buttonJKguru.clearSelection();
        rb5.setSelected(false);rb6.setSelected(false);
        txt_tlpformguru.setText("");txt_alamatformguru.setText(""); 
        
        //walikelas
        txt_nipformwalikelas.setText("");
        txt_namaformwalikelas.setText("");  txt_emailformwalikelas.setText("");
        txt_buttonJKwalikelas.clearSelection();
        rb3.setSelected(false);rb4.setSelected(false);
        txt_tlpformwalikelas.setText("");txt_alamatformwalikelas.setText(""); 
        //kelas                    
        txt_nkformkelas.setText("");txt_tingkatanformkelas.setText("");
        txt_jurusanformkelas.setText("");txt_namaformkelas.setText("");
        
        //admin
        txt_nipregadmin.setText("");txt_namaregadmin.setText("");
        txt_usernameregadmin.setText("");txt_passwordregadmin.setText("");
    }
    
    //AUTO NUMBER
    public void Autonumber(){
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
               txt_idregadmin.setText("ADM" + Nol + AN);
            } else {
               txt_idregadmin.setText("ADM001");
            }

           }catch(NumberFormatException | SQLException e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    
    public void autonumberwalas(){
       try {
            sql="select * from walikelas order by idwalikelas desc";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            if (rs.next()) {
                String noid = rs.getString("idwalikelas").substring(3);
                String AN = "" + (Integer.parseInt(noid) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "00";}
                else if(AN.length()==2)
                {Nol = "0";}
                else if(AN.length()==3)
                {Nol = "";}
               txt_idformwalikelas.setText("WLS" + Nol + AN);
            } else {
               txt_idformwalikelas.setText("WLS001");
            }

           }catch(NumberFormatException | SQLException e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    
    //SET TABLE
    public void settable(){
        //SETTING TRANSPARASI SCROLLPANE TABEL SISWA
        ((DefaultTableCellRenderer)tabel_siswa.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_siswa.setGridColor(Color.BLACK);
        tabel_siswa.setForeground(Color.BLACK);
        tabsiswa.setOpaque(false);
        tabsiswa.getViewport().setOpaque(false);
        tabsiswa.setViewportBorder(null);
        tabel_siswa.setShowGrid(true);
        
        //SETTING TRANPARANSI SCROLLPANE TABEL GURU
        ((DefaultTableCellRenderer)tabel_guru.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_guru.setGridColor(Color.BLACK);
        tabel_guru.setForeground(Color.BLACK);
        tabguru.setOpaque(false);
        tabguru.getViewport().setOpaque(false);
        tabguru.setViewportBorder(null);
        tabel_guru.setShowGrid(true);
        
        //SETTING TRANPARANSI SCROLLPANE TABEL WALIKELAS
        ((DefaultTableCellRenderer)tabel_walikelas.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_walikelas.setGridColor(Color.BLACK);
        tabel_walikelas.setForeground(Color.BLACK);
        tabwalikelas.setOpaque(false);
        tabwalikelas.getViewport().setOpaque(false);
        tabwalikelas.setViewportBorder(null);
        tabel_walikelas.setShowGrid(true);
               
        //SETTING TRANSPARASI SCROLLPANE TABLE KELAS
        ((DefaultTableCellRenderer)tabel_kelas.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_kelas.setGridColor(Color.BLACK);
        tabel_kelas.setForeground(Color.BLACK);
        tabkelas.setOpaque(false);
        tabkelas.getViewport().setOpaque(false);
        tabkelas.setViewportBorder(null);
        tabel_kelas.setShowGrid(true);
        
    //SETTING TRANSPARASI SCROLLPANE TABLE LAP ADMIN
        ((DefaultTableCellRenderer)tabel_admin.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_admin.setGridColor(Color.BLACK);
        tabel_admin.setForeground(Color.BLACK);
        tabadmin.setOpaque(true);        
        tabadmin.getViewport().setOpaque(false);
        tabadmin.setViewportBorder(null);
        tabel_admin.setShowGrid(true);
        
        //SETTING TRANSPARASI SCROLLPANE TABLE LAP ABSEN
        ((DefaultTableCellRenderer)tabel_absen.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_absen.setGridColor(Color.BLACK);
        tabel_absen.setForeground(Color.BLACK);
        tababsen.setOpaque(false);
        tababsen.getViewport().setOpaque(false);
        tababsen.setViewportBorder(null);
        tabel_absen.setShowGrid(true);
        
        //SETTING TRANSPARASI SCROLLPANE RIWAYAT ABSENSI
        ((DefaultTableCellRenderer)tabel_riwayatsiswa.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_riwayatsiswa.setGridColor(Color.BLACK);
        tabel_riwayatsiswa.setForeground(Color.BLACK);
        tabriwayatsiswa.setOpaque(false);
        tabriwayatsiswa.getViewport().setOpaque(false);
        tabriwayatsiswa.setViewportBorder(null);
        tabel_riwayatsiswa.setShowGrid(true);
        
        //SETTING TRANSPARASI SCROLLPANE TABLE ANGGOTA KELAS
        ((DefaultTableCellRenderer)tabel_anggotakelas.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_anggotakelas.setGridColor(Color.BLACK);
        tabel_anggotakelas.setForeground(Color.BLACK);
        tab_anggotakelas.setOpaque(false);
        tab_anggotakelas.getViewport().setOpaque(false);
        tab_anggotakelas.setViewportBorder(null);
        tabel_anggotakelas.setShowGrid(true);
        
        ((DefaultTableCellRenderer)tabel_lapabsen.getDefaultRenderer(Object.class)).setBackground(new Color(255,255,255));
        tabel_lapabsen.setGridColor(Color.BLACK);
        tabel_lapabsen.setForeground(Color.BLACK);
        tab_lapabsen.setOpaque(false);
        tab_lapabsen.getViewport().setOpaque(false);
        tab_lapabsen.setViewportBorder(null);
        tabel_lapabsen.setShowGrid(true);
    }
  
    //RESET ALL PANEL
    public void resetpanel(){
        datasiswa.setVisible(false);
        datawalikelas.setVisible(false);
        datakelas.setVisible(false);
        dataabsensi.setVisible(false);
        datalapabsensi.setVisible(false);
        profilesiswa.setVisible(false);
        dataadmin.setVisible(false);
    }
    
    public void logout(){
        btn_logout.setBackground(new Color(88,163,234));
        this.setVisible(false);
        Session.setusername(null);
        Session.setnipguru(null);
        Session.setnipadmin(null);
        Session.setnissiswa(null);
        Session.setnipwalas(null);
        Session.setnkkelas(null);
        login lgn=new login();
        lgn.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_buttonJKsiswa = new javax.swing.ButtonGroup();
        txt_buttonJKguru = new javax.swing.ButtonGroup();
        txt_buttonJKwalikelas = new javax.swing.ButtonGroup();
        PanelUtama = new javax.swing.JPanel();
        panelbutton = new javax.swing.JPanel();
        btn_dashboard = new javax.swing.JPanel();
        lbdashboard = new javax.swing.JLabel();
        icondashboard = new javax.swing.JLabel();
        btn_datasiswa = new javax.swing.JPanel();
        lbsiswa = new javax.swing.JLabel();
        iconsiswa = new javax.swing.JLabel();
        btn_dataguru = new javax.swing.JPanel();
        lb_guru = new javax.swing.JLabel();
        icon_guru = new javax.swing.JLabel();
        btn_datawalikelas = new javax.swing.JPanel();
        lb_walikelas = new javax.swing.JLabel();
        icon_walikelas = new javax.swing.JLabel();
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
        form_registrasi = new javax.swing.JLayeredPane();
        panelformregistrasi = new javax.swing.JPanel();
        lb_idregadmin = new javax.swing.JLabel();
        lb_nipregadmin = new javax.swing.JLabel();
        lb_namaregadmin = new javax.swing.JLabel();
        lb_usernameregadmin = new javax.swing.JLabel();
        lb_paswordregadmin = new javax.swing.JLabel();
        lb_statusregadmin = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txt_idregadmin = new javax.swing.JTextField();
        txt_nipregadmin = new javax.swing.JTextField();
        txt_namaregadmin = new javax.swing.JTextField();
        txt_usernameregadmin = new javax.swing.JTextField();
        txt_passwordregadmin = new javax.swing.JPasswordField();
        btn_daftarregadmin = new javax.swing.JButton();
        txt_level = new javax.swing.JComboBox<>();
        btn_kembaliregadmin = new javax.swing.JButton();
        btn_scantambahadmin = new javax.swing.JButton();
        bgregistrasiadmin = new javax.swing.JLabel();
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
        cb_gendersiswa = new javax.swing.JComboBox<>();
        btn_scanprofilesiswa = new javax.swing.JButton();
        btn_editprofilesiswa = new javax.swing.JButton();
        btn_simpanprofilesiswa = new javax.swing.JButton();
        btn_riwayatabsen = new javax.swing.JButton();
        btn_kembaliprofilesiswa = new javax.swing.JButton();
        bgprofilesiswa = new javax.swing.JLabel();
        form_siswa = new javax.swing.JLayeredPane();
        panelformsiswa = new javax.swing.JPanel();
        lb_rfidformsiswa = new javax.swing.JLabel();
        lb_nkformsiswa = new javax.swing.JLabel();
        lb_nisformsiswa = new javax.swing.JLabel();
        lb_namaformsiswa = new javax.swing.JLabel();
        lb_alamatformsiswa = new javax.swing.JLabel();
        lb_jkformsiswa = new javax.swing.JLabel();
        lb_emailformsiswa = new javax.swing.JLabel();
        lb_idwalasformsiswa = new javax.swing.JLabel();
        lb_telpformsiswa = new javax.swing.JLabel();
        lb_nortuformsiswa = new javax.swing.JLabel();
        lb_noortuformsiswa = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_rfidformsiswa = new javax.swing.JTextField();
        txt_nkformsiswa = new javax.swing.JTextField();
        txt_nisformsiswa = new javax.swing.JTextField();
        txt_namaformsiswa = new javax.swing.JTextField();
        txt_alamatformsiswa = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        txt_emailformsiswa = new javax.swing.JTextField();
        txt_idwalasformsiswa = new javax.swing.JTextField();
        txt_telpformsiswa = new javax.swing.JTextField();
        txt_nortuformsiswa = new javax.swing.JTextField();
        txt_noortuformsiswa = new javax.swing.JTextField();
        btn_scantambahsiswa1 = new javax.swing.JButton();
        btn_simpantambahsiswa = new javax.swing.JButton();
        btn_scantambahsiswa = new javax.swing.JButton();
        btn_kembalitambahisiswa = new javax.swing.JButton();
        bgtambahsiswa = new javax.swing.JLabel();
        riwayat_siswa = new javax.swing.JLayeredPane();
        panelriwayatsiswa = new javax.swing.JPanel();
        panel_chartrs = new javax.swing.JPanel();
        txt_tanggalriwayatsiswa = new javax.swing.JTextField();
        txt_tanggal2riwayatsiswa = new javax.swing.JTextField();
        lb_sampairiwayatsiswa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_nisrs = new javax.swing.JLabel();
        lb_namars = new javax.swing.JLabel();
        lb_nkrs = new javax.swing.JLabel();
        lb_nisriwayatsiswa = new javax.swing.JLabel();
        lb_namariwayatsiswa = new javax.swing.JLabel();
        lb_nkriwayatsiswa = new javax.swing.JLabel();
        btn_searchriwayatsiswa = new javax.swing.JButton();
        btn_cetakriwayatsiswa = new javax.swing.JButton();
        tabriwayatsiswa = new javax.swing.JScrollPane();
        tabel_riwayatsiswa = new javax.swing.JTable();
        btn_refreshriwayatsiswa = new javax.swing.JButton();
        btn_kembaliriwayatabsen = new javax.swing.JButton();
        btn_lihathistory = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bgriwayatsiswa = new javax.swing.JLabel();
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
        lb_nipprofileguru = new javax.swing.JLabel();
        lb_jabatanprofileguru = new javax.swing.JLabel();
        lb_namaprofileguru = new javax.swing.JLabel();
        lb_emailprofileguru = new javax.swing.JLabel();
        lb_jkprofileguru = new javax.swing.JLabel();
        lb_notelpprofileguru = new javax.swing.JLabel();
        lb_alamatprofileguru = new javax.swing.JLabel();
        lb_titik15 = new javax.swing.JLabel();
        lb_titik16 = new javax.swing.JLabel();
        lb_titik17 = new javax.swing.JLabel();
        lb_titik18 = new javax.swing.JLabel();
        lb_titik19 = new javax.swing.JLabel();
        lb_titik20 = new javax.swing.JLabel();
        lb_titik21 = new javax.swing.JLabel();
        txt_nipprofileguru = new javax.swing.JTextField();
        txt_jabatanprofileguru = new javax.swing.JTextField();
        txt_namaprofileguru = new javax.swing.JTextField();
        txt_emailprofileguru = new javax.swing.JTextField();
        txt_jkprofileguru = new javax.swing.JTextField();
        txt_notelpprofileguru = new javax.swing.JTextField();
        txt_alamatprofileguru = new javax.swing.JTextField();
        btn_simpanprofileguru = new javax.swing.JButton();
        btn_editprofileguru = new javax.swing.JButton();
        btn_kembaliprofileguru = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lb_gambar2 = new javax.swing.JLabel();
        bgprofileguru = new javax.swing.JLabel();
        form_guru = new javax.swing.JLayeredPane();
        panelformguru = new javax.swing.JPanel();
        lb_nipformguru = new javax.swing.JLabel();
        lb_jabatanformguru = new javax.swing.JLabel();
        lb_namaformguru = new javax.swing.JLabel();
        lb_emailformguru = new javax.swing.JLabel();
        lb_jkformguru = new javax.swing.JLabel();
        lb_notelpformguru = new javax.swing.JLabel();
        lb_alamatformguru = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txt_nipformguru = new javax.swing.JTextField();
        txt_jabatanformguru = new javax.swing.JTextField();
        txt_namaformguru = new javax.swing.JTextField();
        txt_emailformguru = new javax.swing.JTextField();
        rb5 = new javax.swing.JRadioButton();
        rb6 = new javax.swing.JRadioButton();
        txt_tlpformguru = new javax.swing.JTextField();
        txt_alamatformguru = new javax.swing.JTextField();
        btn_simpanformguru = new javax.swing.JButton();
        btn_kembaliformguru = new javax.swing.JButton();
        bgtambahguru = new javax.swing.JLabel();
        datawalikelas = new javax.swing.JLayeredPane();
        panelwalikelas = new javax.swing.JPanel();
        txt_searchwalikelas = new javax.swing.JTextField();
        btn_searchwalikelas = new javax.swing.JButton();
        tabwalikelas = new javax.swing.JScrollPane();
        tabel_walikelas = new javax.swing.JTable();
        btn_lihatwalikelas = new javax.swing.JButton();
        brn_hapuswalikelas = new javax.swing.JButton();
        btn_refreshdatawalikelas = new javax.swing.JButton();
        btn_tambahwalikelas = new javax.swing.JButton();
        bgwalikelas = new javax.swing.JLabel();
        profilewalikelas = new javax.swing.JLayeredPane();
        panelprofilewalikelas = new javax.swing.JPanel();
        lb_gambar = new javax.swing.JLabel();
        lb_idprofilewalikelas = new javax.swing.JLabel();
        lb_nipprofilewalikelas = new javax.swing.JLabel();
        lb_nkprofilewalikelas = new javax.swing.JLabel();
        lb_namaprofilewalikelas = new javax.swing.JLabel();
        lb_emailprofilewalikelas = new javax.swing.JLabel();
        lb_jkprofilewalikelas = new javax.swing.JLabel();
        lb_notelpprofilewalikelas = new javax.swing.JLabel();
        lb_alamatprofilewalikelas = new javax.swing.JLabel();
        lb_titik1 = new javax.swing.JLabel();
        lb_titik2 = new javax.swing.JLabel();
        lb_titik3 = new javax.swing.JLabel();
        lb_titik4 = new javax.swing.JLabel();
        lb_titik5 = new javax.swing.JLabel();
        lb_titik6 = new javax.swing.JLabel();
        lb_titik7 = new javax.swing.JLabel();
        lb_titik27 = new javax.swing.JLabel();
        txt_idprofilewalikelas = new javax.swing.JTextField();
        txt_nipprofilewalikelas = new javax.swing.JTextField();
        txt_nkprofilewalikelas = new javax.swing.JTextField();
        txt_namaprofilewalikelas = new javax.swing.JTextField();
        txt_emailprofilewalikelas = new javax.swing.JTextField();
        txt_jkprofilewalikelas = new javax.swing.JTextField();
        txt_notelpprofilewalikelas = new javax.swing.JTextField();
        txt_alamatprofilewalikelas = new javax.swing.JTextField();
        btn_simpanprofilewalikelas = new javax.swing.JButton();
        btn_editprofilewalikelas = new javax.swing.JButton();
        btn_lihatdatasiswa = new javax.swing.JButton();
        btn_kembaliprofilewalikelas = new javax.swing.JButton();
        bgprofilewalikelas = new javax.swing.JLabel();
        form_walikelas = new javax.swing.JLayeredPane();
        panelformwalikelas = new javax.swing.JPanel();
        lb_nipformwalikelas1 = new javax.swing.JLabel();
        lb_nipformwalikelas = new javax.swing.JLabel();
        lb_namaformwalikelas = new javax.swing.JLabel();
        lb_emailformwalikelas = new javax.swing.JLabel();
        lb_jkformwalikelas = new javax.swing.JLabel();
        lb_notelpformwalikelas = new javax.swing.JLabel();
        lb_alamatformwalikelas = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txt_idformwalikelas = new javax.swing.JTextField();
        txt_nipformwalikelas = new javax.swing.JTextField();
        txt_namaformwalikelas = new javax.swing.JTextField();
        txt_emailformwalikelas = new javax.swing.JTextField();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        txt_tlpformwalikelas = new javax.swing.JTextField();
        txt_alamatformwalikelas = new javax.swing.JTextField();
        btn_scantambahwalas = new javax.swing.JButton();
        btn_simpanformwalikelas = new javax.swing.JButton();
        btn_kembaliformwalikelas = new javax.swing.JButton();
        bgtambahwalikelas = new javax.swing.JLabel();
        datakelas = new javax.swing.JLayeredPane();
        panelkelas = new javax.swing.JPanel();
        btn_addkelas = new javax.swing.JButton();
        txt_searchkelas = new javax.swing.JTextField();
        btn_searchkelas = new javax.swing.JButton();
        btn_refreshkelas = new javax.swing.JButton();
        tabkelas = new javax.swing.JScrollPane();
        tabel_kelas = new javax.swing.JTable();
        btn_hapuskelas = new javax.swing.JButton();
        btn_lihatkelas = new javax.swing.JButton();
        saveadmkelas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        profilekelas = new javax.swing.JLayeredPane();
        panelprofilekelas = new javax.swing.JPanel();
        lb_gambar1 = new javax.swing.JLabel();
        lb_nkprofilekelas = new javax.swing.JLabel();
        lb_tingkatprofilekelas = new javax.swing.JLabel();
        lb_jurusanprofilekelas = new javax.swing.JLabel();
        lb_namaprofilekelas = new javax.swing.JLabel();
        lb_jmlprofilekelas = new javax.swing.JLabel();
        lb_nipprofilekelas = new javax.swing.JLabel();
        lb_smtprofilekelas = new javax.swing.JLabel();
        lb_taprofilekelas = new javax.swing.JLabel();
        lb_titik8 = new javax.swing.JLabel();
        lb_titik9 = new javax.swing.JLabel();
        lb_titik10 = new javax.swing.JLabel();
        lb_titik11 = new javax.swing.JLabel();
        lb_titik12 = new javax.swing.JLabel();
        lb_titik13 = new javax.swing.JLabel();
        lb_titik28 = new javax.swing.JLabel();
        lb_titik29 = new javax.swing.JLabel();
        txt_taprofilekelas = new javax.swing.JTextField();
        txt_smtprofilekelas = new javax.swing.JTextField();
        cb_smtprofilekelas = new javax.swing.JComboBox<>();
        txt_nkprofilekelas = new javax.swing.JTextField();
        txt_angkatanprofilekelas = new javax.swing.JTextField();
        txt_jurusanprofilekelas = new javax.swing.JTextField();
        txt_namaprofilekelas = new javax.swing.JTextField();
        txt_jmlprofilekelas = new javax.swing.JTextField();
        txt_walasprofilekelas = new javax.swing.JTextField();
        btn_simpanprofilekelas = new javax.swing.JButton();
        btn_editprofilekelas = new javax.swing.JButton();
        btn_lihatanggotakelas = new javax.swing.JButton();
        btn_kembaliprofilekelas = new javax.swing.JButton();
        cb_walasprofilekelas = new javax.swing.JComboBox<>();
        bgprofilekelas = new javax.swing.JLabel();
        form_kelas = new javax.swing.JLayeredPane();
        panelformkelas = new javax.swing.JPanel();
        lb_nkformkelas = new javax.swing.JLabel();
        lb_nipformkelas = new javax.swing.JLabel();
        lb_tingkatanformkelas = new javax.swing.JLabel();
        lb_jurusanformkelas = new javax.swing.JLabel();
        lb_namaformkelas = new javax.swing.JLabel();
        lb_smtformkelas = new javax.swing.JLabel();
        lb_taformkelas = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        cb_smtformkelas = new javax.swing.JComboBox<>();
        txt_nkformkelas = new javax.swing.JTextField();
        txt_tingkatanformkelas = new javax.swing.JTextField();
        txt_jurusanformkelas = new javax.swing.JTextField();
        txt_namaformkelas = new javax.swing.JTextField();
        txt_taformkelas = new javax.swing.JTextField();
        btn_simpanformkelas = new javax.swing.JButton();
        cb_walasformkelas = new javax.swing.JComboBox<>();
        btn_kembaliformkelas = new javax.swing.JButton();
        bgformkelas = new javax.swing.JLabel();
        dataadmin = new javax.swing.JLayeredPane();
        paneladmin = new javax.swing.JPanel();
        txt_searchadmin = new javax.swing.JTextField();
        btn_lihatadmin = new javax.swing.JButton();
        btn_registrasi = new javax.swing.JButton();
        btn_hapusadmin = new javax.swing.JButton();
        tabadmin = new javax.swing.JScrollPane();
        tabel_admin = new javax.swing.JTable();
        bgadmin = new javax.swing.JLabel();
        saveadm = new javax.swing.JLabel();
        profileadmin = new javax.swing.JLayeredPane();
        panelprofileadmin = new javax.swing.JPanel();
        lb_gambar3 = new javax.swing.JLabel();
        lb_idadminprofileadmin = new javax.swing.JLabel();
        lb_namaprofileadmin = new javax.swing.JLabel();
        lb_usenameprofileadmin = new javax.swing.JLabel();
        lb_passprofileadmin = new javax.swing.JLabel();
        lb_statusprofileadmin = new javax.swing.JLabel();
        lb_nipprofileadmin = new javax.swing.JLabel();
        lb_titik14 = new javax.swing.JLabel();
        lb_titik22 = new javax.swing.JLabel();
        lb_titik23 = new javax.swing.JLabel();
        lb_titik24 = new javax.swing.JLabel();
        lb_titik25 = new javax.swing.JLabel();
        lb_titik26 = new javax.swing.JLabel();
        txt_idadminprofileadmin = new javax.swing.JTextField();
        txt_namaprofileadmin = new javax.swing.JTextField();
        txt_usernameprofileadmin = new javax.swing.JTextField();
        txt_passprofileadmin = new javax.swing.JPasswordField();
        txt_pass2profileadmin = new javax.swing.JTextField();
        txt_statusprofileadmin = new javax.swing.JTextField();
        txt_nipprofileadmin = new javax.swing.JTextField();
        btn_checkpassprofileadmin = new javax.swing.JToggleButton();
        btn_simpanprofileadmin = new javax.swing.JButton();
        btn_editprofileadmin = new javax.swing.JButton();
        btn_kembaliprofileadmin = new javax.swing.JButton();
        cb_statusprofileadmin = new javax.swing.JComboBox<>();
        bgprofileadmin = new javax.swing.JLabel();
        dataabsensi = new javax.swing.JLayeredPane();
        panelabsensi = new javax.swing.JPanel();
        txt_searchdataabsen = new javax.swing.JTextField();
        txt_searchtgldataabsen = new javax.swing.JTextField();
        txt_searchtgl2dataabsen = new javax.swing.JTextField();
        btn_searchdataabsen = new javax.swing.JButton();
        nisdataabsensi = new javax.swing.JLabel();
        tgldataabsensi = new javax.swing.JLabel();
        statusdatabsensi = new javax.swing.JLabel();
        sampaidatabsensi = new javax.swing.JLabel();
        btn_updateabsen = new javax.swing.JButton();
        btn_refreshdataabsen = new javax.swing.JButton();
        tababsen = new javax.swing.JScrollPane();
        tabel_absen = new javax.swing.JTable();
        cb_statusdataabsen = new javax.swing.JComboBox<>();
        lb_idabsen = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bgabsensi = new javax.swing.JLabel();
        datalapabsensi = new javax.swing.JLayeredPane();
        panellapabsensi = new javax.swing.JPanel();
        txt_searchbynis = new javax.swing.JTextField();
        txt_tglakhir = new javax.swing.JTextField();
        tab_lapabsen = new javax.swing.JScrollPane();
        tabel_lapabsen = new javax.swing.JTable();
        bglapabsensi = new javax.swing.JLabel();
        datalapsiswamasalah = new javax.swing.JLayeredPane();
        panelsiswabermasalah = new javax.swing.JPanel();
        txt_siswabermasalah = new javax.swing.JTextField();
        cb_siswabermasalah = new javax.swing.JComboBox<>();
        btn_lihatprofilesiswabermasalah = new javax.swing.JButton();
        btn_updatestatussiswabermasalah = new javax.swing.JButton();
        tab_siswabermasalah = new javax.swing.JScrollPane();
        tabel_siswabermasalah = new javax.swing.JTable();
        bglapabsensi1 = new javax.swing.JLabel();
        dataanggotakelas = new javax.swing.JLayeredPane();
        panelanggotakelas = new javax.swing.JPanel();
        panel_chartanggotakelas = new javax.swing.JPanel();
        tab_anggotakelas = new javax.swing.JScrollPane();
        tabel_anggotakelas = new javax.swing.JTable();
        lb_nkak = new javax.swing.JLabel();
        lb_iwak = new javax.swing.JLabel();
        lb_nkanggotakelas = new javax.swing.JLabel();
        lb_nisanggotakelas = new javax.swing.JLabel();
        txt_searchnisanggotakelas = new javax.swing.JTextField();
        lb_idwalasanggotakelas = new javax.swing.JLabel();
        btn_refreshanggotakelas = new javax.swing.JButton();
        btn_searchanggotakelas = new javax.swing.JButton();
        btn_cetakanggotakelas = new javax.swing.JButton();
        btn_kembalianggotakelas = new javax.swing.JButton();
        bganggotakelas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelUtama.setOpaque(false);
        PanelUtama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelbutton.setBackground(new java.awt.Color(37, 112, 183));
        panelbutton.setOpaque(false);
        panelbutton.setLayout(null);

        btn_dashboard.setBackground(new java.awt.Color(37, 112, 183));
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
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
        btn_datasiswa.setBounds(0, 55, 260, 51);

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

        lb_guru.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lb_guru.setForeground(new java.awt.Color(255, 255, 255));
        lb_guru.setText("Data Guru");
        btn_dataguru.add(lb_guru, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        icon_guru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconguru.png"))); // NOI18N
        btn_dataguru.add(icon_guru, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_dataguru);
        btn_dataguru.setBounds(0, 110, 260, 51);

        btn_datawalikelas.setBackground(new java.awt.Color(37, 112, 183));
        btn_datawalikelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_datawalikelasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_datawalikelasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_datawalikelasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_datawalikelasMousePressed(evt);
            }
        });
        btn_datawalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_walikelas.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        lb_walikelas.setForeground(new java.awt.Color(255, 255, 255));
        lb_walikelas.setText("Data Walikelas");
        btn_datawalikelas.add(lb_walikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        icon_walikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconguru.png"))); // NOI18N
        btn_datawalikelas.add(icon_walikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        panelbutton.add(btn_datawalikelas);
        btn_datawalikelas.setBounds(0, 165, 260, 51);

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
        btn_datakelas.setBounds(0, 220, 260, 51);

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
        btn_dataadmin.setBounds(0, 275, 260, 51);

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
        btn_dataabsensi.setBounds(0, 330, 260, 52);

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
        lblapabsen.setText("Laporan Absen");
        btn_lapabsen.add(lblapabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, -1, -1));

        iconlapabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconlapabsensi.png"))); // NOI18N
        btn_lapabsen.add(iconlapabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, -1, -1));

        panelbutton.add(btn_lapabsen);
        btn_lapabsen.setBounds(0, 385, 260, 53);

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
        btn_logout.setBounds(0, 442, 260, 54);

        PanelUtama.add(panelbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 260, 658));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(39, 113, 184));
        username.setText("INI USERNAME");
        PanelUtama.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 30, 190, 40));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/listmenu3.png"))); // NOI18N
        PanelUtama.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        LayerPane.setEnabled(false);
        LayerPane.setFocusable(false);
        LayerPane.setOpaque(true);
        LayerPane.setRequestFocusEnabled(false);
        LayerPane.setVerifyInputWhenFocusTarget(false);
        LayerPane.setLayout(new java.awt.CardLayout());

        form_registrasi.setOpaque(true);
        form_registrasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformregistrasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_idregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_idregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_idregadmin.setText("ID Admin");
        panelformregistrasi.add(lb_idregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        lb_nipregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nipregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipregadmin.setText("NIP");
        panelformregistrasi.add(lb_nipregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        lb_namaregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_namaregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaregadmin.setText("Nama");
        panelformregistrasi.add(lb_namaregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        lb_usernameregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_usernameregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_usernameregadmin.setText("Username");
        panelformregistrasi.add(lb_usernameregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        lb_paswordregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_paswordregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_paswordregadmin.setText("Password");
        panelformregistrasi.add(lb_paswordregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        lb_statusregadmin.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_statusregadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_statusregadmin.setText("Status");
        panelformregistrasi.add(lb_statusregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jLabel66.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 51, 204));
        jLabel66.setText(":");
        panelformregistrasi.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 195, -1, -1));

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

        txt_idregadmin.setEnabled(false);
        panelformregistrasi.add(txt_idregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 840, 30));
        panelformregistrasi.add(txt_nipregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 760, 30));
        panelformregistrasi.add(txt_namaregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));
        panelformregistrasi.add(txt_usernameregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 840, 30));

        txt_passwordregadmin.setText("jPasswordField1");
        panelformregistrasi.add(txt_passwordregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 840, 30));

        btn_daftarregadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_daftarregadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_daftarregadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_daftarregadmin.setText("Daftar");
        btn_daftarregadmin.setIconTextGap(18);
        btn_daftarregadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarregadminActionPerformed(evt);
            }
        });
        panelformregistrasi.add(btn_daftarregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 580, 140, 35));

        txt_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guru BK", "Bagian IT", "Kepala Sekolah" }));
        txt_level.setFocusable(false);
        txt_level.setLightWeightPopupEnabled(false);
        txt_level.setRequestFocusEnabled(false);
        txt_level.setVerifyInputWhenFocusTarget(false);
        panelformregistrasi.add(txt_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 840, 40));

        btn_kembaliregadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliregadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliregadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliregadmin.setIconTextGap(18);
        btn_kembaliregadmin.setMargin(new java.awt.Insets(1, 14, 1, 14));
        btn_kembaliregadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliregadminActionPerformed(evt);
            }
        });
        panelformregistrasi.add(btn_kembaliregadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 55, 35));

        btn_scantambahadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_scantambahadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_scantambahadmin.setText("Check");
        btn_scantambahadmin.setIconTextGap(18);
        btn_scantambahadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scantambahadminActionPerformed(evt);
            }
        });
        panelformregistrasi.add(btn_scantambahadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 70, 30));

        bgregistrasiadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelregistrasiadmin.png"))); // NOI18N
        panelformregistrasi.add(bgregistrasiadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_registrasi.add(panelformregistrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_registrasi, "card4");

        datasiswa.setEnabled(false);
        datasiswa.setMinimumSize(new java.awt.Dimension(1366, 768));
        datasiswa.setOpaque(true);
        datasiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsiswa.setLayout(null);
        panelsiswa.add(txt_searchsiswa);
        txt_searchsiswa.setBounds(190, 110, 780, 30);

        btn_searchsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchsiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_searchsiswa);
        btn_searchsiswa.setBounds(980, 110, 30, 30);

        btn_lihatsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatsiswa.setText("Lihat Data");
        btn_lihatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatsiswaActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_lihatsiswa);
        btn_lihatsiswa.setBounds(770, 570, 120, 40);

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
        btn_hapussiswa.setBounds(930, 570, 120, 40);

        tabel_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "NIS", "Jenis Kelamin", "Nama"
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
        btn_refreshdatasiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
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

        txt_rfidsiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_rfidsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_rfidsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_rfidsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 470, 30));

        txt_nissiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nissiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nissiswa.setEnabled(false);
        panelprofilesiswa.add(txt_nissiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 550, 30));

        txt_nksiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nksiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nksiswa.setEnabled(false);
        panelprofilesiswa.add(txt_nksiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 181, 550, 30));

        txt_namasiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_namasiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_namasiswa.setEnabled(false);
        panelprofilesiswa.add(txt_namasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 222, 550, 30));

        txt_alamatsiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_alamatsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_alamatsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_alamatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 265, 550, 60));

        txt_gendersiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_gendersiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_gendersiswa.setEnabled(false);
        panelprofilesiswa.add(txt_gendersiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 335, 550, 30));

        txt_notelpsiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_notelpsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_notelpsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_notelpsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 376, 550, 30));

        txt_emailsiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_emailsiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_emailsiswa.setEnabled(false);
        panelprofilesiswa.add(txt_emailsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 417, 550, 30));

        txt_walassiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_walassiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_walassiswa.setEnabled(false);
        panelprofilesiswa.add(txt_walassiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 457, 550, 30));

        txt_ortusiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_ortusiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_ortusiswa.setEnabled(false);
        panelprofilesiswa.add(txt_ortusiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 497, 550, 30));

        txt_noortusiswa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_noortusiswa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_noortusiswa.setEnabled(false);
        panelprofilesiswa.add(txt_noortusiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 538, 550, 30));

        cb_gendersiswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        cb_gendersiswa.setFocusable(false);
        cb_gendersiswa.setLightWeightPopupEnabled(false);
        cb_gendersiswa.setRequestFocusEnabled(false);
        panelprofilesiswa.add(cb_gendersiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 335, 550, 30));

        btn_scanprofilesiswa.setText("Scan");
        btn_scanprofilesiswa.setEnabled(false);
        btn_scanprofilesiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scanprofilesiswaActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_scanprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, 70, 30));

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

        btn_riwayatabsen.setBackground(new java.awt.Color(255, 255, 255));
        btn_riwayatabsen.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_riwayatabsen.setText("Riwayat Absen");
        btn_riwayatabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_riwayatabsenActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_riwayatabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 590, 120, 40));

        btn_kembaliprofilesiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofilesiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliprofilesiswa.setIconTextGap(18);
        btn_kembaliprofilesiswa.setMargin(new java.awt.Insets(1, 10, 1, 10));
        btn_kembaliprofilesiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofilesiswaActionPerformed(evt);
            }
        });
        panelprofilesiswa.add(btn_kembaliprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 590, 55, 35));

        bgprofilesiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofilesiswa2.png"))); // NOI18N
        panelprofilesiswa.add(bgprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profilesiswa.add(panelprofilesiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profilesiswa, "card4");

        form_siswa.setOpaque(true);
        form_siswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_rfidformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_rfidformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_rfidformsiswa.setText("RFID");
        panelformsiswa.add(lb_rfidformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 95, -1, -1));

        lb_nkformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nkformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_nkformsiswa.setText("ID Kelas");
        panelformsiswa.add(lb_nkformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 135, -1, -1));

        lb_nisformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nisformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_nisformsiswa.setText("NIS");
        panelformsiswa.add(lb_nisformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 175, -1, -1));

        lb_namaformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_namaformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaformsiswa.setText("Nama");
        panelformsiswa.add(lb_namaformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 215, -1, -1));

        lb_alamatformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_alamatformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamatformsiswa.setText("Alamat");
        panelformsiswa.add(lb_alamatformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        lb_jkformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_jkformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_jkformsiswa.setText("Jenis Kelamin");
        panelformsiswa.add(lb_jkformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        lb_emailformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_emailformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_emailformsiswa.setText("E-Mail");
        panelformsiswa.add(lb_emailformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 365, -1, 20));

        lb_idwalasformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_idwalasformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_idwalasformsiswa.setText("ID Walikelas");
        panelformsiswa.add(lb_idwalasformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 405, -1, -1));

        lb_telpformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_telpformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_telpformsiswa.setText("No.Tlpn");
        panelformsiswa.add(lb_telpformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, -1, -1));

        lb_nortuformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nortuformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_nortuformsiswa.setText("Nama Orang tua");
        panelformsiswa.add(lb_nortuformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, -1, -1));

        lb_noortuformsiswa.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_noortuformsiswa.setForeground(new java.awt.Color(0, 51, 204));
        lb_noortuformsiswa.setText("No. Orang tua");
        panelformsiswa.add(lb_noortuformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, -1, -1));

        jLabel23.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 204));
        jLabel23.setText(":");
        panelformsiswa.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 95, -1, -1));

        jLabel25.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 204));
        jLabel25.setText(":");
        panelformsiswa.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 135, -1, -1));

        jLabel12.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText(":");
        panelformsiswa.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 175, -1, -1));

        jLabel15.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText(":");
        panelformsiswa.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 215, -1, -1));

        jLabel17.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 204));
        jLabel17.setText(":");
        panelformsiswa.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jLabel18.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setText(":");
        panelformsiswa.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, -1, -1));

        jLabel20.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText(":");
        panelformsiswa.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 365, -1, 20));

        jLabel26.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 204));
        jLabel26.setText(":");
        panelformsiswa.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 405, -1, 20));

        jLabel21.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 204));
        jLabel21.setText(":");
        panelformsiswa.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, -1, -1));

        jLabel24.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText(":");
        panelformsiswa.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, -1));

        jLabel22.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 204));
        jLabel22.setText(":");
        panelformsiswa.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, -1, -1));

        txt_rfidformsiswa.setEnabled(false);
        panelformsiswa.add(txt_rfidformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 760, 30));

        txt_nkformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_nkformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 760, 30));

        txt_nisformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_nisformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 840, 30));

        txt_namaformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_namaformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 840, 30));

        txt_alamatformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_alamatformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 840, 70));

        rb1.setBackground(new java.awt.Color(255, 255, 255));
        rb1.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb1.setText("Perempuan");
        panelformsiswa.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        rb2.setBackground(new java.awt.Color(255, 255, 255));
        rb2.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb2.setText("Laki-Laki");
        panelformsiswa.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, -1));

        txt_emailformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_emailformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 840, 30));

        txt_idwalasformsiswa.setEnabled(false);
        panelformsiswa.add(txt_idwalasformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 840, 30));

        txt_telpformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_telpformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 840, 30));

        txt_nortuformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_nortuformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 840, 30));

        txt_noortuformsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformsiswa.add(txt_noortuformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, 840, 30));

        btn_scantambahsiswa1.setBackground(new java.awt.Color(255, 255, 255));
        btn_scantambahsiswa1.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_scantambahsiswa1.setText("Check");
        btn_scantambahsiswa1.setIconTextGap(18);
        btn_scantambahsiswa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scantambahsiswa1ActionPerformed(evt);
            }
        });
        panelformsiswa.add(btn_scantambahsiswa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 70, 30));

        btn_simpantambahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpantambahsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 14)); // NOI18N
        btn_simpantambahsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpantambahsiswa.setText("Simpan");
        btn_simpantambahsiswa.setIconTextGap(18);
        btn_simpantambahsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpantambahsiswaActionPerformed(evt);
            }
        });
        panelformsiswa.add(btn_simpantambahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 140, 40));

        btn_scantambahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_scantambahsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_scantambahsiswa.setText("Regis");
        btn_scantambahsiswa.setIconTextGap(18);
        btn_scantambahsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scantambahsiswaActionPerformed(evt);
            }
        });
        panelformsiswa.add(btn_scantambahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, 70, 30));

        btn_kembalitambahisiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembalitambahisiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembalitambahisiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembalitambahisiswa.setIconTextGap(18);
        btn_kembalitambahisiswa.setMargin(new java.awt.Insets(1, 5, 1, 5));
        btn_kembalitambahisiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembalitambahisiswaActionPerformed(evt);
            }
        });
        panelformsiswa.add(btn_kembalitambahisiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 55, 35));

        bgtambahsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahsiswa.png"))); // NOI18N
        panelformsiswa.add(bgtambahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_siswa.add(panelformsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_siswa, "card4");

        riwayat_siswa.setEnabled(false);
        riwayat_siswa.setMinimumSize(new java.awt.Dimension(1366, 768));
        riwayat_siswa.setOpaque(true);
        riwayat_siswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelriwayatsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_chartrs.setBackground(new java.awt.Color(255, 255, 255));
        panel_chartrs.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Presentasi Kehadiran", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
        panel_chartrs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_chartrs.setDoubleBuffered(false);
        panel_chartrs.setFocusable(false);
        panel_chartrs.setInheritsPopupMenu(true);
        panel_chartrs.setOpaque(false);
        panel_chartrs.setRequestFocusEnabled(false);
        panel_chartrs.setVerifyInputWhenFocusTarget(false);
        panel_chartrs.setLayout(new javax.swing.OverlayLayout(panel_chartrs));
        panelriwayatsiswa.add(panel_chartrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 430, 460));
        panelriwayatsiswa.add(txt_tanggalriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, 30));

        txt_tanggal2riwayatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tanggal2riwayatsiswaActionPerformed(evt);
            }
        });
        panelriwayatsiswa.add(txt_tanggal2riwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 170, 30));

        lb_sampairiwayatsiswa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_sampairiwayatsiswa.setText("-");
        panelriwayatsiswa.add(lb_sampairiwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel2.setText("Tanggal ");
        panelriwayatsiswa.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 195, 70, 20));

        lb_nisrs.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nisrs.setText("NIPD ");
        panelriwayatsiswa.add(lb_nisrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 50, 20));

        lb_namars.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_namars.setText("NK");
        panelriwayatsiswa.add(lb_namars, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 125, 50, 20));

        lb_nkrs.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nkrs.setText("Nama");
        panelriwayatsiswa.add(lb_nkrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        lb_nisriwayatsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelriwayatsiswa.add(lb_nisriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 250, 20));

        lb_namariwayatsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelriwayatsiswa.add(lb_namariwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 125, 250, 20));

        lb_nkriwayatsiswa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelriwayatsiswa.add(lb_nkriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 148, 250, 20));

        btn_searchriwayatsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchriwayatsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchriwayatsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchriwayatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchriwayatsiswaActionPerformed(evt);
            }
        });
        panelriwayatsiswa.add(btn_searchriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 30, 30));

        btn_cetakriwayatsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_cetakriwayatsiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_cetakriwayatsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconcetakdata.png"))); // NOI18N
        btn_cetakriwayatsiswa.setText("Cetak");
        btn_cetakriwayatsiswa.setIconTextGap(18);
        btn_cetakriwayatsiswa.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_cetakriwayatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakriwayatsiswaActionPerformed(evt);
            }
        });
        panelriwayatsiswa.add(btn_cetakriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 570, 120, 40));

        tabel_riwayatsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Jam", "Status"
            }
        ));
        tabel_riwayatsiswa.setRowHeight(20);
        tabel_riwayatsiswa.setRowMargin(2);
        tabel_riwayatsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_riwayatsiswaMouseClicked(evt);
            }
        });
        tabriwayatsiswa.setViewportView(tabel_riwayatsiswa);

        panelriwayatsiswa.add(tabriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 570, 320));

        btn_refreshriwayatsiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshriwayatsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
        btn_refreshriwayatsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshriwayatsiswaActionPerformed(evt);
            }
        });
        panelriwayatsiswa.add(btn_refreshriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 40, 30));

        btn_kembaliriwayatabsen.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliriwayatabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliriwayatabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliriwayatabsenActionPerformed(evt);
            }
        });
        panelriwayatsiswa.add(btn_kembaliriwayatabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 573, 55, -1));

        btn_lihathistory.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihathistory.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihathistory.setText("Lihat History");
        panelriwayatsiswa.add(btn_lihathistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(813, 570, 120, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText(":");
        panelriwayatsiswa.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText(":");
        panelriwayatsiswa.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText(":");
        panelriwayatsiswa.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 193, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(":");
        panelriwayatsiswa.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText(":");
        panelriwayatsiswa.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 125, -1, -1));

        bgriwayatsiswa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bgriwayatsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelriwayatabsen.png"))); // NOI18N
        panelriwayatsiswa.add(bgriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        riwayat_siswa.add(panelriwayatsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(riwayat_siswa, "card2");

        dataguru.setOpaque(true);
        dataguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelguru.add(txt_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 770, 30));

        btn_searchguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 110, 30, 30));

        tabel_guru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "NIP", "Nama", "Jabatan", "Jenis Kelamin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_guru.setRowHeight(20);
        tabel_guru.setRowMargin(2);
        tabel_guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_guruMouseClicked(evt);
            }
        });
        tabguru.setViewportView(tabel_guru);
        if (tabel_guru.getColumnModel().getColumnCount() > 0) {
            tabel_guru.getColumnModel().getColumn(0).setResizable(false);
            tabel_guru.getColumnModel().getColumn(1).setResizable(false);
            tabel_guru.getColumnModel().getColumn(2).setResizable(false);
            tabel_guru.getColumnModel().getColumn(3).setResizable(false);
            tabel_guru.getColumnModel().getColumn(4).setResizable(false);
        }

        panelguru.add(tabguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 1020, 370));

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
        btn_refreshdataguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshdataguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_refreshdataguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 40, 30));

        btn_tambahguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambahguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_tambahguru.setText("Add Guru");
        btn_tambahguru.setIconTextGap(18);
        btn_tambahguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_tambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 150, 30));

        bgguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelguru.png"))); // NOI18N
        panelguru.add(bgguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataguru.add(panelguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataguru, "card3");

        profileguru.setOpaque(true);
        profileguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofileguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_nipprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nipprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipprofileguru.setText("NIP");
        panelprofileguru.add(lb_nipprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 50, 30));

        lb_jabatanprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jabatanprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_jabatanprofileguru.setText("Jabatan");
        panelprofileguru.add(lb_jabatanprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 90, 30));

        lb_namaprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_namaprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaprofileguru.setText("Nama");
        panelprofileguru.add(lb_namaprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 90, 30));

        lb_emailprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_emailprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_emailprofileguru.setText("E-mail");
        panelprofileguru.add(lb_emailprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 60, 30));

        lb_jkprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jkprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_jkprofileguru.setText("Jenis Kelamin");
        panelprofileguru.add(lb_jkprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 130, 30));

        lb_notelpprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_notelpprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_notelpprofileguru.setText("No.Telepon");
        panelprofileguru.add(lb_notelpprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 120, 30));

        lb_alamatprofileguru.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_alamatprofileguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamatprofileguru.setText("Alamat");
        panelprofileguru.add(lb_alamatprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, 130, 30));

        lb_titik15.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik15.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik15.setText(":");
        panelprofileguru.add(lb_titik15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 20, 30));

        lb_titik16.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik16.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik16.setText(":");
        panelprofileguru.add(lb_titik16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 20, 30));

        lb_titik17.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik17.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik17.setText(":");
        panelprofileguru.add(lb_titik17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 20, 30));

        lb_titik18.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik18.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik18.setText(":");
        panelprofileguru.add(lb_titik18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 20, 30));

        lb_titik19.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik19.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik19.setText(":");
        panelprofileguru.add(lb_titik19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 20, 30));

        lb_titik20.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik20.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik20.setText(":");
        panelprofileguru.add(lb_titik20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 20, 30));

        lb_titik21.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik21.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik21.setText(":");
        panelprofileguru.add(lb_titik21, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 20, 30));

        txt_nipprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nipprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nipprofileguru.setEnabled(false);
        panelprofileguru.add(txt_nipprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 530, 35));

        txt_jabatanprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_jabatanprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_jabatanprofileguru.setEnabled(false);
        panelprofileguru.add(txt_jabatanprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 530, 35));

        txt_namaprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_namaprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_namaprofileguru.setEnabled(false);
        panelprofileguru.add(txt_namaprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 530, 35));

        txt_emailprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_emailprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_emailprofileguru.setEnabled(false);
        panelprofileguru.add(txt_emailprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 530, 35));

        txt_jkprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_jkprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_jkprofileguru.setEnabled(false);
        panelprofileguru.add(txt_jkprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 530, 35));

        txt_notelpprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_notelpprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_notelpprofileguru.setEnabled(false);
        panelprofileguru.add(txt_notelpprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 530, 35));

        txt_alamatprofileguru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_alamatprofileguru.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_alamatprofileguru.setEnabled(false);
        panelprofileguru.add(txt_alamatprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 530, 80));

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
        btn_kembaliprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliprofileguru.setIconTextGap(18);
        btn_kembaliprofileguru.setMargin(new java.awt.Insets(1, 10, 1, 10));
        btn_kembaliprofileguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofileguruActionPerformed(evt);
            }
        });
        panelprofileguru.add(btn_kembaliprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 560, 55, 35));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        lb_gambar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_profileguru.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(lb_gambar2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lb_gambar2)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panelprofileguru.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 290, 260));

        bgprofileguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofileguru.png"))); // NOI18N
        panelprofileguru.add(bgprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profileguru.add(panelprofileguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profileguru, "card4");

        form_guru.setOpaque(true);
        form_guru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_nipformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nipformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipformguru.setText("NIP");
        panelformguru.add(lb_nipformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 115, -1, -1));

        lb_jabatanformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_jabatanformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_jabatanformguru.setText("Jabatan");
        panelformguru.add(lb_jabatanformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 155, -1, -1));

        lb_namaformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_namaformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaformguru.setText("Nama");
        panelformguru.add(lb_namaformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 195, -1, -1));

        lb_emailformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_emailformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_emailformguru.setText("E-mail");
        panelformguru.add(lb_emailformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 235, -1, -1));

        lb_jkformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_jkformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_jkformguru.setText("Jenis Kelamin");
        panelformguru.add(lb_jkformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 275, -1, -1));

        lb_notelpformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_notelpformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_notelpformguru.setText("No.Telepon");
        panelformguru.add(lb_notelpformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 315, -1, -1));

        lb_alamatformguru.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_alamatformguru.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamatformguru.setText("Alamat");
        panelformguru.add(lb_alamatformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jLabel56.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 51, 204));
        jLabel56.setText(":");
        panelformguru.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 155, -1, -1));

        jLabel62.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 51, 204));
        jLabel62.setText(":");
        panelformguru.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 195, -1, -1));

        jLabel63.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 51, 204));
        jLabel63.setText(":");
        panelformguru.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 235, -1, -1));

        jLabel64.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 51, 204));
        jLabel64.setText(":");
        panelformguru.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        jLabel71.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 51, 204));
        jLabel71.setText(":");
        panelformguru.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 275, -1, -1));

        jLabel74.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 51, 204));
        jLabel74.setText(":");
        panelformguru.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 315, -1, -1));

        jLabel75.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 51, 204));
        jLabel75.setText(":");
        panelformguru.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, -1, -1));
        panelformguru.add(txt_nipformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 840, 30));
        panelformguru.add(txt_jabatanformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 840, 30));
        panelformguru.add(txt_namaformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 840, 30));
        panelformguru.add(txt_emailformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));

        rb5.setBackground(new java.awt.Color(255, 255, 255));
        rb5.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb5.setText("Perempuan");
        panelformguru.add(rb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 275, -1, -1));

        rb6.setBackground(new java.awt.Color(255, 255, 255));
        rb6.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb6.setText("Laki-Laki");
        panelformguru.add(rb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 275, -1, -1));
        panelformguru.add(txt_tlpformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 840, 30));
        panelformguru.add(txt_alamatformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 840, 70));

        btn_simpanformguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanformguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanformguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanformguru.setText("Simpan");
        btn_simpanformguru.setIconTextGap(18);
        btn_simpanformguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanformguruActionPerformed(evt);
            }
        });
        panelformguru.add(btn_simpanformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 140, 35));

        btn_kembaliformguru.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliformguru.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliformguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliformguru.setIconTextGap(18);
        btn_kembaliformguru.setMargin(new java.awt.Insets(1, 14, 1, 14));
        btn_kembaliformguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliformguruActionPerformed(evt);
            }
        });
        panelformguru.add(btn_kembaliformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 55, 35));

        bgtambahguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahguru.png"))); // NOI18N
        panelformguru.add(bgtambahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_guru.add(panelformguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_guru, "card4");

        datawalikelas.setOpaque(true);
        datawalikelas.setPreferredSize(new java.awt.Dimension(1107, 658));
        datawalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelwalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelwalikelas.add(txt_searchwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 770, 30));

        btn_searchwalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchwalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchwalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchwalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchwalikelasActionPerformed(evt);
            }
        });
        panelwalikelas.add(btn_searchwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 110, 30, 30));

        tabel_walikelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "ID Walikelas", "NIP", "Nama", "Jenis Kelamin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_walikelas.setRowHeight(20);
        tabel_walikelas.setRowMargin(2);
        tabel_walikelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_walikelasMouseClicked(evt);
            }
        });
        tabwalikelas.setViewportView(tabel_walikelas);
        if (tabel_walikelas.getColumnModel().getColumnCount() > 0) {
            tabel_walikelas.getColumnModel().getColumn(0).setResizable(false);
            tabel_walikelas.getColumnModel().getColumn(1).setResizable(false);
            tabel_walikelas.getColumnModel().getColumn(2).setResizable(false);
            tabel_walikelas.getColumnModel().getColumn(3).setResizable(false);
            tabel_walikelas.getColumnModel().getColumn(4).setResizable(false);
        }

        panelwalikelas.add(tabwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 1020, 370));

        btn_lihatwalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatwalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatwalikelas.setText("Lihat Data");
        btn_lihatwalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatwalikelasActionPerformed(evt);
            }
        });
        panelwalikelas.add(btn_lihatwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 140, 40));

        brn_hapuswalikelas.setBackground(new java.awt.Color(255, 255, 255));
        brn_hapuswalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        brn_hapuswalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/delete.png"))); // NOI18N
        brn_hapuswalikelas.setText("Hapus");
        brn_hapuswalikelas.setIconTextGap(18);
        brn_hapuswalikelas.setMargin(new java.awt.Insets(1, 1, 1, 10));
        brn_hapuswalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_hapuswalikelasActionPerformed(evt);
            }
        });
        panelwalikelas.add(brn_hapuswalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 570, 140, 40));

        btn_refreshdatawalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshdatawalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
        btn_refreshdatawalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshdatawalikelasActionPerformed(evt);
            }
        });
        panelwalikelas.add(btn_refreshdatawalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 40, 30));

        btn_tambahwalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambahwalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_tambahwalikelas.setText("Add Walikelas");
        btn_tambahwalikelas.setIconTextGap(18);
        btn_tambahwalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahwalikelasActionPerformed(evt);
            }
        });
        panelwalikelas.add(btn_tambahwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 150, 30));

        bgwalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelwalikelas.png"))); // NOI18N
        panelwalikelas.add(bgwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datawalikelas.add(panelwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datawalikelas, "card3");

        profilewalikelas.setOpaque(true);
        profilewalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofilewalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_gambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_profileguru.png"))); // NOI18N
        panelprofilewalikelas.add(lb_gambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 280, 270));

        lb_idprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_idprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_idprofilewalikelas.setText("ID Walikelas");
        panelprofilewalikelas.add(lb_idprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 127, 110, 30));

        lb_nipprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nipprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipprofilewalikelas.setText("NIP");
        panelprofilewalikelas.add(lb_nipprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 177, 50, 30));

        lb_nkprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nkprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nkprofilewalikelas.setText("NK");
        panelprofilewalikelas.add(lb_nkprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 222, 50, 30));

        lb_namaprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_namaprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaprofilewalikelas.setText("Nama");
        panelprofilewalikelas.add(lb_namaprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 90, 30));

        lb_emailprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_emailprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_emailprofilewalikelas.setText("E-mail");
        panelprofilewalikelas.add(lb_emailprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 60, 30));

        lb_jkprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jkprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_jkprofilewalikelas.setText("Jenis Kelamin");
        panelprofilewalikelas.add(lb_jkprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 130, 30));

        lb_notelpprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_notelpprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_notelpprofilewalikelas.setText("No.Telepon");
        panelprofilewalikelas.add(lb_notelpprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 120, 30));

        lb_alamatprofilewalikelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_alamatprofilewalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamatprofilewalikelas.setText("Alamat");
        panelprofilewalikelas.add(lb_alamatprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 130, 30));

        lb_titik1.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik1.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik1.setText(":");
        panelprofilewalikelas.add(lb_titik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 177, 20, 30));

        lb_titik2.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik2.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik2.setText(":");
        panelprofilewalikelas.add(lb_titik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 20, 30));

        lb_titik3.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik3.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik3.setText(":");
        panelprofilewalikelas.add(lb_titik3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 10, 30));

        lb_titik4.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik4.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik4.setText(":");
        panelprofilewalikelas.add(lb_titik4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 20, 30));

        lb_titik5.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik5.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik5.setText(":");
        panelprofilewalikelas.add(lb_titik5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 20, 30));

        lb_titik6.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik6.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik6.setText(":");
        panelprofilewalikelas.add(lb_titik6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 20, 30));

        lb_titik7.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik7.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik7.setText(":");
        panelprofilewalikelas.add(lb_titik7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 127, 20, 30));

        lb_titik27.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik27.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik27.setText(":");
        panelprofilewalikelas.add(lb_titik27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 222, 20, 30));

        txt_idprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_idprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_idprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_idprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 125, 530, 35));

        txt_nipprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nipprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nipprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_nipprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 175, 530, 35));

        txt_nkprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nkprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nkprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_nkprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 222, 530, 35));

        txt_namaprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_namaprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_namaprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_namaprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 530, 35));

        txt_emailprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_emailprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_emailprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_emailprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 530, 35));

        txt_jkprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_jkprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_jkprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_jkprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 530, 35));

        txt_notelpprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_notelpprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_notelpprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_notelpprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, 530, 35));

        txt_alamatprofilewalikelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_alamatprofilewalikelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_alamatprofilewalikelas.setEnabled(false);
        panelprofilewalikelas.add(txt_alamatprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 530, 80));

        btn_simpanprofilewalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanprofilewalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofilewalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofilewalikelas.setText("Simpan");
        btn_simpanprofilewalikelas.setIconTextGap(18);
        btn_simpanprofilewalikelas.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_simpanprofilewalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofilewalikelasActionPerformed(evt);
            }
        });
        panelprofilewalikelas.add(btn_simpanprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 570, 140, 40));

        btn_editprofilewalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_editprofilewalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editprofilewalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editprofilewalikelas.setText("Edit");
        btn_editprofilewalikelas.setIconTextGap(18);
        btn_editprofilewalikelas.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_editprofilewalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editprofilewalikelasActionPerformed(evt);
            }
        });
        panelprofilewalikelas.add(btn_editprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 570, 140, 40));

        btn_lihatdatasiswa.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatdatasiswa.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatdatasiswa.setText("Lihat Siswa");
        panelprofilewalikelas.add(btn_lihatdatasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 570, 140, 40));

        btn_kembaliprofilewalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofilewalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofilewalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliprofilewalikelas.setIconTextGap(18);
        btn_kembaliprofilewalikelas.setMargin(new java.awt.Insets(1, 10, 1, 10));
        btn_kembaliprofilewalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofilewalikelasActionPerformed(evt);
            }
        });
        panelprofilewalikelas.add(btn_kembaliprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 560, 55, 35));

        bgprofilewalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofilewalas.png"))); // NOI18N
        panelprofilewalikelas.add(bgprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profilewalikelas.add(panelprofilewalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profilewalikelas, "card4");

        form_walikelas.setOpaque(true);
        form_walikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformwalikelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_nipformwalikelas1.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nipformwalikelas1.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipformwalikelas1.setText("ID Walikelas");
        panelformwalikelas.add(lb_nipformwalikelas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 115, -1, -1));

        lb_nipformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_nipformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipformwalikelas.setText("NIP");
        panelformwalikelas.add(lb_nipformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 155, -1, -1));

        lb_namaformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_namaformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaformwalikelas.setText("Nama");
        panelformwalikelas.add(lb_namaformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        lb_emailformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_emailformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_emailformwalikelas.setText("E-mail");
        panelformwalikelas.add(lb_emailformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        lb_jkformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_jkformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_jkformwalikelas.setText("Jenis Kelamin");
        panelformwalikelas.add(lb_jkformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        lb_notelpformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_notelpformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_notelpformwalikelas.setText("No.Telepon");
        panelformwalikelas.add(lb_notelpformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        lb_alamatformwalikelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_alamatformwalikelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_alamatformwalikelas.setText("Alamat");
        panelformwalikelas.add(lb_alamatformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        jLabel57.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 51, 204));
        jLabel57.setText(":");
        panelformwalikelas.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jLabel58.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 51, 204));
        jLabel58.setText(":");
        panelformwalikelas.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jLabel59.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 51, 204));
        jLabel59.setText(":");
        panelformwalikelas.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, -1));

        jLabel60.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 51, 204));
        jLabel60.setText(":");
        panelformwalikelas.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        jLabel61.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 51, 204));
        jLabel61.setText(":");
        panelformwalikelas.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        jLabel65.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 51, 204));
        jLabel65.setText(":");
        panelformwalikelas.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 155, -1, -1));

        jLabel67.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 204));
        jLabel67.setText(":");
        panelformwalikelas.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, -1, -1));

        txt_idformwalikelas.setEnabled(false);
        panelformwalikelas.add(txt_idformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 840, 30));

        txt_nipformwalikelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformwalikelas.add(txt_nipformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 760, 30));

        txt_namaformwalikelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformwalikelas.add(txt_namaformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 840, 30));

        txt_emailformwalikelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformwalikelas.add(txt_emailformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));

        rb3.setBackground(new java.awt.Color(255, 255, 255));
        rb3.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb3.setText("Perempuan");
        panelformwalikelas.add(rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        rb4.setBackground(new java.awt.Color(255, 255, 255));
        rb4.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        rb4.setText("Laki-Laki");
        panelformwalikelas.add(rb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));
        panelformwalikelas.add(txt_tlpformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 840, 30));

        txt_alamatformwalikelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformwalikelas.add(txt_alamatformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 840, 70));

        btn_scantambahwalas.setBackground(new java.awt.Color(255, 255, 255));
        btn_scantambahwalas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_scantambahwalas.setText("Check");
        btn_scantambahwalas.setIconTextGap(18);
        btn_scantambahwalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scantambahwalasActionPerformed(evt);
            }
        });
        panelformwalikelas.add(btn_scantambahwalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 150, 70, 30));

        btn_simpanformwalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanformwalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanformwalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanformwalikelas.setText("Simpan");
        btn_simpanformwalikelas.setIconTextGap(18);
        btn_simpanformwalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanformwalikelasActionPerformed(evt);
            }
        });
        panelformwalikelas.add(btn_simpanformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 140, 35));

        btn_kembaliformwalikelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliformwalikelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliformwalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliformwalikelas.setIconTextGap(18);
        btn_kembaliformwalikelas.setMargin(new java.awt.Insets(1, 14, 1, 14));
        btn_kembaliformwalikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliformwalikelasActionPerformed(evt);
            }
        });
        panelformwalikelas.add(btn_kembaliformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 55, 35));

        bgtambahwalikelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahwalas.png"))); // NOI18N
        panelformwalikelas.add(bgtambahwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_walikelas.add(panelformwalikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_walikelas, "card4");

        datakelas.setOpaque(true);
        datakelas.setPreferredSize(new java.awt.Dimension(1107, 658));
        datakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_addkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_addkelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_addkelas.setText("Tambah Kelas");
        btn_addkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addkelasActionPerformed(evt);
            }
        });
        panelkelas.add(btn_addkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 120, 30));
        panelkelas.add(txt_searchkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 770, 30));

        btn_searchkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchkelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchkelasActionPerformed(evt);
            }
        });
        panelkelas.add(btn_searchkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, 30, 30));

        btn_refreshkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
        btn_refreshkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshkelasActionPerformed(evt);
            }
        });
        panelkelas.add(btn_refreshkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 60, 30));

        tabkelas.setBackground(new java.awt.Color(255, 255, 255));

        tabel_kelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "NK", "Wali Kelas", "P", "L", "Jumlah Siswa"
            }
        ));
        tabel_kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kelasMouseClicked(evt);
            }
        });
        tabkelas.setViewportView(tabel_kelas);

        panelkelas.add(tabkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 1010, 380));

        btn_hapuskelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapuskelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_hapuskelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/delete.png"))); // NOI18N
        btn_hapuskelas.setText("Hapus");
        btn_hapuskelas.setIconTextGap(18);
        btn_hapuskelas.setMargin(new java.awt.Insets(1, 1, 1, 14));
        btn_hapuskelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapuskelasActionPerformed(evt);
            }
        });
        panelkelas.add(btn_hapuskelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 580, 130, 40));

        btn_lihatkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatkelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatkelas.setText("LIhat Kelas");
        btn_lihatkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatkelasActionPerformed(evt);
            }
        });
        panelkelas.add(btn_lihatkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 580, 130, 40));

        saveadmkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelkelas.png"))); // NOI18N
        panelkelas.add(saveadmkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setText("jLabel1");
        panelkelas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, -1, -1));

        datakelas.add(panelkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datakelas, "card4");

        profilekelas.setOpaque(true);
        profilekelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofilekelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_gambar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_profilekelas.png"))); // NOI18N
        lb_gambar1.setText("jLabel15");
        panelprofilekelas.add(lb_gambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 270, 240));

        lb_nkprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nkprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nkprofilekelas.setText("NK");
        panelprofilekelas.add(lb_nkprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 50, 30));

        lb_tingkatprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_tingkatprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_tingkatprofilekelas.setText("Tingkat");
        panelprofilekelas.add(lb_tingkatprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, 30));

        lb_jurusanprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jurusanprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_jurusanprofilekelas.setText("Jurusan");
        panelprofilekelas.add(lb_jurusanprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 100, 30));

        lb_namaprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_namaprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaprofilekelas.setText("Nama Kelas");
        panelprofilekelas.add(lb_namaprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 130, 30));

        lb_jmlprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_jmlprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_jmlprofilekelas.setText("Jumlah Siswa");
        panelprofilekelas.add(lb_jmlprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 120, 30));

        lb_nipprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nipprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipprofilekelas.setText("Wali Kelas");
        panelprofilekelas.add(lb_nipprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 130, 30));

        lb_smtprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_smtprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_smtprofilekelas.setText("Semester");
        panelprofilekelas.add(lb_smtprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 120, 30));

        lb_taprofilekelas.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_taprofilekelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_taprofilekelas.setText("Tahun Ajaran");
        panelprofilekelas.add(lb_taprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 140, 30));

        lb_titik8.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik8.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik8.setText(":");
        panelprofilekelas.add(lb_titik8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 20, 30));

        lb_titik9.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik9.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik9.setText(":");
        panelprofilekelas.add(lb_titik9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 20, 30));

        lb_titik10.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik10.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik10.setText(":");
        panelprofilekelas.add(lb_titik10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 20, 30));

        lb_titik11.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik11.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik11.setText(":");
        panelprofilekelas.add(lb_titik11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 20, 30));

        lb_titik12.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik12.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik12.setText(":");
        panelprofilekelas.add(lb_titik12, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 20, 30));

        lb_titik13.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik13.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik13.setText(":");
        panelprofilekelas.add(lb_titik13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 20, 30));

        lb_titik28.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik28.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik28.setText(":");
        panelprofilekelas.add(lb_titik28, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 20, 30));

        lb_titik29.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik29.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik29.setText(":");
        panelprofilekelas.add(lb_titik29, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 20, 30));

        txt_taprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_taprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_taprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 530, 30));

        txt_smtprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_smtprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_smtprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 530, 30));

        cb_smtprofilekelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ganjil", "Genap" }));
        cb_smtprofilekelas.setFocusable(false);
        cb_smtprofilekelas.setLightWeightPopupEnabled(false);
        cb_smtprofilekelas.setRequestFocusEnabled(false);
        panelprofilekelas.add(cb_smtprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 530, 30));

        txt_nkprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_nkprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_nkprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 530, 30));

        txt_angkatanprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_angkatanprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_angkatanprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 530, 30));

        txt_jurusanprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_jurusanprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_jurusanprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 530, 30));

        txt_namaprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_namaprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_namaprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 530, 30));

        txt_jmlprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_jmlprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_jmlprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, 530, 30));

        txt_walasprofilekelas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_walasprofilekelas.setEnabled(false);
        panelprofilekelas.add(txt_walasprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 530, 30));

        btn_simpanprofilekelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanprofilekelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofilekelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofilekelas.setText("Simpan");
        btn_simpanprofilekelas.setIconTextGap(18);
        btn_simpanprofilekelas.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_simpanprofilekelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofilekelasActionPerformed(evt);
            }
        });
        panelprofilekelas.add(btn_simpanprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 560, 140, 40));

        btn_editprofilekelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_editprofilekelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editprofilekelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editprofilekelas.setText("Edit");
        btn_editprofilekelas.setIconTextGap(18);
        btn_editprofilekelas.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_editprofilekelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editprofilekelasActionPerformed(evt);
            }
        });
        panelprofilekelas.add(btn_editprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 560, 140, 40));

        btn_lihatanggotakelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatanggotakelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatanggotakelas.setText("Lihat Siswa");
        btn_lihatanggotakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatanggotakelasActionPerformed(evt);
            }
        });
        panelprofilekelas.add(btn_lihatanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 140, 40));

        btn_kembaliprofilekelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofilekelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofilekelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliprofilekelas.setIconTextGap(18);
        btn_kembaliprofilekelas.setMargin(new java.awt.Insets(1, 10, 1, 10));
        btn_kembaliprofilekelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofilekelasActionPerformed(evt);
            }
        });
        panelprofilekelas.add(btn_kembaliprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 560, 55, 35));

        cb_walasprofilekelas.setFocusable(false);
        cb_walasprofilekelas.setLightWeightPopupEnabled(false);
        cb_walasprofilekelas.setRequestFocusEnabled(false);
        panelprofilekelas.add(cb_walasprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 530, 30));

        bgprofilekelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofilekelas.png"))); // NOI18N
        panelprofilekelas.add(bgprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profilekelas.add(panelprofilekelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profilekelas, "card4");

        form_kelas.setOpaque(true);
        form_kelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelformkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_nkformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_nkformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nkformkelas.setText("NK");
        panelformkelas.add(lb_nkformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 135, -1, -1));

        lb_nipformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_nipformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipformkelas.setText("Walikelas");
        panelformkelas.add(lb_nipformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 185, -1, -1));

        lb_tingkatanformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_tingkatanformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_tingkatanformkelas.setText("Tingkatan");
        panelformkelas.add(lb_tingkatanformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 235, -1, -1));

        lb_jurusanformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_jurusanformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_jurusanformkelas.setText("Jurusan");
        panelformkelas.add(lb_jurusanformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 285, -1, -1));

        lb_namaformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_namaformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaformkelas.setText("Nama Kelas");
        panelformkelas.add(lb_namaformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 335, -1, -1));

        lb_smtformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_smtformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_smtformkelas.setText("Semester");
        panelformkelas.add(lb_smtformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 385, -1, -1));

        lb_taformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        lb_taformkelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_taformkelas.setText("Tahun Ajaran");
        panelformkelas.add(lb_taformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 435, -1, -1));

        jLabel78.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 51, 204));
        jLabel78.setText(":");
        panelformkelas.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 135, -1, -1));

        jLabel80.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 51, 204));
        jLabel80.setText(":");
        panelformkelas.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 185, -1, -1));

        jLabel81.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 51, 204));
        jLabel81.setText(":");
        panelformkelas.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 235, -1, -1));

        jLabel84.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 51, 204));
        jLabel84.setText(":");
        panelformkelas.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 285, -1, -1));

        jLabel82.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 51, 204));
        jLabel82.setText(":");
        panelformkelas.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 335, -1, -1));

        jLabel83.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 51, 204));
        jLabel83.setText(":");
        panelformkelas.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 385, -1, -1));

        jLabel85.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 51, 204));
        jLabel85.setText(":");
        panelformkelas.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 435, -1, -1));

        cb_smtformkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ganjil", "Genap" }));
        panelformkelas.add(cb_smtformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 840, 30));

        txt_nkformkelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformkelas.add(txt_nkformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 840, 30));

        txt_tingkatanformkelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformkelas.add(txt_tingkatanformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 840, 30));

        txt_jurusanformkelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformkelas.add(txt_jurusanformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 840, 30));

        txt_namaformkelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformkelas.add(txt_namaformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 840, 30));

        txt_taformkelas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        panelformkelas.add(txt_taformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 840, 30));

        btn_simpanformkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanformkelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanformkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanformkelas.setText("Simpan");
        btn_simpanformkelas.setIconTextGap(18);
        btn_simpanformkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanformkelasActionPerformed(evt);
            }
        });
        panelformkelas.add(btn_simpanformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 580, 140, 35));

        cb_walasformkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih---" }));
        cb_walasformkelas.setFocusable(false);
        cb_walasformkelas.setLightWeightPopupEnabled(false);
        cb_walasformkelas.setRequestFocusEnabled(false);
        cb_walasformkelas.setVerifyInputWhenFocusTarget(false);
        panelformkelas.add(cb_walasformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 840, 30));

        btn_kembaliformkelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliformkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliformkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliformkelasActionPerformed(evt);
            }
        });
        panelformkelas.add(btn_kembaliformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 580, 55, 35));

        bgformkelas.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        bgformkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneltambahkelas.png"))); // NOI18N
        panelformkelas.add(bgformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        form_kelas.add(panelformkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(form_kelas, "card4");

        dataadmin.setOpaque(true);
        dataadmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paneladmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_searchadmin.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        paneladmin.add(txt_searchadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 850, 30));

        btn_lihatadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihatadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_lihatadmin.setText("Lihat Profile");
        btn_lihatadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatadminActionPerformed(evt);
            }
        });
        paneladmin.add(btn_lihatadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 130, 40));

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
        btn_hapusadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusadminActionPerformed(evt);
            }
        });
        paneladmin.add(btn_hapusadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 570, 130, 40));

        tabadmin.setBackground(new java.awt.Color(255, 255, 255));
        tabadmin.setBorder(null);

        tabel_admin.setForeground(new java.awt.Color(255, 255, 255));
        tabel_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID Admin", "NIP", "Nama", "Username", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_admin.setEditingColumn(1);
        tabel_admin.setEditingRow(1);
        tabel_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_adminMouseClicked(evt);
            }
        });
        tabadmin.setViewportView(tabel_admin);

        paneladmin.add(tabadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 1010, 390));

        bgadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/paneladmin.png"))); // NOI18N
        paneladmin.add(bgadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        paneladmin.add(saveadm, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 100, 50));

        dataadmin.add(paneladmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataadmin, "card4");

        profileadmin.setOpaque(true);
        profileadmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprofileadmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_gambar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_profileadmin.png"))); // NOI18N
        panelprofileadmin.add(lb_gambar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 260, 230));

        lb_idadminprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_idadminprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_idadminprofileadmin.setText("ID Admin");
        panelprofileadmin.add(lb_idadminprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 110, 30));

        lb_namaprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_namaprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_namaprofileadmin.setText("Nama");
        panelprofileadmin.add(lb_namaprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, 30));

        lb_usenameprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_usenameprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_usenameprofileadmin.setText("Username");
        panelprofileadmin.add(lb_usenameprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 100, 30));

        lb_passprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_passprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_passprofileadmin.setText("Password");
        panelprofileadmin.add(lb_passprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 130, 30));

        lb_statusprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_statusprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_statusprofileadmin.setText("Status");
        panelprofileadmin.add(lb_statusprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 120, 30));

        lb_nipprofileadmin.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_nipprofileadmin.setForeground(new java.awt.Color(0, 51, 204));
        lb_nipprofileadmin.setText("NIP");
        panelprofileadmin.add(lb_nipprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 130, 30));

        lb_titik14.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik14.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik14.setText(":");
        panelprofileadmin.add(lb_titik14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 20, 30));

        lb_titik22.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik22.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik22.setText(":");
        panelprofileadmin.add(lb_titik22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 20, 30));

        lb_titik23.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik23.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik23.setText(":");
        panelprofileadmin.add(lb_titik23, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 20, 30));

        lb_titik24.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik24.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik24.setText(":");
        panelprofileadmin.add(lb_titik24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 20, 30));

        lb_titik25.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik25.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik25.setText(":");
        panelprofileadmin.add(lb_titik25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 20, 30));

        lb_titik26.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        lb_titik26.setForeground(new java.awt.Color(0, 51, 204));
        lb_titik26.setText(":");
        panelprofileadmin.add(lb_titik26, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 20, 30));

        txt_idadminprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_idadminprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 530, 30));

        txt_namaprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_namaprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 530, 30));

        txt_usernameprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_usernameprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 530, 30));

        txt_passprofileadmin.setText("jPasswordField1");
        txt_passprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_passprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 460, 30));
        panelprofileadmin.add(txt_pass2profileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 460, 30));

        txt_statusprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_statusprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 530, 30));

        txt_nipprofileadmin.setEnabled(false);
        panelprofileadmin.add(txt_nipprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 530, 30));

        btn_checkpassprofileadmin.setText("Lihat");
        btn_checkpassprofileadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkpassprofileadminActionPerformed(evt);
            }
        });
        panelprofileadmin.add(btn_checkpassprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 320, 60, 30));

        btn_simpanprofileadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpanprofileadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_simpanprofileadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/floppy-disk.png"))); // NOI18N
        btn_simpanprofileadmin.setText("Simpan");
        btn_simpanprofileadmin.setIconTextGap(18);
        btn_simpanprofileadmin.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_simpanprofileadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanprofileadminActionPerformed(evt);
            }
        });
        panelprofileadmin.add(btn_simpanprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 560, 140, 40));

        btn_editprofileadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_editprofileadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_editprofileadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/edit.png"))); // NOI18N
        btn_editprofileadmin.setText("Edit");
        btn_editprofileadmin.setIconTextGap(18);
        btn_editprofileadmin.setMargin(new java.awt.Insets(1, 1, 1, 10));
        btn_editprofileadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editprofileadminActionPerformed(evt);
            }
        });
        panelprofileadmin.add(btn_editprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 560, 140, 40));

        btn_kembaliprofileadmin.setBackground(new java.awt.Color(255, 255, 255));
        btn_kembaliprofileadmin.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_kembaliprofileadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembaliprofileadmin.setIconTextGap(18);
        btn_kembaliprofileadmin.setMargin(new java.awt.Insets(1, 10, 1, 10));
        btn_kembaliprofileadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliprofileadminActionPerformed(evt);
            }
        });
        panelprofileadmin.add(btn_kembaliprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 560, 55, 35));

        cb_statusprofileadmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guru BK", "Bagian IT", "Kepala Sekolah" }));
        cb_statusprofileadmin.setFocusable(false);
        cb_statusprofileadmin.setLightWeightPopupEnabled(false);
        cb_statusprofileadmin.setRequestFocusEnabled(false);
        panelprofileadmin.add(cb_statusprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 530, 30));

        bgprofileadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelprofileadmin.png"))); // NOI18N
        panelprofileadmin.add(bgprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        profileadmin.add(panelprofileadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(profileadmin, "card4");

        dataabsensi.setOpaque(true);
        dataabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        dataabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelabsensi.add(txt_searchdataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 430, 30));

        txt_searchtgldataabsen.setToolTipText("");
        txt_searchtgldataabsen.setName(""); // NOI18N
        txt_searchtgldataabsen.setVerifyInputWhenFocusTarget(false);
        panelabsensi.add(txt_searchtgldataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 200, 30));

        txt_searchtgl2dataabsen.setToolTipText("");
        txt_searchtgl2dataabsen.setName(""); // NOI18N
        txt_searchtgl2dataabsen.setVerifyInputWhenFocusTarget(false);
        panelabsensi.add(txt_searchtgl2dataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 190, 30));

        btn_searchdataabsen.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchdataabsen.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchdataabsen.setText("Search");
        btn_searchdataabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchdataabsenActionPerformed(evt);
            }
        });
        panelabsensi.add(btn_searchdataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 100, 30));

        nisdataabsensi.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        nisdataabsensi.setText("NIS");
        panelabsensi.add(nisdataabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 114, 40, -1));

        tgldataabsensi.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        tgldataabsensi.setText("Tanggal");
        panelabsensi.add(tgldataabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 155, -1, -1));

        statusdatabsensi.setFont(new java.awt.Font("Noto Serif", 1, 14)); // NOI18N
        statusdatabsensi.setText("Status ");
        panelabsensi.add(statusdatabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 109, -1, -1));

        sampaidatabsensi.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 18)); // NOI18N
        sampaidatabsensi.setText("-");
        panelabsensi.add(sampaidatabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        btn_updateabsen.setBackground(new java.awt.Color(255, 255, 255));
        btn_updateabsen.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_updateabsen.setText("Update");
        btn_updateabsen.setRequestFocusEnabled(false);
        btn_updateabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateabsenActionPerformed(evt);
            }
        });
        panelabsensi.add(btn_updateabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 140, -1, 30));

        btn_refreshdataabsen.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshdataabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh.png"))); // NOI18N
        btn_refreshdataabsen.setIconTextGap(18);
        btn_refreshdataabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshdataabsenActionPerformed(evt);
            }
        });
        panelabsensi.add(btn_refreshdataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 30, 30));

        tabel_absen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "NIS", "NK", "Nama", "Tanggal", "Jam", "Status"
            }
        ));
        tabel_absen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_absenMouseClicked(evt);
            }
        });
        tababsen.setViewportView(tabel_absen);

        panelabsensi.add(tababsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 1010, 370));

        cb_statusdataabsen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hadir", "Sakit", "Izin", "Alpha" }));
        cb_statusdataabsen.setFocusable(false);
        cb_statusdataabsen.setLightWeightPopupEnabled(false);
        cb_statusdataabsen.setRequestFocusEnabled(false);
        cb_statusdataabsen.setVerifyInputWhenFocusTarget(false);
        cb_statusdataabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_statusdataabsenActionPerformed(evt);
            }
        });
        panelabsensi.add(cb_statusdataabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 350, 30));

        lb_idabsen.setText("jLabel2");
        panelabsensi.add(lb_idabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText(":");
        panelabsensi.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 10, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText(":");
        panelabsensi.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 10, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText(":");
        panelabsensi.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 10, -1));

        bgabsensi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bgabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelabsensi.png"))); // NOI18N
        panelabsensi.add(bgabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataabsensi.add(panelabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataabsensi, "card4");

        datalapabsensi.setOpaque(true);
        datalapabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        datalapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panellapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panellapabsensi.add(txt_searchbynis, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 400, 30));
        panellapabsensi.add(txt_tglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 400, 30));

        tabel_lapabsen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID Laporan", "Wali Kelas", "NIS", "NK", "Nama", "Hadir", "Sakit", "Izin", "Alpha", "Terlambat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_lapabsen.setViewportView(tabel_lapabsen);
        if (tabel_lapabsen.getColumnModel().getColumnCount() > 0) {
            tabel_lapabsen.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        panellapabsensi.add(tab_lapabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 1010, 400));

        bglapabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panellapabsensi.png"))); // NOI18N
        panellapabsensi.add(bglapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datalapabsensi.add(panellapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datalapabsensi, "card4");

        datalapsiswamasalah.setOpaque(true);
        datalapsiswamasalah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsiswabermasalah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelsiswabermasalah.add(txt_siswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 400, 30));

        cb_siswabermasalah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelsiswabermasalah.add(cb_siswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 400, 30));

        btn_lihatprofilesiswabermasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        panelsiswabermasalah.add(btn_lihatprofilesiswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 30, 30));

        btn_updatestatussiswabermasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        panelsiswabermasalah.add(btn_updatestatussiswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 30, 30));

        tabel_siswabermasalah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_siswabermasalah.setViewportView(tabel_siswabermasalah);

        panelsiswabermasalah.add(tab_siswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 1010, 400));

        bglapabsensi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panellapabsensi.png"))); // NOI18N
        panelsiswabermasalah.add(bglapabsensi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datalapsiswamasalah.add(panelsiswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datalapsiswamasalah, "card4");

        dataanggotakelas.setOpaque(true);
        dataanggotakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelanggotakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_chartanggotakelas.setBackground(new java.awt.Color(255, 255, 255));
        panel_chartanggotakelas.setLayout(new javax.swing.OverlayLayout(panel_chartanggotakelas));
        panelanggotakelas.add(panel_chartanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 460, 440));

        tabel_anggotakelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "NIS", "Nama", "Jenis Kelamin"
            }
        ));
        tab_anggotakelas.setViewportView(tabel_anggotakelas);

        panelanggotakelas.add(tab_anggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 530, 340));

        lb_nkak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        panelanggotakelas.add(lb_nkak, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 300, 30));

        lb_iwak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        panelanggotakelas.add(lb_iwak, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 135, 290, 30));

        lb_nkanggotakelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_nkanggotakelas.setForeground(new java.awt.Color(0, 51, 240));
        lb_nkanggotakelas.setText("NK");
        panelanggotakelas.add(lb_nkanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 105, -1, -1));

        lb_nisanggotakelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_nisanggotakelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_nisanggotakelas.setText("Id Walikelas");
        panelanggotakelas.add(lb_nisanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));
        panelanggotakelas.add(txt_searchnisanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 290, 30));

        lb_idwalasanggotakelas.setFont(new java.awt.Font("Noto Serif", 0, 14)); // NOI18N
        lb_idwalasanggotakelas.setForeground(new java.awt.Color(0, 51, 204));
        lb_idwalasanggotakelas.setText("NIS");
        panelanggotakelas.add(lb_idwalasanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 175, -1, -1));

        btn_refreshanggotakelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_refreshanggotakelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/refresh (1).png"))); // NOI18N
        btn_refreshanggotakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshanggotakelasActionPerformed(evt);
            }
        });
        panelanggotakelas.add(btn_refreshanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 40, 30));

        btn_searchanggotakelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchanggotakelas.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        btn_searchanggotakelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/brn_search2.png"))); // NOI18N
        btn_searchanggotakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchanggotakelasActionPerformed(evt);
            }
        });
        panelanggotakelas.add(btn_searchanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 30, 30));

        btn_cetakanggotakelas.setBackground(new java.awt.Color(255, 255, 255));
        btn_cetakanggotakelas.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 12)); // NOI18N
        btn_cetakanggotakelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconcetakdata.png"))); // NOI18N
        btn_cetakanggotakelas.setText("Cetak");
        btn_cetakanggotakelas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_cetakanggotakelas.setIconTextGap(18);
        btn_cetakanggotakelas.setMargin(new java.awt.Insets(1, 1, 2, 11));
        btn_cetakanggotakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakanggotakelasActionPerformed(evt);
            }
        });
        panelanggotakelas.add(btn_cetakanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 580, 130, 40));

        btn_kembalianggotakelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/btn_kembali.png"))); // NOI18N
        btn_kembalianggotakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembalianggotakelasActionPerformed(evt);
            }
        });
        panelanggotakelas.add(btn_kembalianggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 583, 55, 35));

        bganggotakelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panel/panelanggotakelas.png"))); // NOI18N
        panelanggotakelas.add(bganggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataanggotakelas.add(panelanggotakelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataanggotakelas, "card4");

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
        this.tampilsiswa();
        this.editprofilesiswa(false);
        txt_searchsiswa.setText("");
    }//GEN-LAST:event_btn_datasiswaMouseClicked

    private void btn_datawalikelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datawalikelasMouseClicked
         switchpanel(datawalikelas);
         this.tampilwalas();
         this.editprofilewalas(false);
         txt_searchwalikelas.setText("");
    }//GEN-LAST:event_btn_datawalikelasMouseClicked

    private void btn_datakelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseClicked
         switchpanel(datakelas);
         this.tampilkelas();
         this.editprofilekelas(false);
         txt_searchkelas.setText("");
    }//GEN-LAST:event_btn_datakelasMouseClicked

    private void btn_dataabsensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseClicked
        switchpanel(dataabsensi);
        this.tampilabsen();
        txt_searchdataabsen.setText("");
        txt_searchtgldataabsen.setText("");
        txt_searchtgl2dataabsen.setText("");
    }//GEN-LAST:event_btn_dataabsensiMouseClicked

    private void btn_lapabsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapabsenMouseClicked
         switchpanel(datalapabsensi);
         this.tampillapabsen();
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

    private void btn_datawalikelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datawalikelasMouseEntered
        btn_datawalikelas.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_datawalikelasMouseEntered

    private void btn_datawalikelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datawalikelasMouseExited
        btn_datawalikelas.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_datawalikelasMouseExited

    private void btn_datawalikelasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datawalikelasMousePressed
        btn_datawalikelas.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_datawalikelasMousePressed

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
        if (txt_searchsiswa.getText().equals("")){
            JOptionPane.showMessageDialog(null, ("Data Pencarian Tidak Ditemukan"), 
            "Data Siswa", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            this.querysearchsiswa();
        }
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

    private void brn_hapuswalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_hapuswalikelasActionPerformed
        this.validasideletedatawalas();
    }//GEN-LAST:event_brn_hapuswalikelasActionPerformed

    private void btn_searchwalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchwalikelasActionPerformed
        if (txt_searchwalikelas.getText().equals("")){
            JOptionPane.showMessageDialog(null, ("Data Pencarian Tidak Ditemukan"), 
            "Data Guru", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            this.querysearchwalas();
        }
    }//GEN-LAST:event_btn_searchwalikelasActionPerformed

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
        this.logout();
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

    private void tabel_walikelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_walikelasMouseClicked
        this.querysearchklikwalas();
    }//GEN-LAST:event_tabel_walikelasMouseClicked

    private void tabel_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_adminMouseClicked
        this.querysearchklikadmin();
    }//GEN-LAST:event_tabel_adminMouseClicked

    private void btn_lihatadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatadminActionPerformed
        if (txt_searchadmin.getText().equals(Session.getnipadmin())){
            Session.setnipadmin(txt_searchadmin.getText());
            switchpanel(profileadmin);
            this.tampilprofileadmin();
            this.convertstatusadmin();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Admin", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatadminActionPerformed

    private void btn_registrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrasiActionPerformed
        switchpanel(form_registrasi);
        this.Autonumber();
        this.hapuslayar();
    }//GEN-LAST:event_btn_registrasiActionPerformed

    private void btn_hapusadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusadminMouseClicked
        this.validasideletedataadmin();
    }//GEN-LAST:event_btn_hapusadminMouseClicked

    private void btn_dataadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseClicked
        switchpanel(dataadmin);
        tampiladmin();
        txt_searchadmin.setText("");
    }//GEN-LAST:event_btn_dataadminMouseClicked

    private void btn_editprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofilesiswaActionPerformed
        this.editprofilesiswa(true);
    }//GEN-LAST:event_btn_editprofilesiswaActionPerformed

    private void btn_kembaliprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofilesiswaActionPerformed
        switchpanel(datasiswa);
        this.editprofilesiswa(false);
        this.tampilsiswa();
        txt_searchsiswa.setText("");
    }//GEN-LAST:event_btn_kembaliprofilesiswaActionPerformed

    private void btn_simpanprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofilesiswaActionPerformed
        this.simpanprofilesiswa();
        this.editprofilesiswa(false);
    }//GEN-LAST:event_btn_simpanprofilesiswaActionPerformed

    private void btn_lihatwalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatwalikelasActionPerformed
        if (txt_searchwalikelas.getText().equals(Session.getnipwalas())){ 
            Session.setnipwalas(txt_searchwalikelas.getText());
            switchpanel(profilewalikelas);
            this.tampilprofilewalas();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Guru", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatwalikelasActionPerformed

    private void btn_editprofilewalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofilewalikelasActionPerformed
        // TODO add your handling code here:
        this.editprofilewalas(true);
    }//GEN-LAST:event_btn_editprofilewalikelasActionPerformed

    private void btn_kembaliprofilewalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofilewalikelasActionPerformed
        // TODO add your handling code here:
        switchpanel(datawalikelas);
        this.tampilwalas();
        this.editprofilewalas(false);
        txt_searchwalikelas.setText("");

    }//GEN-LAST:event_btn_kembaliprofilewalikelasActionPerformed

    private void btn_simpanprofilewalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofilewalikelasActionPerformed
        // TODO add your handling code here:
        this.simpanprofilewalas();
        this.editprofilewalas(false);
    }//GEN-LAST:event_btn_simpanprofilewalikelasActionPerformed

    private void btn_refreshdatasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshdatasiswaActionPerformed
        // TODO add your handling code here:
        this.tampilsiswa();
    }//GEN-LAST:event_btn_refreshdatasiswaActionPerformed

    private void btn_simpantambahsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpantambahsiswaActionPerformed
        this.addrfid(txt_rfidformsiswa.getText(),txt_nisformsiswa.getText(),txt_namaformsiswa.getText());
        this.walastambahsiswa();
        this.tambahdatasiswa();        
    }//GEN-LAST:event_btn_simpantambahsiswaActionPerformed

    private void btn_tambahsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahsiswaActionPerformed
        // TODO add your handling code here:
        switchpanel(form_siswa);
        this.hapuslayar();
    }//GEN-LAST:event_btn_tambahsiswaActionPerformed

    private void btn_simpanformwalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanformwalikelasActionPerformed
        this.tambahdatawalas();
        this.autonumberwalas();
    }//GEN-LAST:event_btn_simpanformwalikelasActionPerformed

    private void btn_tambahwalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahwalikelasActionPerformed
        // TODO add your handling code here:
        switchpanel(form_walikelas);
        this.hapuslayar();
        this.autonumberwalas();
    }//GEN-LAST:event_btn_tambahwalikelasActionPerformed

    private void btn_daftarregadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarregadminActionPerformed
        this.tambahdataadmin();
        this.Autonumber();
        this.hapuslayar();
    }//GEN-LAST:event_btn_daftarregadminActionPerformed

    private void btn_refreshdatawalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshdatawalikelasActionPerformed
        // TODO add your handling code here:
        this.tampilwalas();
    }//GEN-LAST:event_btn_refreshdatawalikelasActionPerformed

    private void btn_simpanformkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanformkelasActionPerformed
        // TODO add your handling code here:
        this.tambahdatakelas();
    }//GEN-LAST:event_btn_simpanformkelasActionPerformed

    private void btn_simpanprofilekelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofilekelasActionPerformed
        // TODO add your handling code here:
        this.simpanprofilekelas();
        this.editprofilekelas(false);
    }//GEN-LAST:event_btn_simpanprofilekelasActionPerformed

    private void btn_editprofilekelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofilekelasActionPerformed
        // TODO add your handling code here:
       this.editprofilekelas(true);
       this.getwalasprofilekelas();
    }//GEN-LAST:event_btn_editprofilekelasActionPerformed

    private void btn_kembaliprofilekelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofilekelasActionPerformed
            switchpanel(datakelas);
            this.editprofilekelas(false);
            this.tampilkelas();
            txt_searchkelas.setText("");
    }//GEN-LAST:event_btn_kembaliprofilekelasActionPerformed

    private void btn_kembaliregadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliregadminActionPerformed
        switchpanel(dataadmin);
        this.tampiladmin();
        this.hapuslayar();
        txt_searchadmin.setText("");
    }//GEN-LAST:event_btn_kembaliregadminActionPerformed

    private void btn_kembalitambahisiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembalitambahisiswaActionPerformed
        switchpanel(datasiswa);
        this.tampilsiswa();
        this.hapuslayar();
        txt_searchsiswa.setText("");
    }//GEN-LAST:event_btn_kembalitambahisiswaActionPerformed

    private void btn_kembaliformwalikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliformwalikelasActionPerformed
        switchpanel(datawalikelas);
        this.tampilwalas();
        this.hapuslayar();
        txt_searchwalikelas.setText("");
    }//GEN-LAST:event_btn_kembaliformwalikelasActionPerformed

    private void btn_scantambahsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scantambahsiswaActionPerformed
        this.rfidlogging();
        this.rfidloggingformsiswa();
    }//GEN-LAST:event_btn_scantambahsiswaActionPerformed

    private void btn_dataguruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseClicked
        switchpanel(dataguru);
        this.tampilguru();
        txt_searchguru.setText("");
    }//GEN-LAST:event_btn_dataguruMouseClicked

    private void btn_dataguruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseEntered
        btn_dataguru.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataguruMouseEntered

    private void btn_dataguruMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseExited
        btn_dataguru.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataguruMouseExited

    private void btn_dataguruMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMousePressed
        btn_dataguru.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataguruMousePressed

    private void btn_searchguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchguruActionPerformed
        this.querysearchguru();
    }//GEN-LAST:event_btn_searchguruActionPerformed

    private void tabel_guruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_guruMouseClicked
        this.querysearchklikguru();
    }//GEN-LAST:event_tabel_guruMouseClicked

    private void btn_lihatguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatguruActionPerformed
        switchpanel(profileguru);
        this.tampilprofileguru();
    }//GEN-LAST:event_btn_lihatguruActionPerformed

    private void brn_hapusguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_hapusguruActionPerformed
        this.deletedataguru();
    }//GEN-LAST:event_brn_hapusguruActionPerformed

    private void btn_refreshdataguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshdataguruActionPerformed
        this.tampilguru();
    }//GEN-LAST:event_btn_refreshdataguruActionPerformed

    private void btn_tambahguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahguruActionPerformed
        switchpanel(form_guru);
        
    }//GEN-LAST:event_btn_tambahguruActionPerformed

    private void btn_scanprofilesiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanprofilesiswaActionPerformed
        this.updaterfid();
    }//GEN-LAST:event_btn_scanprofilesiswaActionPerformed

    private void btn_scantambahsiswa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scantambahsiswa1ActionPerformed
        this.walastambahsiswa();
        this.querynamawalassiswa();
    }//GEN-LAST:event_btn_scantambahsiswa1ActionPerformed

    private void btn_simpanformguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanformguruActionPerformed
        this.tambahdataguru();
    }//GEN-LAST:event_btn_simpanformguruActionPerformed

    private void btn_kembaliformguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliformguruActionPerformed
        switchpanel(dataguru);
        this.tampilguru();
        this.editprofileguru(false);
        this.hapuslayar();
        txt_searchguru.setText("");
    }//GEN-LAST:event_btn_kembaliformguruActionPerformed

    private void btn_simpanprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofileguruActionPerformed
        this.simpanprofileguru();
        this.editprofileguru(false);
    }//GEN-LAST:event_btn_simpanprofileguruActionPerformed

    private void btn_editprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofileguruActionPerformed
        this.editprofileguru(true);
    }//GEN-LAST:event_btn_editprofileguruActionPerformed

    private void btn_kembaliprofileguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofileguruActionPerformed
        switchpanel(dataguru);
        this.tampilguru();
        txt_searchguru.setText("");
    }//GEN-LAST:event_btn_kembaliprofileguruActionPerformed

    private void btn_addkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addkelasActionPerformed
        switchpanel(form_kelas);
        this.updatewalasprofilekelas();
    }//GEN-LAST:event_btn_addkelasActionPerformed

    private void btn_lihatkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatkelasActionPerformed
        // TODO add your handling code here:
        if (txt_searchkelas.getText().equals(Session.getnkkelas())){
            Session.setnkkelas(txt_searchkelas.getText());
            switchpanel(profilekelas);
            this.tampilprofilekelas();
        }else{
            JOptionPane.showMessageDialog(null, ("Data Tidak Ditemukan"), 
            "Data Profile Kelas", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_lihatkelasActionPerformed

    private void btn_hapuskelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapuskelasActionPerformed
        this.validasideletedatakelas();
    }//GEN-LAST:event_btn_hapuskelasActionPerformed

    private void btn_searchkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchkelasActionPerformed
        // TODO add your handling code here:
         if (txt_searchkelas.getText().equals("")){
            JOptionPane.showMessageDialog(null, ("Data Pencarian Tidak Ditemukan"), 
            "Data Kelas", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            this.querysearchkelas();
        }
    }//GEN-LAST:event_btn_searchkelasActionPerformed

    private void btn_refreshkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshkelasActionPerformed
        // TODO add your handling code here:
        this.tampilkelas();
    }//GEN-LAST:event_btn_refreshkelasActionPerformed

    private void tabel_kelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kelasMouseClicked
        // TODO add your handling code here:
        this.querysearchklikkelas();
    }//GEN-LAST:event_tabel_kelasMouseClicked

    private void btn_searchdataabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchdataabsenActionPerformed
        // TODO add your handling code here:
        this.querysearchabsen();
    }//GEN-LAST:event_btn_searchdataabsenActionPerformed

    private void btn_scantambahwalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scantambahwalasActionPerformed
        this.querydataguruwalas();
    }//GEN-LAST:event_btn_scantambahwalasActionPerformed

    private void btn_hapussiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapussiswaActionPerformed
        this.validasideletedatasiswa();
    }//GEN-LAST:event_btn_hapussiswaActionPerformed

    private void btn_scantambahadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scantambahadminActionPerformed
        this.querydataguruadmin();
    }//GEN-LAST:event_btn_scantambahadminActionPerformed

    private void btn_riwayatabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_riwayatabsenActionPerformed
        // TODO add your handling code here:
        switchpanel(riwayat_siswa);
        this.tampilriwayatabsensiswa();
    }//GEN-LAST:event_btn_riwayatabsenActionPerformed

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        dashboard ds = new dashboard();
        ds.setVisible(true);
                        
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_kembaliformkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliformkelasActionPerformed
        // TODO add your handling code here:
        switchpanel(datakelas);
        tampilkelas();
        txt_searchkelas.setText("");
    }//GEN-LAST:event_btn_kembaliformkelasActionPerformed

    private void btn_hapusadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusadminActionPerformed
        this.validasideletedataadmin();
    }//GEN-LAST:event_btn_hapusadminActionPerformed

    private void btn_simpanprofileadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanprofileadminActionPerformed
        // TODO add your handling code here:
        this.checkusernameprofileadmin();
        this.editprofileadmin(false);
    }//GEN-LAST:event_btn_simpanprofileadminActionPerformed

    private void btn_editprofileadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editprofileadminActionPerformed
        this.editprofileadmin(true);
    }//GEN-LAST:event_btn_editprofileadminActionPerformed

    private void btn_kembaliprofileadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliprofileadminActionPerformed
        switchpanel(dataadmin);
        this.editprofileadmin(false);
        this.tampiladmin();
        btn_checkpassprofileadmin.setSelected(false);
        txt_pass2profileadmin.setVisible(false);
        txt_passprofileadmin.setVisible(true);
        txt_searchadmin.setText("");
    }//GEN-LAST:event_btn_kembaliprofileadminActionPerformed

    private void btn_updateabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateabsenActionPerformed
        // TODO add your handling code here:
        this.updatestatusabsen();
        txt_searchtgl2dataabsen.setText("");
        this.tampilabsen();
    }//GEN-LAST:event_btn_updateabsenActionPerformed

    private void btn_lihatanggotakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatanggotakelasActionPerformed
        // TODO add your handling code here:
        switchpanel(dataanggotakelas);
        this.tampilanggotakelas();
    }//GEN-LAST:event_btn_lihatanggotakelasActionPerformed

    private void btn_cetakanggotakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakanggotakelasActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("ANGGOTA KELAS");
        try {
            tabel_anggotakelas.print(JTable.PrintMode.FIT_WIDTH, header,null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btn_cetakanggotakelasActionPerformed

    private void btn_checkpassprofileadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkpassprofileadminActionPerformed
        this.checkpassprofileadmin();
    }//GEN-LAST:event_btn_checkpassprofileadminActionPerformed

    private void tabel_absenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_absenMouseClicked
        this.querysearchklikabsen();
    }//GEN-LAST:event_tabel_absenMouseClicked

    private void btn_refreshdataabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshdataabsenActionPerformed
        this.tampilabsen();
        txt_searchdataabsen.setText("");
        txt_searchtgldataabsen.setText("");
        txt_searchtgl2dataabsen.setText("");
    }//GEN-LAST:event_btn_refreshdataabsenActionPerformed

    private void cb_statusdataabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_statusdataabsenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_statusdataabsenActionPerformed

    private void btn_searchanggotakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchanggotakelasActionPerformed
        this.querysearchanggotakelas();
    }//GEN-LAST:event_btn_searchanggotakelasActionPerformed

    private void btn_refreshanggotakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshanggotakelasActionPerformed
        this.tampilanggotakelas();
    }//GEN-LAST:event_btn_refreshanggotakelasActionPerformed

    private void btn_kembalianggotakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembalianggotakelasActionPerformed
        switchpanel(profilekelas);
        this.tampilprofilekelas();
    }//GEN-LAST:event_btn_kembalianggotakelasActionPerformed

    private void btn_kembaliriwayatabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliriwayatabsenActionPerformed
        // TODO add your handling code here:
        switchpanel(profilesiswa);
        tampilprofilesiswa();
        this.editprofilesiswa(false);
    }//GEN-LAST:event_btn_kembaliriwayatabsenActionPerformed

    private void btn_refreshriwayatsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshriwayatsiswaActionPerformed
        this.tampilriwayatabsensiswa();
    }//GEN-LAST:event_btn_refreshriwayatsiswaActionPerformed

    private void tabel_riwayatsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_riwayatsiswaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_riwayatsiswaMouseClicked

    private void btn_cetakriwayatsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakriwayatsiswaActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("RIWAYAT ABSEN");
        try {
            tabel_riwayatsiswa.print(JTable.PrintMode.FIT_WIDTH, header,null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_btn_cetakriwayatsiswaActionPerformed

    private void btn_searchriwayatsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchriwayatsiswaActionPerformed
        this.searchriwayatsiswa();
    }//GEN-LAST:event_btn_searchriwayatsiswaActionPerformed

    private void txt_tanggal2riwayatsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tanggal2riwayatsiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tanggal2riwayatsiswaActionPerformed

    
    public static void main(String args[]) {
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
    private javax.swing.JLabel bganggotakelas;
    private javax.swing.JLabel bgformkelas;
    private javax.swing.JLabel bgguru;
    private javax.swing.JLabel bglapabsensi;
    private javax.swing.JLabel bglapabsensi1;
    private javax.swing.JLabel bgprofileadmin;
    private javax.swing.JLabel bgprofileguru;
    private javax.swing.JLabel bgprofilekelas;
    private javax.swing.JLabel bgprofilesiswa;
    private javax.swing.JLabel bgprofilewalikelas;
    private javax.swing.JLabel bgregistrasiadmin;
    private javax.swing.JLabel bgriwayatsiswa;
    private javax.swing.JLabel bgsiswa;
    private javax.swing.JLabel bgtambahguru;
    private javax.swing.JLabel bgtambahsiswa;
    private javax.swing.JLabel bgtambahwalikelas;
    private javax.swing.JLabel bgwalikelas;
    private javax.swing.JButton brn_hapusguru;
    private javax.swing.JButton brn_hapuswalikelas;
    private javax.swing.JButton btn_addkelas;
    private javax.swing.JButton btn_cetakanggotakelas;
    private javax.swing.JButton btn_cetakriwayatsiswa;
    private javax.swing.JToggleButton btn_checkpassprofileadmin;
    private javax.swing.JButton btn_daftarregadmin;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JPanel btn_dataabsensi;
    private javax.swing.JPanel btn_dataadmin;
    private javax.swing.JPanel btn_dataguru;
    private javax.swing.JPanel btn_datakelas;
    private javax.swing.JPanel btn_datasiswa;
    private javax.swing.JPanel btn_datawalikelas;
    private javax.swing.JButton btn_editprofileadmin;
    private javax.swing.JButton btn_editprofileguru;
    private javax.swing.JButton btn_editprofilekelas;
    private javax.swing.JButton btn_editprofilesiswa;
    private javax.swing.JButton btn_editprofilewalikelas;
    private javax.swing.JButton btn_hapusadmin;
    private javax.swing.JButton btn_hapuskelas;
    private javax.swing.JButton btn_hapussiswa;
    private javax.swing.JButton btn_kembalianggotakelas;
    private javax.swing.JButton btn_kembaliformguru;
    private javax.swing.JButton btn_kembaliformkelas;
    private javax.swing.JButton btn_kembaliformwalikelas;
    private javax.swing.JButton btn_kembaliprofileadmin;
    private javax.swing.JButton btn_kembaliprofileguru;
    private javax.swing.JButton btn_kembaliprofilekelas;
    private javax.swing.JButton btn_kembaliprofilesiswa;
    private javax.swing.JButton btn_kembaliprofilewalikelas;
    private javax.swing.JButton btn_kembaliregadmin;
    private javax.swing.JButton btn_kembaliriwayatabsen;
    private javax.swing.JButton btn_kembalitambahisiswa;
    private javax.swing.JPanel btn_lapabsen;
    private javax.swing.JButton btn_lihatadmin;
    private javax.swing.JButton btn_lihatanggotakelas;
    private javax.swing.JButton btn_lihatdatasiswa;
    private javax.swing.JButton btn_lihatguru;
    private javax.swing.JButton btn_lihathistory;
    private javax.swing.JButton btn_lihatkelas;
    private javax.swing.JButton btn_lihatprofilesiswabermasalah;
    private javax.swing.JButton btn_lihatsiswa;
    private javax.swing.JButton btn_lihatwalikelas;
    private javax.swing.JPanel btn_logout;
    private javax.swing.JButton btn_refreshanggotakelas;
    private javax.swing.JButton btn_refreshdataabsen;
    private javax.swing.JButton btn_refreshdataguru;
    private javax.swing.JButton btn_refreshdatasiswa;
    private javax.swing.JButton btn_refreshdatawalikelas;
    private javax.swing.JButton btn_refreshkelas;
    private javax.swing.JButton btn_refreshriwayatsiswa;
    private javax.swing.JButton btn_registrasi;
    private javax.swing.JButton btn_riwayatabsen;
    private javax.swing.JButton btn_scanprofilesiswa;
    private javax.swing.JButton btn_scantambahadmin;
    private javax.swing.JButton btn_scantambahsiswa;
    private javax.swing.JButton btn_scantambahsiswa1;
    private javax.swing.JButton btn_scantambahwalas;
    private javax.swing.JButton btn_searchanggotakelas;
    private javax.swing.JButton btn_searchdataabsen;
    private javax.swing.JButton btn_searchguru;
    private javax.swing.JButton btn_searchkelas;
    private javax.swing.JButton btn_searchriwayatsiswa;
    private javax.swing.JButton btn_searchsiswa;
    private javax.swing.JButton btn_searchwalikelas;
    private javax.swing.JButton btn_simpanformguru;
    private javax.swing.JButton btn_simpanformkelas;
    private javax.swing.JButton btn_simpanformwalikelas;
    private javax.swing.JButton btn_simpanprofileadmin;
    private javax.swing.JButton btn_simpanprofileguru;
    private javax.swing.JButton btn_simpanprofilekelas;
    private javax.swing.JButton btn_simpanprofilesiswa;
    private javax.swing.JButton btn_simpanprofilewalikelas;
    private javax.swing.JButton btn_simpantambahsiswa;
    private javax.swing.JButton btn_tambahguru;
    private javax.swing.JButton btn_tambahsiswa;
    private javax.swing.JButton btn_tambahwalikelas;
    private javax.swing.JButton btn_updateabsen;
    private javax.swing.JButton btn_updatestatussiswabermasalah;
    private javax.swing.JComboBox<String> cb_gendersiswa;
    private javax.swing.JComboBox<String> cb_siswabermasalah;
    private javax.swing.JComboBox<String> cb_smtformkelas;
    private javax.swing.JComboBox<String> cb_smtprofilekelas;
    private javax.swing.JComboBox<String> cb_statusdataabsen;
    private javax.swing.JComboBox<String> cb_statusprofileadmin;
    private javax.swing.JComboBox<String> cb_walasformkelas;
    private javax.swing.JComboBox<String> cb_walasprofilekelas;
    private javax.swing.JLayeredPane dataabsensi;
    private javax.swing.JLayeredPane dataadmin;
    private javax.swing.JLayeredPane dataanggotakelas;
    private javax.swing.JLayeredPane dataguru;
    private javax.swing.JLayeredPane datakelas;
    private javax.swing.JLayeredPane datalapabsensi;
    private javax.swing.JLayeredPane datalapsiswamasalah;
    private javax.swing.JLayeredPane datasiswa;
    private javax.swing.JLayeredPane datawalikelas;
    private javax.swing.JLayeredPane form_guru;
    private javax.swing.JLayeredPane form_kelas;
    private javax.swing.JLayeredPane form_registrasi;
    private javax.swing.JLayeredPane form_siswa;
    private javax.swing.JLayeredPane form_walikelas;
    private javax.swing.JLabel icon_guru;
    private javax.swing.JLabel icon_walikelas;
    private javax.swing.JLabel iconabsen;
    private javax.swing.JLabel iconadmin;
    private javax.swing.JLabel icondashboard;
    private javax.swing.JLabel iconkelas;
    private javax.swing.JLabel iconlapabsen;
    private javax.swing.JLabel iconlogout;
    private javax.swing.JLabel iconsiswa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_alamatformguru;
    private javax.swing.JLabel lb_alamatformsiswa;
    private javax.swing.JLabel lb_alamatformwalikelas;
    private javax.swing.JLabel lb_alamatprofileguru;
    private javax.swing.JLabel lb_alamatprofilewalikelas;
    private javax.swing.JLabel lb_emailformguru;
    private javax.swing.JLabel lb_emailformsiswa;
    private javax.swing.JLabel lb_emailformwalikelas;
    private javax.swing.JLabel lb_emailprofileguru;
    private javax.swing.JLabel lb_emailprofilewalikelas;
    private javax.swing.JLabel lb_gambar;
    private javax.swing.JLabel lb_gambar1;
    private javax.swing.JLabel lb_gambar2;
    private javax.swing.JLabel lb_gambar3;
    private javax.swing.JLabel lb_guru;
    private javax.swing.JLabel lb_idabsen;
    private javax.swing.JLabel lb_idadminprofileadmin;
    private javax.swing.JLabel lb_idprofilewalikelas;
    private javax.swing.JLabel lb_idregadmin;
    private javax.swing.JLabel lb_idwalasanggotakelas;
    private javax.swing.JLabel lb_idwalasformsiswa;
    private javax.swing.JLabel lb_iwak;
    private javax.swing.JLabel lb_jabatanformguru;
    private javax.swing.JLabel lb_jabatanprofileguru;
    private javax.swing.JLabel lb_jkformguru;
    private javax.swing.JLabel lb_jkformsiswa;
    private javax.swing.JLabel lb_jkformwalikelas;
    private javax.swing.JLabel lb_jkprofileguru;
    private javax.swing.JLabel lb_jkprofilewalikelas;
    private javax.swing.JLabel lb_jmlprofilekelas;
    private javax.swing.JLabel lb_jurusanformkelas;
    private javax.swing.JLabel lb_jurusanprofilekelas;
    private javax.swing.JLabel lb_namaformguru;
    private javax.swing.JLabel lb_namaformkelas;
    private javax.swing.JLabel lb_namaformsiswa;
    private javax.swing.JLabel lb_namaformwalikelas;
    private javax.swing.JLabel lb_namaprofileadmin;
    private javax.swing.JLabel lb_namaprofileguru;
    private javax.swing.JLabel lb_namaprofilekelas;
    private javax.swing.JLabel lb_namaprofilewalikelas;
    private javax.swing.JLabel lb_namaregadmin;
    private javax.swing.JLabel lb_namariwayatsiswa;
    private javax.swing.JLabel lb_namars;
    private javax.swing.JLabel lb_nipformguru;
    private javax.swing.JLabel lb_nipformkelas;
    private javax.swing.JLabel lb_nipformwalikelas;
    private javax.swing.JLabel lb_nipformwalikelas1;
    private javax.swing.JLabel lb_nipprofileadmin;
    private javax.swing.JLabel lb_nipprofileguru;
    private javax.swing.JLabel lb_nipprofilekelas;
    private javax.swing.JLabel lb_nipprofilewalikelas;
    private javax.swing.JLabel lb_nipregadmin;
    private javax.swing.JLabel lb_nisanggotakelas;
    private javax.swing.JLabel lb_nisformsiswa;
    private javax.swing.JLabel lb_nisriwayatsiswa;
    private javax.swing.JLabel lb_nisrs;
    private javax.swing.JLabel lb_nkak;
    private javax.swing.JLabel lb_nkanggotakelas;
    private javax.swing.JLabel lb_nkformkelas;
    private javax.swing.JLabel lb_nkformsiswa;
    private javax.swing.JLabel lb_nkprofilekelas;
    private javax.swing.JLabel lb_nkprofilewalikelas;
    private javax.swing.JLabel lb_nkriwayatsiswa;
    private javax.swing.JLabel lb_nkrs;
    private javax.swing.JLabel lb_noortuformsiswa;
    private javax.swing.JLabel lb_nortuformsiswa;
    private javax.swing.JLabel lb_notelpformguru;
    private javax.swing.JLabel lb_notelpformwalikelas;
    private javax.swing.JLabel lb_notelpprofileguru;
    private javax.swing.JLabel lb_notelpprofilewalikelas;
    private javax.swing.JLabel lb_passprofileadmin;
    private javax.swing.JLabel lb_paswordregadmin;
    private javax.swing.JLabel lb_rfidformsiswa;
    private javax.swing.JLabel lb_sampairiwayatsiswa;
    private javax.swing.JLabel lb_smtformkelas;
    private javax.swing.JLabel lb_smtprofilekelas;
    private javax.swing.JLabel lb_statusprofileadmin;
    private javax.swing.JLabel lb_statusregadmin;
    private javax.swing.JLabel lb_taformkelas;
    private javax.swing.JLabel lb_taprofilekelas;
    private javax.swing.JLabel lb_telpformsiswa;
    private javax.swing.JLabel lb_tingkatanformkelas;
    private javax.swing.JLabel lb_tingkatprofilekelas;
    private javax.swing.JLabel lb_titik1;
    private javax.swing.JLabel lb_titik10;
    private javax.swing.JLabel lb_titik11;
    private javax.swing.JLabel lb_titik12;
    private javax.swing.JLabel lb_titik13;
    private javax.swing.JLabel lb_titik14;
    private javax.swing.JLabel lb_titik15;
    private javax.swing.JLabel lb_titik16;
    private javax.swing.JLabel lb_titik17;
    private javax.swing.JLabel lb_titik18;
    private javax.swing.JLabel lb_titik19;
    private javax.swing.JLabel lb_titik2;
    private javax.swing.JLabel lb_titik20;
    private javax.swing.JLabel lb_titik21;
    private javax.swing.JLabel lb_titik22;
    private javax.swing.JLabel lb_titik23;
    private javax.swing.JLabel lb_titik24;
    private javax.swing.JLabel lb_titik25;
    private javax.swing.JLabel lb_titik26;
    private javax.swing.JLabel lb_titik27;
    private javax.swing.JLabel lb_titik28;
    private javax.swing.JLabel lb_titik29;
    private javax.swing.JLabel lb_titik3;
    private javax.swing.JLabel lb_titik4;
    private javax.swing.JLabel lb_titik5;
    private javax.swing.JLabel lb_titik6;
    private javax.swing.JLabel lb_titik7;
    private javax.swing.JLabel lb_titik8;
    private javax.swing.JLabel lb_titik9;
    private javax.swing.JLabel lb_usenameprofileadmin;
    private javax.swing.JLabel lb_usernameregadmin;
    private javax.swing.JLabel lb_walikelas;
    private javax.swing.JLabel lbabsen;
    private javax.swing.JLabel lbdashboard;
    private javax.swing.JLabel lbkelas;
    private javax.swing.JLabel lbkelas1;
    private javax.swing.JLabel lblapabsen;
    private javax.swing.JLabel lblogout;
    private javax.swing.JLabel lbsiswa;
    private javax.swing.JLabel nisdataabsensi;
    private javax.swing.JPanel panel_chartanggotakelas;
    private javax.swing.JPanel panel_chartrs;
    private javax.swing.JPanel panelabsensi;
    private javax.swing.JPanel paneladmin;
    private javax.swing.JPanel panelanggotakelas;
    private javax.swing.JPanel panelbutton;
    private javax.swing.JPanel panelformguru;
    private javax.swing.JPanel panelformkelas;
    private javax.swing.JPanel panelformregistrasi;
    private javax.swing.JPanel panelformsiswa;
    private javax.swing.JPanel panelformwalikelas;
    private javax.swing.JPanel panelguru;
    private javax.swing.JPanel panelkelas;
    private javax.swing.JPanel panellapabsensi;
    private javax.swing.JPanel panelprofileadmin;
    private javax.swing.JPanel panelprofileguru;
    private javax.swing.JPanel panelprofilekelas;
    private javax.swing.JPanel panelprofilesiswa;
    private javax.swing.JPanel panelprofilewalikelas;
    private javax.swing.JPanel panelriwayatsiswa;
    private javax.swing.JPanel panelsiswa;
    private javax.swing.JPanel panelsiswabermasalah;
    private javax.swing.JPanel panelwalikelas;
    private javax.swing.JLayeredPane profileadmin;
    private javax.swing.JLayeredPane profileguru;
    private javax.swing.JLayeredPane profilekelas;
    private javax.swing.JLayeredPane profilesiswa;
    private javax.swing.JLayeredPane profilewalikelas;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JRadioButton rb5;
    private javax.swing.JRadioButton rb6;
    private javax.swing.JLayeredPane riwayat_siswa;
    private javax.swing.JLabel sampaidatabsensi;
    private javax.swing.JLabel saveadm;
    private javax.swing.JLabel saveadmkelas;
    private javax.swing.JLabel statusdatabsensi;
    private javax.swing.JScrollPane tab_anggotakelas;
    private javax.swing.JScrollPane tab_lapabsen;
    private javax.swing.JScrollPane tab_siswabermasalah;
    private javax.swing.JScrollPane tababsen;
    private javax.swing.JScrollPane tabadmin;
    private javax.swing.JTable tabel_absen;
    private javax.swing.JTable tabel_admin;
    private javax.swing.JTable tabel_anggotakelas;
    private javax.swing.JTable tabel_guru;
    private javax.swing.JTable tabel_kelas;
    private javax.swing.JTable tabel_lapabsen;
    private javax.swing.JTable tabel_riwayatsiswa;
    private javax.swing.JTable tabel_siswa;
    private javax.swing.JTable tabel_siswabermasalah;
    private javax.swing.JTable tabel_walikelas;
    private javax.swing.JScrollPane tabguru;
    private javax.swing.JScrollPane tabkelas;
    private javax.swing.JScrollPane tabriwayatsiswa;
    private javax.swing.JScrollPane tabsiswa;
    private javax.swing.JScrollPane tabwalikelas;
    private javax.swing.JLabel tgldataabsensi;
    private javax.swing.JTextField txt_alamatformguru;
    private javax.swing.JTextField txt_alamatformsiswa;
    private javax.swing.JTextField txt_alamatformwalikelas;
    private javax.swing.JTextField txt_alamatprofileguru;
    private javax.swing.JTextField txt_alamatprofilewalikelas;
    private javax.swing.JTextField txt_alamatsiswa;
    private javax.swing.JTextField txt_angkatanprofilekelas;
    private javax.swing.ButtonGroup txt_buttonJKguru;
    private javax.swing.ButtonGroup txt_buttonJKsiswa;
    private javax.swing.ButtonGroup txt_buttonJKwalikelas;
    private javax.swing.JTextField txt_emailformguru;
    private javax.swing.JTextField txt_emailformsiswa;
    private javax.swing.JTextField txt_emailformwalikelas;
    private javax.swing.JTextField txt_emailprofileguru;
    private javax.swing.JTextField txt_emailprofilewalikelas;
    private javax.swing.JTextField txt_emailsiswa;
    private javax.swing.JTextField txt_gendersiswa;
    private javax.swing.JTextField txt_idadminprofileadmin;
    private javax.swing.JTextField txt_idformwalikelas;
    private javax.swing.JTextField txt_idprofilewalikelas;
    private javax.swing.JTextField txt_idregadmin;
    private javax.swing.JTextField txt_idwalasformsiswa;
    private javax.swing.JTextField txt_jabatanformguru;
    private javax.swing.JTextField txt_jabatanprofileguru;
    private javax.swing.JTextField txt_jkprofileguru;
    private javax.swing.JTextField txt_jkprofilewalikelas;
    private javax.swing.JTextField txt_jmlprofilekelas;
    private javax.swing.JTextField txt_jurusanformkelas;
    private javax.swing.JTextField txt_jurusanprofilekelas;
    private javax.swing.JComboBox<String> txt_level;
    private javax.swing.JTextField txt_namaformguru;
    private javax.swing.JTextField txt_namaformkelas;
    private javax.swing.JTextField txt_namaformsiswa;
    private javax.swing.JTextField txt_namaformwalikelas;
    private javax.swing.JTextField txt_namaprofileadmin;
    private javax.swing.JTextField txt_namaprofileguru;
    private javax.swing.JTextField txt_namaprofilekelas;
    private javax.swing.JTextField txt_namaprofilewalikelas;
    private javax.swing.JTextField txt_namaregadmin;
    private javax.swing.JTextField txt_namasiswa;
    private javax.swing.JTextField txt_nipformguru;
    private javax.swing.JTextField txt_nipformwalikelas;
    private javax.swing.JTextField txt_nipprofileadmin;
    private javax.swing.JTextField txt_nipprofileguru;
    private javax.swing.JTextField txt_nipprofilewalikelas;
    private javax.swing.JTextField txt_nipregadmin;
    private javax.swing.JTextField txt_nisformsiswa;
    private javax.swing.JTextField txt_nissiswa;
    private javax.swing.JTextField txt_nkformkelas;
    private javax.swing.JTextField txt_nkformsiswa;
    private javax.swing.JTextField txt_nkprofilekelas;
    private javax.swing.JTextField txt_nkprofilewalikelas;
    private javax.swing.JTextField txt_nksiswa;
    private javax.swing.JTextField txt_noortuformsiswa;
    private javax.swing.JTextField txt_noortusiswa;
    private javax.swing.JTextField txt_nortuformsiswa;
    private javax.swing.JTextField txt_notelpprofileguru;
    private javax.swing.JTextField txt_notelpprofilewalikelas;
    private javax.swing.JTextField txt_notelpsiswa;
    private javax.swing.JTextField txt_ortusiswa;
    private javax.swing.JTextField txt_pass2profileadmin;
    private javax.swing.JPasswordField txt_passprofileadmin;
    private javax.swing.JPasswordField txt_passwordregadmin;
    private javax.swing.JTextField txt_rfidformsiswa;
    private javax.swing.JTextField txt_rfidsiswa;
    private javax.swing.JTextField txt_searchadmin;
    private javax.swing.JTextField txt_searchbynis;
    private javax.swing.JTextField txt_searchdataabsen;
    private javax.swing.JTextField txt_searchguru;
    private javax.swing.JTextField txt_searchkelas;
    private javax.swing.JTextField txt_searchnisanggotakelas;
    private javax.swing.JTextField txt_searchsiswa;
    private javax.swing.JTextField txt_searchtgl2dataabsen;
    private javax.swing.JTextField txt_searchtgldataabsen;
    private javax.swing.JTextField txt_searchwalikelas;
    private javax.swing.JTextField txt_siswabermasalah;
    private javax.swing.JTextField txt_smtprofilekelas;
    private javax.swing.JTextField txt_statusprofileadmin;
    private javax.swing.JTextField txt_taformkelas;
    private javax.swing.JTextField txt_tanggal2riwayatsiswa;
    private javax.swing.JTextField txt_tanggalriwayatsiswa;
    private javax.swing.JTextField txt_taprofilekelas;
    private javax.swing.JTextField txt_telpformsiswa;
    private javax.swing.JTextField txt_tglakhir;
    private javax.swing.JTextField txt_tingkatanformkelas;
    private javax.swing.JTextField txt_tlpformguru;
    private javax.swing.JTextField txt_tlpformwalikelas;
    private javax.swing.JTextField txt_usernameprofileadmin;
    private javax.swing.JTextField txt_usernameregadmin;
    private javax.swing.JTextField txt_walasprofilekelas;
    private javax.swing.JTextField txt_walassiswa;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
