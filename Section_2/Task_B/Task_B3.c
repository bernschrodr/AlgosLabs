#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int compare(char x1[], char x2[]){
  return strcmp(x1,x2);
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
     qsort(SortedCountries[0], n, 10*sizeof(char), compare);

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