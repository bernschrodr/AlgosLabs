#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void swap(char a[10], char b[10])
{
    char temp[10];
    strcpy(temp, b);
    strcpy(b, a);
    strcpy(a, temp);
}

void quicksort(char Country[][10], char Name[][10], long l, long r)
{
    char v[10];
    strcpy(v, Country[r]);
    if (r <= l)
        return;
    long i = l;
    long j = r - 1;
    long p = l - 1;
    long q = r;
    while (i <= j)
    {
        while (strcmp(Country[i], v) < 0)
            i++;
        while (strcmp(Country[j], v) > 0)
            j--;
        if (i >= j)
            break;
        swap(Country[i], Country[j]);
        swap(Name[i], Name[j]);
        if (strcmp(Country[i], v) == 0)
            p++;
        swap(Country[p], Country[i]);
        swap(Name[p], Name[i]);
        i++;
        if (strcmp(Country[j], v) == 0)
        q--;
        swap(Country[q], Country[j]);
        swap(Name[q], Name[j]);
        j--;
    }
    swap(Country[i], Country[r]);
    swap(Name[i], Name[r]);
    j = i - 1;
    i++;
    for (long k = l; k <= p; k++, j--)
    {
        swap(Country[k], Country[j]);
        swap(Name[k], Name[j]);
    }
    for (long k = r - 1; k >= q; k--, i++)
    {
        swap(Country[k], Country[i]);
        swap(Name[k], Name[i]);
    }
    quicksort(Country, Name, l, j);
    quicksort(Country, Name, i, r);
}

void main()
{

  FILE *fin, *fout;
  fin = fopen("race.in", "r");
  fout = fopen("race.out", "w");
  long n;
  fscanf(fin, "%ld", &n);
  char Country[n][10], Name[n][10];

  //Ввод
  for (int i = 0; i < n; i++)
  {
    fscanf(fin, "%s", Country[i]);
    fscanf(fin, "%s", Name[i]);
  }

  //Сортировка по алфавиту
  quicksort(Country, Name, 0, n);

  //вывод
  for (int i = 0; i < n; i++)
  {
    if (i == 0)
    {
      fprintf(fout, "=== %s ===\n", Country[i]);
      fprintf(fout, "%s\n", Name[i]);
    }
    else if (strcmp(Country[i], Country[i - 1]) == 0)
    {
      fprintf(fout, "%s\n", Name[i]);
    }
    else
    {
      fprintf(fout, "=== %s ===\n", Country[i]);
      fprintf(fout, "%s\n", Name[i]);
    }
  }
}