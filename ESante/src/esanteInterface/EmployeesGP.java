/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package esanteInterface;
import static esanteInterface.MySQLConnection.getConnection;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.Medcin;
/**
 *
 * @author ideap
 */
public class EmployeesGP extends javax.swing.JFrame {

    /**
     * Creates new form EmployeesGP
     */
    public EmployeesGP() {
        initComponents();
        showEmployees();
    }
    
public void updateEmployee() {
    String query = "UPDATE Employee SET nom = ?, prenom = ?, dateNaiss = ?, adrPer = ?, sect = ?, anciennite = ?, nCatalogue =?, userName = ?,userPW = ? WHERE codeEmployee = ?;";
    String query1 = "UPDATE Medcin SET specialite = ? WHERE codeEmployee = ?";
    String query2 = "SELECT * From Medcin WHERE codeEmployee = ?";
    String query3 = "INSERT INTO Medcin Values (?,?)";
    String query4 = "DELETE FROM Medcin WHERE codeEmployee = ?";
    SecteurRadioField.setActionCommand("Medcin");
    SecteurRadioField1.setActionCommand("Paramedicale");
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        PreparedStatement pstmt1 = conn.prepareStatement(query1);
        PreparedStatement pstmt2 =conn.prepareStatement(query2);
        PreparedStatement pstmt3 = conn.prepareStatement(query3);
        PreparedStatement pstmt4 = conn.prepareStatement(query4);) {
            pstmt.setInt(10, Integer.parseInt(codeField.getText()));
            pstmt.setString(1,nameField.getText());
            pstmt.setString(2, prenomField.getText());
            pstmt.setString(3, datenaissanceField1.getText());
            pstmt.setString(4, addresseField.getText());
            pstmt.setString(5, typeEmployee.getSelection().getActionCommand());
            pstmt.setInt(6, Integer.parseInt(ancienniteField.getText()));
            pstmt.setInt(7, Integer.parseInt(codecatalogueField.getText()));
            pstmt.setString(8, userField.getText());
            pstmt.setString(9, PasswordField.getText());
            
            pstmt.executeUpdate();
            
            if (typeEmployee.getSelection().getActionCommand()!=null && typeEmployee.getSelection().getActionCommand().equals("Medcin")) {
                pstmt2.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    pstmt1.setInt(2,Integer.parseInt(codeField.getText()));
                    pstmt1.setString(1, specialiteField.getText());
                    pstmt1.executeUpdate();
                }
                
                else {
                    pstmt3.setInt(1,Integer.parseInt(codeField.getText()));
                    pstmt3.setString(2, specialiteField.getText());
                    pstmt3.executeUpdate(); 
                }

            }
            
            else {
               pstmt2.setInt(1, Integer.parseInt(codeField.getText()));
               ResultSet rs2 = pstmt2.executeQuery(); 
               if (rs2.next()) {
                    pstmt4.setInt(1,Integer.parseInt(codeField.getText()));
                    pstmt4.executeUpdate();
               }
            }
            
            JOptionPane.showMessageDialog(null,"Record modified successfully!");

            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0);
            showEmployees();
            
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,"Informations Incorrect");
    }
}
    
  public void deleteRecordEmployee() {
        String query = "DELETE FROM employee WHERE codeEmployee = ?";
        String query1 = "DELETE FROM medcin WHERE codeEmployee = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)
            ) {
            pstmt.setInt(1, Integer.parseInt(codeField.getText()));
            
            if (typeEmployee.getSelection().getActionCommand()!=null && typeEmployee.getSelection().getActionCommand().equals("Medcin")) {
                pstmt1.setInt(1, Integer.parseInt(codeField.getText()));
                pstmt1.executeUpdate();
            }
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null,"Record deleted successfully!");
                DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                model.setRowCount(0);
                showEmployees();
                
            } else {
                JOptionPane.showMessageDialog(null,"No records found for the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!");
        }
    }
    
 public void insertEmployee() {
    String query = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String query1 = "INSERT INTO medcin VALUES (?, ?)";
    SecteurRadioField.setActionCommand("Medcin");
    SecteurRadioField1.setActionCommand("Paramedicale");
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        PreparedStatement pstmt1 = conn.prepareStatement(query1)) {
            pstmt.setInt(1, Integer.parseInt(codeField.getText()));
            pstmt.setString(2,nameField.getText());
            pstmt.setString(3, prenomField.getText());
            pstmt.setString(4, datenaissanceField1.getText());
            pstmt.setString(5, addresseField.getText());
            pstmt.setString(6, typeEmployee.getSelection().getActionCommand()); 
            pstmt.setInt(7,Integer.parseInt(ancienniteField.getText()));
            pstmt.setInt(8,Integer.parseInt(codecatalogueField.getText()));
            pstmt.setString(9, userField.getText());
            pstmt.setString(10, PasswordField.getText());
            
            pstmt.executeUpdate();
            
            if (typeEmployee.getSelection().getActionCommand()!=null && typeEmployee.getSelection().getActionCommand().equals("Medcin")) {
                pstmt1.setInt(1,Integer.parseInt(codeField.getText()));
                pstmt1.setString(2, specialiteField.getText());
                
                pstmt1.executeUpdate();
            }
            
            JOptionPane.showMessageDialog(null,"Record inserted successfully!");

            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0);
            showEmployees();
            
    } catch (SQLIntegrityConstraintViolationException e) {
        
        JOptionPane.showMessageDialog(null,"Employee with the same code already exists in the database.");
    } catch (SQLException e) {
        
        JOptionPane.showMessageDialog(null,"An error occurred while inserting the employee record.");
    }
}
    
 public ArrayList<Medcin> employeeList () {
      ArrayList<Medcin> empList = new ArrayList<>();
        try {
            Connection conn = (Connection) MySQLConnection.getConnection();
            String query="SELECT * From Employee";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            String query1="SELECT specialite From Medcin";
            Statement st1=conn.createStatement();
            ResultSet rs1=st1.executeQuery(query1);
            Medcin emp;
            while (rs.next()) {
                if (rs.getString("sect")!=null && rs.getString("sect").equals("Medcin")) {
                    String specialite=null;
                    if (rs1.next()) {
                        //rs1.next()
                        specialite = rs1.getString("specialite");
                    }
                    emp = new Medcin(rs.getInt("codeEmployee"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaiss"), rs.getString("adrPer"), rs.getString("sect"), rs.getInt("anciennite"), rs.getInt("nCatalogue"), rs.getString("username"), rs.getString("userpw"), specialite);
                } else {
                    emp = new Medcin(rs.getInt("codeEmployee"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaiss"), rs.getString("adrPer"), rs.getString("sect"), rs.getInt("anciennite"), rs.getInt("nCatalogue"), rs.getString("username"), rs.getString("userpw"), "");
                }
                empList.add(emp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesGP.class.getName()).log(Level.SEVERE, null, ex);
        }
      return empList;
    } 
    public void showEmployees (){
        ArrayList<Medcin> list = employeeList();
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        Object[] row= new Object[11];
        for (int i=0;i<list.size();i++) {
           row[0]=list.get(i).getCodeEmployee();
           row[1]=list.get(i).getNom();
           row[2]=list.get(i).getPrenom();
           row[3]=list.get(i).getDateNaiss();
           row[4]=list.get(i).getAdrPer();
           row[5]=list.get(i).getSect();
           row[6]=list.get(i).getAnciennite();
           row[7]=list.get(i).getNCatalogue();
           row[8]=list.get(i).getuserName();
           row[9]=list.get(i).getuserPW();
           row[10]="";
           if (row[5]!=null && row[5].equals("Medcin")){
               row[10]=list.get(i).getSpecialite();
           }
           model.addRow(row);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeEmployee = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        ajouter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        modifier = new javax.swing.JButton();
        prenomField = new javax.swing.JTextField();
        supprimer = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        codecatalogueField = new javax.swing.JTextField();
        userField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        datenaissanceField1 = new javax.swing.JTextField();
        ancienniteField = new javax.swing.JTextField();
        addresseField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JTextField();
        SecteurRadioField = new javax.swing.JRadioButton();
        SecteurRadioField1 = new javax.swing.JRadioButton();
        specialiteField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel6.setText("Date Naissance:");

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Nom", "Prenom", "Date Naissance", "Adresse", "Secteur", "Anciennité", "Code Catalogue", "User", "Password", "Specialité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel7.setText("Secteur:");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel8.setText("Anciennite:");

        jButton2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton2.setText("RETOUR");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ajouter.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        ajouter.setText("Ajouter");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel2.setText("Nom:");

        modifier.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        modifier.setText("Modifier");
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        prenomField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomFieldActionPerformed(evt);
            }
        });

        supprimer.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        supprimer.setText("Supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gerer Employees:");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel3.setText("Prenom:");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel5.setText("Code:");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel9.setText("Code Catalogue:");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel10.setText("User:");

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel11.setText("Password:");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N

        ancienniteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ancienniteFieldActionPerformed(evt);
            }
        });

        addresseField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addresseFieldActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel13.setText("Adresse");

        typeEmployee.add(SecteurRadioField);
        SecteurRadioField.setText("Medcin");
        SecteurRadioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecteurRadioFieldActionPerformed(evt);
            }
        });

        typeEmployee.add(SecteurRadioField1);
        SecteurRadioField1.setText("Paramedicale");
        SecteurRadioField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecteurRadioField1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel14.setText("Specialité medcin:");

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
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameField)
                                    .addComponent(prenomField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codecatalogueField))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(5, 5, 5)
                                .addComponent(ancienniteField))
                            .addComponent(ajouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(specialiteField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(datenaissanceField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addresseField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SecteurRadioField1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SecteurRadioField)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(prenomField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(datenaissanceField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(addresseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(SecteurRadioField1)
                                    .addComponent(SecteurRadioField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(ancienniteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codecatalogueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(specialiteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ajouter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modifier)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(supprimer)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        HomePageAdmin retour= new HomePageAdmin();
        this.setVisible(false);
        retour.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
       insertEmployee();
    }//GEN-LAST:event_ajouterActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        updateEmployee();
    }//GEN-LAST:event_modifierActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
       deleteRecordEmployee();
    }//GEN-LAST:event_supprimerActionPerformed

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked
                                               
       // get the model from the jtable
       DefaultTableModel model = (DefaultTableModel)employeeTable.getModel();

        // get the selected row index
       int selectedRowIndex = employeeTable.getSelectedRow();
       
        // set the selected row data into jtextfields
       nameField.setText(model.getValueAt(selectedRowIndex, 1).toString());
       prenomField.setText(model.getValueAt(selectedRowIndex, 2).toString());
       codeField.setText(model.getValueAt(selectedRowIndex, 0).toString());
       datenaissanceField1.setText(model.getValueAt(selectedRowIndex, 3).toString());
       addresseField.setText(model.getValueAt(selectedRowIndex, 4).toString());
       Object value = model.getValueAt(selectedRowIndex, 5);
        if (value != null) {
            String secteur = value.toString();
            if (secteur.equals("Medcin")) {
                typeEmployee.setSelected(SecteurRadioField.getModel(), true);
            } else {
                typeEmployee.setSelected(SecteurRadioField1.getModel(), true);
            }
        }
       ancienniteField.setText(model.getValueAt(selectedRowIndex, 6).toString());
       codecatalogueField.setText(model.getValueAt(selectedRowIndex, 7).toString());
       userField.setText(model.getValueAt(selectedRowIndex, 8).toString());
       PasswordField.setText(model.getValueAt(selectedRowIndex, 9).toString());
       specialiteField.setText(model.getValueAt(selectedRowIndex, 10).toString());
    }//GEN-LAST:event_employeeTableMouseClicked

    private void prenomFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomFieldActionPerformed

    private void addresseFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addresseFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addresseFieldActionPerformed

    private void ancienniteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ancienniteFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ancienniteFieldActionPerformed

    private void SecteurRadioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecteurRadioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SecteurRadioFieldActionPerformed

    private void SecteurRadioField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecteurRadioField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SecteurRadioField1ActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeesGP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesGP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesGP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesGP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeesGP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PasswordField;
    private javax.swing.JRadioButton SecteurRadioField;
    private javax.swing.JRadioButton SecteurRadioField1;
    private javax.swing.JTextField addresseField;
    private javax.swing.JButton ajouter;
    private javax.swing.JTextField ancienniteField;
    private javax.swing.JTextField codeField;
    private javax.swing.JTextField codecatalogueField;
    private javax.swing.JTextField datenaissanceField1;
    private javax.swing.JTable employeeTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField prenomField;
    private javax.swing.JTextField specialiteField;
    private javax.swing.JButton supprimer;
    private javax.swing.ButtonGroup typeEmployee;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
