
package model;

import java.util.Date;

public class Employee extends Personne {
    private int codeEmployee;
    private String sect;
    private int anciennite;
    private int nCatalogue;
    private String userName;
    private String userPW;
    
    
    /*public enum Secteur {
    Medical,
    Paramedical
    }*/
    
    public Employee (int codeEmployee, String nom,String prenom,Date dateNaiss, String adrPer, String sect,int anciennite, int nCatalogue){
        this.codeEmployee=codeEmployee;
        this.sect=sect;
        this.adrPer=adrPer;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaiss=dateNaiss;
        this.anciennite=anciennite;
        this.nCatalogue=nCatalogue;
    }
    
    public Employee (int codeEmployee, String nom,String prenom,Date dateNaiss, String adrPer, String sect,int anciennite, int nCatalogue,String userName,String userPW){
        this.codeEmployee=codeEmployee;
        this.sect=sect;
        this.adrPer=adrPer;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaiss=dateNaiss;
        this.anciennite=anciennite;
        this.nCatalogue=nCatalogue;
        this.userName=userName;
        this.userPW=userPW;
    }
    
    public String getuserName() {
        return userName;
    }
    
    public void setuserName(String userName) {
        this.userName = userName;
    }
    
    public String getuserPW() {
        return userPW;
    }
    
    public void setuserPW(String userPW) {
        this.userPW = userPW;
    }
    
    
    public int getNCatalogue() {
        return nCatalogue;
    }
    
    public void setNCatalogue(int nCatalogue) {
        this.nCatalogue = nCatalogue;
    }
    
    public int getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(int codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public String getSect() {
        return sect;
    }

    public void setSect(String sect) {
        this.sect = sect;
    }

    public int getAnciennite() {
        return anciennite;
    }

    public void setAnciennite(int anciennite) {
        this.anciennite = anciennite;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getAdrPer() {
        return adrPer;
    }

    public void setAdrPer(String adrPer) {
        this.adrPer = adrPer;
    }
}
