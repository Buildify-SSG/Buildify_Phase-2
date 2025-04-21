package com.wareflow.buildify.domain.auth.login.mapper;

import com.wareflow.buildify.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminLoginMapper {
    AdminVO findById(String adminId);
}
