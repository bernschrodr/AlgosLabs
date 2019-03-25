#include <stdio.h>
#include <stdlib.h>

// Слияние
void Merge(long long a[], long left, long mid, long right)
{
  long it1 = 0;
  long it2 = 0;
  long result[right - left];

  while ((left + it1 < mid) & (mid + it2 < right)){
    if (a[left + it1] < a[mid + it2])
    {
      result[it1 + it2] = a[left + it1];
      it1 += 1;
    }
    else
    {
      result[it1 + it2] = a[mid + it2];
      it2 += 1;
    }}
    
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

  for (long i = 0; i < it1 + it2; i++)
    a[left + i] = result[i];
}

//Рекурсивная сортировка слиянием
void MergeSort(long long a[], long left, long right)
{
  if (left + 1 >= right)
    return;
  long mid = (left + right) / 2;
  MergeSort(a, left, mid);
  MergeSort(a, mid, right);
  Merge(a, left, mid, right);
}

int main()
{

  FILE *fin, *fout;
  fin = fopen("sort.in", "r");
  fout = fopen("sort.out", "w");
  long n;
  fscanf(fin, "%ld", &n);
  long long arr[n];

//Ввод массива
  for (int i = 0; i < n; i++)
  {
    fscanf(fin, "%lld", &arr[i]);
  }

//Сортировка
  MergeSort(arr, 0, n);

//Вывод
  for (int i = 0; i < n; i++)
  {
    fprintf(fout, "%lld ", arr[i]);
  }

  fclose(fin);
  fclose(fout);
  return 0;
}