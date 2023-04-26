package twisk.vues.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.MenuException;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurRenommer implements EventHandler<ActionEvent> {
    private MondeIG m;
    public EcouteurRenommer(MondeIG mondeIG){
        m = mondeIG;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Renommer l'activité selectionner");
            dialog.setHeaderText("Entrez votre nom :");
            dialog.setContentText("Nom:");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                this.m.renommer(result.get());
            });
        }catch(MenuException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Renommage impossible !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();

        }
    }
}
