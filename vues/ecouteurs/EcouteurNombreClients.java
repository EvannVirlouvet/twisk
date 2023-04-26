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

public class EcouteurNombreClients implements EventHandler<ActionEvent> {
    private MondeIG m;

    public EcouteurNombreClients(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Initialisation nombre de clients");
            dialog.setHeaderText("Initialiser le nombre de clients du monde :");
            dialog.setContentText("Nombre Jetons :");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                this.m.setNbClients(result.get());
            });
        }catch (ParametresExcpetion e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'initialiser le nombre de clients !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }
}
