package com.yupi.yuaiagent.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.model.Media;
import org.springframework.ai.model.MediaContent;
import org.springframework.core.io.Resource;

import java.util.*;

@Data
public class DbMessage {

    private MessageType messageType;
    private String text;
    private Map<String, Object> metadata;
}

