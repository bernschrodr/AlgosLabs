#include <stdlib.h>
#include <stdio.h>

int main(void)
{

    int masiv[1000][1000];
    int arrr[1000][1000];
    int dp[1000][1000];

    FILE *fin, *fout;
    fin = fopen("turtle.in", "r");
    fout = fopen("turtle.out", "w");

    int h, w;
    fscanf(fin, "%d %d", &h, &w);

    
    for (int i = 0; i < h; i++)
        for (int j = 0; j < w; j++){
            fscanf(fin, "%d", &masiv[i][j]);
            printf("%d ", masiv[i][j]);}
   
    printf("\n");

    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            arrr[i][j] = masiv[h - 1 - i][j];
            printf("%d ", arrr[i][j]);
        }
    }

    int res = 0;

    for (int i = 0; i < h; i++)
    {
        res += arrr[i][0];
        dp[i][0] = res;
    }
/*
    res = 0;
    for (int j = 0; j < w; j++)
    {
        res += arrr[0][j];
        dp[0][j] = res;
    } 

    for (int i = 1; i < h; i++)
        for (int j = 1; j < w; j++)
        {
            if (dp[i - 1][j] > dp[i][j - 1])
            {
                dp[i][j] = dp[i - 1][j] + arrr[i][j];
            }
            else
                dp[i][j] = dp[i][j - 1] + arrr[i][j];
        }

    fprintf(fout, "%d", dp[h - 1][w - 1]);*/
    fclose(fin);
    fclose(fout);
    
    return 0;
    }