package com.zh;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/3/4 14:49
 */
public class Stack {
    private int size;
    private int top;
    private char[] stackArray;

    //构造函数
    public Stack(int size){
       stackArray=new char[size];
       top=-1;
       this.size=size;
    }
    //入栈
    public void push(char elem){
        //入栈钱判断是否已满，即在入栈钱保证栈未满,这里要对异常进行处理
        stackArray[++top]=elem;
    }
   //出栈
    public char pop(){
        //出栈前判断是否有元素，即在出栈钱保证有元素，这里也要对异常进行处理
        return stackArray[top--];
    }
    //查看栈顶元素
    public char peek(){
        return stackArray[top];
    }

    //判空
    public boolean isEmpty(){
        return (top==-1);
    }
    //判满
    public boolean isFull(){
        return (top==size-1);
    }


}
