#include <fstream>
#include <vector>
using namespace std;

int BinSearch(vector <int> &Inp, int Get, int &Out1, int &Out2)
{

    int Mid = Inp.size() / 2;

    while ((Mid > 0) && (Mid < Inp.size() - 1))
    {
        if (Inp[Mid] < Get)
            Mid /= 2;
        else
            Mid += (Inp.size() - Mid) / 2;

        if ((Inp[Mid] == Get) && (Out1 == 0))
            Out1 = Mid;
        else if (Inp[Mid] == Get)
            Out2 = Mid;
    }

    if ((Out1 == 0) && (Out2 == 0))
    {
        Out1 = -1;
        Out2 = -1;
    }
    return 0;
}

int main()
{
    fstream fs;
    fs.open("binsearch.in", fstream::in);
    int n, m;
    int Out1 = 0, Out2 = 0;
    fs >> n;
    vector <int> Arr(n);

    for (int i = 0; i < n; i++)
    {
        fs >> Arr[i];
    }

    fs >> m;
    vector<int> Get(m);

    for (int i = 0; i < m; i++)
    {
        fs >> Get[i];
    }

    fs.close();

    fs.open("binsearch.out", fstream::out);

    for (int i = 0; i < m; ++i)
    {
        BinSearch(Arr, Get[i], Out1, Out2);
        fs << Out1 << Out2 << endl;
    }
    fs.close();
    return 0;
}