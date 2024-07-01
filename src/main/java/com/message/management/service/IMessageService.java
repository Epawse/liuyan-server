package com.message.management.service;

import com.message.management.entity.Message;
import com.message.management.entity.vo.MessageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMessageService extends IService<Message> {

	// 根据queryCode查询消息
	Message getByQueryCode(Long queryCode);

	// 获取userid下的所有消息
	List<Message> getAllByUserId(String userId);

	// 获取所有消息的基础信息
	List<Message> getAllBaseInfo();

	// 过滤条件查询
	IPage<MessageVo> getMessagePage(int pageNum, int pageSize, Integer districtId, Integer handleState);

	// 通过关键字查询，不分页
	List<Message> getAllByKeyWord(String keyword);
}