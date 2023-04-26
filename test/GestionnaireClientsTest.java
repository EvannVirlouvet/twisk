package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.GestionnaireClients;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {
    private GestionnaireClients test;
    private Activite activite ;

    @BeforeEach
    void setUp() {
        test = new GestionnaireClients(50) ;
        activite =new Activite("ferme");
    }


    @Test
    void setClients() {
        test.setClients(10,50,30,40);
        int x = 4 ;
        assert(x == test.nbclient()): "Tout les clients ne sont pas dans la collection" ;
    }

    @Test
    void allerA() {
        setClients();
        test.allerA(10,activite,10);
        for(int i = 0 ; i < test.nbclient(); ++i){
            if(this.test.getClients().get(i).getNumeroClient() == 10){
                assert (this.test.getClients().get(i).getE()== activite):"mauvaise activité envoyé";
                assert (this.test.getClients().get(i).getRang() == 10): "mauvais rang envoyé" ;
            }
        }
    }

    @Test
    void nettoyer() {
        setClients();
        test.nettoyer();

        assert (this.test.nbclient() == 0): "Tout les clients ont été nettoyer" ;
    }

}