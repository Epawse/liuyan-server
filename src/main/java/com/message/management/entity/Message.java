package com.message.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("messages")
public class Message {
	@TableId(type = IdType.AUTO)
	private Long id;
	private Long queryCode;
	private String userId;
	private String subject;
	private String content;
	private LocalDateTime dateline;
	private Integer threadState;
	private Integer handleState;
	private Integer processState;
	private Integer traceState;
	private Integer markState;
	private Integer reportState;
	private Integer satisfaction;
	private LocalDateTime lastUpdate;
	private LocalDateTime lastAnswerTime;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer districtId;
	private Boolean hasAttachment;
}