/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

public class Session {
    public static String usernm;
    public static String nissiswa;
    public static String nipguru;
    public static String nipadmin;
    public static String editadmin;
    public static String nipwalas;
    public static String nkkelas;
    public static String absen;
    
    public static String getusername(){
        return usernm;
    }
    
    public static void setusername(String usernm){
        Session.usernm=usernm;
    }
    
    public static void setnissiswa(String nissiswa){
        Session.nissiswa=nissiswa;
    }
    
    public static String getnissiswa(){
        return nissiswa;
    }
    
    public static void setnipguru(String nipguru){
        Session.nipguru=nipguru;
    }
    
    public static String getnipguru(){
        return nipguru;
    }
    
    public static void setnipadmin (String nipadmin){
        Session.nipadmin = nipadmin;
    }
    
    public static String getnipadmin (){
        return nipadmin;
    }
    
    public static void seteditadmin (String editadmin){
        Session.editadmin = editadmin;
    }
    
    public static String geteditadmin (){
        return editadmin;
    }
    
    public static void setnipwalas (String nipwalas){
        Session.nipwalas = nipwalas;
    }
    
    public static String getnipwalas (){
        return nipwalas;
    }
    
    public static void setnkkelas(String nkkelas){
        Session.nkkelas = nkkelas;
    }
    
    public static String getnkkelas (){
        return nkkelas;
    }
    
      public static void setabsen(String absen){
        Session.absen= absen;
    }
    
    public static String getabsen(){
        return absen;
    }
}
