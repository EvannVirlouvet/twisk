package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurActivite implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private EtapeIG activite;

    public EcouteurActivite(MondeIG monde, EtapeIG activite) {
        this.monde = monde;
        this.activite = activite;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.monde.clickActivite(activite);
    }
}
