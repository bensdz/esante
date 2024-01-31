
package model;


public class ConsultationParamed extends Consultation {
    private String traitementEff;
    
    public ConsultationParamed(int consulID, int nReservation, int nSS, int codeEmployee, String resultat, String recommendation, String etatPat, String traitementEff) {
        super(consulID, nReservation, nSS, codeEmployee, resultat, recommendation, etatPat);
        this.traitementEff = traitementEff;
    }
    
    public String getTraitementEff() {
        return traitementEff;
    }
    
    public void setTraitementEff(String traitementEff) {
        this.traitementEff = traitementEff;
    }
}
