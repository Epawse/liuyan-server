package com.message.management.service.impl;

import com.message.management.entity.Answer;
import com.message.management.mapper.AnswerMapper;
import com.message.management.service.IAnswerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

	@Autowired
	AnswerMapper answerMapper;

	// 根据queryCode查询回复
	@Override
	public Answer getByQueryCode(Long queryCode) {
		return answerMapper.getByQueryCode(queryCode);
	}

	// 获取userid下的所有回复
	@Override
	public List<Answer> getAllByUserId(Long userId) {
		return answerMapper.getAllByUserId(userId);
	}

}
