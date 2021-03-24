// breeder.hpp

#include "engimon.hpp"
#include "skill.hpp"
#include <cstdlib>
#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include <vector>
#include <algorithm>

#ifndef BREEDER
#define BREEDER

using namespace std;

class breeder {
    protected:
        static string list_of_elements[5];
        static float element_advantage[5][5];
    public:
        int rng (int max);
        static engimon breed (engimon *parent1, engimon *parent2, string name);
        string inheritSpecies (engimon *parent1, engimon *parent2);
        void inheritSkill (engimon child, engimon *parent1, engimon *parent2);

        // Misc functions
        int indexOfElmt (string elmt);
        float getElmtAdvantage (string elmt1, string elmt2);
        string getHigherElmtAdvantage(string elmt1, string elmt2);
        void getDominantElmt (engimon *parent1, engimon *parent2, string *parent_elmt1, string *parent_elmt2);
        bool isLearnable (skill x, engimon y);
};

#endif
