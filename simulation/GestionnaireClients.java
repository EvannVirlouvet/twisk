package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>{

    private ArrayList<Client> clients ;

    //Creer le gestionnaire de client
    public GestionnaireClients(int nbClients){
        this.clients = new ArrayList<>(nbClients) ;
    }

    public void setClients(int... tabClients){
        for(int t : tabClients){
            this.clients.add(new Client(t)) ;
        }

    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    //Permet de deplacer un client dans une autre etape
    public void allerA(int numeroCLient , Etape etape, int rang){
        for(int i = 0 ; i < nbclient(); ++i){
            if(this.clients.get(i).numeroClient == numeroCLient){
                this.clients.get(i).allerA(etape, rang);
            }
        }

    }

    //Vide la liste de client
    public void nettoyer(){
        this.clients.clear();
    }

    public int nbclient(){
        return this.clients.size() ;
    }

    @Override
    public String toString() {
        return "GestionnaireClients{" +
                "clients=" + clients +
                '}';
    }

    @Override
    public Iterator<Client> iterator() {
        return clients.iterator();
    }

}
