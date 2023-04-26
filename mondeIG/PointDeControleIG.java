package twisk.mondeIG;


public class PointDeControleIG {
    private int x;
    private int y;
    private String idUnique;
    private transient EtapeIG etape;

    public PointDeControleIG(int x,int y, String idUnique, EtapeIG etape) {
        this.x = x;
        this.y = y;
        this.idUnique = idUnique;
        this.etape = etape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getIdUnique() {
        return idUnique;
    }

    public EtapeIG getEtape() {
        return etape;
    }

    @Override
    public String toString() {
        return "PointDeControleIG{" +
                "x=" + x +
                ", y=" + y +
                ", idUnique='" + idUnique + '\'' +
                ", etape=" + etape +
                '}';
    }

}
