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
        //移除元素
        remove();
        //利用removeAll来移除两个数组之间的差集
        removeAll();
        //判断两个数组是否相等
        equals();

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
    //删除数组元素
    private static void remove(){
        ArrayList<String> strings=new ArrayList<>();
        strings.clear();
        strings.add(0,"第1个元素");
        strings.add(1,"第二个元素");
        strings.add(2,"第三个元素");
        System.out.println(strings+"移除前");
        strings.remove(0);
        strings.remove("第二个元素");
        System.out.println(strings+"移除后");
    }
    //使用removeAll()方法来计算两个数组之间的差集
    private static void removeAll(){
        ArrayList obj1=new ArrayList();
        ArrayList obj2=new ArrayList();
        obj1.add(0,1);
        obj1.add(1,2);
        obj1.add(2,3);
        obj1.add(3,4);

        obj2.add(0,1);
        obj2.add(1,3);

        System.out.println("两个数组之间的交集"+obj1.retainAll(obj2)+obj1);

        System.out.println("是否包含元素"+obj1.contains(1)+"  ,,..  ");
        System.out.println(obj1+"移除差集前");
        obj1.removeAll(obj2);
        System.out.println(obj1+"移除差集后");
    }
    //利用equals方法来判断数组是否相等
    private static void equals(){
        int ary[]={1,2,3,4,5,6};
        int ary1[]={1,2,3,4,5,6};
        int ary2[]={1,2,3,4,5};

        System.out.println("ary与ary1两个数组是否相等"+Arrays.equals(ary,ary1));

        System.out.println("ary与ary2两个数组是否相等"+Arrays.equals(ary,ary2));

    }

}
