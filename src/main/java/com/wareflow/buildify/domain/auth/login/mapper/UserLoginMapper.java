package com.wareflow.buildify.domain.auth.login.mapper;

import com.wareflow.buildify.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {
    UserVO findById(String userId);
}
