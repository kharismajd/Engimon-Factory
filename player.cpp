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
}

player::player(string name, engimon starting_engimon, int max_inventory_capacity, int player_x, int player_y, int active_engimon_x, int active_engimon_y)
{
	starting_engimon.setActive();

	this->name = name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = player_x;
	this->player_y = player_y;
	this->addEngimon(starting_engimon);
}

player::player(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
	copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this->engimon_inventory.contents)); 
	copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this->skill_inventory.contents)); 
}

player& player::operator=(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
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
	}
	else
	{
		cout << "Tidak ada engimon yang sedang aktif" << endl;
	}
}

void player::moveUp()
{
	//TODO
}

void player::moveDown()
{
	//TODO
}

void player::moveLeft()
{
	//TODO
}

void player::moveRight()
{
	//TODO
}

void player::addEngimon(engimon engimon)
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

void player::deleteEngimon(engimon& engimon)
{
	this->engimon_inventory.deleteItem(engimon);
}

void player::addSkillItem(string skillName)
{
	
	if(!this->isInventoryFull())
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
	int i;
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
	int i;
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
	//TODO
	//showengimonlist -> pilih engimon di engimon_inventory -> set engimon yang dipilih jadi aktif
	//->set active_engimon jadi ngga aktif
}

void player::interact()
{
	this->getActiveEngimon().cry();
}

void player::useSkillItem()
{
	//TODO
	//showSkillItemList -> pilih skillitem yang ingin dipakai -> showengimonlist -> pilih engimon yang ingin learn
	//cek kalau elemen kompatibel -> kalau kompatibel, learnMove dan deleteSkillItem

}

bool player::isInventoryFull()
{
	return (this->engimon_inventory.countItem() + this->skill_inventory.countItem() >= this->max_inventory_capacity);
}