package twisk.monde;

public class Activite extends Etape {
    protected int temps ;
    protected int ecartTemps ;

    //Creer une activité avec un temps et un ecart predefini
    public Activite(String nom){
         super(nom);
         this.temps = 5;
         this.ecartTemps = 2;

    }
    //Creer une activité ou l'on choisi le temps et l'ecart temps
    public Activite(String nom, int t, int e){
        super(nom);
        this.temps = t ;
        this.ecartTemps = e ;

    }

    public boolean estUneSortie(){ return false ;}

    public boolean estUneEntree(){return false ;} ;
    public boolean estUneActivite(){
        return true;
    }

    @Override
    public String toString() {
        return  nom  + " : " + gestionnaireSuccesseurs.nbEtapes() +
                " successeur - " + gestionnaireSuccesseurs
                ;
    }

    //Permet de creer le code C
    @Override
    public String toC() {

        StringBuilder sb = new StringBuilder();
        if (nbSuccesseurs() >1) {
            sb.append("delai(" + temps + "," + ecartTemps + ") ;\n");
            sb.append("srand( time( NULL ) );\n");
            sb.append("nb = (int) ((rand() / (float) RAND_MAX) * " + nbSuccesseurs() + ");\n");

            sb.append("switch (nb) {\n");
            for (int i = 0; i < nbSuccesseurs(); i++) {
                sb.append(" case " + i + ": \n");
                sb.append("     transfert(" + this.nom + "," + this.gestionnaireSuccesseurs.getNext(i).getNom() + ") ;\n");
                sb.append(gestionnaireSuccesseurs.getNext(i).toC()+"\n") ;
                sb.append("     break;\n");

            }
            sb.append("}\n");
        }
        if(nbSuccesseurs() == 1){
            sb.append("delai(" + temps + "," + ecartTemps + ") ;\n");
            sb.append("transfert(" + this.nom + "," + this.gestionnaireSuccesseurs.getNext().getNom() + ") ;\n");
            sb.append(gestionnaireSuccesseurs.getNext().toC());
        }
        return sb.toString();

    }
}
