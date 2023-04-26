package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private ArrayList<Thread> threads;

    private static ThreadsManager instance = new ThreadsManager();
    private ThreadsManager(){
        threads = new ArrayList<>();
    }

    public static ThreadsManager getInstance(){
        return instance ;
    }

    //Lance le thread
    public void lancer(Task task){
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();


    }

    //Permet d'arreter le thread en cours
    public void detruireTout(){
        for (Thread thread : threads){
            thread.interrupt();
        }

    }


}
