package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurBouton implements EventHandler<ActionEvent> {
    private MondeIG monde;
    private String s;
    public EcouteurBouton(MondeIG monde,String s) {
        this.monde = monde;
        this.s = s;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter(s);
    }
}
