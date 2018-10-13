#include <fstream>
#include <vector>
using namespace std;

void antiQsort(vector<long> &Inp)
{
    for (int i = 2; i < Inp.size(); i++)
    {
        swap(Inp[i], Inp[i / 2]);
    }
}

int main()
{
    fstream fs;
    fs.open("kth.in", fstream::in);
    long long n,k;
    fs >> n;
    fs >> k;

    fs.close();

    vector <long> Inp(n);

    for (long i = 0; i < n; i++)
    {
        Inp[i] = i + 1;
    }

    antiQsort(Inp);

    fs.open("kth.out", fstream::out);

    for (long i = 0; i < n; i++)
    {
        fs << Inp[i] << endl;
    }

    fs.close();

    return 0;
}