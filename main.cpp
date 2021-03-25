#include <iostream>
#include <string>
#include <cctype>
#include <algorithm>
#include "gameMap.hpp"
#include "battle.hpp"
#include "player.hpp"
using namespace std;

/*
g++ main.cpp tile.cpp gameMap.cpp engimon.cpp skill.cpp player.cpp inventory.cpp battle.cpp
*/

void printHelp()
{
	cout << "Commands: " << endl;
	cout << "Move: w/a/s/d" << endl;
	cout << "Legend: l" << endl;
	cout << "Interact: z" << endl;
	cout << "Show Map: m" << endl;
	cout << "Battle: b" << endl;
	cout << "Show Engimon: e" << endl;
	cout << "Show Item: i" << endl;
	cout << "Use Skill Item: u" << endl;
	cout << "Switch Active Engimon: x" << endl;
}

void resolveMove(player &P, gameMap &g)
{
	if (g.isTileOccupied(P.getPlayerPosX(),P.getPlayerPosY()))
	{
		cout << "Battle" << endl;
		Battle b(&(P.getActiveEngimon()), &(g.getTileEngimon(P.getPlayerPosX(),P.getPlayerPosY())), &P);
		b.initiateBattle();
	}

	try
	{
		g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
	}
	catch(const char* error)
	{
		cout << error << endl;
	}
	
	g.printMap();
}

int main()
{
	//Null values
	engimon nullEngimon;

	//Inisialisasi
	skill::InitializeSkillDatabase();
	engimon::IntializeDatabase();
	//Deklarasi
	string userInput;
	string starter_engimon_name;
	engimon starter_engimon;
	gameMap g;
	

	// /*Program jalan*/
	// cout << "... Compile Success!" << endl;

	// cout << "Pilih engimon pertama anda" << endl;
	// cout << "- Charmander" << endl;
	// cout << "- Pikachu" << endl;
	// cout << "- Glastrier" << endl << endl;
	// cout << "Engimon: ";

	// cin >> userInput;
	// cout << endl;
	// while (userInput != "Charmander" && userInput != "Pikachu" && userInput != "Glastrier")
	// {
	// 	cout << "Species engimon tidak tepat, silahkan coba lagi" << endl;
	// 	cout << "- Charmander" << endl;
	// 	cout << "- Pikachu" << endl;
	// 	cout << "- Glastrier" << endl << endl;
	// 	cout << "Engimon: ";

	// 	cin >> userInput;
	// 	cout << endl;
	// }

	// cout << "Please name your engimon: ";
	// cin >> starter_engimon_name;
	// cout << endl;

	// try
	// {
	// 	starter_engimon = engimon(starter_engimon_name, &nullEngimon, &nullEngimon, userInput, 20, 0);
	// }
	// catch (char const* e)
	// {
	// 	cout << e;
	// 	return -1;
	// }



	// if (userInput == "Charmander")
	// {
	// 	cout << "Charmander picture ... " << endl;
	// }

	// else if (userInput == "Pikachu")
	// {
	// 	cout << "Pikachu picture ... " << endl;
	// }
	// else /*userInput == "Glastrier*/
	// {
	// 	cout << "Glastrier picture ... " << endl;
	// }

	// cout << "This is your starting engimon: " << endl;
	// starter_engimon.showAttributes();

	// cout << endl << "Now, please tell us your name" << endl;
	// cout << "Name: ";
	// cin >> userInput;
	// cout << endl;

	// player P(userInput, starter_engimon, 50, 5, 4, 5, 5);
	starter_engimon = engimon("Gab", &nullEngimon, &nullEngimon, "Pikachu", 100, 0);
	starter_engimon.showAttributes();
	player P("Ash", starter_engimon, 50, 5, 6, 5, 5);
	cout << endl;
	cout << P.getName() << ", you are now ready, step in to the world of engimon" << endl;

	g.generateEngimon();
	g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
	g.printMap();

	cin >> userInput;
	while (userInput != "exit")
	{
		//Lower userInput
		// cout << "Player Position" << P.getPlayerPosX() << "," << P.getPlayerPosY() << endl;
		// cout << "Active Engimon Position" << P.getActivePetPosX() << "," << P.getActivePetPosY() << endl;
		transform(userInput.begin(), userInput.end(), userInput.begin(),[](unsigned char c){ return tolower(c); });
		if (userInput == "c" || userInput == "command" || userInput == "commands" ||userInput == "h" || userInput == "help")
		{
			printHelp();
		}
		else if (userInput == "l")
		{
			g.printLegend();
		}
		else if (userInput == "w")
		{
			P.moveUp();
			resolveMove(P, g);
			
		}
		else if (userInput == "a")
		{
			P.moveLeft();
			resolveMove(P, g);
		}
		else if (userInput == "s")
		{
			P.moveDown();
			resolveMove(P, g);
		}
		else if (userInput == "d")
		{
			P.moveRight();
			resolveMove(P, g);
		}
		else if (userInput == "z")
		{
			P.interact();
		}
		else if (userInput == "m")
		{
			g.printMap();
		}
		else if (userInput == "b")
		{
			//Breeding
		}
		else if (userInput == "e")
		{
			P.showEngimonList();
		}
		else if (userInput == "i")
		{
			P.showSkillItemList();
		}
		else if (userInput == "u")
		{
			P.useSkillItem();
		}
		else if (userInput == "x")
		{
			P.switchOutEngimon();
		}
		else
		{
			cout << "Invalid Command, type 'h' to list all commands" << endl;
		}
		cin >> userInput;
	}

	cout << "Bye" << endl;
	
	return 0;
}