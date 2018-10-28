#include <fstream>
#include <iostream>
#include <vector>
#include <string>
using namespace std;

string getMax(vector <string> &arr, int n) 
{ 
    vector <string> mx(1);
    mx.push_back(arr[0]);
    mx.erase(mx.begin());
    
    for (int i = 1; i < n; i++){
        cout << mx[0];
        if (arr[i].compare(mx[0]) <= 0){
            mx.push_back(arr[i]);
            mx.erase(mx.begin());
            cout << mx[0];
            }}
    return mx[0];
    
} 
  
// A function to do counting sort of arr[] according to 
// the digit represented by exp. 
void countSort(vector <string> &arr, int n, int exp) 
{ 
    vector <string> output(n); // output array 
    int i, count[256] = {0}; 
    
    // Store count of occurrences in count[] 
    for (i = 0; i < n; i++) 
        count[ (int)arr[i][exp] ]++; 
  
    // Change count[i] so that count[i] now contains actual 
    //  position of this digit in output[] 
    for (i = 1; i < 256; i++) 
        count[i] += count[i - 1];
    // Build the output array 
    for (i = n - 1; i >= 0; i--) 
    { 
        output[ (int)count[ arr[i][exp] ] - 1] = arr[i]; 
        count[ (int)arr[i][exp] ]--;
    } 
  
    // Copy the output array to arr[], so that arr[] now 
    // contains sorted numbers according to current digit 
    for (i = 0; i < n; i++) 
        arr[i] = output[i];
} 
  
// The main function to that sorts arr[] of size n using  
// Radix Sort 
void radixsort(vector <string> &arr, int n) 
{ 
    // Find the maximum number to know number of digits 
    vector <string> m(1);
    m.push_back(getMax(arr, n));
  
    // Do counting sort for every digit. Note that instead 
    // of passing digit number, exp is passed. exp is 10^i 
    // where i is current digit number 
    int exp = n;
    while(!m.empty()){
        countSort(arr, n, exp);
        m.erase(m.end());
        exp--;
        printf(" + ");
        }
}

int main()
{
    int n,k,m;

    fstream fs;
    fs.open("radixsort.in", fstream::in);
    fs >> n >> k >> m;
    vector <string> Arr(n);

    for (int i = 0; i < n; ++i)
    {
        fs >> Arr[i];
    }

    radixsort(Arr,m);

    fs.open("radixsort.out", fstream::out);
    for (int i = 0; i < n; ++i)
    {
        fs << Arr[i] << "--" << endl;
    }
    fs.close();

    return 0;
}