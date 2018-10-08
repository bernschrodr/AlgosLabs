#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void Merge(char Country[][10], char Name[][10], long Left, long Mid, long Right)
{
	long i, j, k;
	long n1 = Mid - Left + 1;
	long n2 = Right - Mid;

	char Lcountries[Mid][10];
	char Lsurnames[Mid][10];
	char Rcountries[Mid][10]; 
	char Rsurnames[Mid][10]; 

	for (i = 0; i < n1; i++)
	{
		
		strcpy(Lcountries[i], Country[Left + i] );
		strcpy(Lsurnames[i], Name[Left + i]);
	}
	for (j = 0; j < n2; j++)
	{
		
		strcpy(Rcountries[j], Country[Mid + 1 + j] );
		strcpy(Rsurnames[j], Name[Mid + 1 + j] );
	}

	i = 0;
	j = 0;
	k = Left;

	while (i < n1 && j < n2)
	{
		if (Lcountries[i] <= Rcountries[j])
		{
      strcpy(Country[k], Lcountries[i]);
      strcpy(Name[k], Lsurnames[i]);
			k++;
			i++;
		}
		else
		{
       strcpy(Country[k], Rcountries[j]);
      strcpy(Name[k], Rsurnames[j]);       
			k++;
			j++;
		}
	}

	while (i < n1)
	{
    strcpy(Country[k], Lcountries[i]); 
    strcpy(Name[k],Lsurnames[i]);
		k++;
		i++;
	}
	while (j < n2)
	{
    strcpy(Country[k], Rcountries[j]); 
		strcpy(Name[k], Rsurnames[j]);
		k++;
		j++;
	}
}

void MergeSort(char Country[][10], char Name[][10], long Left, long Right)
{
	if (Right > Left)
	{
		int Mid = (Right + Left) / 2;
		MergeSort(Country, Name, Left, Mid);
		MergeSort(Country, Name, Mid + 1, Right);

		Merge(Country, Name, Left, Mid, Right);
	}
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
  for (long i = 0; i < n; i++)
  {
    fscanf(fin, "%s", Country[i]);
    fscanf(fin, "%s", Name[i]);
  }

  //Сортировка по алфавиту
  MergeSort(Country, Name, 0, n-1); 

  //вывод
  for (long i = 0; i < n; i++)
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