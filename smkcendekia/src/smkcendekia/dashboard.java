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
    int hadirhr,sakithr,izinhr,alphahr,terlambathr = 0;
    int allhadir,allsakit,allizin,allalpha,allterlambat = 0;
    int hadir10,sakit10,izin10,alpha10,terlambat10 = 0;
    int hadir11,sakit11,izin11,alpha11,terlambat11 = 0;
    int hadir12,sakit12,izin12,alpha12,terlambat12 = 0;
    int hadirtitl,sakittitl,izintitl,alphatitl,terlambattitl=0;
    int hadirbdp,sakitbdp,izinbdp,alphabdp,terlambatbdp ;
    int hadirotkp,sakitotkp,izinotkp,alphaotkp,terlambatotkp = 0;
    
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
        dataabsensisiswaharian();
        chartsemuasiswaharian();
        
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
        jCalendar1 = new com.toedter.calendar.JCalendar();
        btn_keluar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_siswabermasalah = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_kelolaabsen = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

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
        panelgrafik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharian.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiharian.setLayout(new javax.swing.OverlayLayout(chartabsensiharian));
        panelgrafik.add(chartabsensiharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 440, 420));

        chartabsensijurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensijurusan.setLayout(new javax.swing.OverlayLayout(chartabsensijurusan));
        panelgrafik.add(chartabsensijurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 440, 420));

        chartabsensiangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiangkatan.setLayout(new javax.swing.OverlayLayout(chartabsensiangkatan));
        panelgrafik.add(chartabsensiangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 440, 420));

        chartabsensisiswa.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensisiswa.setLayout(new javax.swing.OverlayLayout(chartabsensisiswa));
        panelgrafik.add(chartabsensisiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 440, 420));

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

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));
        jCalendar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jCalendar1.setAutoscrolls(true);
        jCalendar1.setDoubleBuffered(false);
        jCalendar1.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 8)); // NOI18N
        panel_waktu.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 370, 180));

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
        jLabel4.setText("Logout");

        javax.swing.GroupLayout btn_keluarLayout = new javax.swing.GroupLayout(btn_keluar);
        btn_keluar.setLayout(btn_keluarLayout);
        btn_keluarLayout.setHorizontalGroup(
            btn_keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_keluarLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel4)
                .addContainerGap(152, Short.MAX_VALUE))
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
        jLabel7.setText("Kelola Absen");

        javax.swing.GroupLayout btn_siswabermasalahLayout = new javax.swing.GroupLayout(btn_siswabermasalah);
        btn_siswabermasalah.setLayout(btn_siswabermasalahLayout);
        btn_siswabermasalahLayout.setHorizontalGroup(
            btn_siswabermasalahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_siswabermasalahLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel7)
                .addContainerGap(97, Short.MAX_VALUE))
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
    
    // CHART SEMUA SISWA HARIAN
    public void dataabsensisiswaharian(){
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE()";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hadirhr=hadirhr+1;
               }
               else if(obj[0].equals("Sakit")){
                   sakithr=sakithr+1;
               }
               else if(obj[0].equals("Izin")){
                   izinhr=izinhr+1;
               }
               else if(obj[0].equals("Alpha")){
                   alphahr=alphahr+1;
               }
               else if(obj[0].equals("Terlambat")){
                   terlambathr=terlambathr+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuasiswaharian(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadirhr, "Hadir", "Kehadiran Siswa Harian");
        ctdata.setValue(sakithr, "Sakit", "Kehadiran Siswa Harian");
        ctdata.setValue(izinhr, "Izin", "Kehadiran Siswa Harian");
        ctdata.setValue(alphahr, "Alpha", "Kehadiran Siswa Harian");
        ctdata.setValue(terlambathr, "Terlambat", "Kehadiran Siswa Harian");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Harian", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
    private javax.swing.JPanel chartabsensiharian;
    private javax.swing.JPanel chartabsensijurusan;
    private javax.swing.JPanel chartabsensisiswa;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb_jumlahguru;
    private javax.swing.JLabel lb_jumlahkelas;
    private javax.swing.JLabel lb_jumlahsiswa;
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
