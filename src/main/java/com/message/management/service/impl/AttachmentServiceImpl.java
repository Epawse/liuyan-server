package com.message.management.service.impl;

import com.message.management.entity.Attachment;
import com.message.management.mapper.AttachmentMapper;
import com.message.management.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

}