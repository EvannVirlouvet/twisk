package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{
    protected String nom;
    protected GestionnaireSuccesseurs gestionnaireSuccesseurs;
    private int fabrique ;
    private String identifiant = "";
    private int nbDoublons;

    private Monde monde ;
    public void clear(){
        gestionnaireSuccesseurs.clear();
    }

    //Creer une etape
    public Etape(String nom) {
        this.nbDoublons =2;
        this.nom = nom;
        this.gestionnaireSuccesseurs = new GestionnaireSuccesseurs();
        this.fabrique = FabriqueNumero.getInstance().getNumeroEtape();
    }

    public Monde getMonde() {
        return monde;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getNbDoublons() {
        return nbDoublons;
    }

    public void setNbDoublons(int nbDoublons) {
        this.nbDoublons = nbDoublons;
    }

    public GestionnaireSuccesseurs getGestionnaireSuccesseurs() {
        return gestionnaireSuccesseurs;
    }

    //ajoute les succeseurs dans le gestionnaireSuccesseurs
    public void ajouterSuccesseur(Etape...etapes){
        for(Etape e : etapes){
            gestionnaireSuccesseurs.ajouter(e);
        }

    }

    //compte le nombre de successeur dans le gestionnaire de successeur
    public int nbSuccesseurs(){

        int count =0;
        Iterator<Etape> iterator = gestionnaireSuccesseurs.iterator();
        while (iterator.hasNext()){
            count++;
            iterator.next();
        }

        return gestionnaireSuccesseurs.nbEtapes();
    }

    public boolean estUneActivite(){
        return false;
    }
    public boolean estUnGuichet(){
        return  false;
    }

    public boolean estUneSortie(){ return false ;}


    @Override
    public Iterator<Etape> iterator() {
        return gestionnaireSuccesseurs.iterator();
    }

    public int getFabrique() {
        return fabrique;
    }

    @Override
    public String toString() {
        return nom + " : " + gestionnaireSuccesseurs.nbEtapes() +
                " successeur - " + gestionnaireSuccesseurs ;
    }

    public abstract String toC() ;

    public String getNom() {
        return nom;
    }

    //creer le code C de l'etape d'apres l'etape actuel
    public String nextToC(){
        return this.gestionnaireSuccesseurs.getNext().toC();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean estUneEntree(){ return false;}
}
