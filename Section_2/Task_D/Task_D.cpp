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
    fs.open("antiqs.in", fstream::in);
    long n;
    fs >> n;
    fs.close();

    vector <long> Inp(n);

    for (long i = 0; i < n; i++)
    {
        Inp[i] = i + 1;
    }

    antiQsort(Inp);

    fs.open("antiqs.out", fstream::out);

    for (long i = 0; i < n; i++)
    {
        fs << Inp[i] << endl;
    }

    fs.close();

    return 0;
}