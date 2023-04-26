package twisk.vues;

import javafx.scene.layout.VBox;
import twisk.vues.ecouteurs.EcouteurDragDetected;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public abstract class VueEtapeIG extends VBox implements Observateur {
    private MondeIG monde;
    private EtapeIG etapeIG;
    public VueEtapeIG(MondeIG mondeIG, EtapeIG etapeIG){
        this.monde = mondeIG;
        this.etapeIG = etapeIG;
        this.setOnDragDetected(new EcouteurDragDetected(mondeIG,this));
    }

    public EtapeIG getEtapeIG() {
        return etapeIG;
    }
}
