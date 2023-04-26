package twisk.vues;

import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.vues.ecouteurs.EcouteurArc;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class VueArcIG extends Pane implements Observateur {
    private MondeIG monde;
    private ArcIG arc;
    public VueArcIG(MondeIG mondeIG, ArcIG arcIG){
        monde = mondeIG;
        arc = arcIG;
        Line ligne = new Line();
        Polyline triangle = new Polyline();

        this.setOnMouseClicked(new EcouteurArc(mondeIG, arc));
        ligne.setStartX(arcIG.getPdc1().getX());
        ligne.setStartY(arcIG.getPdc1().getY());
        ligne.setEndX(arcIG.getPdc2().getX());
        ligne.setEndY(arcIG.getPdc2().getY());
        triangle.getPoints().addAll(ligne.getEndX()-30,ligne.getEndY()-30,ligne.getEndX()-30,ligne.getEndY()+30,ligne.getEndX()+30,ligne.getEndY()-30,ligne.getEndX()+30,ligne.getEndY()+30,ligne.getEndX()-30,ligne.getEndY()-30);
        ligne.setStyle("-fx-stroke: red;");
        ligne.setStrokeWidth(5);
        triangle.setStrokeWidth(5);
        triangle.setStyle("-fx-stroke: red;");


        ligne.setStyle("-fx-background-color: blue");
        this.setOnMouseClicked(new EcouteurArc(mondeIG, arc));
        this.setPickOnBounds(false);
        Glow glow = new Glow();
        glow.setLevel(1);

        if(mondeIG.getArcsSelec().contains(arc)) this.setEffect(glow);
        this.getChildren().addAll(ligne,triangle);
    }
    @Override
    public void reagir() {

    }
}
