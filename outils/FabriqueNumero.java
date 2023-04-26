package twisk.outils;

public class FabriqueNumero {
    private int cptEtape ;
    private int cptSemaphore ;
    private int cptLib;
    private int cptLibCharg;
    private static FabriqueNumero instance = new FabriqueNumero();

    private FabriqueNumero(){
        this.cptEtape = -1 ;
        this.cptSemaphore = 0 ;
        this.cptLib =0;
        this.cptLibCharg =0;
    }

    public static FabriqueNumero getInstance(){
        return instance ;
    }

    public int getNumeroEtape(){
        cptEtape++ ;
        return cptEtape ;
    }

    public int getNumeroSemaphore(){
        cptSemaphore++ ;
        return  cptSemaphore ;
    }

    //Permet de revenir en arriere dans l'ajout de numero d'etape
    public void reset(){
        cptEtape = -1 ;
        cptSemaphore = 0 ;
    }

    public int getCptLib() {
        cptLib++;
        return cptLib;
    }

    public int getCptLibCharg() {
        cptLibCharg++;
        return cptLibCharg;
    }
}
