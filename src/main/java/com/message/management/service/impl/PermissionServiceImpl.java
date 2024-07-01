package com.message.management.service.impl;

import com.message.management.entity.Permission;
import com.message.management.mapper.PermissionMapper;
import com.message.management.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张乔
 * @since 2024-02-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
