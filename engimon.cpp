#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include <vector>
#include <algorithm>
#include "engimon.hpp"
using namespace std;

set<tuple<string,string,string>> engimon::all_species;

engimon::engimon()//Null
{
	name = name_null;
	parent1 = name_null;
	parent2 = name_null;
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
}
engimon::engimon(string nm, string pr1, string pr2, string sp, skill skill_bawaan, int lvl, int exp)
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
	moves[0] = skill_bawaan;

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
	return *this;
}

engimon::~engimon()
{
	delete[] moves;
}

void engimon::showAttributes()
{
	cout << "Engimon name: " << name << endl;
	if (parent1 != name_null)
	{
		cout << "Parent 1: " <<parent1 << endl;
	}

	if (parent2 != name_null)
	{
		cout << "Parent 2: " << parent2 << endl;
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

    vector<string> move_elements = (*itr).getElements();

    auto element1_checker = find(move_elements.begin(), move_elements.end(), this->element1);
    auto element2_checker = find(move_elements.begin(), move_elements.end(), this->element2);
    if (element1_checker == move_elements.end() && element2_checker == move_elements.end())
    {
    	throw "Error, skill tidak compatible dengan engimon";
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

string engimon::getName()
{
	return name;
}

void engimon::IntializeSpecies()
{
	all_species.insert(make_tuple("Enmander", "Fire", string_null));
	all_species.insert(make_tuple("Engikarp", "Water", string_null));
	all_species.insert(make_tuple("Enkachu", "Electric", string_null));
	all_species.insert(make_tuple("Endude", "Ground", string_null));
	all_species.insert(make_tuple("Enstrier", "Ice", string_null));
}