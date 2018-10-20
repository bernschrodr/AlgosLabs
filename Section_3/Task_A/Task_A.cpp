#include <fstream>
#include <vector>
using namespace std;

int BinSearchL(vector<int> &Inp, int Get)
{               // Запускаем бинарный поиск
    int l = -1; // l, r — левая и правая границы
    int r = Inp.size();
    int m;
    while (l < r - 1)
    {                    // Запускаем цикл
        m = (l + r) / 2; // m — середина области поиска
        if (Inp[m] < Get)
            l = m;
        else
            r = m;
    } // Сужение границ
    if (r == Inp.size()){
        r = -1;
        return r;
    }
    else
         return ++r;
}      

int BinSearchR(vector<int> &Inp, int Get)
{                           // Запускаем бинарный поиск
    int l = -1; // l, r — левая и правая границы
    int r = Inp.size();
    int m;
    while (l < r - 1)
    {                    // Запускаем цикл
        m = (l + r) / 2; // m — середина области поиска
        if (Inp[m] <= Get)
            l = m;
        else
            r = m;
    } // Сужение границ
    
    if (r == Inp.size())
        r = -1;
        return r;
}

int main()
{
    fstream fs;
    fs.open("binsearch.in", fstream::in);
    int n, m;
    int Out1 = 0, Out2 = 0;
    fs >> n;
    vector<int> Arr(n);

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
        //Out1 = BinSearchL(Arr, Get[i]);
        Out2 = BinSearchR(Arr, Get[i]);
        fs << Out1 << " " << Out2 << endl;
    }
    fs.close();
    return 0;
}