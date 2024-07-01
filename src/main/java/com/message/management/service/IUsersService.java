package com.message.management.service;

import com.message.management.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.management.entity.dto.DtoLogin;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 张乔
 * @since 2024-02-26
 */
public interface IUsersService extends IService<User> {

	String login(DtoLogin dtoLogin);

	User getByUsername(String username);

	// List<User> getByRoleId(Long roleId);
}
