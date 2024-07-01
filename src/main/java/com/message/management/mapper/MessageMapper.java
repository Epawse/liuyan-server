package com.message.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.message.management.entity.Message;
import com.message.management.entity.vo.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

	// // 分页查询
	// IPage<MessageVo> selectPageVo(Page<?> page);

	List<Message> selectMessagesByUserId(String userId);

	// 获取所有留言基础信息
	List<Message> getAllBaseInfo();

	// 带有行政区和处理状态过滤条件的分页查询
	IPage<MessageVo> selectPageVo(Page<?> page,
			@Param("districtId") Integer districtId,
			@Param("handleState") Integer handleState);

	// 通过关键字查询
	List<Message> selectMessagesByKeyWord(String keyword);

	// 通过queryCode查询
	Message getByQueryCode(Long queryCode);

}