package com.message.management.controller;

import com.alibaba.fastjson.JSON;
import com.message.management.entity.MyUserDetail;
import com.message.management.entity.Result;
import com.message.management.entity.User;
import com.message.management.entity.dto.DtoLogin;
import com.message.management.service.IUsersService;
import com.message.management.utils.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUsersService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public Result<String> login(@RequestBody DtoLogin dtoLogin) {
		System.out.println(dtoLogin);
		String token = userService.login(dtoLogin);
		return Result.successData(token);
	}

	@PostMapping("/register")
	public Result register(@RequestBody DtoLogin dtoLogin) {
		System.out.println(dtoLogin);
		if (userService.getByUsername(dtoLogin.getUsername()) != null) {
			return Result.error("用户名已存在");
		}

		User user = new User();
		user.setUsername(dtoLogin.getUsername());
		user.setPassword(passwordEncoder.encode(dtoLogin.getPassword()));
		user.setStatus(0);
		userService.save(user);
		return Result.success();

	}

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/logout")
	public Result logout(@RequestHeader("token") String token) {
		Integer id = jwtUtil.getUsernameFromToken(token);
		redisTemplate.delete(String.valueOf(id));
		System.out.println("用户退出=======>");
		return Result.success();
	}

	@GetMapping("/info")
	public Result info(@RequestHeader("token") String token) {
		System.out.println("controller层获取到的token=======>" + token);
		Integer id = jwtUtil.getUsernameFromToken(token);
		String redisUser = redisTemplate.opsForValue().get(String.valueOf(id));
		MyUserDetail myTUserDetail = JSON.parseObject(redisUser, MyUserDetail.class);
		return Result.successData(myTUserDetail);

	}

	@GetMapping("/all")
	public Result<List<User>> getAll() {
		List<User> users = userService.list();
		return Result.successData(users);
	}

	@GetMapping("/getById/{userId}")
	public Result<User> getUser(@PathVariable Integer userId) {
		User user = userService.getById(userId);
		return Result.successData(user);
	}

	@GetMapping("/baseInfo")
	// 获取指定页码和页面大小的留言基础信息
	public Result<List<User>> getUsers(@RequestParam int pageNum, @RequestParam int pageSize,
			@RequestParam(required = false) Integer districtId, @RequestParam(required = false) Integer roleId) {

		IPage<User> page = new Page<>(pageNum, pageSize);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if (districtId != null || roleId != null) {
			queryWrapper.eq(districtId != null, "district_id", districtId);
			queryWrapper.eq(roleId != null, "role_id", roleId);
		}

		IPage<User> userPage = userService.page(page, queryWrapper);
		List<User> userList = userPage.getRecords();
		return Result.successData(userList);

	}

	@GetMapping("/user-info")
	public Result<User> getUserInfo(@RequestHeader("token") String token) {
		Integer id = jwtUtil.getUsernameFromToken(token);
		User user = userService.getById(id);
		return Result.successData(user);
	}

	// @GetMapping("/getByDistrictId/{districtId}")
	// public Result<List<User>> getByDistrictId(@PathVariable Integer districtId) {
	// List<User> users = userService.getByDistrictId(districtId);
	// return Result.successData(users);
	// }

	@PostMapping("/update")
	public Result update(@RequestBody User user) {
		userService.updateById(user);
		return Result.success();
	}
}
