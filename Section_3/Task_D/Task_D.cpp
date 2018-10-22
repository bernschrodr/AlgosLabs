#include <fstream>
#include <vector>
using namespace std;

void heapify(vector<int> &arr, int n, int i)
{
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l < n && arr[l] > arr[largest])
        largest = l;

    if (r < n && arr[r] > arr[largest])
        largest = r;

    if (largest != i)
    {
        swap(arr[i], arr[largest]);

        heapify(arr, n, largest);
    }
}

void heapSort(vector<int> &arr, int n)
{

    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);

    for (int i = n - 1; i >= 0; i--)
    {
        swap(arr[0], arr[i]);

        heapify(arr, i, 0);
    }
}


int main()
{
    int n;

    fstream fs;
    fs.open("sort.in", fstream::in);
    fs >> n;
    vector<int> Arr(n);
    for (int i = 0; i < n; ++i)
    {
        fs >> Arr[i];
    }
    fs.close();

    heapSort(Arr, Arr.size());

    fs.open("sort.out", fstream::out);

    for (int i = 0; i < n; ++i)
    {
        fs << Arr[i] << endl;
    }

    fs.close();
    return 0;
}