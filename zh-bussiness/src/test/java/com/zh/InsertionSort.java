package com.zh;

import java.util.Random;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/3/4 10:22
 */
public class InsertionSort {

    public static void main(String[] args) {
//        insertSort();
//        selectSort();
//        binarySearch();
//        chaek();
        randomArray();
    }


    /**
     * 插入排序
     */
    public static void insertSort() {
        int array[] = {8, 2, 4, 9, 3, 6};
        int len = array.length - 1;
        int counter = 1;
        for (int i = 1; i < len; i++) {
            int temp = array[i];//待排序的元素的值
            int insertPoint = i - 1;//与待排序的元素值作比较的元素的下标
            while (insertPoint >= 0 && array[insertPoint] > temp) {
                array[insertPoint + 1] = array[insertPoint];//当前元素后移一位
                insertPoint--;
            }
            array[insertPoint + 1] = temp;
            for (int j = 0; j <= len; j++) {
                System.out.print(array[j] + "  ");
            }
            System.out.println(counter + "插入排序");
            counter++;
        }
    }

    /**
     * 选择排序
     */
    public static void selectSort() {
        int array[] = {20, 40, 30, 10, 60, 50};
        //第一次是从第二个元素开始查找最小的元素与第一个元素比较，如果比第一个元素小则跟第一个元素进行交换，
        // 第二次是从第三个元素开始查找得到最小的元素与第二个元素进行比较，如果比第二个元素小，则将第二个元素与这个最小元素进行交换
        for (int i = 0; i < array.length; i++) {
            int temp = 0;
            int minPoint = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minPoint] > array[j]) {
                    minPoint = j;
                }
            }
            if (minPoint != i) {
                temp = array[i];
                array[i] = array[minPoint];
                array[minPoint] = temp;
            }
            for (int a = 0; a <= array.length - 1; a++) {
                System.out.print(array[a] + "  ");
            }
            System.out.println("选择排序");
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            for (int a = 0; a < array.length; a++) {
                System.out.print(array[a] + " ");
            }
            System.out.println("冒泡排序");
        }
    }

    /**
     * 二分查找
     */
    public static int binarySearch() {
        int target = 2;
        int array[] = {3, 6, 7, 8, 9, 1, 5, 4};
        bubbleSort(array);//先排序
        //搜索段下标最小值
        int low = 0;
        //搜索段下标最大值
        int high = array.length - 1;
        //当前要搜索元素的下标
        int normal;
        while (true) {
            normal = (low + high) / 2;
            if (high <= 0) {//如果数组为空
                System.out.println("不存在");
                return -1;
            }
            if (array[normal] == target) {//如果当前搜索的这个元素正好相等，则输出
                System.out.println(normal + ",,,..." + array[normal]);
                return array[normal];
            }
            if (array[low] > target) {//排好序之后的最小值都比要搜索的值大
                System.out.println("不存在");
            } else {//且比排好序之后的最小值大
                if (array[normal] < target) {
                    low = normal;//则将这个normal值赋给搜索段下标的最小值
                } else if (array[normal] > target) {
                    high = normal;//如果当前搜索元素的值大于要搜索的元素
                } else if (array[high] > target) {//排好序之后的最大值比要搜索的值大，
                    normal = (low + high) / 2;
                } else if (array[high] < target) {//排好序之后的最大值都比要搜索的值小
                    System.out.println("不存在");
                    return -1;
                }
            }
        }
    }

    //出栈，入栈
    public static void chaek() {
        String input = "a{b(c[d]e)f}";//字符串
        Stack stack = new Stack(input.length());
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '{':
                case '(':
                case '[':
                    stack.push(ch);
                    break;
                case ']':
                case ')':
                case '}':
                    if (!stack.isEmpty()) {
                        char ch1 = stack.pop();//读取栈顶元素
                        if ((ch == '{' && ch1 != '}') || (ch == '(' && ch1 != '(') || (ch == '[' && ch1 != ']')) {
                            System.out.println("匹配出错，字符：" + ch + ",下标" + i);
                        }
                    } else {
                        System.out.println("匹配出错，字符：" + ch + ",下标" + i);
                    }
                default:
                    break;
            }
        }
    }

    /**
     * 生成随机数组
     */
    public static void randomArray() {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            int index = random.nextInt(array.length);
            int move = array[i];
            array[i] = array[index];
            array[index] = move;
        }
        for (int v : array) {
            System.out.print(v + "   ");
        }
    }

    /**
     * 快速排序
     *
     * @param low
     * @param high
     */
    public int[] quickSort(int low, int high) {
        //快速排序的原理：首先找好基数prot，然后找到搜索段的最小下标low，和搜索段的最大下标high，第一次循环是，基数为array[0],low=0,high=array.length-1
        int array[] = {57, 68, 59, 52, 72, 28, 96, 33, 24, 19};
        int prot = 0;
        for (int i = 0; i < array.length; i++) {
            prot = array[i];
           int partion=partion(low,high,prot,array);
            quickSort(low, partion-1);
            quickSort(partion, high);
        }
        return array;
    }

    public int partion(int low, int high, int port, int[] array) {
        //首先找到  从前面扫描比基数大的数
        while (low <= high) {
            while (array[low] <= port) {
                low++;
            }
            //此时将array[i]放到port的左边，将两个互换位置
            exchange(array[low], port);
            while (array[high] >=port) {
                high--;
            }
            //此时将array[i]放到port的右边，将两者互换位置
            exchange(array[high], port);
        }
        return low;
    }
    public boolean exchange(int a, int b) {
        int temp = a;
        b = a;
        b = temp;
        return true;
    }
}
