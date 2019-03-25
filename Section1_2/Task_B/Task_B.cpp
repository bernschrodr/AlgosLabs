#include <fstream>
#include <string>
#include <vector>
using namespace std;

void Merge(vector<string> &Country, vector<string> &Name, int Left, int Middle, int Right)
{
	int i, j, k;
	int n1 = Middle - Left + 1;
	int n2 = Right - Middle;

	vector<string> lCountry;
	vector<string> lName;
	vector<string> rCountry;
	vector<string> rName;
	for (i = 0; i < n1; i++)
	{
		lCountry.push_back( Country[Left + i] );
		lName.push_back( Name[Left + i] );
	}
	for (j = 0; j < n2; j++)
	{
		rCountry.push_back( Country[Middle + 1 + j] );
		rName.push_back( Name[Middle + 1 + j] );
	}

	i = 0;
	j = 0;
	k = Left;
	while (i < n1 && j < n2)
	{
		if (lCountry[i] <= rCountry[j])
		{
			Country[k] = lCountry[i];
			Name[k] = lName[i];
			k++;
			i++;
		}
		else
		{
			Country[k] = rCountry[j];
			Name[k] = rName[j];
			k++;
			j++;
		}
	}

	while (i < n1)
	{
		Country[k] = lCountry[i];
		Name[k] = lName[i];
		k++;
		i++;
	}
	while (j < n2)
	{
		Country[k] = rCountry[j];
		Name[k] = rName[j];
		k++;
		j++;
	}
}

void MergeSort(vector<string> &Country, vector<string> &Name, int Left, int Right)
{
	if (Right > Left)
	{
		int Middle = (Right + Left) / 2;
		MergeSort(Country, Name, Left, Middle);
		MergeSort(Country, Name, Middle + 1, Right);

		Merge(Country, Name, Left, Middle, Right);
	}
}
int main()
{
	vector<string> NameArr;
	vector<string> CountryArr;
	fstream fs;

	fs.open("race.in", fstream::in);
	string Name;
	string Country;
	int size;
	fs >> size;
	for (int i = 0; i < size; ++i)
	{
		fs >> Country;
		CountryArr.push_back(Country);

		fs >> Name;
		NameArr.push_back(Name);
	}

	fs.close();

	MergeSort(CountryArr, NameArr, 0, CountryArr.size() - 1);

	fs.open("race.out", fstream::out);
	for (int i = 0; i < size; i++)
	{
		if (i == 0)
		{
			fs << "=== " << CountryArr[i] << " ===" << endl;
			fs << NameArr[i] << endl;
		}
		else if (CountryArr[i] == CountryArr[i - 1])
		{
			fs << NameArr[i] << endl;
		}
		else
		{
			fs << "=== " << CountryArr[i] << " ===" << endl;
			fs << NameArr[i] << endl;
		}
	}
	fs.close();



	return 0;
}
    