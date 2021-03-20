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
class engimon
{
public:
	engimon();//Null
	engimon(string nm, string pr1, string pr2, string sp, skill skill_bawaan, int lvl, int exp);
	engimon(const engimon& e);
	engimon& operator=(const engimon& e);
	~engimon();

	void showAttributes();
	void printMoves();
	void gainExp(int);
	void learnMove(string);
	void cry();

	void setActive();
	void setInactive();
	bool isActive();

	string getName();


	static void IntializeDatabase();
protected:
	string name;
	string parent1;
	string parent2;
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
	static set<tuple<string,string,string>> all_species;
	static map<string,string> engimon_cry;
};
#endif