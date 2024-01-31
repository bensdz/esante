
package model;

public class Stats {
    private int codeEmployee;
    private int nbVisites;
    private int reservAnnulee;
    private float nbVisitesPat;
    private float nbAppelsJour;
  
   
    public Stats(int codeEmployee, int nbVisites, int reservAnnulee, float nbVisitesPat, float nbAppelsJour) {
        this.codeEmployee = codeEmployee;
        this.nbVisites = nbVisites;
        this.reservAnnulee = reservAnnulee;
        this.nbVisitesPat = nbVisitesPat;
        this.nbAppelsJour = nbAppelsJour;
    }
    
    // Getter methods
    public int getCodeEmployee() {
        return codeEmployee;
    }
    
    public int getNbVisites() {
        return nbVisites;
    }
    
    public int getReservAnnulee() {
        return reservAnnulee;
    }
    
    public float getNbVisitesPat() {
        return nbVisitesPat;
    }
    
    public float getNbAppelsJour() {
        return nbAppelsJour;
    }
    
    // Setter methods
    public void setCodeEmployee(int codeEmployee) {
        this.codeEmployee = codeEmployee;
    }
    
    public void setNbVisites(int nbVisites) {
        this.nbVisites = nbVisites;
    }
    
    public void setReservAnnulee(int reservAnnulee) {
        this.reservAnnulee = reservAnnulee;
    }
    
    public void setNbVisitesPat(float nbVisitesPat) {
        this.nbVisitesPat = nbVisitesPat;
    }
    
    public void setNbAppelsJour(float nbAppelsJour) {
        this.nbAppelsJour = nbAppelsJour;
    }
}


