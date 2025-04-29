package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.userManagement.mapper.AdminUserManagementMapper;
import com.wareflow.buildify.vo.UserVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class UserManagementMapperTests {

    @Autowired
    AdminUserManagementMapper adminUserManagementMapper;

    @Test
    @Transactional
    @DisplayName("관리자 회원 정보 조회")
    public void userView(){
        log.info("회원 정보 조회 테스트 시작");
        List<UserVO> userVOList = adminUserManagementMapper.getUserInfo();
        log.info("회원 : " + userVOList.size() + "명");
        for (int i = 0; i < userVOList.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userVOList.get(i).getClientId(),userVOList.get(i).getUserName());
        }

    }

    @Test
    @Transactional
    @DisplayName("회원 검색 기능")
    public void userSearch(){
        log.info("회원 검색 테스트 시작");


        log.info("클라이언트 ID 검색");
        List<UserVO> userVOList = adminUserManagementMapper.search("clientId","CLT-005-EEE");
        log.info("ID 검색 결과 : {} 명",userVOList.size());
        for (int i = 0; i < userVOList.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userVOList.get(i).getClientId(),userVOList.get(i).getUserName());
        }

        log.info("이름 검색");
        List<UserVO> userVOList2 = adminUserManagementMapper.search("userName","유재석");
        log.info("이름 검색 결과 : {} 명",userVOList2.size());
        for (int i = 0; i < userVOList2.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userVOList2.get(i).getClientId(),userVOList2.get(i).getUserName());
        }

        log.info("연락처 검색");
        List<UserVO> userVOList3 = adminUserManagementMapper.search("userPhone","01044445555");
        log.info("연락처 검색 결과 : {} 명",userVOList3.size());
        for (int i = 0; i < userVOList3.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userVOList3.get(i).getClientId(),userVOList3.get(i).getUserName());
        }

        log.info("사업자 번호 검색");
        String cleanKeyword = "123-12-22222".replaceAll("-", "");
        List<UserVO> userVOList4 = adminUserManagementMapper.search("businessNumber",cleanKeyword);
        log.info("사업자번호 검색 결과 : {} 명",userVOList4.size());
        for (int i = 0; i < userVOList4.size(); i++) {
            log.info("클라이언트 ID : {} , 회원 이름 : {}",
                    userVOList4.get(i).getClientId(),userVOList4.get(i).getUserName());
        }

    }

}