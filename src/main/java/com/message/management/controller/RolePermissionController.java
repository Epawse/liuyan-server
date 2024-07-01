package com.message.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.message.management.service.IRolePermissionService;

import com.message.management.entity.Permission;
import com.message.management.entity.RolePermission;
import com.message.management.entity.Result;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/role-permissions")
public class RolePermissionController {

	@Autowired
	private IRolePermissionService rolePermissionService;

	@GetMapping("allRolePermissions")
	public Result<List<RolePermission>> allRolePermissions() {
		List<RolePermission> rolePermissions = rolePermissionService.list();
		return Result.successData(rolePermissions);
	}

	@GetMapping("getPermissionsByRoleId")
	public Result<List<Permission>> getPermissionsByRoleId(@RequestParam Integer roleId) {
		List<Permission> permissions = rolePermissionService.getPermissionsByRoleId(roleId);
		return Result.successData(permissions);
	}
}
