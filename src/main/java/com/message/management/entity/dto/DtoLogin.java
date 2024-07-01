package com.message.management.entity.dto;

import lombok.Data;

@Data
public class DtoLogin {

private String username;
private String password;
private String codeKey;
private String codeValue;
private Boolean rememberMe;


}
