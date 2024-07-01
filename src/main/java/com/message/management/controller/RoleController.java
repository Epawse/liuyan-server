package com.message.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.message.management.entity.Result;
import com.message.management.entity.Role;
import com.message.management.service.IRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@GetMapping("allRoles")
	public Result<List<Role>> getAll() {
		List<Role> roles = roleService.list();
		return Result.successData(roles);
	}

}
