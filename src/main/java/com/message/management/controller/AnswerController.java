package com.message.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.message.management.entity.Result;
import com.message.management.entity.Answer;
import com.message.management.service.IAnswerService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private IAnswerService answerService;

	@GetMapping("/queryCode")
	// 根据queryCode查询回复
	public Result<Answer> getByQueryCode(@RequestParam Long queryCode) {
		return Result.successData(answerService.getByQueryCode(queryCode));
	}

	@GetMapping("/userId")
	// 获取userid下的所有回复
	public Result<List<Answer>> getAllByUserId(@RequestParam Long userId) {
		return Result.successData(answerService.getAllByUserId(userId));
	}

	@GetMapping("/baseInfo")
	// 获取指定页码和页面大小的回复基础信息
	public Result<List<Answer>> getAnswers(@RequestParam int pageNum,
			@RequestParam int pageSize,
			@RequestParam(required = false) Integer handleState) {
		IPage<Answer> page = new Page<>(pageNum, pageSize);
		QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
		if (handleState != null) {
			queryWrapper.eq(handleState != null, "handle_state", handleState);
		}

		IPage<Answer> answerPage = answerService.page(page, queryWrapper);
		List<Answer> answerList = answerPage.getRecords();
		return Result.successData(answerList);
	}

}
