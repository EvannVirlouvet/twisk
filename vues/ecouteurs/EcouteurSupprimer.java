package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private MondeIG monde;
    public EcouteurSupprimer(MondeIG mondeIG){
        this.monde = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.supprimer();
    }
}
