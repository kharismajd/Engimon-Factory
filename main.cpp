#include <iostream>
#include <string>
#include <cctype>
#include <algorithm>
#include "gameMap.hpp"
#include "player.hpp"
using namespace std;

/*
g++ main.cpp tile.cpp gameMap.cpp engimon.cpp skill.cpp player.cpp inventory.cpp
*/

void printHelp()
{
	cout << "Commands: " << endl;
	cout << "Move: w/a/s/d" << endl;
	cout << "Legend: l" << endl;
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
	

	//Program jalan
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
	// 	starter_engimon = engimon(starter_engimon_name, &nullEngimon, &nullEngimon, userInput, 1, 0);
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

	//player P(userInput, starter_engimon, 50, 5, 4, 5, 5);
	starter_engimon = engimon("Gab", &nullEngimon, &nullEngimon, "Pikachu", 1, 0);
	player P("Ash", starter_engimon, 50, 5, 4, 5, 5);
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
			g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
			g.printMap();
		}
		else if (userInput == "a")
		{
			P.moveLeft();
			g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
			g.printMap();
		}
		else if (userInput == "s")
		{
			P.moveDown();
			g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
			g.printMap();
		}
		else if (userInput == "d")
		{
			P.moveRight();
			g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY());
			g.printMap();
		}
		else if (userInput == "z")
		{
			P.interact();
		}
		else
		{
			cout << "Invalid Command: " << endl;
		}
		cin >> userInput;
	}

	cout << "Bye" << endl;
	
	return 0;
}