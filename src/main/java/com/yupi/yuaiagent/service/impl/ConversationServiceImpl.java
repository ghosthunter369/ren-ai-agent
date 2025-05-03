package com.yupi.yuaiagent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yuaiagent.model.domain.Conversation;
import com.yupi.yuaiagent.service.ConversationService;
import com.yupi.yuaiagent.mapper.ConversationMapper;
import org.springframework.stereotype.Service;

/**
* @author 张子涵
* @description 针对表【conversation(对话记录表)】的数据库操作Service实现
* @createDate 2025-05-03 16:20:18
*/
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation>
    implements ConversationService{

}




