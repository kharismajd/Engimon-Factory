#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "tile.hpp"
#include "gameMap.hpp"
#include <stdio.h>      
#include <stdlib.h>
#include <time.h>

int gameMap::map_move_count = MAP_MOVE;
int gameMap::engimon_count = 0;

gameMap::gameMap()
{
	tile_map = new tile*[MAP_WIDTH];
	for (int k = 0; k < MAP_WIDTH; ++k)
	{
		tile_map[k] = new tile[MAP_LENGTH];
	}


	engimon nullEngimon;
	for (int i = 0; i < MAP_WIDTH; ++i)
	{
		for (int j = 0; j < MAP_LENGTH	; ++j)
		{
			if (i < MAP_LENGTH/2 && j > MAP_WIDTH/2)
			{
				tile_map[i][j] = tile(i,j,nullEngimon,"sea");
			}
			else
			{
				tile_map[i][j] = tile(i,j,nullEngimon,"grassland");
			}
			
		}
	}
}
gameMap::~gameMap()
{
	for (int i = 0; i < MAP_WIDTH; ++i)
	{
		delete[] tile_map[i];
	}
	delete[] tile_map;
}

void gameMap::printMap()
{
	for (int i = 0; i < MAP_WIDTH; ++i)
	{
		for (int j = 0; j < MAP_LENGTH	; ++j)
		{
			tile_map[i][j].printTile();
			cout << " ";
		}
		cout << endl;
	}
}
void gameMap::printLegend()
{
	cout << "Legend:" << endl;
	cout << "W/w: Water engimon" << endl;
	cout << "I/i : Ice engimon" << endl;
	cout << "F/f: Fire engimon" << endl;
	cout << "G/g: Ground engimon" << endl;
	cout << "E/e: Electric engimon" << endl;
	cout << "L/l: Fire/Electric engimon" << endl;
	cout << "S/s: Water/Ice engimon" << endl;
	cout << "N/n: Water/Ground engimon" << endl;

	cout << endl;
	cout << "P: Player" << endl;
	cout << "X: Active Engimon" << endl;

	cout << endl;
	cout << "- : Grassland" << endl;
	cout << "o : Sea" << endl;
}

void moveTileEngimon(tile &tile1, tile &tile2)
{
	/*Menggerakan pokemon dari tile1 ke tile2*/

	if (tile2.haveWildEngimon() == false && tile2.isTileValid(tile1.getEngimon()) && tile2.isPlayerHere() == false && tile2.isactiveEngimonHere() == false)
	{
		engimon nullEngimon;

		tile2.setEngimon(tile1.getEngimon());
		tile1.setEngimon(nullEngimon);
	}

}

void gameMap::moveWildEngimon()
{
	/*Random Module*/
	srand(tile::random_seed);
	tile::random_seed++;
	int randomDirection;
	randomDirection = rand() %  4;
	srand(tile::random_seed);
	tile::random_seed++;

	int i, j;

	/*First pass, find all empty tiles*/
	for (i = 0; i < MAP_WIDTH; ++i)
	{
		for (j = 0; j < MAP_LENGTH; ++j)
		{
			if (tile_map[i][j].haveWildEngimon() == false)
			{
				tile_map[i][j].passed();
			}
		}
	}

	/*Second pass, move semua tile yang ada pokemon*/
	for (i = 0; i < MAP_WIDTH; ++i)
	{
		for (j = 0; j < MAP_LENGTH	; ++j)
		{
			if (tile_map[i][j].haveWildEngimon() && tile_map[i][j].isPass() == false)
			{
				// cout << j << "," << i << " ";

				// tile_map[i][j].getEngimon().showAttributes();
				// cout << endl;
				
				if (randomDirection == 0 && i > 0) //up
				{
					moveTileEngimon(tile_map[i][j], tile_map[i-1][j]);
					// cout << "move up" << endl;
				}
				else if (randomDirection == 1 && j > 0)//left
				{
					moveTileEngimon(tile_map[i][j], tile_map[i][j-1]);
					// cout << "move left" << endl;
				}
				else if (randomDirection == 2 && j < MAP_LENGTH - 1)//right
				{
					moveTileEngimon(tile_map[i][j], tile_map[i][j+1]);
					// cout << "move right" << endl;
				}
				else
				{
					if (randomDirection == 3 && i < MAP_WIDTH - 1)//down
					{
						moveTileEngimon(tile_map[i][j], tile_map[i+1][j]);
						// cout << "move down" << endl;
					}
				}
				tile_map[i][j].passed();
				randomDirection = rand() %  4;
				srand(time(NULL));
			}
		}
	}

	for (i = 0; i < MAP_WIDTH; ++i)
	{
		for (j = 0; j < MAP_LENGTH; ++j)
		{
			tile_map[i][j].resetPass();
		}
	}

}
void gameMap::generateEngimon()
{
	/*Random Module*/
	int seed = time(NULL);
	while(gameMap::engimon_count < MAP_ENGIMON_COUNT)
	{
		srand(seed);
		int randomX, randomY;
		randomX = rand() %  MAP_LENGTH;
		srand(seed);
		randomY = rand() % MAP_WIDTH;

		//cout << randomX << "," << randomY << endl;

		if (tile_map[randomY][randomX].haveWildEngimon() == false)
		{
			tile_map[randomY][randomX].spawn();
			gameMap::engimon_count++;
		}
		seed++;
	}
}
	
void gameMap::updateMap(int player_x, int player_y, int active_x, int active_y)
{
	/*y itu baris, x itu column*/
	for (int i = 0; i < MAP_WIDTH; ++i)
	{
		for (int j = 0; j < MAP_LENGTH; ++j)
		{
			tile_map[i][j].playerIsNotHere();
			tile_map[i][j].activeEngimonIsNotHere();
		}
	}

	if (tile_map[active_y][active_x].haveWildEngimon())
	{
		throw "Active Engimon menabrak Wild Engimon";
	}

	this->tile_map[player_y][player_x].playerIsHere();
	this->tile_map[active_y][active_x].activeEngimonIsHere();
	gameMap::map_move_count--;

	if (gameMap::map_move_count <= 0)
	{
		moveWildEngimon();
		gameMap::map_move_count = 5;
	}
	this->tile_map[player_y][player_x].playerIsHere();
	this->tile_map[active_y][active_x].activeEngimonIsHere();
}

void gameMap::deleteTileEngimon(int x, int y)
{
	engimon nullEngimon;
	if (!(tile_map[y][x].isPlayerHere() || tile_map[y][x].isactiveEngimonHere()) && tile_map[y][x].haveWildEngimon())
	{
		this->tile_map[y][x].setEngimon(nullEngimon);
		gameMap::engimon_count--;
		if (gameMap::engimon_count < 3)
		{
			generateEngimon();
		}
	}
	
}