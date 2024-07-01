package com.message.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;
	private T data;

	public static <T> Result<T> successData(T data) {
		return new Result<>(200, "操作成功", data);
	}

	public static <T> Result<T> successMessage(String message) {
		return new Result<>(200, message, null);
	}

	public static <T> Result<T> success() {
		return new Result<>(200, "操作成功", null);
	}

	public static <T> Result<T> error(String message) {
		return new Result<>(500, message, null);
	}

}
