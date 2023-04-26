package twisk.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireSuccesseurs;
import twisk.monde.Guichet;

import java.util.Iterator;

class GestionnaireSuccesseursTest {
    private GestionnaireSuccesseurs test ;
    @BeforeEach
    void setUp() {
        test = new GestionnaireSuccesseurs() ;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ajouter() {
        test.ajouter(new Guichet("guichet 1"), new Activite("activité 1"));
        int x = test.nbEtapes() ;
        assert(x == 2) : "bug si x n'est pas egale au bon nombre d'etape" ;
    }

    @Test
    void nbEtapes() {
    }

    @Test
    Iterator<Etape> iterator() {
        Iterator<Etape> iter = this.test.iterator() ;

        return iter;
    }
}