package twisk.vues;

import javafx.scene.shape.Circle;
import twisk.vues.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class VuePointDeControleIG extends Circle implements Observateur {
    private MondeIG monde;
    private PointDeControleIG pointDeControleIG;

    public VuePointDeControleIG(MondeIG mondeIG,PointDeControleIG pdc){
        monde = mondeIG;
        this.pointDeControleIG = pdc;
        this.relocate(pointDeControleIG.getX(),pointDeControleIG.getY());
        this.setStyle("-fx-stroke: brown; -fx-fill: white;");
        this.setRadius(7);
        this.setOnMouseClicked(new EcouteurPointDeControle(monde,pointDeControleIG));


    }

    @Override
    public void reagir() {

    }
}
