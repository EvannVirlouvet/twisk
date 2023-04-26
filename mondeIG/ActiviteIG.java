package twisk.mondeIG;

import javafx.geometry.Pos;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;

public class ActiviteIG extends EtapeIG {

    public ActiviteIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        this.pdc = new ArrayList<>(4);
        this.ecartTemps = 1;
        this.delai = 5;
        //ajout des point de controles
        this.pdc.add(new PointDeControleIG(posX,posY+(hauteur/2), FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;
        this.pdc.add(new PointDeControleIG(posX+largeur,posY+(hauteur/2), FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;
        this.pdc.add(new PointDeControleIG(posX+(largeur/2),posY, FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;
        this.pdc.add(new PointDeControleIG(posX+(largeur/2),posY+hauteur, FabriqueIdentifiant.getInstance().getIdentifiantPdc(), this)) ;

    }
    public boolean estUneActivite(){
        return true;
    }

    public boolean estUneActiviteRestreinte() {
        return ActiviteRestreinte;
    }

}