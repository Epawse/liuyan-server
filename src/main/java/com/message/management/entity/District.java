package com.message.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("districts")
public class District {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String districtName;
}