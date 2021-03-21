#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "player.hpp"
#include "engimon.hpp"
#include "skill.hpp"

player::player()
{
	this->name = "null";
	this->active_engimon = NULL;
	this->max_inventory_capacity = 50;
	this->player_x = 0;
	this->player_y = 0;
	this->active_engimon_x = 1;
	this->active_engimon_y = 0;
}

player::player(string name, engimon starting_engimon, int max_inventory_capacity, int player_x, int player_y, int active_engimon_x, int active_engimon_y)
{
	starting_engimon.setActive();

	this->name = name;
	this->addEngimon(starting_engimon);
	this->active_engimon = &engimon_inventory.contents[0];
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = player_x;
	this->player_y = player_y;
	this->active_engimon_x = active_engimon_x;
	this->active_engimon_y = active_engimon_y;
}

player::player(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
	this->active_engimon_x = play.active_engimon_x;
	this->active_engimon_y = play.active_engimon_y;
	copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this->engimon_inventory.contents)); 
	copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this->skill_inventory.contents)); 
	
	auto i = this->engimon_inventory.contents.begin();
	for (i = this->engimon_inventory.contents.begin(); i != this->engimon_inventory.contents.end(); ++i)
	{
		if ((*i).isActive())
		{
			this->active_engimon = (&*i);
			break;
		}
	}
	if (i == this->engimon_inventory.contents.end())
	{
		this->active_engimon = NULL;
	}
}

player& player::operator=(const player& play)
{
	this->name = play.name;
	this->max_inventory_capacity = max_inventory_capacity;
	this->player_x = play.player_x;
	this->player_y = play.player_y;
	this->active_engimon_x = play.active_engimon_x;
	this->active_engimon_y = play.active_engimon_y;
	copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this->engimon_inventory.contents)); 
	copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this->skill_inventory.contents)); 
	
	auto i = this->engimon_inventory.contents.begin();
	for (i = this->engimon_inventory.contents.begin(); i != this->engimon_inventory.contents.end(); ++i)
	{
		if ((*i).isActive())
		{
			this->active_engimon = (&*i);
			break;
		}
	}
	if (i == this->engimon_inventory.contents.end())
	{
		this->active_engimon = NULL;
	}
	return *this;
}

player::~player()
{
	delete &skill_inventory;
	delete &engimon_inventory;
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

void player::deleteEngimon(engimon engimon)
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

void player::showEngimonDetails() //Cuma untuk engimon yang lagi aktif, nanti ditambah
{
	this->active_engimon->showAttributes();
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
				cout << count << ". " << (*i).getName() << "(" << (*i).getSpecies() << " lv " << (*i).getLevel() << ")" << " [active]" << endl;
			}
			else
			{
				cout << count << ". " << (*i).getName() << "(" << (*i).getSpecies() << " lv " << (*i).getLevel() << ")" << endl;
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

void player::switchOutEngimon()
{
	//TODO
	//showengimonlist -> pilih engimon di engimon_inventory -> set engimon yang dipilih jadi aktif
	//->set active_engimon jadi ngga aktif -> ubah pointer active_engimon ke engimon yang dipilih tadi
}

void player::interact()
{
	this->active_engimon->cry();
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

template<class T>
inventory<T>::inventory() 
{
	//Inventory kosong
}

template<class T>
inventory<T>::inventory(const inventory<T>& inv)
{
	copy(inv.contents.begin(), inv.contents.end(), back_inserter(this->contents)); 
}

template<class T>
inventory<T>& inventory<T>::operator=(const inventory<T>& inv)
{
	copy(inv.contents.begin(), inv.contents.end(), back_inserter(this->contents)); 
	return *this;
}

template<class T>
inventory<T>::~inventory()
{
	this->contents.clear();
	vector<T>().swap(this->contents);
}

void inventory<engimon>::addItem(engimon engimon)
{
	this->contents.push_back(engimon);
}

void inventory<engimon>::deleteItem(engimon engimon)
{
	auto i = this->contents.begin();
	for (i = this->contents.begin(); i != this->contents.end(); ++i)
	{
		if (&engimon == (&*i))
		{
			break;
		}
	}
	if (i != this->contents.end())
	{
		this->contents.erase(i);
	}
	else
	{
		throw "Error, tidak ada engimon tersebut dalam inventory";
	}
}

int inventory<engimon>::countItem()
{
	return this->contents.size();
}

void inventory<skill>::addItem(skill skill)
{
	auto i = this->contents.begin();
	for (i = this->contents.begin(); i != this->contents.end(); ++i)
	{
		if (skill.getSkillName() == (*i).getSkillName())
		{
			skill.setAmountInInventory(skill.getAmountInInventory() + 1);
			break;
		}
	}

	if (i != this->contents.end())
	{
		skill.setAmountInInventory(1);
		this->contents.push_back(skill);
	}
}

void inventory<skill>::deleteItem(skill skill)
{
	auto i = this->contents.begin();
	for (i = this->contents.begin(); i != this->contents.end(); ++i)
	{
		if (skill.getSkillName() == (*i).getSkillName())
		{
			break;
		}
	}
	if (i != this->contents.end())
	{
		(*i).setAmountInInventory((*i).getAmountInInventory() - 1);
		if ((*i).getAmountInInventory() <= 0) 
		{
			this->contents.erase(i);
		}
	}
	else
	{
		throw "Error, tidak ada skill tersebut dalam inventory";
	}
}

int inventory<skill>::countItem()
{
	int count = 0;
	auto i = this->contents.begin();
	for (i = this->contents.begin(); i != this->contents.end(); ++i)
	{
		count += (*i).getAmountInInventory();
	}
	return count;
}