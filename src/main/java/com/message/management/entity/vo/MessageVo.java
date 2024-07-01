package com.message.management.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageVo {
	private Long queryCode;
	private String userId;
	private String subject;
	private LocalDateTime dateline;
	private Integer handleState;
	private Integer satisfaction;
	private LocalDateTime lastUpdate;
	private LocalDateTime lastAnswerTime;
	private Integer districtId;
	private Boolean hasAttachment;
}