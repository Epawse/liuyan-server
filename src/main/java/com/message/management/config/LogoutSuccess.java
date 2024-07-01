package com.message.management.config;

import com.alibaba.fastjson.JSON;
import com.message.management.entity.Result;
import com.message.management.entity.ResultException;
import com.message.management.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutSuccess implements LogoutSuccessHandler {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String token = request.getHeader("token");
		if (token == null) {
			throw new ResultException(555, "先去登录");
		}
		Integer id = jwtUtil.getUsernameFromToken(token);
		// 删除redis中的用户信息
		redisTemplate.delete(String.valueOf(id));
		Result<String> result = Result.successMessage("退出成功");

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(result));
	}
}
