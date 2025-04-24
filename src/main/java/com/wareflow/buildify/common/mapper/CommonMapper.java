package com.wareflow.buildify.common.mapper;

import com.wareflow.buildify.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

    List<AdminVO> getAdminList();
}
