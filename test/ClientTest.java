package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client;
    Etape e ;
    @BeforeEach
    void setUp() {
        client = new Client(0);
        e= new Activite("act1",6,2);
    }

    @Test
    void allerA() {
        client.allerA(e,0);
        assert(client.getE() ==e):"Bug Aller A pour le changement d'étape Client.java";
        assert(client.getRang() == 0):"Bug AllerA pour le changement de rang Client.java";

    }
}