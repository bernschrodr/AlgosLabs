#include <fstream>
#include <vector>
using namespace std;

// Слияние
void Merge(long a[], long left, long mid, long right, long Counter)
{
	long it1 = 0;
	long it2 = 0;
	long result[right - left];

	while ((left + it1 < mid) & (mid + it2 < right)) {
		if (a[left + it1] < a[mid + it2])
		{
			result[it1 + it2] = a[left + it1];
			it1 += 1;
		}
		else
		{
			result[it1 + it2] = a[mid + it2];
			it2 += 1;
			Counter += ((sizeof(a) / sizeof(*a)) - it1);
		}
	}

	while (left + it1 < mid)
	{
		result[it1 + it2] = a[left + it1];
		it1 += 1;
	}

	while (mid + it2 < right)
	{
		result[it1 + it2] = a[mid + it2];
		it2 += 1;
	}

	for (long i = 0; i < it1 + it2; i++)
		a[left + i] = result[i];
}

//Рекурсивная сортировка слиянием
void MergeSort(long a[], long left, long right, long Counter)
{
	if (left + 1 >= right)
		return;
	long mid = (left + right) / 2;
	MergeSort(a, left, mid, Counter);
	MergeSort(a, mid, right, Counter);
	Merge(a, left, mid, right, Counter);
}
int main()
{
	fstream fs;

	fs.open("inversions.in", fstream::in);
	
	long size,k;
	fs >> size;
	long Inp[size];
	
	for (int i = 0; i < size; ++i) fs >> Inp[i];

	fs.close();

	MergeSort(Inp, 0, size - 1, k);

	fs.open("inversions.out", fstream::out);
	fs << k << endl;
	fs.close();



	return 0;
}
