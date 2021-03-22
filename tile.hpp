#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
using namespace std;

#ifndef TILE
#define TILE
class tile
{
public:
	tile();
	~tile();
	
protected:
	engimon wild;
	char symbol;
	string tile_type;
	bool isPlayer;
	
};
#endif