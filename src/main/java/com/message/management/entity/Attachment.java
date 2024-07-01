package com.message.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("attachments")
public class Attachment {
	@TableId(type = IdType.AUTO)
	private Long id;
	private Long queryCode;
	private String fileName;
	private String fileType;
	private Integer fileSize;
	private String attachmentPath;
	private Boolean isImage;
	private String thumbPath;
	private String middlePath;
	private Integer width;
	private Integer height;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}