package twisk.monde;

public class SasSortie extends Activite{
    public SasSortie(String nom) {
        super(nom);

    }

    @Override
    public boolean estUneSortie() {
        return true ;
    }

    @Override
    public String toString() {
        return "Sortie : " +
                this.nbSuccesseurs() + " successseur - " +
                gestionnaireSuccesseurs
                ;
    }
}
