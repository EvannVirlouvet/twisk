package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;


public class EcouteurDragDropped implements EventHandler<DragEvent> {
    private MondeIG m;
    public EcouteurDragDropped(MondeIG mondeIG){
        m = mondeIG;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        int x = (int)dragEvent.getX();
        int y = (int)dragEvent.getY();
        Dragboard db = dragEvent.getDragboard();
        String identifiant = db.getString();
        m.dragActivite(identifiant,x,y);
        dragEvent.consume();
    }
}
