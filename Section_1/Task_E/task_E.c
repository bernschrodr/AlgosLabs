#include <stdio.h>
#include <string.h>

int main(void)
{
    int n, i, j, tmp, min, max, between;

    FILE *fin, *fout;
    fin = fopen("sortland.in", "rb");
    fout = fopen("sortland.out", "w");
    fscanf(fin, "%d", &n);

    float money[n-1], money_i[n-1];

    //получаем массив
    for (i = 0; i < n; i++)
    {
        fscanf(fin, "%f", &money[i]);
    }

    //копируем массив, в котором индексы останутся неизменными
    memcpy(money_i, money, n*sizeof(float));

    //сортировка
    for (i = 0; i < n - 1; i++)
    {
        for (j = 0; j < n - i - 1; j++)
        {
            if (money[j] > money[j + 1])
            {
                float tmp = money[j];
                money[j] = money[j + 1];
                money[j + 1] = tmp;
            }
        }
    }

    for (i = 0; i < n; i++) {
        if (money_i[i] == money[0])
            min = i+1;}

    for (i = 0; i < n; i++){
        if (money_i[i] == money[n - 1])
            max = i+1;}

    for (i = 0; i < n; i++){
        if (money[n / 2] == money_i[i])
            between = i+1;}
            
    fprintf(fout, "%d %i %d", min, between, max);
    fclose(fin);
    fclose(fout);
    return 0;
}