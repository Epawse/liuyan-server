package com.message.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.message.management.entity.*;
import com.message.management.mapper.UserMapper;
import com.message.management.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MyUserDetailServerImpl implements MyUserDetailServer {
	@Autowired
	UserMapper userService;

	/**
	 * 返回一个账号所拥有的权限码集合
	 */

	// 角色权限表
	@Autowired
	IRolePermissionService rolePermissionsService;
	// 用户角色表
	@Autowired
	IUserRolesService userRolesService;
	// 权限表
	@Autowired
	IPermissionService permissionsService;

	// 角色表
	@Autowired
	IRoleService rolesService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User users = userService
				.selectOne(new LambdaQueryWrapper<User>().eq(username != null, User::getUsername, username));
		if (users == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		log.info("UserDetailServer中的user:=========>" + users);
		MyUserDetail myTUserDetail = new MyUserDetail();
		myTUserDetail.setUsers(users);

		// 查询用户权限

		// 根据用户id从用户角色表中获取角色id
		List<UserRole> roleIds = userRolesService.list(new LambdaQueryWrapper<UserRole>()
				.eq(UserRole::getUserId, users.getId()));
		List<Integer> rolesList = roleIds.stream().map(UserRole::getRoleId).toList();

		if (!(roleIds.size() > 0)) {
			// 用户没有分配角色
			return myTUserDetail;
		}

		Set<String> listPermission = new HashSet<>();

		rolesList.forEach(roleId -> {
			// 根据角色id从角色权限表中获取权限id
			List<RolePermission> rolePermissions = rolePermissionsService
					.list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
			// 根据权限id从权限表中获取权限名称
			rolePermissions.forEach(permissionsId -> {
				Permission permissions = permissionsService.getById(permissionsId.getPermissionId());
				listPermission.add(permissions.getName());
			});
		});

		myTUserDetail.setPermissions(listPermission);

		// 查询角色角色

		Set<String> listRole = new HashSet<>();

		roleIds.forEach(roleId -> {
			Role byId = rolesService.getById(roleId.getRoleId());
			listRole.add(byId.getName());
		});
		myTUserDetail.setRoles(listRole);
		log.info("UserDetailServer中的查完权限的myTUserDetail:=========>" + myTUserDetail);
		return myTUserDetail;
	}

}
