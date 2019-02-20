package com.zh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/20 16:08
 */
public class MainClass {
    public static  void main(String args[]){
        //输出数组长度
        outputLength();
        //数组排序
        printArray();
        //数组插入
        insertElement();
        //数组合并
        merge();
        //输出数组中最大值和最小是
        minAndMax();
        //输出数组中
        output();
        //数组反转
        reverse();
        //数组填充
        fill();
        //数组扩容
        extend();
        //找数组中重复元素
        findDupicateInArray();

    }
    //数组填充
    private static void fill(){
        int array1[]=new int[6];
        Arrays.fill(array1,100);
        System.out.println(Arrays.toString(array1)+"@");
        Arrays.fill(array1,3,6,50);//左闭右开
        System.out.println(Arrays.toString(array1)+"mm");
    }

    //数组合并
    private static void merge(){
        String[] a={"A","B","C"};
        String[] b={"D","E","F"};
        List list=new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Object[] c=list.toArray();
        System.out.println(Arrays.toString(c)+",,");
    }

    //输出数组中最大值和最小是
    private static void minAndMax(){
        Integer[] integers={8,2,7,1,4,9,5};
        int min=Collections.min(Arrays.asList(integers));
        int max=Collections.max(Arrays.asList(integers));
        System.out.println(min+",,,,,"+max);
    }
    //输出数组
    private static void output(){
        String ss[]=new String[3];
        ss[0]="AAA";
        ss[1]="BBB";
        ss[2]="CCC";
        System.out.println(Arrays.toString(ss)+",,,,,");

        for (int i=0;i<ss.length;i++){
            System.out.println(ss[i]+".....");
        }
    }
    //数组反转
    private static void reverse(){
        ArrayList<String> strings=new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");
        strings.add("F");
        System.out.println(strings+",,,,");
        Collections.reverse(strings);
        System.out.println(strings);
    }

    //输出数组长度
    private static void outputLength(){
        String[][] data=new String[3][6];
        System.out.println(data.length+"  "+data[0].length);
    }

    //数组扩容
    private static void extend(){
        String s[]={"A","B","C"};
        String t[]=new String[5];
        t[3]="D";
        t[4]="E";
        System.arraycopy(s,0,t,0,s.length);
        System.out.println(Arrays.toString(t)+"kk");
    }
    //查找数组中重复的元素
    private static void findDupicateInArray(){
        int a[]={1,2,5,5,6,6,7,2,9,2};
        int count=0;
        for (int j=0;j<a.length;j++){
            for (int k=j+1;k<a.length;k++){
                if (a[k]==a[j]){
                    count++;
                }
            }
            if (count==1){
                System.out.println("重复元素为："+a[j]);
            }
            count=0;
        }
    }
   //数组排序
    private static void printArray(){
        int array[]={2,5,-2,6,-3,8,0,-7,-9,4};
        Arrays.sort(array);
        System.out.println("数组排序的结果为："+":[length:"+array.length+"]");
        for (int i=0;i<array.length;i++){
            if (i!=0){
                System.out.print("，");
            }
            System.out.print(array[i]);
        }

       System.out.println();
    }
   //数组中插入元素
    private static int[] insertElement(){
        int original[]={2,5,-2,6,-3,8,0,-7,-9,4};
        Arrays.sort(original);
        int index=Arrays.binarySearch(original,1);
        if (index<0){
            index=-index;
        }
        else{
            index=index+1;
        }
        int l=original.length;
        int destination[]=new int[l+1];
        //将原数组index之前的元素一一赋值给新数组index位置之前的元素
        System.arraycopy(original,0,destination,0,index);
        //给将插入的数组的位置的值给赋值
        destination[index]=1;
        //将原数组index之后的元素一一赋值给新元素index+1之后的元素
        System.arraycopy(original,index,destination,index+1,l-index);
        return destination;
    }
}
