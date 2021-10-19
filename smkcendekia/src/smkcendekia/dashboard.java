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
    
//SELURUH SISWA
    //HARI
    int hrhadir,hrsakit,hrizin,hralpha,hrterlambat=0;
    //MINGGU
    int mghadir,mgsakit,mgizin,mgalpha,mgterlambat=0;
    //BULAN
    int blnhadir,blnsakit,blnizin,blnalpha,blnterlambat= 0;
    //SEMESTER
    int allhadir,allsakit,allizin,allalpha,allterlambat = 0;
    
//JURUSAN
    //HARI
    int hrhadirotkp,hrsakitotkp,hrizinotkp,hralphaotkp,hrterlambatotkp = 0;
    int hrhadirtitl,hrsakittitl,hrizintitl,hralphatitl,hrterlambattitl = 0;
    int hrhadirbdp,hrsakitbdp,hrizinbdp,hralphabdp,hrterlambatbdp = 0;
    //MINGGU
    int mghadirotkp,mgsakitotkp,mgizinotkp,mgalphaotkp,mgterlambatotkp = 0;
    int mghadirtitl,mgsakittitl,mgizintitl,mgalphatitl,mgterlambattitl = 0;
    int mghadirbdp,mgsakitbdp,mgizinbdp,mgalphabdp,mgterlambatbdp = 0;
    //BULAN
    int blnhadirotkp,blnsakitotkp,blnizinotkp,blnalphaotkp,blnterlambatotkp = 0;
    int blnhadirtitl,blnsakittitl,blnizintitl,blnalphatitl,blnterlambattitl = 0;
    int blnhadirbdp,blnsakitbdp,blnizinbdp,blnalphabdp,blnterlambatbdp = 0;  
    //SEMESTER
    int hadirtitl,sakittitl,izintitl,alphatitl,terlambattitl=0;
    int hadirbdp,sakitbdp,izinbdp,alphabdp,terlambatbdp=0;
    int hadirotkp,sakitotkp,izinotkp,alphaotkp,terlambatotkp = 0;

//ANGKATAN
    //HARI
    int hrhadir10,hrsakit10,hrizin10,hralpha10,hrterlambat10=0;
    int hrhadir11,hrsakit11,hrizin11,hralpha11,hrterlambat11=0;
    int hrhadir12,hrsakit12,hrizin12,hralpha12,hrterlambat12=0;
    //MINGGU
    int mghadir10,mgsakit10,mgizin10,mgalpha10,mgterlambat10=0;
    int mghadir11,mgsakit11,mgizin11,mgalpha11,mgterlambat11=0;
    int mghadir12,mgsakit12,mgizin12,mgalpha12,mgterlambat12=0;
    //BULAN
    int blnhadir10,blnsakit10,blnizin10,blnalpha10,blnterlambat10=0;
    int blnhadir11,blnsakit11,blnizin11,blnalpha11,blnterlambat11=0;
    int blnhadir12,blnsakit12,blnizin12,blnalpha12,blnterlambat12=0;
    //SEMESTER
    int hadir10,sakit10,izin10,alpha10,terlambat10 = 0;
    int hadir11,sakit11,izin11,alpha11,terlambat11 = 0;
    int hadir12,sakit12,izin12,alpha12,terlambat12 = 0;
    
    //
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
        
        //SEMUA SISWA
        //HARI
            dataabsensisiswaharian();
            chartabsensiharian();
        //MINGGU
            dataabsensisiswamingguan();
            chartabsensimingguan();
        //BULAN
            dataabsensisiswabulanan();
            chartabsensibulanan();
        //SEMESTER
            dataabsensisiswa();
            chartsemuasiswa();
            
            
        //JURUSAN
        //HARI
            dataabsensisiswaharianbdp();
            dataabsensisiswahariantitl();
            dataabsensisiswaharianotkp();
            chartsemuasiswaharianjurusan();
        
        //MINGGU
            dataabsensisiswamingguanbdp();
            dataabsensisiswamingguantitl();
            dataabsensisiswamingguanotkp();
            chartsemuasiswamingguanjurusan();
        
        //BULAN
            dataabsensisiswabulananbdp();
            dataabsensisiswabulanantitl();
            dataabsensisiswabulananotkp();
            chartsemuasiswabulananjurusan();
        
        //SEMESTER
            dataabsensijurusantitl();
            dataabsensijurusanbdp();
            dataabsensijurusanotkp();
            chartjurusan();
        
        
        //ANGKATAN
        //HARI
        //MINGGU
        //BULAN
        //SEMESTER
        dataabsensiangkatan10();
        dataabsensiangkatan11();
        dataabsensiangkatan12();
        chartangkatan();
        
        //ABSENSI JURUSAN
        
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
        panelchartabsharian = new javax.swing.JPanel();
        chartabsensiharian = new javax.swing.JPanel();
        jPanel77 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jPanel79 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        lb_sah = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        lb_aah = new javax.swing.JLabel();
        lb_hah = new javax.swing.JLabel();
        lb_tah = new javax.swing.JLabel();
        lb_iah = new javax.swing.JLabel();
        panelchartabsmingguan = new javax.swing.JPanel();
        chartabsensimingguan = new javax.swing.JPanel();
        jPanel92 = new javax.swing.JPanel();
        jPanel93 = new javax.swing.JPanel();
        jPanel94 = new javax.swing.JPanel();
        jPanel95 = new javax.swing.JPanel();
        jPanel96 = new javax.swing.JPanel();
        jLabel195 = new javax.swing.JLabel();
        lb_sam = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        lb_aam = new javax.swing.JLabel();
        lb_ham = new javax.swing.JLabel();
        lb_tam = new javax.swing.JLabel();
        lb_iam = new javax.swing.JLabel();
        panelchartabsbulanan = new javax.swing.JPanel();
        chartabsensibulanan = new javax.swing.JPanel();
        jPanel107 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        jLabel228 = new javax.swing.JLabel();
        lb_sab = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jLabel238 = new javax.swing.JLabel();
        jLabel239 = new javax.swing.JLabel();
        lb_aab = new javax.swing.JLabel();
        lb_hab = new javax.swing.JLabel();
        lb_tab = new javax.swing.JLabel();
        lb_iab = new javax.swing.JLabel();
        panelchartabssemester = new javax.swing.JPanel();
        chartabsensisemester = new javax.swing.JPanel();
        jPanel122 = new javax.swing.JPanel();
        jPanel123 = new javax.swing.JPanel();
        jPanel124 = new javax.swing.JPanel();
        jPanel125 = new javax.swing.JPanel();
        jPanel126 = new javax.swing.JPanel();
        jLabel261 = new javax.swing.JLabel();
        lb_sas = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jLabel264 = new javax.swing.JLabel();
        jLabel265 = new javax.swing.JLabel();
        jLabel267 = new javax.swing.JLabel();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jLabel270 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        lb_aas = new javax.swing.JLabel();
        lb_has = new javax.swing.JLabel();
        lb_tas = new javax.swing.JLabel();
        lb_ias = new javax.swing.JLabel();
        panelchartabsharijurusan = new javax.swing.JPanel();
        chartabsensiharijurusan = new javax.swing.JPanel();
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
        panelchartabsminggujurusan = new javax.swing.JPanel();
        chartabsensiminggujurusan = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        lb_sjt2 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        lb_ajt2 = new javax.swing.JLabel();
        lb_hjt2 = new javax.swing.JLabel();
        lb_tjt2 = new javax.swing.JLabel();
        lb_ijt2 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        lb_sjo2 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        lb_ajo2 = new javax.swing.JLabel();
        lb_hjo2 = new javax.swing.JLabel();
        lb_tjo2 = new javax.swing.JLabel();
        lb_ijo2 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        lb_sjb2 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        lb_ajb2 = new javax.swing.JLabel();
        lb_hjb2 = new javax.swing.JLabel();
        lb_tjb2 = new javax.swing.JLabel();
        lb_ijb2 = new javax.swing.JLabel();
        panelchartabsbulanjurusan = new javax.swing.JPanel();
        chartabsensibulanjurusan = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        lb_sjt3 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        lb_ajt3 = new javax.swing.JLabel();
        lb_hjt3 = new javax.swing.JLabel();
        lb_tjt3 = new javax.swing.JLabel();
        lb_ijt3 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        lb_sjo3 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        lb_ajo3 = new javax.swing.JLabel();
        lb_hjo3 = new javax.swing.JLabel();
        lb_tjo3 = new javax.swing.JLabel();
        lb_ijo3 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        lb_sjb3 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        lb_ajb3 = new javax.swing.JLabel();
        lb_hjb3 = new javax.swing.JLabel();
        lb_tjb3 = new javax.swing.JLabel();
        lb_ijb3 = new javax.swing.JLabel();
        panelchartabssmtjurusan = new javax.swing.JPanel();
        chartabsensismtjurusan = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        lb_sjt4 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        lb_ajt4 = new javax.swing.JLabel();
        lb_hjt4 = new javax.swing.JLabel();
        lb_tjt4 = new javax.swing.JLabel();
        lb_ijt4 = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        lb_sjo4 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        lb_ajo4 = new javax.swing.JLabel();
        lb_hjo4 = new javax.swing.JLabel();
        lb_tjo4 = new javax.swing.JLabel();
        lb_ijo4 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        lb_sjb4 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        lb_ajb4 = new javax.swing.JLabel();
        lb_hjb4 = new javax.swing.JLabel();
        lb_tjb4 = new javax.swing.JLabel();
        lb_ijb4 = new javax.swing.JLabel();
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
        panelgrafik.setPreferredSize(new java.awt.Dimension(930, 4091));
        panelgrafik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelchartabsharian.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsharian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharian.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiharian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensiharian.setLayout(new javax.swing.OverlayLayout(chartabsensiharian));
        panelchartabsharian.add(chartabsensiharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel77.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharian.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel78.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharian.add(jPanel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel79.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharian.add(jPanel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel80.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharian.add(jPanel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel81.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharian.add(jPanel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel162.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel162.setText("Terlambat");
        panelchartabsharian.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_sah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel163.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel163.setText("Sakit");
        panelchartabsharian.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel164.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel164.setText("Izin");
        panelchartabsharian.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel165.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel165.setText("Alpha");
        panelchartabsharian.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel166.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel166.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsharian.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel168.setText(":");
        panelchartabsharian.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel169.setText(":");
        panelchartabsharian.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel170.setText(":");
        panelchartabsharian.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel171.setText(":");
        panelchartabsharian.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel172.setText(":");
        panelchartabsharian.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel173.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel173.setText("Hadir");
        panelchartabsharian.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_aah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_aah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_hah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_tah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_iah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_iah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        panelgrafik.add(panelchartabsharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 440));

        panelchartabsmingguan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsmingguan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensimingguan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensimingguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensimingguan.setLayout(new javax.swing.OverlayLayout(chartabsensimingguan));
        panelchartabsmingguan.add(chartabsensimingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel92.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
        jPanel92.setLayout(jPanel92Layout);
        jPanel92Layout.setHorizontalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel92Layout.setVerticalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsmingguan.add(jPanel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel93.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
        jPanel93.setLayout(jPanel93Layout);
        jPanel93Layout.setHorizontalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel93Layout.setVerticalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsmingguan.add(jPanel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel94.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel94Layout = new javax.swing.GroupLayout(jPanel94);
        jPanel94.setLayout(jPanel94Layout);
        jPanel94Layout.setHorizontalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel94Layout.setVerticalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsmingguan.add(jPanel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel95.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel95Layout = new javax.swing.GroupLayout(jPanel95);
        jPanel95.setLayout(jPanel95Layout);
        jPanel95Layout.setHorizontalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel95Layout.setVerticalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsmingguan.add(jPanel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel96.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel96Layout = new javax.swing.GroupLayout(jPanel96);
        jPanel96.setLayout(jPanel96Layout);
        jPanel96Layout.setHorizontalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel96Layout.setVerticalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsmingguan.add(jPanel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel195.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel195.setText("Terlambat");
        panelchartabsmingguan.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_sam, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel196.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel196.setText("Sakit");
        panelchartabsmingguan.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel197.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel197.setText("Izin");
        panelchartabsmingguan.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel198.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel198.setText("Alpha");
        panelchartabsmingguan.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel199.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel199.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsmingguan.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel201.setText(":");
        panelchartabsmingguan.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel202.setText(":");
        panelchartabsmingguan.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel203.setText(":");
        panelchartabsmingguan.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel204.setText(":");
        panelchartabsmingguan.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel205.setText(":");
        panelchartabsmingguan.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel206.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel206.setText("Hadir");
        panelchartabsmingguan.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_aam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_aam, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_ham.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_ham, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_tam, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_iam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_iam, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        panelgrafik.add(panelchartabsmingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 455, 930, 440));

        panelchartabsbulanan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsbulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensibulanan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensibulanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensibulanan.setLayout(new javax.swing.OverlayLayout(chartabsensibulanan));
        panelchartabsbulanan.add(chartabsensibulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel107.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanan.add(jPanel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel108.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanan.add(jPanel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel109.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
        jPanel109.setLayout(jPanel109Layout);
        jPanel109Layout.setHorizontalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel109Layout.setVerticalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanan.add(jPanel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel110.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
        jPanel110.setLayout(jPanel110Layout);
        jPanel110Layout.setHorizontalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel110Layout.setVerticalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanan.add(jPanel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel111.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel111Layout.setVerticalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanan.add(jPanel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel228.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel228.setText("Terlambat");
        panelchartabsbulanan.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_sab, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel229.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel229.setText("Sakit");
        panelchartabsbulanan.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel230.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel230.setText("Izin");
        panelchartabsbulanan.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel231.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel231.setText("Alpha");
        panelchartabsbulanan.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel232.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel232.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsbulanan.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel234.setText(":");
        panelchartabsbulanan.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel235.setText(":");
        panelchartabsbulanan.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel236.setText(":");
        panelchartabsbulanan.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel237.setText(":");
        panelchartabsbulanan.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel238.setText(":");
        panelchartabsbulanan.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel239.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel239.setText("Hadir");
        panelchartabsbulanan.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_aab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_aab, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_hab, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_iab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_iab, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        panelgrafik.add(panelchartabsbulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 910, 930, 440));

        panelchartabssemester.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabssemester.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensisemester.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensisemester.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensisemester.setLayout(new javax.swing.OverlayLayout(chartabsensisemester));
        panelchartabssemester.add(chartabsensisemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel122.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
        jPanel122.setLayout(jPanel122Layout);
        jPanel122Layout.setHorizontalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel122Layout.setVerticalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssemester.add(jPanel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel123.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssemester.add(jPanel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel124.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssemester.add(jPanel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel125.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
        jPanel125.setLayout(jPanel125Layout);
        jPanel125Layout.setHorizontalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel125Layout.setVerticalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssemester.add(jPanel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel126.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
        jPanel126.setLayout(jPanel126Layout);
        jPanel126Layout.setHorizontalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel126Layout.setVerticalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssemester.add(jPanel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel261.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel261.setText("Terlambat");
        panelchartabssemester.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_sas, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel262.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel262.setText("Sakit");
        panelchartabssemester.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel263.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel263.setText("Izin");
        panelchartabssemester.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel264.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel264.setText("Alpha");
        panelchartabssemester.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel265.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel265.setText("KEHADIRAN SELURUH SISWA");
        panelchartabssemester.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel267.setText(":");
        panelchartabssemester.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel268.setText(":");
        panelchartabssemester.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel269.setText(":");
        panelchartabssemester.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel270.setText(":");
        panelchartabssemester.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel271.setText(":");
        panelchartabssemester.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel272.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel272.setText("Hadir");
        panelchartabssemester.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_aas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_aas, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_has.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_has.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_has, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_tas, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ias.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_ias, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        panelgrafik.add(panelchartabssemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1365, 930, 440));

        panelchartabsharijurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsharijurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharijurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiharijurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensiharijurusan.setLayout(new javax.swing.OverlayLayout(chartabsensiharijurusan));
        panelchartabsharijurusan.add(chartabsensiharijurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

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

        panelchartabsharijurusan.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

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

        panelchartabsharijurusan.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

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

        panelchartabsharijurusan.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

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

        panelchartabsharijurusan.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

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

        panelchartabsharijurusan.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel30.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel30.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel32.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel32.setText("Sakit");
        panelchartabsharijurusan.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel33.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel33.setText("Izin");
        panelchartabsharijurusan.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel34.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel34.setText("Alpha");
        panelchartabsharijurusan.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel35.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel35.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsharijurusan.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel36.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel36.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsharijurusan.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 284, -1, -1));

        jLabel37.setText(":");
        panelchartabsharijurusan.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel38.setText(":");
        panelchartabsharijurusan.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel39.setText(":");
        panelchartabsharijurusan.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel40.setText(":");
        panelchartabsharijurusan.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel41.setText(":");
        panelchartabsharijurusan.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel52.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel52.setText("Hadir");
        panelchartabsharijurusan.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_ajt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ijt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

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

        panelchartabsharijurusan.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

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

        panelchartabsharijurusan.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

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

        panelchartabsharijurusan.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

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

        panelchartabsharijurusan.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

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

        panelchartabsharijurusan.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel31.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel31.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel53.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel53.setText("Sakit");
        panelchartabsharijurusan.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel54.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel54.setText("Izin");
        panelchartabsharijurusan.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel55.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel55.setText("Alpha");
        panelchartabsharijurusan.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel56.setText(":");
        panelchartabsharijurusan.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel57.setText(":");
        panelchartabsharijurusan.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel58.setText(":");
        panelchartabsharijurusan.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel59.setText(":");
        panelchartabsharijurusan.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel60.setText(":");
        panelchartabsharijurusan.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel61.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel61.setText("Hadir");
        panelchartabsharijurusan.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_ajo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_tjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_ijo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

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

        panelchartabsharijurusan.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

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

        panelchartabsharijurusan.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

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

        panelchartabsharijurusan.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

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

        panelchartabsharijurusan.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

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

        panelchartabsharijurusan.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel47.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel47.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel48.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel48.setText("Sakit");
        panelchartabsharijurusan.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel49.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel49.setText("Izin");
        panelchartabsharijurusan.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel50.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel50.setText("Alpha");
        panelchartabsharijurusan.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel51.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel51.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsharijurusan.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel62.setText(":");
        panelchartabsharijurusan.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel63.setText(":");
        panelchartabsharijurusan.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel64.setText(":");
        panelchartabsharijurusan.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel65.setText(":");
        panelchartabsharijurusan.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel66.setText(":");
        panelchartabsharijurusan.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel67.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel67.setText("Hadir");
        panelchartabsharijurusan.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_ajb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_tjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_ijb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabsharijurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1820, 930, 440));

        panelchartabsminggujurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsminggujurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiminggujurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensiminggujurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensiminggujurusan.setLayout(new javax.swing.OverlayLayout(chartabsensiminggujurusan));
        panelchartabsminggujurusan.add(chartabsensiminggujurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel20.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel34.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel35.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel36.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel37.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel42.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel42.setText("Terlambat");
        panelchartabsminggujurusan.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sjt2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sjt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel43.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel43.setText("Sakit");
        panelchartabsminggujurusan.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel44.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel44.setText("Izin");
        panelchartabsminggujurusan.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel45.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel45.setText("Alpha");
        panelchartabsminggujurusan.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel46.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel46.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsminggujurusan.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel68.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel68.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsminggujurusan.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 284, -1, -1));

        jLabel69.setText(":");
        panelchartabsminggujurusan.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel70.setText(":");
        panelchartabsminggujurusan.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel71.setText(":");
        panelchartabsminggujurusan.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel72.setText(":");
        panelchartabsminggujurusan.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel73.setText(":");
        panelchartabsminggujurusan.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel74.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel74.setText("Hadir");
        panelchartabsminggujurusan.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_ajt2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ajt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hjt2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hjt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tjt2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_tjt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ijt2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ijt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel21.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel38.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel39.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel40.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel41.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel75.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel75.setText("Terlambat");
        panelchartabsminggujurusan.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sjo2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sjo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel76.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel76.setText("Sakit");
        panelchartabsminggujurusan.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel77.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel77.setText("Izin");
        panelchartabsminggujurusan.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel78.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel78.setText("Alpha");
        panelchartabsminggujurusan.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel79.setText(":");
        panelchartabsminggujurusan.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel80.setText(":");
        panelchartabsminggujurusan.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel81.setText(":");
        panelchartabsminggujurusan.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel82.setText(":");
        panelchartabsminggujurusan.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel83.setText(":");
        panelchartabsminggujurusan.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel84.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel84.setText("Hadir");
        panelchartabsminggujurusan.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_ajo2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ajo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hjo2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hjo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_tjo2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_tjo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_ijo2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ijo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel42.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel43.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel44.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel45.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel46.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsminggujurusan.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel85.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel85.setText("Terlambat");
        panelchartabsminggujurusan.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sjb2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sjb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel86.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel86.setText("Sakit");
        panelchartabsminggujurusan.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel87.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel87.setText("Izin");
        panelchartabsminggujurusan.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel88.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel88.setText("Alpha");
        panelchartabsminggujurusan.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel89.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel89.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsminggujurusan.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel90.setText(":");
        panelchartabsminggujurusan.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel91.setText(":");
        panelchartabsminggujurusan.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel92.setText(":");
        panelchartabsminggujurusan.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel93.setText(":");
        panelchartabsminggujurusan.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel94.setText(":");
        panelchartabsminggujurusan.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel95.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel95.setText("Hadir");
        panelchartabsminggujurusan.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_ajb2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ajb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hjb2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hjb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_tjb2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_tjb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_ijb2.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_ijb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabsminggujurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2275, 930, 440));

        panelchartabsbulanjurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsbulanjurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensibulanjurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensibulanjurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensibulanjurusan.setLayout(new javax.swing.OverlayLayout(chartabsensibulanjurusan));
        panelchartabsbulanjurusan.add(chartabsensibulanjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel47.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel48.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel49.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel50.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel51.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel96.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel96.setText("Terlambat");
        panelchartabsbulanjurusan.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sjt3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sjt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel97.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel97.setText("Sakit");
        panelchartabsbulanjurusan.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel98.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel98.setText("Izin");
        panelchartabsbulanjurusan.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel99.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel99.setText("Alpha");
        panelchartabsbulanjurusan.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel100.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel100.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsbulanjurusan.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel101.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel101.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsbulanjurusan.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 284, -1, -1));

        jLabel102.setText(":");
        panelchartabsbulanjurusan.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel103.setText(":");
        panelchartabsbulanjurusan.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel104.setText(":");
        panelchartabsbulanjurusan.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel105.setText(":");
        panelchartabsbulanjurusan.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel106.setText(":");
        panelchartabsbulanjurusan.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel107.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel107.setText("Hadir");
        panelchartabsbulanjurusan.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_ajt3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ajt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hjt3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hjt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tjt3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_tjt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ijt3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ijt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel52.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel53.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel54.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel55.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel56.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel108.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel108.setText("Terlambat");
        panelchartabsbulanjurusan.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sjo3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sjo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel109.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel109.setText("Sakit");
        panelchartabsbulanjurusan.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel110.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel110.setText("Izin");
        panelchartabsbulanjurusan.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel111.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel111.setText("Alpha");
        panelchartabsbulanjurusan.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel112.setText(":");
        panelchartabsbulanjurusan.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel113.setText(":");
        panelchartabsbulanjurusan.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel114.setText(":");
        panelchartabsbulanjurusan.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel115.setText(":");
        panelchartabsbulanjurusan.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel116.setText(":");
        panelchartabsbulanjurusan.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel117.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel117.setText("Hadir");
        panelchartabsbulanjurusan.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_ajo3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ajo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hjo3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hjo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_tjo3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_tjo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_ijo3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ijo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel57.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel58.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel59.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel60.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel61.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanjurusan.add(jPanel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel118.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel118.setText("Terlambat");
        panelchartabsbulanjurusan.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sjb3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sjb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel119.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel119.setText("Sakit");
        panelchartabsbulanjurusan.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel120.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel120.setText("Izin");
        panelchartabsbulanjurusan.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel121.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel121.setText("Alpha");
        panelchartabsbulanjurusan.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel122.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel122.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsbulanjurusan.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel123.setText(":");
        panelchartabsbulanjurusan.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel124.setText(":");
        panelchartabsbulanjurusan.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel125.setText(":");
        panelchartabsbulanjurusan.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel126.setText(":");
        panelchartabsbulanjurusan.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel127.setText(":");
        panelchartabsbulanjurusan.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel128.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel128.setText("Hadir");
        panelchartabsbulanjurusan.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_ajb3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ajb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hjb3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hjb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_tjb3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_tjb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_ijb3.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_ijb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabsbulanjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2730, 930, 440));

        panelchartabssmtjurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabssmtjurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensismtjurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensismtjurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chartabsensismtjurusan.setLayout(new javax.swing.OverlayLayout(chartabsensismtjurusan));
        panelchartabssmtjurusan.add(chartabsensismtjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel62.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel63.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel64.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel65.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel66.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel129.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel129.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sjt4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sjt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel130.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel130.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel131.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel131.setText("Izin");
        panelchartabssmtjurusan.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel132.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel132.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel133.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel133.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabssmtjurusan.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel134.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel134.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabssmtjurusan.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 284, -1, -1));

        jLabel135.setText(":");
        panelchartabssmtjurusan.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel136.setText(":");
        panelchartabssmtjurusan.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel137.setText(":");
        panelchartabssmtjurusan.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel138.setText(":");
        panelchartabssmtjurusan.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel139.setText(":");
        panelchartabssmtjurusan.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel140.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel140.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_ajt4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ajt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hjt4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hjt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_tjt4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_tjt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_ijt4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ijt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel67.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel68.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel69.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel70.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel71.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel141.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel141.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sjo4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sjo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel142.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel142.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel143.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel143.setText("Izin");
        panelchartabssmtjurusan.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel144.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel144.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel145.setText(":");
        panelchartabssmtjurusan.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel146.setText(":");
        panelchartabssmtjurusan.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel147.setText(":");
        panelchartabssmtjurusan.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel148.setText(":");
        panelchartabssmtjurusan.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel149.setText(":");
        panelchartabssmtjurusan.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel150.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel150.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_ajo4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ajo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hjo4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hjo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_tjo4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_tjo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_ijo4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ijo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel72.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel73.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel74.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel75.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel76.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel151.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel151.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sjb4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sjb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel152.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel152.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel153.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel153.setText("Izin");
        panelchartabssmtjurusan.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel154.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel154.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel155.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 10)); // NOI18N
        jLabel155.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabssmtjurusan.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel156.setText(":");
        panelchartabssmtjurusan.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel157.setText(":");
        panelchartabssmtjurusan.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel158.setText(":");
        panelchartabssmtjurusan.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel159.setText(":");
        panelchartabssmtjurusan.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel160.setText(":");
        panelchartabssmtjurusan.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel161.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel161.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_ajb4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ajb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hjb4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hjb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_tjb4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_tjb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_ijb4.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_ijb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabssmtjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3185, 930, 440));

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
    
//SEMUA SISWA
    //HARI
    public void dataabsensisiswaharian(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE()";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadir=hrhadir+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakit=hrsakit+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizin=hrizin+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralpha=hralpha+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambat=hrterlambat+1;
               }
            }
           totalhadir = Integer.toString(hrhadir);
           totalsakit = Integer.toString(hrsakit);
           totalizin  = Integer.toString(hrizin);
           totalalpha = Integer.toString(hralpha);
           totalterlambat = Integer.toString(hrterlambat);
           lb_hah.setText(totalhadir);
           lb_sah.setText(totalsakit);
           lb_iah.setText(totalizin);
           lb_aah.setText(totalalpha);
           lb_tah.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartabsensiharian(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hrhadir, "Hadir", "Kehadiran Siswa");
        ctdata.setValue(hrsakit, "Sakit", "Kehadiran Siswa");
        ctdata.setValue(hrizin, "Izin", "Kehadiran Siswa");
        ctdata.setValue(hralpha, "Alpha", "Kehadiran Siswa");
        ctdata.setValue(hrterlambat, "Terlambat", "Kehadiran Siswa");
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
    
    //MINGGU
    public void dataabsensisiswamingguan(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE())";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   mghadir=mghadir+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakit=mgsakit+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizin=mgizin+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalpha=mgalpha+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambat=mgterlambat+1;
               }
            }      
            totalhadir = Integer.toString(hrhadir);
            totalsakit = Integer.toString(hrsakit);
            totalizin  = Integer.toString(hrizin);
            totalalpha = Integer.toString(hralpha);
            totalterlambat = Integer.toString(hrterlambat);
            lb_ham.setText(totalhadir);
            lb_sam.setText(totalsakit);
            lb_iam.setText(totalizin);
            lb_aam.setText(totalalpha);
            lb_tam.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartabsensimingguan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(mghadir, "Hadir", "Kehadiran Siswa");
        ctdata.setValue(mgsakit, "Sakit", "Kehadiran Siswa");
        ctdata.setValue(mgizin, "Izin", "Kehadiran Siswa");
        ctdata.setValue(mgalpha, "Alpha", "Kehadiran Siswa");
        ctdata.setValue(mgterlambat, "Terlambat", "Kehadiran Siswa");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Siswa per Minggu ini", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartabsensimingguan.validate();
    }
    
    //BULAN
    public void dataabsensisiswabulanan(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE tanggal between DATE_FORMAT(CURDATE(),'%Y-%M-01') AND CURDATE()";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   blnhadir=blnhadir+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakit=blnsakit+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizin=blnizin+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalpha=blnalpha+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambat=blnterlambat+1;
               }
            }
            totalhadir = Integer.toString(hrhadir);
            totalsakit = Integer.toString(hrsakit);
            totalizin  = Integer.toString(hrizin);
            totalalpha = Integer.toString(hralpha);
            totalterlambat = Integer.toString(hrterlambat);
            lb_hab.setText(totalhadir);
            lb_sab.setText(totalsakit);
            lb_iab.setText(totalizin);
            lb_aab.setText(totalalpha);
            lb_tab.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartabsensibulanan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(blnhadir, "Hadir", "Kehadiran Siswa");
        ctdata.setValue(blnsakit, "Sakit", "Kehadiran Siswa");
        ctdata.setValue(blnizin, "Izin", "Kehadiran Siswa");
        ctdata.setValue(blnalpha, "Alpha", "Kehadiran Siswa");
        ctdata.setValue(blnterlambat, "Terlambat", "Kehadiran Siswa");
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
    
    //SEMESTER
    public void dataabsensisiswa(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
            totalhadir = Integer.toString(allhadir);
            totalsakit = Integer.toString(allsakit);
            totalizin  = Integer.toString(allizin);
            totalalpha = Integer.toString(allalpha);
            totalterlambat = Integer.toString(allterlambat);
            lb_has.setText(totalhadir);
            lb_sas.setText(totalsakit);
            lb_ias.setText(totalizin);
            lb_aas.setText(totalalpha);
            lb_tas.setText(totalterlambat);
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
        chartabsensisemester.removeAll();
        chartabsensisemester.add(barChartPanel);
        chartabsensisemester.validate();
    }


//JURUSAN    
    //HARI
    public void dataabsensisiswaharianotkp(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadirotkp=hadirotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakitotkp=hrsakitotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizinotkp=izinotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralphaotkp=hralphaotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambatotkp=hrterlambatotkp+1;
               }
            }
           totalhadir = Integer.toString(hrhadirotkp);
           totalsakit = Integer.toString(hrsakitotkp);
           totalizin  = Integer.toString(hrizinotkp);
           totalalpha = Integer.toString(hralphaotkp);
           totalterlambat = Integer.toString(hrterlambatotkp);
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
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%BDP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadirbdp=hrhadirbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakitbdp=hrsakitbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizinbdp=hrizinbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralphabdp=hralphabdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambatbdp=hrterlambatbdp+1;
               }
            }
           totalhadir = Integer.toString(hrhadirbdp);
           totalsakit = Integer.toString(hrsakitbdp);
           totalizin  = Integer.toString(hrizinbdp);
           totalalpha = Integer.toString(hralphabdp);
           totalterlambat = Integer.toString(hrterlambatbdp);
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
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%TITL%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadirtitl=hrhadirtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakittitl=hrsakittitl+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizintitl=hrizintitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralphatitl=hralphatitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambattitl=hrterlambattitl+1;
               }
            }
           totalhadir = Integer.toString(hrhadirtitl);
           totalsakit = Integer.toString(hrsakittitl);
           totalizin  = Integer.toString(hrizintitl);
           totalalpha = Integer.toString(hralphatitl);
           totalterlambat = Integer.toString(hrterlambattitl);
           lb_hjt1.setText(totalhadir);
           lb_sjt1.setText(totalsakit);
           lb_ijt1.setText(totalizin);
           lb_ajt1.setText(totalalpha);
           lb_tjt1.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuasiswaharianjurusan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hrhadirtitl, "Hadir", "TITL");
        ctdata.setValue(hrsakittitl, "Sakit", "TITL");
        ctdata.setValue(hrizintitl, "Izin", "TITL");
        ctdata.setValue(hralphatitl, "Alpha", "TITL");
        ctdata.setValue(hrterlambattitl, "Terlambat", "TITL");
        ctdata.setValue(hrhadirbdp, "Hadir", "BDP");
        ctdata.setValue(hrsakitbdp, "Sakit", "BDP");
        ctdata.setValue(hrizinbdp, "Izin", "BDP");
        ctdata.setValue(hralphabdp, "Alpha", "BDP");
        ctdata.setValue(hrterlambatbdp, "Terlambat", "BDP");
        ctdata.setValue(hrhadirotkp, "Hadir", "OTKP");
        ctdata.setValue(hrsakitotkp, "Sakit", "OTKP");
        ctdata.setValue(hrizinotkp, "Izin", "OTKP");
        ctdata.setValue(hralphaotkp, "Alpha", "OTKP");
        ctdata.setValue(hrterlambatotkp, "Terlambat", "OTKP");
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
        chartabsensiharijurusan.removeAll();
        chartabsensiharijurusan.add(barChartPanel);
        chartabsensiharijurusan.validate();
    }
    
    //MINGGU
    public void dataabsensisiswamingguanotkp(){
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   mghadirotkp=mghadirotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakitotkp=mgsakitotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizinotkp=mgizinotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalphaotkp=mgalphaotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambatotkp=mgterlambatotkp+1;
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
                   mghadirbdp=mghadirbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakitbdp=mgsakitbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizinbdp=mgizinbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalphabdp=mgalphabdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambatbdp=mgterlambatbdp+1;
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
                   mghadirtitl=mghadirtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakittitl=mgsakittitl+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizintitl=mgizintitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalphatitl=mgalphatitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambattitl=mgterlambattitl+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuasiswamingguanjurusan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(mghadirtitl, "Hadir", "TITL");
        ctdata.setValue(mgsakittitl, "Sakit", "TITL");
        ctdata.setValue(mgizintitl, "Izin", "TITL");
        ctdata.setValue(mgalphatitl, "Alpha", "TITL");
        ctdata.setValue(mgterlambattitl, "Terlambat", "TITL");
        ctdata.setValue(mghadirbdp, "Hadir", "BDP");
        ctdata.setValue(mgsakitbdp, "Sakit", "BDP");
        ctdata.setValue(mgizinbdp, "Izin", "BDP");
        ctdata.setValue(mgalphabdp, "Alpha", "BDP");
        ctdata.setValue(mgterlambatbdp, "Terlambat", "BDP");
        ctdata.setValue(mghadirotkp, "Hadir", "OTKP");
        ctdata.setValue(mgsakitotkp, "Sakit", "OTKP");
        ctdata.setValue(mgizinotkp, "Izin", "OTKP");
        ctdata.setValue(mgalphaotkp, "Alpha", "OTKP");
        ctdata.setValue(mgterlambatotkp, "Terlambat", "OTKP");
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
        chartabsensiminggujurusan.removeAll();
        chartabsensiminggujurusan.add(barChartPanel);
        chartabsensiminggujurusan.setVisible(true);
        chartabsensiminggujurusan.validate();
        
    }
    
    
    //BULAN
    public void dataabsensisiswabulananotkp(){
        
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
                   blnhadirbdp=blnhadirbdp+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakitbdp=blnsakitbdp+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizinbdp=blnizinbdp+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalphabdp=blnalphabdp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambatbdp=blnterlambatbdp+1;
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
                   blnhadirtitl=blnhadirtitl+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakittitl=blnsakittitl+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizintitl=blnizintitl+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalphatitl=blnalphatitl+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambattitl=blnterlambattitl+1;
               }
            }      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void chartsemuasiswabulananjurusan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(blnhadirtitl, "Hadir", "TITL");
        ctdata.setValue(blnsakittitl, "Sakit", "TITL");
        ctdata.setValue(blnizintitl, "Izin", "TITL");
        ctdata.setValue(blnalphatitl, "Alpha", "TITL");
        ctdata.setValue(blnterlambattitl, "Terlambat", "TITL");
        ctdata.setValue(blnhadirbdp, "Hadir", "BDP");
        ctdata.setValue(blnsakitbdp, "Sakit", "BDP");
        ctdata.setValue(blnizinbdp, "Izin", "BDP");
        ctdata.setValue(blnalphabdp, "Alpha", "BDP");
        ctdata.setValue(blnterlambatbdp, "Terlambat", "BDP");
        ctdata.setValue(blnhadirotkp, "Hadir", "OTKP");
        ctdata.setValue(blnsakitotkp, "Sakit", "OTKP");
        ctdata.setValue(blnizinotkp, "Izin", "OTKP");
        ctdata.setValue(blnalphaotkp, "Alpha", "OTKP");
        ctdata.setValue(blnterlambatotkp, "Terlambat", "OTKP");
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
        chartabsensibulanjurusan.removeAll();
        chartabsensibulanjurusan.add(barChartPanel);
        chartabsensibulanjurusan.validate();
    }
    
    //SEMESTER
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
        chartabsensismtjurusan.removeAll();
        chartabsensismtjurusan.add(barChartPanel);
        chartabsensismtjurusan.validate();
    }
    
//ANGKATAN   
    //HARI
    
    //MINGGU
    
    //BULANAN
    
    //SEMESTER
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
//        chartabsensiangkatan.removeAll();
//      chartabsensiangkatan.add(barChartPanel);
//      chartabsensiangkatan.validate();
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
    private javax.swing.JPanel chartabsensibulanan;
    private javax.swing.JPanel chartabsensibulanjurusan;
    private javax.swing.JPanel chartabsensiharian;
    private javax.swing.JPanel chartabsensiharijurusan;
    private javax.swing.JPanel chartabsensimingguan;
    private javax.swing.JPanel chartabsensiminggujurusan;
    private javax.swing.JPanel chartabsensisemester;
    private javax.swing.JPanel chartabsensismtjurusan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
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
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
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
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb_aab;
    private javax.swing.JLabel lb_aah;
    private javax.swing.JLabel lb_aam;
    private javax.swing.JLabel lb_aas;
    private javax.swing.JLabel lb_ajb1;
    private javax.swing.JLabel lb_ajb2;
    private javax.swing.JLabel lb_ajb3;
    private javax.swing.JLabel lb_ajb4;
    private javax.swing.JLabel lb_ajo1;
    private javax.swing.JLabel lb_ajo2;
    private javax.swing.JLabel lb_ajo3;
    private javax.swing.JLabel lb_ajo4;
    private javax.swing.JLabel lb_ajt1;
    private javax.swing.JLabel lb_ajt2;
    private javax.swing.JLabel lb_ajt3;
    private javax.swing.JLabel lb_ajt4;
    private javax.swing.JLabel lb_hab;
    private javax.swing.JLabel lb_hah;
    private javax.swing.JLabel lb_ham;
    private javax.swing.JLabel lb_has;
    private javax.swing.JLabel lb_hjb1;
    private javax.swing.JLabel lb_hjb2;
    private javax.swing.JLabel lb_hjb3;
    private javax.swing.JLabel lb_hjb4;
    private javax.swing.JLabel lb_hjo1;
    private javax.swing.JLabel lb_hjo2;
    private javax.swing.JLabel lb_hjo3;
    private javax.swing.JLabel lb_hjo4;
    private javax.swing.JLabel lb_hjt1;
    private javax.swing.JLabel lb_hjt2;
    private javax.swing.JLabel lb_hjt3;
    private javax.swing.JLabel lb_hjt4;
    private javax.swing.JLabel lb_iab;
    private javax.swing.JLabel lb_iah;
    private javax.swing.JLabel lb_iam;
    private javax.swing.JLabel lb_ias;
    private javax.swing.JLabel lb_ijb1;
    private javax.swing.JLabel lb_ijb2;
    private javax.swing.JLabel lb_ijb3;
    private javax.swing.JLabel lb_ijb4;
    private javax.swing.JLabel lb_ijo1;
    private javax.swing.JLabel lb_ijo2;
    private javax.swing.JLabel lb_ijo3;
    private javax.swing.JLabel lb_ijo4;
    private javax.swing.JLabel lb_ijt1;
    private javax.swing.JLabel lb_ijt2;
    private javax.swing.JLabel lb_ijt3;
    private javax.swing.JLabel lb_ijt4;
    private javax.swing.JLabel lb_jumlahguru;
    private javax.swing.JLabel lb_jumlahkelas;
    private javax.swing.JLabel lb_jumlahsiswa;
    private javax.swing.JLabel lb_sab;
    private javax.swing.JLabel lb_sah;
    private javax.swing.JLabel lb_sam;
    private javax.swing.JLabel lb_sas;
    private javax.swing.JLabel lb_sjb1;
    private javax.swing.JLabel lb_sjb2;
    private javax.swing.JLabel lb_sjb3;
    private javax.swing.JLabel lb_sjb4;
    private javax.swing.JLabel lb_sjo1;
    private javax.swing.JLabel lb_sjo2;
    private javax.swing.JLabel lb_sjo3;
    private javax.swing.JLabel lb_sjo4;
    private javax.swing.JLabel lb_sjt1;
    private javax.swing.JLabel lb_sjt2;
    private javax.swing.JLabel lb_sjt3;
    private javax.swing.JLabel lb_sjt4;
    private javax.swing.JLabel lb_tab;
    private javax.swing.JLabel lb_tah;
    private javax.swing.JLabel lb_tam;
    private javax.swing.JLabel lb_tas;
    private javax.swing.JLabel lb_tjb1;
    private javax.swing.JLabel lb_tjb2;
    private javax.swing.JLabel lb_tjb3;
    private javax.swing.JLabel lb_tjb4;
    private javax.swing.JLabel lb_tjo1;
    private javax.swing.JLabel lb_tjo2;
    private javax.swing.JLabel lb_tjo3;
    private javax.swing.JLabel lb_tjo4;
    private javax.swing.JLabel lb_tjt1;
    private javax.swing.JLabel lb_tjt2;
    private javax.swing.JLabel lb_tjt3;
    private javax.swing.JLabel lb_tjt4;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JLabel lbl_waktu;
    private javax.swing.JPanel panel_waktu;
    private javax.swing.JPanel panelchartabsbulanan;
    private javax.swing.JPanel panelchartabsbulanjurusan;
    private javax.swing.JPanel panelchartabsharian;
    private javax.swing.JPanel panelchartabsharijurusan;
    private javax.swing.JPanel panelchartabsmingguan;
    private javax.swing.JPanel panelchartabsminggujurusan;
    private javax.swing.JPanel panelchartabssemester;
    private javax.swing.JPanel panelchartabssmtjurusan;
    private javax.swing.JPanel paneldashboard;
    private javax.swing.JPanel panelgrafik;
    private javax.swing.JPanel paneljumlahkelas;
    private javax.swing.JLabel txt_jam;
    private javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
