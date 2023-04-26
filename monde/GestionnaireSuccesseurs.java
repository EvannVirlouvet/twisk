package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>{
    protected ArrayList<Etape> etape ;
    public void clear(){
        etape.clear();
    }

    //Creer un gestionnaire de successeur
    public GestionnaireSuccesseurs() {
        etape = new ArrayList<>() ;
    }

    //Ajoute les etapes dans la liste d'etape
    public void ajouter(Etape... etapes){
        for(Etape e : etapes){
            this.etape.add(e) ;
        }
    }

    //Compte le nombre d'etape dans la liste
    public int nbEtapes(){

        return etape.size();
    }

    //Permet de recupere l'etape d'apres
    public Etape getNext(){
        Iterator<Etape> iterator = etape.iterator() ;
        Etape e = iterator.next() ;
        return  e;
    }
    //Permet de recuperer l'etape voulu
    public Etape getNext(int i){
        return etape.get(i);
    }





    @Override
    public Iterator<Etape> iterator() {
        return this.etape.iterator();
    }

    @Override
    public String toString() {
        Iterator<Etape> iterator = etape.iterator();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<etape.size();i++){
            Etape e = iterator.next();
            sb.append(e.nom);
            if(i < etape.size()-1){
                sb.append("  - ");
            }
        }

        return sb.toString();
    }
}
