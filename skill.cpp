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
}

skill::skill(string nm, int bp, int mastery_lvl, string elem[5], string engimon_name /*Isi "0", jika tidak unik*/)
{
	this->skillName = nm;
	this->base_power = bp;
	this->mastery_level = mastery_lvl;

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
vector<string> skill::getElements()
{
	return elements;
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

void skill::InitializeSkillDatabase()
{
	string element_tackle[5] = {"Fire", "Water", "Electric", "Ground", "Ice"};
	skill tackle("tackle", 50, 0, element_tackle, "0");
	skill_database.push_back(tackle);

	string element_ember[5] = {"Fire"};
	skill ember("ember", 80, 0, element_ember, "0");
	skill_database.push_back(ember);

	string element_bubble[5] = {"Water"};
	skill bubble("bubble", 80, 0, element_bubble, "0");
	skill_database.push_back(bubble);
}