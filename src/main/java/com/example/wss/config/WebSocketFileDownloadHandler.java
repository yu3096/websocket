package com.example.wss.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.File;
import java.nio.ByteBuffer;

@Component
public class WebSocketFileDownloadHandler extends BinaryWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        logger.debug("message {}", message);
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = null; //{fileTarget: aaa, fileName: bbb}
        try {
            jsonObj = (JSONObject) jsonParser.parse(message.getPayload().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        File file = new File("C:\\study\\jhvjhvkkvjhvjhk\\src\\main\\resources\\download.file.path\\" + jsonObj.get("fileTarget"));
        logger.debug("-->{}", file.exists());
        logger.debug("-->{}", file.length());
        byte[] fileBytes = new byte[(int) file.length()];
        ByteBuffer bb = ByteBuffer.wrap(fileBytes);

        try {
            this.handleBinaryMessage(session, new BinaryMessage(bb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("afterConnectionEstablished - {}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.debug("handleMessage - {} {}", session.getId(), message.getPayload());
    }



    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.debug("handleTransportError - {} {}", session.getId(), exception.getLocalizedMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("afterConnectionClosed - {} {}", session.getId(), closeStatus.getReason());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
     */
}
