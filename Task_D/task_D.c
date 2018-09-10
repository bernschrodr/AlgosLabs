#include <stdio.h>
#include <string.h>

int main(void)
{
    int i, j, n;
    FILE *fin, *fout;
    fin = fopen("smallsort.in", "r");
    fout = fopen("smallsort.out", "w");
    fscanf(fin, "%d", &n);

    long long numbers[n - 1], tmp;

//Считываем массив
      for (i = 0; i < n; i++)
    {
        fscanf(fin, "%lld", &numbers[i]);
    }

//Сортировка

for (i = 0; i < n - 1; i++)
    {
        for (j = 0; j < n - i - 1; j++)
        {
            if (numbers[j] > numbers[j + 1])
            {
                double tmp = numbers[j];
                numbers[j] = numbers[j + 1];
                numbers[j + 1] = tmp;
            }
        }
    }

//вывод
    for (i = 0; i < n; i++){
        fprintf(fout, "%lld ", numbers[i]);
    }
    
    fclose(fin);
    fclose(fout);
    return 0;
}
