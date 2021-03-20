#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "skill.hpp"
#include "engimon.hpp"
#include <set>
using namespace std;

/*Compile dengan*/
/*g++ driver.cpp engimon.cpp skill.cpp*/
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
		engimon Gab("Gab", "null", "null", "Magikarp", nullSkill, 4, 0);
		engimon test;
		test = Gab;
		Gab.learnMove("tackle");
		Gab.learnMove("bubble");
		Gab.showAttributes();
		cout << endl;
		//test.showAttributes();
		Gab.cry();
	}
	catch (char const* e)
	{
		cout << e << endl;
	}
	

	return 0;
}