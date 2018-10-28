#include <fstream>
#include <vector>
#include <string>
#include <iostream>

using namespace std;

void CountSort(string * arr, int n, int exp)
{
    string* output = new string[n];
    int count[256] = {0};

    for (int i = 0; i < n; i++)
        count[(int)arr[i][exp]]++;

    for (int i = 64; i < 123; i++)
        count[i] = count[i - 1];

    for (int i = n - 1; i >= 0; i--)
    {
        char c = arr[i][exp];
        int index = count[(int)c]--;
        output[index] = arr[i];
    }

    //возвращаем массив
    for (int i = 0; i < n; i++)
        arr[i] = output[i];
    return;
}

int main()
{
    int n, k, m;

    fstream fs;
    fs.open("radixsort.in", fstream::in);
    fs >> n >> k >> m;
    string *Arr = new string[n];

    for (int i = 0; i < n; i++)
    {
        fs >> Arr[i];
    }
    fs.close();

    for (int i = 0; i < k; i++)
        CountSort(Arr, n,m - i - 1);

    fs.open("radixsort.out", fstream::out);
    for (int i = 0; i < n; i++)
    {
        fs << Arr[i] << endl;
    }
    fs.close();

    return 0;
}