package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape>{
    private ArrayList<Etape> etape ;

    //Creer un gestionnaire d'etapes
    public GestionnaireEtapes() {
        this.etape = new ArrayList<>() ;
    }
    public void clear(){
        etape.clear();
    }

    //ajoute les etapes dans une liste d'etape
    public void ajouter(Etape... etapes){
        for(Etape e : etapes){
            this.etape.add(e) ;
        }
    }

    //Compte le nombre d'etape
    public int nbEtapes(){

        return etape.size();
    }

    //Compte le nombre de guichet dans la liste d'etape
    public int nbGuichet(){
        int count =0;
        Etape e;
        Iterator<Etape> iterator = etape.iterator();
        while (iterator.hasNext()){
            e = iterator.next();
            if(e.estUnGuichet()){
                count++;
            }
        }
        return count;
    }

    @Override
    public Iterator<Etape> iterator() {
        return this.etape.iterator();
    }

    public ArrayList<Etape> getEtape() {
        return etape;
    }

    @Override
    public String toString() {
        Iterator<Etape> iterator = etape.iterator();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<etape.size();i++){
            sb.append(iterator.next());
            sb.append("\n");
        }

        return sb.toString();
    }

    //Permet d'ajouter les client a l'emplacement voulu dans la liste
    public void ajouterEmplacement(int i,Etape e){
        etape.add(i,e);
    }

}
