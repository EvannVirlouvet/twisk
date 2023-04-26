package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurEntree implements EventHandler<ActionEvent> {
    private MondeIG m;
    public EcouteurEntree(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        m.aCommeEntree();
    }
}
