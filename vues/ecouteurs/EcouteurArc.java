package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class EcouteurArc implements EventHandler<MouseEvent> {
    private MondeIG m;
    private ArcIG a;
    public EcouteurArc(MondeIG mondeIG, ArcIG arcIG){
        m = mondeIG;
        a= arcIG;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        this.m.clickArc(a);

    }
}
