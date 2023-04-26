package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.FabriqueNumero;

import java.util.ArrayList;

public class GuichetIG extends EtapeIG{
    public GuichetIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        this.pdc = new ArrayList<>(2);
        this.successeurs = new ArrayList<>(1);
        nbJetons = 3;
        debutnom="Guichet";
        //Ajout des Pdc
        this.pdc.add(new PointDeControleIG(posX,posY+(hauteur/2)+15, FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;
        this.pdc.add(new PointDeControleIG(posX+largeur+50,posY+(hauteur/2)+15, FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;
    }
    public boolean estUnGuichet(){
        return true;
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
}
