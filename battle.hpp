#include <iostream>
#include <stdlib.h>
#include "engimon.hpp"
#include "player.hpp"
#include "skill.hpp"

#ifndef BATTLE_HPP
#define BATTLE_HPP

#define FIRE_IDX 0
#define WATER_IDX 1
#define ELEC_IDX 2
#define GROUND_IDX 3
#define ICE_IDX 4

using namespace std;
/*SISTEM BATTLE*/
/*MENENTUKAN PEMENANG BERDASARKAN POWER LEVEL COMPARISON */
class Battle {
    private:
        engimon mymon;
        engimon yourmon;
        player character;
        float attrAdv[5][5] = {{1,0,1,0.5,2}, {2,1,0,1,1}, {1,2,1,0,1.5}, {1.5,1,2,1,0}, {0,1,0.5,2,1}};
                                     
    public:
        Battle(engimon a, engimon b, player character);
        Battle(const Battle& battle);
        ~Battle();
        Battle& operator=(const Battle& battle);

        engimon getMyEngimon();
        engimon getYourEngimon();
        player getPlayer();
        float elmtAdvantage(string* elmtEngimonFrom, string* elmtEngimonTo);
        float* power(); // PERHATIKAN ELMT ADVANTAGE DAN POIN F PADA SPEK
        void printTotalPowLv(float mymonpower, float yourmonpower);
        void win(); //add exp, add random skill item, add engimon
        void lose(); //mati
        void initiateBattle();
};

#endif