#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
using namespace std;

#define HGIH_LEVEL_CAP 20

#ifndef TILE
#define TILE
class tile
{
public:
	tile();
	tile(int x, int y, engimon e, string type);
	~tile();

	bool isTileValid(engimon);
	bool haveWildEngimon();

	void passed();
	void resetPass();
	bool isPass();
	void spawn();
	void printTile();
	
	void setEngimon(engimon e);
	engimon& getEngimon();
	void setSymbol(char);

	void playerIsHere();
	void playerIsNotHere();
	bool isPlayerHere();

	void activeEngimonIsHere();
	void activeEngimonIsNotHere();
	bool isactiveEngimonHere();

	void updateCharacter();

	static int random_seed;
	static engimon universal_nullEngimon;
protected:
	int x;
	int y;

	engimon wild_engimon;
	char symbol;
	string tile_type; //Hanya berupa grassland atau sea
	bool tilePass;	
	bool isPlayer = false;
	bool isActivePokemon = false;

	
};
#endif