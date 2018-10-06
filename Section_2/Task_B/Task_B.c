#include <stdio.h>
#include <string.h>


// Слияние
void Merge(char a[][10], long left, long mid, long right)
{
  long it1 = 0;
  long it2 = 0;
  char result[right - left][10];

  while ((left + it1 < mid) & (mid + it2 < right)){
    if (strcmp (a[left + it1], a[mid + it2]) < 0)
    {
      strcpy(result[it1 + it2],a[left + it1]);
      it1 += 1; 
    }
    else
    {
      strcpy(result[it1 + it2],a[mid + it2]);
      it2 += 1;
    }}
    
  while (left + it1 < mid)
  {
    strcpy(result[it1 + it2], a[left + it1]);
    it1 += 1;
  }

  while (mid + it2 < right)
  {
    strcpy(result[it1 + it2], a[mid + it2]);
    it2 += 1;
  }

  for (long i = 0; i < it1 + it2; i++)
    strcpy(a[left + i], result[i]);
}

//Рекурсивная сортировка слиянием
void MergeSort(char a[][10], long left, long right)
{
  if (left + 1 >= right)
    return;
  long mid = (left + right) / 2;
  MergeSort(a, left, mid);
  MergeSort(a, mid, right);
  Merge(a, left, mid, right);
}


void main()
{

    FILE *fin, *fout;
    fin = fopen("race.in", "r");
    fout = fopen("race.out", "w");
    long n;
    long k = 0;
    fscanf(fin, "%ld", &n);
    char Country[n][10], Name[n][10], SortedCountries[n][10], SortedCountriesOne[200][10];
    
    //Ввод
    for (int i = 0; i < n; i++)
    {
        fscanf(fin, "%s", Country[i]);
        fscanf(fin, "%s", Name[i]);
    }

    //Копируем массив, который будем сортировать
    memcpy (SortedCountries, Country, sizeof(Country)); 
    
    //Копируем первый элемент для сравнения 
    strcpy(SortedCountriesOne[0], Country[0]);

    //Сортировка по алфавиту
    MergeSort(SortedCountries, 0, n);

    //Создаем массив без повторяющихся элементов
    for (int i = 1; i <= n; i++)
        if (strcmp(SortedCountries[i], SortedCountries[i - 1]) != 0)
        {
            ++k;
            strcpy(SortedCountriesOne[k], SortedCountries[i]);
        }

    //вывод
    for(long i = 0; i < k; i++){
        fprintf(fout, "=== %s ===\n", SortedCountriesOne[i]);
        for(long j = 0; j < n; j++){
            if(strcmp(SortedCountriesOne[i], Country[j]) == 0){
                fprintf(fout, "%s\n", Name[j]);
            }
        }

    }
}