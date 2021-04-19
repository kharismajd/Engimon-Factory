#include <iostream>
#include <string>
#include <algorithm>
#include <iterator> 
#include <set>
#include <stdio.h>      
#include <stdlib.h>
#include <time.h> 
#include "skill.hpp"
#include "engimon.hpp"
#include "wildEngimon.hpp"

#include <vector>

using namespace std;

engimon wildEngimon::wildNullEngimon;

wildEngimon::wildEngimon(string sp, int lvl) : engimon (sp, &wildNullEngimon, &wildNullEngimon, sp, lvl, 0)
{
	/* Random Module*/
	srand(time(NULL));
	int randomNumber = rand() %  skill::skill_database.size() + 1;

	// /* Inisialisasi*/
	std::vector<string>::iterator p1;
	std::vector<string>::iterator p2;

	/*Cari 1 skill yang compatible*/
	auto itr = skill::skill_database.begin();
	while (randomNumber > 0)
	{
		if (itr == skill::skill_database.end())
		{
			itr = skill::skill_database.begin();
		}

		/*Fail*/
		vector<string> elementSkill = (*itr).getElements();

		//Find skill dengan elemen yang sesuai
		//p1 = std::find(elementSkill.begin(), elementSkill.end(), this->element1);
		//p2 = std::find(elementSkill.begin(), elementSkill.end(), this->element2);
		p1 = elementSkill.end();
		p2 = elementSkill.end();

		for (auto i = elementSkill.begin(); i != elementSkill.end(); ++i)
		{
			if (*i == this->element1)
			{
				p1 = i;
				break;
			}

			if (*i == this->element2)
			{
				p2 = i;
				break;
			}
		}



		// cout << *elementSkill.begin();
		
		if (p1 != elementSkill.end() || p2 != elementSkill.end())
		{
			randomNumber--;
			if (randomNumber <= 0)
			{
				break;
			}
		}

		if (randomNumber > 0)
		{
			++itr;
		}

		
	}
	try
	{
		this->learnMove((*itr).getSkillName());
	}
	catch (int e)
	{

	}
}

wildEngimon::wildEngimon(const wildEngimon& w) : engimon(w){}

wildEngimon::~wildEngimon(){}

// engimon& wildEngimon::operator=(const wildEngimon& w)
// {
// 	engimon e = new engimon(w.name, w.parent1, w.parent2, w.species, w.level, w.experience);
// 	for (int i = 0; i < 4; ++i)
// 	{
// 		e.setSkill(w.moves[i],i,1);
// 	}
// 	return e;
// }

engimon& wildEngimon::operator=(const wildEngimon& w)
{
	delete[] moves;
	this->name = w.name;
	this->parent1 = w.parent1;
	this->parent2 = w.parent2;
	this->species = w.species;
	this->moves = new skill[4];
	for (int i = 0; i < 4; ++i)
	{
		this->moves[i] = w.moves[i];
	}
	this->element1 = w.element1;
	this->element2 = w.element2;
	this->level = w.level;
	this->experience = w.experience;
	this->cummulative_experience = w.cummulative_experience;
	this->active = w.active;
	return *this;
}

void wildEngimon::learnMove(string move)
{
	auto itr = skill::skill_database.begin();
	for (itr = skill::skill_database.begin(); itr != skill::skill_database.end(); ++itr)
	{
		if ((*itr).getSkillName() == move)	
		{
			break;
		}
	}
    
    if (itr == skill::skill_database.end())
    {
    	throw -1; //"Error, skill tidak ditemukan"
    }

    if ((*itr).getEngimonSpecies() != this->species && (*itr).getEngimonSpecies() != "0")
    {
    	throw -2; //"Error, engimon tidak sesuai"
    }

    vector<string> move_elements = (*itr).getElements();

    auto element1_checker = find(move_elements.begin(), move_elements.end(), this->element1);
    auto element2_checker = find(move_elements.begin(), move_elements.end(), this->element2);

    if (element1_checker == move_elements.end() && element2_checker == move_elements.end())
    {
    	throw -3; //"Error, skill tidak compatible dengan elemen engimon"
    }

    int i = 0;
    for (i = 0; i < 4; ++i)
    {
    	if (this->moves[i].getSkillName() == move)
    	{
    		throw -4; //"Error, skill sudah ada"
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
}