package com.unuse.message.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.unuse.message.api.Message;
import com.unuse.message.mapper.MessageMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Unuse on 2017/7/4.
 */

public class MessageServer {

    private static SocketIOServer server;

    private static Map<Long, SocketIOClient> clientsMap;

    @Autowired
    private MessageMapper messageMapper;

    private Logger logger = Logger.getLogger(MessageServer.class);

    public void main(String[] args) throws InterruptedException {
        startServer();
    }

    public void startServer() throws InterruptedException {
        if (null != server) {
            return;
        }

        logger.info("初始化Socket----------------");
//        System.out.println("初始化Socket----------------");

        // 初始化Socket
        Configuration config = new Configuration();
        config.setPort(9090);
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        server = new SocketIOServer(config);

        // 初始化链接的客户端Cache
        clientsMap = new HashMap<Long, SocketIOClient>();

        // 添加连接事件
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                String sa = client.getRemoteAddress().toString();
                String clientIp = sa.substring(1, sa.indexOf(":")); //获取设备ip

                logger.info(clientIp + "-------------------------客户端已连接");
//                System.out.println(clientIp + "-------------------------客户端已连接");

                // 获取连接的uid
                Map<String, List<String>>  params = client.getHandshakeData().getUrlParams();
                Long uid = Long.valueOf(params.get("uid").get(0));
                if (null != uid) {
                    clientsMap.put(uid, client);

                    logger.info("uid: " + uid + " connected ");
//                    System.out.println("uid: " + uid + " connected ");
                }
            }
        });

        // 添加断开连接事件
        server.addDisconnectListener(new DisconnectListener() {
            public void onDisconnect(SocketIOClient client) {
                String sa = client.getRemoteAddress().toString();
                String clientIp = sa.substring(1, sa.indexOf(":"));//获取设备ip
                logger.info(clientIp + "-------------------------客户端已断开连接");

                // 获取断开连接的uid
                Map<String, List<String>>  params = client.getHandshakeData().getUrlParams();
                Long uid = Long.valueOf(params.get("uid").get(0));
                if (null != uid) {
                    clientsMap.remove(uid);

                    logger.info("uid: " + uid + " disconnected ");
//                    System.out.println("uid: " + uid + " disconnected ");
                }
            }
        });

        // 添加接收消息事件
        server.addEventListener("message", Message.class, new DataListener<Message>() {
            public void onData(SocketIOClient client, Message data, AckRequest ackSender) throws Exception {
                // 获取uid
                Map<String, List<String>>  params = client.getHandshakeData().getUrlParams();
                Long uid = Long.valueOf(params.get("uid").get(0));
                if (null != uid) {
                    logger.info("uid: " + uid + "发送了一条消息");
//                    System.out.println("uid: " + uid + " 发送了一条消息 ");

                    Date now = new Date();
                    data.setCreateTime(now);
                    data.setUpdateTime(now);
                    data.setStatus(1);
                    messageMapper.addMessage(data);
                    sendMessageToClients("message", data);
                }
            }
        });

        server.start();

    }

    public void stopServer() {
        if(server != null){
            server.stop();
            server = null;
        }
    }

    public void sendMessageToClients(String event, Message message) {
        if (null == message) {
            logger.error("消息不能为空!");
            return;
        }

        SocketIOClient client = null;
        for (Map.Entry entry : clientsMap.entrySet()) {
            client = (SocketIOClient) entry.getValue();
            client.sendEvent(event, message);
        }

    }

    public void sendMessageToOneClient(String event, Message message) {
        if (null == message) {
            logger.error("消息不能为空!");
            return;
        }

        SocketIOClient client = clientsMap.get(message.getTo());
        if (null == client) {
            logger.error("客户端不存在!");
        } else {
            client.sendEvent(event, message);
        }
    }

}
