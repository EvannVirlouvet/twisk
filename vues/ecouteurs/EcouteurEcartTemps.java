package twisk.vues.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.ParametresExcpetion;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurEcartTemps implements EventHandler<ActionEvent> {
    private MondeIG m;
    public EcouteurEcartTemps(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Initialisation écart temps");
            dialog.setHeaderText("Initialiser l'écart temps de cette activité :");
            dialog.setContentText("Ecart temps :");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                this.m.ecartTemps(result.get());
            });
        }catch (ParametresExcpetion e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'initialiser l'écart temps !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }
}
