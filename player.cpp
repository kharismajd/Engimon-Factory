#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "player.hpp"
#include "engimon.hpp"
#include "skill.hpp"
#include "inventory.hpp"
using namespace std;

engimon nullEngimon;

player::player()
{
	this->name = "null";
	this->max_inventory_capacity = 50;
	this->player_x = 0;
	this->player_y = 0;
	this->activeEngimon_x = -1;
	this->activeEngimon_y = -1;
}

player::player(string name, engimon starting_engimon, int max_inventory_capacity, int player_x, int player_y, int activeEngimon_x, int activeEngimon_y)
{
	starting_engimon.setActive();

	this->name = name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = player_x;
	this->player_y = player_y;
	this->activeEngimon_x = activeEngimon_x;
	this->activeEngimon_y = activeEngimon_y;
	this->addEngimon(starting_engimon);
}

player::player(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
	this->activeEngimon_x = play.activeEngimon_x;
	this->activeEngimon_y = play.activeEngimon_y;
	copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this->engimon_inventory.contents)); 
	copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this->skill_inventory.contents)); 
}

player& player::operator=(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
	this->activeEngimon_x = play.activeEngimon_x;
	this->activeEngimon_y = play.activeEngimon_y;
	copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this->engimon_inventory.contents)); 
	copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this->skill_inventory.contents)); 
	return *this;
}

player::~player()
{
	delete &skill_inventory;
	delete &engimon_inventory;
}

engimon& player::getActiveEngimon()
{
	auto i = this->engimon_inventory.contents.begin();
	for (i = this->engimon_inventory.contents.begin(); i != this->engimon_inventory.contents.end(); ++i)
	{
		if ((*i).isActive())
		{
			break;
		}
	}

	if (i != this->engimon_inventory.contents.end())
	{
		return(*i);
	}
	else
	{
		return nullEngimon;
	}
}

void player::deleteActiveEngimon()
{
	if (this->getActiveEngimon().getName() != "null")
	{
		deleteEngimon(this->getActiveEngimon());
		this->activeEngimon_x = -1;
		this->activeEngimon_y = -1;
	}
	else
	{
		cout << "Tidak ada engimon yang sedang aktif" << endl;
	}
}

void player::moveDown()
{
	if (player_y != 9){
		int oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
		int oldPosY = player_y;
		player_y += 1;
		activeEngimon_x = oldPosX;
		activeEngimon_y = oldPosY;
	}
}

void player::moveUp()
{
	if (player_y != 0){
		int oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
		int oldPosY = player_y;
		player_y -= 1;
		activeEngimon_x = oldPosX;
		activeEngimon_y = oldPosY;
	}
}

void player::moveLeft()
{
	if (player_x != 0){
		int oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
		int oldPosY = player_y;
		player_x -= 1;
		activeEngimon_x = oldPosX;
		activeEngimon_y = oldPosY;
	}
}

void player::moveRight()
{
	if (player_x != 11){
		int oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
		int oldPosY = player_y;
		player_x += 1;
		activeEngimon_x = oldPosX;
		activeEngimon_y = oldPosY;
	}
}

void player::addEngimon(engimon engimon)
{
	if (engimon.getName() != "null")
	{
		if(!this->isInventoryFull())
		{
			this->engimon_inventory.addItem(engimon);
		}
		else
		{
			cout << "Inventory sudah penuh" << endl;
		}
	}
}

void player::deleteEngimon(engimon& engimon)
{
	this->engimon_inventory.deleteItem(engimon);
}

void player::addSkillItem(string skillName)
{
	if (skillName != "null")
	{	
		if (!this->isInventoryFull())
		{
			auto itr = skill::skill_database.begin();
			for (itr = skill::skill_database.begin(); itr != skill::skill_database.end(); ++itr)
				if ((*itr).getSkillName() == skillName)	
				{
					break;
				}
			
			if (itr == skill::skill_database.end())
			{
				throw "Error, skill tidak ditemukan";
			}
			
			this->skill_inventory.addItem((*itr));
		}
		else
		{
			cout << "Inventory sudah penuh" << endl;
		}
	}
}

void player::deleteSkillItem(string skillName)
{
	auto itr = skill::skill_database.begin();
	for (itr = skill::skill_database.begin(); itr != skill::skill_database.end(); ++itr)
		if ((*itr).getSkillName() == skillName)	
		{
			break;
		}
    
    if (itr == skill::skill_database.end())
    {
    	throw "Error, skill tidak ditemukan";
    }
	this->skill_inventory.deleteItem((*itr));
}

void player::showEngimonList()
{
	if (this->engimon_inventory.countItem() > 0)
	{
		cout << "List engimon kamu: " << endl;

		int count = 1;
		auto i = this->engimon_inventory.contents.begin();
		for (i = this->engimon_inventory.contents.begin(); i != this->engimon_inventory.contents.end(); ++i)
		{
			if ((*i).isActive())
			{
				cout << count << ". " << (*i).getName() << " (" << (*i).getSpecies() << " lv " << (*i).getLevel() << ")" << " [active]" << endl;
			}
			else
			{
				cout << count << ". " << (*i).getName() << " (" << (*i).getSpecies() << " lv " << (*i).getLevel() << ")" << endl;
			}
			count++;
		}
	}
	else
	{
		cout << "Anda tidak memiliki engimon" << endl;
	}
}

void player::showSkillItemList()
{
	if (this->skill_inventory.countItem() > 0)
	{
		cout << "List skill item kamu: " << endl;

		int count = 1;
		auto i = this->skill_inventory.contents.begin();
		for (i = this->skill_inventory.contents.begin(); i != this->skill_inventory.contents.end(); ++i)
		{
			cout << count << ". " << (*i).getSkillName() << " (" << (*i).getAmountInInventory() << ")" << endl;
		}
	}
	else
	{
		cout << "Anda tidak memiliki skill item" << endl;
	}
}

void player::deleteEngimonSelect()
{
	unsigned int i;
	if (this->engimon_inventory.countItem() > 0) {
		this->showEngimonList();
		cout << "Ketik nomor engimon yang ingin dibuang: ";
		cin >> i;
		if (i >= 1 && i <= this->engimon_inventory.contents.size())
		{
			deleteEngimon(this->engimon_inventory.contents[i-1]);
		}
	}
	else
	{
		cout << "Anda tidak memiliki engimon" << endl;
	}
}

void player::showEngimonDetails()
{
	unsigned int i;
	if (this->engimon_inventory.countItem() > 0) {
		this->showEngimonList();
		cout << "Ketik nomor yang ada untuk melihat detail engimon: ";
		cin >> i;
		if (i >= 1 && i <= this->engimon_inventory.contents.size())
		{
			this->engimon_inventory.contents[i-1].showAttributes();
		}
	}
	else
	{
		cout << "Anda tidak memiliki engimon" << endl;
	}
}

void player::switchOutEngimon()
{
	unsigned int i;
	if (this->engimon_inventory.countItem() > 0) {
		this->showEngimonList();
		cout << "Ketik nomor engimon yang ingin dibuat aktif: ";
		cin >> i;
		if (i >= 1 && i <= this->engimon_inventory.contents.size())
		{
			if (getActiveEngimon().getName() != "null"){
				if (this->engimon_inventory.contents[i-1].isActive()){
					cout << "Engimon tersebut sedang aktif" << endl;
				}
				else{
					cout << "Replace active Engimon " << getActiveEngimon().getName() << " dengan Engimon " << this->engimon_inventory.contents[i-1].getName() << endl;
					getActiveEngimon().setInactive();
					this->engimon_inventory.contents[i-1].setActive();
				}
			}
			else{
				this->engimon_inventory.contents[i-1].setActive();
				cout << "Set " << this->engimon_inventory.contents[i-1].getName() << " sebagai active Engimon" << endl;
				if (player_x != 0){
					activeEngimon_x = player_x - 1;
					activeEngimon_y = player_y;
				}
				else{
					activeEngimon_x = player_x + 1;
					activeEngimon_y = player_y;
				}
			}
		}
	}
	else
	{
		cout << "Anda tidak memiliki engimon" << endl;
	}
}

void player::interact()
{
	this->getActiveEngimon().cry();
}

void player::useSkillItem()				//////// useSkillItem belum sepenuhnya benar, pakai try & catch agar tidak ada kondisi skill tetap dihapus meskipun tidak terpakai karena masuk throw
{
	unsigned int i;
	unsigned int j;
	if (this->skill_inventory.countItem() > 0 && this->engimon_inventory.countItem() > 0 ) {
		this->showSkillItemList();
		cout << "Ketik nomor skill yang ingin dipakai: ";
		cin >> i;
		if (i >= 1 && i <= this->skill_inventory.contents.size())
		{
			this->showEngimonList();
			cout << "Ketik nomor engimon yang ingin learn skill " << this->skill_inventory.contents[i-1].getSkillName() << " : ";
			cin >> j;
			if (j >= 1 && j <= this->engimon_inventory.contents.size())
			{
				/*
				try
				{
					e.learnMove("tackle")
				}

				catch (int e)
				{
				//handler
				}
				*/
				this->engimon_inventory.contents[j-1].learnMove(this->skill_inventory.contents[i-1].getSkillName());
				deleteSkillItem(this->skill_inventory.contents[i-1].getSkillName());
			}
		}
	}
	else
	{
		cout << "Anda harus memiliki minimal 1 engimon dan item skill" << endl;
	}
}

bool player::isInventoryFull()
{
	return (this->engimon_inventory.countItem() + this->skill_inventory.countItem() >= this->max_inventory_capacity);
}

// Posisi activeEngimon_x
int player::getActivePetPosX() {return activeEngimon_x;}
// Posisi activeEngimon_y
int player::getActivePetPosY() {return activeEngimon_y;}
// Posisi activeEngimon_x
int player::getPlayerPosX() {return player_x;}
// Posisi activeEngimon_y
int player::getPlayerPosY() {return player_y;}

//Nama player
string player::getName() {return name;}