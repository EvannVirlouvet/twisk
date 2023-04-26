package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurEnregistrerSous implements EventHandler<ActionEvent> {
    private MondeIG m;

    public EcouteurEnregistrerSous(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
