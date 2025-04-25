package com.buildify.wms.mapperTests;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.dashboard.mapper.DashboardMapper;
import com.wareflow.buildify.domain.user.product.mapper.ProductMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml"
})
@MapperScan(basePackages = "com.wareflow.buildify.domain.user.dashboard.mapper")
@Log4j2
public class UserDashboardMapperTests {
    @Autowired
    private DashboardMapper dashboardMapper;

    @Test
    public void testFindByClientId() {

        int count1 = dashboardMapper.selectDayOutbound("USR-250424-XXCCVV", 0);
        log.info("일간 출고 요청 : " + count1);

        int count2 = dashboardMapper.selectWeekOutbound("USR-250424-XXCCVV", 0);
        log.info("주간 출고 요청: " + count2);

        int count3 = dashboardMapper.selectDayInbound("USR-250424-XXCCVV", 0);
        log.info("일간 입고 요청: " + count3);
        int count4 = dashboardMapper.selectWeekInbound("USR-250424-XXCCVV", 0);
        log.info("주간 출고 요청: " + count4);

        int count5 = dashboardMapper.selectDayOutbound("USR-250424-XXCCVV", 1);
        log.info("일간 출고 승인 : " + count5);

        int count6 = dashboardMapper.selectWeekOutbound("USR-250424-XXCCVV", 1);
        log.info("주간 출고 승인: " + count6);

        int count7 = dashboardMapper.selectDayInbound("USR-250424-XXCCVV", 1);
        log.info("일간 입고 승인: " + count7);

        int count8 = dashboardMapper.selectWeekInbound("USR-250424-XXCCVV", 1);
        log.info("주간 출고 승인: " + count8);
    }
}
