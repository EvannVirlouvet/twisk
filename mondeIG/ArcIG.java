package twisk.mondeIG;

public class ArcIG {
    private PointDeControleIG pdc1;
    private PointDeControleIG pdc2;
    private boolean estSelec;

    public ArcIG(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        this.pdc1 = pdc1;
        this.pdc2 = pdc2;
        estSelec = false;
    }

    public PointDeControleIG getPdc1() {
        return pdc1;
    }

    public PointDeControleIG getPdc2() {
        return pdc2;
    }

    @Override
    public String toString() {
        return "ArcIG{" +
                "pdc1=" + pdc1 +
                ", pdc2=" + pdc2 +
                '}';
    }

    public void setPdc1(PointDeControleIG pdc1) {
        this.pdc1 = pdc1;
    }

    public void setPdc2(PointDeControleIG pdc2) {
        this.pdc2 = pdc2;
    }

    public boolean isEstSelec() {
        return estSelec;
    }

    public void setEstSelec(boolean estSelec) {
        this.estSelec = estSelec;
    }

}
