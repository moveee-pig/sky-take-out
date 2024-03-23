package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
/**
 * 微信登录
 * @param
 * @return
 */
public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
