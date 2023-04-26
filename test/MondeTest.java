package twisk.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

class MondeTest {
    Monde test ;
    Monde test2 ;
    @BeforeEach
    void setUp() {
        test = new Monde();
        test2 = new Monde();
        Etape file1 = new Guichet("File 1",6);
        Etape file2 = new Guichet("File 2",10);

        Etape toilettes = new ActiviteRestreinte("Toilettes",10,4);
        Etape zoo = new ActiviteRestreinte("Zoo",5,2);
        Etape lol = new Activite("Lol", 6,2);

        test.ajouter(file1,toilettes,file2,zoo,lol);
        file1.ajouterSuccesseur(toilettes);
        toilettes.ajouterSuccesseur(file2);
        file2.ajouterSuccesseur(zoo);
        zoo.ajouterSuccesseur(lol);
        test.aCommeEntree(file1);
        test.aCommeSortie(lol);


        test2.ajouter(toilettes);
        test2.aCommeSortie(toilettes);
        test2.aCommeEntree(toilettes);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void iterator() {
    }

    @Test
    void aCommeEntree() {
    }

    @Test
    void aCommeSortie() {
    }

    @Test
    void ajouter() {
    }

    @Test
    void nbEtapes() {
    }

    @Test
    void nbGuichet() {
        System.out.println(test.getSasSortie());
        System.out.println(test.getSasEntree().getGestionnaireSuccesseurs().getNext().getNom());
        System.out.println(test.getSasSortie().getGestionnaireSuccesseurs().getNext().getNom());

    }
    @Test
    void testToC(){
        System.out.println(test.toC());
        //System.out.println(test2.toC());
    }
}