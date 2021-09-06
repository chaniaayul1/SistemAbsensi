/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class data_admin extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi k;
    DefaultTableModel model;
    
    
    public data_admin() {
        initComponents();
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        username.setText(Session.getusername());
        tampil();
        
        JTableHeader header = tabadmin.getTableHeader();
        header.setBackground(Color.blue);
        header.setForeground(Color.black);
        tabadmin.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        tabadmin.setFont(new Font("Tahoma",Font.PLAIN,16));
        
    }

    public void tampil(){        
        model = new DefaultTableModel ( );
        tabadmin.setModel(model);
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
            
             tabadmin.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
      }
    }
    
    public void deletedata(){
        model = new DefaultTableModel ( );
        tabadmin.setModel(model);
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
    
    public void querysearchklik(){
        int i = tabadmin.getSelectedRow();
        if(i>-1){
            saveadm.setText(model.getValueAt(i, 1).toString());
            txt_search.setText(model.getValueAt(i, 2).toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAdmin = new javax.swing.JPanel();
        delete = new javax.swing.JButton();
        registrasi = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        lihat = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        username = new javax.swing.JLabel();
        tableadmin = new javax.swing.JScrollPane();
        tabadmin = new javax.swing.JTable();
        Background = new javax.swing.JLabel();
        saveadm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setText("Delete");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        PanelAdmin.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 90, 40));

        registrasi.setText("Tambah Admin");
        registrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrasiActionPerformed(evt);
            }
        });
        PanelAdmin.add(registrasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 100, 40));

        edit.setText("Edit");
        PanelAdmin.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 110, 40));

        lihat.setText("Lihat");
        lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatActionPerformed(evt);
            }
        });
        PanelAdmin.add(lihat, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 100, 40));

        txt_search.setBackground(new java.awt.Color(240, 240, 240));
        txt_search.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelAdmin.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 250, 40));

        username.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        username.setForeground(new java.awt.Color(39, 113, 184));
        username.setText("INI USERNAME");
        PanelAdmin.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 190, 60));

        tableadmin.setBackground(new java.awt.Color(255, 255, 255));
        tableadmin.setBorder(null);

        tabadmin.setForeground(new java.awt.Color(255, 255, 255));
        tabadmin.setModel(new javax.swing.table.DefaultTableModel(
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
        tabadmin.setFocusable(false);
        tabadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabadminMouseClicked(evt);
            }
        });
        tableadmin.setViewportView(tabadmin);

        PanelAdmin.add(tableadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 287, 1280, 360));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/data_admin.png"))); // NOI18N
        PanelAdmin.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 760));

        saveadm.setText("jLabel1");
        PanelAdmin.add(saveadm, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 690, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        this.deletedata();
    }//GEN-LAST:event_deleteMouseClicked

    private void registrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrasiActionPerformed
        registrasi reg=new registrasi();
        reg.Autonumber();
        reg.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_registrasiActionPerformed

    private void lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lihatActionPerformed

    private void tabadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabadminMouseClicked
        this.querysearchklik();
    }//GEN-LAST:event_tabadminMouseClicked

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
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel PanelAdmin;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton lihat;
    private javax.swing.JButton registrasi;
    private javax.swing.JLabel saveadm;
    private javax.swing.JTable tabadmin;
    private javax.swing.JScrollPane tableadmin;
    private javax.swing.JTextField txt_search;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
