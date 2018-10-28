#include <fstream>
#include <vector>
using namespace std;

int BinSearchFirst(vector<int> &Inp, int Get)
{
    int l = 0, r = Inp.size() - 1;
    while (l < r)
    {
        int mid = l + (r - l) / 2;
        if (Inp[mid] >= Get)
            r = mid;
        else
            l = mid + 1;
    }
    if (Inp[l] == Get)
        return l;
    else
        return -1;
}

int BinSearchLast(vector<int> &Inp, int Get)
{
    int l = 0, r = Inp.size() - 1;
    while (l < r)
    {
        int mid = r - (r - l) / 2;
        if (Inp[mid] <= Get)
            l = mid;
        else
            r = mid - 1;
    }
    if (Inp[l] == Get)
        return l;
    else
        return -1;
}

int main()
{
    fstream fs;
    fs.open("binsearch.in", fstream::in);
    int n, m;
    int Out1 = 0, Out2 = 0;
    fs >> n;
    vector<int> Inp(n);

    for (int i = 0; i < n; i++)
    {
        fs >> Inp[i];
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
        Out1 = BinSearchFirst(Inp, Get[i]);
        Out2 = BinSearchLast(Inp, Get[i]);
        if (Out1 != -1)
        {
            Out1++;
            Out2++;
        }
        fs << Out1 << " " << Out2 << endl;
    }
    fs.close();
    return 0;
}