#include <stdio.h>
#include <stdlib.h>
#include "def.h"
#include "constante.h"
#include <string.h>

int main(int argc, char** argv) {
    // code à compléter
    int nbclients = 5;
    int nbetapes = 9;
    int nbguichet = 3;
    int tabJetonsGuichet[3] ;
    tabJetonsGuichet[0] = 1 ;
    tabJetonsGuichet[1] = 1;
    tabJetonsGuichet[2] = 1;


    int* tab = start_simulation(nbetapes,nbguichet,nbclients,tabJetonsGuichet) ;
    printf("les client :  ") ;
    for(int i= 0; i < nbclients ; i++){
        printf("%d",tab[i]) ;
        if( i < nbclients-1){
            printf(", ");
        }
    }
    printf("\n") ;
    char etape[9][50] ={""};
    strcpy(etape[0],"Entrée");
    strcpy(etape[1],"Guichet");
    strcpy(etape[2],"Musée");
    strcpy(etape[3],"Guichet2");
    strcpy(etape[4],"Piscine");
    strcpy(etape[5],"Douche");
    strcpy(etape[6],"Guichet3");
    strcpy(etape[7],"LOL");
    strcpy(etape[8],"Sortie");


    
    int y = 0 ;
    int nmEtape = 1 ;
    int stop = 0 ;
    int t=0;
    int* x = ou_sont_les_clients(nbetapes,nbclients) ;
    while(stop !=1){ // nul !!!!
        // for(int i=0;i<nbetapes*(nbclients+1);i++){
        //     printf("%d, ",x[i]);
        // }


        for(int i= 0; i < nbetapes*(nbclients+1) ; i++){
            if (i%(nbclients+1) == 0){
                    printf("\n") ;
                    printf("étape %d %s %d client :",nmEtape,etape[y], x[i]) ;
                    y++ ;
                    t=x[i];
                    nmEtape++;  
            }else{
                for(int n=0;n<t;n++){
                    printf("%d ,",x[i+n]) ;
                }
                t=0;
            }
        }
        printf("\n") ;
        sleep(1) ;
        y = 0 ;
        nmEtape = 1 ;
        if(x[(nbclients+1)*(nbetapes-1)]==nbclients){
            stop=1;
        }
        x = ou_sont_les_clients(nbetapes,nbclients) ;

    }
    

    nettoyage();
    return 0 ;
}
