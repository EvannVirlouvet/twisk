package twisk.monde;

public class SasEntree extends Activite{

    public SasEntree(String nom) {
        super(nom);

    }

    public boolean estUneEntree() {
        return true ;
    }


    @Override
    public String toString() {
        return "Entrées : " +
                this.nbSuccesseurs() + " successseur - " +
                gestionnaireSuccesseurs
                ;
    }

}
