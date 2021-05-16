

void fun()
{
    int i;
     i = x + y;
}


typedef int ElementType;

 struct StackInfo
{
    int topOfStack;
    ElementType stack[1][12];
    char* s;
};
ElementType stack[64][12];

typedef struct StackInfo StackInfo_st;


int stack_push(StackInfo_st *s,ElementType value);
int stack_pop(StackInfo_st *s,ElementType *value);
int stack_top(StackInfo_st *s,ElementType *value);
int stack_is_full(StackInfo_st *s);
int stack_is_empty(StackInfo_st *s);



int stack_push(struct StackInfo *s,ElementType value)
{
    int i;
    char* j;
//    int k;
//    int a[];
//
//    a[i][k] += 10;

//    *j = 10;
j = &i;
*i = 10;


for(i = 0; i < 10; i++)
{
}




    while(i < 11 && i > 1)
    {

        if(i != 10)
        {
                printf("%d", i);

        }

    }
    return *i;
}


int stack_pop(StackInfo_st *s,ElementType *value)
{

    if(stack_is_empty(s))
        return -1;
    *value = s->stack[s->topOfStack];
    s->topOfStack--;
    return 0;
}


int stack_top(StackInfo_st *s,ElementType *value)
{

    if(stack_is_empty(s))
        return -1;
    *value = s->stack[s->topOfStack];
    return 0;
}


int stack_is_full(StackInfo_st *s)
{
    return s->topOfStack == 64 - 1;
}


int stack_is_empty(StackInfo_st *s)
{
    return s->topOfStack == - 1;
}
int main(void)
{


    StackInfo_st stack;
    stack.topOfStack = -1;


    if(stack_is_empty(&stack))
    {
        printf("push value 1\n");
        stack_push(&stack,1);
    }


    int topVal;
    stack_top(&stack, &topVal);
    printf("top value %d\n",topVal);


    int popVal;
    stack_pop(&stack, &popVal);
    printf("pop value %d\n",popVal);
    int i = 0;
    while(0 == stack_push(&stack,i))
    {
        i++;
    }
    printf("stack is full,topOfStack is发发发 %d\n",stack.topOfStack);

    return 0;
}
