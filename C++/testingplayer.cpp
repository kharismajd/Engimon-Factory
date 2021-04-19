#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "skill.hpp"
#include "engimon.hpp"
#include "player.hpp"
#include <set>
using namespace std;

int main()
{
    skill::InitializeSkillDatabase();
	engimon::IntializeDatabase();
    try
    {
        skill nullSkill;
        engimon nullEngimon;
        engimon Gab("Gab", &nullEngimon, &nullEngimon, "Magikarp", 34, 0);
        engimon Pulu("Pulu", &nullEngimon, &nullEngimon, "Vulpichu", 3, 50);
        player newPlayer("nama", Gab, 50, 0, 0, 0, 0);
        newPlayer.addInventoryContent(Pulu);
        newPlayer.breeding();
        newPlayer.showEngimonList();
        newPlayer.showSkillItemList();

        delete &newPlayer;
    }
    catch (char const* e)
	{
		cout << e << endl;
	}
    return 0;
}