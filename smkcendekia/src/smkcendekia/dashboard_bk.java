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
public class dashboard_bk extends javax.swing.JFrame {
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
    Object[ ] obj2 = new Object[10];
    Object[ ] obj3 = new Object[11];
    //
    public dashboard_bk() {
        initComponents();       
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        //RUN QUERY DEFAULT
        defaulttanggal();
        tanggal();
        tampil_jam();
        
        username.setText(Session.getusername());
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
            dataabsensisiswaharianangkatan10();
            dataabsensisiswaharianangkatan11();
            dataabsensisiswaharianangkatan12();
            chartsemuasiswaharianangkatan();
        //MINGGU
            dataabsensisiswamingguanangkatan10();
            dataabsensisiswamingguanangkatan11();
            dataabsensisiswamingguanangkatan12();
            chartsemuasiswamingguanangkatan();
        //BULAN
            dataabsensisiswabulananangkatan10();
            dataabsensisiswabulananangkatan11();
            dataabsensisiswabulananangkatan12();
            chartsemuasiswabulananangkatan();
        //SEMESTER
            dataabsensiangkatan10();
            dataabsensiangkatan11();
            dataabsensiangkatan12();
            chartangkatan();
    
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
        paneljumlahguru = new javax.swing.JPanel();
        lb_jumlahguru = new javax.swing.JLabel();
        paneljumlahsiswa = new javax.swing.JPanel();
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
        lb_sakitabsenmingguantitl = new javax.swing.JLabel();
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
        lb_alphaabsenmingguantitl = new javax.swing.JLabel();
        lb_hadirabsenmingguantitl = new javax.swing.JLabel();
        lb_terlambatabsenmingguantitl = new javax.swing.JLabel();
        lb_izinabsenmingguantitl = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        lb_sakitabsenmingguanotkp = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        lb_alphaabsenmingguanotkp = new javax.swing.JLabel();
        lb_hadirabsenmingguanotkp = new javax.swing.JLabel();
        lb_terlambatabsenmingguanotkp = new javax.swing.JLabel();
        lb_izinabsenmingguanotkp = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        lb_sakitabsenmingguanbdp = new javax.swing.JLabel();
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
        lb_alphaabsenmingguanbdp = new javax.swing.JLabel();
        lb_hadirabsenmingguanbdp = new javax.swing.JLabel();
        lb_terlambatabsenmingguanbdp = new javax.swing.JLabel();
        lb_izinabsenmingguanbdp = new javax.swing.JLabel();
        panelchartabsbulanjurusan = new javax.swing.JPanel();
        chartabsensibulanjurusan = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        lb_sakitabsenbulantitl = new javax.swing.JLabel();
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
        lb_alphaabsenbulantitl = new javax.swing.JLabel();
        lb_hadirabsenbulantitl = new javax.swing.JLabel();
        lb_terlambatabsenbulantitl = new javax.swing.JLabel();
        lb_izinabsenbulantitl = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        lb_sakitabsenbulanotkp = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        lb_alphaabsenbulanotkp = new javax.swing.JLabel();
        lb_hadirabsenbulanotkp = new javax.swing.JLabel();
        lb_terlambatabsenbulanotkp = new javax.swing.JLabel();
        lb_izinabsenbulanotkp = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        lb_sakitabsenbulanbdp = new javax.swing.JLabel();
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
        lb_alphaabsenbulanbdp = new javax.swing.JLabel();
        lb_hadirabsenbulanbdp = new javax.swing.JLabel();
        lb_terlambatabsenbulanbdp = new javax.swing.JLabel();
        lb_izinabsenbulanbdp = new javax.swing.JLabel();
        panelchartabssmtjurusan = new javax.swing.JPanel();
        chartabsensissmtjurusan = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jPanel83 = new javax.swing.JPanel();
        jPanel84 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jPanel86 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        lb_sakitabsensmttitl = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        lb_alphaabsensmttitl = new javax.swing.JLabel();
        lb_hadirabsensmttitl = new javax.swing.JLabel();
        lb_terlambatabsensmttitl = new javax.swing.JLabel();
        lb_izinabsensmttitl = new javax.swing.JLabel();
        jPanel87 = new javax.swing.JPanel();
        jPanel88 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jPanel90 = new javax.swing.JPanel();
        jPanel91 = new javax.swing.JPanel();
        jLabel185 = new javax.swing.JLabel();
        lb_sakitabsensmtotkp = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        lb_alphaabsensmtotkp = new javax.swing.JLabel();
        lb_hadirabsensmtotkp = new javax.swing.JLabel();
        lb_terlambatabsensmtotkp = new javax.swing.JLabel();
        lb_izinabsensmtotkp = new javax.swing.JLabel();
        jPanel97 = new javax.swing.JPanel();
        jPanel98 = new javax.swing.JPanel();
        jPanel99 = new javax.swing.JPanel();
        jPanel100 = new javax.swing.JPanel();
        jPanel101 = new javax.swing.JPanel();
        jLabel200 = new javax.swing.JLabel();
        lb_sakitabsensmtbdp = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        lb_alphaabsensmtbdp = new javax.swing.JLabel();
        lb_hadirabsensmtbdp = new javax.swing.JLabel();
        lb_terlambatabsensmtbdp = new javax.swing.JLabel();
        lb_izinabsensmtbdp = new javax.swing.JLabel();
        panelchartabsharianangkatan = new javax.swing.JPanel();
        chartabsensisharianangkatan = new javax.swing.JPanel();
        jPanel142 = new javax.swing.JPanel();
        jPanel143 = new javax.swing.JPanel();
        jPanel144 = new javax.swing.JPanel();
        jPanel145 = new javax.swing.JPanel();
        jPanel146 = new javax.swing.JPanel();
        jLabel305 = new javax.swing.JLabel();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        jLabel313 = new javax.swing.JLabel();
        jLabel314 = new javax.swing.JLabel();
        jLabel315 = new javax.swing.JLabel();
        jLabel316 = new javax.swing.JLabel();
        lb_sakitabsenhariangkatan10 = new javax.swing.JLabel();
        lb_alphaabsenhariangkatan10 = new javax.swing.JLabel();
        lb_hadirabsenhariangkatan10 = new javax.swing.JLabel();
        lb_terlambatabsenhariangkatan10 = new javax.swing.JLabel();
        lb_izinabsenhariangkatan10 = new javax.swing.JLabel();
        jPanel147 = new javax.swing.JPanel();
        jPanel148 = new javax.swing.JPanel();
        jPanel149 = new javax.swing.JPanel();
        jPanel150 = new javax.swing.JPanel();
        jPanel151 = new javax.swing.JPanel();
        jLabel317 = new javax.swing.JLabel();
        lb_sakitabsenhariangkatan12 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jLabel319 = new javax.swing.JLabel();
        jLabel320 = new javax.swing.JLabel();
        jLabel321 = new javax.swing.JLabel();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        jLabel326 = new javax.swing.JLabel();
        lb_alphaabsenhariangkatan12 = new javax.swing.JLabel();
        lb_hadirabsenhariangkatan12 = new javax.swing.JLabel();
        lb_terlambatabsenhariangkatan12 = new javax.swing.JLabel();
        lb_izinabsenhariangkatan12 = new javax.swing.JLabel();
        jPanel152 = new javax.swing.JPanel();
        jPanel153 = new javax.swing.JPanel();
        jPanel154 = new javax.swing.JPanel();
        jPanel155 = new javax.swing.JPanel();
        jPanel156 = new javax.swing.JPanel();
        jLabel327 = new javax.swing.JLabel();
        jLabel328 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        jLabel330 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        jLabel332 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        jLabel336 = new javax.swing.JLabel();
        jLabel337 = new javax.swing.JLabel();
        lb_hadirabsenhariangkatan11 = new javax.swing.JLabel();
        lb_sakitabsenhariangkatan11 = new javax.swing.JLabel();
        lb_alphaabsenhariangkatan11 = new javax.swing.JLabel();
        lb_izinabsenhariangkatan11 = new javax.swing.JLabel();
        lb_terlambatabsenhariangkatan11 = new javax.swing.JLabel();
        panelchartabsmingguanangkatan = new javax.swing.JPanel();
        chartmingguanangkatan = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
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
        lb_sakitabsenmingguanangkatan10 = new javax.swing.JLabel();
        lb_alphaabsenmingguanangkatan10 = new javax.swing.JLabel();
        lb_hadirabsenmingguanangkatan10 = new javax.swing.JLabel();
        lb_terlambatabsenmingguanangkatan10 = new javax.swing.JLabel();
        lb_izinabsenmingguanangkatan10 = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        lb_sakitabsenmingguanangkatan12 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        lb_alphaabsenmingguanangkatan12 = new javax.swing.JLabel();
        lb_hadirabsenmingguanangkatan12 = new javax.swing.JLabel();
        lb_terlambatabsenmingguanangkatan12 = new javax.swing.JLabel();
        lb_izinabsenmingguanangkatan12 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
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
        lb_hadirabsenmingguanangkatan11 = new javax.swing.JLabel();
        lb_sakitabsenmingguaniangkatan11 = new javax.swing.JLabel();
        lb_alphaabsenmingguaangkatan11 = new javax.swing.JLabel();
        lb_izinabsenmingguaniangkatan11 = new javax.swing.JLabel();
        lb_terlambatabsenmingguanangkatan11 = new javax.swing.JLabel();
        panelchartabsbulanangkatan = new javax.swing.JPanel();
        chartabsensisbulananangkatan = new javax.swing.JPanel();
        jPanel157 = new javax.swing.JPanel();
        jPanel158 = new javax.swing.JPanel();
        jPanel159 = new javax.swing.JPanel();
        jPanel160 = new javax.swing.JPanel();
        jPanel161 = new javax.swing.JPanel();
        jLabel338 = new javax.swing.JLabel();
        jLabel339 = new javax.swing.JLabel();
        jLabel340 = new javax.swing.JLabel();
        jLabel341 = new javax.swing.JLabel();
        jLabel342 = new javax.swing.JLabel();
        jLabel343 = new javax.swing.JLabel();
        jLabel344 = new javax.swing.JLabel();
        jLabel345 = new javax.swing.JLabel();
        jLabel346 = new javax.swing.JLabel();
        jLabel347 = new javax.swing.JLabel();
        jLabel348 = new javax.swing.JLabel();
        jLabel349 = new javax.swing.JLabel();
        lb_sakitabsenbulanangkatan10 = new javax.swing.JLabel();
        lb_alphaabsenbulanangkatan10 = new javax.swing.JLabel();
        lb_hadirabsenbulanangkatan10 = new javax.swing.JLabel();
        lb_terlambatabsenbulanangkatan10 = new javax.swing.JLabel();
        lb_izinabsenbulanangkatan10 = new javax.swing.JLabel();
        jPanel162 = new javax.swing.JPanel();
        jPanel163 = new javax.swing.JPanel();
        jPanel164 = new javax.swing.JPanel();
        jPanel165 = new javax.swing.JPanel();
        jPanel166 = new javax.swing.JPanel();
        jLabel350 = new javax.swing.JLabel();
        lb_sakitabsenbulaniangkatan12 = new javax.swing.JLabel();
        jLabel351 = new javax.swing.JLabel();
        jLabel352 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        jLabel354 = new javax.swing.JLabel();
        jLabel355 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        jLabel357 = new javax.swing.JLabel();
        jLabel358 = new javax.swing.JLabel();
        jLabel359 = new javax.swing.JLabel();
        lb_alphaabsenbulaniangkatan12 = new javax.swing.JLabel();
        lb_hadirabsenbulanangkatan12 = new javax.swing.JLabel();
        lb_terlambatabsenbulaniangkatan12 = new javax.swing.JLabel();
        lb_izinabsenbulaniangkatan12 = new javax.swing.JLabel();
        jPanel167 = new javax.swing.JPanel();
        jPanel168 = new javax.swing.JPanel();
        jPanel169 = new javax.swing.JPanel();
        jPanel170 = new javax.swing.JPanel();
        jPanel171 = new javax.swing.JPanel();
        jLabel360 = new javax.swing.JLabel();
        jLabel361 = new javax.swing.JLabel();
        jLabel362 = new javax.swing.JLabel();
        jLabel363 = new javax.swing.JLabel();
        jLabel364 = new javax.swing.JLabel();
        jLabel365 = new javax.swing.JLabel();
        jLabel366 = new javax.swing.JLabel();
        jLabel367 = new javax.swing.JLabel();
        jLabel368 = new javax.swing.JLabel();
        jLabel369 = new javax.swing.JLabel();
        jLabel370 = new javax.swing.JLabel();
        lb_hadirabsenbulanangkatan11 = new javax.swing.JLabel();
        lb_sakitabsenbulanangkatan11 = new javax.swing.JLabel();
        lb_alphaabsenbulanangkatan11 = new javax.swing.JLabel();
        lb_izinabsenbulanangkatan11 = new javax.swing.JLabel();
        lb_terlambatabsenbulanangkatan11 = new javax.swing.JLabel();
        panelchartabssmtangkatan = new javax.swing.JPanel();
        chartabsensissmtnangkatan = new javax.swing.JPanel();
        jPanel172 = new javax.swing.JPanel();
        jPanel173 = new javax.swing.JPanel();
        jPanel174 = new javax.swing.JPanel();
        jPanel175 = new javax.swing.JPanel();
        jPanel176 = new javax.swing.JPanel();
        jLabel371 = new javax.swing.JLabel();
        jLabel372 = new javax.swing.JLabel();
        jLabel373 = new javax.swing.JLabel();
        jLabel374 = new javax.swing.JLabel();
        jLabel375 = new javax.swing.JLabel();
        jLabel376 = new javax.swing.JLabel();
        jLabel377 = new javax.swing.JLabel();
        jLabel378 = new javax.swing.JLabel();
        jLabel379 = new javax.swing.JLabel();
        jLabel380 = new javax.swing.JLabel();
        jLabel381 = new javax.swing.JLabel();
        jLabel382 = new javax.swing.JLabel();
        lb_sakitabsensmtangkatan10 = new javax.swing.JLabel();
        lb_alphaabsensmtangkatan10 = new javax.swing.JLabel();
        lb_hadirabsensmtangkatan10 = new javax.swing.JLabel();
        lb_terlambatabsensmtangkatan10 = new javax.swing.JLabel();
        lb_izinabsensmtangkatan10 = new javax.swing.JLabel();
        jPanel177 = new javax.swing.JPanel();
        jPanel178 = new javax.swing.JPanel();
        jPanel179 = new javax.swing.JPanel();
        jPanel180 = new javax.swing.JPanel();
        jPanel181 = new javax.swing.JPanel();
        jLabel383 = new javax.swing.JLabel();
        lb_sakitabsensmtangkatan12 = new javax.swing.JLabel();
        jLabel384 = new javax.swing.JLabel();
        jLabel385 = new javax.swing.JLabel();
        jLabel386 = new javax.swing.JLabel();
        jLabel387 = new javax.swing.JLabel();
        jLabel388 = new javax.swing.JLabel();
        jLabel389 = new javax.swing.JLabel();
        jLabel390 = new javax.swing.JLabel();
        jLabel391 = new javax.swing.JLabel();
        jLabel392 = new javax.swing.JLabel();
        lb_alphaabsensmtangkatan12 = new javax.swing.JLabel();
        lb_hadirabsensmtangkatan12 = new javax.swing.JLabel();
        lb_terlambatabsensmtangkatan12 = new javax.swing.JLabel();
        lb_izinabsensmtangkatan12 = new javax.swing.JLabel();
        jPanel182 = new javax.swing.JPanel();
        jPanel183 = new javax.swing.JPanel();
        jPanel184 = new javax.swing.JPanel();
        jPanel185 = new javax.swing.JPanel();
        jPanel186 = new javax.swing.JPanel();
        jLabel393 = new javax.swing.JLabel();
        jLabel394 = new javax.swing.JLabel();
        jLabel395 = new javax.swing.JLabel();
        jLabel396 = new javax.swing.JLabel();
        jLabel397 = new javax.swing.JLabel();
        jLabel398 = new javax.swing.JLabel();
        jLabel399 = new javax.swing.JLabel();
        jLabel400 = new javax.swing.JLabel();
        jLabel401 = new javax.swing.JLabel();
        jLabel402 = new javax.swing.JLabel();
        jLabel403 = new javax.swing.JLabel();
        lb_hadirabsensmtangkatan11 = new javax.swing.JLabel();
        lb_sakitabsensmtangkatan11 = new javax.swing.JLabel();
        lb_alphaabsensmtangkatan11 = new javax.swing.JLabel();
        lb_izinabsensmtangkatan11 = new javax.swing.JLabel();
        lb_terlambatabsensmtangkatan11 = new javax.swing.JLabel();
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
        btn_kelolaabsensi = new javax.swing.JPanel();
        lb_kelolaabsensi = new javax.swing.JLabel();
        icon_kelolaabsensi = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JPanel();
        lb_keluar = new javax.swing.JLabel();
        icon_keluar = new javax.swing.JLabel();
        btn_editprofile = new javax.swing.JLabel();
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
        setResizable(false);
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
        lb_jumlahkelas.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 24)); // NOI18N
        lb_jumlahkelas.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahkelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paneljumlahkelas.add(lb_jumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(paneljumlahkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 260, 50));

        paneljumlahguru.setBackground(new java.awt.Color(0, 179, 131));
        paneljumlahguru.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahguru.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahguru.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 24)); // NOI18N
        lb_jumlahguru.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahguru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paneljumlahguru.add(lb_jumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(paneljumlahguru, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 260, 50));

        paneljumlahsiswa.setBackground(new java.awt.Color(162, 32, 195));
        paneljumlahsiswa.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 12)); // NOI18N
        paneljumlahsiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_jumlahsiswa.setBackground(new java.awt.Color(255, 255, 255));
        lb_jumlahsiswa.setFont(new java.awt.Font("Roboto Slab SemiBold", 0, 24)); // NOI18N
        lb_jumlahsiswa.setForeground(new java.awt.Color(255, 255, 255));
        lb_jumlahsiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paneljumlahsiswa.add(lb_jumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        paneldashboard.add(paneljumlahsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, 50));

        Scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        Scrollpane.setBorder(null);
        Scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Scrollpane.setOpaque(false);
        Scrollpane.setPreferredSize(new java.awt.Dimension(930, 5000));

        panelgrafik.setBackground(new java.awt.Color(255, 255, 255));
        panelgrafik.setPreferredSize(new java.awt.Dimension(930, 5500));
        panelgrafik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelchartabsharian.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsharian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharian.setBackground(new java.awt.Color(255, 255, 255));
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

        panelchartabsharian.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 13, 13));

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

        panelchartabsharian.add(jPanel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, 13, 13));

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

        panelchartabsharian.add(jPanel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 13, 13));

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

        panelchartabsharian.add(jPanel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, 13, 13));

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

        panelchartabsharian.add(jPanel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, 13, 13));

        jLabel162.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel162.setText("Terlambat");
        panelchartabsharian.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, -1, -1));

        lb_sah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_sah, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 70, 18));

        jLabel163.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel163.setText("Sakit");
        panelchartabsharian.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        jLabel164.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel164.setText("Izin");
        panelchartabsharian.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, -1));

        jLabel165.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel165.setText("Alpha");
        panelchartabsharian.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, -1, -1));

        jLabel166.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel166.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsharian.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, -1, -1));

        jLabel168.setText(":");
        panelchartabsharian.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, -1, -1));

        jLabel169.setText(":");
        panelchartabsharian.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, -1, -1));

        jLabel170.setText(":");
        panelchartabsharian.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, -1));

        jLabel171.setText(":");
        panelchartabsharian.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        jLabel172.setText(":");
        panelchartabsharian.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, -1, -1));

        jLabel173.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel173.setText("Hadir");
        panelchartabsharian.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, -1, -1));

        lb_aah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_aah, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 70, 18));

        lb_hah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_hah, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, 70, 18));

        lb_tah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_tah, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, 70, 18));

        lb_iah.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharian.add(lb_iah, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 70, 18));

        panelgrafik.add(panelchartabsharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 440));

        panelchartabsmingguan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsmingguan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensimingguan.setBackground(new java.awt.Color(255, 255, 255));
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

        panelchartabsmingguan.add(jPanel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 13, 13));

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

        panelchartabsmingguan.add(jPanel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 13, 13));

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

        panelchartabsmingguan.add(jPanel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 13, 13));

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

        panelchartabsmingguan.add(jPanel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 13, 13));

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

        panelchartabsmingguan.add(jPanel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 13, 13));

        jLabel195.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel195.setText("Terlambat");
        panelchartabsmingguan.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, -1, -1));

        lb_sam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_sam, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 70, 18));

        jLabel196.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel196.setText("Sakit");
        panelchartabsmingguan.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, -1));

        jLabel197.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel197.setText("Izin");
        panelchartabsmingguan.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, -1, -1));

        jLabel198.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel198.setText("Alpha");
        panelchartabsmingguan.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, -1, -1));

        jLabel199.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel199.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsmingguan.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, -1));

        jLabel201.setText(":");
        panelchartabsmingguan.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, -1, -1));

        jLabel202.setText(":");
        panelchartabsmingguan.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, -1, -1));

        jLabel203.setText(":");
        panelchartabsmingguan.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 160, -1, -1));

        jLabel204.setText(":");
        panelchartabsmingguan.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, -1, -1));

        jLabel205.setText(":");
        panelchartabsmingguan.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, -1, -1));

        jLabel206.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel206.setText("Hadir");
        panelchartabsmingguan.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, -1, -1));

        lb_aam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_aam, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 70, 18));

        lb_ham.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_ham, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 70, 18));

        lb_tam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_tam, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 70, 18));

        lb_iam.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguan.add(lb_iam, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, 70, 18));

        panelgrafik.add(panelchartabsmingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 455, 930, 440));

        panelchartabsbulanan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsbulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensibulanan.setBackground(new java.awt.Color(255, 255, 255));
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

        panelchartabsbulanan.add(jPanel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 13, 13));

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

        panelchartabsbulanan.add(jPanel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 13, 13));

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

        panelchartabsbulanan.add(jPanel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, 13, 13));

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

        panelchartabsbulanan.add(jPanel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, 13, 13));

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

        panelchartabsbulanan.add(jPanel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 13, 13));

        jLabel228.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel228.setText("Terlambat");
        panelchartabsbulanan.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, -1, -1));

        lb_sab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_sab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, 70, 18));

        jLabel229.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel229.setText("Sakit");
        panelchartabsbulanan.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, -1, -1));

        jLabel230.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel230.setText("Izin");
        panelchartabsbulanan.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        jLabel231.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel231.setText("Alpha");
        panelchartabsbulanan.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, -1));

        jLabel232.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel232.setText("KEHADIRAN SELURUH SISWA");
        panelchartabsbulanan.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, -1, -1));

        jLabel234.setText(":");
        panelchartabsbulanan.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, -1, -1));

        jLabel235.setText(":");
        panelchartabsbulanan.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        jLabel236.setText(":");
        panelchartabsbulanan.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        jLabel237.setText(":");
        panelchartabsbulanan.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, -1, -1));

        jLabel238.setText(":");
        panelchartabsbulanan.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, -1));

        jLabel239.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel239.setText("Hadir");
        panelchartabsbulanan.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, -1));

        lb_aab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_aab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 70, 18));

        lb_hab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_hab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 70, 18));

        lb_tab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 70, 18));

        lb_iab.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_iab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanan.add(lb_iab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 70, 18));

        panelgrafik.add(panelchartabsbulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 910, 930, 440));

        panelchartabssemester.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabssemester.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensisemester.setBackground(new java.awt.Color(255, 255, 255));
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

        panelchartabssemester.add(jPanel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 13, 13));

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

        panelchartabssemester.add(jPanel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 13, 13));

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

        panelchartabssemester.add(jPanel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 13, 13));

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

        panelchartabssemester.add(jPanel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 13, 13));

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

        panelchartabssemester.add(jPanel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 13, 13));

        jLabel261.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel261.setText("Terlambat");
        panelchartabssemester.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, -1, -1));

        lb_sas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_sas, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 70, 18));

        jLabel262.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel262.setText("Sakit");
        panelchartabssemester.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, -1));

        jLabel263.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel263.setText("Izin");
        panelchartabssemester.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, -1, -1));

        jLabel264.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel264.setText("Alpha");
        panelchartabssemester.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, -1, -1));

        jLabel265.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel265.setText("KEHADIRAN SELURUH SISWA");
        panelchartabssemester.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, -1, -1));

        jLabel267.setText(":");
        panelchartabssemester.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, -1, -1));

        jLabel268.setText(":");
        panelchartabssemester.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, -1, -1));

        jLabel269.setText(":");
        panelchartabssemester.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 160, -1, -1));

        jLabel270.setText(":");
        panelchartabssemester.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, -1, -1));

        jLabel271.setText(":");
        panelchartabssemester.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, -1, -1));

        jLabel272.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel272.setText("Hadir");
        panelchartabssemester.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, -1, -1));

        lb_aas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_aas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_aas, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 70, 18));

        lb_has.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_has.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_has, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 70, 18));

        lb_tas.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_tas, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 70, 18));

        lb_ias.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssemester.add(lb_ias, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, 70, 18));

        panelgrafik.add(panelchartabssemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1365, 930, 440));

        panelchartabsharijurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsharijurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiharijurusan.setBackground(new java.awt.Color(255, 255, 255));
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

        panelchartabsharijurusan.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 13, 13));

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

        panelchartabsharijurusan.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 13, 13));

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

        panelchartabsharijurusan.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 13, 13));

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

        panelchartabsharijurusan.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 13, 13));

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

        panelchartabsharijurusan.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 13, 13));

        jLabel30.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel30.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, -1, -1));

        lb_sjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 70, 18));

        jLabel32.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel32.setText("Sakit");
        panelchartabsharijurusan.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, -1, -1));

        jLabel33.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel33.setText("Izin");
        panelchartabsharijurusan.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, -1, -1));

        jLabel34.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel34.setText("Alpha");
        panelchartabsharijurusan.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, -1, -1));

        jLabel35.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel35.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsharijurusan.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        jLabel36.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel36.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsharijurusan.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, -1, -1));

        jLabel37.setText(":");
        panelchartabsharijurusan.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, -1, -1));

        jLabel38.setText(":");
        panelchartabsharijurusan.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        jLabel39.setText(":");
        panelchartabsharijurusan.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, -1, -1));

        jLabel40.setText(":");
        panelchartabsharijurusan.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, -1, -1));

        jLabel41.setText(":");
        panelchartabsharijurusan.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, -1, -1));

        jLabel52.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel52.setText("Hadir");
        panelchartabsharijurusan.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, -1, -1));

        lb_ajt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 70, 18));

        lb_hjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 70, 18));

        lb_tjt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 70, 18));

        lb_ijt1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 70, 18));

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

        panelchartabsharijurusan.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 320, 13, 13));

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

        panelchartabsharijurusan.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 13, 13));

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

        panelchartabsharijurusan.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 13, 13));

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

        panelchartabsharijurusan.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 13, 13));

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

        panelchartabsharijurusan.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 13, 13));

        jLabel31.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel31.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, -1, -1));

        lb_sjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 70, 18));

        jLabel53.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel53.setText("Sakit");
        panelchartabsharijurusan.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, -1, -1));

        jLabel54.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel54.setText("Izin");
        panelchartabsharijurusan.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, -1, -1));

        jLabel55.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel55.setText("Alpha");
        panelchartabsharijurusan.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, -1, -1));

        jLabel56.setText(":");
        panelchartabsharijurusan.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, -1, -1));

        jLabel57.setText(":");
        panelchartabsharijurusan.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, -1, -1));

        jLabel58.setText(":");
        panelchartabsharijurusan.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, -1));

        jLabel59.setText(":");
        panelchartabsharijurusan.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, -1, -1));

        jLabel60.setText(":");
        panelchartabsharijurusan.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, -1, -1));

        jLabel61.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel61.setText("Hadir");
        panelchartabsharijurusan.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, -1, -1));

        lb_ajo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 380, 70, 18));

        lb_hjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 70, 18));

        lb_tjo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 400, 70, 18));

        lb_ijo1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 360, 70, 18));

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

        panelchartabsharijurusan.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 13, 13));

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

        panelchartabsharijurusan.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 13, 13));

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

        panelchartabsharijurusan.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 13, 13));

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

        panelchartabsharijurusan.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 13, 13));

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

        panelchartabsharijurusan.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 13, 13));

        jLabel47.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel47.setText("Terlambat");
        panelchartabsharijurusan.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, -1, -1));

        lb_sjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_sjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, 70, 18));

        jLabel48.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel48.setText("Sakit");
        panelchartabsharijurusan.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

        jLabel49.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel49.setText("Izin");
        panelchartabsharijurusan.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, -1, -1));

        jLabel50.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel50.setText("Alpha");
        panelchartabsharijurusan.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, -1, -1));

        jLabel51.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel51.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsharijurusan.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jLabel62.setText(":");
        panelchartabsharijurusan.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, -1, -1));

        jLabel63.setText(":");
        panelchartabsharijurusan.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, -1, -1));

        jLabel64.setText(":");
        panelchartabsharijurusan.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, -1, -1));

        jLabel65.setText(":");
        panelchartabsharijurusan.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, -1, -1));

        jLabel66.setText(":");
        panelchartabsharijurusan.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, -1, -1));

        jLabel67.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel67.setText("Hadir");
        panelchartabsharijurusan.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, -1));

        lb_ajb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ajb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ajb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, 70, 18));

        lb_hjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_hjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 70, 18));

        lb_tjb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_tjb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_tjb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, 70, 18));

        lb_ijb1.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_ijb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharijurusan.add(lb_ijb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 70, 18));

        panelgrafik.add(panelchartabsharijurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1820, 930, 440));

        panelchartabsminggujurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsminggujurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensiminggujurusan.setBackground(new java.awt.Color(255, 255, 255));
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

        lb_sakitabsenmingguantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sakitabsenmingguantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel43.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel43.setText("Sakit");
        panelchartabsminggujurusan.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel44.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel44.setText("Izin");
        panelchartabsminggujurusan.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel45.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel45.setText("Alpha");
        panelchartabsminggujurusan.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel46.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel46.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsminggujurusan.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        jLabel68.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel68.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsminggujurusan.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, -1, -1));

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

        lb_alphaabsenmingguantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_alphaabsenmingguantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsenmingguantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hadirabsenmingguantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsenmingguantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_terlambatabsenmingguantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsenmingguantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_izinabsenmingguantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

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

        lb_sakitabsenmingguanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sakitabsenmingguanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

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

        lb_alphaabsenmingguanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_alphaabsenmingguanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsenmingguanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hadirabsenmingguanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsenmingguanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_terlambatabsenmingguanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsenmingguanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_izinabsenmingguanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

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

        lb_sakitabsenmingguanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_sakitabsenmingguanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel86.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel86.setText("Sakit");
        panelchartabsminggujurusan.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel87.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel87.setText("Izin");
        panelchartabsminggujurusan.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel88.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel88.setText("Alpha");
        panelchartabsminggujurusan.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel89.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel89.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsminggujurusan.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 144, -1, -1));

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

        lb_alphaabsenmingguanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_alphaabsenmingguanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hadirabsenmingguanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_hadirabsenmingguanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_terlambatabsenmingguanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_terlambatabsenmingguanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_izinabsenmingguanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsminggujurusan.add(lb_izinabsenmingguanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabsminggujurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2275, 930, 440));

        panelchartabsbulanjurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsbulanjurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensibulanjurusan.setBackground(new java.awt.Color(255, 255, 255));
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

        lb_sakitabsenbulantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sakitabsenbulantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel97.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel97.setText("Sakit");
        panelchartabsbulanjurusan.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel98.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel98.setText("Izin");
        panelchartabsbulanjurusan.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel99.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel99.setText("Alpha");
        panelchartabsbulanjurusan.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel100.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel100.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabsbulanjurusan.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        jLabel101.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel101.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabsbulanjurusan.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 284, -1, -1));

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

        lb_alphaabsenbulantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_alphaabsenbulantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsenbulantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hadirabsenbulantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsenbulantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_terlambatabsenbulantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsenbulantitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulantitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_izinabsenbulantitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

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

        lb_sakitabsenbulanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sakitabsenbulanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

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

        lb_alphaabsenbulanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_alphaabsenbulanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsenbulanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hadirabsenbulanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsenbulanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_terlambatabsenbulanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsenbulanotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulanotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_izinabsenbulanotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

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

        lb_sakitabsenbulanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_sakitabsenbulanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel119.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel119.setText("Sakit");
        panelchartabsbulanjurusan.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel120.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel120.setText("Izin");
        panelchartabsbulanjurusan.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel121.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel121.setText("Alpha");
        panelchartabsbulanjurusan.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel122.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel122.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabsbulanjurusan.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, -1, -1));

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

        lb_alphaabsenbulanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_alphaabsenbulanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hadirabsenbulanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_hadirabsenbulanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_terlambatabsenbulanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_terlambatabsenbulanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_izinabsenbulanbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulanbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanjurusan.add(lb_izinabsenbulanbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabsbulanjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2730, 930, 440));

        panelchartabssmtjurusan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabssmtjurusan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensissmtjurusan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensissmtjurusan.setLayout(new javax.swing.OverlayLayout(chartabsensissmtjurusan));
        panelchartabssmtjurusan.add(chartabsensissmtjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel82.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel83.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel84.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel85.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel86.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel167.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel167.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        lb_sakitabsensmttitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmttitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sakitabsensmttitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        jLabel174.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel174.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel175.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel175.setText("Izin");
        panelchartabssmtjurusan.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel176.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel176.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel177.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel177.setText("KEHADIARAN SISWA JURUSAN TITL");
        panelchartabssmtjurusan.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        jLabel178.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel178.setText("KEHADIARAN SISWA JURUSAN OTKP");
        panelchartabssmtjurusan.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 278, -1, -1));

        jLabel179.setText(":");
        panelchartabssmtjurusan.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel180.setText(":");
        panelchartabssmtjurusan.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel181.setText(":");
        panelchartabssmtjurusan.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel182.setText(":");
        panelchartabssmtjurusan.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel183.setText(":");
        panelchartabssmtjurusan.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel184.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel184.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_alphaabsensmttitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmttitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_alphaabsensmttitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsensmttitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmttitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hadirabsensmttitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsensmttitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmttitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_terlambatabsensmttitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsensmttitl.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmttitl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_izinabsensmttitl, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel87.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel87Layout = new javax.swing.GroupLayout(jPanel87);
        jPanel87.setLayout(jPanel87Layout);
        jPanel87Layout.setHorizontalGroup(
            jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel87Layout.setVerticalGroup(
            jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel88.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel89.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
        jPanel89.setLayout(jPanel89Layout);
        jPanel89Layout.setHorizontalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel90.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel90Layout = new javax.swing.GroupLayout(jPanel90);
        jPanel90.setLayout(jPanel90Layout);
        jPanel90Layout.setHorizontalGroup(
            jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel90Layout.setVerticalGroup(
            jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel91.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel91Layout = new javax.swing.GroupLayout(jPanel91);
        jPanel91.setLayout(jPanel91Layout);
        jPanel91Layout.setHorizontalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel91Layout.setVerticalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel185.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel185.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sakitabsensmtotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmtotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sakitabsensmtotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel186.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel186.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel187.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel187.setText("Izin");
        panelchartabssmtjurusan.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel188.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel188.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel189.setText(":");
        panelchartabssmtjurusan.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel190.setText(":");
        panelchartabssmtjurusan.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel191.setText(":");
        panelchartabssmtjurusan.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel192.setText(":");
        panelchartabssmtjurusan.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel193.setText(":");
        panelchartabssmtjurusan.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel194.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel194.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_alphaabsensmtotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmtotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_alphaabsensmtotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsensmtotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmtotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hadirabsensmtotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsensmtotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmtotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_terlambatabsensmtotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsensmtotkp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmtotkp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_izinabsensmtotkp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel97.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
        jPanel97.setLayout(jPanel97Layout);
        jPanel97Layout.setHorizontalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel97Layout.setVerticalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel98.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
        jPanel98.setLayout(jPanel98Layout);
        jPanel98Layout.setHorizontalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel98Layout.setVerticalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel99.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel99Layout = new javax.swing.GroupLayout(jPanel99);
        jPanel99.setLayout(jPanel99Layout);
        jPanel99Layout.setHorizontalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel99Layout.setVerticalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel100.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
        jPanel100.setLayout(jPanel100Layout);
        jPanel100Layout.setHorizontalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel100Layout.setVerticalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel101.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel101Layout = new javax.swing.GroupLayout(jPanel101);
        jPanel101.setLayout(jPanel101Layout);
        jPanel101Layout.setHorizontalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel101Layout.setVerticalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabssmtjurusan.add(jPanel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel200.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel200.setText("Terlambat");
        panelchartabssmtjurusan.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        lb_sakitabsensmtbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmtbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_sakitabsensmtbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        jLabel207.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel207.setText("Sakit");
        panelchartabssmtjurusan.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel208.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel208.setText("Izin");
        panelchartabssmtjurusan.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel209.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel209.setText("Alpha");
        panelchartabssmtjurusan.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel210.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel210.setText("KEHADIARAN SISWA JURUSAN BDP");
        panelchartabssmtjurusan.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 143, -1, -1));

        jLabel211.setText(":");
        panelchartabssmtjurusan.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel212.setText(":");
        panelchartabssmtjurusan.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel213.setText(":");
        panelchartabssmtjurusan.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel214.setText(":");
        panelchartabssmtjurusan.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel215.setText(":");
        panelchartabssmtjurusan.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel216.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel216.setText("Hadir");
        panelchartabssmtjurusan.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_alphaabsensmtbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmtbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_alphaabsensmtbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_hadirabsensmtbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmtbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_hadirabsensmtbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_terlambatabsensmtbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmtbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_terlambatabsensmtbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        lb_izinabsensmtbdp.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmtbdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtjurusan.add(lb_izinabsensmtbdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        panelgrafik.add(panelchartabssmtjurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3185, 930, 440));

        panelchartabsharianangkatan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsharianangkatan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensisharianangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensisharianangkatan.setLayout(new javax.swing.OverlayLayout(chartabsensisharianangkatan));
        panelchartabsharianangkatan.add(chartabsensisharianangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel142.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
        jPanel142.setLayout(jPanel142Layout);
        jPanel142Layout.setHorizontalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel142Layout.setVerticalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel143.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
        jPanel143.setLayout(jPanel143Layout);
        jPanel143Layout.setHorizontalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel143Layout.setVerticalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel144.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
        jPanel144.setLayout(jPanel144Layout);
        jPanel144Layout.setHorizontalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel144Layout.setVerticalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel145.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
        jPanel145.setLayout(jPanel145Layout);
        jPanel145Layout.setHorizontalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel145Layout.setVerticalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel146.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
        jPanel146.setLayout(jPanel146Layout);
        jPanel146Layout.setHorizontalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel146Layout.setVerticalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel305.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel305.setText("Terlambat");
        panelchartabsharianangkatan.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        jLabel306.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel306.setText("Sakit");
        panelchartabsharianangkatan.add(jLabel306, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel307.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel307.setText("Izin");
        panelchartabsharianangkatan.add(jLabel307, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel308.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel308.setText("Alpha");
        panelchartabsharianangkatan.add(jLabel308, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel309.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel309.setText("KEHADIARAN SISWA KELAS 10");
        panelchartabsharianangkatan.add(jLabel309, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jLabel310.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel310.setText("KEHADIARAN SISWA KELAS 12");
        panelchartabsharianangkatan.add(jLabel310, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, -1));

        jLabel311.setText(":");
        panelchartabsharianangkatan.add(jLabel311, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel312.setText(":");
        panelchartabsharianangkatan.add(jLabel312, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel313.setText(":");
        panelchartabsharianangkatan.add(jLabel313, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel314.setText(":");
        panelchartabsharianangkatan.add(jLabel314, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel315.setText(":");
        panelchartabsharianangkatan.add(jLabel315, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel316.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel316.setText("Hadir");
        panelchartabsharianangkatan.add(jLabel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_sakitabsenhariangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenhariangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_sakitabsenhariangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        lb_alphaabsenhariangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenhariangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_alphaabsenhariangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsenhariangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenhariangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_hadirabsenhariangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsenhariangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenhariangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_terlambatabsenhariangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsenhariangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenhariangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_izinabsenhariangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel147.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel147Layout = new javax.swing.GroupLayout(jPanel147);
        jPanel147.setLayout(jPanel147Layout);
        jPanel147Layout.setHorizontalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel147Layout.setVerticalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel148.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel148Layout = new javax.swing.GroupLayout(jPanel148);
        jPanel148.setLayout(jPanel148Layout);
        jPanel148Layout.setHorizontalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel148Layout.setVerticalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel149.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
        jPanel149.setLayout(jPanel149Layout);
        jPanel149Layout.setHorizontalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel149Layout.setVerticalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel150.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
        jPanel150.setLayout(jPanel150Layout);
        jPanel150Layout.setHorizontalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel150Layout.setVerticalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel151.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel151Layout = new javax.swing.GroupLayout(jPanel151);
        jPanel151.setLayout(jPanel151Layout);
        jPanel151Layout.setHorizontalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel151Layout.setVerticalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel317.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel317.setText("Terlambat");
        panelchartabsharianangkatan.add(jLabel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sakitabsenhariangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenhariangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_sakitabsenhariangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel318.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel318.setText("Sakit");
        panelchartabsharianangkatan.add(jLabel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel319.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel319.setText("Izin");
        panelchartabsharianangkatan.add(jLabel319, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel320.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel320.setText("Alpha");
        panelchartabsharianangkatan.add(jLabel320, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel321.setText(":");
        panelchartabsharianangkatan.add(jLabel321, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel322.setText(":");
        panelchartabsharianangkatan.add(jLabel322, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel323.setText(":");
        panelchartabsharianangkatan.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel324.setText(":");
        panelchartabsharianangkatan.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel325.setText(":");
        panelchartabsharianangkatan.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel326.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel326.setText("Hadir");
        panelchartabsharianangkatan.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_alphaabsenhariangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenhariangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_alphaabsenhariangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsenhariangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenhariangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_hadirabsenhariangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsenhariangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenhariangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_terlambatabsenhariangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsenhariangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenhariangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_izinabsenhariangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel152.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel152Layout = new javax.swing.GroupLayout(jPanel152);
        jPanel152.setLayout(jPanel152Layout);
        jPanel152Layout.setHorizontalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel152Layout.setVerticalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel153.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel153Layout = new javax.swing.GroupLayout(jPanel153);
        jPanel153.setLayout(jPanel153Layout);
        jPanel153Layout.setHorizontalGroup(
            jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel153Layout.setVerticalGroup(
            jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel154.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel154Layout = new javax.swing.GroupLayout(jPanel154);
        jPanel154.setLayout(jPanel154Layout);
        jPanel154Layout.setHorizontalGroup(
            jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel154Layout.setVerticalGroup(
            jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel155.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel155Layout = new javax.swing.GroupLayout(jPanel155);
        jPanel155.setLayout(jPanel155Layout);
        jPanel155Layout.setHorizontalGroup(
            jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel155Layout.setVerticalGroup(
            jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel156.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel156Layout = new javax.swing.GroupLayout(jPanel156);
        jPanel156.setLayout(jPanel156Layout);
        jPanel156Layout.setHorizontalGroup(
            jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel156Layout.setVerticalGroup(
            jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsharianangkatan.add(jPanel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel327.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel327.setText("Terlambat");
        panelchartabsharianangkatan.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        jLabel328.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel328.setText("Sakit");
        panelchartabsharianangkatan.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel329.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel329.setText("Izin");
        panelchartabsharianangkatan.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel330.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel330.setText("Alpha");
        panelchartabsharianangkatan.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel331.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel331.setText("KEHADIARAN SISWA KELAS 11");
        panelchartabsharianangkatan.add(jLabel331, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 143, -1, -1));

        jLabel332.setText(":");
        panelchartabsharianangkatan.add(jLabel332, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel333.setText(":");
        panelchartabsharianangkatan.add(jLabel333, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel334.setText(":");
        panelchartabsharianangkatan.add(jLabel334, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel335.setText(":");
        panelchartabsharianangkatan.add(jLabel335, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel336.setText(":");
        panelchartabsharianangkatan.add(jLabel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel337.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel337.setText("Hadir");
        panelchartabsharianangkatan.add(jLabel337, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_hadirabsenhariangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenhariangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_hadirabsenhariangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_sakitabsenhariangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenhariangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_sakitabsenhariangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        lb_alphaabsenhariangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenhariangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_alphaabsenhariangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_izinabsenhariangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenhariangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_izinabsenhariangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        lb_terlambatabsenhariangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenhariangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsharianangkatan.add(lb_terlambatabsenhariangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        panelgrafik.add(panelchartabsharianangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3640, 930, 440));

        panelchartabsmingguanangkatan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsmingguanangkatan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartmingguanangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartmingguanangkatan.setLayout(new javax.swing.OverlayLayout(chartmingguanangkatan));
        panelchartabsmingguanangkatan.add(chartmingguanangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

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

        panelchartabsmingguanangkatan.add(jPanel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel129.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel129.setText("Terlambat");
        panelchartabsmingguanangkatan.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        jLabel130.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel130.setText("Sakit");
        panelchartabsmingguanangkatan.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel131.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel131.setText("Izin");
        panelchartabsmingguanangkatan.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel132.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel132.setText("Alpha");
        panelchartabsmingguanangkatan.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel133.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel133.setText("KEHADIARAN SISWA KELAS 10");
        panelchartabsmingguanangkatan.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        jLabel134.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel134.setText("KEHADIARAN SISWA KELAS 12");
        panelchartabsmingguanangkatan.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 271, -1, 30));

        jLabel135.setText(":");
        panelchartabsmingguanangkatan.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel136.setText(":");
        panelchartabsmingguanangkatan.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel137.setText(":");
        panelchartabsmingguanangkatan.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel138.setText(":");
        panelchartabsmingguanangkatan.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel139.setText(":");
        panelchartabsmingguanangkatan.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel140.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel140.setText("Hadir");
        panelchartabsmingguanangkatan.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_sakitabsenmingguanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_sakitabsenmingguanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        lb_alphaabsenmingguanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_alphaabsenmingguanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsenmingguanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_hadirabsenmingguanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsenmingguanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_terlambatabsenmingguanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsenmingguanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_izinabsenmingguanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

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

        panelchartabsmingguanangkatan.add(jPanel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel141.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel141.setText("Terlambat");
        panelchartabsmingguanangkatan.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sakitabsenmingguanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_sakitabsenmingguanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel142.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel142.setText("Sakit");
        panelchartabsmingguanangkatan.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel143.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel143.setText("Izin");
        panelchartabsmingguanangkatan.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel144.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel144.setText("Alpha");
        panelchartabsmingguanangkatan.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel145.setText(":");
        panelchartabsmingguanangkatan.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel146.setText(":");
        panelchartabsmingguanangkatan.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel147.setText(":");
        panelchartabsmingguanangkatan.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel148.setText(":");
        panelchartabsmingguanangkatan.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel149.setText(":");
        panelchartabsmingguanangkatan.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel150.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel150.setText("Hadir");
        panelchartabsmingguanangkatan.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_alphaabsenmingguanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_alphaabsenmingguanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsenmingguanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_hadirabsenmingguanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsenmingguanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_terlambatabsenmingguanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsenmingguanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_izinabsenmingguanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

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

        panelchartabsmingguanangkatan.add(jPanel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

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

        panelchartabsmingguanangkatan.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel151.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel151.setText("Terlambat");
        panelchartabsmingguanangkatan.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        jLabel152.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel152.setText("Sakit");
        panelchartabsmingguanangkatan.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel153.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel153.setText("Izin");
        panelchartabsmingguanangkatan.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel154.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel154.setText("Alpha");
        panelchartabsmingguanangkatan.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel155.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel155.setText("KEHADIARAN SISWA KELAS 11");
        panelchartabsmingguanangkatan.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 137, -1, 30));

        jLabel156.setText(":");
        panelchartabsmingguanangkatan.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel157.setText(":");
        panelchartabsmingguanangkatan.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel158.setText(":");
        panelchartabsmingguanangkatan.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel159.setText(":");
        panelchartabsmingguanangkatan.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel160.setText(":");
        panelchartabsmingguanangkatan.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel161.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel161.setText("Hadir");
        panelchartabsmingguanangkatan.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_hadirabsenmingguanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenmingguanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_hadirabsenmingguanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_sakitabsenmingguaniangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenmingguaniangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_sakitabsenmingguaniangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        lb_alphaabsenmingguaangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenmingguaangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_alphaabsenmingguaangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_izinabsenmingguaniangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenmingguaniangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_izinabsenmingguaniangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        lb_terlambatabsenmingguanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenmingguanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsmingguanangkatan.add(lb_terlambatabsenmingguanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        panelgrafik.add(panelchartabsmingguanangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4095, 930, 440));

        panelchartabsbulanangkatan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabsbulanangkatan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensisbulananangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensisbulananangkatan.setLayout(new javax.swing.OverlayLayout(chartabsensisbulananangkatan));
        panelchartabsbulanangkatan.add(chartabsensisbulananangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel157.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
        jPanel157.setLayout(jPanel157Layout);
        jPanel157Layout.setHorizontalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel157Layout.setVerticalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel158.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
        jPanel158.setLayout(jPanel158Layout);
        jPanel158Layout.setHorizontalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel158Layout.setVerticalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel159.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
        jPanel159.setLayout(jPanel159Layout);
        jPanel159Layout.setHorizontalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel159Layout.setVerticalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel160.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
        jPanel160.setLayout(jPanel160Layout);
        jPanel160Layout.setHorizontalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel160Layout.setVerticalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel161.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel161Layout = new javax.swing.GroupLayout(jPanel161);
        jPanel161.setLayout(jPanel161Layout);
        jPanel161Layout.setHorizontalGroup(
            jPanel161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel161Layout.setVerticalGroup(
            jPanel161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel338.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel338.setText("Terlambat");
        panelchartabsbulanangkatan.add(jLabel338, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        jLabel339.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel339.setText("Sakit");
        panelchartabsbulanangkatan.add(jLabel339, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel340.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel340.setText("Izin");
        panelchartabsbulanangkatan.add(jLabel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel341.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel341.setText("Alpha");
        panelchartabsbulanangkatan.add(jLabel341, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel342.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel342.setText("KEHADIARAN SISWA KELAS 10");
        panelchartabsbulanangkatan.add(jLabel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        jLabel343.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel343.setText("KEHADIARAN SISWA KELAS 12");
        panelchartabsbulanangkatan.add(jLabel343, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 271, -1, 30));

        jLabel344.setText(":");
        panelchartabsbulanangkatan.add(jLabel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel345.setText(":");
        panelchartabsbulanangkatan.add(jLabel345, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel346.setText(":");
        panelchartabsbulanangkatan.add(jLabel346, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel347.setText(":");
        panelchartabsbulanangkatan.add(jLabel347, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel348.setText(":");
        panelchartabsbulanangkatan.add(jLabel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel349.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel349.setText("Hadir");
        panelchartabsbulanangkatan.add(jLabel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_sakitabsenbulanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_sakitabsenbulanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        lb_alphaabsenbulanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_alphaabsenbulanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsenbulanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_hadirabsenbulanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsenbulanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_terlambatabsenbulanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsenbulanangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulanangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_izinabsenbulanangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel162.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel162Layout = new javax.swing.GroupLayout(jPanel162);
        jPanel162.setLayout(jPanel162Layout);
        jPanel162Layout.setHorizontalGroup(
            jPanel162Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel162Layout.setVerticalGroup(
            jPanel162Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel163.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel163Layout = new javax.swing.GroupLayout(jPanel163);
        jPanel163.setLayout(jPanel163Layout);
        jPanel163Layout.setHorizontalGroup(
            jPanel163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel163Layout.setVerticalGroup(
            jPanel163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel164.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel164Layout = new javax.swing.GroupLayout(jPanel164);
        jPanel164.setLayout(jPanel164Layout);
        jPanel164Layout.setHorizontalGroup(
            jPanel164Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel164Layout.setVerticalGroup(
            jPanel164Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel165.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel165Layout = new javax.swing.GroupLayout(jPanel165);
        jPanel165.setLayout(jPanel165Layout);
        jPanel165Layout.setHorizontalGroup(
            jPanel165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel165Layout.setVerticalGroup(
            jPanel165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel166.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel166Layout = new javax.swing.GroupLayout(jPanel166);
        jPanel166.setLayout(jPanel166Layout);
        jPanel166Layout.setHorizontalGroup(
            jPanel166Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel166Layout.setVerticalGroup(
            jPanel166Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel350.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel350.setText("Terlambat");
        panelchartabsbulanangkatan.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sakitabsenbulaniangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulaniangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_sakitabsenbulaniangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 70, 18));

        jLabel351.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel351.setText("Sakit");
        panelchartabsbulanangkatan.add(jLabel351, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel352.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel352.setText("Izin");
        panelchartabsbulanangkatan.add(jLabel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel353.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel353.setText("Alpha");
        panelchartabsbulanangkatan.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel354.setText(":");
        panelchartabsbulanangkatan.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel355.setText(":");
        panelchartabsbulanangkatan.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel356.setText(":");
        panelchartabsbulanangkatan.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel357.setText(":");
        panelchartabsbulanangkatan.add(jLabel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel358.setText(":");
        panelchartabsbulanangkatan.add(jLabel358, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel359.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel359.setText("Hadir");
        panelchartabsbulanangkatan.add(jLabel359, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_alphaabsenbulaniangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulaniangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_alphaabsenbulaniangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsenbulanangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulanangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_hadirabsenbulanangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsenbulaniangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulaniangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_terlambatabsenbulaniangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsenbulaniangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulaniangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_izinabsenbulaniangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel167.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel167Layout = new javax.swing.GroupLayout(jPanel167);
        jPanel167.setLayout(jPanel167Layout);
        jPanel167Layout.setHorizontalGroup(
            jPanel167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel167Layout.setVerticalGroup(
            jPanel167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel168.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel168Layout = new javax.swing.GroupLayout(jPanel168);
        jPanel168.setLayout(jPanel168Layout);
        jPanel168Layout.setHorizontalGroup(
            jPanel168Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel168Layout.setVerticalGroup(
            jPanel168Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel169.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel169Layout = new javax.swing.GroupLayout(jPanel169);
        jPanel169.setLayout(jPanel169Layout);
        jPanel169Layout.setHorizontalGroup(
            jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel169Layout.setVerticalGroup(
            jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel170.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel170Layout = new javax.swing.GroupLayout(jPanel170);
        jPanel170.setLayout(jPanel170Layout);
        jPanel170Layout.setHorizontalGroup(
            jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel170Layout.setVerticalGroup(
            jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel171.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel171Layout = new javax.swing.GroupLayout(jPanel171);
        jPanel171.setLayout(jPanel171Layout);
        jPanel171Layout.setHorizontalGroup(
            jPanel171Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel171Layout.setVerticalGroup(
            jPanel171Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        panelchartabsbulanangkatan.add(jPanel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel360.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel360.setText("Terlambat");
        panelchartabsbulanangkatan.add(jLabel360, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        jLabel361.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel361.setText("Sakit");
        panelchartabsbulanangkatan.add(jLabel361, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel362.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel362.setText("Izin");
        panelchartabsbulanangkatan.add(jLabel362, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel363.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel363.setText("Alpha");
        panelchartabsbulanangkatan.add(jLabel363, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel364.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel364.setText("KEHADIARAN SISWA KELAS 11");
        panelchartabsbulanangkatan.add(jLabel364, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 137, -1, 30));

        jLabel365.setText(":");
        panelchartabsbulanangkatan.add(jLabel365, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel366.setText(":");
        panelchartabsbulanangkatan.add(jLabel366, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel367.setText(":");
        panelchartabsbulanangkatan.add(jLabel367, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel368.setText(":");
        panelchartabsbulanangkatan.add(jLabel368, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel369.setText(":");
        panelchartabsbulanangkatan.add(jLabel369, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel370.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel370.setText("Hadir");
        panelchartabsbulanangkatan.add(jLabel370, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_hadirabsenbulanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsenbulanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_hadirabsenbulanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_sakitabsenbulanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsenbulanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_sakitabsenbulanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        lb_alphaabsenbulanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsenbulanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_alphaabsenbulanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_izinabsenbulanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsenbulanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_izinabsenbulanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        lb_terlambatabsenbulanangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsenbulanangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabsbulanangkatan.add(lb_terlambatabsenbulanangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        panelgrafik.add(panelchartabsbulanangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4550, 930, 440));

        panelchartabssmtangkatan.setBackground(new java.awt.Color(255, 255, 255));
        panelchartabssmtangkatan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartabsensissmtnangkatan.setBackground(new java.awt.Color(255, 255, 255));
        chartabsensissmtnangkatan.setLayout(new javax.swing.OverlayLayout(chartabsensissmtnangkatan));
        panelchartabssmtangkatan.add(chartabsensissmtnangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 660, 430));

        jPanel172.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel172Layout = new javax.swing.GroupLayout(jPanel172);
        jPanel172.setLayout(jPanel172Layout);
        jPanel172Layout.setHorizontalGroup(
            jPanel172Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel172Layout.setVerticalGroup(
            jPanel172Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 13, 13));

        jPanel173.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel173Layout = new javax.swing.GroupLayout(jPanel173);
        jPanel173.setLayout(jPanel173Layout);
        jPanel173Layout.setHorizontalGroup(
            jPanel173Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel173Layout.setVerticalGroup(
            jPanel173Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 13, 13));

        jPanel174.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel174Layout = new javax.swing.GroupLayout(jPanel174);
        jPanel174.setLayout(jPanel174Layout);
        jPanel174Layout.setHorizontalGroup(
            jPanel174Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel174Layout.setVerticalGroup(
            jPanel174Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 13, 13));

        jPanel175.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel175Layout = new javax.swing.GroupLayout(jPanel175);
        jPanel175.setLayout(jPanel175Layout);
        jPanel175Layout.setHorizontalGroup(
            jPanel175Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel175Layout.setVerticalGroup(
            jPanel175Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 13, 13));

        jPanel176.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel176Layout = new javax.swing.GroupLayout(jPanel176);
        jPanel176.setLayout(jPanel176Layout);
        jPanel176Layout.setHorizontalGroup(
            jPanel176Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel176Layout.setVerticalGroup(
            jPanel176Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 13, 13));

        jLabel371.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel371.setText("Terlambat");
        panelchartabssmtangkatan.add(jLabel371, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        jLabel372.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel372.setText("Sakit");
        panelchartabssmtangkatan.add(jLabel372, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel373.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel373.setText("Izin");
        panelchartabssmtangkatan.add(jLabel373, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel374.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel374.setText("Alpha");
        panelchartabssmtangkatan.add(jLabel374, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jLabel375.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel375.setText("KEHADIARAN SISWA KELAS 10");
        panelchartabssmtangkatan.add(jLabel375, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, -3, -1, 40));

        jLabel376.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel376.setText("KEHADIARAN SISWA KELAS 12");
        panelchartabssmtangkatan.add(jLabel376, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 271, -1, 30));

        jLabel377.setText(":");
        panelchartabssmtangkatan.add(jLabel377, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jLabel378.setText(":");
        panelchartabssmtangkatan.add(jLabel378, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        jLabel379.setText(":");
        panelchartabssmtangkatan.add(jLabel379, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel380.setText(":");
        panelchartabssmtangkatan.add(jLabel380, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, -1));

        jLabel381.setText(":");
        panelchartabssmtangkatan.add(jLabel381, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 61, -1, -1));

        jLabel382.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel382.setText("Hadir");
        panelchartabssmtangkatan.add(jLabel382, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lb_sakitabsensmtangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmtangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_sakitabsensmtangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 70, 18));

        lb_alphaabsensmtangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmtangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_alphaabsensmtangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 70, 18));

        lb_hadirabsensmtangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmtangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_hadirabsensmtangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 70, 18));

        lb_terlambatabsensmtangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmtangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_terlambatabsensmtangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 70, 18));

        lb_izinabsensmtangkatan10.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmtangkatan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_izinabsensmtangkatan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 70, 18));

        jPanel177.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel177Layout = new javax.swing.GroupLayout(jPanel177);
        jPanel177.setLayout(jPanel177Layout);
        jPanel177Layout.setHorizontalGroup(
            jPanel177Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel177Layout.setVerticalGroup(
            jPanel177Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 13, 13));

        jPanel178.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel178Layout = new javax.swing.GroupLayout(jPanel178);
        jPanel178.setLayout(jPanel178Layout);
        jPanel178Layout.setHorizontalGroup(
            jPanel178Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel178Layout.setVerticalGroup(
            jPanel178Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 13, 13));

        jPanel179.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel179Layout = new javax.swing.GroupLayout(jPanel179);
        jPanel179.setLayout(jPanel179Layout);
        jPanel179Layout.setHorizontalGroup(
            jPanel179Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel179Layout.setVerticalGroup(
            jPanel179Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 13, 13));

        jPanel180.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel180Layout = new javax.swing.GroupLayout(jPanel180);
        jPanel180.setLayout(jPanel180Layout);
        jPanel180Layout.setHorizontalGroup(
            jPanel180Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel180Layout.setVerticalGroup(
            jPanel180Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 13, 13));

        jPanel181.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel181Layout = new javax.swing.GroupLayout(jPanel181);
        jPanel181.setLayout(jPanel181Layout);
        jPanel181Layout.setHorizontalGroup(
            jPanel181Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel181Layout.setVerticalGroup(
            jPanel181Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 13, 13));

        jLabel383.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel383.setText("Terlambat");
        panelchartabssmtangkatan.add(jLabel383, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lb_sakitabsensmtangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmtangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_sakitabsensmtangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 328, 70, 20));

        jLabel384.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel384.setText("Sakit");
        panelchartabssmtangkatan.add(jLabel384, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, -1, -1));

        jLabel385.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel385.setText("Izin");
        panelchartabssmtangkatan.add(jLabel385, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel386.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel386.setText("Alpha");
        panelchartabssmtangkatan.add(jLabel386, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel387.setText(":");
        panelchartabssmtangkatan.add(jLabel387, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, -1, -1));

        jLabel388.setText(":");
        panelchartabssmtangkatan.add(jLabel388, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, -1, -1));

        jLabel389.setText(":");
        panelchartabssmtangkatan.add(jLabel389, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        jLabel390.setText(":");
        panelchartabssmtangkatan.add(jLabel390, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        jLabel391.setText(":");
        panelchartabssmtangkatan.add(jLabel391, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, -1));

        jLabel392.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel392.setText("Hadir");
        panelchartabssmtangkatan.add(jLabel392, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, -1, -1));

        lb_alphaabsensmtangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmtangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_alphaabsensmtangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 70, 18));

        lb_hadirabsensmtangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmtangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_hadirabsensmtangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 70, 18));

        lb_terlambatabsensmtangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmtangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_terlambatabsensmtangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 70, 18));

        lb_izinabsensmtangkatan12.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmtangkatan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_izinabsensmtangkatan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 70, 18));

        jPanel182.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel182Layout = new javax.swing.GroupLayout(jPanel182);
        jPanel182.setLayout(jPanel182Layout);
        jPanel182Layout.setHorizontalGroup(
            jPanel182Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel182Layout.setVerticalGroup(
            jPanel182Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 13, 13));

        jPanel183.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel183Layout = new javax.swing.GroupLayout(jPanel183);
        jPanel183.setLayout(jPanel183Layout);
        jPanel183Layout.setHorizontalGroup(
            jPanel183Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel183Layout.setVerticalGroup(
            jPanel183Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 13, 13));

        jPanel184.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel184Layout = new javax.swing.GroupLayout(jPanel184);
        jPanel184.setLayout(jPanel184Layout);
        jPanel184Layout.setHorizontalGroup(
            jPanel184Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel184Layout.setVerticalGroup(
            jPanel184Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 13, 13));

        jPanel185.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel185Layout = new javax.swing.GroupLayout(jPanel185);
        jPanel185.setLayout(jPanel185Layout);
        jPanel185Layout.setHorizontalGroup(
            jPanel185Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel185Layout.setVerticalGroup(
            jPanel185Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 13, 13));

        jPanel186.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel186Layout = new javax.swing.GroupLayout(jPanel186);
        jPanel186.setLayout(jPanel186Layout);
        jPanel186Layout.setHorizontalGroup(
            jPanel186Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel186Layout.setVerticalGroup(
            jPanel186Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelchartabssmtangkatan.add(jPanel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 13, 13));

        jLabel393.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel393.setText("Terlambat");
        panelchartabssmtangkatan.add(jLabel393, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        jLabel394.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel394.setText("Sakit");
        panelchartabssmtangkatan.add(jLabel394, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jLabel395.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel395.setText("Izin");
        panelchartabssmtangkatan.add(jLabel395, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel396.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel396.setText("Alpha");
        panelchartabssmtangkatan.add(jLabel396, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        jLabel397.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jLabel397.setText("KEHADIARAN SISWA KELAS 11");
        panelchartabssmtangkatan.add(jLabel397, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 137, -1, 30));

        jLabel398.setText(":");
        panelchartabssmtangkatan.add(jLabel398, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel399.setText(":");
        panelchartabssmtangkatan.add(jLabel399, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jLabel400.setText(":");
        panelchartabssmtangkatan.add(jLabel400, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel401.setText(":");
        panelchartabssmtangkatan.add(jLabel401, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel402.setText(":");
        panelchartabssmtangkatan.add(jLabel402, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        jLabel403.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        jLabel403.setText("Hadir");
        panelchartabssmtangkatan.add(jLabel403, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        lb_hadirabsensmtangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_hadirabsensmtangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_hadirabsensmtangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 70, 18));

        lb_sakitabsensmtangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_sakitabsensmtangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_sakitabsensmtangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, 18));

        lb_alphaabsensmtangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_alphaabsensmtangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_alphaabsensmtangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 70, 18));

        lb_izinabsensmtangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_izinabsensmtangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_izinabsensmtangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 70, 18));

        lb_terlambatabsensmtangkatan11.setFont(new java.awt.Font("Roboto Slab", 0, 11)); // NOI18N
        lb_terlambatabsensmtangkatan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelchartabssmtangkatan.add(lb_terlambatabsensmtangkatan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 70, 18));

        panelgrafik.add(panelchartabssmtangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5000, 930, 440));

        Scrollpane.setViewportView(panelgrafik);

        paneldashboard.add(Scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 950, 480));

        panel_waktu.setBackground(new java.awt.Color(255, 255, 255));
        panel_waktu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(37, 112, 183), 2, true));
        panel_waktu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(37, 112, 183));
        lbl_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tanggal.setText("Tanggal");
        lbl_tanggal.setToolTipText("");
        panel_waktu.add(lbl_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 390, -1));

        txt_tanggal.setBackground(new java.awt.Color(102, 255, 102));
        txt_tanggal.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        txt_tanggal.setForeground(new java.awt.Color(37, 112, 183));
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 270, 40));

        lbl_waktu.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        lbl_waktu.setForeground(new java.awt.Color(37, 112, 183));
        lbl_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_waktu.setText("Waktu ");
        panel_waktu.add(lbl_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 380, -1));

        txt_jam.setBackground(new java.awt.Color(255, 51, 51));
        txt_jam.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 24)); // NOI18N
        txt_jam.setForeground(new java.awt.Color(37, 112, 183));
        txt_jam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_waktu.add(txt_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 270, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_jam.png"))); // NOI18N
        panel_waktu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_calender.png"))); // NOI18N
        panel_waktu.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        jLabel13.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(37, 112, 183));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("<html> Jl. Raya Batujajar, Batujajar Barat, Kecamatan Batujajar, Kabupaten Bandung Barat, <br> Jawa Barat <br> 40561  </html>");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_waktu.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 320, 130));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_location.png"))); // NOI18N
        panel_waktu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 40, 40));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icon_telepon.png"))); // NOI18N
        panel_waktu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 40, 40));

        jLabel10.setFont(new java.awt.Font("Roboto Slab SemiBold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(37, 112, 183));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("(022) 6867255");
        panel_waktu.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 140, 40));

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));
        jCalendar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jCalendar1.setDecorationBackgroundColor(new java.awt.Color(102, 204, 255));
        jCalendar1.setFont(new java.awt.Font("Noto Serif", 0, 10)); // NOI18N
        jCalendar1.setSundayForeground(new java.awt.Color(255, 0, 0));
        jCalendar1.setWeekdayForeground(new java.awt.Color(0, 51, 102));
        panel_waktu.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 213, 380, 190));

        paneldashboard.add(panel_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 390, 640));

        btn_kelolaabsensi.setBackground(new java.awt.Color(245, 245, 245));
        btn_kelolaabsensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kelolaabsensiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kelolaabsensiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_kelolaabsensiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_kelolaabsensiMouseReleased(evt);
            }
        });
        btn_kelolaabsensi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_kelolaabsensi.setFont(new java.awt.Font("Gadugi", 0, 26)); // NOI18N
        lb_kelolaabsensi.setForeground(new java.awt.Color(37, 112, 183));
        lb_kelolaabsensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_kelolaabsensi.setText("KELOLA ABSENSI");
        btn_kelolaabsensi.add(lb_kelolaabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 390, 50));

        icon_kelolaabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/dashabsen.png"))); // NOI18N
        btn_kelolaabsensi.add(icon_kelolaabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        paneldashboard.add(btn_kelolaabsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 450, 50));

        btn_keluar.setBackground(new java.awt.Color(245, 245, 245));
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
        btn_keluar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_keluar.setBackground(new java.awt.Color(255, 255, 255));
        lb_keluar.setFont(new java.awt.Font("Gadugi", 0, 26)); // NOI18N
        lb_keluar.setForeground(new java.awt.Color(37, 112, 183));
        lb_keluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_keluar.setText("KELUAR APLIKASI");
        btn_keluar.add(lb_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 390, 50));

        icon_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/dashkeluar.png"))); // NOI18N
        btn_keluar.add(icon_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        paneldashboard.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 690, 450, 50));

        btn_editprofile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_editprofile.setForeground(new java.awt.Color(255, 255, 255));
        btn_editprofile.setText("EDIT PROFILE");
        btn_editprofile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editprofileMouseClicked(evt);
            }
        });
        paneldashboard.add(btn_editprofile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 65, 90, -1));

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
           sql  = "SELECT COUNT(*) from siswa WHERE status='Aktif'";
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
           sql  = "SELECT COUNT(*) from guru WHERE status='Aktif'";
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
           sql  = "Select * from semester order by idsemester asc";
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa per Hari", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
            totalhadir = Integer.toString(mghadir);
            totalsakit = Integer.toString(mgsakit);
            totalizin  = Integer.toString(mgizin);
            totalalpha = Integer.toString(mgalpha);
            totalterlambat = Integer.toString(mgterlambat);
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa per Minggu", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
           //sql  = "SELECT  status  FROM absen WHERE tanggal between DATE_FORMAT(CURDATE(),'%Y-%M-01') AND CURDATE()";
           sql = "SELECT status FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE())";
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
            totalhadir = Integer.toString(blnhadir);
            totalsakit = Integer.toString(blnsakit);
            totalizin  = Integer.toString(blnizin);
            totalalpha = Integer.toString(blnalpha);
            totalterlambat = Integer.toString(blnterlambat);
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa per Bulan", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa per Semester", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
                   hrhadirotkp=hrhadirotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakitotkp=hrsakitotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizinotkp=hrizinotkp+1;
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Jurusan per Hari", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(mghadirotkp);
           totalsakit = Integer.toString(mgsakitotkp);
           totalizin  = Integer.toString(mgizinotkp);
           totalalpha = Integer.toString(mgalphaotkp);
           totalterlambat = Integer.toString(mgterlambatotkp);
           lb_hadirabsenmingguanotkp.setText(totalhadir);
           lb_sakitabsenmingguanotkp.setText(totalsakit);
           lb_izinabsenmingguanotkp.setText(totalizin);
           lb_alphaabsenmingguanotkp.setText(totalalpha);
           lb_terlambatabsenmingguanotkp.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswamingguanbdp(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(mghadirbdp);
           totalsakit = Integer.toString(mgsakitbdp);
           totalizin  = Integer.toString(mgizinbdp);
           totalalpha = Integer.toString(mgalphabdp);
           totalterlambat = Integer.toString(mgterlambatbdp);
           lb_hadirabsenmingguanbdp.setText(totalhadir);
           lb_sakitabsenmingguanbdp.setText(totalsakit);
           lb_izinabsenmingguanbdp.setText(totalizin);
           lb_alphaabsenmingguanbdp.setText(totalalpha);
           lb_terlambatabsenmingguanbdp.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswamingguantitl(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(mghadirtitl);
           totalsakit = Integer.toString(mgsakittitl);
           totalizin  = Integer.toString(mgizintitl);
           totalalpha = Integer.toString(mgalphatitl);
           totalterlambat = Integer.toString(mgterlambattitl);
           lb_hadirabsenmingguantitl.setText(totalhadir);
           lb_sakitabsenmingguantitl.setText(totalsakit);
           lb_izinabsenmingguantitl.setText(totalizin);
           lb_alphaabsenmingguantitl.setText(totalalpha);
           lb_terlambatabsenmingguantitl.setText(totalterlambat);      
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Jurusan per Minggu", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, false);
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
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%OTKP%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   blnhadirotkp=blnhadirotkp+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakitotkp=blnsakitotkp+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizinotkp=blnizinotkp+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalphaotkp=blnalphaotkp+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambatotkp=blnterlambatotkp+1;
               }
            }
           totalhadir = Integer.toString(blnhadirotkp);
           totalsakit = Integer.toString(blnsakitotkp);
           totalizin  = Integer.toString(blnizinotkp);
           totalalpha = Integer.toString(blnalphaotkp);
           totalterlambat = Integer.toString(blnterlambatotkp);
           lb_hadirabsenbulanotkp.setText(totalhadir);
           lb_sakitabsenbulanotkp.setText(totalsakit);
           lb_izinabsenbulanotkp.setText(totalizin);
           lb_alphaabsenbulanotkp.setText(totalalpha);
           lb_terlambatabsenbulanotkp.setText(totalterlambat);       
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswabulananbdp(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%BDP%'";
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
           totalhadir = Integer.toString(blnhadirbdp);
           totalsakit = Integer.toString(blnsakitbdp);
           totalizin  = Integer.toString(blnizinbdp);
           totalalpha = Integer.toString(blnalphabdp);
           totalterlambat = Integer.toString(blnterlambatbdp);
           lb_hadirabsenbulanbdp.setText(totalhadir);
           lb_sakitabsenbulanbdp.setText(totalsakit);
           lb_izinabsenbulanbdp.setText(totalizin);
           lb_alphaabsenbulanbdp.setText(totalalpha);
           lb_terlambatabsenbulanbdp.setText(totalterlambat);          
        } catch (SQLException e) {
            System.out.println(e);
        }
    }    
    
    public void dataabsensisiswabulanantitl(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%TITL%'";
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
           totalhadir = Integer.toString(blnhadirtitl);
           totalsakit = Integer.toString(blnsakittitl);
           totalizin  = Integer.toString(blnizintitl);
           totalalpha = Integer.toString(blnalphatitl);
           totalterlambat = Integer.toString(blnterlambattitl);
           lb_hadirabsenbulantitl.setText(totalhadir);
           lb_sakitabsenbulantitl.setText(totalsakit);
           lb_izinabsenbulantitl.setText(totalizin);
           lb_alphaabsenbulantitl.setText(totalalpha);
           lb_terlambatabsenbulantitl.setText(totalterlambat);    
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Jurusan per Bulan", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadirtitl);
           totalsakit = Integer.toString(sakittitl);
           totalizin  = Integer.toString(izintitl);
           totalalpha = Integer.toString(alphatitl);
           totalterlambat = Integer.toString(terlambattitl);
           lb_hadirabsensmttitl.setText(totalhadir);
           lb_sakitabsensmttitl.setText(totalsakit);
           lb_izinabsensmttitl.setText(totalizin);
           lb_alphaabsensmttitl.setText(totalalpha);
           lb_terlambatabsensmttitl.setText(totalterlambat); 
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensijurusanbdp(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadirbdp);
           totalsakit = Integer.toString(sakitbdp);
           totalizin  = Integer.toString(izinbdp);
           totalalpha = Integer.toString(alphabdp);
           totalterlambat = Integer.toString(terlambatbdp);
           lb_hadirabsensmtbdp.setText(totalhadir);
           lb_sakitabsensmtbdp.setText(totalsakit);
           lb_izinabsensmtbdp.setText(totalizin);
           lb_alphaabsensmtbdp.setText(totalalpha);
           lb_terlambatabsensmtbdp.setText(totalterlambat);      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensijurusanotkp(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadirotkp);
           totalsakit = Integer.toString(sakitotkp);
           totalizin  = Integer.toString(izinotkp);
           totalalpha = Integer.toString(alphaotkp);
           totalterlambat = Integer.toString(terlambatotkp);
           lb_hadirabsensmtotkp.setText(totalhadir);
           lb_sakitabsensmtotkp.setText(totalsakit);
           lb_izinabsensmtotkp.setText(totalizin);
           lb_alphaabsensmtotkp.setText(totalalpha);
           lb_terlambatabsensmtotkp.setText(totalterlambat);   
           
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Jurusan per Semester", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartabsensissmtjurusan.removeAll();
        chartabsensissmtjurusan.add(barChartPanel);
        chartabsensissmtjurusan.validate();
    }
    
//ANGKATAN   
    //HARI
    public void dataabsensisiswaharianangkatan10(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%10%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadir10=hrhadir10+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakit10=hrsakit10+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizin10=hrizin10+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralpha10=hralpha10+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambat10=hrterlambat10+1;
               }
            }
           totalhadir = Integer.toString(hrhadir10);
           totalsakit = Integer.toString(hrsakit10);
           totalizin  = Integer.toString(hrizin10);
           totalalpha = Integer.toString(hralpha10);
           totalterlambat = Integer.toString(hrterlambat10);
           lb_hadirabsenhariangkatan10.setText(totalhadir);
           lb_sakitabsenhariangkatan10.setText(totalsakit);
           lb_izinabsenhariangkatan10.setText(totalizin);
           lb_alphaabsenhariangkatan10.setText(totalalpha);
           lb_terlambatabsenhariangkatan10.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dataabsensisiswaharianangkatan11(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%11%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadir11=hrhadir11+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakit11=hrsakit11+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizin11=hrizin11+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralpha11=hralpha11+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambat11=hrterlambat11+1;
               }
            }
           totalhadir = Integer.toString(hrhadir11);
           totalsakit = Integer.toString(hrsakit11);
           totalizin  = Integer.toString(hrizin11);
           totalalpha = Integer.toString(hralpha11);
           totalterlambat = Integer.toString(hrterlambat11);
           lb_hadirabsenhariangkatan11.setText(totalhadir);
           lb_sakitabsenhariangkatan11.setText(totalsakit);
           lb_izinabsenhariangkatan11.setText(totalizin);
           lb_alphaabsenhariangkatan11.setText(totalalpha);
           lb_terlambatabsenhariangkatan11.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensisiswaharianangkatan12(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "Select status from absen WHERE tanggal=CURDATE() and nk LIKE '%12%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   hrhadir12=hrhadir12+1;
               }
               else if(obj[0].equals("Sakit")){
                   hrsakit12=hrsakit12+1;
               }
               else if(obj[0].equals("Izin")){
                   hrizin12=hrizin12+1;
               }
               else if(obj[0].equals("Alpha")){
                   hralpha12=hralpha12+1;
               }
               else if(obj[0].equals("Terlambat")){
                   hrterlambat12=hrterlambat12+1;
               }
            }
           totalhadir = Integer.toString(hrhadir12);
           totalsakit = Integer.toString(hrsakit12);
           totalizin  = Integer.toString(hrizin12);
           totalalpha = Integer.toString(hralpha12);
           totalterlambat = Integer.toString(hrterlambat12);
           lb_hadirabsenhariangkatan12.setText(totalhadir);
           lb_sakitabsenhariangkatan12.setText(totalsakit);
           lb_izinabsenhariangkatan12.setText(totalizin);
           lb_alphaabsenhariangkatan12.setText(totalalpha);
           lb_terlambatabsenhariangkatan12.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

     public void chartsemuasiswaharianangkatan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(hrhadir10, "Hadir", "Angkatan 10");
        ctdata.setValue(hrsakit10, "Sakit", "Angkatan 10");
        ctdata.setValue(hrizin10, "Izin", "Angkatan 10");
        ctdata.setValue(hralpha10, "Alpha", "Angkatan 10");
        ctdata.setValue(hrterlambat10, "Terlambat", "Angkatan 10");
        ctdata.setValue(hrhadir11, "Hadir", "Angkatan 11");
        ctdata.setValue(hrsakit11, "Sakit", "Angkatan 11");
        ctdata.setValue(hrizin11, "Izin", "Angkatan 11");
        ctdata.setValue(hralpha11, "Alpha", "Angkatan 11");
        ctdata.setValue(hrterlambat11, "Terlambat", "Angkatan 11");
        ctdata.setValue(hrhadir12, "Hadir", "Angkatan 12");
        ctdata.setValue(hrsakit12, "Sakit", "Angkatan 12");
        ctdata.setValue(hrizin12, "Izin", "Angkatan 12");
        ctdata.setValue(hralpha12, "Alpha", "Angkatan 12");
        ctdata.setValue(hrterlambat12, "Terlambat", "Angkatan 12");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Angkatan per Hari", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartabsensisharianangkatan.removeAll();
        chartabsensisharianangkatan.add(barChartPanel);
        chartabsensisharianangkatan.validate();
    }
    
    //MINGGU
    public void dataabsensisiswamingguanangkatan10(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%10%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   mghadir10=mghadir10+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakit10=mgsakit10+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizin10=mgizin10+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalpha10=mgalpha10+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambat10=mgterlambat10+1;
               }
            }
           totalhadir = Integer.toString(mghadir10);
           totalsakit = Integer.toString(mgsakit10);
           totalizin  = Integer.toString(mgizin10);
           totalalpha = Integer.toString(mgalpha10);
           totalterlambat = Integer.toString(mgterlambat10);
           lb_hadirabsenmingguanangkatan10.setText(totalhadir);
           lb_sakitabsenmingguanangkatan10.setText(totalsakit);
           lb_izinabsenmingguanangkatan10.setText(totalizin);
           lb_alphaabsenmingguanangkatan10.setText(totalalpha);
           lb_terlambatabsenmingguanangkatan10.setText(totalterlambat);
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }    
    
    public void dataabsensisiswamingguanangkatan11(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%11%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   mghadir11=mghadir11+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakit11=mgsakit11+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizin11=mgizin11+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalpha11=mgalpha11+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambat11=mgterlambat11+1;
               }
            }
           totalhadir = Integer.toString(mghadir11);
           totalsakit = Integer.toString(mgsakit11);
           totalizin  = Integer.toString(mgizin11);
           totalalpha = Integer.toString(mgalpha11);
           totalterlambat = Integer.toString(mgterlambat11);
           lb_hadirabsenmingguanangkatan11.setText(totalhadir);
           lb_sakitabsenmingguaniangkatan11.setText(totalsakit);
           lb_izinabsenmingguaniangkatan11.setText(totalizin);
           lb_alphaabsenmingguaangkatan11.setText(totalalpha);
           lb_terlambatabsenmingguanangkatan11.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }   
    
    public void dataabsensisiswamingguanangkatan12(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT status FROM absen WHERE WEEKOFYEAR(tanggal)=WEEKOFYEAR(CURDATE()) and nk LIKE '%12%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   mghadir12=mghadir12+1;
               }
               else if(obj[0].equals("Sakit")){
                   mgsakit12=mgsakit12+1;
               }
               else if(obj[0].equals("Izin")){
                   mgizin12=mgizin12+1;
               }
               else if(obj[0].equals("Alpha")){
                   mgalpha12=mgalpha12+1;
               }
               else if(obj[0].equals("Terlambat")){
                   mgterlambat12=mgterlambat12+1;
               }
            }
           totalhadir = Integer.toString(mghadir12);
           totalsakit = Integer.toString(mgsakit12);
           totalizin  = Integer.toString(mgizin12);
           totalalpha = Integer.toString(mgalpha12);
           totalterlambat = Integer.toString(mgterlambat12);
           lb_hadirabsenmingguanangkatan12.setText(totalhadir);
           lb_sakitabsenmingguanangkatan12.setText(totalsakit);
           lb_izinabsenmingguanangkatan12.setText(totalizin);
           lb_alphaabsenmingguanangkatan12.setText(totalalpha);
           lb_terlambatabsenmingguanangkatan12.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }   
    
    public void chartsemuasiswamingguanangkatan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(mghadir10, "Hadir", "Angkatan 10");
        ctdata.setValue(mgsakit10, "Sakit", "Angkatan 10");
        ctdata.setValue(mgizin10, "Izin", "Angkatan 10");
        ctdata.setValue(mgalpha10, "Alpha", "Angkatan 10");
        ctdata.setValue(mgterlambat10, "Terlambat", "Angkatan 10");
        ctdata.setValue(mghadir11, "Hadir", "Angkatan 11");
        ctdata.setValue(mgsakit11, "Sakit", "Angkatan 11");
        ctdata.setValue(mgizin11, "Izin", "Angkatan 11");
        ctdata.setValue(mgalpha11, "Alpha", "Angkatan 11");
        ctdata.setValue(mgterlambat11, "Terlambat", "Angkatan 11");
        ctdata.setValue(mghadir12, "Hadir", "Angkatan 12");
        ctdata.setValue(mgsakit12, "Sakit", "Angkatan 12");
        ctdata.setValue(mgizin12, "Izin", "Angkatan 12");
        ctdata.setValue(mgalpha12, "Alpha", "Angkatan 12");
        ctdata.setValue(mgterlambat12, "Terlambat", "Angkatan 12");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Angkatan per Minggu", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartmingguanangkatan.removeAll();
        chartmingguanangkatan.add(barChartPanel);
        chartmingguanangkatan.validate();
    }
    
    
    //BULANAN
    public void dataabsensisiswabulananangkatan10(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%10%'";;
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   blnhadir10=blnhadir10+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakit10=blnsakit10+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizin10=blnizin10+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalpha10=blnalpha10+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambat10=blnterlambat10+1;
               }
            }
           totalhadir = Integer.toString(blnhadir10);
           totalsakit = Integer.toString(blnsakit10);
           totalizin  = Integer.toString(blnizin10);
           totalalpha = Integer.toString(blnalpha10);
           totalterlambat = Integer.toString(blnterlambat10);
           lb_hadirabsenbulanangkatan10.setText(totalhadir);
           lb_sakitabsenbulanangkatan10.setText(totalsakit);
           lb_izinabsenbulanangkatan10.setText(totalizin);
           lb_alphaabsenbulanangkatan10.setText(totalalpha);
           lb_terlambatabsenbulanangkatan10.setText(totalterlambat);       
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
    public void dataabsensisiswabulananangkatan11(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%11%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   blnhadir11=blnhadir11+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakit11=blnsakit11+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizin11=blnizin11+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalpha11=blnalpha11+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambat11=blnterlambat11+1;
               }
            }
           totalhadir = Integer.toString(blnhadir11);
           totalsakit = Integer.toString(blnsakit11);
           totalizin  = Integer.toString(blnizin11);
           totalalpha = Integer.toString(blnalpha11);
           totalterlambat = Integer.toString(blnterlambat11);
           lb_hadirabsenbulanangkatan11.setText(totalhadir);
           lb_sakitabsenbulanangkatan11.setText(totalsakit);
           lb_izinabsenbulanangkatan11.setText(totalizin);
           lb_alphaabsenbulanangkatan11.setText(totalalpha);
           lb_terlambatabsenbulanangkatan11.setText(totalterlambat);       
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
          
    public void dataabsensisiswabulananangkatan12(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
        try{
           stat = con.createStatement( );
           sql  = "SELECT  status  FROM absen WHERE MONTH(tanggal)=MONTH(CURDATE()) AND nk LIKE '%12%'";
           rs   = stat.executeQuery(sql);
           while(rs.next ()){
               Object[] obj=new Object[1];
               obj[0]=rs.getString("status");
               if(obj[0].equals("Hadir")){
                   blnhadir12=blnhadir12+1;
               }
               else if(obj[0].equals("Sakit")){
                   blnsakit12=blnsakit12+1;
               }
               else if(obj[0].equals("Izin")){
                   blnizin12=blnizin12+1;
               }
               else if(obj[0].equals("Alpha")){
                   blnalpha12=blnalpha12+1;
               }
               else if(obj[0].equals("Terlambat")){
                   blnterlambat12=blnterlambat12+1;
               }
            }
           totalhadir = Integer.toString(blnhadir12);
           totalsakit = Integer.toString(blnsakit12);
           totalizin  = Integer.toString(blnizin12);
           totalalpha = Integer.toString(blnalpha12);
           totalterlambat = Integer.toString(blnterlambat11);
           lb_hadirabsenbulanangkatan12.setText(totalhadir);
           lb_sakitabsenbulaniangkatan12.setText(totalsakit);
           lb_izinabsenbulaniangkatan12.setText(totalizin);
           lb_alphaabsenbulaniangkatan12.setText(totalalpha);
           lb_terlambatabsenbulaniangkatan12.setText(totalterlambat);       
        } catch (SQLException e) {
            System.out.println(e);
        }
    }   
    
    public void chartsemuasiswabulananangkatan(){
        DefaultCategoryDataset ctdata=new DefaultCategoryDataset();
        ctdata.setValue(blnhadir10, "Hadir", "Angkatan 10");
        ctdata.setValue(blnsakit10, "Sakit", "Angkatan 10");
        ctdata.setValue(blnizin10, "Izin", "Angkatan 10");
        ctdata.setValue(blnizin10, "Alpha", "Angkatan 10");
        ctdata.setValue(blnterlambat10, "Terlambat", "Angkatan 10");
        ctdata.setValue(blnhadir11, "Hadir", "Angkatan 11");
        ctdata.setValue(blnsakit11, "Sakit", "Angkatan 11");
        ctdata.setValue(blnizin11, "Izin", "Angkatan 11");
        ctdata.setValue(blnalpha11, "Alpha", "Angkatan 11");
        ctdata.setValue(blnterlambat11, "Terlambat", "Angkatan 11");
        ctdata.setValue(blnhadir12, "Hadir", "Angkatan 12");
        ctdata.setValue(blnsakit12, "Sakit", "Angkatan 12");
        ctdata.setValue(blnizin12, "Izin", "Angkatan 12");
        ctdata.setValue(blnalpha12, "Alpha", "Angkatan 12");
        ctdata.setValue(blnterlambat12, "Terlambat", "Angkatan 12");
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Angkatan per Bulan", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartabsensisbulananangkatan.removeAll();
        chartabsensisbulananangkatan.add(barChartPanel);
        chartabsensisbulananangkatan.validate();
    }
    
     
    //SEMESTER
    public void dataabsensiangkatan10(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadir10);
           totalsakit = Integer.toString(sakit10);
           totalizin  = Integer.toString(izin10);
           totalalpha = Integer.toString(alpha10);
           totalterlambat = Integer.toString(terlambat10);
           lb_hadirabsensmtangkatan10.setText(totalhadir);
           lb_sakitabsensmtangkatan10.setText(totalsakit);
           lb_izinabsensmtangkatan10.setText(totalizin);
           lb_alphaabsensmtangkatan10.setText(totalalpha);
           lb_terlambatabsensmtangkatan10.setText(totalterlambat); 
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void dataabsensiangkatan11(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadir11);
           totalsakit = Integer.toString(sakit11);
           totalizin  = Integer.toString(izin11);
           totalalpha = Integer.toString(alpha11);
           totalterlambat = Integer.toString(terlambat11);
           lb_hadirabsensmtangkatan11.setText(totalhadir);
           lb_sakitabsensmtangkatan11.setText(totalsakit);
           lb_izinabsensmtangkatan11.setText(totalizin);
           lb_alphaabsensmtangkatan11.setText(totalalpha);
           lb_terlambatabsensmtangkatan11.setText(totalterlambat);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
         
    public void dataabsensiangkatan12(){
        String totalhadir,totalsakit,totalizin,totalalpha,totalterlambat;
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
           totalhadir = Integer.toString(hadir12);
           totalsakit = Integer.toString(sakit12);
           totalizin  = Integer.toString(izin12);
           totalalpha = Integer.toString(alpha12);
           totalterlambat = Integer.toString(terlambat12);
           lb_hadirabsensmtangkatan12.setText(totalhadir);
           lb_sakitabsensmtangkatan12.setText(totalsakit);
           lb_izinabsensmtangkatan12.setText(totalizin);
           lb_alphaabsensmtangkatan12.setText(totalalpha);
           lb_terlambatabsensmtangkatan12.setText(totalterlambat);
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
        JFreeChart chart=ChartFactory.createBarChart("Presensi Semua Siswa Setiap Angkatan per Semester", "Status", "Jumlah",ctdata, PlotOrientation.VERTICAL, true, true, true);
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
        chartabsensissmtnangkatan.removeAll();
        chartabsensissmtnangkatan.add(barChartPanel);
        chartabsensissmtnangkatan.validate();
    }
   
    
    private void btn_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseEntered
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(37,112,183));
        lb_keluar.setForeground(Color.white);
    }//GEN-LAST:event_btn_keluarMouseEntered

    private void btn_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseExited
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(240,240,240));
        lb_keluar.setForeground(new Color(37,112,183));
    }//GEN-LAST:event_btn_keluarMouseExited

    private void btn_keluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMousePressed
        login lgn=new login();
        lgn.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_keluarMousePressed

    private void btn_keluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseReleased
        
    }//GEN-LAST:event_btn_keluarMouseReleased

    private void btn_editprofileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editprofileMouseClicked
        profiladmin pa=new profiladmin();
        pa.setVisible(true);
    }//GEN-LAST:event_btn_editprofileMouseClicked

    private void btn_kelolaabsensiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsensiMouseEntered
        btn_kelolaabsensi.setBackground(new Color(37,112,183));
        lb_kelolaabsensi.setForeground(Color.white);
    }//GEN-LAST:event_btn_kelolaabsensiMouseEntered

    private void btn_kelolaabsensiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsensiMouseExited
        btn_kelolaabsensi.setBackground(new Color(240,240,240));
        lb_kelolaabsensi.setForeground(new Color(37,112,183));
    }//GEN-LAST:event_btn_kelolaabsensiMouseExited

    private void btn_kelolaabsensiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsensiMousePressed
        keloladata_bk kbk=new keloladata_bk();
        kbk.setVisible(true);
        this.setVisible(false);
        kbk.notifikasialphasemester();
    }//GEN-LAST:event_btn_kelolaabsensiMousePressed

    private void btn_kelolaabsensiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kelolaabsensiMouseReleased
        
    }//GEN-LAST:event_btn_kelolaabsensiMouseReleased

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
            java.util.logging.Logger.getLogger(dashboard_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard_bk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard_bk().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scrollpane;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_editprofile;
    private javax.swing.JPanel btn_kelolaabsensi;
    private javax.swing.JPanel btn_keluar;
    private javax.swing.JPanel chartabsensibulanan;
    private javax.swing.JPanel chartabsensibulanjurusan;
    private javax.swing.JPanel chartabsensiharian;
    private javax.swing.JPanel chartabsensiharijurusan;
    private javax.swing.JPanel chartabsensimingguan;
    private javax.swing.JPanel chartabsensiminggujurusan;
    private javax.swing.JPanel chartabsensisbulananangkatan;
    private javax.swing.JPanel chartabsensisemester;
    private javax.swing.JPanel chartabsensisharianangkatan;
    private javax.swing.JPanel chartabsensissmtjurusan;
    private javax.swing.JPanel chartabsensissmtnangkatan;
    private javax.swing.JPanel chartmingguanangkatan;
    private javax.swing.JLabel icon_kelolaabsensi;
    private javax.swing.JLabel icon_keluar;
    private com.toedter.calendar.JCalendar jCalendar1;
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
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
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
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel358;
    private javax.swing.JLabel jLabel359;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel360;
    private javax.swing.JLabel jLabel361;
    private javax.swing.JLabel jLabel362;
    private javax.swing.JLabel jLabel363;
    private javax.swing.JLabel jLabel364;
    private javax.swing.JLabel jLabel365;
    private javax.swing.JLabel jLabel366;
    private javax.swing.JLabel jLabel367;
    private javax.swing.JLabel jLabel368;
    private javax.swing.JLabel jLabel369;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel370;
    private javax.swing.JLabel jLabel371;
    private javax.swing.JLabel jLabel372;
    private javax.swing.JLabel jLabel373;
    private javax.swing.JLabel jLabel374;
    private javax.swing.JLabel jLabel375;
    private javax.swing.JLabel jLabel376;
    private javax.swing.JLabel jLabel377;
    private javax.swing.JLabel jLabel378;
    private javax.swing.JLabel jLabel379;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel380;
    private javax.swing.JLabel jLabel381;
    private javax.swing.JLabel jLabel382;
    private javax.swing.JLabel jLabel383;
    private javax.swing.JLabel jLabel384;
    private javax.swing.JLabel jLabel385;
    private javax.swing.JLabel jLabel386;
    private javax.swing.JLabel jLabel387;
    private javax.swing.JLabel jLabel388;
    private javax.swing.JLabel jLabel389;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel390;
    private javax.swing.JLabel jLabel391;
    private javax.swing.JLabel jLabel392;
    private javax.swing.JLabel jLabel393;
    private javax.swing.JLabel jLabel394;
    private javax.swing.JLabel jLabel395;
    private javax.swing.JLabel jLabel396;
    private javax.swing.JLabel jLabel397;
    private javax.swing.JLabel jLabel398;
    private javax.swing.JLabel jLabel399;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel400;
    private javax.swing.JLabel jLabel401;
    private javax.swing.JLabel jLabel402;
    private javax.swing.JLabel jLabel403;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
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
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
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
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel151;
    private javax.swing.JPanel jPanel152;
    private javax.swing.JPanel jPanel153;
    private javax.swing.JPanel jPanel154;
    private javax.swing.JPanel jPanel155;
    private javax.swing.JPanel jPanel156;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel161;
    private javax.swing.JPanel jPanel162;
    private javax.swing.JPanel jPanel163;
    private javax.swing.JPanel jPanel164;
    private javax.swing.JPanel jPanel165;
    private javax.swing.JPanel jPanel166;
    private javax.swing.JPanel jPanel167;
    private javax.swing.JPanel jPanel168;
    private javax.swing.JPanel jPanel169;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel170;
    private javax.swing.JPanel jPanel171;
    private javax.swing.JPanel jPanel172;
    private javax.swing.JPanel jPanel173;
    private javax.swing.JPanel jPanel174;
    private javax.swing.JPanel jPanel175;
    private javax.swing.JPanel jPanel176;
    private javax.swing.JPanel jPanel177;
    private javax.swing.JPanel jPanel178;
    private javax.swing.JPanel jPanel179;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel180;
    private javax.swing.JPanel jPanel181;
    private javax.swing.JPanel jPanel182;
    private javax.swing.JPanel jPanel183;
    private javax.swing.JPanel jPanel184;
    private javax.swing.JPanel jPanel185;
    private javax.swing.JPanel jPanel186;
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
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb_aab;
    private javax.swing.JLabel lb_aah;
    private javax.swing.JLabel lb_aam;
    private javax.swing.JLabel lb_aas;
    private javax.swing.JLabel lb_ajb1;
    private javax.swing.JLabel lb_ajo1;
    private javax.swing.JLabel lb_ajt1;
    private javax.swing.JLabel lb_alphaabsenbulanangkatan10;
    private javax.swing.JLabel lb_alphaabsenbulanangkatan11;
    private javax.swing.JLabel lb_alphaabsenbulanbdp;
    private javax.swing.JLabel lb_alphaabsenbulaniangkatan12;
    private javax.swing.JLabel lb_alphaabsenbulanotkp;
    private javax.swing.JLabel lb_alphaabsenbulantitl;
    private javax.swing.JLabel lb_alphaabsenhariangkatan10;
    private javax.swing.JLabel lb_alphaabsenhariangkatan11;
    private javax.swing.JLabel lb_alphaabsenhariangkatan12;
    private javax.swing.JLabel lb_alphaabsenmingguaangkatan11;
    private javax.swing.JLabel lb_alphaabsenmingguanangkatan10;
    private javax.swing.JLabel lb_alphaabsenmingguanangkatan12;
    private javax.swing.JLabel lb_alphaabsenmingguanbdp;
    private javax.swing.JLabel lb_alphaabsenmingguanotkp;
    private javax.swing.JLabel lb_alphaabsenmingguantitl;
    private javax.swing.JLabel lb_alphaabsensmtangkatan10;
    private javax.swing.JLabel lb_alphaabsensmtangkatan11;
    private javax.swing.JLabel lb_alphaabsensmtangkatan12;
    private javax.swing.JLabel lb_alphaabsensmtbdp;
    private javax.swing.JLabel lb_alphaabsensmtotkp;
    private javax.swing.JLabel lb_alphaabsensmttitl;
    private javax.swing.JLabel lb_hab;
    private javax.swing.JLabel lb_hadirabsenbulanangkatan10;
    private javax.swing.JLabel lb_hadirabsenbulanangkatan11;
    private javax.swing.JLabel lb_hadirabsenbulanangkatan12;
    private javax.swing.JLabel lb_hadirabsenbulanbdp;
    private javax.swing.JLabel lb_hadirabsenbulanotkp;
    private javax.swing.JLabel lb_hadirabsenbulantitl;
    private javax.swing.JLabel lb_hadirabsenhariangkatan10;
    private javax.swing.JLabel lb_hadirabsenhariangkatan11;
    private javax.swing.JLabel lb_hadirabsenhariangkatan12;
    private javax.swing.JLabel lb_hadirabsenmingguanangkatan10;
    private javax.swing.JLabel lb_hadirabsenmingguanangkatan11;
    private javax.swing.JLabel lb_hadirabsenmingguanangkatan12;
    private javax.swing.JLabel lb_hadirabsenmingguanbdp;
    private javax.swing.JLabel lb_hadirabsenmingguanotkp;
    private javax.swing.JLabel lb_hadirabsenmingguantitl;
    private javax.swing.JLabel lb_hadirabsensmtangkatan10;
    private javax.swing.JLabel lb_hadirabsensmtangkatan11;
    private javax.swing.JLabel lb_hadirabsensmtangkatan12;
    private javax.swing.JLabel lb_hadirabsensmtbdp;
    private javax.swing.JLabel lb_hadirabsensmtotkp;
    private javax.swing.JLabel lb_hadirabsensmttitl;
    private javax.swing.JLabel lb_hah;
    private javax.swing.JLabel lb_ham;
    private javax.swing.JLabel lb_has;
    private javax.swing.JLabel lb_hjb1;
    private javax.swing.JLabel lb_hjo1;
    private javax.swing.JLabel lb_hjt1;
    private javax.swing.JLabel lb_iab;
    private javax.swing.JLabel lb_iah;
    private javax.swing.JLabel lb_iam;
    private javax.swing.JLabel lb_ias;
    private javax.swing.JLabel lb_ijb1;
    private javax.swing.JLabel lb_ijo1;
    private javax.swing.JLabel lb_ijt1;
    private javax.swing.JLabel lb_izinabsenbulanangkatan10;
    private javax.swing.JLabel lb_izinabsenbulanangkatan11;
    private javax.swing.JLabel lb_izinabsenbulanbdp;
    private javax.swing.JLabel lb_izinabsenbulaniangkatan12;
    private javax.swing.JLabel lb_izinabsenbulanotkp;
    private javax.swing.JLabel lb_izinabsenbulantitl;
    private javax.swing.JLabel lb_izinabsenhariangkatan10;
    private javax.swing.JLabel lb_izinabsenhariangkatan11;
    private javax.swing.JLabel lb_izinabsenhariangkatan12;
    private javax.swing.JLabel lb_izinabsenmingguanangkatan10;
    private javax.swing.JLabel lb_izinabsenmingguanangkatan12;
    private javax.swing.JLabel lb_izinabsenmingguanbdp;
    private javax.swing.JLabel lb_izinabsenmingguaniangkatan11;
    private javax.swing.JLabel lb_izinabsenmingguanotkp;
    private javax.swing.JLabel lb_izinabsenmingguantitl;
    private javax.swing.JLabel lb_izinabsensmtangkatan10;
    private javax.swing.JLabel lb_izinabsensmtangkatan11;
    private javax.swing.JLabel lb_izinabsensmtangkatan12;
    private javax.swing.JLabel lb_izinabsensmtbdp;
    private javax.swing.JLabel lb_izinabsensmtotkp;
    private javax.swing.JLabel lb_izinabsensmttitl;
    private javax.swing.JLabel lb_jumlahguru;
    private javax.swing.JLabel lb_jumlahkelas;
    private javax.swing.JLabel lb_jumlahsiswa;
    private javax.swing.JLabel lb_kelolaabsensi;
    private javax.swing.JLabel lb_keluar;
    private javax.swing.JLabel lb_sab;
    private javax.swing.JLabel lb_sah;
    private javax.swing.JLabel lb_sakitabsenbulanangkatan10;
    private javax.swing.JLabel lb_sakitabsenbulanangkatan11;
    private javax.swing.JLabel lb_sakitabsenbulanbdp;
    private javax.swing.JLabel lb_sakitabsenbulaniangkatan12;
    private javax.swing.JLabel lb_sakitabsenbulanotkp;
    private javax.swing.JLabel lb_sakitabsenbulantitl;
    private javax.swing.JLabel lb_sakitabsenhariangkatan10;
    private javax.swing.JLabel lb_sakitabsenhariangkatan11;
    private javax.swing.JLabel lb_sakitabsenhariangkatan12;
    private javax.swing.JLabel lb_sakitabsenmingguanangkatan10;
    private javax.swing.JLabel lb_sakitabsenmingguanangkatan12;
    private javax.swing.JLabel lb_sakitabsenmingguanbdp;
    private javax.swing.JLabel lb_sakitabsenmingguaniangkatan11;
    private javax.swing.JLabel lb_sakitabsenmingguanotkp;
    private javax.swing.JLabel lb_sakitabsenmingguantitl;
    private javax.swing.JLabel lb_sakitabsensmtangkatan10;
    private javax.swing.JLabel lb_sakitabsensmtangkatan11;
    private javax.swing.JLabel lb_sakitabsensmtangkatan12;
    private javax.swing.JLabel lb_sakitabsensmtbdp;
    private javax.swing.JLabel lb_sakitabsensmtotkp;
    private javax.swing.JLabel lb_sakitabsensmttitl;
    private javax.swing.JLabel lb_sam;
    private javax.swing.JLabel lb_sas;
    private javax.swing.JLabel lb_sjb1;
    private javax.swing.JLabel lb_sjo1;
    private javax.swing.JLabel lb_sjt1;
    private javax.swing.JLabel lb_tab;
    private javax.swing.JLabel lb_tah;
    private javax.swing.JLabel lb_tam;
    private javax.swing.JLabel lb_tas;
    private javax.swing.JLabel lb_terlambatabsenbulanangkatan10;
    private javax.swing.JLabel lb_terlambatabsenbulanangkatan11;
    private javax.swing.JLabel lb_terlambatabsenbulanbdp;
    private javax.swing.JLabel lb_terlambatabsenbulaniangkatan12;
    private javax.swing.JLabel lb_terlambatabsenbulanotkp;
    private javax.swing.JLabel lb_terlambatabsenbulantitl;
    private javax.swing.JLabel lb_terlambatabsenhariangkatan10;
    private javax.swing.JLabel lb_terlambatabsenhariangkatan11;
    private javax.swing.JLabel lb_terlambatabsenhariangkatan12;
    private javax.swing.JLabel lb_terlambatabsenmingguanangkatan10;
    private javax.swing.JLabel lb_terlambatabsenmingguanangkatan11;
    private javax.swing.JLabel lb_terlambatabsenmingguanangkatan12;
    private javax.swing.JLabel lb_terlambatabsenmingguanbdp;
    private javax.swing.JLabel lb_terlambatabsenmingguanotkp;
    private javax.swing.JLabel lb_terlambatabsenmingguantitl;
    private javax.swing.JLabel lb_terlambatabsensmtangkatan10;
    private javax.swing.JLabel lb_terlambatabsensmtangkatan11;
    private javax.swing.JLabel lb_terlambatabsensmtangkatan12;
    private javax.swing.JLabel lb_terlambatabsensmtbdp;
    private javax.swing.JLabel lb_terlambatabsensmtotkp;
    private javax.swing.JLabel lb_terlambatabsensmttitl;
    private javax.swing.JLabel lb_tjb1;
    private javax.swing.JLabel lb_tjo1;
    private javax.swing.JLabel lb_tjt1;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JLabel lbl_waktu;
    private javax.swing.JPanel panel_waktu;
    private javax.swing.JPanel panelchartabsbulanan;
    private javax.swing.JPanel panelchartabsbulanangkatan;
    private javax.swing.JPanel panelchartabsbulanjurusan;
    private javax.swing.JPanel panelchartabsharian;
    private javax.swing.JPanel panelchartabsharianangkatan;
    private javax.swing.JPanel panelchartabsharijurusan;
    private javax.swing.JPanel panelchartabsmingguan;
    private javax.swing.JPanel panelchartabsmingguanangkatan;
    private javax.swing.JPanel panelchartabsminggujurusan;
    private javax.swing.JPanel panelchartabssemester;
    private javax.swing.JPanel panelchartabssmtangkatan;
    private javax.swing.JPanel panelchartabssmtjurusan;
    private javax.swing.JPanel paneldashboard;
    private javax.swing.JPanel panelgrafik;
    private javax.swing.JPanel paneljumlahguru;
    private javax.swing.JPanel paneljumlahkelas;
    private javax.swing.JPanel paneljumlahsiswa;
    private javax.swing.JLabel txt_jam;
    private javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
