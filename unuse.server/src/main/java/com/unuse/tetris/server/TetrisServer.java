package com.unuse.tetris.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.unuse.message.server.MessageServer;
import com.unuse.tetris.api.Tetris;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Unuse on 2017/8/2.
 */
public class TetrisServer {

    private static SocketIOServer server;

    private static Map<Integer, SocketIOClient> clientsMap;

    private Logger logger = Logger.getLogger(MessageServer.class);

    public void startServer() throws InterruptedException {
        if (null != server) {
            return;
        }

        logger.info("初始化Socket----------------");
//        System.out.println("初始化Socket----------------");

        // 初始化Socket
        Configuration config = new Configuration();
        config.setPort(9091);
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        server = new SocketIOServer(config);

        // 初始化链接的客户端Cache
        clientsMap = new HashMap<Integer, SocketIOClient>();

        // 添加连接事件
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                String sa = client.getRemoteAddress().toString();
                String clientIp = sa.substring(1, sa.indexOf(":")); //获取设备ip

                logger.info(clientIp + "-------------------------客户端已连接");
//                System.out.println(clientIp + "-------------------------客户端已连接");

                // 获取连接的uid
                Map<String, List<String>>  params = client.getHandshakeData().getUrlParams();
                Integer uid = Integer.valueOf(params.get("uid").get(0));
                if (null != uid) {
                    clientsMap.put(uid, client);

                    Tetris tetris = new Tetris();
                    tetris.setTo(uid);
                    tetris.setRole(clientsMap.size()%2 == 1 ? 1 : 2);
                    sendTetrisToOneClient("user", tetris);

                    logger.info("uid: " + uid + " connected ");
//                    System.out.println("uid: " + uid + " connected ");
                }
                if (clientsMap.size() == 2) {
                    Tetris tetris = new Tetris();
                    sendTetrisToClients("start", tetris);
                    logger.info("start");
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
                Integer uid = Integer.valueOf(params.get("uid").get(0));
                if (null != uid) {
                    clientsMap.remove(uid);

                    logger.info("uid: " + uid + " disconnected ");
//                    System.out.println("uid: " + uid + " disconnected ");
                }
            }
        });

        server.addEventListener("start", Tetris.class, new DataListener<Tetris>() {
            public void onData(SocketIOClient client, Tetris data, AckRequest ackSender) throws Exception {
                Tetris result = new Tetris();
                sendTetrisToClients("start", result);
            }
        });

        server.addEventListener("newBlock", Tetris.class, new DataListener<Tetris>() {
            public void onData(SocketIOClient client, Tetris data, AckRequest ackSender) throws Exception {
                Tetris result = new Tetris();
                Random random = new Random();
                result.setType(random.nextInt(5) + 1);
                sendTetrisToClients("newBlock", result);
            }
        });

        server.addEventListener("action", Tetris.class, new DataListener<Tetris>() {
            public void onData(SocketIOClient client, Tetris data, AckRequest ackSender) throws Exception {
                System.out.println("action" + data.getAction());
                sendTetrisToClients("action", data);
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

    public void sendTetrisToClients(String event, Tetris tetris) {
        if (null == tetris) {
            logger.error("消息不能为空!");
            return;
        }

        SocketIOClient client = null;
        for (Map.Entry entry : clientsMap.entrySet()) {
            client = (SocketIOClient) entry.getValue();
            client.sendEvent(event, tetris);
        }

    }

    public void sendTetrisToOneClient(String event, Tetris tetris) {
        if (null == tetris) {
            logger.error("消息不能为空!");
            return;
        }

        SocketIOClient client = clientsMap.get(tetris.getTo());
        if (null == client) {
            logger.error("客户端不存在!");
        } else {
            client.sendEvent(event, tetris);
        }
    }


}
