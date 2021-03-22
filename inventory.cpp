#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "skill.hpp"
#include "inventory.hpp"
using namespace std;

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

void inventory<engimon>::deleteItem(engimon& engimon)
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
			break;
		}
	}

	if (i != this->contents.end())
	{
		(*i).setAmountInInventory((*i).getAmountInInventory() + 1);
	}
	else
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