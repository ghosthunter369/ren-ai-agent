package com.yupi.yuaiagent.tuku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.yuaiagent.model.domain.Picture;
import com.yupi.yuaiagent.mapper.PictureMapper;
import jakarta.annotation.Resource;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PictureTools {
    @Resource
    private PictureMapper pictureMapper;
    @Tool(description = "根据用户Id获取最新、最近的图片")
    public Picture getPictureByLatest(String userId) {
        // 创建查询条件：按用户ID筛选，并按创建时间降序排序，只取第一条
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<Picture>()
                .eq("userId", userId)
                .orderByDesc("createTime")  // 假设您的表中有create_time字段记录创建时间
                .last("LIMIT 1");            // 只取最新的一条记录
        return pictureMapper.selectOne(queryWrapper);
    }
}
