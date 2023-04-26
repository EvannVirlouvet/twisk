package twisk.vues.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurStyles implements EventHandler<ActionEvent> {
    private MondeIG m;
    private char c;
    public EcouteurStyles(MondeIG mondeIG,char c){
        m = mondeIG;
        this.c = c;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        m.setStyle(c);

    }
}
