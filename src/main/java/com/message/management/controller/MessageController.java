package com.message.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.message.management.entity.Result;
import com.message.management.entity.User;
import com.message.management.entity.vo.MessageVo;
import com.alibaba.fastjson2.JSONWriter.Path;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.message.management.entity.Message;
import com.message.management.service.IMessageService;
import com.message.management.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.message.management.utils.JwtUtil;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private IMessageService messageService;

	@Autowired
	private IUsersService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/allBaseInfo")
	// 获取所有留言基础信息
	public Result<List<Message>> list() {
		// 获取除了content字段的所有留言信息
		return Result.successData(messageService.getAllBaseInfo());
	}

	@GetMapping("/baseInfo")
	// 获取指定页码和页面大小的留言基础信息
	public Result<List<Message>> getMessages(@RequestParam int pageNum,
			@RequestParam int pageSize,
			@RequestParam(required = false) Integer districtId,
			@RequestParam(required = false) Integer handleState) {

		IPage<Message> page = new Page<>(pageNum, pageSize);
		QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
		if (districtId != null || handleState != null) {
			queryWrapper.eq(districtId != null, "district_id", districtId);
			queryWrapper.eq(handleState != null, "handle_state", handleState);
		}

		IPage<Message> messagePage = messageService.page(page, queryWrapper);
		List<Message> messageList = messagePage.getRecords();
		return Result.successData(messageList);

	}

	@GetMapping("/getByQueryCode")
	// 根据queryCode获取留言
	public Result<Message> get(@RequestParam("queryCode") String queryCode) {
		Long code = Long.parseLong(queryCode);
		return Result.successData(messageService.getByQueryCode(code));
	}

	@GetMapping("/getByUserId/{userId}")
	// 根据userId获取留言
	public Result<List<Message>> getByUserId(@RequestParam("userId") Long userId) {
		String id = String.valueOf(userId);
		return Result.successData(messageService.getAllByUserId(id));
	}

	@GetMapping("getByUser")
	public Result<List<Message>> getByUser(@RequestHeader("token") String token) {
		Integer id = jwtUtil.getUsernameFromToken(token);
		String userId = userService.getById(id).getUserId();
		System.out.println(userId);
		return Result.successData(messageService.getAllByUserId(userId));
	}

	@GetMapping("getByKeyWord")
	public Result<List<Message>> getByKeyWord(@RequestParam("keyword") String keyword) {
		return Result.successData(messageService.getAllByKeyWord(keyword));
	}

	@GetMapping("update")
	public Result<Message> update(@RequestParam("message") Message message) {
		messageService.updateById(message);
		return Result.success();
	}

}
