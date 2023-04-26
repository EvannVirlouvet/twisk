package twisk.outils;

public class FabriqueIdentifiant {
    private int noEtape;
    private int noPdc;



    private static FabriqueIdentifiant instance = new FabriqueIdentifiant();

    public static FabriqueIdentifiant getInstance(){
        return instance;
    }

    public String getIdentifiantEtape(){
        noEtape++;
        String etape = new String("Etape " + String.valueOf(noEtape));
        return etape;
    }
    public String getIdentifiantPdc(){
        noPdc++;
        String pdc = new String("PointDeControle " + String.valueOf(noPdc));
        return pdc;
    }

    @Override
    public String toString() {
        return "FabriqueIdentifiant{" +
                "noEtape=" + noEtape +
                '}';
    }
}
