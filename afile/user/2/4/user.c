#include <stdio.h>
#include <stdlib.h>

int main()
{
    int numberSize, i, target;
    scanf("%d", &numberSize);
    int* numbers = (int *)malloc(sizeof(int )* numberSize);
    for (i = 0; i < numberSize; i++)
    {
        scanf("%d", numbers+i);
    }
    scanf("%d", &target);
    int* ret = (int*)malloc(sizeof(int) * 2);

    int low = 0, high = numberSize - 1;
    while (low < high) {
        int sum = numbers[low] + numbers[high];
        if (sum == target) {
            ret[0] = low + 1, ret[1] = high + 1;
            break;
        } else if (sum < target) {
            ++low;
        } else {
            --high;
        }
    }
    for (i = 0; i < 2; i++)
    {

        printf("%d", ret[i]);
        if(i != 1)
        {
            printf(" ");
        }
    }
    free(numbers);
    return 0;
}