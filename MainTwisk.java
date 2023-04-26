package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;


public class MainTwisk extends Application {
    public void start(Stage primaryStage){
        MondeIG mondeIG = new MondeIG();
        BorderPane root = new BorderPane() ;

        root.setBottom(new VueOutils(mondeIG)) ;

        VueMondeIG vueMondeIG = new VueMondeIG(mondeIG);
        root.setCenter(vueMondeIG);
        VueMenu vueMenu= new VueMenu(mondeIG);
        root.setTop(vueMenu);
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);

    }
}
