#include <stdio.h>

int max(int a, int b)
{
    if (a > b)
    {
        return a;
    }
    else
    {
        return b;
    }
}

int main(void)
{
    int n, h, w, i, j;
    const int size_m = 1000;
    int ans[size_m][size_m];
    int ans2[size_m][size_m];

    FILE *fin, *fout;
    fin = fopen("turtle.in", "r");
    fout = fopen("turtle.out", "w");
    fscanf(fin, "%d %d", &h, &w);

    // считываем массив
    for (i = 0; i < h; i++)
        for (j = 0; j < w; j++)
        {
            fscanf(fin, "%d", &ans[i][j]);
        }

    // обращаем порядок строк в таблице
    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            ans2[i][j] = ans[h - 1 - i][j];
        }
    }

    // суммируем каждый последующий элемент первого столбца таблицы с предыдущим
    for (i = 1; i < h; i++)
    {
        ans2[i][0] += ans2[i - 1][0];
    }

    // то же самое в первой строке таблицы
    for (i = 1; i < w; i++)
    {
        ans2[0][i] += ans2[0][i - 1];
    }

    // прибавляем максимальную сумму предыдущих возможных ходов
    for (i = 1; i < h; i++)
    {
        for (j = 1; j < w; j++)
        {
            ans2[i][j] += max(ans2[i - 1][j], ans2[i][j - 1]);
        }
    }

    fprintf(fout, "%d", ans2[h - 1][w - 1]);
    fclose(fin);
    fclose(fout);
    return 0;
}