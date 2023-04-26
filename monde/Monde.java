package twisk.monde;

import java.text.Normalizer;

import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

public class Monde implements Iterable<Etape>{
    private GestionnaireEtapes gestionnaireEtapes ;
    private SasEntree sasEntree ;
    private SasSortie sasSortie ;

    //Efface tout les composants du monde
    public void MondeClear(){
        gestionnaireEtapes.clear();
        sasSortie.clear();
        sasEntree.clear();
    }

    //Creer un monde
    public Monde(){
        this.gestionnaireEtapes = new GestionnaireEtapes();
        this.sasEntree = new SasEntree("ENTREE");
        this.sasSortie = new SasSortie("SORTIE");
    }

    //Defini l'entree du monde
    public void aCommeEntree(Etape... etapes){
        for(Etape e : etapes){
            sasEntree.ajouterSuccesseur(e) ;
        }
        gestionnaireEtapes.ajouterEmplacement(0,sasEntree);
    }

    //Defini la sortie du monde
    public void aCommeSortie(Etape... etapes){
        gestionnaireEtapes.ajouterEmplacement(1,sasSortie);
        for(Etape e : etapes){
            e.ajouterSuccesseur(sasSortie);
        }
    }

    //Ajoute des Etapes dans le gestionnaire d'etape
    public void ajouter(Etape... etapes){
        for(Etape e : etapes){
            gestionnaireEtapes.ajouter(e);
        }

    }

    //Compte le nombre d'etape du gestionnaire d'etape
    public int nbEtapes(){

        return gestionnaireEtapes.nbEtapes();
    }

    //Compte le nombre de guichet dans le gestionnaire d'etape
    public int nbGuichet(){

        return gestionnaireEtapes.nbGuichet();
    }

    @Override
    public Iterator<Etape> iterator() {
        return  this.gestionnaireEtapes.iterator();
    }

    public SasEntree getSasEntree() {
        return sasEntree;
    }

    public SasSortie getSasSortie() {
        return sasSortie;
    }

    @Override
    public String toString() {
        return gestionnaireEtapes.toString();
    }

    public GestionnaireEtapes getGestionnaireEtapes() {
        return gestionnaireEtapes;
    }

    //Creer le code C
    public String toC(){
        StringBuilder sb = new StringBuilder() ;
        sb.append(enteteC());
        sb.append("entrer("+sasEntree.getNom()+") ;\n") ;
        sb.append("delai(3,1) ;\n") ;
        if(sasEntree.nbSuccesseurs() >1){
            sb.append("srand( time( NULL ) );\n");
            sb.append("nb = (int) ((rand() / (float) RAND_MAX) * " + sasEntree.nbSuccesseurs() + ");\n");
            sb.append("switch (nb) {\n");
            for (int i = 0; i < sasEntree.nbSuccesseurs(); i++) {
                sb.append(" case " + i + ": \n");
                sb.append("     transfert(" + this.sasEntree.nom + "," + sasEntree.gestionnaireSuccesseurs.getNext(i).getNom() + ") ;\n");

                sb.append(sasEntree.gestionnaireSuccesseurs.getNext(i).toC()) ;
                sb.append("     break;\n");
            }
            sb.append("}\n");
        }else if (sasEntree.nbSuccesseurs() == 1){
            sb.append("transfert(" + this.sasEntree.nom + "," + sasEntree.gestionnaireSuccesseurs.getNext().getNom() + ") ;\n");
            sb.append(sasEntree.gestionnaireSuccesseurs.getNext().toC()) ;
        }


        sb.append("\n}");
        return sb.toString();
    }

    //Creer les includes du code C
    public String enteteC(){
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <time.h>\n");
        sb.append("#include \"def.h\" \n");
        sb.append(define());
        sb.append("void simulation(int ids){ \n");
        Iterator<Etape> iterator = gestionnaireEtapes.iterator() ;
        while (iterator.hasNext()){
            if(iterator.next().nbSuccesseurs() > 1){
                sb.append("int nb = 0 ;\n");
                break;
            }
        }
        return sb.toString();
    }

    //Creer les define du code C
    public String define(){
        StringBuilder sb = new StringBuilder();
        int x = 0;
        Etape[] tab = new Etape[100] ;
        int testEntree = 0;
        int testSortie = 0 ;
        tab[0] = sasEntree ;
        Etape e ;
        Iterator<Etape> iterator = this.iterator() ;

        while (iterator.hasNext()){
            e = iterator.next() ;
            char[] chars = sansEspace(e);
            String nomActivité = String.valueOf(chars);
            if(Character.isDigit(chars[0])){
                nomActivité ="_"+nomActivité;
            }

            e.setNom(sansAccent(nomActivité));
            if (!e.estUneEntree()){
                if (!e.estUneSortie()){
                    e.setNom(e.getNom()+"_");
                }
            }
            if(!e.estUneEntree()){
                if (!e.estUneSortie()) {
                    doublementNom(e, tab, x);
                }
            }

            if(e.estUneEntree() && testEntree == 0){
                sb.append("#define "+e.getNom()+" " +x+"\n");
                testEntree++ ;
            }
            if(e.estUneSortie() && testSortie== 0){
                sb.append("#define "+e.getNom()+" " +x+"\n");
                testSortie++ ;
            }

            if(!e.estUneEntree()){
                if(!e.estUneSortie()){
                    sb.append("#define "+e.getNom()+" " +x+"\n");
                }
            }
            if(e.estUnGuichet()){
                Guichet g =(Guichet) e;
                sb.append("#define num_sem_guichet_"+g.getNumeroSemaphore()+" "+g.getNumeroSemaphore()+"\n");
            }
            tab[x] = e;
            ++x;
        }

        return sb.toString();
    }

    //enleve les accent du string choisi
    public static String sansAccent(String s)
    {

        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    //enleve les espaces du string choisi
    public char[] sansEspace(Etape e){
        char[] chars = e.getNom().toCharArray();
        char[] pasGentil = {' ','.','!',':',',',';','?','&','"','(','[','|','/','^','@',')',']','=','+','}','{','°','*','$','£','§','µ','<','>','œ','~','#','\'','\\','-'} ;
        for(int i= 0;i< chars.length;++i){
            for (int j = 0; j <pasGentil.length; ++j){
                if(chars[i] == pasGentil[j]) {
                    chars[i] = '_';
                }
            }
        }
        return chars;
    }

    //Permet de modifier le string, si il existe deja un string du meme nom dans la liste
    public void doublementNom(Etape e,Etape[] tab,int x){
        for(int i=0;i<x;++i){
            if(tab[i].getNom().equals(e.getNom()) ){
                if(e.getNbDoublons()==2) {
                    e.setNom(e.getNom() + "_" + tab[i].getNbDoublons());
                }

                tab[i].setNbDoublons(tab[i].getNbDoublons()+1);
                e.setNbDoublons(e.getNbDoublons()+1);
            }
        }
    }
}
