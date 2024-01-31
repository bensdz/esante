
package model;

public class Feedback {
    private String commentaire;
    private int nbEtService;
    private int nbEtPonct;
    private int nbEtTraitement;
    private int consulID;

    public Feedback(String commentaire, int nbEtService, int nbEtPonct, int nbEtTraitement, int consulID) {
        this.commentaire = commentaire;
        this.consulID=consulID;
        this.nbEtService = nbEtService;
        this.nbEtPonct = nbEtPonct;
        this.nbEtTraitement = nbEtTraitement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNbEtService() {
        return nbEtService;
    }

    public void setNbEtService(int nbEtService) {
        this.nbEtService = nbEtService;
    }

    public int getNbEtPonct() {
        return nbEtPonct;
    }

    public void setNbEtPonct(int nbEtPonct) {
        this.nbEtPonct = nbEtPonct;
    }

    public int getNbEtTraitement() {
        return nbEtTraitement;
    }

    public void setNbEtTraitement(int nbEtTraitement) {
        this.nbEtTraitement = nbEtTraitement;
    }
}

