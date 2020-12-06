package com.example.common.controller;

import com.example.common.vo.WsMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.Base64;

@Controller
public class FileController {
    @Autowired private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/download")
    @SendTo("/file/download")
    public void download(WsMessageVo wsMessageVo) throws IOException {
        System.out.println("@@@@@@@@@@@@?");
        File file = new File("C:\\study\\jhvjhvkkvjhvjhk\\src\\main\\resources\\download.file.path\\qweqwejbrgklerjbtlkjbqw");
        //byte[] fileBytes = new byte[(int) file.length()];
        //ByteBuffer bb = ByteBuffer.wrap(fileBytes);
        //return bb.array();
        //messageTemplate.convertAndSend(bb.toString());
        //return new WsFileVo().setTmp("ljrbnqlkjbtaerljkfbasejklfabslkjfhadsbjhlfasd");

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        String fileString = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while( (len = fis.read(buffer)) != -1 ){
                baos.write(buffer, 0, len);
            }

            byte[] fileArr = baos.toByteArray();
            fileString = new String(Base64.getEncoder().encode(fileArr));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            baos.close();
            fis.close();
        }

        messageTemplate.convertAndSend(fileString);
    }
}
