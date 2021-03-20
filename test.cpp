#include <iostream> 
#include <string>
#include <iterator> 
#include <set> 
#include <tuple>

#define element_null "null"

using namespace std;

int main(int argc, char const *argv[])
{
	set<tuple<string,string,string>> all_species;
	all_species.insert(make_tuple("Alpha", element_null, element_null));
	all_species.insert(make_tuple("Omega", element_null, element_null));
	all_species.insert(make_tuple("Bulbasuar", "Ground", element_null));

	//set<tuple<string,string,string>>:: iterator i;

	//auto i = all_species.begin();
	set<tuple<string,string,string>>::iterator i = all_species.begin();

	for (i = all_species.begin(); i != all_species.end(); ++i)
	{

		cout << get<0>(*i) << " " << get<1>(*i) << endl;
		if (get<0>(*i) == "Bulbasuar")
		{
			break;
		}
	}

	cout << get<0>(*i) << endl;

	cout << all_species.max_size() << endl;
	return 0;
}