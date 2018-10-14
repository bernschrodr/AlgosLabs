#include <fstream>
#include <vector>
using namespace std;

void swap(int a, int b)
{
    int temp = b;
    b = a;
    a = temp;
}

void quicksort(vector <int> &Inp, long l, long r)
{
    int v;
    v = Inp[r];
    if (r <= l)
        return;
    long i = l;
    long j = r - 1;
    long p = l - 1;
    long q = r;
    while (i <= j)
    {
        while (Inp[i] < v)
            i++;
        while (Inp[i] > v)
            j--;
        if (i >= j)
            break;
        swap(Inp[i], Inp[j]);
        if (Inp[i] == v)
            p++;
        swap(Inp[p], Inp[i]);
        i++;
        if (Inp[j] == v)
        q--;
        swap(Inp[q], Inp[j]);
        j--;
    }
    swap(Inp[i], Inp[r]);
    j = i - 1;
    i++;
    for (long k = l; k <= p; k++, j--)
    {
        swap(Inp[k], Inp[j]);
    }
    for (long k = r - 1; k >= q; k--, i++)
    {
        swap(Inp[k], Inp[i]);
    }
    quicksort(Inp, l, j);
    quicksort(Inp, i, r);
}


int main()
{
    fstream fs;
    fs.open("kth.in", fstream::in);
    int n, k, A, B, C;
    fs >> n;
    vector<int> Arr(n);
    fs >> k;
    fs >> A;
    fs >> B;
    fs >> C;
    fs >> Arr[0];
    fs >> Arr[1];

    fs.close();

    for (int i = 2; i < n; i++)
    {
        Arr[i] = A * Arr[i - 2] + B * Arr[i - 1] + C;

    }

    quicksort(Arr,0,n);

    fs.open("kth.out", fstream::out);

    fs << Arr[k-1] << endl;

    fs.close();

    return 0;
}