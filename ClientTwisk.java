package twisk;

import twisk.exceptions.ExceptionSimulation;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {

    public ClientTwisk(){

    }

    public void initialiser(){
        Monde m = new Monde();

        Etape GUICHET_ZOO = new Guichet("GUICHET ZOO",6);
        Etape siecle = new Activite("w",3,1);
        Etape ZOO = new ActiviteRestreinte("ZOO",4,1);

        m.ajouter(GUICHET_ZOO,ZOO,siecle);
        m.aCommeEntree(GUICHET_ZOO);
        m.aCommeSortie(siecle);
        GUICHET_ZOO.ajouterSuccesseur(ZOO);
        ZOO.ajouterSuccesseur(siecle);

        nvMonde(m,5);

        Monde m2 = new Monde();
        Etape g = new Guichet("z",6);
        Etape x = new ActiviteRestreinte("Miaou");
        Etape xw = new Activite("miaouclafin");
        m2.ajouter(g,x,xw);
        m2.aCommeEntree(g);
        m2.aCommeSortie(xw);
        g.ajouterSuccesseur(x);
        x.ajouterSuccesseur(xw);
        nvMonde(m2,3);

    }
    private void nvMonde(Monde m,int nbClients){
        try {
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(this.getClass().getClassLoader());
            Class<?> sim = classLoaderPerso.loadClass(Simulation.class.getName());

            Object o =  sim.getConstructor().newInstance() ;

            Method methodNbclient = sim.getMethod("setNbClients",int.class);
            Method methodSimuler = sim.getMethod("simuler", Monde.class);

            methodNbclient.invoke(o,nbClients);
            methodSimuler.invoke(o,m);

        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e){
            System.out.println(e);
            throw new ExceptionSimulation(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClientTwisk Ct = new ClientTwisk();
        Ct.initialiser();

    }
}
