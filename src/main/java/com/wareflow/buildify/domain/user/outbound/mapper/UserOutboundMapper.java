package com.wareflow.buildify.domain.user.outbound.mapper;

import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.OutboundVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOutboundMapper {


    List<OutboundVO> outboundlist(String a);


}
