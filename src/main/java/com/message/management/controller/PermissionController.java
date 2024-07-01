package com.message.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.message.management.service.IPermissionService;
import com.message.management.entity.Permission;
import com.message.management.entity.Result;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	@GetMapping("allPermissions")
	public Result<List<Permission>> getAll() {
		List<Permission> permissions = permissionService.list();
		return Result.successData(permissions);
	}
}
