package com.buildify.wms.mapperTests;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.dto.InboundApproveDTO;
import com.wareflow.buildify.vo.InboundVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml"
})

@Log4j2
public class inbound {
    @Autowired
    private AdminInboundMapper adminInboundMapper;
    @Test
    public void test1(){
        List<InboundVO > vo = adminInboundMapper.inboundvo();
        log.info(vo.size());

    }

    @Test
    public void test2(){
        List<InboundApproveDTO> inboundApproveDTOList = adminInboundMapper.inboundApproveList();
        log.info(inboundApproveDTOList);
    }
}
