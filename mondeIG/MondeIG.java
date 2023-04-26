package twisk.mondeIG;

import javafx.application.Platform;
import javafx.concurrent.Task;
import twisk.exceptions.*;
import twisk.monde.*;
import twisk.outils.*;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur  {

    private HashMap<String,EtapeIG> etapeIGS;

    private Monde monde ;
    private CorrespondanceEtapes correspondanceEtapes ;
    protected GestionnaireClients gestionnaireClients;
    protected char style;
    protected Method method;
    protected boolean start = false;
    protected Object o;
    protected ArrayList<ArcIG> arcs;
    protected ArrayList<EtapeIG> entrees;
    protected ArrayList<EtapeIG> sorties;
    protected ArrayList<ArcIG> arcsSelec;
    protected PointDeControleIG pdc;
    protected ArrayList<EtapeIG> activiteSelec;
    protected int nbActivite;
    protected int nbGuichet;
    protected int nbClients;

    private String nomButton ;

    public MondeIG() {
        this.style = 'd';
        gestionnaireClients = null;
        this.pdc =null;
        this.arcsSelec = new ArrayList<>();
        this.activiteSelec = new ArrayList<>();
        this.entrees = new ArrayList<>();
        this.sorties = new ArrayList<>();
        this.etapeIGS = new HashMap();
        this.arcs = new ArrayList<>(50);
        nbGuichet=1;
        nbActivite=1;
        nbClients = 5;
        nomButton = "Start" ;
        ajouter("Activite");
    }

    public ArrayList<ArcIG> getArcs() {
        return arcs;
    }

    public GestionnaireClients getGestionnaireClients() {
        return gestionnaireClients;
    }

    public void ajouter(String type){
        //Ajout d'une Etape selon son type
        String fabrique;
        fabrique = FabriqueIdentifiant.getInstance().getIdentifiantEtape();
        if(type.equals("Activite")) {
            etapeIGS.put(fabrique,new ActiviteIG(type+nbActivite, fabrique, TailleComposants.getInstance().getwVBox(), TailleComposants.getInstance().gethVBox()));
            nbActivite++;
            notifierObservateurs();
        }else if(type.equals("Guichet")){
            etapeIGS.put(fabrique,new GuichetIG(type+nbGuichet +" : 3 jetons", fabrique, TailleComposants.getInstance().getwVBox(), TailleComposants.getInstance().gethVBox()));
            nbGuichet++;
            notifierObservateurs();
        }
    }
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2)throws ArcIncorrectException {
        //Ajout d'un arc selon 2 pdc en paramètres
        ArcIG arcIG = new ArcIG(pt1, pt2);
        for (int i = 0; i < arcs.size(); i++) {
            if (pt1.getIdUnique() == arcs.get(i).getPdc1().getIdUnique() && pt2.getIdUnique() == arcs.get(i).getPdc2().getIdUnique() || pt1.getIdUnique() == arcs.get(i).getPdc2().getIdUnique() && pt2.getIdUnique() == arcs.get(i).getPdc1().getIdUnique()) {
                this.pdc = null;
                throw new ArcIncorrectException("Arc déjà réalisé entre ses 2 points de contrôle");
            }
            if (pt1.getEtape().estUnGuichet() && pt1.getEtape().nbSucc() ==1) {
                this.pdc = null;
                throw new ArcIncorrectException("Un guichet n'a qu'un successeur");
            }

        }
        if(pt1.getEtape().equals(pt2.getEtape())) {
            this.pdc = null;
            throw new ArcIncorrectException("Même activité");
        }
        sensCirculation(pt1,pt2);
        pt1.getEtape().ajouterSucc(pt2.getEtape());
        this.arcs.add(arcIG);

        notifierObservateurs();
    }
    public void sensCirculation(PointDeControleIG pt1, PointDeControleIG pt2){
        //Permettre d'établir le sens de circulation des étapes selon les positions X des pdc
        if (!pt1.getEtape().aUnSensDeCirculation && !pt2.getEtape().aUnSensDeCirculation) {
            if (pt1.getX() > pt2.getX()) {
                pt1.getEtape().aUnSensDeCirculation = true ;
                pt2.getEtape().aUnSensDeCirculation = true ;
                pt1.getEtape().sensDeCirculation = "Droite";
                pt2.getEtape().sensDeCirculation = "Droite";
            } else {
                pt1.getEtape().aUnSensDeCirculation = true ;
                pt2.getEtape().aUnSensDeCirculation = true ;
                pt1.getEtape().sensDeCirculation = "Gauche";
                pt2.getEtape().sensDeCirculation = "Gauche";
            }
        }else if(!pt2.getEtape().aUnSensDeCirculation){
            if (pt1.getX() > pt2.getX()) {
                pt2.getEtape().aUnSensDeCirculation = true ;
                pt2.getEtape().sensDeCirculation = "Droite";
            } else {
                pt2.getEtape().aUnSensDeCirculation = true ;
                pt2.getEtape().sensDeCirculation = "Gauche";
            }
        } else if(!pt1.getEtape().aUnSensDeCirculation){
            if (pt1.getX() > pt2.getX()) {
                pt1.getEtape().aUnSensDeCirculation = true ;
                pt1.getEtape().sensDeCirculation = "Droite";
            } else {
                pt1.getEtape().aUnSensDeCirculation = true ;
                pt1.getEtape().sensDeCirculation = "Gauche";
            }
        }
    }



    @Override
    public Iterator<EtapeIG> iterator() {
        return etapeIGS.values().iterator() ;
    }

    public Iterator<ArcIG> arcIGIterator(){
        return arcs.iterator();
    }

    public int getSizeHM() {
        return etapeIGS.size();
    }


    @Override
    public String toString() {
        return "MondeIG{" +
                "etapeIGS=" + etapeIGS +
                '}';
    }

    public HashMap<String, EtapeIG> getEtapeIGS() {
        return etapeIGS;
    }

    public void clickPointDeControle(PointDeControleIG pointDeControleIG)throws ArcIncorrectException {
        //Permet d'ajouter un point de controle dans un champ privé pour créer des arcs
        if(pdc == null){
            this.pdc = pointDeControleIG;
        }else{
            if(!pdc.equals(pointDeControleIG)){
                this.ajouter(pdc, pointDeControleIG);
                this.pdc = null;
            }else{
                this.pdc = null;
                throw new ArcIncorrectException("Même point de controle");
            }
        }

    }
    public void clickActivite(EtapeIG act){
        //Sélectionne et dé-selectionne des étapes
        if(activiteSelec.contains(act))
            activiteSelec.remove(act);
        else
            activiteSelec.add(act);

        notifierObservateurs();
    }
    public void aCommeEntree(){
        //Définit comme entrées les étapes sélectionnés
        for(EtapeIG e : activiteSelec){
            if(entrees.contains(e)) {
                entrees.remove(e);
            }else {
                entrees.add(e);
            }
        }
        activiteSelec.clear();
        notifierObservateurs();
    }
    public void aCommeSortie(){
        //Définit comme sorties les étapes sélectionnés
        for(EtapeIG e : activiteSelec){
            if(e.estUnGuichet())
                throw new ParametresExcpetion("Un guichet ne peut pas être une sortie !");
            if(sorties.contains(e)) {
                sorties.remove(e);
            }else {
                sorties.add(e);
            }
        }
        activiteSelec.clear();
        notifierObservateurs();
    }
    public void clickArc(ArcIG arc){
        //Selectionne un arc
        if(arcsSelec.contains(arc))
            arcsSelec.remove(arc);
        else
            arcsSelec.add(arc);

        notifierObservateurs();
    }
    public ArrayList<EtapeIG> getActiviteSelec() {
        return activiteSelec;
    }
    public void supprimer(){
        //Permet de supprimer tout les élements sélectionnés + les arcs reliés à elles
        for(EtapeIG e : activiteSelec){
            etapeIGS.remove(e.getIdentifiant());
            Iterator<ArcIG> iterator = this.arcIGIterator();
            while (iterator.hasNext()){
                ArcIG arcIG = iterator.next();
                Iterator<PointDeControleIG> pointDeControleIGIterator = e.iterator();
                while (pointDeControleIGIterator.hasNext()){
                    PointDeControleIG pdc = pointDeControleIGIterator.next();
                    if(arcIG.getPdc1().getIdUnique() == pdc.getIdUnique()  ||arcIG.getPdc2().getIdUnique() == pdc.getIdUnique()){
                        arcIG.getPdc1().getEtape().successeurs.remove(arcIG.getPdc2().getEtape()) ;
                        arcIG.getPdc1().getEtape().aUnSensDeCirculation = false ;
                        arcIG.getPdc2().getEtape().aUnSensDeCirculation = false ;
                        iterator.remove();
                    }
                }
            }

        }
        if(arcsSelec.size() != 0) {
            for (int l = 0; l < arcsSelec.size(); ++l) {
                arcsSelec.get(l).getPdc1().getEtape().successeurs.remove(arcsSelec.get(l).getPdc2().getEtape()) ;
                arcs.remove(arcsSelec.get(l));
            }
            arcsSelec.clear();
        }
        notifierObservateurs();
    }
    public void renommer(String s){
        //Renomme une étape
        if(activiteSelec.size() ==1){
            if(activiteSelec.get(0).estUneActivite())
                activiteSelec.get(0).setNom(s);
            else {
                activiteSelec.get(0).setEstRename(true);
                activiteSelec.get(0).setNom(s + " : " + activiteSelec.get(0).getNbJetons() + " jetons");
                activiteSelec.get(0).setDebutnom(s);
            }

        }else{
            throw new MenuException("On ne peut renommer qu'une seule activité à la fois!");
        }
        notifierObservateurs();
    }
    public void delai(String s){
        //Met un délai à une activité
        if(activiteSelec.size() ==1){
            boolean isNumeric =  s.matches("[+-]?\\d*(\\.\\d+)?");
            if(isNumeric) {
                if(Integer.parseInt(s) >=50){
                    throw new ParametresExcpetion("Le délai doit être inférieur à 50 secondes !");
                }
                if(Integer.parseInt(s) < 2){
                    throw new ParametresExcpetion("Le délai doit être supérieur à 1 seconde !");
                }
                activiteSelec.get(0).setDelai(Integer.parseInt(s));

            }else {
                throw new ParametresExcpetion("Veuillez mettre un nombre valide !");
            }
        }else{
            throw new ParametresExcpetion("On ne peut changer le délai d'une seule activité à la fois!");
        }
        activiteSelec.clear();
        notifierObservateurs();
    }
    public void ecartTemps(String s){
        //Met un écart temps à une activité
        if(activiteSelec.size() ==1){
            boolean isNumeric =  s.matches("[+-]?\\d*(\\.\\d+)?");
            if(isNumeric) {
                if(Integer.parseInt(s) >= activiteSelec.get(0).getDelai()){
                    throw new ParametresExcpetion("L'écart temps doit être strictement inférieur au délai !");
                }
                if(Integer.parseInt(s) < 1){
                    throw new ParametresExcpetion("Le délai doit être supérieur à 0 seconde !");
                }
                activiteSelec.get(0).setEcartTemps(Integer.parseInt(s));
            }else {
                throw new ParametresExcpetion("Veuillez mettre un nombre valide !");
            }
        }else{
            throw new ParametresExcpetion("On ne peut changer l'écart temps d'une seule activité à la fois!");
        }
        activiteSelec.clear();
        notifierObservateurs();
    }
    public void nombreJetons(String s){
        //Choisir un nombre de jetons à un guichet
        if(activiteSelec.size() ==1){
            boolean isNumeric =  s.matches("[+-]?\\d*(\\.\\d+)?");
            if(isNumeric) {
                if(activiteSelec.get(0).estUneActivite()){
                    throw new ParametresExcpetion("Veuillez mettre un Guichet et non une activité !");
                }
                if(Integer.parseInt(s) < 1){
                    throw new ParametresExcpetion("Le nombre de jetons doit être strictement > 0 !");
                }
                activiteSelec.get(0).setNbJetons(Integer.parseInt(s));
                GuichetIG g = (GuichetIG) activiteSelec.get(0);
                if(!g.estRename())
                    activiteSelec.get(0).setNom(activiteSelec.get(0).getDebutnom() + nbGuichet +" : " + Integer.parseInt(s) +" jetons");
                else
                    activiteSelec.get(0).setNom(activiteSelec.get(0).getDebutnom()  +" : " + Integer.parseInt(s) +" jetons");
            }else {
                throw new ParametresExcpetion("Veuillez mettre un nombre valide !");
            }
        }else{
            throw new ParametresExcpetion("On ne peut changer l'écart temps d'une seule activité à la fois!");
        }
        activiteSelec.clear();
        notifierObservateurs();
    }

    public ArrayList<ArcIG> getArcsSelec() {
        return arcsSelec;
    }

    public ArrayList<EtapeIG> getEntrees() {
        return entrees;
    }

    public ArrayList<EtapeIG> getSorties() {
        return sorties;
    }

    public char getStyle() {
        return style;
    }

    public void dragActivite(String identifiant, int x, int y){
        Iterator<EtapeIG> iterator = this.iterator();
        boolean changementPdc1 = false;
        boolean changementPdc2 = false;
        ArrayList<Integer> tabJ =new ArrayList<>();
        ArrayList<Integer> tabI =new ArrayList<>();
        while (iterator.hasNext()){
            EtapeIG a = iterator.next();
            if(a.getIdentifiant() == identifiant){
                a.setPosX(x);
                a.setPosY(y);
                for(int j=0;j<arcs.size();j++) {
                    for (int i = 0; i < a.pdcSize(); ++i) {
                        if(arcs.get(j).getPdc1() == a.pdc.get(i)){
                            tabI.add(j);
                            tabI.add(i);
                            changementPdc1 = true;
                        }else if(arcs.get(j).getPdc2() == a.pdc.get(i)){
                            tabJ.add(j);
                            tabJ.add(i);
                            changementPdc2 = true;
                        }
                    }
                }
                a.setNewPdc();
                changementDePdcsPourArcs(changementPdc1,changementPdc2,tabI,tabJ,a);
            }
        }
        notifierObservateurs();
    }

    public void changementDePdcsPourArcs(boolean changementPdc1, boolean changementPdc2, ArrayList<Integer> tabI,ArrayList<Integer> tabJ,EtapeIG a){
        //Permet de repositionner les pdcs après qu'une étape a était changer de place
        if(changementPdc1){
            for(int i=0;i<tabI.size();i++) {
                arcs.get(tabI.get(i)).setPdc1(a.pdc.get(tabI.get(i+1)));
                ++i;
            }
            tabI.clear();
            changementPdc1 = false;
        }
        if(changementPdc2){
            for(int i=0;i<tabJ.size();i++) {
                arcs.get(tabJ.get(i)).setPdc2(a.pdc.get(tabJ.get(i+1)));
                ++i;
            }
            tabJ.clear();
            changementPdc2 = false;
        }
    }

    public void setStyle(char style) {
        this.style = style;
        notifierObservateurs();
    }

    public void effacer(){
        //reset les actvités sélectionner
        arcsSelec.clear();
        activiteSelec.clear();
        this.pdc = null;
        notifierObservateurs();
    }

    private void verifierMondeIG() throws MondeIncorrectException{
        //Vérifie si le mondeIG est correct
        if (entrees.size()==0)
            throw new MondeIncorrectException("Le monde n'a aucune entrée !");
        if (sorties.size()==0)
            throw new MondeIncorrectException("Le monde n'a aucune sortie !");
        Iterator<EtapeIG> iter = this.iterator();
        while (iter.hasNext()){
            EtapeIG e = iter.next();
            if(e.estUnGuichet()){
                if(e.nbSucc()==1) {
                    if(e.successeurs.get(0).estUnGuichet()){
                        throw new MondeIncorrectException("Seul une activité peut suivre un guichet!");
                    }
                    e.successeurs.get(0).setActiviteRestreinte(true);
                }else {
                    throw new MondeIncorrectException("Un guichet n'a pas de successeur!");
                }
            }
            if(e.nbSucc()==0 && !sorties.contains(e)){
                throw new MondeIncorrectException("Une étape n'a pas de successeur!");
            }
        }
        detectionCircuit();
    }

    public Monde creerMonde(){
        //Crée un monde selon le mondeIG
        Monde monde = new Monde() ;
        Iterator<EtapeIG> etapeIGIter = this.iterator() ;
        correspondanceEtapes = new CorrespondanceEtapes() ;
        while(etapeIGIter.hasNext()){
            EtapeIG etapeIG = etapeIGIter.next() ;
            if(etapeIG.estUneActivite() && !etapeIG.estUneActiviteRestreinte()){
                Activite activite = new Activite(etapeIG.getNom(),etapeIG.getDelai(),etapeIG.getEcartTemps()) ;
                correspondanceEtapes.ajouter(etapeIG , activite ) ;
            }
            if(etapeIG.estUnGuichet()){
                correspondanceEtapes.ajouter(etapeIG, new Guichet(etapeIG.debutnom, etapeIG.nbJetons)) ;
            }
            if(etapeIG.estUneActiviteRestreinte()){
                ActiviteRestreinte activiteRestreinte = new ActiviteRestreinte(etapeIG.getNom(),etapeIG.getDelai(),etapeIG.getEcartTemps()) ;
                correspondanceEtapes.ajouter(etapeIG, activiteRestreinte) ;
            }
        }

        Iterator<EtapeIG> etapeIGIterator2 = this.iterator() ;
        while(etapeIGIterator2.hasNext()){
            EtapeIG etapeIG = etapeIGIterator2.next() ;
            monde.ajouter(correspondanceEtapes.get(etapeIG));
            if(sorties.contains(etapeIG)){
                monde.aCommeSortie(correspondanceEtapes.get(etapeIG));
            }
            if(entrees.contains(etapeIG)){
                monde.aCommeEntree(correspondanceEtapes.get(etapeIG));
            }

            for(int i = 0 ; i < etapeIG.nbSucc(); i++){
                correspondanceEtapes.get(etapeIG).ajouterSuccesseur(correspondanceEtapes.get(etapeIG.successeurs.get(i)));
            }
        }
        return monde ;

    }

    public void simuler() throws MondeIncorrectException{
        //Lance la simulation
        verifierMondeIG();
        start = true;
        nomButton= "Stop" ;
        animer();

    }

    public void nvMonde(Monde m,int nbClients){
        //Introspection pour lancer une simulation de Simulation.java avec un monde en paramètre
        try {
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(this.getClass().getClassLoader());
            Class<?> sim = classLoaderPerso.loadClass(Simulation.class.getName());

            Object o =  sim.getConstructor().newInstance() ;
            this.o = o;

            Method methodNbclient = sim.getMethod("setNbClients",int.class);
            Method methodSimuler = sim.getMethod("simuler", Monde.class);
            Method methodGestionnaireClients = sim.getMethod("getClients");
            Method method = sim.getMethod("ajouterObservateur",Observateur.class);
            this.method = methodGestionnaireClients;

            methodNbclient.invoke(o,nbClients);
            method.invoke(o,this);
            methodSimuler.invoke(o,m);
            this.gestionnaireClients = (GestionnaireClients) methodGestionnaireClients.invoke(o);





        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                InvocationTargetException e){
            throw new ExceptionSimulation(e.getMessage());
        }
    }
    public void getGestionnaire(){
        //Récupère le gestionnaire de clients de Simulation
        try {
            gestionnaireClients = (GestionnaireClients) method.invoke(o);
        } catch (IllegalAccessException e) {
            throw new MondeIncorrectException(e.getMessage()) ;
        } catch (InvocationTargetException e) {
            throw new MondeIncorrectException(e.getMessage()) ;
        }

    }


    @Override
    public void reagir() {
        notifierObservateurs();

    }
    public void animer(){
        //Tâche pour l'animation graphique
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws MondeIncorrectException {
                nvMonde(creerMonde(), nbClients);
                nomButton = "Start";
                start = false;
                notifierObservateurs();
                return null;
            }
        };
        ThreadsManager.getInstance().lancer(task);
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void stop(){
        //Permet de stopper le monde
        nomButton = "Start" ;
        ThreadsManager.getInstance().detruireTout();
        KitC kitC = new KitC() ;
        for(Client c : gestionnaireClients){
            kitC.tuerClient(c.getNumeroClient());
        }
        gestionnaireClients.getClients().clear();
        notifierObservateurs();
    }

    public String getNomButton() {
        return nomButton;
    }
    public void detectionCircuit(){
        //Détection de circuit/boucle du mondeIG
        for(EtapeIG x : etapeIGS.values()){
            for(EtapeIG y : etapeIGS.values()){
                if(x.estAccessibleDepuis(y) && y.estAccessibleDepuis(x)){
                    throw new MondeIncorrectException("Circuit détecter !") ;
                }
            }
        }
    }


    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(String s) {
        //Set le nombre de clients
        boolean isNumeric =  s.matches("[+-]?\\d*(\\.\\d+)?");
        if(isNumeric) {
            if(Integer.parseInt(s) < 1){
                throw new ParametresExcpetion("Le nombre de clients doit être strictement > 0 !");
            }
            nbClients = Integer.parseInt(s) ;
        }else {
            throw new ParametresExcpetion("Veuillez mettre un nombre valide !");
        }
        notifierObservateurs();
    }
}
