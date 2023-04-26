package twisk.vues.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.MondeIG;
import javafx.scene.input.TransferMode;
import twisk.vues.VueEtapeIG;

public class EcouteurDragDetected implements EventHandler<MouseEvent> {
    private MondeIG m;
    private VueEtapeIG vue;
    ActiviteIG a;
    public EcouteurDragDetected(MondeIG mondeIG, VueEtapeIG vueEtapeIG){
        m = mondeIG;
        vue = vueEtapeIG  ;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard dragboard = vue.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        vue.setId(vue.getEtapeIG().getIdentifiant());
        content.putString(vue.getId());
        dragboard.setContent(content);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        Image image = this.vue.snapshot(snapshotParameters,null);
        dragboard.setDragView(image);

    }
}
