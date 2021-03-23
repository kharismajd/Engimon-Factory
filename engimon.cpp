#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include <vector>
#include <algorithm>
#include <map>
#include "engimon.hpp"
using namespace std;

set<tuple<string,string,string>> engimon::all_species;
map<string,string> engimon::engimon_cry;
engimon::engimon()//Null
{
	name = name_null;
	parent1 = this;
	parent2 = this;
	species = "null";
	moves = new skill[4];
	skill nullSkill;
	for (int i = 0; i < 4; ++i)
	{
		moves[i] = nullSkill;
	}
	element1 = "null";
	element2 = "null";
	level = 0;
	experience = 0;
	cummulative_experience = 0;
	active = false;
}
engimon::engimon(string nm, engimon* pr1, engimon* pr2, string sp, int lvl, int exp)
{
	name = nm;
	parent1 = pr1;
	parent2 = pr2;
	species = sp;

	auto i = all_species.begin();
	for (i = all_species.begin(); i != all_species.end(); ++i)
	{
		if (sp == get<0>(*i))
		{
			break;
		}
	}

	if (i == all_species.end())
	{
		throw "Error, tidak ketemu species";
	}

	moves = new skill[4];

	auto j = skill::skill_database.begin();
	for (j = skill::skill_database.begin(); j != skill::skill_database.end(); ++j)
	{
		if ((*j).getEngimonSpecies() == sp)
		{
			break;
		}
	}
	if (j == skill::skill_database.end())
	{
		throw "Skill engimon tidak ditemukan";
	}
	moves[0] = (*j);

	skill nullSkill;
	for (int j = 1; j < 4; ++j)
	{
		moves[j] = nullSkill;
	}

	element1 = get<1>(*i);
	element2 = get<2>(*i);
	level = lvl;
	experience = exp;
	cummulative_experience = (lvl-1)*100 + exp;
	active = false;
}

engimon::engimon(const engimon& e)
{
	this->name = e.name;
	this->parent1 = e.parent1;
	this->parent2 = e.parent2;
	this->species = e.species;

	this->moves = new skill[4];
	for (int i = 0; i < 4; ++i)
	{
		this->moves[i] = e.moves[i];
	}
	this->element1 = e.element1;
	this->element2 = e.element2;
	this->level = e.level;
	this->experience = e.experience;
	this->cummulative_experience = e.cummulative_experience;
	this->active = e.active;
}
engimon& engimon::operator=(const engimon& e)
{
	delete[] moves;
	this->name = e.name;
	this->parent1 = e.parent1;
	this->parent2 = e.parent2;
	this->species = e.species;

	this->moves = new skill[4];
	for (int i = 0; i < 4; ++i)
	{
		this->moves[i] = e.moves[i];
	}
	this->element1 = e.element1;
	this->element2 = e.element2;
	this->level = e.level;
	this->experience = e.experience;
	this->cummulative_experience = e.cummulative_experience;
	this->active = e.active;
	return *this;
}

engimon::~engimon()
{
	delete[] moves;
}

void engimon::showAttributes()
{
	cout << "Engimon name: " << name << endl;
	if ((*parent1).getName() != name_null)
	{
		cout << "Parent 1: " <<(*parent1).getName() << endl;
	}

	if ((*parent2).getName() != name_null)
	{
		cout << "Parent 2: " << (*parent2).getName() << endl;
 	}
	cout << "Species: " << species << endl;

	cout << "Element: " << element1;

	if (element2 != "null")
	{
		cout << "/" << element2 << endl;
	}
	else
	{
		cout << endl;
	}

	printMoves();

	cout << "Level: " << level << endl;
	cout << "Experience: " << experience << endl;
	cout << "Cummulative Experience: " << cummulative_experience << endl;
}

void engimon::printMoves()
{
	cout << "Moves: " << endl;
	for (int i = 0; i < 4; ++i)
	{
		if (moves[i].getSkillName() != string_null)
		{
			cout << "- " << moves[i].getSkillName() << endl;
		}
		
	}
}
void engimon::gainExp(int exp)
{
	experience = exp;
	if (experience > 100)
	{
		level = experience/100+ level;
		experience = experience % 100;
	}

	cummulative_experience = (level-1)*100 + exp;
}
void engimon::learnMove(string move)
{
	auto itr = skill::skill_database.begin();
	for (itr = skill::skill_database.begin(); itr != skill::skill_database.end(); ++itr)
		if ((*itr).getSkillName() == move)	
		{
			break;
		}
    
    if (itr == skill::skill_database.end())
    {
    	throw "Error, skill tidak ditemukan";
    }

    if ((*itr).getEngimonSpecies() != this->species && (*itr).getEngimonSpecies() != "0")
    {
    	throw "Error, engimon tidak sesuai";
    }

    vector<string> move_elements = (*itr).getElements();

    auto element1_checker = find(move_elements.begin(), move_elements.end(), this->element1);
    auto element2_checker = find(move_elements.begin(), move_elements.end(), this->element2);

    if (element1_checker == move_elements.end() && element2_checker == move_elements.end())
    {
    	throw "Error, skill tidak compatible dengan elemen engimon";
    }

    int i = 0;
    for (i = 0; i < 4; ++i)
    {
    	if (this->moves[i].getSkillName() == move)
    	{
    		throw "Error, skill sudah ada";
    	}
    	if (this->moves[i].getSkillName() == "null")
    	{
    		break;
    	}
    }

    while (!(0 <= i && i <= 3))
    {
    	this->printMoves();
    	cout << "Masukkan move yang ingin direplace" << endl;
    	cin >> i;
    }

    this->moves[i] = (*itr);
    cout << (*itr).getSkillName() <<" berhasil dipelajari "<< this->name << endl;
}

void engimon::setSkill(skill x, int idx, int mastery_level)
{
	x.setMasteryLv(mastery_level);
	this->moves[idx] = x;
}
void engimon::cry()
{
	map<string,string>::iterator i = engimon_cry.find(this->species);
	if (i == engimon_cry.end())
	{
		throw "Cry engimon tidak ditemukan";
	}
	cout << "[" << this->name << "]: " << i->second << endl;
}

void engimon::setActive()
{
	this->active = true;
}
void engimon::setInactive()
{
	this->active = false;
}
bool engimon::isActive()
{
	return this->active;
}

string engimon::getName()
{
	return name;
}

string engimon::getSpecies()
{
	return species;
}

int engimon::getLevel()
{
	return level;
}

void engimon::setLevel(int x) {
	this->level = x;
}

string engimon::getElmt1() {
	return element1;
}
string engimon::getElmt2() {
	return element2;
}

skill engimon::getMove(int x) {
	return this->moves[x];
}

bool engimon::isNull()
{
	if (this->name == name_null)
	{
		return true;
	}
	else
	{
		return false;
	}
}

void engimon::IntializeDatabase()
{
	all_species.insert(make_tuple("Charmander", "Fire", string_null));
	all_species.insert(make_tuple("Magikarp", "Water", string_null));
	all_species.insert(make_tuple("Pikachu", "Electric", string_null));
	all_species.insert(make_tuple("Geodude", "Ground", string_null));
	all_species.insert(make_tuple("Glastrier", "Ice", string_null));
	all_species.insert(make_tuple("Vulpichu", "Fire", "Electric"));
	all_species.insert(make_tuple("Lapras", "Water", "Ice"));
	all_species.insert(make_tuple("Wooper", "Water", "Ground"));
	//all_species.insert(make_tuple("Volcanion", "Water", "Fire"));
	engimon_cry.insert(pair<string,string>("Charmander","Char!"));
	engimon_cry.insert(pair<string,string>("Magikarp","Splash"));
	engimon_cry.insert(pair<string,string>("Pikachu","Pika Pika"));
	engimon_cry.insert(pair<string,string>("Geodude","Grunt"));
	engimon_cry.insert(pair<string,string>("Glastrier","Neigh"));
	engimon_cry.insert(pair<string,string>("Vulpichu","Vulka!"));
	engimon_cry.insert(pair<string,string>("Lapras","Phwargh"));
	engimon_cry.insert(pair<string,string>("Wooper","Chomp!"));

}