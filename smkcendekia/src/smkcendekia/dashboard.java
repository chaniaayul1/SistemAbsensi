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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.AbstractDataset;
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
    /**
     * Creates new form dashboard
     */


    public dashboard() {
        initComponents();
        tanggal();
        tampil_jam();
        this.username.setText(Session.getusername());
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        //RUN QUERY DEFAULT
        queryjumlahsiswa();
        queryjumlahguru();
        queryjumlahkelas();
        chartsemuaabsensisiswa();
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
        Scrollpane = new javax.swing.JScrollPane();
        panelgrafik = new javax.swing.JPanel();
        Paneljumlahsiswa = new javax.swing.JPanel();
        lb_jumlahsiswa = new javax.swing.JLabel();
        lb_siswa = new javax.swing.JLabel();
        Paneljumlahguru = new javax.swing.JPanel();
        lb_jumlahguru = new javax.swing.JLabel();
        lb_guru = new javax.swing.JLabel();
        paneljumlahkelas = new javax.swing.JPanel();
        lb_jumlahkelas = new javax.swing.JLabel();
        lb_kelas = new javax.swing.JLabel();
        chartabsensissiwa = new javax.swing.JPanel();
        chartabsensikelas = new javax.swing.JPanel();
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

        Scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        Scrollpane.setBorder(null);
        Scrollpane.setOpaque(false);

        panelgrafik.setBackground(new java.awt.Color(255, 255, 255));
        panelgrafik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Paneljumlahsiswa.setBackground(new java.awt.Color(162, 32, 195));
        Paneljumlahsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahsiswa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahsiswa.setForeground(new java.awt.Color(255, 255, 255));
        Paneljumlahsiswa.add(lb_jumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 60, 50));

        lb_siswa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_siswa.setForeground(new java.awt.Color(255, 255, 255));
        lb_siswa.setText("SISWA");
        Paneljumlahsiswa.add(lb_siswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, 50));

        panelgrafik.add(Paneljumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 260, 50));

        Paneljumlahguru.setBackground(new java.awt.Color(0, 179, 131));
        Paneljumlahguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahguru.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahguru.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahguru.setForeground(new java.awt.Color(255, 255, 255));
        Paneljumlahguru.add(lb_jumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 60, 50));

        lb_guru.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_guru.setForeground(new java.awt.Color(255, 255, 255));
        lb_guru.setText("GURU");
        Paneljumlahguru.add(lb_guru, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, 50));

        panelgrafik.add(Paneljumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 260, 50));

        paneljumlahkelas.setBackground(new java.awt.Color(227, 162, 21));
        paneljumlahkelas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahkelas.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahkelas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_jumlahkelas.setForeground(new java.awt.Color(255, 255, 255));
        paneljumlahkelas.add(lb_jumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 60, 50));

        lb_kelas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_kelas.setForeground(new java.awt.Color(255, 255, 255));
        lb_kelas.setText("KELAS");
        paneljumlahkelas.add(lb_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, 50));

        panelgrafik.add(paneljumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 260, 50));

        chartabsensissiwa.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensissiwa.setLayout(new javax.swing.OverlayLayout(chartabsensissiwa));
        panelgrafik.add(chartabsensissiwa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 260, 270));

        chartabsensikelas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartabsensikelasLayout = new javax.swing.GroupLayout(chartabsensikelas);
        chartabsensikelas.setLayout(chartabsensikelasLayout);
        chartabsensikelasLayout.setHorizontalGroup(
            chartabsensikelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        chartabsensikelasLayout.setVerticalGroup(
            chartabsensikelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        panelgrafik.add(chartabsensikelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 260, 260));

        Scrollpane.setViewportView(panelgrafik);

        paneldashboard.add(Scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 950, 550));

        panel_waktu.setBackground(new java.awt.Color(255, 255, 255));
        panel_waktu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panel_waktu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tanggal.setText("Tanggal");
        lbl_tanggal.setToolTipText("");
        panel_waktu.add(lbl_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        txt_tanggal.setBackground(new java.awt.Color(102, 255, 102));
        txt_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 18)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 190, 33));

        lbl_waktu.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 14)); // NOI18N
        lbl_waktu.setText("Waktu ");
        panel_waktu.add(lbl_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 125, -1, -1));

        txt_jam.setBackground(new java.awt.Color(255, 51, 51));
        txt_jam.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        txt_jam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_jam.png"))); // NOI18N
        panel_waktu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_calender.png"))); // NOI18N
        panel_waktu.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 40, 40));

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
                lb_jumlahsiswa.setText((String) obj[0]);
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
                lb_jumlahguru.setText((String) obj[0]);
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
                lb_jumlahkelas.setText((String) obj[0]);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuaabsensisiswa(){
        int hadir = 0,sakit = 0,izin = 0,alpha = 0,terlambat = 0;
        try{
           stat = con.createStatement( );
           sql  = "Select hadir,sakit,izin,alpha,terlambat from lapabsen";
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
        
        final String series1 = "Hadir";
        final String series2 = "Sakit";
        final String series3 = "Izin";
        final String series4 = "Alpha";
        final String series5 = "Terlambat";
        

        final String category1 = "Semua Siswa";
        
        
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hadir, "Hadir", "Semua Siswa");
        ctdata.setValue(sakit, "Sakit", "Semua Siswa");
        ctdata.setValue(izin, "Izin", "Semua Siswa");
        ctdata.setValue(alpha, "Alpha", "Semua Siswa");
        ctdata.setValue(terlambat, "Terlambat", "Semua Siswa");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, false, true);
        //final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        //final PiePlot plot1 = (PiePlot) chart.getPlot();
        //plot1.setLabelGenerator(labelGenerator);
        //PiePlot piePlot=(PiePlot) chart.getPlot();
        //chart.getPlot().setBackgroundPaint( Color.white);
        //chart.getPlot().setOutlinePaint(null);
        //piePlot.setSectionPaint("Hadir",Color.green);
        //piePlot.setSectionPaint("Sakit",Color.yellow);
        //piePlot.setSectionPaint("Izin",Color.blue);
        //piePlot.setSectionPaint("Alpha",Color.red);
        //piePlot.setSectionPaint("Terlambat",Color.orange);
        ChartPanel barChartPanel=new ChartPanel(chart);
        chartabsensissiwa.removeAll();
        chartabsensissiwa.add(barChartPanel);
        chartabsensissiwa.validate();
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
    private javax.swing.JPanel chartabsensikelas;
    private javax.swing.JPanel chartabsensissiwa;
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
    private javax.swing.JLabel lb_guru;
    private javax.swing.JLabel lb_jumlahguru;
    private javax.swing.JLabel lb_jumlahkelas;
    private javax.swing.JLabel lb_jumlahsiswa;
    private javax.swing.JLabel lb_kelas;
    private javax.swing.JLabel lb_siswa;
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
