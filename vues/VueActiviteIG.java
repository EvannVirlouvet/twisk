package twisk.vues;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import twisk.vues.ecouteurs.EcouteurActivite;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public class VueActiviteIG extends VueEtapeIG implements Observateur{
    private Label label;
    private HBox hBox;
    private MondeIG m;

    public VueActiviteIG(MondeIG mondeIG, EtapeIG etapeIG) {
        super(mondeIG, etapeIG);
        m = mondeIG;
        hBox= new HBox();
        this.label = new Label(etapeIG.getNom());
        this.setAlignment(Pos.CENTER);
        this.label.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.label.setTextAlignment(TextAlignment.CENTER);

        this.relocate(etapeIG.getPosX(),etapeIG.getPosY());
        this.setPrefHeight(TailleComposants.getInstance().gethVBox());
        this.setPrefWidth(TailleComposants.getInstance().getwVBox());


        hBox.setPrefHeight(TailleComposants.getInstance().gethHBox());
        hBox.setPrefWidth(TailleComposants.getInstance().getwHBox());


        this.setOnMouseClicked(new EcouteurActivite(mondeIG, etapeIG));

        effetsSurLactivite(etapeIG);
        changementStyles();
        if(mondeIG.isStart())
            this.setDisable(true);
        this.getChildren().addAll(label,hBox);


    }
    public void effetsSurLactivite(EtapeIG etapeIG){
        Glow glow = new Glow();
        glow.setLevel(0.5);

        Image image = new Image(getClass().getResourceAsStream("/images/E.png"),20, 20,true,true);
        ImageView icon = new ImageView(image);
        Image image2 = new Image(getClass().getResourceAsStream("/images/S.png"),20, 20,true,true);
        ImageView icon2 = new ImageView(image2);
        Image image3 = new Image(getClass().getResourceAsStream("/images/ES.png"),20, 20,false,true);
        ImageView icon3 = new ImageView(image3);
        if(m.getActiviteSelec().contains(etapeIG)) this.setEffect(glow);
        if(m.getEntrees().contains(etapeIG)) label.setGraphic(icon);
        if(m.getSorties().contains(etapeIG)) label.setGraphic(icon2);
        if(m.getSorties().contains(etapeIG) && m.getEntrees().contains(etapeIG)) label.setGraphic(icon3);
    }

    public void changementStyles(){
        switch (m.getStyle()){
            case 'd':
                this.setStyle("-fx-background-color: ANTIQUEWHITE;-fx-border-color: #0026ff; -fx-border-width: 3px; -fx-border-radius: 10px;");
                hBox.setStyle("-fx-border-color: #0026ff; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-border-width: 3px; -fx-border-radius: 10px;");
                hBox.setStyle("-fx-background-color: LIGHTGRAY ;");
                break;
            case 'l':
                this.setStyle("-fx-background-color: lightsalmon;-fx-border-color: brown; -fx-border-width: 3px;");
                this.label.setStyle("-fx-font-size: 20;-fx-text-fill: ANTIQUEWHITE;-fx-font-weight: bold;");
                hBox.setStyle("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
                hBox.setStyle("-fx-background-color: moccasin");
                break;
            case 'b':
                this.setStyle("-fx-background-color: steelblue;-fx-border-color: brown; -fx-border-width: 3px;");
                this.label.setStyle("-fx-font-size: 20;-fx-text-fill: ANTIQUEWHITE;-fx-font-weight: bold;");
                hBox.setStyle("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
                hBox.setStyle("-fx-background-color: skyblue");
                break;
            case 'p':
                this.setStyle("-fx-background-color: lightpink;-fx-border-color: brown; -fx-border-width: 3px;");
                this.label.setStyle("-fx-font-size: 20;-fx-font-weight: bold;");
                hBox.setStyle("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
                hBox.setStyle("-fx-background-color: mistyrose");
                break;
        }

    }
    @Override
    public void reagir() {

    }
}
