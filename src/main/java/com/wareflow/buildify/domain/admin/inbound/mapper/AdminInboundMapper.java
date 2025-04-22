package com.wareflow.buildify.domain.admin.inbound.mapper;

import com.wareflow.buildify.vo.InboundProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminInboundMapper {


    List<InboundProductVO> adminInboundList();

    List<InboundProductVO> adminInboundCheckList();


}
