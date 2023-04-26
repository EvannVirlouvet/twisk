package twisk.outils;

import twisk.simulation.Client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {
    private int cptLib;
    public KitC() {
        this.cptLib = FabriqueNumero.getInstance().getCptLib();

    }

    //Genere les fichier C dans tmp/twisk
    public void creerEnvironnement(){
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà.
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des fichiers depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                InputStream source = getClass().getResource("/codeC/" + nom).openStream();
                File destination = new File("/tmp/twisk/" + nom);
                copier(source, destination);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void copier(InputStream source, File dest) throws IOException {
        InputStream sourceFile = source;
        OutputStream destinationFile = new FileOutputStream(dest) ;
        // Lecture par segment de 0.5Mo
        byte buffer[] = new byte[512 * 1024];
        int nbLecture;
        while ((nbLecture = sourceFile.read(buffer)) != -1){
            destinationFile.write(buffer, 0, nbLecture);
        }
        destinationFile.close();
        sourceFile.close();
    }
    public void creerFichier(String codeC){
        try {
            Path chemin = Paths.get("/tmp/twisk/client.c");
            byte[] code = codeC.getBytes();
            Path CheminFichierCreer = Files.write(chemin, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compiler(){
        try {
            Runtime runtime = Runtime.getRuntime();
            String commande = "gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o ";
            Process p = runtime.exec(commande);
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne ;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void construireLaLibrairie(){
        try {
            Runtime runtime = Runtime.getRuntime();
            String commande = "gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk"+cptLib +".so ";
            Process p = runtime.exec(commande);
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne ;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tuerClient(int numeroClient){
        try {
            Runtime.getRuntime().exec("kill -9 "+ numeroClient).waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
