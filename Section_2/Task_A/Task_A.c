#include <stdio.h>
#include <stdlib.h>


// Функция сортировки нисходящим слиянием
void mergeSort(long long *a, long l, long r)
{
  if (l == r) return; // границы сомкнулись
  int mid = (l + r) / 2; // определяем середину последовательности
  // и рекурсивно вызываем функцию сортировки для каждой половины
  mergeSort(a, l, mid);
  mergeSort(a, mid + 1, r);
  int i = l;  // начало первого пути
  int j = mid + 1; // начало второго пути
  long long *tmp = (long long*)malloc(r * sizeof(long long)); // дополнительный массив
  for (int step = 0; step < r - l + 1; step++) // для всех элементов дополнительного массива
  {
    // записываем в формируемую последовательность меньший из элементов двух путей
    // или остаток первого пути если j > r
    if ((j > r) || ((i <= mid) && (a[i] < a[j]))) 
    {
      tmp[step] = a[i];
      i++;
    }
    else 
    {
      tmp[step] = a[j];
      j++;
    }
  }
  // переписываем сформированную последовательность в исходный массив
  for (int step = 0; step < r - l + 1; step++)
    a[l + step] = tmp[step];
}

int main()
{

    FILE *fin, *fout;
    fin = fopen("sort.in", "r");
    fout = fopen("sort.out", "w");
    long n;
    fscanf(fin, "%ld", &n);
    long long arr[n], arr2[n];

    for (int i = 0; i < n; i++)
    {
        fscanf(fin, "%lld", &arr[i]);
    }

    mergeSort(arr,1,n);

    for (int i = 0; i < n; i++)
    {
        fprintf(fout, "%lld ", arr[i]);
    }

    fclose(fin);
    fclose(fout);
    return 0;
}