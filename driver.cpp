#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "skill.hpp"
#include "engimon.hpp"
#include "player.hpp"
#include "inventory.hpp"
#include <set>
using namespace std;

/*Compile dengan*/
/*g++ driver.cpp engimon.cpp skill.cpp, player.cpp, inventory.cpp*/
int main()
{
	string elem[5] = {"Fire", "Water", "Electric", "Ice"};
	string lol[5] = {"Fire"};
	skill a("a", 50, 0, lol, "0");
	skill* s;
	s = new skill[4];
	skill nullSkill;
	// s[0].printAll();
	// s[1] = a;
	// s[1].printAll();

	skill::InitializeSkillDatabase();

	// for (auto i = skill::skill_database.begin(); i != skill::skill_database.end(); ++i)
 //        (*i).printAll();

	engimon::IntializeDatabase();

	try
	{
		engimon nullEngimon;
		engimon Gab("Gab", &nullEngimon, &nullEngimon, "Magikarp", nullSkill, 4, 0);
		engimon test;
		test = Gab;
		Gab.learnMove("tackle");
		Gab.learnMove("bubble");
		Gab.showAttributes();
		cout << endl;
		//test.showAttributes();
		Gab.cry();

		engimon Pulu("Pulu", &nullEngimon, &nullEngimon, "Vulpichu", nullSkill, 10, 50);
		engimon Pulu2("Pulu", &nullEngimon, &nullEngimon, "Vulpichu", nullSkill, 13, 20);
        player newPlayer("coba", Gab, 50, 0, 0, 0, 1);

		newPlayer.interact();

        newPlayer.addEngimon(Pulu);
        newPlayer.addEngimon(Pulu2);         

        newPlayer.deleteEngimonSelect();
        newPlayer.showEngimonDetails();

        newPlayer.showEngimonList();
	}
	catch (char const* e)
	{
		cout << e << endl;
	}
	

	return 0;
}