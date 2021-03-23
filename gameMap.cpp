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
}

void moveTileEngimon(tile &tile1, tile &tile2)
{
	/*Menggerakan pokemon dari tile1 ke tile2*/

	if (tile2.haveWildPokemon() == false && tile2.isTileValid(tile1.getEngimon()))
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
			if (tile_map[i][j].haveWildPokemon() == false)
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
			if (tile_map[i][j].haveWildPokemon() && tile_map[i][j].isPass() == false)
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

		cout << randomX << "," << randomY << endl;

		if (tile_map[randomY][randomX].haveWildPokemon() == false)
		{
			tile_map[randomY][randomX].spawn();
			gameMap::engimon_count++;
		}
		seed++;
	}
}
	
