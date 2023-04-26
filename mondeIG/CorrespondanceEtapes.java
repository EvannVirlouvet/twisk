package twisk.mondeIG;

import twisk.monde.Etape;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;

public class CorrespondanceEtapes {

    private HashMap<String,Etape>  etapes ;

    private HashMap<String,EtapeIG> etapesIG;

    public CorrespondanceEtapes(){
        etapesIG = new HashMap() ;
        etapes = new HashMap() ;
    }

    public void ajouter(EtapeIG etapeIG, Etape etape){
        /*Ajout des etape et etape ig dans les hashmap
          Création d'un identifiant pour comparer les étapes entre elles
        */
        etape.setIdentifiant(etapeIG.getIdentifiant());
        etapes.put(etapeIG.getIdentifiant(),etape) ;
        etapesIG.put(etapeIG.getIdentifiant(),etapeIG) ;
    }

    public Etape get(EtapeIG e){
        return etapes.get(e.getIdentifiant()) ;
    }
}
