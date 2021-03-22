#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "skill.hpp"
using namespace std;

#ifndef PLAYER
#define PLAYER

class player
{
public:
	player();
	player(string name, engimon starting_engimon, int max_inventory_capacity, int player_x, int player_y, int active_engimon_x, int active_engimon_y);
	player(const player& play);
	player& operator=(const player& play);
	~player();

	void moveUp();
	void moveDown();
	void moveLeft();
	void moveRight();

	void addEngimon(engimon);
	void deleteEngimon(engimon);
	void addSkillItem(string);
	void deleteSkillItem(string);

	void showEngimonDetails();
	void showEngimonList();
	void showSkillItemList();

	void switchOutEngimon(); 
	void interact();

	void useSkillItem();
	
	//breeding()
	//battle()

	bool isInventoryFull();
protected:
	string name;
	engimon* active_engimon;
	int max_inventory_capacity;
	int player_x;
	int player_y;
	int active_engimon_x;
	int active_engimon_y;

	inventory<engimon> engimon_inventory;
	inventory<skill> skill_inventory;
};

#endif

#ifndef INVENTORY
#define INVENTORY

template<class T>
class inventory
{
public:
	inventory();
	inventory(const inventory& inv);
	inventory& operator=(const inventory& inv);
	~inventory();

	friend class player;
private:
	void addItem(T);
	void deleteItem(T);
	int countItem();

	vector<T> contents;
};
#endif