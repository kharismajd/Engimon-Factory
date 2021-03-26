#include <iostream>
#include <fstream>
#include <string>
#include <iterator> 
#include <vector>
#include "inventory.hpp"
#include "battle.hpp"
#include "wildEngimon.hpp"
#include "gameMap.hpp"
#include <set>
using namespace std;

/*Compile dengan*/
/*
g++ driver.cpp engimon.cpp skill.cpp wildEngimon.cpp gameMap.cpp tile.cpp

, player.cpp, inventory.cpp*/
int main()
{
	skill::InitializeSkillDatabase();
	engimon::IntializeDatabase();
	// string elem[5] = {"Fire", "Water", "Electric", "Ice"};
	// string lol[5] = {"Fire"};
	// skill a("a", 50, 0, lol, "0");
	// skill* s;
	// s = new skill[4];
	// skill nullSkill;
	// // s[0].printAll();
	// // s[1] = a;
	// // s[1].printAll();

	

	// // for (auto i = skill::skill_database.begin(); i != skill::skill_database.end(); ++i)
 // //        (*i).printAll();

	


	// engimon nullEngimon;
	// engimon Gab("Gab", &nullEngimon, &nullEngimon, "Magikarp", 4, 0);
	// engimon test;
	// test = Gab;
	// Gab.learnMove("tackle");
	// Gab.learnMove("bubble");
	// Gab.showAttributes();
	// cout << endl;
	// //test.showAttributes();
	// Gab.cry();

	// engimon Pulu("Pulu", &nullEngimon, &nullEngimon, "Magikarp", 10, 50);
	// engimon Pulu2("Pulu", &nullEngimon, &nullEngimon, "Vulpichu", 13, 20);
	// engimon Pulu3("Pulu", &nullEngimon, &nullEngimon, "Vulpichu", 2, 3);
	// Pulu.learnMove("bubble");
	// Pulu3.learnMove("tackle");
	// Pulu3.printMoves();

	// cout << endl;
	// wildEngimon WILDE("Pikachu",20);
	// WILDE.showAttributes();
	

	// player newPlayer("coba", Pulu, 50, 0, 1, 0, 0);

	// newPlayer.showEngimonDetails();
	// cout << endl <<Pulu3.getLevel();
	// Battle b2(&newPlayer.getActiveEngimon(), &Pulu3, &newPlayer);
	// b2.initiateBattle();
	//newPlayer.getActiveEngimon().getName();

	// Battle b1(&Pulu, &Pulu2, &newPlayer);
	// cout << "sini?" <<endl;
	// b1.initiateBattle();
	// cout << "masuk kah?" <<endl;
	// newPlayer.addEngimon(Pulu2);  

	// newPlayer.interact();       

	// newPlayer.deleteEngimonSelect();
	// newPlayer.showEngimonDetails();
	
	try{
	gameMap g("inputMapFile.txt");
	g.generateEngimon();
	g.updateMap(5,5,6,5);
	g.printMap();
}
	catch(char const* e)
	{
		cout << e;
	}

	// ifstream f("inputMapFile.txt");
	// int a, b;
	// char c;
	// f >> a;
	// f >> b;

	// cout << a << ", " << b << endl;
	// f = ifstream("inputMapFile.txt");
	// while(f >> c)
	// {
	// 	cout << c;
	// }
	return 0;
}