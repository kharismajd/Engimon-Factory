// breeder.cpp

#include "engimon.hpp"
#include "skill.hpp"
#include "breeder.hpp"
#include <cstdlib>
#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include <vector>
#include <algorithm>
#include <time.h>

using namespace std;

string breeder::list_of_elements[5] = {"Fire", "Water", "Electric", "Ground", "Ice"};
float breeder::element_advantage[5][5] = {{1, 0, 1, 0.5, 2}, {2, 1, 0, 1, 1}, {1, 2, 1, 0, 1.5}, {1.5, 1, 2, 1, 0}, {0, 1, 0.5, 2, 1}};

// Generates a random number between 0 to max
int breeder::rng(int max) {
    srand(time(NULL));
    return (rand() % max);
}

// Main breeder function
engimon breeder::breed (engimon *parent1, engimon *parent2, string name) {
    if (parent1->getLevel() > 30 && parent2->getLevel() > 30) {
        breeder a;
        
        string sp = a.inheritSpecies(parent1, parent2);
        engimon child(name, parent1, parent2, sp, 1, 0);

        a.inheritSkill(&child, parent1, parent2);

        parent1->setLevel(parent1->getLevel() - 30);
        parent2->setLevel(parent2->getLevel() - 30);

        cout << "Breeding berhasil dilakukan" << endl << endl;
        child.showAttributes();
        return child;
    } else {
        throw "Error, salah satu atau kedua parent memiliki level di bawah 31";
    }
}

// Method to get species of child engimon
string breeder::inheritSpecies (engimon *parent1, engimon *parent2) {

    // Jika parent memiliki species sama, langsung turunkan species anak
    if (parent1->getSpecies() == parent2->getSpecies()) {
        return parent1->getSpecies();
    // Jika parent memiliki species berbeda, namun elemen sama, randomize species anak
    } else if (sameElements(parent1, parent2)) {
        int chance = rng(2);
        if (chance) {
            return parent1->getSpecies();
        } else {
            return parent2->getSpecies();
        }
    } else {
        // Get dominant element from both parents
        string parent_elmt1, parent_elmt2;
        getDominantElmt(parent1, parent2, &parent_elmt1, &parent_elmt2);

        if (parent_elmt1 == parent_elmt2) {
            // Case 1: both parents have same dom element, child inherits randomized species
            int chance = rng(2);
            if (chance) {
                return parent1->getSpecies();
            } else {
                return parent2->getSpecies();
            }
        } else if (getHigherElmtAdvantage(parent_elmt1, parent_elmt2) != string_null) {
            // Case 2: parents have different dom elements, get higher element advantage species
            if (getHigherElmtAdvantage(parent_elmt1, parent_elmt2) == parent_elmt1) {
                return parent1->getSpecies();
            } else {
                return parent2->getSpecies();
            }
        } else {
            auto i = engimon::all_species.begin();
            for (i = engimon::all_species.begin(); i != engimon::all_species.end(); ++i)
            {
                if ((get<1>(*i) == parent_elmt1 && get<2>(*i) == parent_elmt2) || (get<1>(*i) == parent_elmt2 && get<1>(*i) == parent_elmt1)) break;
            }
            return get<0>(*i);
        }
    }
}

// Method to get skill of child engimon
void breeder::inheritSkill (engimon *child, engimon *parent1, engimon *parent2) {
    int iter1[4] = {0, 0, 0, 0};
    int iter2[4] = {0, 0, 0, 0};
    int max, idxmax;

    int i = 1;
    bool end = false; // True if no more skills can be learned

    while (i < 4 && !end) {
        max = -1;
        idxmax = -1;

        // Find skill with max mastery level
        for (int j = 1; j < 4; j++) {
            if (iter1[j] != 1) {
                if (!isLearnable(parent1->getMove(j), *child)) {
                    iter1[j] = 1;
                } else if (parent1->getMove(j).getMasteryLv() > max) {
                    max = parent1->getMove(j).getMasteryLv();
                    idxmax = j;
                }
            }
            if (iter2[j] != 1) {
                if (!isLearnable(parent2->getMove(j), *child)) {
                    iter2[j] = 1;
                } else if (parent2->getMove(j).getMasteryLv() > max) {
                    max = parent2->getMove(j).getMasteryLv();
                    idxmax = j + 4;
                }
            }
        }

        //cout << iter1[0] << iter1[1] << iter1[2] << iter1[3] << endl;
        //cout << iter2[0] << iter2[1] << iter2[2] << iter2[3] << endl;

        if (idxmax == -1) {
            // No skills to learn, exit function
            end = true;
        } else {
            // There are still skills to be learned
            int mastery;
            skill chosen_skill; // Filled when skill is chosen to be learned
            // cout << "Max is " << max << " and idxmax is " << idxmax << endl;

            // Check 1st parent's skills
            if (idxmax < 4) {
                iter1[idxmax] = 1;
                chosen_skill = parent1->getMove(idxmax);
                mastery = chosen_skill.getMasteryLv();
                // chosen_skill.printAll();

                // Mark skills that both parents have
                for (int j = 1; j < 4; j++) {
                    if (chosen_skill.getSkillName() == parent2->getMove(j).getSkillName()) {
                        iter2[j] = 1;
                        if (chosen_skill.getMasteryLv() == parent2->getMove(j).getMasteryLv()) {
                            // Learn skill, chosen mastery level + 1
                            mastery = chosen_skill.getMasteryLv() + 1;
                        }
                        break;
                    }
                }
            // Check 2nd parent's skills
            } else {
                iter2[idxmax - 4] = 1;
                chosen_skill = parent2->getMove(idxmax - 4);
                mastery = chosen_skill.getMasteryLv();
                // chosen_skill.printAll();

                // Mark skills that both parents have
                for (int j = 0; j < 4; j++) {
                    if (chosen_skill.getSkillName() == parent1->getMove(j).getSkillName()) {
                        iter1[j] = 1;
                    }
                    break;
                }
            }
            child->setSkill(chosen_skill, i, mastery);
            // cout << chosen_skill.getSkillName() << " has been learned with mastery = " << mastery << endl << endl;
        }
        i++;
    }
}

// Returns true if both parents have same elements
bool breeder::sameElements (engimon *parent1, engimon *parent2) {
    string elmt1_1 = parent1->getElmt1();
    string elmt1_2 = parent1->getElmt2();
    string elmt2_1 = parent2->getElmt1();
    string elmt2_2 = parent2->getElmt2();
    return ((elmt1_1 == elmt2_1 && elmt1_2 == elmt2_2) || (elmt1_1 == elmt2_2 && elmt1_2 == elmt2_1));
}

// Returns index in elmt advantage table
int breeder::indexOfElmt (string elmt) {
    int i = 0;
    while (list_of_elements[i] != elmt) {
        i++;
    }
    return i;
}

// Returns element advantage of elmt1 towards elmt2
float breeder::getElmtAdvantage (string elmt1, string elmt2) {
    int type1 = indexOfElmt(elmt1);
    int type2 = indexOfElmt(elmt2);
    return element_advantage[type1][type2];
}

// Returns the element with the higher advantage, with null cases
string breeder::getHigherElmtAdvantage (string elmt1, string elmt2) {
    if (elmt1 == string_null) {
        return elmt2;
    } else if (elmt2 == string_null) {
        return elmt1;
    } else {
        if (getElmtAdvantage(elmt1, elmt2) > getElmtAdvantage(elmt2, elmt1)) {
            return elmt1;
        } else if (getElmtAdvantage(elmt1, elmt2) < getElmtAdvantage(elmt2, elmt1)) {
            return elmt2;
        } else {
            return string_null;
        }
    }
}

// Returns the most found elmt, if all elements are equal, get highest elmt advantage
void breeder::getDominantElmt (engimon *parent1, engimon *parent2, string *parent_elmt1, string *parent_elmt2) {
    // Initialize values
    string candidates[2] = {string_null, string_null};
    string elmt1_1 = parent1->getElmt1();
    string elmt1_2 = parent1->getElmt2();
    string elmt2_1 = parent2->getElmt1();
    string elmt2_2 = parent2->getElmt2();

    // Find most found elmt
    if (elmt1_1 == elmt2_1 || elmt1_1 == elmt2_2) {
        candidates[0] = elmt1_1;
    } else if (elmt1_2 == elmt2_1 || elmt1_2 == elmt2_2) {
        candidates[1] = elmt1_2;
    }

    if (candidates[0] != string_null && candidates[1] != string_null) {
        // Find higher element advantage
        *parent_elmt1 = getHigherElmtAdvantage(elmt1_1, elmt1_2);
        *parent_elmt2 = getHigherElmtAdvantage(elmt1_1, elmt1_2);
    } else if (candidates[0] != string_null) {
        *parent_elmt1 = elmt1_1;
        *parent_elmt2 = elmt1_1;
    } else if (candidates[0] != string_null) {
        *parent_elmt1 = elmt1_2;
        *parent_elmt2 = elmt1_2;
    } else {
        // If all parent elements are unique
        *parent_elmt1 = getHigherElmtAdvantage(elmt1_1, elmt1_2);
        *parent_elmt2 = getHigherElmtAdvantage(elmt2_1, elmt2_2);
    }
}

// Skill x can be learned by engimon y
bool breeder::isLearnable (skill x, engimon y) {
    bool check = false;
    // Check if child element is within skill element
    vector<string> move_elements = x.getElements();
    auto element1_checker = find(move_elements.begin(), move_elements.end(), y.getElmt1());
    auto element2_checker = find(move_elements.begin(), move_elements.end(), y.getElmt2());

    if (element1_checker == move_elements.end() && element2_checker == move_elements.end()) {
    	return false;
    }
    // Check if string is learnable with other parameters
    return (x.getSkillName() != string_null && (x.getUniqueName() == y.getSpecies() || x.getUniqueName() == "0"));
}