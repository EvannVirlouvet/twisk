package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import twisk.simulation.Client;

import twisk.vues.ecouteurs.EcouteurDragDropped;
import twisk.vues.ecouteurs.EcouteurDragOver;
import twisk.mondeIG.*;

import java.util.Iterator;

public class VueMondeIG extends Pane implements Observateur {
    private MondeIG mondeIG;


    public VueMondeIG(MondeIG monde){
        this.mondeIG = monde;
        this.mondeIG.ajouterObservateur(this);
        ajouterVueActiviteEtPdc();
        this.setOnDragOver(new EcouteurDragOver(mondeIG));
        this.setOnDragDropped(new EcouteurDragDropped(mondeIG));
    }
    @Override
    public void reagir() {
        Pane panneau = this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                panneau.getChildren().clear();
                ajouterVueArcIG();
                ajouterVueActiviteEtPdc();
                if(mondeIG.isStart()){
                    mondeIG.getGestionnaire();
                    ajouterVueDesssin();
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }

    }
    public void ajouterVueDesssin(){
        for(Client c : mondeIG.getGestionnaireClients()) {
            for (EtapeIG etapeIG : mondeIG) {
                VueDessin vueDessin = new VueDessin(this.mondeIG, c, etapeIG);
                this.getChildren().add(vueDessin);
            }

        }

    }
    public void ajouterVueArcIG(){
        Iterator<ArcIG> arcIGIterator = mondeIG.arcIGIterator();
        while (arcIGIterator.hasNext()){
            ArcIG arc = arcIGIterator.next();
            VueArcIG vueArcIG = new VueArcIG(this.mondeIG,arc);
            this.getChildren().add(vueArcIG);
        }
    }
    public void ajouterVueActiviteEtPdc(){
        for(EtapeIG etapeIG : mondeIG){
            if(etapeIG.estUneActivite()) {
                VueActiviteIG vueActiviteIG = new VueActiviteIG(this.mondeIG, etapeIG);
                this.getChildren().add(vueActiviteIG);
                for (PointDeControleIG pointDeControleIG : etapeIG) {
                    VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(mondeIG, pointDeControleIG);
                    this.getChildren().add(vuePointDeControleIG);
                }
            }
            if(etapeIG.estUnGuichet()) {
                VueGuichetIG vueGuichet = new VueGuichetIG(this.mondeIG, etapeIG);
                this.getChildren().add(vueGuichet);
                for (PointDeControleIG pointDeControleIG : etapeIG) {
                    VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(mondeIG, pointDeControleIG);
                    this.getChildren().add(vuePointDeControleIG);
                }
            }

        }

    }
}
