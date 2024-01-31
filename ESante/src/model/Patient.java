
package model;

import java.util.Date;


public class Patient extends Personne{
    private int nSS;
    private int nTlphPat;
    private String adrMail;

    public Patient (int nSS, String nom,String prenom, Date dateNaiss, String adrPer, int nTlphPat, String adrMail){
        this.adrMail=adrMail;
        this.nTlphPat=nTlphPat;
        this.nSS=nSS;
        this.adrPer=adrPer;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaiss=dateNaiss;
    }

        public int getNSS() {
        return nSS;
    }

    public void setNSS(int nSS) {
        this.nSS = nSS;
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

    public int getNTlphPat() {
        return nTlphPat;
    }

    public void setNTlphPat(int nTlphPat) {
        this.nTlphPat = nTlphPat;
    }

    public String getAdrMail() {
        return adrMail;
    }

    public void setAdrMail(String adrMail) {
        this.adrMail = adrMail;
    }
}

