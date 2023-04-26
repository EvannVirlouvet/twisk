#include <stdio.h>
#include <stdlib.h>
#include "def.h"
#include "constante.h"

void simulation(int ids){
    entrer(sasEntree) ;
    delai(5,2);
    transfert(sasEntree,guichet);

    P(ids,num_sem_guichet);
    transfert(guichet,activite);
    delai(4,2);
    V(ids,num_sem_guichet);
    transfert(activite,guichet2);


    P(ids,num_sem_guichet2);
    transfert(guichet2,activite2);
    delai(5,2);
    
    transfert(activite2,activite3);
    delai(5,2);
    V(ids,num_sem_guichet2);
    transfert(activite3,guichet3);
    
    P(ids,num_sem_guichet3);
    transfert(guichet3,activite4);
    delai(8,4);
    

    transfert(activite4,sasSorti);
    V(ids,num_sem_guichet3);
}
