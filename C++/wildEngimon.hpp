#include <iostream>
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>
#include "skill.hpp"
#include "engimon.hpp"
#include <map>
using namespace std;


#ifndef WILD_ENGIMON
#define WILD_ENGIMON
class wildEngimon : public engimon
{
public:
	wildEngimon(string sp, int lvl);
	wildEngimon(const wildEngimon& w);
	~wildEngimon();//Move akan dihandle engimon

	//engimon& operator=(const wildEngimon& w);
	engimon& operator=(const wildEngimon& w);
	void learnMove(string);

protected:
	static engimon wildNullEngimon;
};
#endif


