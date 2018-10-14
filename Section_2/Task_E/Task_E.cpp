#include <fstream>
#include <vector>
using namespace std;

static int i = 0, j = 0;
void partition(vector<int> &arr, int &l, int &r)
{
    int k = (l + r) / 2;
    int key = arr[k];
    i = l;
    j = r;

    while (i <= j)
    {
        while (arr[i] < key)
            i++;
        while (key < arr[j])
            j--;
        if (i <= j)
        {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++;
            j--;
        }
    }
}

int kElement(vector<int> &arr, int l, int r, int k)
{

    if (r < l)
    {
        return arr[l];
    }

    partition(arr, l, r);

    if (j + 1 <= k && k <= i - 1)
    {
        return arr[j + 1];
    }
    else if (l <= k && k <= j)
    {
        return kElement(arr, l, j, k);
    }
    else
    {
        return kElement(arr, i, r, k);
    }
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

    fs.open("kth.out", fstream::out);

    fs << kElement(Arr, 0, n - 1, k - 1) << endl;

    fs.close();

    return 0;
}