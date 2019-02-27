package com.zh;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/18 10:53
 */
public class Switch {
    private static final String ENCODE_RULES = "zheng";
    /**
     * 当遇到 break语句时，switch语句终止。程序跳转到switch语句后面的语句执行。case语句不必须要包含break语句。如果没有break语句出现，程序会继续执行下一条case语句，直到出现
     * break语句
     * switch case 语句判断一个变量与一系列值中的某个值是否相等，每个值称为一个分支
     * @param args
     */
//    public static void main(String args[]){
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
//        int i=1;
//        switch (i){
//            case 0:
//                System.out.println("0");
//            case 1:
//                System.out.println("1");
//            case 2:
//                System.out.println("2");
//            default:
//                System.out.println("default");
//        }
        // 输出结果 default

        //如果当前匹配成功的case语句块中没有break语句，则从当前case开始，后续所有case的值都会输出
        //如果后续的case语句块有break语句则会跳出判断
//        int i=1;
//        switch (i){
//
//            case 1:
//                System.out.println("1");
//            case 2:
//                System.out.println("2");
//            case 3:
//                System.out.println("3");
//                break;
//            case 4:
//                System.out.println("4");
//            default:
//                System.out.println("default");
//
//        }

      //输出结果为 1 2 3

        //当default放在最前面时不被执行，只有不在最前面的时候才会执行

//        switch (i){
//
//            case 0:
//                System.out.println("0");
//                break;
//            case 1:
//                System.out.println("1");
//                break;
//            case 2:
//                System.out.println("2");
//                break;
//            default:
//                System.out.println("default");
//                break;
//        }
//        aesEncode("root");
//        int[] list={2,9,9,7,1,9,0,2,6,8};
//
//        int temp=0;
//        for (int i=0;i<list.length-1;i++){
//            boolean f=false;
//            for (int j=list.length-1;j>i;j--){
//                if (list[j-1]>list[j]){
//                    temp=list[j];
//                    list[j]=list[j-1];
//                    list[j-1]=temp;
//                    f=true;
//                }
//            }
//            if (f==false)
//                break;
//            System.out.println("第"+i+"趟"+ Arrays.toString(list));
//        }
//
//        for (int c=0;c<=10;c++) {
//            System.out.printf("Fib of %d is %d\n",c,fib((long) c));
//            System.out.printf("Fac of %d! = %d\n",c,fac((long) c));
//        }
//
//    }

    public static String aesEncode(String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(ENCODE_RULES.getBytes());
            keyGenerator.init(128, random);
            //3.产生原始对称密钥
            SecretKey originalKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String aesEncode = new String(new BASE64Encoder().encode(byteAES));
            System.out.println(aesEncode);
            //11.将字符串返回
            return aesEncode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }



    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String aesDecode(String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(ENCODE_RULES.getBytes());
            keygen.init(128, random);
            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byteDecode = cipher.doFinal(byteContent);
            String aesDecode = new String(byteDecode, "utf-8");
            return aesDecode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("兄弟，配置文件中的密码需要使用AES加密，请使用com.zheng.common.util.AESUtil工具类修改这些值！");
            //e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }
    public static void main(String[] args) {
        String[] keys = {
                "", "root"
        };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print(key + " | ");
            String encryptString = aesEncode(key);
            System.out.print(encryptString + " | ");
            String decryptString = aesDecode(encryptString);
            System.out.println(decryptString);
        }
    }



    public static long fib(Long number){
        if ((number==0)||(number==1)){
            return number;
        }
        else{
            return fib(number-1)+fib(number-2);
        }

    }

    public static long fac(Long number){
        if ((number==0)||(number==1)){
            return 1;
        }
        else {
            return fac(number-1)*number;
        }
    }

}
