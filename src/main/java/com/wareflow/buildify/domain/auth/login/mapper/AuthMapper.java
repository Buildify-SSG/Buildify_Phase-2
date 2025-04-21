package com.wareflow.buildify.domain.auth.login.mapper;

import com.wareflow.buildify.vo.AuthVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    AuthVO findById(String id);
}
