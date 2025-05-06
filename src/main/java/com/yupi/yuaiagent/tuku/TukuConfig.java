package com.yupi.yuaiagent.tuku;

import com.yupi.yuaiagent.mapper.ConversationMapper;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TukuConfig {
    private ChatClient chatClient;
    private final ChatModel dashscopeChatModel;
    @Resource
    private ChatMemory MysqlBasedChatMemory;
    @Resource
    PictureTools pictureTools;
    /**
     * 初始化 ChatClient
     *
     * @param dashscopeChatModel
     */
    public TukuConfig(ChatModel dashscopeChatModel) {
        this.dashscopeChatModel = dashscopeChatModel;
    }
    @Bean
    public ChatClient pictureChatClient() {
        return ChatClient.builder(dashscopeChatModel)
                .defaultSystem("你是一个乐于助人的AAI")
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(MysqlBasedChatMemory), // CHAT MEMORY
                        new SimpleLoggerAdvisor())
                .defaultTools(pictureTools)
                .build();
    }

}
