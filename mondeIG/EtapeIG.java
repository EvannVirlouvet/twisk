package twisk.mondeIG;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class EtapeIG implements Iterable<PointDeControleIG> {

    private String nom;
    private String identifiant;
    protected boolean ActiviteRestreinte;
    protected int posX;
    protected int posY;
    protected int delai;
    protected int ecartTemps;
    protected int largeur;
    protected int hauteur;
    protected boolean estRename = false;
    protected int nbJetons;
    protected String debutnom;
    protected ArrayList<PointDeControleIG> pdc ;
    protected transient ArrayList<EtapeIG> successeurs;
    protected String sensDeCirculation = "";
    protected boolean aUnSensDeCirculation = false;



    public EtapeIG(String nom, String idf, int larg, int haut) {
        this.nom = nom;
        this.ActiviteRestreinte = false;
        this.successeurs = new ArrayList<>();
        this.identifiant = idf;
        this.largeur = larg;
        this.hauteur = haut;
        Random random = new Random();
        this.posX = random.nextInt(750);
        this.posY = random.nextInt(550);

    }

    public int nbSucc(){
        return this.successeurs.size();
    }
    public ArrayList<EtapeIG> getSuccesseurs() {
        return successeurs;
    }
    public void ajouterSucc(EtapeIG e){
        this.successeurs.add(e);
    }
    @Override
    public String toString() {
        return "EtapeIG{" +
                "nom='" + nom + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                '}';
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }

    public int getPosX() {
        return posX;
    }

    public int getDelai() {
        return delai;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }

    public String getNom() {
        return nom;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getPosY() {
        return posY;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNewPdc(){
        //Ajout des point de controle selon ly type de l'étape
        if(estUneActivite()) {
            ArrayList<PointDeControleIG> pdc2 = new ArrayList<>(4);
            pdc2.add(new PointDeControleIG(posX, posY + (hauteur / 2), pdc.get(0).getIdUnique(), this));
            pdc2.add(new PointDeControleIG(posX + largeur, posY + (hauteur / 2), pdc.get(1).getIdUnique(), this));
            pdc2.add(new PointDeControleIG(posX + (largeur / 2), posY, pdc.get(2).getIdUnique(), this));
            pdc2.add(new PointDeControleIG(posX + (largeur / 2), posY + hauteur, pdc.get(3).getIdUnique(), this));

            pdc.clear();
            pdc.addAll(pdc2);
        }else if(estUnGuichet()){
            ArrayList<PointDeControleIG> pdc2 = new ArrayList<>(2);
            pdc2.add(new PointDeControleIG(posX, posY + (hauteur / 2) + 15, pdc.get(0).getIdUnique(), this));
            pdc2.add(new PointDeControleIG(posX + largeur + 50, posY + (hauteur / 2) + 15, pdc.get(1).getIdUnique(), this));
            pdc.clear();
            pdc.addAll(pdc2);
        }

    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return pdc.iterator();
    }

    public boolean estRename(){
        return estRename;
    }

    public void setEstRename(boolean estRename) {
        this.estRename = estRename;
    }

    public boolean estUnGuichet(){
        return false;
    }
    public boolean estUneActivite(){
        return false;
    }
    public int pdcSize(){
        return pdc.size();
    }

    public boolean estUneActiviteRestreinte() {
        return ActiviteRestreinte;
    }

    public void setActiviteRestreinte(boolean activiteRestreinte) {
        ActiviteRestreinte = activiteRestreinte;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }
    public String getDebutnom() {
        return debutnom;
    }

    public void setDebutnom(String debutnom) {
        this.debutnom = debutnom;
    }

    public boolean estAccessibleDepuis(EtapeIG etape){
        //Retourne vrai si this est accessible depuis une etapeIG
        if (etape.successeurs.contains(this)) {
            return true;
        }else {
            if(etape.successeurs.size() != 0) {
                for (EtapeIG e : etape.successeurs) {
                    return this.estAccessibleDepuis(e);
                }
            }
        }
        return false;
    }

    public String getSensDeCirculation() {
        return sensDeCirculation;
    }

    public void setSensDeCirculation(String sensDeCirculation) {
        this.sensDeCirculation = sensDeCirculation;
    }
}
