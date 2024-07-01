package com.message.management.service;

import com.message.management.entity.Answer;
import com.message.management.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

public interface IAnswerService extends IService<Answer> {
	// 根据queryCode查询回复
	Answer getByQueryCode(Long queryCode);

	// 获取userid下的所有回复
	List<Answer> getAllByUserId(Long userId);

}