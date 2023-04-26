#include <stdio.h>
#include <stdlib.h>
#include "def.h"
#include "constante.h"

void simulation(int ids){
    printf("j'entre dans le sas d'entrée\n");
    entrer(sasEntree) ;
    delai(5,2);
    printf("je quitte le sas d'entrée et je rentre dans le guichet\n");


    transfert(sasEntree,guichet);
    P(ids,num_sem_guichet);
    delai(5,2);
    printf("je quitte le guichet et j'entre dans l'activité\n");
    transfert(guichet,activite);
    delai(10,4);


    int x = V(ids,num_sem_guichet);
    printf("REGARDEZ MOI JE SUIS V %d ",x);
    printf("je quitte l'activité et j'entre dans le sas sortie\n");
    transfert(activite,sasSorti);
    delai(5,3);
    printf("je quitte le sas sortie");

}
