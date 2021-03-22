#include <iostream>
#include <string>
#include <iterator> 
#include <vector>
#include "engimon.hpp"
#include "skill.hpp"
using namespace std;

#ifndef INVENTORY
#define INVENTORY

template<class T>
class inventory
{
public:
	inventory();
	inventory(const inventory& inv);
	inventory& operator=(const inventory& inv);
	~inventory();

	friend class player;
private:
	void addItem(T);
    void deleteItem(T);
	int countItem();

	vector<T> contents;
};

template<>
class inventory<engimon>
{
public:
	friend class player;
private:
	void addItem(engimon);
    void deleteItem(engimon&);
	int countItem();

	vector<engimon> contents;
};

template<>
class inventory<skill>
{
public:
	friend class player;
private:
	void addItem(skill);
    void deleteItem(skill);
	int countItem();

	vector<skill> contents;
};
#endif