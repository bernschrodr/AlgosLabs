#include <fstream>
#include <iostream>
#include <iomanip>

using namespace std;

double Search(double level, double low, int n)
{
    double mid = (level + low) / 2;
    double past = level, present = low, future = 0;
    double H = level, h = 0;
    
    while (!(level == mid || low == mid))
    {
        past = H;
        present = mid;
        
        for (int i = 2; i < n; i++)
        {
            future = 2 * present - past + 2;
            if (future <= 0)
                break;
            
            if (i == n - 1)
                h = future;
            past = present;
            present = future;
        }
        
        (future <= 0 ? low : level) = mid;
        mid = (level + low) / 2;
    }
    return h;
}


int main()
{
    ifstream in("garland.in");
    ofstream out("garland.out");
    
    int n;
    double A;
    in >> n;
    in >> A;
    
    out << fixed << setprecision(2) << Search(A, 0, n);
}



