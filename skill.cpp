#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "skill.hpp"
using namespace std;

vector<skill> skill::skill_database;

skill::skill()
{
	this->skillName = "null";
	this->base_power = 0;
	this->mastery_level = 0;
	this->amount_in_inventory = 0;
}

skill::skill(string nm, int bp, int mastery_lvl, string elem[5], string engimon_name /*Isi "0", jika tidak unik*/)
{
	this->skillName = nm;
	this->base_power = bp;
	this->mastery_level = mastery_lvl;
	this->amount_in_inventory = 0;

	//Elements yang dapat mempelajari skill ini
	//Hanya bisa berupa Fire, Water, Electric, Ground, Ice

	int i;
	for (i = 0; i < 5; ++i)
	{
		if (elem[i] == "Fire" || elem[i] == "Water" || elem[i] == "Electric" || elem[i] == "Ground" || elem[i] == "Ice")
		{
			this->elements.push_back(elem[i]);
		}
	}
	//JIka ada value nama engimon, maka hanya bisa dipelajari engimon tersebut
	//Jika tidak unique, isi 0
	if (engimon_name != "0")
	{
		engimon_unique = engimon_name;
	}
}
skill::~skill()
{
	//Tidak perlu diimplementasikan
}


string skill::getSkillName()
{
	return skillName;
}

string skill::getEngimonSpecies()
{
	return this->engimon_unique;
}

int skill::getAmountInInventory() 
{
	return amount_in_inventory;
}

vector<string> skill::getElements()
{
	return elements;
}

int skill :: getBasePower(){
	return this->base_power;
}

int skill :: getMasteryLv(){
	return this->mastery_level;
}

void skill::setMasteryLv(int i)
{
	this->mastery_level = i;
}


void skill::printAll()
{
	cout << "Name: " << skillName << endl;
	cout << "Base Power: " << base_power << endl;
	cout << "Mastery level: " << mastery_level << endl;
	cout << "Elements: ";
	for (auto i = this->elements.begin(); i != elements.end(); ++i)
	{
		cout << *i << " ";
	}
	cout << endl;

	if (engimon_unique != "0")
	{
		cout << "Move is unique to " << engimon_unique << endl;
	}
	cout << endl;

}

void skill::setAmountInInventory(int amount)
{
	amount_in_inventory = amount;
}

void skill::InitializeSkillDatabase()
{
	string element_tackle[5] = {"Fire", "Water", "Electric", "Ground", "Ice"};
	skill tackle("tackle", 50, 1, element_tackle, "0");
	skill_database.push_back(tackle);

	string element_ember[5] = {"Fire"};
	skill ember("ember", 80, 1, element_ember, "0");
	skill_database.push_back(ember);

	string element_bubble[5] = {"Water"};
	skill bubble("bubble", 80, 1, element_bubble, "0");
	skill_database.push_back(bubble);

	string element_rock_smash[5] = {"Ground"};
	skill rock_smash("rock_smash", 80, 1, element_rock_smash, "0");
	skill_database.push_back(rock_smash);

	string element_thunderbolt[5] = {"Electric"};
	skill thunderbolt("thunderbolt", 80, 1, element_thunderbolt, "0");
	skill_database.push_back(thunderbolt);

	string element_ice_beam[5] = {"Ice"};
	skill ice_beam("ice_beam", 80, 1, element_ice_beam, "0");
	skill_database.push_back(ice_beam);

	string element_charred[5] = {"Fire"};
	skill charred("charred", 80, 1, element_charred, "Charmander");
	skill_database.push_back(charred);

	string element_splash[5] = {"Water"};
	skill splash("splash", 5, 1, element_splash, "Magikarp");
	skill_database.push_back(splash);

	string element_electrified_tail[5] = {"Electric"};
	skill electrified_tail("electrified_tail", 80, 1, element_electrified_tail, "Pikachu");
	skill_database.push_back(electrified_tail);

	string element_rock_punch[5] = {"Ground"};
	skill rock_punch("rock_punch", 80, 1, element_rock_punch, "Geodude");
	skill_database.push_back(rock_punch);

	string element_snowball[5] = {"Ice"};
	skill snowball("snowball", 80, 1, element_snowball, "Glastrier");
	skill_database.push_back(snowball);

	string element_overload[5] = {"Fire", "Electric"};
	skill overload("overload", 80, 1, element_overload, "Vulpichu");
	skill_database.push_back(overload);

	string element_ice_breath[5] = {"Water", "Ice"};
	skill ice_breath("ice_breath", 80, 1, element_ice_breath, "Lapras");
	skill_database.push_back(ice_breath);

	string element_mudball[5] = {"Water", "Ground"};
	skill mudball("mudball", 80, 1, element_mudball, "Wooper");
	skill_database.push_back(mudball);

}