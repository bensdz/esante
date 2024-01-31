
package model;

import java.util.Date;

public class Medcin extends Employee {
    private String specialite;

    public Medcin(int codeEmployee, String nom,String prenom,Date dateNaiss, String adrPer, String sect,int anciennite, int nCatalogue,String userName,String userPW, String specialite) {
        super(codeEmployee, nom, prenom, dateNaiss, adrPer, sect, anciennite,nCatalogue,userName,userPW);
        this.specialite = specialite;
    }
    

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}

