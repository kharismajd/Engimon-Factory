// testbreeder.cpp

#include "engimon.hpp"
#include "skill.hpp"
#include "breeder.hpp"
#include <iostream>
#include <cstdlib>
#include <string>
#include <iterator> 
#include <set>
#include <tuple>
#include <vector>

int main() {
    skill::InitializeSkillDatabase();
	engimon::IntializeDatabase();

    engimon A;
    engimon B;
    engimon C;
    engimon D;
    engimon parent1("Mona", &A, &B, "Charmander", 35, 100);
    engimon parent2("Lisa", &C, &D, "Magikarp", 30, 200);

    // Parent Skills
    try {
        parent1.learnMove("tackle");
        parent1.learnMove("ember");
        parent2.learnMove("tackle");
        // parent2.learnMove("thunderbolt");
    } catch (int x) {
        cout << x << endl;
    }

    cout << "PARENTS INITIAL STATE" << endl << endl;
    parent1.showAttributes();
    cout << endl;
    parent2.showAttributes();
    cout << endl << endl;

    cout << "BREEDING CHILD..." << endl << endl;

    engimon child = breeder::breed(&parent1, &parent2, "da Vinci");
    child.showAttributes();
    cout << endl << endl;

    cout << "PARENTS FINAL STATE" << endl << endl;
    parent1.showAttributes();
    parent2.showAttributes();
    cout << endl << endl;

    // MISCELLANEOUS TESTERS

    // int b = a.rng(50);
    // cout << b << endl << endl;

    // string c = a.inheritSpecies(&parent1, &parent2);
    // cout << c << endl << endl;
}