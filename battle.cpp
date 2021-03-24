#include <iostream>
#include <stdlib.h>
#include <ctime>
#include "engimon.hpp"
#include "player.hpp"
#include "skill.hpp"
#include "battle.hpp"

using namespace std;

Battle::Battle(engimon* a, engimon* b, player* character){
    this->mymon = a;
    this->yourmon = b;
    this->character = character;
}

Battle::Battle(const Battle& battle){
    this->mymon = battle.mymon;
    this->yourmon = battle.yourmon;
    this->character = battle.character;
}

Battle :: ~Battle(){
    // No implementation
}

Battle& Battle::operator=(const Battle& battle){
    this->mymon = battle.mymon;
    this->yourmon = battle.yourmon;
    this->character = battle.character;
    return *this;
}

engimon Battle :: getMyEngimon(){
    return *this->mymon;
}

engimon Battle :: getYourEngimon(){
    return *this->yourmon;
}

player Battle :: getPlayer(){
    return *this->character;
}

float Battle :: elmtAdvantage(string* elmtEngimonFrom, string* elmtEngimonTo){
    int i; int j;
    float elmtComp1;
    float elmtComp2;
    float elmtAdvFinal = 0;

    for (i=0;i<2;i++){
        elmtComp1 = 0;
        if (elmtEngimonFrom[i]=="null"){
            //Do nothing
        } else if (elmtEngimonFrom[i]=="Fire"){
            for (j=0;j<2;j++){
                if (elmtEngimonTo[j]=="null"){
                    //Do nothing
                } else if (elmtEngimonTo[j]=="Fire") {
                    elmtComp2 = this->attrAdv[FIRE_IDX][FIRE_IDX];
                } else if (elmtEngimonTo[j]=="Water"){
                    elmtComp2 = this->attrAdv[FIRE_IDX][WATER_IDX];
                } else if (elmtEngimonTo[j]=="Electro"){
                    elmtComp2 = this->attrAdv[FIRE_IDX][ELEC_IDX];
                } else if (elmtEngimonTo[j]=="Ground"){
                    elmtComp2 = this->attrAdv[FIRE_IDX][GROUND_IDX];
                } else if (elmtEngimonTo[j]=="Ice") {
                    elmtComp2 = this->attrAdv[FIRE_IDX][ICE_IDX];
                }

                if (elmtComp2>elmtComp1){
                    elmtComp1 = elmtComp2;
                }
            }
        } else if (elmtEngimonFrom[i]=="Water"){
            for (j=0;j<2;j++){
                if (elmtEngimonTo[j]=="null"){
                    //Do nothing
                } else if (elmtEngimonTo[j]=="Fire") {
                    elmtComp2 = this->attrAdv[WATER_IDX][FIRE_IDX];
                } else if (elmtEngimonTo[j]=="Water"){
                    elmtComp2 = this->attrAdv[WATER_IDX][WATER_IDX];
                } else if (elmtEngimonTo[j]=="Electro"){
                    elmtComp2 = this->attrAdv[WATER_IDX][ELEC_IDX];
                } else if (elmtEngimonTo[j]=="Ground"){
                    elmtComp2 = this->attrAdv[WATER_IDX][GROUND_IDX];
                } else if (elmtEngimonTo[j]=="Ice") {
                    elmtComp2 = this->attrAdv[WATER_IDX][ICE_IDX];
                }

                if (elmtComp2>elmtComp1){
                    elmtComp1 = elmtComp2;
                }
            }
        } else if (elmtEngimonFrom[i]=="Electro"){
            for (j=0;j<2;j++){
                if (elmtEngimonTo[j]=="null"){
                    //Do nothing
                } else if (elmtEngimonTo[j]=="Fire") {
                    elmtComp2 = this->attrAdv[ELEC_IDX][FIRE_IDX];
                } else if (elmtEngimonTo[j]=="Water"){
                    elmtComp2 = this->attrAdv[ELEC_IDX][WATER_IDX];
                } else if (elmtEngimonTo[j]=="Electro"){
                    elmtComp2 = this->attrAdv[ELEC_IDX][ELEC_IDX];
                } else if (elmtEngimonTo[j]=="Ground"){
                    elmtComp2 = this->attrAdv[ELEC_IDX][GROUND_IDX];
                } else if (elmtEngimonTo[j]=="Ice") {
                    elmtComp2 = this->attrAdv[ELEC_IDX][ICE_IDX];
                }

                if (elmtComp2>elmtComp1){
                    elmtComp1 = elmtComp2;
                }
            }
        } else if (elmtEngimonFrom[i]=="Ground"){
            for (j=0;j<2;j++){
                if (elmtEngimonTo[j]=="null"){
                    //Do nothing
                } else if (elmtEngimonTo[j]=="Fire") {
                    elmtComp2 = this->attrAdv[GROUND_IDX][FIRE_IDX];
                } else if (elmtEngimonTo[j]=="Water"){
                    elmtComp2 = this->attrAdv[GROUND_IDX][WATER_IDX];
                } else if (elmtEngimonTo[j]=="Electro"){
                    elmtComp2 = this->attrAdv[GROUND_IDX][ELEC_IDX];
                } else if (elmtEngimonTo[j]=="Ground"){
                    elmtComp2 = this->attrAdv[GROUND_IDX][GROUND_IDX];
                } else if (elmtEngimonTo[j]=="Ice") {
                    elmtComp2 = this->attrAdv[GROUND_IDX][ICE_IDX];
                }

                if (elmtComp2>elmtComp1){
                    elmtComp1 = elmtComp2;
                }
            }
        } else if (elmtEngimonFrom[i]=="Ice"){
            for (j=0;j<2;j++){
                if (elmtEngimonTo[j]=="null"){
                    //Do nothing
                } else if (elmtEngimonTo[j]=="Fire") {
                    elmtComp2 = this->attrAdv[ICE_IDX][FIRE_IDX];
                } else if (elmtEngimonTo[j]=="Water"){
                    elmtComp2 = this->attrAdv[ICE_IDX][WATER_IDX];
                } else if (elmtEngimonTo[j]=="Electro"){
                    elmtComp2 = this->attrAdv[ICE_IDX][ELEC_IDX];
                } else if (elmtEngimonTo[j]=="Ground"){
                    elmtComp2 = this->attrAdv[ICE_IDX][GROUND_IDX];
                } else if (elmtEngimonTo[j]=="Ice") {
                    elmtComp2 = this->attrAdv[ICE_IDX][ICE_IDX];
                }

                if (elmtComp2>elmtComp1){
                    elmtComp1 = elmtComp2;
                }
            }
        }
        if (elmtAdvFinal<elmtComp1){
            elmtAdvFinal = elmtComp1;
        }
    }
    return elmtAdvFinal;
}

float* Battle :: power(){
    //RETURN: [mymon power, yourmon power]
    int i;
    int j;
    float elmtComp2;
    float elmtComp1;

    engimon mymon = *this->mymon;
    engimon yourmon = *this->yourmon;

    int mylv = mymon.getLevel();
    int yourlv = yourmon.getLevel();

    string myelmt[2] = {mymon.getElmt1(), mymon.getElmt2()};
    string yourelmt[2] = {yourmon.getElmt1(), yourmon.getElmt2()};

    skill* myskill = new skill[4];
    skill* yourskill = new skill[4];
    for (i=0;i<4;i++){
        myskill[i] = mymon.getMove(i);
        yourskill[i] = yourmon.getMove(i);
    }

    int sumofmyskill = 0;
    int sumofyourskill = 0;

    for (i=0;i<4;i++){
        sumofmyskill += myskill[i].getBasePower() * myskill[i].getMasteryLv();
        sumofyourskill += yourskill[i].getBasePower() * yourskill[i].getMasteryLv();
    }

    float* power = new float[2];

    power[0] = mymon.getLevel()*elmtAdvantage(myelmt,yourelmt) + sumofmyskill;
    power[1] = yourmon.getLevel()*elmtAdvantage(yourelmt,myelmt) + sumofyourskill;

    return power;
}


void Battle :: printTotalPowLv(float mymonpower, float yourmonpower){
    cout << "My Engimon Power: " << mymonpower << endl;
    cout << "Enemy Engimon Power: " << yourmonpower << endl;
}

void Battle :: win(){
    int countSkill;
    int isNull;
    int diffLv = yourmon->getLevel() - mymon->getLevel();
    float exp = (10*diffLv + 600)/(80-diffLv)+5;

    mymon->gainExp(exp);
    character->addEngimon(*this->yourmon);
    
    isNull = 0;
    countSkill = 0;
    while (!isNull && countSkill<4){
        if (yourmon->getMove(countSkill).getSkillName()=="null"){
            isNull = 1;
        } else {
            countSkill++;
        }
    }
    srand(time(0));
    int idxSkill = rand() % (countSkill);
    string newSkill = yourmon->getMove(idxSkill).getSkillName();
    character->addSkillItem(newSkill);
    cout << "Berhasil mendapatkan skill item \""<< newSkill << "\" dari Wild Engimon" <<endl;
    cout << "Berhasil mendapatkan engimon "<< yourmon->getSpecies() << " [Lv " << yourmon->getLevel() << "]" <<endl;
    this->~Battle();
}

void Battle :: lose(){
    character->deleteActiveEngimon();
    // this->~Battle();
}

void Battle ::initiateBattle(){
    float* pow = new float[2];
    pow = power();

    printTotalPowLv(pow[0],pow[1]);

    if (pow[0]>pow[1]){
        cout << "You Win!!" << endl;
        win();
    } else {
        cout << "You Lose!!" << endl;
        lose();
    }
}