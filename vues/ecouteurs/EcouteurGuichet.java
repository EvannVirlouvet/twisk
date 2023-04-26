package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurGuichet implements EventHandler<MouseEvent> {
    private MondeIG m;
    private EtapeIG e;
    public EcouteurGuichet(MondeIG m, EtapeIG etapeIG){
        this.m = m;
        this.e = etapeIG;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        this.m.clickActivite(e);
    }
}
