package RegionP;

public class Region {

    private int m;
    private int n;
    private String owner;

    private boolean StandHere;

    public Region(int m, int n) {
        StandHere = false;
        this.m = m;
        this.n = n;
        this.owner = "-";
    }

    public boolean isStandHere() {
        return StandHere;
    }

    public void setStandHere(boolean standHere) {
        StandHere = standHere;
    }

    public String getOwner() {
        return owner;
    }

    public void setPosition(String owner) {
        this.owner = owner;
    }

}