package twisk.monde;

public class ActiviteRestreinte extends Activite{
    //Creer une activité avec un temps et un ecart predefini
    public ActiviteRestreinte(String nom) {
        super(nom);
        this.temps = 4;
        this.ecartTemps =2;
    }

    //Creer une activité ou l'on choisi le temps et l'ecart temps
    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    //Permet d'afficher le code C
    public String toC() {
        StringBuilder sb = new StringBuilder();
        sb.append("delai(" + temps + "," + ecartTemps + ") ;\n");

        return sb.toString();
    }

}
