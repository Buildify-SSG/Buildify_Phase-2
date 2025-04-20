package com.wareflow.buildify.domain.auth.signup.mapper;

import com.wareflow.buildify.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SignupMapper {
    int insertUser(UserVO userVO);
    int existCheckByUserId(String userid);
    int insertAuth(Map<String, Object> authMap);
}
