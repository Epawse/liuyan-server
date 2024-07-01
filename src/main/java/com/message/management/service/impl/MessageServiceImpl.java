package com.message.management.service.impl;

import com.message.management.entity.Message;
import com.message.management.entity.vo.MessageVo;
import com.message.management.mapper.MessageMapper;
import com.message.management.service.IMessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public Message getByQueryCode(Long queryCode) {
		Message message = messageMapper.getByQueryCode(queryCode);
		return message;
	}

	@Override
	public List<Message> getAllByUserId(String userId) {
		List<Message> messages = messageMapper.selectMessagesByUserId(userId);
		return messages;
	}

	@Override
	public List<Message> getAllBaseInfo() {
		return messageMapper.getAllBaseInfo();
	}

	// public IPage<MessageVo> getMessagePage(int pageNum, int pageSize) {
	// Page<MessageVo> page = new Page<>(pageNum, pageSize);
	// return messageMapper.selectPageVo(page);
	// }

	public IPage<MessageVo> getMessagePage(int pageNum, int pageSize, Integer districtId, Integer handleState) {
		Page<MessageVo> page = new Page<>(pageNum, pageSize);
		return messageMapper.selectPageVo(page, districtId, handleState);
	}

	public List<Message> getAllByKeyWord(String keyword) {

		return messageMapper.selectMessagesByKeyWord(keyword);
	}

}