package com.yupi.yuaiagent.controller;

import com.yupi.yuaiagent.app.LoveApp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

}
