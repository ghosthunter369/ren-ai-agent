package com.yupi.yuaiagent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.yuaiagent.model.domain.Picture;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张子涵
* @description 针对表【picture(图片)】的数据库操作Mapper
* @createDate 2025-05-06 21:55:44
* @Entity com/yupi/yuaiagent/tuku.domain.Picture
*/
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {

}




