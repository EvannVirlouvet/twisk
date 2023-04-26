package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.MondeIG;

public class EcouteurDragOver implements EventHandler<DragEvent> {
    private MondeIG m;
    public EcouteurDragOver(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.MOVE);
        dragEvent.consume();
    }
}
