package com.zh;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/18 10:53
 */
public class Switch {
    /**
     * 当遇到 break语句时，switch语句终止。程序跳转到switch语句后面的语句执行。case语句不必须要包含break语句。如果没有break语句出现，程序会继续执行下一条case语句，直到出现
     * break语句
     * switch case 语句判断一个变量与一系列值中的某个值是否相等，每个值称为一个分支
     * @param args
     */
    public static void main(String args[]){
    //switch语句可以包含一个default分支，该分支一般是switch语句的最后一个分支（可以在任何位置，但建议在最后一个）
        //default在没有case语句的值和变量值相等的时候执行。default分支不需要break语句
//            char grade='B';
//            switch (grade){
//                case 'A':
//                    System.out.println("优秀");
//                    break;
//                case 'B':
//                case 'C':
//                    System.out.println("良好");
//                    break;
//                case 'D':
//                    System.out.println("及格");
//                    break;
//            }
//            System.out.println("你的等级是"+grade);

        //switch语句中的变量类型可以是：byte、short、int或者char。从Java SE 7开始，switch支持字符串string类型，同时case标签必须为字符串常量或者字面量

//如果case语句块中没有break语句时，jvm并不会顺序输出每一个case对应的返回值，而是继续匹配，匹配不成功则返回默认case
        int i=1;
        switch (i){
            case 0:
                System.out.println("0");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            default:
                System.out.println("default");
        }
        // 输出结果 default

        //如果当前匹配成功的case语句块中没有break语句，则从当前case开始，后续所有case的值都会输出
        //如果后续的case语句块有break语句则会跳出判断
//        int i=1;
        switch (i){

            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
            default:
                System.out.println("default");

        }

      //输出结果为 1 2 3

        //当default放在最前面时不被执行，只有不在最前面的时候才会执行

        switch (i){

            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
