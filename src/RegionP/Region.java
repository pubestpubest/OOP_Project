package RegionP;

public class Region {

    private int m;
    private int n;
    private String owner;

    public Region(int m, int n) {
        this.m = m;
        this.n = n;
        this.owner = "-";
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}