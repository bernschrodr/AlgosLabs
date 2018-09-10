#include <stdio.h>
#include <string.h>

int main(void)
{
    int w, h, i, j;

    FILE *fin, *fout;
    fin = fopen("turtle.in", "r");
    fout = fopen("turtle.out", "w");
    fscanf(fin, "%d %d", &w &h);
    int A[h,w], B[h,w];

    fprintf(fout, "");
    fclose(fin);
    fclose(fout);
    return 0;
}