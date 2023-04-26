package twisk.vues.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.exceptions.ArcIncorrectException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class EcouteurPointDeControle implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private PointDeControleIG pointDeControleIG;

    public EcouteurPointDeControle(MondeIG monde,PointDeControleIG pdc) {
        this.monde = monde;
        this.pointDeControleIG = pdc;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            this.monde.clickPointDeControle(pointDeControleIG);
        }catch(ArcIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Arc incorrect !");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();


        }

    }
}
