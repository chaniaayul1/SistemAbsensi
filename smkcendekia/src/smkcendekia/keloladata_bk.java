/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    String sql;
    koneksi k;
    DefaultTableModel model;
    public boolean checkpanel;
    public keloladata_bk() {
        initComponents();
        if (checkpanel==true)
        {
            switchpanel(datasiswa);
        }
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        tampilsiswa();
        tampilguru();
        
        setExtendedState(MAXIMIZED_BOTH);
        this.toFront();
        //Setting font Header        
        
        //SETTING TRANSPARASI SCROLLPANE TABEL SISWA
        ((DefaultTableCellRenderer)table_siswa.getDefaultRenderer(Object.class)).setBackground(new Color(241,239,238,238));
        table_siswa.setGridColor(Color.BLACK);
        table_siswa.setForeground(Color.BLACK);
        tablesiswa.setOpaque(false);
        tablesiswa.setOpaque(false);
        tablesiswa.getViewport().setOpaque(false);
        tablesiswa.setBorder(null);
        tablesiswa.setViewportBorder(null);
        table_siswa.setShowGrid(true);
        
        //SETTING TRANPARANSI SCROLLPANE TABEL GURU
        ((DefaultTableCellRenderer)table_siswa.getDefaultRenderer(Object.class)).setBackground(new Color(241,239,238,238));
        tabel_guru.setGridColor(Color.BLACK);
        tabel_guru.setForeground(Color.BLACK);
        tabelguru.setOpaque(false);
        tabelguru.setOpaque(false);
        tabelguru.getViewport().setOpaque(false);
        tabelguru.setBorder(null);
        tabelguru.setViewportBorder(null);
        tabel_guru.setShowGrid(true);
        
    }

    //FUNGSI ATAU PROSEDUR MENAMPILKAN DATA SISWA PADA TABEL
    public void tampilsiswa(){        
        model = new DefaultTableModel ( );
        table_siswa.setModel(model);
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
            
             table_siswa.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    //VOID MENAMPILKAN DATA GURU PADA TABEL
    public void tampilguru(){
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("NK");
        model.addColumn("Nama");
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
    //PROSEDUR MENGHAPUS DATA SISWA PADA TABEL
     public void deletedata(){
          model = new DefaultTableModel ( );
        table_siswa.setModel(model);
        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("NK");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM siswa WHERE nis='"+txt_search.getText()+"'");
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
     
     //PROSEDUR MENGHAPUS DATA GURU PADA TABEL
     public void deletedataguru(){
        model = new DefaultTableModel ( );
        tabel_guru.setModel(model);
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("NK");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        try {
            stat = con.createStatement( );
            con.createStatement().executeUpdate("DELETE FROM guru WHERE nis='"+txt_searchguru.getText()+"'");
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
    //PROSEDUR MENCARI DATA SISWA PADA TABEL
     public void querysearch(){
        int row = table_siswa.getRowCount();
        for(int a=0;a<row;a++){
            model.removeRow(0);
        }
        
        try{
           stat = con.createStatement( );
           sql  = "Select * from siswa WHERE nis='"+txt_search.getText()+"'";
                   
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
     
    public void querysearchklik(){
        int i = table_siswa.getSelectedRow();
        if(i>-1){
            txt_search.setText(model.getValueAt(i, 1).toString());
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
        }
    }
    
    public void switchpanel(JLayeredPane panel)
    {
        LayerPane.removeAll();
        LayerPane.add(panel);
        LayerPane.repaint();
        LayerPane.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelUtama = new javax.swing.JPanel();
        PanelButton = new javax.swing.JPanel();
        btn_datasiswa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_dataguru = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_datakelas = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_dataabsensi = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_dataadmin = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_lapAbsen = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        LayerPane = new javax.swing.JLayeredPane();
        datasiswa = new javax.swing.JLayeredPane();
        panelsiswa = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_lihat = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_addsiswa = new javax.swing.JPanel();
        btn_kembali = new javax.swing.JButton();
        tablesiswa = new javax.swing.JScrollPane();
        table_siswa = new javax.swing.JTable();
        bgsiswa = new javax.swing.JLabel();
        dataguru = new javax.swing.JLayeredPane();
        panelguru = new javax.swing.JPanel();
        btn_addguru = new javax.swing.JPanel();
        txt_searchguru = new javax.swing.JTextField();
        btn_searchguru = new javax.swing.JButton();
        tabelguru = new javax.swing.JScrollPane();
        tabel_guru = new javax.swing.JTable();
        btn_kembaliguru = new javax.swing.JButton();
        btn_lihatguru = new javax.swing.JButton();
        brn_hapusguru = new javax.swing.JButton();
        bgguru = new javax.swing.JLabel();
        datakelas = new javax.swing.JLayeredPane();
        panelkelas = new javax.swing.JPanel();
        bgkelas = new javax.swing.JLabel();
        dataabsensi = new javax.swing.JLayeredPane();
        panelabsensi = new javax.swing.JPanel();
        bgabsensi = new javax.swing.JLabel();
        datalapabsensi = new javax.swing.JLayeredPane();
        panellapabsensi = new javax.swing.JPanel();
        bglapabsensi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelUtama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelButton.setBackground(new java.awt.Color(37, 112, 183));

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

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Siswa");

        javax.swing.GroupLayout btn_datasiswaLayout = new javax.swing.GroupLayout(btn_datasiswa);
        btn_datasiswa.setLayout(btn_datasiswaLayout);
        btn_datasiswaLayout.setHorizontalGroup(
            btn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_datasiswaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(86, 86, 86))
        );
        btn_datasiswaLayout.setVerticalGroup(
            btn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_datasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

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

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dashboard");

        javax.swing.GroupLayout btn_dashboardLayout = new javax.swing.GroupLayout(btn_dashboard);
        btn_dashboard.setLayout(btn_dashboardLayout);
        btn_dashboardLayout.setHorizontalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(88, 88, 88))
        );
        btn_dashboardLayout.setVerticalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Data Guru");

        javax.swing.GroupLayout btn_dataguruLayout = new javax.swing.GroupLayout(btn_dataguru);
        btn_dataguru.setLayout(btn_dataguruLayout);
        btn_dataguruLayout.setHorizontalGroup(
            btn_dataguruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dataguruLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(86, 86, 86))
        );
        btn_dataguruLayout.setVerticalGroup(
            btn_dataguruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dataguruLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        jLabel10.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Data Kelas");

        javax.swing.GroupLayout btn_datakelasLayout = new javax.swing.GroupLayout(btn_datakelas);
        btn_datakelas.setLayout(btn_datakelasLayout);
        btn_datakelasLayout.setHorizontalGroup(
            btn_datakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_datakelasLayout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(87, 87, 87))
        );
        btn_datakelasLayout.setVerticalGroup(
            btn_datakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_datakelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Data Absen");

        javax.swing.GroupLayout btn_dataabsensiLayout = new javax.swing.GroupLayout(btn_dataabsensi);
        btn_dataabsensi.setLayout(btn_dataabsensiLayout);
        btn_dataabsensiLayout.setHorizontalGroup(
            btn_dataabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dataabsensiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(80, 80, 80))
        );
        btn_dataabsensiLayout.setVerticalGroup(
            btn_dataabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dataabsensiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Logout");

        javax.swing.GroupLayout btn_logoutLayout = new javax.swing.GroupLayout(btn_logout);
        btn_logout.setLayout(btn_logoutLayout);
        btn_logoutLayout.setHorizontalGroup(
            btn_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_logoutLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_logoutLayout.setVerticalGroup(
            btn_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_logoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Data Admin");

        javax.swing.GroupLayout btn_dataadminLayout = new javax.swing.GroupLayout(btn_dataadmin);
        btn_dataadmin.setLayout(btn_dataadminLayout);
        btn_dataadminLayout.setHorizontalGroup(
            btn_dataadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dataadminLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(79, 79, 79))
        );
        btn_dataadminLayout.setVerticalGroup(
            btn_dataadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dataadminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btn_lapAbsen.setBackground(new java.awt.Color(37, 112, 183));
        btn_lapAbsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lapAbsenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_lapAbsenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_lapAbsenMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_lapAbsenMousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Data Laporan Absen");

        javax.swing.GroupLayout btn_lapAbsenLayout = new javax.swing.GroupLayout(btn_lapAbsen);
        btn_lapAbsen.setLayout(btn_lapAbsenLayout);
        btn_lapAbsenLayout.setHorizontalGroup(
            btn_lapAbsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_lapAbsenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(25, 25, 25))
        );
        btn_lapAbsenLayout.setVerticalGroup(
            btn_lapAbsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lapAbsenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_datasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_dataguru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_datakelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_dataabsensi, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
            .addComponent(btn_dataadmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_lapAbsen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_datasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dataguru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_datakelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dataadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dataabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lapAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        PanelUtama.add(PanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 257, 658));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(39, 113, 184));
        username.setText("INI USERNAME");
        PanelUtama.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 30, 190, 40));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/listmenu.png"))); // NOI18N
        PanelUtama.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        LayerPane.setOpaque(true);
        LayerPane.setLayout(new java.awt.CardLayout());

        datasiswa.setMinimumSize(new java.awt.Dimension(1366, 768));
        datasiswa.setOpaque(true);
        datasiswa.setPreferredSize(new java.awt.Dimension(1366, 768));
        datasiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsiswa.setLayout(null);

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        panelsiswa.add(txt_search);
        txt_search.setBounds(160, 110, 810, 30);

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_search);
        btn_search.setBounds(990, 110, 65, 30);

        btn_lihat.setText("Lihat Data");
        btn_lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_lihat);
        btn_lihat.setBounds(890, 570, 81, 23);

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_hapus);
        btn_hapus.setBounds(990, 570, 63, 23);

        javax.swing.GroupLayout btn_addsiswaLayout = new javax.swing.GroupLayout(btn_addsiswa);
        btn_addsiswa.setLayout(btn_addsiswaLayout);
        btn_addsiswaLayout.setHorizontalGroup(
            btn_addsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        btn_addsiswaLayout.setVerticalGroup(
            btn_addsiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelsiswa.add(btn_addsiswa);
        btn_addsiswa.setBounds(50, 110, 100, 30);

        btn_kembali.setText("Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        panelsiswa.add(btn_kembali);
        btn_kembali.setBounds(60, 570, 69, 23);

        table_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "NIS", "NK", "Nama", "Jenis Kelamin"
            }
        ));
        table_siswa.setRowHeight(20);
        table_siswa.setRowMargin(2);
        tablesiswa.setViewportView(table_siswa);

        panelsiswa.add(tablesiswa);
        tablesiswa.setBounds(50, 160, 1010, 340);

        bgsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panelsiswa.png"))); // NOI18N
        panelsiswa.add(bgsiswa);
        bgsiswa.setBounds(0, 0, 1107, 658);

        datasiswa.add(panelsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datasiswa, "card2");

        dataguru.setOpaque(true);
        dataguru.setPreferredSize(new java.awt.Dimension(1107, 658));
        dataguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout btn_addguruLayout = new javax.swing.GroupLayout(btn_addguru);
        btn_addguru.setLayout(btn_addguruLayout);
        btn_addguruLayout.setHorizontalGroup(
            btn_addguruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        btn_addguruLayout.setVerticalGroup(
            btn_addguruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panelguru.add(btn_addguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 30));
        panelguru.add(txt_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 790, 30));

        btn_searchguru.setText("Search");
        btn_searchguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_searchguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 100, -1, 30));

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
        tabelguru.setViewportView(tabel_guru);

        panelguru.add(tabelguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 1020, 370));

        btn_kembaliguru.setText("Kembali");
        btn_kembaliguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliguruActionPerformed(evt);
            }
        });
        panelguru.add(btn_kembaliguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 110, -1));

        btn_lihatguru.setText("Lihat Data");
        panelguru.add(btn_lihatguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 570, 110, -1));

        brn_hapusguru.setText("Hapus");
        brn_hapusguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_hapusguruActionPerformed(evt);
            }
        });
        panelguru.add(brn_hapusguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 570, 80, -1));

        bgguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panelguru.png"))); // NOI18N
        panelguru.add(bgguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataguru.add(panelguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataguru, "card3");

        datakelas.setOpaque(true);
        datakelas.setPreferredSize(new java.awt.Dimension(1107, 658));
        datakelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panelkelas.png"))); // NOI18N
        panelkelas.add(bgkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datakelas.add(panelkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datakelas, "card4");

        dataabsensi.setOpaque(true);
        dataabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        dataabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panelabsensi.png"))); // NOI18N
        panelabsensi.add(bgabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dataabsensi.add(panelabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(dataabsensi, "card4");

        datalapabsensi.setOpaque(true);
        datalapabsensi.setPreferredSize(new java.awt.Dimension(1107, 658));
        datalapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panellapabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bglapabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/panellapabsensi.png"))); // NOI18N
        panellapabsensi.add(bglapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        datalapabsensi.add(panellapabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1107, 658));

        LayerPane.add(datalapabsensi, "card4");

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
        // TODO add your handling code here:
        switchpanel(datasiswa);
    }//GEN-LAST:event_btn_datasiswaMouseClicked

    private void btn_dataguruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseClicked
        // TODO add your handling code here:
         switchpanel(dataguru);
    }//GEN-LAST:event_btn_dataguruMouseClicked

    private void btn_datakelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseClicked
        // TODO add your handling code here:
         switchpanel(datakelas);
    }//GEN-LAST:event_btn_datakelasMouseClicked

    private void btn_dataabsensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseClicked
        // TODO add your handling code here:
         switchpanel(dataabsensi);
    }//GEN-LAST:event_btn_dataabsensiMouseClicked

    private void btn_lapAbsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapAbsenMouseClicked
        // TODO add your handling code here:
         switchpanel(datalapabsensi);
    }//GEN-LAST:event_btn_lapAbsenMouseClicked

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        // TODO add your handling code here:
        btn_dashboard.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dashboardMouseEntered

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
        // TODO add your handling code here:
        btn_dashboard.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMousePressed
        // TODO add your handling code here:
        btn_dashboard.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dashboardMousePressed

    private void btn_datasiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMouseEntered
        // TODO add your handling code here:
        btn_datasiswa.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_datasiswaMouseEntered

    private void btn_datasiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMouseExited
        // TODO add your handling code here:
        btn_datasiswa.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_datasiswaMouseExited

    private void btn_datasiswaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datasiswaMousePressed
        // TODO add your handling code here:
        btn_datasiswa.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_datasiswaMousePressed

    private void btn_dataguruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseEntered
        // TODO add your handling code here:
        btn_dataguru.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataguruMouseEntered

    private void btn_dataguruMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMouseExited
        // TODO add your handling code here:
        btn_dataguru.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataguruMouseExited

    private void btn_dataguruMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataguruMousePressed
        // TODO add your handling code here:
        btn_dataguru.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataguruMousePressed

    private void btn_datakelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseEntered
        // TODO add your handling code here:
        btn_datakelas.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_datakelasMouseEntered

    private void btn_datakelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMouseExited
        // TODO add your handling code here:
        btn_datakelas.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_datakelasMouseExited

    private void btn_datakelasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_datakelasMousePressed
        // TODO add your handling code here:
        btn_datakelas.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_datakelasMousePressed

    private void btn_dataabsensiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseEntered
        // TODO add your handling code here:
        btn_dataabsensi.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataabsensiMouseEntered

    private void btn_dataabsensiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMouseExited
        // TODO add your handling code here:
        btn_dataabsensi.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataabsensiMouseExited

    private void btn_dataabsensiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataabsensiMousePressed
        // TODO add your handling code here:
        btn_dataabsensi.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataabsensiMousePressed

    private void btn_lapAbsenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapAbsenMouseEntered
        // TODO add your handling code here:
        btn_lapAbsen.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_lapAbsenMouseEntered

    private void btn_lapAbsenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapAbsenMouseExited
        // TODO add your handling code here:
        btn_lapAbsen.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_lapAbsenMouseExited

    private void btn_lapAbsenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lapAbsenMousePressed
        // TODO add your handling code here:
        btn_lapAbsen.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_lapAbsenMousePressed

    private void btn_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseEntered
        // TODO add your handling code here:
        btn_logout.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_logoutMouseEntered

    private void btn_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseExited
        // TODO add your handling code here:
        btn_logout.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_logoutMouseExited

    private void btn_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMousePressed
        // TODO add your handling code here:
        btn_logout.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_logoutMousePressed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
         this.querysearch();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_lihatActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        this.deletedata();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void btn_kembaliguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliguruActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliguruActionPerformed

    private void brn_hapusguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_hapusguruActionPerformed
        // TODO add your handling code here:
        deletedataguru();
    }//GEN-LAST:event_brn_hapusguruActionPerformed

    private void btn_searchguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchguruActionPerformed
        // TODO add your handling code here:
        querysearchguru();
    }//GEN-LAST:event_btn_searchguruActionPerformed

    private void btn_dataadminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseEntered
        // TODO add your handling code here:
         btn_dataadmin.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_dataadminMouseEntered

    private void btn_dataadminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseExited
        // TODO add your handling code here:
        btn_dataadmin.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_dataadminMouseExited

    private void btn_dataadminMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMousePressed
        // TODO add your handling code here:
        btn_dataadmin.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_dataadminMousePressed

    private void btn_dataadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataadminMouseClicked
        // TODO add your handling code here:
        //harhaswitchpanel(dataadmin);
    }//GEN-LAST:event_btn_dataadminMouseClicked

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
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelUtama;
    private javax.swing.JLabel bgabsensi;
    private javax.swing.JLabel bgguru;
    private javax.swing.JLabel bgkelas;
    private javax.swing.JLabel bglapabsensi;
    private javax.swing.JLabel bgsiswa;
    private javax.swing.JButton brn_hapusguru;
    private javax.swing.JPanel btn_addguru;
    private javax.swing.JPanel btn_addsiswa;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JPanel btn_dataabsensi;
    private javax.swing.JPanel btn_dataadmin;
    private javax.swing.JPanel btn_dataguru;
    private javax.swing.JPanel btn_datakelas;
    private javax.swing.JPanel btn_datasiswa;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_kembaliguru;
    private javax.swing.JPanel btn_lapAbsen;
    private javax.swing.JButton btn_lihat;
    private javax.swing.JButton btn_lihatguru;
    private javax.swing.JPanel btn_logout;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_searchguru;
    private javax.swing.JLayeredPane dataabsensi;
    private javax.swing.JLayeredPane dataguru;
    private javax.swing.JLayeredPane datakelas;
    private javax.swing.JLayeredPane datalapabsensi;
    private javax.swing.JLayeredPane datasiswa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panelabsensi;
    private javax.swing.JPanel panelguru;
    private javax.swing.JPanel panelkelas;
    private javax.swing.JPanel panellapabsensi;
    private javax.swing.JPanel panelsiswa;
    private javax.swing.JTable tabel_guru;
    private javax.swing.JScrollPane tabelguru;
    private javax.swing.JTable table_siswa;
    private javax.swing.JScrollPane tablesiswa;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_searchguru;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
