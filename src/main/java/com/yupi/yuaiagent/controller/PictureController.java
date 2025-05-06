package com.yupi.yuaiagent.controller;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequestMapping("/tool/picture")
public class PictureController {
    @Resource
    ChatClient pictureChatClient;

    @RequestMapping("/getPictureByLatest")
    public Flux<String> getPictureByLatest(String prompt) {
        return pictureChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, "726dabe1-75d4-4e4e-84cf-2e4603cff7d3"))
                .stream()
                .content();
    }
}
