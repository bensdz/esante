
package model;
import java.util.*;
public class Reservation {
    private int nReservation;
    private String serviceDem;
    private String typeRes;
    private Date dateres;
    private int nSS;

    public Reservation(int nReservation, String serviceDem, String typeRes, Date dateres, int nSS) {
        this.nReservation = nReservation;
        this.serviceDem = serviceDem;
        this.typeRes = typeRes;
        this.dateres = dateres;
        this.nSS = nSS;
    }

    public int getnReservation() {
        return nReservation;
    }

    public void setnReservation(int nReservation) {
        this.nReservation = nReservation;
    }

    public String getServiceDem() {
        return serviceDem;
    }

    public void setServiceDem(String serviceDem) {
        this.serviceDem = serviceDem;
    }

    public String getTypeRes() {
        return typeRes;
    }

    public void setTypeRes(String typeRes) {
        this.typeRes = typeRes;
    }

    public Date getDateres() {
        return dateres;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }

    public int getnSS() {
        return nSS;
    }

    public void setnSS(int nSS) {
        this.nSS = nSS;
    }
}
