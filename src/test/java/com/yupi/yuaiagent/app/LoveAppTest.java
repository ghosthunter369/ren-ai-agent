package com.yupi.yuaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;
    @Test
    void testProhibited() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "违禁词这是";
        String answer = loveApp.doChat(message, chatId);
    }

    @Test
    void testMysql() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "我是谁？";
        String answer = loveApp.doChatWithMysql(message,"4b761174-a33e-4df5-a2db-0ea3c508badc");
//        String answer = loveApp.doChatWithMysql(message,null);
    }
    @Test
    void testMysql2() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "我是王五，你好";
//        String answer = loveApp.doChatWithMysql(message,"67e8427d-56de-4498-ac01-a373913ad159");
        String answer = loveApp.doChatWithMysql(message,null);
    }
    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我是程序员鱼皮";
        String answer = loveApp.doChatWithMysql(message, chatId);
        // 第二轮
        message = "我想让另一半（编程导航）更爱我";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我的另一半叫什么来着？刚跟你说过，帮我回忆一下";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是程序员鱼皮，我想让另一半（编程导航）更爱我，但我不知道该怎么做";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }
}
