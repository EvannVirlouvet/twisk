package twisk.vues.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.exceptions.MondeIncorrectException;
import twisk.exceptions.ParametresExcpetion;
import twisk.mondeIG.MondeIG;

public class EcouteurSortie implements EventHandler<ActionEvent> {
    private MondeIG m;
    public EcouteurSortie(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            m.aCommeSortie();
        }catch (ParametresExcpetion e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Guichet != Sortie !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished(event -> alert.close());
            pauseTransition.play();
        }

    }
}
