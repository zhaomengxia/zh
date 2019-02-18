package com.zh.util;

//import com.linkage.netmsg.NetMsgclient;
//import com.linkage.netmsg.server.ReceiveMsg;

import java.util.List;


/**
 * Copyright 2016 envCloud Inc.
 *
 * @author DrJoseph
 * @description:
 */

public class MsgSenderUtil {

    private static String msgUserName = "0512C00188259";

    private static String msgPassword = "135790";


    public static void sendMag(List<String> telephoneNums, String msgContent){
//        NetMsgclient client   = new NetMsgclient();
//
//        /*ReceiveMsgImpl为ReceiveMsg类的子类，构造时，构造自己继承的子类就行*/
//        ReceiveMsg receiveMsg = new ReceiveDemo();
//
//        /*初始化参数*/
//        client = client.initParameters("202.102.41.101", 9005, msgUserName, msgPassword, receiveMsg);
//
//        try {
//
//            /*登录认证*/
//            boolean isLogin = client.anthenMsg(client);
//            if(isLogin){
//                System.out.println("login sucess");
//            }else {
//                return;
//            }
//
//            for(int i =0 ; i < telephoneNums.size(); i++)
//            {
//                String telephoneNum = telephoneNums.get(i);
//	            /*发送下行短信*/
//                client.sendMsg(client, 0, telephoneNum, msgContent, 1);
//            }
//            //关闭发送短信与接收短信连接
//            client.closeConn();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }


    }


}
