package RegionP;

public class Region {

    private String owner;

    private String StandHere;

    private boolean isCapital;
    private double currentDeposite = 0 ;

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

    public double getCurrentDeposite() {
        return currentDeposite;
    }

    public void increaseCurdeposit(double curdeposit) {
        this.currentDeposite += curdeposit;
    }

    public void decreaseCurdeposit(double curdeposit) {
        this.currentDeposite -= curdeposit;
    }
    public void setCurrentDeposite(double x){
        currentDeposite = x;}
}