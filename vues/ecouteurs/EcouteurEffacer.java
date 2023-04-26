package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurEffacer implements EventHandler<ActionEvent> {
    private MondeIG mondeIG;
    public EcouteurEffacer(MondeIG m){
        this.mondeIG = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        mondeIG.effacer();
    }
}
