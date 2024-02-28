class Region {
    private int m;
    private int n;
    private char owner;

    public Region(int m, int n) {
        this.m = m;
        this.n = n;
        this.owner = '-';
    }

    public char getOwner() {
        return owner;
    }

    public void setOwner(char owner) {
        this.owner = owner;
    }
}