#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "skill.hpp"
#include "inventory.hpp"
#include "breeder.hpp"
using namespace std;

#ifndef PLAYER
#define PLAYER

class player
{
public:
	player();
	player(string name, engimon starting_engimon, int max_inventory_capacity, int player_x, int player_y, int activeEngimon_x, int activeEngimon_y);
	player(const player& play);
	player& operator=(const player& play);
	~player();

	//Mengambil engimon yang sedang aktif. Jika tidak ada, mengembalikan nullEngimon.
	engimon& getActiveEngimon();
	//Menghapus engimon yang sedang aktif. Jika tidak ada, mengeluarkan pesan.
	void deleteActiveEngimon();

	void moveUp(int mapLength, int mapWidth);
	void moveDown(int mapLength, int mapWidth);
	void moveLeft(int mapLength, int mapWidth);
	void moveRight(int mapLength, int mapWidth);

	// Menambahkan engimon ke engimon_inventory
	void addInventoryContent(engimon);
	// Menghapus engimon yang direferensi di inventory
	void deleteInventoryContent(engimon&);
	// Menambahkan skill item ke skill_inventory
	void addInventoryContent(string);
	// Menghapus skill item di skill_inventory
	void deleteInventoryContent(string);

	// Memperlihatkan list engimon di inventory
	void showEngimonList();
	// Memperlihatkan list skill item di inventory
	void showSkillItemList();

	// Menghapus engimon yang dipilih
	void deleteEngimonSelect();
	// Memperlihatkan detail engimon yang dipilih
	void showEngimonDetails();
	// Mengganti pokemon yang aktif
	void switchOutEngimon(); 
	
	// Berinteraksi dengan engimon yang sedang aktif
	void interact();
	// Menggunakan skill item
	void useSkillItem();
	// Melakukan breeding
	void breeding();

	// Mengecek jika inventory full
	bool isInventoryFull();

	int getActivePetPosX();
	int getActivePetPosY();
	void setActivePetPosX(int);
	void setActivePetPosY(int);

	int getPlayerPosX();
	int getPlayerPosY();
	void setPlayerPosX(int);
	void setPlayerPosY(int);

	// Mengambil name
	string getName();

protected:
	string name;
	int max_inventory_capacity;
	int player_x;
	int player_y;
	int activeEngimon_x;
	int activeEngimon_y;

	inventory<engimon> engimon_inventory;
	inventory<skill> skill_inventory;
};

#endif