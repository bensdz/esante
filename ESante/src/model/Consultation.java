
package model;


public class Consultation {
    private int consulID;
    private int nReservation;
    private int nSS;
    private int codeEmployee;
    private String resultat;
    private String recommendation;
    private String etatPat;
    
    public Consultation(int consulID, int nReservation, int nSS, int codeEmployee, String resultat, String recommendation, String etatPat) {
        this.nSS = nSS;
        this.consulID=consulID;
        this.codeEmployee = codeEmployee;
        this.resultat = resultat;
        this.recommendation = recommendation;
        this.etatPat = etatPat;
        this.nReservation=nReservation;
    }
    
    public int getconsulID() {
        return consulID;
    }
    
    public void setconsulID(int consulID) {
        this.consulID = consulID;
    }
    
    public int getnReservation() {
        return nReservation;
    }
    
    public void setnReservation(int nReservation) {
        this.nReservation = nReservation;
    }
    
    public int getNSS() {
        return nSS;
    }
    
    public void setNSS(int nSS) {
        this.nSS = nSS;
    }
    
    public int getCodeEmployee() {
        return codeEmployee;
    }
    
    public void setCodeEmployee(int codeEmployee) {
        this.codeEmployee = codeEmployee;
    }
    
    public String getResultat() {
        return resultat;
    }
    
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
    public String getRecommendation() {
        return recommendation;
    }
    
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    
    public String getEtatPat() {
        return etatPat;
    }
    
    public void setEtatPat(String etatPat) {
        this.etatPat = etatPat;
    }
}

