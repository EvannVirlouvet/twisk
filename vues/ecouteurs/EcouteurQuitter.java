package twisk.vues.ecouteurs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurQuitter implements EventHandler<ActionEvent> {
    private MondeIG mondeIG;
    public EcouteurQuitter(MondeIG m){
        this.mondeIG = m;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit();
    }
}
