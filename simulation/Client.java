package twisk.simulation;

import twisk.monde.Etape;

public class Client {
    protected int numeroClient;
    protected int rang;
    protected Etape e;

    public Client(int numero) {
        this.numeroClient = numero;
    }

    //Permet de deplacer le client d'une etape a une autre
    public void allerA(Etape etape, int rang){
        this.e = etape;
        this.rang = rang;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public int getRang() {
        return rang;
    }

    public Etape getE() {
        return e;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroClient=" + numeroClient +
                ", rang=" + rang +
                ", e=" + e +
                '}';
    }
}