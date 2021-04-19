#include <iostream>
#include <fstream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "tile.hpp"
#include "gameMap.hpp"
#include <stdio.h>      
#include <stdlib.h>
#include <time.h>


gameMap::gameMap()
{
	tile_map = new tile*[this->mapWidth];
	for (int k = 0; k < this->mapWidth; ++k)
	{
		tile_map[k] = new tile[this->mapLength];
	}


	engimon nullEngimon;
	for (int i = 0; i < this->mapWidth; ++i)
	{
		for (int j = 0; j < this->mapLength	; ++j)
		{
			if (i < this->mapLength/2 && j > this->mapWidth/2)
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

gameMap::gameMap(string externalFile)
{
	ifstream f(externalFile);

	if (!f.is_open())
	{
		throw "File tidak dibisa dibuka atau tidak ada";
	}
	int charCount = 0;
	int baris, kolom;
	char c;
	f >> baris;
	f >> kolom;

	while (f >> c)
	{
		charCount++;
	}

	if (charCount != baris*kolom)
	{
		throw "Input file salah, periksa ulang";
	}

	f = ifstream(externalFile);
	f >> baris;
	f >> kolom;
	this->mapLength = kolom;
	this->mapWidth = baris;

	tile_map = new tile*[this->mapWidth];
	for (int k = 0; k < this->mapWidth; ++k)
	{
		tile_map[k] = new tile[this->mapLength];
	}


	engimon nullEngimon;
	for (int i = 0; i < this->mapWidth; ++i)
	{
		for (int j = 0; j < this->mapLength	; ++j)
		{
			f >> c;
			if (c == 'o')
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
	for (int i = 0; i < this->mapWidth; ++i)
	{
		delete[] tile_map[i];
	}
	delete[] tile_map;
}

void gameMap::printMap()
{
	for (int i = 0; i < this->mapWidth; ++i)
	{
		for (int j = 0; j < this->mapLength	; ++j)
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
	for (i = 0; i < this->mapWidth; ++i)
	{
		for (j = 0; j < this->mapLength; ++j)
		{
			if (tile_map[i][j].haveWildEngimon() == false)
			{
				tile_map[i][j].passed();
			}
		}
	}

	/*Second pass, move semua tile yang ada pokemon*/
	for (i = 0; i < this->mapWidth; ++i)
	{
		for (j = 0; j < this->mapLength	; ++j)
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
				else if (randomDirection == 2 && j < this->mapLength - 1)//right
				{
					moveTileEngimon(tile_map[i][j], tile_map[i][j+1]);
					// cout << "move right" << endl;
				}
				else
				{
					if (randomDirection == 3 && i < this->mapWidth - 1)//down
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

	for (i = 0; i < this->mapWidth; ++i)
	{
		for (j = 0; j < this->mapLength; ++j)
		{
			tile_map[i][j].resetPass();
		}
	}

}
void gameMap::generateEngimon()
{
	/*Random Module*/
	int seed = time(NULL);
	while(engimon_count < MAP_ENGIMON_COUNT)
	{
		srand(seed);
		int randomX, randomY;
		randomX = rand() %  this->mapLength;
		srand(seed);
		randomY = rand() % this->mapWidth;

		//cout << randomX << "," << randomY << endl;

		if (tile_map[randomY][randomX].haveWildEngimon() == false)
		{
			tile_map[randomY][randomX].spawn();
			engimon_count++;
		}
		seed++;
	}
}
	
void gameMap::updateMap(int player_x, int player_y, int active_x, int active_y)
{
	/*y itu baris, x itu column*/
	for (int i = 0; i < this->mapWidth; ++i)
	{
		for (int j = 0; j < this->mapLength; ++j)
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
	map_move_count--;

	if (map_move_count <= 0)
	{
		moveWildEngimon();
		map_move_count = 5;
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
		engimon_count--;
		if (engimon_count < 3)
		{
			generateEngimon();
		}
	}
	
}


engimon& gameMap::getTileEngimon(int x, int y)
{
	return tile_map[y][x].getEngimon();
}
string gameMap::findNearbyEngimon(int x, int y)
{
	if (y > 0 && tile_map[y-1][x].haveWildEngimon())
	{
		return "up";
	}
	else if (x > 0 && tile_map[y][x-1].haveWildEngimon())
	{
		return "left";
	}
	else if (y < this->mapWidth-1 &&tile_map[y+1][x].haveWildEngimon())
	{
		return "up";
	}
	else if (x < this->mapLength-1 && tile_map[y][x+1].haveWildEngimon())
	{
		return "right";
	}
	else if (tile_map[y][x].haveWildEngimon())
	{
		return "center";
	}
	else
	{
		return "null";
	}
}

bool gameMap::isTileOccupied(int x, int y)
{
	return (tile_map[y][x].haveWildEngimon());
}

int gameMap::getMapLength()
{
	return mapLength;
}
int gameMap::getMapWidth()
{
	return mapWidth;
}