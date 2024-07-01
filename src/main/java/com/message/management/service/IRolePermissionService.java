package com.message.management.service;

import com.message.management.entity.Permission;
import com.message.management.entity.RolePermission;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 张乔
 * @since 2024-02-27
 */
public interface IRolePermissionService extends IService<RolePermission> {

	List<Permission> getPermissionsByRoleId(Integer roleId);
}
