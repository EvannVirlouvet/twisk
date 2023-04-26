package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurOuvrir implements EventHandler<ActionEvent> {
    private MondeIG m;

    public EcouteurOuvrir(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
