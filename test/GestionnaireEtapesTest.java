package twisk.test;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;
import twisk.monde.Guichet;

import java.util.Iterator;

class GestionnaireEtapesTest {
    private GestionnaireEtapes test ;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        test = new GestionnaireEtapes() ;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void ajouter() {
        test.ajouter(new Guichet("guichet 1"), new Activite("activité 1"));
        int x = test.nbEtapes() ;
        assert(x == 2) : "bug si x n'est pas egale au bon nombre d'etape" ;
    }

    @org.junit.jupiter.api.Test
    void nbEtapes() {

    }

    @org.junit.jupiter.api.Test
    Iterator<Etape> iterator() {
        Iterator<Etape> iter = this.test.iterator() ;

        return iter;
    }
}