package com.buildify.wms.mapperTests;

import com.wareflow.buildify.domain.user.outbound.mapper.UserOutboundMapper;
import com.wareflow.buildify.vo.OutboundVO;
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
@MapperScan(basePackages = "com.wareflow.buildify.domain.user.outbound.mapper")
@Log4j2
public class OutboundMapperTests {

    @Autowired
    private UserOutboundMapper userOutboundMapper;

    @Test
    public void test1( ){
        String a = "PROD-001-AAA";
        List<OutboundVO> vo = userOutboundMapper.outboundlist(a);
        log.info(vo.size());
    }



}
