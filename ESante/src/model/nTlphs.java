
package model;

public class nTlphs {
    private int tlphid;
    private int societeid;
    private int nTlph;

    public nTlphs(int tlphid, int societeid, int nTlph) {
        this.tlphid = tlphid;
        this.societeid = societeid;
        this.nTlph = nTlph;
    }
    public nTlphs(int nTlph) {
        this.tlphid = 0;
        this.societeid = 0;
        this.nTlph = nTlph;
    }

    public int getTlphid() {
        return tlphid;
    }

    public void setTlphid(int tlphid) {
        this.tlphid = tlphid;
    }

    public int getSocieteid() {
        return societeid;
    }

    public void setSocieteid(int societeid) {
        this.societeid = societeid;
    }

    public int getnTlph() {
        return nTlph;
    }

    public void setnTlph(int nTlph) {
        this.nTlph = nTlph;
    }
    
}
