package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

public class SujetObserve {
    private ArrayList<Observateur> observateurs;
    public SujetObserve() {
        this.observateurs = new ArrayList<>();
    }
    public void ajouterObservateur(Observateur v){
        this.observateurs.add(v);
    }
    public void notifierObservateurs(){
        for (Observateur o : this.observateurs) o.reagir();

    }

    public ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }
}
