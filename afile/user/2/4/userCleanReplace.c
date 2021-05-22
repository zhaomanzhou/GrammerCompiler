
int* twoSum(int* numbers, int numbersSize, int target, int* returnSize) {
    int* ret = (int*)malloc(sizeof(int) * 2);
    *returnSize = 2;

    int low = 0, high = numbersSize - 1;
    while (low < high) {
        int sum = numbers[low] + numbers[high];
        if (sum == target) {
            ret[0] = low + 1;
            ret[1] = high + 1;
            return ret;
        } else if (sum < target) {
            ++low;
        } else {
            --high;
        }
    }
    ret[0] = -1;
    ret[1] = -1;
    return ret;
}



int main()
{
    int numberSize, i, target, returnSize;
    scanf("%d", &numberSize);
    int* numbers = (int *)malloc(sizeof(int )* numberSize);
    for (i = 0; i < numberSize; i++)
    {
        scanf("%d", numbers+i);
    }
    scanf("%d", &target);
    int* result = twoSum(numbers, numberSize, target, &returnSize);
    for (i = 0; i < returnSize; i++)
    {

        printf("%d", result[i]);
        if(i != returnSize - 1)
        {
            printf(" ");
        }
    }
    free(numbers);
    free(result);
    return 0;
}
