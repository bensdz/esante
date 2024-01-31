/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package esanteInterface;
import static esanteInterface.ESante.*;
import static esanteInterface.MySQLConnection.*;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import javax.swing.JOptionPane;
import model.ConsultationParamed;



/**
 *
 * @author ideap
 */
public class HomePageParamed extends javax.swing.JFrame {

    /** Creates new form HomePageParamed */
    public HomePageParamed() {
        initComponents();
        showConsPMed ();
    }
    
    public void deleteRecordParamed(int conslid) {
        String query = "DELETE FROM ConsultationParamed WHERE consulid = ?";
        String query1 = "DELETE FROM Consultation WHERE consulid = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)) {
            
            pstmt.setInt(1, conslid);
            pstmt1.setInt(1, conslid);
            int rowsDeleted = pstmt.executeUpdate();
            int rowsDeleted1 = pstmt1.executeUpdate();
            if (rowsDeleted > 0 && rowsDeleted1>0) {
                JOptionPane.showMessageDialog(null,"Record deleted successfully!");
                DefaultTableModel model = (DefaultTableModel) conspmedTable.getModel();
                model.setRowCount(0);
                showConsPMed();
                
            } else {
                JOptionPane.showMessageDialog(null,"No records found for the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateRecordParamed(int conslid) {
        String query = "UPDATE Consultation SET nReservation = ?, nSS = ?, resultat = ?, recommendation = ?, etatPat = ? WHERE consulID = ?;";
        String query1 = "UPDATE ConsultationParamed SET traitementEff = ? WHERE consulid = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)) {
            
            pstmt.setInt(1, Integer.parseInt(reserNField.getText()));
            pstmt.setInt(2, Integer.parseInt(nssField.getText()));
            pstmt.setString(3, resultatField.getText());
            pstmt.setString(4, recommendField.getText());
            pstmt.setString(5, etatField.getText());
            pstmt.setInt(6, conslid);
            
            pstmt1.setString(1, traitField.getText());
            pstmt1.setInt(2, conslid);
            int rowsUpdated = pstmt.executeUpdate();
            int rowsUpdated1 = pstmt1.executeUpdate();
            if (rowsUpdated1 > 0 && rowsUpdated>0) {
                JOptionPane.showMessageDialog(null,"Record modified successfully!");
                DefaultTableModel model = (DefaultTableModel) conspmedTable.getModel();
                model.setRowCount(0);
                showConsPMed();
                
            } else {
                JOptionPane.showMessageDialog(null,"No records found for the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getEmployeeCode(String username, String password) {
        String employeeCode = null;
        String query = "SELECT codeEmployee FROM employee WHERE username = ? AND userpw = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employeeCode = rs.getString("codeEmployee");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }

      return employeeCode;
    }
    
    
    public void insertConsultM() {
    String query = "INSERT INTO Consultation VALUES (?, ?, ?, ?, ?, ?, ?)";
    String query1 = "INSERT INTO ConsultationParamed VALUES (?, ?)";
    int codeEmpl=Integer.parseInt(getEmployeeCode(user, pw));
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         PreparedStatement pstmt1 = conn.prepareStatement(query1)) {
        pstmt.setInt(1, Integer.parseInt(consultIDField.getText()));
        pstmt.setInt(2, Integer.parseInt(reserNField.getText()));
        pstmt.setInt(3, Integer.parseInt(nssField.getText()));
        pstmt.setInt(4, codeEmpl);
        pstmt.setString(5, resultatField.getText());
        pstmt.setString(6, recommendField.getText());
        pstmt.setString(7, etatField.getText());
        
        pstmt1.setInt(1, Integer.parseInt(consultIDField.getText()));
        pstmt1.setString(2, traitField.getText());
        
        
        pstmt.executeUpdate();
        pstmt1.executeUpdate();

        JOptionPane.showMessageDialog(null,"Record inserted successfully!");
        DefaultTableModel model = (DefaultTableModel) conspmedTable.getModel();
        model.setRowCount(0);
        showConsPMed();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,"Informations Incorrect");
    }
}  
    //----------------- push data to array ----------
    public ArrayList<ConsultationParamed> conspmedList() {
    ArrayList<ConsultationParamed> conpmedList = new ArrayList<>();
    try {
        Connection conn = (Connection) MySQLConnection.getConnection();

        String query = "SELECT * FROM Consultation c JOIN Consultationparamed cp ON cp.consulID = c.consulID JOIN Employee e ON c.codeEmployee = e.codeEmployee WHERE e.userName = ? AND e.userPW=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, ESante.user);
        st.setString(2, ESante.pw);
        ResultSet rs = st.executeQuery();

        /*String query1 = "SELECT cp.traitementEff FROM ConsultationParamed cp JOIN Consultation c ON cp.consulID = c.consulID JOIN Employee e ON c.codeEmployee = e.codeEmployee WHERE e.userName = ? AND e.userPW=?";
        PreparedStatement st1 = conn.prepareStatement(query1);
        st1.setString(1, ESante.user);
        st1.setString(2, ESante.pw);
        ResultSet rs1 = st1.executeQuery();*/

        while (rs.next()) {
            ConsultationParamed cons = new ConsultationParamed(rs.getInt("consulID"), rs.getInt("nReservation"), rs.getInt("nSS"), rs.getInt("codeEmployee"), rs.getString("resultat"), rs.getString("recommendation"), rs.getString("etatPat"), rs.getString("traitementEff"));
            conpmedList.add(cons);
        }

    } catch (SQLException ex) {
        Logger.getLogger(EmployeesGP.class.getName()).log(Level.SEVERE, null, ex);
    }
    return conpmedList;
    }
    
    //----------------- push array data to table ----------
    public void showConsPMed (){
        ArrayList<ConsultationParamed> list = conspmedList();
        DefaultTableModel model = (DefaultTableModel) conspmedTable.getModel();
        Object[] row= new Object[8];
        for (int i=0;i<list.size();i++) {
           row[0]=list.get(i).getconsulID();
           row[1]=list.get(i).getnReservation();
           row[2]=list.get(i).getNSS();
           row[3]=list.get(i).getCodeEmployee();
           row[4]=list.get(i).getResultat();
           row[5]=list.get(i).getRecommendation();
           row[6]=list.get(i).getEtatPat();
           row[7]=list.get(i).getTraitementEff();
           model.addRow(row);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        nssField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        resultatField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        conspmedTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        recommendField = new javax.swing.JTextField();
        reserNField = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        etatField = new javax.swing.JTextField();
        consultIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        traitField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 12)); // NOI18N
        jLabel10.setText("Traitement Donné:");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel7.setText("Resultat:");

        jButton4.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton4.setText("Modifier");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        conspmedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "N Reservation", "NSS Patient", "N Employee", "Resultat", "Recommendation", "Etat", "Traitement Donné"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        conspmedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conspmedTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(conspmedTable);

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel8.setText("Recommendation:");

        jButton5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton5.setText("Supprimer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gerer votre consultations paramedicales:");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel3.setText("N Reservation:");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel9.setText("Etat de Patient:");

        jButton2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton2.setText("DECONNECTION");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton3.setText("Ajouter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel5.setText("NSS Patient:");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel2.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nssField)
                                                    .addComponent(consultIDField)
                                                    .addComponent(reserNField)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(resultatField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(recommendField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(etatField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(traitField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(consultIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(reserNField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nssField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(resultatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(recommendField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(etatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(traitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        updateRecordParamed(Integer.parseInt(consultIDField.getText()));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        deleteRecordParamed(Integer.parseInt(consultIDField.getText()));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LoginP retour= new LoginP();
        this.setVisible(false);
        retour.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        insertConsultM();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void conspmedTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conspmedTableMouseClicked
       // get the model from the jtable
       DefaultTableModel model = (DefaultTableModel)conspmedTable.getModel();

        // get the selected row index
       int selectedRowIndex = conspmedTable.getSelectedRow();
       
        // set the selected row data into jtextfields
       consultIDField.setText(model.getValueAt(selectedRowIndex, 0).toString());
       reserNField.setText(model.getValueAt(selectedRowIndex, 1).toString());
       nssField.setText(model.getValueAt(selectedRowIndex, 2).toString());
       resultatField.setText(model.getValueAt(selectedRowIndex, 4).toString());
       recommendField.setText(model.getValueAt(selectedRowIndex, 5).toString());
       etatField.setText(model.getValueAt(selectedRowIndex, 6).toString());
       traitField.setText(model.getValueAt(selectedRowIndex, 7).toString());
    }//GEN-LAST:event_conspmedTableMouseClicked

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
            java.util.logging.Logger.getLogger(HomePageParamed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePageParamed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePageParamed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePageParamed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePageParamed().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable conspmedTable;
    private javax.swing.JTextField consultIDField;
    private javax.swing.JTextField etatField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nssField;
    private javax.swing.JTextField recommendField;
    private javax.swing.JTextField reserNField;
    private javax.swing.JTextField resultatField;
    private javax.swing.JTextField traitField;
    // End of variables declaration//GEN-END:variables

}
