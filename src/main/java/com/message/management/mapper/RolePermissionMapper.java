package com.message.management.mapper;

import com.message.management.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.message.management.entity.Permission;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 张乔
 * @since 2024-02-27
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	// 根据角色id获取权限
	List<Permission> getPermissionsByRoleId(Integer roleId);
}
