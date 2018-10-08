#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void swap(char a[10],char b[10]){
  char temp[10];
  strcpy(temp,b);
  strcpy(b,a);
  strcpy(a,temp);
}

 void quicksort (char a[][2][10], long l, long r){
     char v[10];
     strcpy(v,a[r]);
     if (r <= l)
        return;
     long i = l;
     long j = r - 1;
     long p = l - 1;
     long q = r;
     while (i <= j){ 
        while (strcmp(a[i],v) < 0) 
           i++;
        while (strcmp(a[j],v) > 0) 
           j--;
        if (i >= j)
          break;
        swap(a[i], a[j]);
        if (strcmp(a[i],v) == 0)
           p++;
           swap(a[p], a[i]);
        i++;
        if (strcmp(a[j],v) == 0);
           q--;
           swap(a[q], a[j]);
        j--;}
     swap(a[i], a[r]);
     j = i - 1;
     i++;
     for (long k = l; k <= p; k++, j--) 
        swap(a[k], a[j]);
     for (long k = r - 1; k >= q; k--, i++) 
        swap(a[k], a[i]); 
     quicksort(a, l, j);
     quicksort(a, i, r);}

     
void main()
{

    FILE *fin, *fout;
    fin = fopen("race.in", "r");
    fout = fopen("race.out", "w");
    long n;
    long k = 0;
    fscanf(fin, "%ld", &n);
    char Input[n][3][10], InputOne[200][10];
    
    //Ввод
    for (int i = 0; i < n; i++)
    {
        fscanf(fin, "%s", Input[i][1]); //Страна
        fscanf(fin, "%s", Input[i][2]); //Имя
        Input[i][3][1] = i; //Позиция
    }

    
    //Сортировка по алфавиту
    quicksort (Input, 0, n);
    
    for(int i=0; i<n;i++) printf("%s", Input[i]);

    //Копируем первый элемент для сравнения 
    strcpy(InputOne[0], Input[0]);

    //Создаем массив без повторяющихся элементов
    for (int i = 1; i <= n; i++)
        if (strcmp(Input[i], Input[i - 1]) != 0)
        {
            ++k;
            strcpy(InputOne[k], Input[i]);
        }

    //вывод
    for(long i = 0; i < k; i++){
        fprintf(fout, "=== %s ===\n", InputOne[i]);
        for(long j = 0; j < n; j++){
            if(strcmp(InputOne[i], Input[j]) == 0){
                fprintf(fout, "%s\n", Input[j]);
            }
        }

    }
}