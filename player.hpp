#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "skill.hpp"
#include "inventory.hpp"
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

	void moveUp();
	void moveDown();
	void moveLeft();
	void moveRight();

	// Menambahkan engimon ke engimon_inventory
	void addEngimon(engimon);
	// Menghapus engimon yang direferensi di inventory
	void deleteEngimon(engimon&);
	// Menambahkan skill item ke skill_inventory
	void addSkillItem(string);
	// Menghapus skill item di skill_inventory
	void deleteSkillItem(string);

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
	
	//breeding()
	//battle()

	// Mengecek jika inventory full
	bool isInventoryFull();
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