
package model;

public class Catalogue {
    private int societeId;
    private int nCatalogue;
    private String nomCatalogue;
    
    public Catalogue(int societeId, int nCatalogue, String nomCatalogue) {
        this.societeId = societeId;
        this.nCatalogue = nCatalogue;
        this.nomCatalogue = nomCatalogue;
    }
    
    public int getSocieteId() {
        return societeId;
    }
    
    public void setSocieteId(int societeId) {
        this.societeId = societeId;
    }
    
    public int getNCatalogue() {
        return nCatalogue;
    }
    
    public void setNCatalogue(int nCatalogue) {
        this.nCatalogue = nCatalogue;
    }
    
    public String getNomCatalogue() {
        return nomCatalogue;
    }
    
    public void setNomCatalogue(String nomCatalogue) {
        this.nomCatalogue = nomCatalogue;
    }
}
