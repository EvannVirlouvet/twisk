package twisk.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

class ActiviteTest extends EtapeTest {
    private Activite activite;
    private Etape test ;
    private Etape etape1;
    @BeforeEach
    void setUp() {
        activite = new Activite("act1",6,2);
        test = new Activite("activite1") ;
        etape1 = new Activite("Liste activités",2,3);

        activite.ajouterSuccesseur(test,etape1);



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void estUneActivite() {

    }

    @Test
    void testEstUneActivite() {
        assert(activite.estUneActivite() == true):"Bug est une activité";
    }

    @Test
    void testToC(){
        System.out.println(activite.toC());
    }
}