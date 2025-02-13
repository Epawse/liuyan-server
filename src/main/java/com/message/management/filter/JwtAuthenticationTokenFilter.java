package com.message.management.filter;

import com.alibaba.fastjson.JSON;
import com.message.management.entity.MyUserDetail;
import com.message.management.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 获取请求头中的token
		String token = request.getHeader("token");
		System.out.println("前端的token信息=======>" + token);
		// 如果token为空直接放行，由于用户信息没有存放在SecurityContextHolder.getContext()中所以后面的过滤器依旧认证失败符合要求
		if (!StringUtils.hasText(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		// 解析Jwt中的用户id
		Integer userId = jwtUtil.getUsernameFromToken(token);
		// 从redis中获取用户信息
		String redisUser = redisTemplate.opsForValue().get(String.valueOf(userId));
		if (!StringUtils.hasText(redisUser)) {
			filterChain.doFilter(request, response);
			return;
		}

		MyUserDetail myTUserDetail = JSON.parseObject(redisUser, MyUserDetail.class);
		log.info("Jwt过滤器中MyUserDetail的值============>" + myTUserDetail.toString());

		// 将用户信息存放在SecurityContextHolder.getContext()，后面的过滤器就可以获得用户信息了。这表明当前这个用户是登录过的，后续的拦截器就不用再拦截了
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				myTUserDetail, null, myTUserDetail.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);
	}

}