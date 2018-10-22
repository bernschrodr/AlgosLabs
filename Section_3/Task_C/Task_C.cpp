#include <fstream>
#include <vector>
using namespace std;

int main()
{
    int n, k;

    fstream fs;
    fs.open("isheap.in", fstream::in);
    fs >> n;
    vector<int> Arr(n);
    for (int i = 0; i < n; ++i)
    {
        fs >> Arr[i];
    }
    fs.close();

    k = 0;
    for (int i = 0; i < (n/2) -1; ++i)
    {
        if ((Arr[i] > Arr[(2 * i) + 1]) || (Arr[i] > Arr[(2 * i) + 2]))
            ++k;
    }

    fs.open("isheap.out", fstream::out);
    if(k==0)
    fs << "YES" << endl;
    else
    fs << "NO" << endl;
}