package com.message.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("answers")
public class Answer {
	@TableId(type = IdType.AUTO)
	private Long id;
	private Long queryCode;
	private String answerId;
	private String content;
	private LocalDateTime dateline;
	private Integer satisfaction;
	private Integer handleState;
	private LocalDateTime lastUpdate;
}