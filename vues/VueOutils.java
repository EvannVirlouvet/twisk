package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.PopupWindow;
import twisk.vues.ecouteurs.EcouteurBouton;
import twisk.mondeIG.MondeIG;
import twisk.vues.ecouteurs.EcouteurBoutonSImulation;

public class VueOutils extends TilePane implements Observateur {
    private Button button;
    private Button button2;

    private Button buttonSimulation ;
    private MondeIG monde;

    private String simultation ;


    public VueOutils(MondeIG monde){
        this.monde = monde;
        this.monde.ajouterObservateur(this);
        simultation = monde.getNomButton() ;
        button = new Button("+Activité");
        button2 = new Button("+Guichet");
        buttonSimulation = new Button(simultation) ;
        style(button,"Ajouter une activité","Activite");
        style(button2,"Ajouter un guichet","Guichet");
        styleSimulation(buttonSimulation);
        changementStylesActivite(button);
        changementStylesGuichet(button2);
        this.getChildren().addAll(button,button2,buttonSimulation);
    }

    //Creer la fonction et l'apparence du bouton
    public void style(Button button,String s,String type){
        button.setMinHeight(70);
        button.setMinWidth(100);
        button.setWrapText(true);
        button.setOnAction(new EcouteurBouton(this.monde,type));
        Tooltip tooltip = new Tooltip(s);
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_LEFT);
        button.setTooltip(tooltip);
    }

    //Creer la fonction et l'apparence du bouton Simulation
    public void styleSimulation(Button button){
        button.setMinHeight(70);
        button.setMinWidth(100);
        button.setWrapText(true);
        button.setOnAction(new EcouteurBoutonSImulation(this.monde,simultation));
        Tooltip tooltip = new Tooltip("Faire la Simulation");
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_LEFT);
        button.setTooltip(tooltip);
    }

    //Change l'apparence des activites
    public void changementStylesActivite(Button button){
        switch (monde.getStyle()){
            case 'd':
                button.setStyle("-fx-background-color: #51a1f5; -fx-font-weight : bold");
                break;
            case 'l':
                button.setStyle("-fx-base: moccasin");
                break;
            case 'b':
                button.setStyle("-fx-base: skyblue");
                break;
            case 'p':
                button.setStyle("-fx-base: MISTYROSE");
                break;
        }

    }

    //Cgange l'apparence des Guichets
    public void changementStylesGuichet(Button button){
        switch (monde.getStyle()){
            case 'd':
                button.setStyle("-fx-background-color: #10ff00; -fx-font-weight : bold");
                break;
            case 'l':
                button.setStyle("-fx-base: moccasin");
                break;
            case 'b':
                button.setStyle("-fx-base: skyblue");
                break;
            case 'p':
                button.setStyle("-fx-base: MISTYROSE");
                break;
        }

    }
    @Override
    public void reagir() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                changementStylesActivite(button);
                changementStylesGuichet(button2);
                buttonSimulation.setText(monde.getNomButton());
                buttonSimulation.setOnAction(new EcouteurBoutonSImulation(monde, monde.getNomButton()));
                if(monde.isStart()) {
                    button.setDisable(true);
                    button2.setDisable(true);
                }else {
                    button.setDisable(false);
                    button2.setDisable(false);
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }
    }
}
