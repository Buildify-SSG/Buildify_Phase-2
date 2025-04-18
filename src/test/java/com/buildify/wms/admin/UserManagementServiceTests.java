package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.userManagement.mapper.AdminUserManagementMapper;
import com.wareflow.buildify.domain.admin.userManagement.service.AdminUserManagementService;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.vo.UserVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class UserManagementServiceTests {

    @Autowired
    AdminUserManagementService adminUserManagementService;

    @Test
    @DisplayName("관리자 회원 정보 조회")
    public void userView(){
        log.info("회원 정보 조회 테스트 시작");
        List<UserDTO> userDTOList = adminUserManagementService.getUserInfo();
        log.info("회원 : " + userDTOList.size() + "명");
        for (int i = 0; i < userDTOList.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList.get(i).getClientId(),userDTOList.get(i).getUserName());
        }

    }

    @Test
    @DisplayName("회원 검색 기능")
    public void userSearch(){
        log.info("회원 검색 테스트 시작");


        log.info("클라이언트 ID 검색");
        List<UserDTO> userDTOList = adminUserManagementService.search("clientId","CLT-005-EEE");
        log.info("ID 검색 결과 : {} 명",userDTOList.size());
        for (int i = 0; i < userDTOList.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList.get(i).getClientId(),userDTOList.get(i).getUserName());
        }

        log.info("이름 검색");
        List<UserDTO> userDTOList2 = adminUserManagementService.search("userName","유재석");
        log.info("이름 검색 결과 : {} 명",userDTOList2.size());
        for (int i = 0; i < userDTOList2.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList2.get(i).getClientId(),userDTOList2.get(i).getUserName());
        }

        log.info("연락처 검색");
        List<UserDTO> userDTOList3 = adminUserManagementService.search("userPhone","010-2311-1212");
        log.info("연락처 검색 결과 : {} 명",userDTOList3.size());
        for (int i = 0; i < userDTOList3.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList3.get(i).getClientId(),userDTOList3.get(i).getUserName());
        }

        log.info("연락처 - 제외 검색");
        List<UserDTO> userDTOList3_1 = adminUserManagementService.search("userPhone","01023111212");
        log.info("연락처 - 제외 검색 결과 : {} 명",userDTOList3_1.size());
        for (int i = 0; i < userDTOList3_1.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList3_1.get(i).getClientId(),userDTOList3_1.get(i).getUserName());
        }

        Assertions.assertEquals(userDTOList3.size(),userDTOList3_1.size());

        log.info("사업자 번호 검색");
        List<UserDTO> userDTOList4 = adminUserManagementService.search("businessNumber","123-12-22222");
        log.info("사업자번호 검색 결과 : {} 명",userDTOList4.size());
        for (int i = 0; i < userDTOList4.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList4.get(i).getClientId(),userDTOList4.get(i).getUserName());
        }

        log.info("사업자 번호 - 제외 검색");
        List<UserDTO> userDTOList4_1 = adminUserManagementService.search("businessNumber","1231222222");
        log.info("사업자번호 - 제외 검색 결과 : {} 명",userDTOList4_1.size());
        for (int i = 0; i < userDTOList4_1.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userDTOList4_1.get(i).getClientId(),userDTOList4_1.get(i).getUserName());
        }

        Assertions.assertEquals(userDTOList4.size(),userDTOList4_1.size());


    }

}