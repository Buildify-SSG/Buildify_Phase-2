package com.wareflow.buildify.domain.auth.signup.mapper;

import com.wareflow.buildify.vo.UserVO;

public interface SignupMapper {
    int signUp(UserVO userVO);
    int existCheckId(String userid);
}
