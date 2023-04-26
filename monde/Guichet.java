package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public class Guichet extends Etape{
    private int nbjetons ;
    private int numeroSemaphore;

    //Creer un guichet
    public Guichet(String nom) {
        super(nom);
        nbjetons = 3 ;
        this.numeroSemaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    //Creer un guichet ou l'on doit choisir le nbJeton
    public Guichet(String nom, int nb){
        super(nom);
        this.nbjetons = nb ;
        this.numeroSemaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    public int getNbjetons() {
        return nbjetons;
    }

    public boolean estUnGuichet(){
        return true;
    }

    public int getNumeroSemaphore() {
        return numeroSemaphore;
    }

    @Override
    public String toString() {
        return  nom  + " : " + gestionnaireSuccesseurs.nbEtapes() +
                " successeur - " + gestionnaireSuccesseurs
                ;
    }
    @Override
    public String toC() {
        StringBuilder sb = new StringBuilder() ;
        sb.append("P(ids,"+"num_sem_guichet_"+numeroSemaphore+") ;\n") ;
        sb.append("transfert("+this.nom+","+gestionnaireSuccesseurs.getNext().getNom()+") ;\n") ;
        if(this.gestionnaireSuccesseurs.getNext().nbSuccesseurs() == 1){
            sb.append(nextToC()) ;
            sb.append("V(ids,"+"num_sem_guichet_"+numeroSemaphore+") ;\n") ;
            sb.append("transfert(" + this.gestionnaireSuccesseurs.getNext().getNom()+ "," + this.gestionnaireSuccesseurs.getNext().gestionnaireSuccesseurs.getNext().getNom() + ") ;\n");

            sb.append(gestionnaireSuccesseurs.getNext().gestionnaireSuccesseurs.getNext().toC()+"\n");
        }else if (this.gestionnaireSuccesseurs.getNext().nbSuccesseurs() > 1) {
            sb.append("srand( time( NULL ) );\n");
            sb.append("nb = (int) ((rand() / (float) RAND_MAX) * " + this.gestionnaireSuccesseurs.getNext().nbSuccesseurs() + ");\n");
            sb.append("switch (nb) {\n");
            for (int i = 0; i < this.gestionnaireSuccesseurs.getNext().nbSuccesseurs(); i++) {
                sb.append(" case " + i + ": \n");
                sb.append(nextToC());
                sb.append("   transfert(" + this.gestionnaireSuccesseurs.getNext().getNom() + "," + this.gestionnaireSuccesseurs.getNext().gestionnaireSuccesseurs.getNext(i).getNom() + ") ;\n");
                sb.append("   V(ids,"+"num_sem_guichet_"+numeroSemaphore+") ;\n") ;
                sb.append(gestionnaireSuccesseurs.getNext().gestionnaireSuccesseurs.getNext(i).toC()+"\n");
                sb.append("     break;\n");


            }

            sb.append("}\n");
        }


        return sb.toString();
    }
}
