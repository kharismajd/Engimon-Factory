#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include "skill.hpp"
#include <map>
using namespace std;

#ifndef ENGIMON
#define ENGIMON

#define string_null "null"
#define name_null "null"
#define cummulative_experience_limit 100000
class engimon
{
public:
	engimon();//Null
	engimon(string nm, engimon* pr1, engimon* pr2, string sp, int lvl, int exp);
	engimon(const engimon& e);
	engimon& operator=(const engimon& e);
	virtual ~engimon();

	void showAttributes();
	void printMoves();
	void gainExp(int);
	void learnMove(string);
	void setSkill (skill x, int idx, int mastery_level);
	// sets moves[idx] of engimon to a copy of skill a, with corresponding mastery_level

	void cry();
	
	void setActive();
	void setInactive();
	bool isActive();
	string getName();
	string getSpecies();
	int getLevel();
	void setLevel(int);
	int getCummulativeExp();

	string getElmt1();
	string getElmt2();
	skill getMove(int);

	bool isNull();

	static void IntializeDatabase();
	static set<tuple<string,string,string>> all_species;
	static map<string,string> engimon_cry;
protected:
	string name;
	engimon* parent1;
	engimon* parent2;
	string species;
	skill* moves;
	string element1;
	string element2;
	int level;
	int experience;
	int cummulative_experience;
	bool active;

	//Isi: Nama, element1, element2
	//Jika ada 1 element saja, element2 diisi "null"
	
};
#endif