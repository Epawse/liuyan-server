package com.message.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.message.management.entity.Result;
import com.message.management.entity.District;
import com.message.management.service.IDistrictService;

@RestController
@RequestMapping("/districts")
public class DistrictController {

	@Autowired
	private IDistrictService districtService;

	@GetMapping("/allDistricts")
	// 获取所有行政区
	public Result<List<District>> list() {
		return Result.successData(districtService.list());
	}

	@GetMapping("/{id}")
	// 根据id获取行政区
	public Result<District> get(@RequestParam("id") Integer id) {
		return Result.successData(districtService.getById(id));
	}

}
