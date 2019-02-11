package com.zh.task;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskApplicationTests {


    @Test
    public void contextLoads() {

    }


//    @Test
//    public static void main(String[] args){
//        String wechat="Hellis";
//        String instroduce="每日更新Java相关技术";
//        String hollis=wechat.concat(",").concat(instroduce);
//        System.out.println(hollis);
//    }

    @Test
    public static void main(String[] args){
        long t1=System.currentTimeMillis();
        String str="hollis";
        for (int i=0;i < 50000;i++){

            String s=String.valueOf(i);
            str =(new StringBuilder()).append(str).append(s).toString();
        }
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1);

    }

    private static final int TYPE_LINK = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_IMAGE_TEXT = 3;

    public class ShareItem {
        int type;
        String title;
        String content;
        String imagePath;
        String link;
    }

    public interface ShareListener {

        int STATE_SUCC = 0;
        int STATE_FAIL = 1;

        void onCallback(int state, String msg);
    }

    public void share (ShareItem item, ShareListener listener) {
        if (item != null) {
            if (item.type == TYPE_LINK) {
                // 分享链接
                if (!StrUtil.isEmpty(item.link) && !StrUtil.isEmpty(item.title)) {
//                    doShareLink(item.link, item.title, item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_IMAGE) {
                // 分享图片
                if (!StrUtil.isEmpty(item.imagePath)) {
//                    doShareImage(item.imagePath, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_TEXT) {
                // 分享文本
                if (!StrUtil.isEmpty(item.content)) {
//                    doShareText(item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_IMAGE_TEXT) {
                // 分享图文
                if (!StrUtil.isEmpty(item.imagePath) &&!StrUtil.isEmpty(item.content)) {
//                    doShareImageAndText(item.imagePath, item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else {
                if (listener != null) {
                    listener.onCallback(ShareListener.STATE_FAIL, "不支持的分享类型");
                }
            }
        } else {
            if (listener != null) {
                listener.onCallback(ShareListener.STATE_FAIL, "ShareItem 不能为 null");
            }
        }
    }



}

