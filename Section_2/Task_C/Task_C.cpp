#include <fstream>
using namespace std;

// Слияние
// Функция будет собой возвращать значение счетчика

long long _MergeSort(long long Inp[], long long Temp[], long long Left, long long Right);
long long Merge(long long Inp[], long long Temp[], long long Left, long long Mid, long long Right);

long long MergeSort(long long Inp[], long long Inpay_Size)
{
	long long *Temp = (long long *)malloc(sizeof(long long) * Inpay_Size);
	return _MergeSort(Inp, Temp, 0, Inpay_Size - 1);
}

long long _MergeSort(long long Inp[], long long Temp[], long long Left, long long Right)
{
	long long Mid, Count = 0;
	if (Right > Left)
	{

		Mid = (Right + Left) / 2;

		Count = _MergeSort(Inp, Temp, Left, Mid);
		Count += _MergeSort(Inp, Temp, Mid + 1, Right);

		Count += Merge(Inp, Temp, Left, Mid + 1, Right);
	}
	return Count;
}

long long Merge(long long Inp[], long long Temp[], long long Left, long long Mid, long long Right)
{
	long long i, j, k;
	long long Count = 0;

	i = Left;
	j = Mid;
	k = Left;
	while ((i <= Mid - 1) && (j <= Right))
	{
		if (Inp[i] <= Inp[j])
		{
			Temp[k++] = Inp[i++];
		}

		else
		{
			Temp[k++] = Inp[j++];

			Count = Count + (Mid - i);
		}
	}

	while (i <= Mid - 1)
		Temp[k++] = Inp[i++];

	while (j <= Right)
		Temp[k++] = Inp[j++];

	for (i = Left; i <= Right; i++)
		Inp[i] = Temp[i];

	return Count;
}

int main()
{
	fstream fs;
	fs.open("inversions.in", fstream::in);

	long long Size;
	fs >> Size;
	long long *Inp = new long long[Size];

	for (long long i = 0; i < Size; ++i)
		fs >> Inp[i];

	fs.close();

	fs.open("inversions.out", fstream::out);
	fs << MergeSort(Inp, Size) << endl;

	fs.close();

	return 0;
}
