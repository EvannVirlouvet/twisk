package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;
import twisk.simulation.Client;

import java.util.Random;

public class VueDessin extends Circle implements Observateur {
    private MondeIG mondeIG;
    private Client c;
    private EtapeIG e;

    public VueDessin(MondeIG m, Client c, EtapeIG e){
        this.mondeIG = m;
        this.e = e;
        this.c = c;
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        Color randomColor = Color.rgb(r,g,b);
        this.setStroke(randomColor);

        this.setStrokeWidth(2);

        if(c.getE().getIdentifiant().equals(e.getIdentifiant())){
            this.setRadius(4);
            if(e.getSensDeCirculation() == "Gauche")
                this.relocate(e.getPosX() + c.getRang() * 10, e.getPosY() - TailleComposants.getInstance().gethVBox() + TailleComposants.getInstance().gethHBox());
            else
                this.relocate(e.getPosX() + TailleComposants.getInstance().getwVBox() - c.getRang() * 30, e.getPosY() - TailleComposants.getInstance().gethVBox() + TailleComposants.getInstance().gethHBox());

        }
        if(c.getE().getNom().equals(e.getDebutnom())) {
            this.setRadius(4);
            if(e.getSensDeCirculation() == "Gauche") {
                if(e.getPosX() + c.getRang() *10 > e.getPosX() + TailleComposants.getInstance().getwVBoxGuichet()){
                    this.relocate(e.getPosX() + c.getRang() * 10, e.getPosY() - TailleComposants.getInstance().gethVBoxGuichet() + TailleComposants.getInstance().gethHBoxGuichet() - 20);
                }else {
                    this.relocate(e.getPosX() + (c.getRang()%10) * 10, e.getPosY() - TailleComposants.getInstance().gethVBoxGuichet() + TailleComposants.getInstance().gethHBoxGuichet());
                }
            } else{
                    this.relocate(e.getPosX() + TailleComposants.getInstance().getwVBoxGuichet() - c.getRang() * 30, e.getPosY() - TailleComposants.getInstance().gethVBoxGuichet() + TailleComposants.getInstance().gethHBoxGuichet());
            }
        }

    }
    @Override
    public void reagir() {

    }
}
