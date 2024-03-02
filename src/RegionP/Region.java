package RegionP;

public class Region {

    private String owner;

    private String StandHere;

    private boolean isCapital;
    private double curdeposit = 0 ;

    public Region(int m, int n) {
        StandHere = "-";
        this.owner = "-";
    }

    public String isStandHere() {
        return StandHere;
    }

    public void setStandHere(String standHere) {
        StandHere = standHere;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public double getCurdeposit() {
        return curdeposit;
    }

    public void increaseCurdeposit(double curdeposit) {
        this.curdeposit += curdeposit;
    }

    public void decreaseCurdeposit(double curdeposit) {
        this.curdeposit -= curdeposit;
    }
}