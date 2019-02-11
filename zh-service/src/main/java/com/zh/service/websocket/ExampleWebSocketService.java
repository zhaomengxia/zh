package com.zh.service.websocket;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.zh.api.Result;
import com.zh.entity.sys.SysUser;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author  赵梦霞
 * @since 2019-01-16 14:44

 **/
@ServerEndpoint("/websocket/{token}")
@Component
@Slf4j
public class ExampleWebSocketService {

    private static FindByIndexNameSessionRepository repository;

    @Resource
    public void setRepository(FindByIndexNameSessionRepository repository) {
        ExampleWebSocketService.repository = repository;
    }

    /**
     * 在线人数
     */
    private static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, ExampleWebSocketService> clients = new ConcurrentHashMap<>();
    /**
     * 会话
     */
    private Session session;

    /**
     * 用户信息
     */
    private SysUser sysUser;

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) {
        //查询用户信息
        try {
            this.sysUser = (SysUser) SecurityUtil.getAuthentication(repository, token).getPrincipal();
        } catch (Exception e) {
            session.getAsyncRemote().sendText(JSONUtil.toJsonStr(Result.fail(ExceptionEnum.TOKEN_INVALID)));
        }
        onlineNumber++;
        log.info("现在来连接的客户id：" + session.getId() + "用户名：" + sysUser.getName());
        this.session = session;
        log.info("有新连接加入！ 当前在线人数" + getOnlineCount());
        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        //先给所有人发送通知，说我上线了
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("messageType", 1);
        map1.put("username", sysUser.getName());
        sendMessageAll(JSONUtil.toJsonStr(map1));

        //把自己的信息加入到map当中去
        clients.put(sysUser.getName(), this);
        //给自己发一条消息：告诉自己现在都有谁在线
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("messageType", 3);
        //移除掉自己
        Set<String> set = clients.keySet();
        map2.put("onlineUsers", set);
        sendMessageTo(JSONUtil.toJsonStr(map2), sysUser.getName());


    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
        session.getAsyncRemote().sendText(JSONUtil.toJsonStr(Result.fail("服务端发生了错误")));
        //error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(sysUser.getName());
        //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("messageType", 2);
        map1.put("onlineUsers", clients.keySet());
        map1.put("username", sysUser.getName());
        sendMessageAll(JSONUtil.toJsonStr(map1));
        log.info("有连接关闭！ 当前在线人数" + getOnlineCount());
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSONUtil.parseObj(message);
            String textMessage = jsonObject.getStr("message");
            String fromusername = jsonObject.getStr("username");
            String tousername = jsonObject.getStr("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = Maps.newHashMap();
            map1.put("messageType", 4);
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromusername);
            if (tousername.equals("All")) {
                map1.put("tousername", "所有人");
                sendMessageAll(JSONUtil.toJsonStr(map1));
            } else {
                map1.put("tousername", tousername);
                sendMessageTo(JSONUtil.toJsonStr(map1), tousername);
            }
        } catch (Exception e) {
            log.info("发生了错误了");
        }

    }


    public void sendMessageTo(String message, String toUserName) {
        for (ExampleWebSocketService item : clients.values()) {
            if (item.sysUser.getName().equals(toUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    /**
     * @param message 需要发送的消息 json格式
     * @author  赵梦霞
     * @Description 通知所有连接用户
     * @since 2019/1/17 16:31
     **/
    public void sendMessageAll(String message) throws UnifiedException {
        if (!JSONUtil.isJson(message)) {
            clients.values().parallelStream().forEach(e -> e.session.getAsyncRemote().sendText(JSONUtil.toJsonStr(message)));
        }
        clients.values().parallelStream().forEach(e -> e.session.getAsyncRemote().sendText(message));
    }

    /**
     * @author  赵梦霞
     * @Description 获取当前在线人数
     * @since 2019/1/17 16:32
     **/
    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }


}
