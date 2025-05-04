package com.yupi.yuaiagent.controller;

import com.yupi.yuaiagent.app.LoveApp;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class HealthController {
    @Resource
    LoveApp loveApp;
    @GetMapping
    public String healthCheck() {
        return "ok";
    }
    @PostMapping("/love")
    public String doChat(@RequestParam  String message,@RequestParam(required = false)  String chatId) {
        return loveApp.doChatWithMysql(message, chatId);
    }

    /**
     * 内存流式RAG输出
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/rag", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithRAG(@RequestParam String message,
                                      @RequestParam(required = false) String chatId) {
        return loveApp.doChatWithRAGStream(message, chatId);
    }
    /**
     * 百炼平台流式RAG输出
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/ragCloud", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String doChatWithRAGCloud(@RequestParam String message,
                                      @RequestParam(required = false) String chatId) {
        return loveApp.doChatWithRagCloud(message, chatId);
    }

}

