package com.message.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.message.management.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

	// 根据queryCode查询回复
	Answer getByQueryCode(Long queryCode);

	// 获取userid下的所有回复
	List<Answer> getAllByUserId(Long userId);

}