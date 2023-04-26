package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.mondeIG.SujetObserve;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;

import java.util.ArrayList;
import java.util.Iterator;

public class Simulation extends SujetObserve {
    private int nbclients ;
    private GestionnaireClients clients;
    private KitC kitC;
    private int cptLibCharg;

    //Creer la simulation du monde
    public Simulation() {
        kitC = new KitC();
        clients = new GestionnaireClients(nbclients);
        this.cptLibCharg = FabriqueNumero.getInstance().getCptLibCharg();

    }

    public GestionnaireClients getClients() {
        return clients;

    }

    //lance le code C
    public void simuler(Monde monde) {
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk"+cptLibCharg+".so");
        mainC(monde);

    }

    //lance la simulation
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    //retourne un tableau de client
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    //vide le tableau de client
    public native void nettoyage();

    //Transporte les clients aux bons endroits
    public void mainC(Monde monde) {
        try {
            int[] tabJetonsGuichet = new int[monde.nbGuichet()];
            Iterator<Etape> guichet= monde.iterator();
            while(guichet.hasNext()){
                Etape e = guichet.next();
                if(e.estUnGuichet()){
                    Guichet g = (Guichet) e;
                    tabJetonsGuichet[g.getNumeroSemaphore()-1] = g.getNbjetons() ;

                }
            }
            int[] tab = start_simulation(monde.nbEtapes(), monde.nbGuichet(), nbclients, tabJetonsGuichet);
            String[] etape = new String[monde.nbEtapes()];
            Etape[] etapes = new Etape[monde.nbEtapes()] ;
            int[] nmEtapes = new int[monde.nbEtapes()];
            Iterator<Etape> ite = monde.iterator();
            Etape e ;
            int x =0;
            int posSortie = 0 ;
            int testSortie = 0 ;

            while (ite.hasNext()){
              e = ite.next();
                nmEtapes[x] = e.getFabrique();
                if(e.estUneSortie() && testSortie == 0){
                    posSortie = x ;
                    etape[x] =e.getNom();
                    etapes[x] = e ;
                    testSortie++ ;
                }
                if(!e.estUneSortie()){
                    etape[x] =e.getNom();
                    etapes[x] = e ;
                }
               ++x;
            }

            int nomEtape = 0;
            int stop = 0;
            int nbClientEtape = 0;
            int[] lesClients = ou_sont_les_clients(monde.nbEtapes(), nbclients);
            while (stop != 1) {
                for (int i = 0; i < etapes.length * (nbclients + 1); i++) {
                    if (i % (nbclients + 1) == 0) {
                        nomEtape++;
                        nbClientEtape = lesClients[i];
                    } else {
                        for (int n = 0; n < nbClientEtape; n++) {
                            this.clients.setClients(lesClients[i + n]);
                            this.clients.allerA(lesClients[i + n],etapes[nomEtape-1], n+1);
                        }
                        nbClientEtape = 0;
                    }
                }

                Thread.sleep(1000);
                nomEtape = 0;
                if (lesClients[(nbclients + 1) * posSortie] == nbclients) {
                    stop = 1;
                }

                lesClients = ou_sont_les_clients(monde.nbEtapes(), nbclients);
                notifierObservateurs();
            }
            FabriqueNumero.getInstance().reset();
            nettoyage();
        } catch (InterruptedException e) {
        }
    }

    public int getCptLibCharg() {
        return cptLibCharg;
    }

    public void setNbClients(int nbClients) {
        this.nbclients= nbClients;
    }
}