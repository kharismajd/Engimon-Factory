#include "battle.hpp"

Battle :: Battle(engimon a, engimon b){
    this->mon1 = a;
    this->mon2 = b;
}

Battle :: Battle(const Battle& battle){
    this->mon1 = battle.mon1;
    this->mon2 = battle.mon2;
}

Battle :: ~Battle(){
    for (int i=0;i<5;i++){
        delete[] this->attrAdv[i];
    }
}

Battle& Battle :: operator=(const Battle& battle){
    this->mon1 = battle.mon1;
    this->mon2 = battle.mon2;
}

