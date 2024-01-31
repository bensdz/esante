
package model;
import java.util.*;

public class Societe {
    private int societeId;
    private String adresse;
    private String siteWeb;
    private ArrayList<Integer> nTlphs;
    private int nCCP;
    
    public Societe(int societeId, String adresse, String siteWeb, int nCCP) {
        this.adresse = adresse;
        this.siteWeb = siteWeb;
        this.nTlphs = nTlphs;
        this.nCCP = nCCP;
        this.societeId=societeId;
    }
    
    public int getsocieteId() {
        return societeId;
    }

    public void setsocieteId(int societeId) {
        this.societeId = societeId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public ArrayList<Integer> getNTlphs() {
        return nTlphs;
    }

    public void setNTlphs(ArrayList<Integer> nTlphs) {
        this.nTlphs = nTlphs;
    }

    public int getNCCP() {
        return nCCP;
    }

    public void setNCCP(int nCCP) {
        this.nCCP = nCCP;
    }
}

