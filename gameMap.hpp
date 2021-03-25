#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "tile.hpp"
#include "player.hpp"
using namespace std;

#define MAP_LENGTH 12
#define MAP_WIDTH 10
#define MAP_MOVE 5
#define MAP_ENGIMON_COUNT 5

#ifndef GAMEMAP
#define GAMEMAP

class gameMap
{
public:
	gameMap();
	~gameMap();
	void printMap();
	void printLegend();
	void moveWildEngimon();
	void generateEngimon();
	void updateMap(int player_x, int player_y, int active_x, int active_y);

	void deleteTileEngimon(int x, int y);
	engimon& getTileEngimon(int x, int y);
	string findNearbyEngimon(int x, int y);
	bool isTileOccupied(int x, int y);

protected:
	tile** tile_map;

	static int engimon_count;
	static int map_move_count;
	
};
#endif