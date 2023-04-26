package twisk.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

class EtapeTest {
    private Etape test ;
    private Etape etape1;
    private Etape etape2;
    @BeforeEach
    void setUp() {
        test = new Activite("activite1") ;
        etape1 = new Activite("Liste activités",2,3);
        etape2 = new Guichet("guichet1");
        etape2.ajouterSuccesseur(etape1,test);


    }

    @Test
    void nbEtapes() {
        assert(test.getFabrique() == 0):"Bug getNumeroEtape de la Fabrique";
        assert(etape1.getFabrique() == 1):"Bug getNumeroEtape de la Fabrique";
        assert(etape2.getFabrique() == 2):"Bug getNumeroEtape de la Fabrique";

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void nbSuccesseur() {
        assert(etape2.nbSuccesseurs() == 2):"Bug ajouterSuccesseur";
    }


    @Test
    void iterator() {


    }
}