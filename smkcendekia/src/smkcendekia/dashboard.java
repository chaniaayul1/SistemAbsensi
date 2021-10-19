/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author chani
 */
public class dashboard extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs,rs1,rs2;
    String sql;
    String tanggalpert,tanggalakh;
    //perhari
    int hadirhrotkp,sakithrotkp,izinhrotkp,alphahrotkp,terlambathrotkp = 0;
    int hadirhrtitl,sakithrtitl,izinhrtitl,alphahrtitl,terlambathrtitl = 0;
    int hadirhrbdp,sakithrbdp,izinhrbdp,alphahrbdp,terlambathrbdp = 0;
    //mingguan
    int hadirmgotkp,sakitmgotkp,izinmgotkp,alphamgotkp,terlambatmgotkp = 0;
    int hadirmgtitl,sakitmgtitl,izinmgtitl,alphamgtitl,terlambatmgtitl = 0;
    int hadirmgbdp,sakitmgbdp,izinmgbdp,alphamgbdp,terlambatmgbdp = 0;
    //bulanan
    int hadirblnotkp,sakitblnotkp,izinblnotkp,alphablnotkp,terlambatblnotkp = 0;
    int hadirblntitl,sakitblntitl,izinblntitl,alphablntitl,terlambatblntitl = 0;
    int hadirblnbdp,sakitblnbdp,izinblnbdp,alphablnbdp,terlambatblnbdp = 0;
    
    int allhadir,allsakit,allizin,allalpha,allterlambat = 0;
    int hadir10,sakit10,izin10,alpha10,terlambat10 = 0;
    int hadir11,sakit11,izin11,alpha11,terlambat11 = 0;
    int hadir12,sakit12,izin12,alpha12,terlambat12 = 0;
    int hadirtitl,sakittitl,izintitl,alphatitl,terlambattitl=0;
    int hadirbdp,sakitbdp,izinbdp,alphabdp,terlambatbdp ;
    int hadirotkp,sakitotkp,izinotkp,alphaotkp,terlambatotkp = 0;
    //
    String totalhadir, totalizin, totalalpha, totalterlambat, totalsakit;
    public dashboard() {
        initComponents();       
        this.username.setText(Session.getusername());
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        //RUN QUERY DEFAULT
        defaulttanggal();
        tanggal();
        tampil_jam();
        
        
        //QUERY TOTAL SISWA,GURU,KELAS
        queryjumlahsiswa();
        queryjumlahguru();
        queryjumlahkelas();
        
        //ABSENSI SISWA HARIAN
        dataabsensisiswaharianbdp();
        dataabsensisiswahariantitl();
        dataabsensisiswaharianotkp();
        chartsemuasiswaharian();
        
        //ABSENSI SISWA PER MINGGU
        dataabsensisiswamingguanbdp();
        dataabsensisiswamingguantitl();
        dataabsensisiswamingguanotkp();
        chartsemuasiswamingguan();
        
        //ABSENSI SISWA PER BULAN
        dataabsensisiswabulananbdp();
        dataabsensisiswabulanantitl();
        dataabsensisiswabulananotkp();
        chartsemuasiswabulanan();
        
        
        //ABSENSI SISWA
        dataabsensisiswa();
        chartsemuasiswa();
        
        //ABSENSI ANGKATAN
        dataabsensiangkatan10();
        dataabsensiangkatan11();
        dataabsensiangkatan12();
        chartangkatan();
        
        //ABSENSI JURUSAN
        dataabsensijurusantitl();
        dataabsensijurusanbdp();
        dataabsensijurusanotkp();
        chartjurusan();
    }

    public void tampil_jam(){
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nol_jam = "", nol_menit = "", nol_detik="";
                
                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();
                                            
                if (nilai_jam <=3) nol_jam = "0";
                if(nilai_menit <=9) nol_menit = "0";
                if(nilai_detik <= 9) nol_detik = "0";
                
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik+ Integer.toString(nilai_detik);
                
                txt_jam.setText(jam+":"+menit+":"+detik);
            }
        };
        new Timer(1000, taskPerformer).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        paneldashboard = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        paneljumlahkelas = new javax.swing.JPanel();
        lb_jumlahkelas = new javax.swing.JLabel();
        Paneljumlahguru = new javax.swing.JPanel();
        lb_jumlahguru = new javax.swing.JLabel();
        Paneljumlahsiswa = new javax.swing.JPanel();
        lb_jumlahsiswa = new javax.swing.JLabel();
        Scrollpane = new javax.swing.JScrollPane();
        panelgrafik = new javax.swing.JPanel();
        chartabsensiharian = new javax.swing.JPanel();
        chartabsensijurusan = new javax.swing.JPanel();
        chartabsensiangkatan = new javax.swing.JPanel();
        chartabsensisiswa = new javax.swing.JPanel();
        chartabsensimingguan = new javax.swing.JPanel();
        chartabsensibulanan = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lb_sjt1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lb_ajt1 = new javax.swing.JLabel();
        lb_hjt1 = new javax.swing.JLabel();
        lb_tjt1 = new javax.swing.JLabel();
        lb_ijt1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        lb_sjo1 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        lb_ajo1 = new javax.swing.JLabel();
        lb_hjo1 = new javax.swing.JLabel();
        lb_tjo1 = new javax.swing.JLabel();
        lb_ijo1 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        lb_sjb1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lb_ajb1 = new javax.swing.JLabel();
        lb_hjb1 = new javax.swing.JLabel();
        lb_tjb1 = new javax.swing.JLabel();
        lb_ijb1 = new javax.swing.JLabel();
        panel_waktu = new javax.swing.JPanel();
        lbl_tanggal = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        lbl_waktu = new javax.swing.JLabel();
        txt_jam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_siswabermasalah = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_kelolaabsen = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        paneldashboard.setFocusable(false);
        paneldashboard.setOpaque(false);
        paneldashboard.setPreferredSize(new java.awt.Dimension(1366, 768));
        paneldashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("INI USERNAME");
        paneldashboard.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 35, 190, 40));

        paneljumlahkelas.setBackground(new java.awt.Color(227, 162, 21));
        paneljumlahkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahkelas.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahkelas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahkelas.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahkelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paneljumlahkelas.add(lb_jumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(paneljumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 260, 50));

        Paneljumlahguru.setBackground(new java.awt.Color(0, 179, 131));
        Paneljumlahguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahguru.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahguru.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahguru.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahguru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Paneljumlahguru.add(lb_jumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(Paneljumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 260, 50));

        Paneljumlahsiswa.setBackground(new java.awt.Color(162, 32, 195));
        Paneljumlahsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahsiswa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahsiswa.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahsiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Paneljumlahsiswa.add(lb_jumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(Paneljumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, 50));

        Scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        Scrollpane.setBorder(null);
        Scrollpane.setOpaque(false);

        panelgrafik.setBackground(new java.awt.Color(255, 255, 255));
        panelgrafik.setPreferredSize(new java.awt.Dimension(930, 2791));
        panelgrafik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharian.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiharian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensiharian.setLayout(new javax.swing.OverlayLayout(chartabsensiharian));
        panelgrafik.add(chartabsensiharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        chartabsensijurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensijurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensijurusan.setLayout(new javax.swing.OverlayLayout(chartabsensijurusan));
        panelgrafik.add(chartabsensijurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1890, 660, 430));

        chartabsensiangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiangkatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensiangkatan.setLayout(new javax.swing.OverlayLayout(chartabsensiangkatan));
        panelgrafik.add(chartabsensiangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 2340, 660, 430));

        chartabsensisiswa.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensisiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensisiswa.setLayout(new javax.swing.OverlayLayout(chartabsensisiswa));
        panelgrafik.add(chartabsensisiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1440, 660, 430));

        chartabsensimingguan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensimingguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensimingguan.setLayout(new javax.swing.OverlayLayout(chartabsensimingguan));
        panelgrafik.add(chartabsensimingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 660, 430));

        chartabsensibulanan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensibulanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensibulanan.setLayout(new javax.swing.OverlayLayout(chartabsensibulanan));
        panelgrafik.add(chartabsensibulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 990, 660, 430));

        jPanel17.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel22.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel23.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel24.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel25.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel30.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel30.setText("Terlambat");
        panelgrafik.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_sjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel32.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel32.setText("Sakit");
        panelgrafik.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel33.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel33.setText("Izin");
        panelgrafik.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel34.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel34.setText("Alpha");
        panelgrafik.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel35.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel35.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelgrafik.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel36.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel36.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelgrafik.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 284, -1, -1));

        jLabel37.setText(":");
        panelgrafik.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel38.setText(":");
        panelgrafik.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel39.setText(":");
        panelgrafik.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel40.setText(":");
        panelgrafik.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel41.setText(":");
        panelgrafik.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel52.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel52.setText("Hadir");
        panelgrafik.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_ajt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ajt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_hjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_tjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ijt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ijt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel18.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel26.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel27.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel28.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel29.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel31.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel31.setText("Terlambat");
        panelgrafik.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_sjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel53.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel53.setText("Sakit");
        panelgrafik.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel54.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel54.setText("Izin");
        panelgrafik.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel55.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel55.setText("Alpha");
        panelgrafik.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel56.setText(":");
        panelgrafik.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel57.setText(":");
        panelgrafik.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel58.setText(":");
        panelgrafik.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel59.setText(":");
        panelgrafik.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel60.setText(":");
        panelgrafik.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel61.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel61.setText("Hadir");
        panelgrafik.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_ajo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ajo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_hjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_tjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_tjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_ijo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ijo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel19.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel30.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel31.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel32.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel33.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelgrafik.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel47.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel47.setText("Terlambat");
        panelgrafik.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_sjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel48.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel48.setText("Sakit");
        panelgrafik.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel49.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel49.setText("Izin");
        panelgrafik.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel50.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel50.setText("Alpha");
        panelgrafik.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel51.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel51.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelgrafik.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel62.setText(":");
        panelgrafik.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel63.setText(":");
        panelgrafik.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel64.setText(":");
        panelgrafik.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel65.setText(":");
        panelgrafik.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel66.setText(":");
        panelgrafik.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel67.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel67.setText("Hadir");
        panelgrafik.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_ajb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ajb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_hjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_tjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_tjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_ijb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelgrafik.add(lb_ijb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        Scrollpane.setViewportView(panelgrafik);

        paneldashboard.add(Scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 950, 480));

        panel_waktu.setBackground(new java.awt.Color(255, 255, 255));
        panel_waktu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panel_waktu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        lbl_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tanggal.setText("Tanggal");
        lbl_tanggal.setToolTipText("");
        panel_waktu.add(lbl_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 390, -1));

        txt_tanggal.setBackground(new java.awt.Color(102, 255, 102));
        txt_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 270, 40));

        lbl_waktu.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        lbl_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_waktu.setText("Waktu ");
        panel_waktu.add(lbl_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 380, -1));

        txt_jam.setBackground(new java.awt.Color(255, 51, 51));
        txt_jam.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        txt_jam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 270, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_jam.png"))); // NOI18N
        panel_waktu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_calender.png"))); // NOI18N
        panel_waktu.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        jLabel13.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("<html> Jl. Raya Batujajar, Batujajar Barat, Kecamatan Batujajar, Kabupaten Bandung Barat, <br> Jawa Barat <br> 40561  </html>");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_waktu.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 320, 70));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_location.png"))); // NOI18N
        panel_waktu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 40, 40));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_telepon.png"))); // NOI18N
        panel_waktu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 40, 40));

        jLabel10.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("(022) 6867255");
        panel_waktu.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 140, 40));

        paneldashboard.add(panel_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 390, 640));

        btn_keluar.setBackground(new java.awt.Color(37, 112, 183));
        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_keluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_keluarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_keluarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_keluarMouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Logout");

        javax.swing.GroupLayout btn_keluarLayout = new javax.swing.GroupLayout(btn_keluar);
        btn_keluar.setLayout(btn_keluarLayout);
        btn_keluarLayout.setHorizontalGroup(
            btn_keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        btn_keluarLayout.setVerticalGroup(
            btn_keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_keluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        paneldashboard.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 690, 290, 50));

        btn_siswabermasalah.setBackground(new java.awt.Color(37, 112, 183));
        btn_siswabermasalah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_siswabermasalahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_siswabermasalahMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_siswabermasalahMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_siswabermasalahMouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Kelola Absen");

        javax.swing.GroupLayout btn_siswabermasalahLayout = new javax.swing.GroupLayout(btn_siswabermasalah);
        btn_siswabermasalah.setLayout(btn_siswabermasalahLayout);
        btn_siswabermasalahLayout.setHorizontalGroup(
            btn_siswabermasalahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        btn_siswabermasalahLayout.setVerticalGroup(
            btn_siswabermasalahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_siswabermasalahLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        paneldashboard.add(btn_siswabermasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 690, 290, 50));

        btn_kelolaabsen.setBackground(new java.awt.Color(37, 112, 183));
        btn_kelolaabsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kelolaabsenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kelolaabsenMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_kelolaabsenMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_kelolaabsenMouseReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Laporan Siswa Bermasalah");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/iconlapmasalah.png"))); // NOI18N

        javax.swing.GroupLayout btn_kelolaabsenLayout = new javax.swing.GroupLayout(btn_kelolaabsen);
        btn_kelolaabsen.setLayout(btn_kelolaabsenLayout);
        btn_kelolaabsenLayout.setHorizontalGroup(
            btn_kelolaabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_kelolaabsenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(21, 21, 21))
        );
        btn_kelolaabsenLayout.setVerticalGroup(
            btn_kelolaabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_kelolaabsenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        paneldashboard.add(btn_kelolaabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 300, 50));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard.png"))); // NOI18N
        paneldashboard.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneldashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneldashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void tanggal(){
        
        Date dates = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        txt_tanggal.setText(s.format(dates));
    }
    
    public void queryjumlahsiswa(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT COUNT(*) from siswa";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("COUNT(*)");
                lb_jumlahsiswa.setText((String) obj[0]+" SISWA");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void queryjumlahguru(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT COUNT(*) from guru";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("COUNT(*)");
                lb_jumlahguru.setText((String) obj[0]+" GURU");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void queryjumlahkelas(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT COUNT(*) from kelas";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
                Object[ ] obj = new Object[1];
                obj[0] = rs.getString("COUNT(*)");
                lb_jumlahkelas.setText((String) obj[0]+" KELAS");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void defaulttanggal(){
        try{
           stat = con.createStatement( );
           sql  = "Select * from semester order by idsemester desc";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[ ] obj = new Object[1];
                tanggalpert=rs.getString("tanggalpertama");
                tanggalakh=rs.getString("tanggalterakhir");
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void notifikasimingguan(){
        
    }
    
    public void notifikasialphasemester(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT * FROM lapabsen WHERE alpha>=3 ";
           rs   = stat.executeQuery(sql);
           if (rs.next() ) {    
                JOptionPane.showMessageDialog(null, ("Terdapat siswa bermasalah dengan absensi"), 
                    "Lihat Data Absensi Gagal", JOptionPane.INFORMATION_MESSAGE);
            }
           else{
           }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    // CHART SEMUA SISWA HARIAN
    public void dataabsensisiswaharianotkp(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirhrotkp=hadirhrotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakithrotkp=sakithrotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinhrotkp=izinhrotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphahrotkp=alphahrotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambathrotkp=terlambathrotkp+1;
               }
            }
           totalhadir = Integer.toString(hadirhrotkp);
           totalsakit = Integer.toString(sakithrotkp);
           totalizin  = Integer.toString(izinhrotkp);
           totalalpha = Integer.toString(alphahrotkp);
           totalterlambat = Integer.toString(terlambathrotkp);
           lb_hjo1.setText(totalhadir);
           lb_sjo1.setText(totalsakit);
           lb_ijo1.setText(totalizin);
           lb_ajo1.setText(totalalpha);
           lb_tjo1.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswaharianbdp(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%BDP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirhrbdp=hadirhrbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakithrbdp=sakithrbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinhrbdp=izinhrbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphahrbdp=alphahrbdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambathrbdp=terlambathrbdp+1;
               }
            }
           totalhadir = Integer.toString(hadirhrbdp);
           totalsakit = Integer.toString(sakithrbdp);
           totalizin  = Integer.toString(izinhrbdp);
           totalalpha = Integer.toString(alphahrbdp);
           totalterlambat = Integer.toString(terlambathrbdp);
           lb_hjb1.setText(totalhadir);
           lb_sjb1.setText(totalsakit);
           lb_ijb1.setText(totalizin);
           lb_ajb1.setText(totalalpha);
           lb_tjb1.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswahariantitl(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%TITL%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirhrtitl=hadirhrtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakithrtitl=sakithrtitl+1;
               }
               else if(obj[0].equals("Izin")){
                   izinhrtitl=izinhrtitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphahrtitl=alphahrtitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambathrtitl=terlambathrtitl+1;
               }
            }
           totalhadir = Integer.toString(hadirhrtitl);
           totalsakit = Integer.toString(sakithrtitl);
           totalizin  = Integer.toString(izinhrtitl);
           totalalpha = Integer.toString(alphahrtitl);
           totalterlambat = Integer.toString(terlambathrtitl);
           lb_hjt1.setText(totalhadir);
           lb_sjt1.setText(totalsakit);
           lb_ijt1.setText(totalizin);
           lb_ajt1.setText(totalalpha);
           lb_tjt1.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuasiswaharian(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadirhrtitl, "Hadir", "TITL");
        ctdata.setValue(sakithrtitl, "Sakit", "TITL");
        ctdata.setValue(izinhrtitl, "Izin", "TITL");
        ctdata.setValue(alphahrtitl, "Alpha", "TITL");
        ctdata.setValue(terlambathrtitl, "Terlambat", "TITL");
        ctdata.setValue(hadirhrbdp, "Hadir", "BDP");
        ctdata.setValue(sakithrbdp, "Sakit", "BDP");
        ctdata.setValue(izinhrbdp, "Izin", "BDP");
        ctdata.setValue(alphahrbdp, "Alpha", "BDP");
        ctdata.setValue(terlambathrbdp, "Terlambat", "BDP");
        ctdata.setValue(hadirhrotkp, "Hadir", "OTKP");
        ctdata.setValue(sakithrotkp, "Sakit", "OTKP");
        ctdata.setValue(izinhrotkp, "Izin", "OTKP");
        ctdata.setValue(alphahrotkp, "Alpha", "OTKP");
        ctdata.setValue(terlambathrotkp, "Terlambat", "OTKP");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Hari ini", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
        chart.getPlot().setBackgroundPaint( Color.white);
        ChartPanel barChartPanel=new ChartPanel(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        chartabsensiharian.removeAll();
        chartabsensiharian.add(barChartPanel);
        chartabsensiharian.validate();
    }
    
    // CHART SEMUA SISWA MINGGUAN
    public void dataabsensisiswamingguanotkp(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirmgotkp=hadirmgotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitmgotkp=sakitmgotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinmgotkp=izinmgotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphamgotkp=alphamgotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatmgotkp=terlambatmgotkp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void dataabsensisiswamingguanbdp(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%BDP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirmgbdp=hadirmgbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitmgbdp=sakitmgbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinmgbdp=izinmgbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphamgbdp=alphamgbdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatmgbdp=terlambatmgbdp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public void dataabsensisiswamingguantitl(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%TITL%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirmgtitl=hadirmgtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitmgtitl=sakitmgtitl+1;
               }
               else if(obj[0].equals("Izin")){
                   izinmgtitl=izinmgtitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphamgtitl=alphamgtitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatmgtitl=terlambatmgtitl+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public void chartsemuasiswamingguan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadirmgtitl, "Hadir", "TITL");
        ctdata.setValue(sakitmgtitl, "Sakit", "TITL");
        ctdata.setValue(izinmgtitl, "Izin", "TITL");
        ctdata.setValue(alphamgtitl, "Alpha", "TITL");
        ctdata.setValue(terlambatmgtitl, "Terlambat", "TITL");
        ctdata.setValue(hadirmgbdp, "Hadir", "BDP");
        ctdata.setValue(sakitmgbdp, "Sakit", "BDP");
        ctdata.setValue(izinmgbdp, "Izin", "BDP");
        ctdata.setValue(alphamgbdp, "Alpha", "BDP");
        ctdata.setValue(terlambatmgbdp, "Terlambat", "BDP");
        ctdata.setValue(hadirmgotkp, "Hadir", "OTKP");
        ctdata.setValue(sakitmgotkp, "Sakit", "OTKP");
        ctdata.setValue(izinmgotkp, "Izin", "OTKP");
        ctdata.setValue(alphamgotkp, "Alpha", "OTKP");
        ctdata.setValue(terlambatmgotkp, "Terlambat", "OTKP");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Minggu ini", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, false);
        chart.getPlot().setBackgroundPaint( Color.white);
        ChartPanel barChartPanel=new ChartPanel(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        chartabsensimingguan.removeAll();
        chartabsensimingguan.add(barChartPanel);
        chartabsensimingguan.setVisible(true);
        chartabsensimingguan.validate();
        
    }
    
    
    //CHART ABSEN PER BULAN 
    public void dataabsensisiswabulananotkp(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE tanggal between DATE_FORMAT(CURDATE(),'%Y-%M-01') AND CURDATE() AND nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirblnotkp=hadirblnotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitblnotkp=sakitblnotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinblnotkp=izinblnotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphablnotkp=alphablnotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatblnotkp=terlambatblnotkp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public void dataabsensisiswabulananbdp(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE tanggal between DATE_FORMAT(CURDATE(),'%Y-%M-01') AND CURDATE() AND nk LIKE '%BDP%'";;
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirblnbdp=hadirblnbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitblnbdp=sakitblnbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinblnbdp=izinblnbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphablnbdp=alphablnbdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatblnbdp=terlambatblnbdp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }    
    
    
    public void dataabsensisiswabulanantitl(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE tanggal between DATE_FORMAT(CURDATE(),'%Y-%M-01') AND CURDATE() AND nk LIKE '%TITL%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirblntitl=hadirblntitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitblntitl=sakitblntitl+1;
               }
               else if(obj[0].equals("Izin")){
                   izinblntitl=izinblntitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphablntitl=alphablntitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatblntitl=terlambatblntitl+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public void chartsemuasiswabulanan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadirblntitl, "Hadir", "TITL");
        ctdata.setValue(sakitblntitl, "Sakit", "TITL");
        ctdata.setValue(izinblntitl, "Izin", "TITL");
        ctdata.setValue(alphablntitl, "Alpha", "TITL");
        ctdata.setValue(terlambatblntitl, "Terlambat", "TITL");
        ctdata.setValue(hadirblnbdp, "Hadir", "BDP");
        ctdata.setValue(sakitblnbdp, "Sakit", "BDP");
        ctdata.setValue(izinblnbdp, "Izin", "BDP");
        ctdata.setValue(alphablnbdp, "Alpha", "BDP");
        ctdata.setValue(terlambatblnbdp, "Terlambat", "BDP");
        ctdata.setValue(hadirblnotkp, "Hadir", "OTKP");
        ctdata.setValue(sakitblnotkp, "Sakit", "OTKP");
        ctdata.setValue(izinblnotkp, "Izin", "OTKP");
        ctdata.setValue(alphablnotkp, "Alpha", "OTKP");
        ctdata.setValue(terlambatblnotkp, "Terlambat", "OTKP");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Bulan ini", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
        chart.getPlot().setBackgroundPaint( Color.white);
        ChartPanel barChartPanel=new ChartPanel(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        chartabsensibulanan.removeAll();
        chartabsensibulanan.add(barChartPanel);
        chartabsensibulanan.validate();
    }
    
    
    // CHART SEMUA SISWA
    public void dataabsensisiswa(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   allhadir=allhadir+1;
               }
               else if(obj[0].equals("Sakit")){
                   allsakit=allsakit+1;
               }
               else if(obj[0].equals("Izin")){
                   allizin=allizin+1;
               }
               else if(obj[0].equals("Alpha")){
                   allalpha=allalpha+1;
               }
               else if(obj[0].equals("Terlambat")){
                   allterlambat=allterlambat+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
                             
    }
        
    public void chartsemuasiswa(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(allhadir, "Hadir", "Kehadiran Siswa");
        ctdata.setValue(allsakit, "Sakit", "Kehadiran Siswa");
        ctdata.setValue(allizin, "Izin", "Kehadiran Siswa");
        ctdata.setValue(allalpha, "Alpha", "Kehadiran Siswa");
        ctdata.setValue(allterlambat, "Terlambat", "Kehadiran Siswa");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Semester ini", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
        chart.getPlot().setBackgroundPaint( Color.white);
        ChartPanel barChartPanel=new ChartPanel(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        chartabsensisiswa.removeAll();
        chartabsensisiswa.add(barChartPanel);
        chartabsensisiswa.validate();
    }
    
    // CHART ANGKATAN
    public void dataabsensiangkatan10(){
        try{
           stat = con.createStatement( );
            sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND substr(nk, 1, 2) = '10'";
           rs   = stat.executeQuery(sql);
           
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadir10=hadir10+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakit10=sakit10+1;
               }
               else if(obj[0].equals("Izin")){
                   izin10=izin10+1;
               }
               else if(obj[0].equals("Alpha")){
                   alpha10=alpha10+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambat10=terlambat10+1;
               }
               
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensiangkatan11(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND substr(nk, 1, 2) = '11'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadir11=hadir11+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakit11=sakit11+1;
               }
               else if(obj[0].equals("Izin")){
                   izin11=izin11+1;
               }
               else if(obj[0].equals("Alpha")){
                   alpha11=alpha11+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambat11=terlambat11+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensiangkatan12(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND substr(nk, 1, 2) = '12'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadir12=hadir12+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakit12=sakit12+1;
               }
               else if(obj[0].equals("Izin")){
                   izin12=izin12+1;
               }
               else if(obj[0].equals("Alpha")){
                   alpha12=alpha12+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambat12=terlambat12+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartangkatan(){                
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadir10, "Hadir", "Angkatan 10");
        ctdata.setValue(sakit10, "Sakit", "Angkatan 10");
        ctdata.setValue(izin10, "Izin", "Angkatan 10");
        ctdata.setValue(alpha10, "Alpha", "Angkatan 10");
        ctdata.setValue(terlambat10, "Terlambat", "Angkatan 10");
        ctdata.setValue(hadir11, "Hadir", "Angkatan 11");
        ctdata.setValue(sakit11, "Sakit", "Angkatan 11");
        ctdata.setValue(izin11, "Izin", "Angkatan 11");
        ctdata.setValue(alpha11, "Alpha", "Angkatan 11");
        ctdata.setValue(terlambat11, "Terlambat", "Angkatan 11");
        ctdata.setValue(hadir12, "Hadir", "Angkatan 12");
        ctdata.setValue(sakit12, "Sakit", "Angkatan 12");
        ctdata.setValue(izin12, "Izin", "Angkatan 12");
        ctdata.setValue(alpha12, "Alpha", "Angkatan 12");
        ctdata.setValue(terlambat12, "Terlambat", "Angkatan 12");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Angkatan", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
        chart.getPlot().setBackgroundPaint( Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        ChartPanel barChartPanel=new ChartPanel(chart);        
        chartabsensiangkatan.removeAll();
        chartabsensiangkatan.add(barChartPanel);
        chartabsensiangkatan.validate();
    }
    
    //CHART PER JURUSAN
    public void dataabsensijurusantitl(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND nk LIKE '%TITL%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirtitl=hadirtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakittitl=sakittitl+1;
               }
               else if(obj[0].equals("Izin")){
                   izintitl=izintitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphatitl=alphatitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambattitl=terlambattitl+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensijurusanbdp(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND nk LIKE '%BDP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirbdp=hadirbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitbdp=sakitbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinbdp=izinbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphabdp=alphabdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatbdp=terlambatbdp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensijurusanotkp(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal BETWEEN '"+tanggalpert+"' AND '"+tanggalakh+"' AND nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirotkp=hadirotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakitotkp=sakitotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   izinotkp=izinotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphaotkp=alphaotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambatotkp=terlambatotkp+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartjurusan(){                
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadirtitl, "Hadir", "TITL");
        ctdata.setValue(sakittitl, "Sakit", "TITL");
        ctdata.setValue(izintitl, "Izin", "TITL");
        ctdata.setValue(alphatitl, "Alpha", "TITL");
        ctdata.setValue(terlambattitl, "Terlambat", "TITL");
        ctdata.setValue(hadirbdp, "Hadir", "BDP");
        ctdata.setValue(sakitbdp, "Sakit", "BDP");
        ctdata.setValue(izinbdp, "Izin", "BDP");
        ctdata.setValue(alphabdp, "Alpha", "BDP");
        ctdata.setValue(terlambatbdp, "Terlambat", "BDP");
        ctdata.setValue(hadirotkp, "Hadir", "OTKP");
        ctdata.setValue(sakitotkp, "Sakit", "OTKP");
        ctdata.setValue(izinotkp, "Izin", "OTKP");
        ctdata.setValue(alphaotkp, "Alpha", "OTKP");
        ctdata.setValue(terlambatotkp, "Terlambat", "OTKP");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Jurusan", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
        chart.getPlot().setBackgroundPaint( Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        plot.getRenderer().setSeriesPaint(1, Color.yellow);
        plot.getRenderer().setSeriesPaint(2, Color.blue);
        plot.getRenderer().setSeriesPaint(3, Color.red);
        plot.getRenderer().setSeriesPaint(4, Color.orange);
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getPercentInstance()));
        plot.getRenderer().setDefaultItemLabelsVisible(true);
        ChartPanel barChartPanel=new ChartPanel(chart);        
        chartabsensijurusan.removeAll();
        chartabsensijurusan.add(barChartPanel);
        chartabsensijurusan.validate();
    }
    
    private void btn_kelolaabsenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsenMouseEntered
        // TODO add your handling code here:
        btn_kelolaabsen.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_kelolaabsenMouseEntered

    private void btn_kelolaabsenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsenMouseExited
        // TODO add your handling code here:
        btn_kelolaabsen.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_kelolaabsenMouseExited

    private void btn_kelolaabsenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsenMousePressed
        // TODO add your handling code here:
        btn_kelolaabsen.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_kelolaabsenMousePressed

    private void btn_siswabermasalahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_siswabermasalahMouseEntered
        // TODO add your handling code here:
        btn_siswabermasalah.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_siswabermasalahMouseEntered

    private void btn_siswabermasalahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_siswabermasalahMouseExited
        // TODO add your handling code here:
        btn_siswabermasalah.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_siswabermasalahMouseExited

    private void btn_siswabermasalahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_siswabermasalahMousePressed
        // TODO add your handling code here:
        btn_siswabermasalah.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_siswabermasalahMousePressed

    private void btn_kelolaabsenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsenMouseReleased
        // TODO add your handling code here:
        btn_kelolaabsen.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_kelolaabsenMouseReleased

    private void btn_siswabermasalahMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_siswabermasalahMouseReleased
        // TODO add your handling code here:
        btn_siswabermasalah.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_siswabermasalahMouseReleased

    private void btn_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseEntered
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_keluarMouseEntered

    private void btn_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseExited
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(37,112,183));
    }//GEN-LAST:event_btn_keluarMouseExited

    private void btn_keluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMousePressed
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(88,163,234));
    }//GEN-LAST:event_btn_keluarMousePressed

    private void btn_keluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseReleased
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(63,138,209));
    }//GEN-LAST:event_btn_keluarMouseReleased

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Paneljumlahguru;
    private javax.swing.JPanel Paneljumlahsiswa;
    private javax.swing.JScrollPane Scrollpane;
    private javax.swing.JLabel background;
    private javax.swing.JPanel btn_kelolaabsen;
    private javax.swing.JPanel btn_keluar;
    private javax.swing.JPanel btn_siswabermasalah;
    private javax.swing.JPanel chartabsensiangkatan;
    private javax.swing.JPanel chartabsensibulanan;
    private javax.swing.JPanel chartabsensiharian;
    private javax.swing.JPanel chartabsensijurusan;
    private javax.swing.JPanel chartabsensimingguan;
    private javax.swing.JPanel chartabsensisiswa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb_ajb1;
    private javax.swing.JLabel lb_ajo1;
    private javax.swing.JLabel lb_ajt1;
    private javax.swing.JLabel lb_hjb1;
    private javax.swing.JLabel lb_hjo1;
    private javax.swing.JLabel lb_hjt1;
    private javax.swing.JLabel lb_ijb1;
    private javax.swing.JLabel lb_ijo1;
    private javax.swing.JLabel lb_ijt1;
    private javax.swing.JLabel lb_jumlahguru;
    private javax.swing.JLabel lb_jumlahkelas;
    private javax.swing.JLabel lb_jumlahsiswa;
    private javax.swing.JLabel lb_sjb1;
    private javax.swing.JLabel lb_sjo1;
    private javax.swing.JLabel lb_sjt1;
    private javax.swing.JLabel lb_tjb1;
    private javax.swing.JLabel lb_tjo1;
    private javax.swing.JLabel lb_tjt1;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JLabel lbl_waktu;
    private javax.swing.JPanel panel_waktu;
    private javax.swing.JPanel paneldashboard;
    private javax.swing.JPanel panelgrafik;
    private javax.swing.JPanel paneljumlahkelas;
    private javax.swing.JLabel txt_jam;
    private javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
