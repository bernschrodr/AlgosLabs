#include <fstream>
#include <vector>
using namespace std;

void Merge(vector<int> &a, int left, int mid, int right)
{
    int it1 = 0;
    int it2 = 0;
    int result[right - left];

    while ((left + it1 < mid) & (mid + it2 < right))
    {
        if (a[left + it1] < a[mid + it2])
        {
            result[it1 + it2] = a[left + it1];
            it1 += 1;
        }
        else
        {
            result[it1 + it2] = a[mid + it2];
            it2 += 1;
        }
    }

    while (left + it1 < mid)
    {
        result[it1 + it2] = a[left + it1];
        it1 += 1;
    }

    while (mid + it2 < right)
    {
        result[it1 + it2] = a[mid + it2];
        it2 += 1;
    }

    for (int i = 0; i < it1 + it2; i++)
        a[left + i] = result[i];
}

//Рекурсивная сортировка слиянием
void MergeSort(vector<int> &a, int left, int right)
{
    if (left + 1 >= right)
        return;
    int mid = (left + right) / 2;
    MergeSort(a, left, mid);
    MergeSort(a, mid, right);
    Merge(a, left, mid, right);
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

    MergeSort(Arr,0,n);

     for(int i = 0; i < n; i++)
    {
        printf("%d ", Arr[i]);
    }

    fs.open("kth.out", fstream::out);

    fs << Arr[k-1] << endl;

    fs.close();

    return 0;
}