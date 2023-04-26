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

public class EcouteurDelai implements EventHandler<ActionEvent> {
    private MondeIG m;
    public EcouteurDelai(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Initialisation délai");
            dialog.setHeaderText("Initialiser le délai de cette activité :");
            dialog.setContentText("Délai :");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                this.m.delai(result.get());
            });
        }catch (ParametresExcpetion e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'initialiser le délai !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }
}
