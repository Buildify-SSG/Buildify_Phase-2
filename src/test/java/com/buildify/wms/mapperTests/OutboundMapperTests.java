package com.buildify.wms.mapperTests;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.domain.user.outbound.mapper.UserOutboundMapper;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.InventoryVO;
import com.wareflow.buildify.vo.OutboundVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml"
})
@MapperScan(basePackages = "com.wareflow.buildify.domain.user.outbound.mapper")
@Log4j2
public class OutboundMapperTests {

    @Autowired
    private UserOutboundMapper userOutboundMapper;

    @Autowired
    private AdminInboundMapper adminInboundMapper;

    @Test
    public void userountboundList(){
        List<OutboundVO> vo = userOutboundMapper.outboundlist("PRD-250425-GKUAUK");
        log.info("xptmxmpx");
        log.info(vo.size());
        for (int i = 0; i < vo.size(); i++) {
            log.info("테스트!!!! {}");
        }
    }

//    @Test
//    @DisplayName("outboundInsertList: 클라이언트 ID로 재고·상품 조회")
//    void testOutboundInsertList() {
//        // given
//        String clientId = "USR-TEST-0001";
//
//        // when
//        List<InventoryVO> list = userOutboundMapper.outboundInsertList(clientId);
//
//        // then
//        assertThat(list).isNotNull()
//                .isNotEmpty()
//                .allSatisfy(vo -> {
//                    assertThat(vo.getClientId()).isEqualTo(clientId);
//                    assertThat(vo.getProdId()).isNotBlank();
//                    assertThat(vo.getQuantity()).isPositive();
//                    assertThat(vo.getProdName()).isNotBlank();
//                });



}
