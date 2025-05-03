package com.yupi.yuaiagent.chatmemory;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.dashscope.utils.JsonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.JsonObject;
import com.yupi.yuaiagent.mapper.ConversationMapper;
import com.yupi.yuaiagent.model.domain.Conversation;
import com.yupi.yuaiagent.model.domain.DbMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Component;
import org.springframework.ai.chat.messages.Message;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 存储到数据库，根据conversationId
 */
@Component
@Slf4j
public class MysqlBasedChatMemory implements ChatMemory {
//    InMemoryChatMemory
    @Resource
    private ConversationMapper conversationMapper;
    @Override
    public void add(String conversationId, List<Message> messages) {
        //1.根据conversationId查出对应记录
        Conversation conversation = conversationMapper.selectOne(
                new QueryWrapper<Conversation>()
                        .eq("conversationId", conversationId));
        //数据库信息
        String historyMessage = conversation.getMessage();
        Message message = messages.getFirst();
        DbMessage dbMessage = new DbMessage();
        //用户信息
        if(message.getMessageType() == MessageType.USER){
            dbMessage.setMessageType(MessageType.USER);
        }else {
            //AI信息
            dbMessage.setMessageType(MessageType.ASSISTANT);
        }
        dbMessage.setMetadata(message.getMetadata());
        dbMessage.setText(message.getText());
        if(historyMessage == null){
            ArrayList<DbMessage> dbMessages = new ArrayList<>();
            dbMessages.add(dbMessage);
            JSONArray array = JSONUtil.parseArray(dbMessages);
            conversation.setMessage(array.toStringPretty());
        }else {
            List<DbMessage> dbMessages = JSONUtil.toList(JSONUtil.parseArray(historyMessage), DbMessage.class);
            dbMessages.add(dbMessage);
            String jsonStr = JSONUtil.toJsonStr(dbMessages);
            conversation.setMessage(jsonStr);
        }
        conversationMapper.updateById(conversation);
    }
    @Override
    public List<Message> get(String conversationId, int lastN) {
        Conversation conversation = conversationMapper
                .selectOne(new QueryWrapper<Conversation>()
                        .eq("conversationId", conversationId));
        String messageJson = conversation.getMessage();
        JSONArray jsonArray = JSONUtil.parseArray(messageJson);

        List<Message> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String type = obj.getStr("messageType");
            String text = obj.getStr("text");
            Map<String, Object> metadata = obj.get("metadata", Map.class);

            if ("USER".equalsIgnoreCase(type)) {
                result.add(new UserMessage(text,new ArrayList<>(),metadata));
            } else if ("ASSISTANT".equalsIgnoreCase(type)) {
                result.add(new AssistantMessage(text, metadata));
            } else {
                // 其他类型可以拓展
                log.warn("不支持的消息类型：" + type);

            }
        }

        // 只返回最后N条
        int fromIndex = Math.max(0, result.size() - lastN);
        return result.subList(fromIndex, result.size());
    }


    @Override
    public void clear(String conversationId) {
        if(conversationId == null){
            throw new RuntimeException("conversationId不能为空");
        }
        conversationMapper.delete(new QueryWrapper<Conversation>()
                .eq("conversationId", conversationId));
    }
}
