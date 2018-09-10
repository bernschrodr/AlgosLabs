#include <stdio.h>
#include <string.h>

int main(void)
{
    int n, i, j, min, max, between;

    FILE *fin, *fout;
    fin = fopen("sortland.in", "r");
    fout = fopen("sortland.out", "w");
    fscanf(fin, "%d", &n);

    double money[n], money_i[n];

    //получаем массив
    for (i = 1; i <= n; i++)
    {
        fscanf(fin, "%lf", &money[i]);
    }

    //копируем массив, в котором индексы останутся неизменными
    memcpy(money_i, money, sizeof(money));

    //сортировка
    for (i = 1; i < n; i++)
    {
        for (j = 1; j < n - i; j++)
        {
            if (money[j] > money[j + 1])
            {
                double tmp = money[j];
                money[j] = money[j + 1];
                money[j + 1] = tmp;
            }
        }
    }

    for( i = 0; i <= n; i++){ printf("%f ",money_i[i]);
    }
    printf("\n");
    for( i = 0; i <= n; i++){ printf("%f ",money[i]);
    }

    for (i = 1; i <= n; i++){
        if (money_i[i] == money[1])
            min = i;
            }

    for (i = 1; i <= n; i++){
        if (money[n] == money_i[i])
            max = i;
            }

    for (i = 1; i <= n; i++){
        if (money_i[i] == money[n / 2])
            between = i+1;}

    fprintf(fout, "%d %i %d", min, between, max);
    fclose(fin);
    fclose(fout);
    return 0;
}