package com.message.management.service.impl;

import com.message.management.entity.Permission;
import com.message.management.entity.RolePermission;
import com.message.management.mapper.RolePermissionMapper;
import com.message.management.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
		implements IRolePermissionService {

	private RolePermissionMapper rolePermissionsMapper;

	@Override
	public List<Permission> getPermissionsByRoleId(Integer roleId) {
		return rolePermissionsMapper.getPermissionsByRoleId(roleId);
	}

}
