#include <iostream>
#include "engimon.hpp"

#ifndef BATTLE_HPP
#define BATTLE_HPP

#define FIRE_IDX 0;
#define WATER_IDX 1;
#define ELEC_IDX 2;
#define GROUND_IDX 3;
#define ICE_IDX 4;

using namespace std;
/*SISTEM BATTLE*/
/*MENENTUKAN PEMENANG BERDASARKAN POWER LEVEL COMPARISON */
class Battle {
    private:
        engimon mon1;
        engimon mon2;
        const float attrAdv[5][5] = {{1,0,1,0.5,2},
                                     {2,1,0,1,1},
                                     {1,2,1,0,1.5},
                                     {1.5,1,2,1,0},
                                     {0,1,0.5,2,1}};
                                     
    public:
        Battle(engimon a, engimon b);
        Battle(const Battle& battle);
        ~Battle();
        Battle& operator=(const Battle& battle);

        double power(); // PERHATIKAN ELMT ADVANTAGE DAN POIN F PADA SPEK
        void printTotalPowLv();
        //FUNGSI MATI UDAH DIBUAT??
        void addEngimon(); //JIKA INVEN CUKUP
        void win(); //add exp, add random skill item, add engimon
        void lose(); //mati
};

#endif