#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;
void RadixSort(string *, string *, int *, int, int, int);
int main()
{
    ifstream in("radixsort.in");
    ofstream out("radixsort.out");
    int n;
    in >> n;
    vector <string> arr(n);
    vector <string> brr(n);
    vector <int> ABC(256);
    int lght;
    in >> lght;
    int phase;
    in >> phase;
    for (int i = 0; i < n; i++)
        in >> arr[i];
    
    RadixSort(&arr[0], &brr[0], &ABC[0], n, phase, lght);
    for (int i = 0; i < n - 1; i++)
        out << arr[i] << endl;
    out << arr[n - 1];
    
    in.close();
    out.close();
}

void RadixSort(string * a, string * b, int * c, int n, int phase, int lght)
{
    char check = 0;
    
    for (int i = 0; i < phase; i++)
    {
        for (int j = 0; j < 256; j++)
            c[j] = 0;
        for (int j = 0; j < n; j++)
        {
            check = a[j][lght - i - 1];
            c[check] += 1;
        }
        c[0] -= 1;
        for (int j = 1; j < 256; j++)
            c[j] += c[j - 1];
        
        for (int j = n - 1; j >= 0; j--)
        {
            check = a[j][lght - i - 1];
            b[c[check]] = a[j];
            c[check] -= 1;
        }
        
        for (int j = 0; j < n; j++)
            a[j] = b[j];
    }
}
