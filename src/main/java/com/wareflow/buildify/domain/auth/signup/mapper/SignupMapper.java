package com.wareflow.buildify.domain.auth.signup.mapper;

import com.wareflow.buildify.vo.UserVO;

public interface SignupMapper {
    int insert(UserVO userVO);
    int existCheckId(String userid);
}
