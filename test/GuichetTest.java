package twisk.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Guichet;

class GuichetTest extends EtapeTest {
    private Guichet guichet;
    private Guichet gui ;
    @BeforeEach
    void setUp() {
        guichet = new Guichet("guichet",4);
         gui = new Guichet("guichet2",4) ;
         Activite activité = new Activite("coca",6,2) ;
         Activite activite2 = new Activite("balançoire", 8, 4) ;
         activité.ajouterSuccesseur(gui);
         guichet.ajouterSuccesseur(activité);
         gui.ajouterSuccesseur(activite2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void estUnGuichet() {
        assert(guichet.estUneActivite() == true):"Bug est une activité";

    }

    @Test
    void CptSemaphore() {
        assert(guichet.getNumeroSemaphore() == 1):"Bug getNumeroSemaphore de la Fabrique";
        assert(gui.getNumeroSemaphore() == 2):"Bug getNumeroSemaphore de la Fabrique";
    }

    @Test
    void TestToC(){
        System.out.println(guichet.toC());
    }
}