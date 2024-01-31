package model;


public class ConsultationMed extends Consultation {
    private String traitementDonne;
    
    public ConsultationMed(int consulID,int nReservation, int nSS, int codeEmployee, String resultat, String recommendation, String etatPat, String traitementDonne) {
        super(consulID,nReservation, nSS, codeEmployee, resultat, recommendation, etatPat);
        this.traitementDonne = traitementDonne;
    }
    
    public String getTraitementDonne() {
        return traitementDonne;
    }
    
    public void setTraitementDonne(String traitementDonne) {
        this.traitementDonne = traitementDonne;
    }
}
