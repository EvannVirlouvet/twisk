package twisk.vues.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.exceptions.MondeIncorrectException;
import twisk.mondeIG.MondeIG;

public class EcouteurBoutonSImulation implements EventHandler<ActionEvent> {
    private MondeIG monde;

    private String s ;

    public EcouteurBoutonSImulation(MondeIG monde, String s){

        this.monde = monde;
        this.s = s ;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if (s.equals("Start")){
                monde.simuler();
            }else {
                monde.stop() ;
            }
        }catch (MondeIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Monde Arreter !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }
}
