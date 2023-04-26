package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import twisk.vues.ecouteurs.*;
import twisk.mondeIG.MondeIG;

public class VueMenu extends MenuBar implements Observateur {
    private MondeIG m;
    public VueMenu(MondeIG mondeIG){
        m = mondeIG;
        m.ajouterObservateur(this);
        Menu monde = menuMonde();
        Menu fichier = menuFichier();
        Menu edition = menuEdition();
        Menu param = menuParametre();
        Menu style = menuStyle();

        this.setStyle("-fx-background-color: lightgray;");
        this.getMenus().addAll(fichier,edition,monde,param,style);
    }
    public Menu menuMonde(){
        Menu monde = new Menu("Monde");
        MenuItem entree = new MenuItem("Entrée");
        MenuItem sortie = new MenuItem("Sortie");
        entree.setOnAction(new EcouteurEntree(m));
        sortie.setOnAction(new EcouteurSortie(m));
        monde.getItems().addAll(entree,sortie);
        return monde;
    }
    public Menu menuFichier(){
        Menu fichier = new Menu("Fichier");
        MenuItem quitter = new MenuItem("Quitter");
        MenuItem enregistrer = new MenuItem("Enregistrer Sous") ;
        MenuItem ouvrir = new MenuItem("Ouvrir") ;
        ouvrir.setOnAction(new EcouteurOuvrir(m));
        enregistrer.setOnAction(new EcouteurEnregistrerSous(m));
        quitter.setOnAction(new EcouteurQuitter(m));
        fichier.getItems().addAll(enregistrer,ouvrir,quitter);
        return fichier;
    }
    public Menu menuParametre(){
        Menu parametres = new Menu("Paramètres");
        MenuItem delai = new MenuItem("Délai");
        MenuItem ecartTemps = new MenuItem("Ecart temps");
        MenuItem nbJetons = new MenuItem("Nombre Jetons");
        MenuItem nbClients = new MenuItem("Nombre Clients");
        ecartTemps.setOnAction(new EcouteurEcartTemps(m));
        delai.setOnAction(new EcouteurDelai(m));
        nbClients.setOnAction(new EcouteurNombreClients(m));
        nbJetons.setOnAction(new EcouteurJetons(m));
        parametres.getItems().addAll(delai,ecartTemps,nbJetons,nbClients);
        return parametres;
    }
    public Menu menuEdition(){
        Menu edition = new Menu("Edition");
        MenuItem renommer = new MenuItem("Renommer");
        MenuItem effacer = new MenuItem("Effacer la sélection");
        MenuItem supprimer = new MenuItem("Supprimer");
        effacer.setOnAction(new EcouteurEffacer(m));
        renommer.setOnAction(new EcouteurRenommer(m));
        supprimer.setOnAction(new EcouteurSupprimer(m));
        edition.getItems().addAll(supprimer,renommer,effacer);
        return edition;
    }
    public Menu menuStyle(){
        Menu style = new Menu("Style");
        MenuItem defaut = new MenuItem("Default");
        MenuItem lightSalmon = new MenuItem("LightSalmon");
        MenuItem blue = new MenuItem("Blue");
        MenuItem pink = new MenuItem("Pink");
        defaut.setOnAction(new EcouteurStyles(m,'d'));
        lightSalmon.setOnAction(new EcouteurStyles(m,'l'));
        blue.setOnAction(new EcouteurStyles(m,'b'));
        pink.setOnAction(new EcouteurStyles(m,'p'));
        style.getItems().addAll(defaut,lightSalmon,blue,pink);

        return style;
    }



    @Override
    public void reagir() {
        MenuBar menu = this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                if(m.isStart()) {
                    menu.setDisable(true);
                }else {
                    menu.setDisable(false);
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }

    }
}
