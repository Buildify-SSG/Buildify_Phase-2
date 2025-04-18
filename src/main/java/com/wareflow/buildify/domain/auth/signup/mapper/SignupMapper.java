package com.wareflow.buildify.domain.auth.signup.mapper;

import com.wareflow.buildify.vo.UserVO;

import java.util.Map;

public interface SignupMapper {
    int insertUser(UserVO userVO);
    int existCheckByUserId(String userid);
    int insertAuth(Map<String, Object> authMap);
}
