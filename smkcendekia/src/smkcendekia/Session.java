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
}
