package com.message.management.controller;

import com.message.management.entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @PreAuthorize("hasAnyAuthority('张乔没有的权限')")
    @GetMapping("/hello")
    public Result hello(){
        System.out.println("test接口中的hello方法调用========================");
        return Result.successData("hello");
    }



}
