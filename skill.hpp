#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
using namespace std;

#ifndef SKILL
#define SKILL 
class skill
{
public:
	skill();
	skill(string nm, int bp, int mastery_lvl, string elem[5], string engimon_name /*Isi "0", jika tidak unik*/);
	//skill(const skill &s); Bitwise copy
	//skill& operator= (const skill &s); Bitwise copy
	~skill(); //String dan vector akan destruct sendiri

	
	string getSkillName();
	int getAmountInInventory();
	vector<string> getElements();
	int getBasePower();
	int getMasteryLv();
	void printAll();
	void setAmountInInventory(int);
	static void InitializeSkillDatabase();

	static vector<skill> skill_database;

protected:
	string skillName;
	int base_power;
	int mastery_level;
	int amount_in_inventory;

	//Elements yang dapat mempelajari skill ini
	//Hanya bisa berupa Fire, Water, Electric, Ground, Ice
	vector<string> elements;

	//JIka ada value nama engimon, maka hanya bisa dipelajari engimon tersebut
	//Jika tidak unique, isi 0
	string engimon_unique = "0";

};

#endif