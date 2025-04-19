package com.wareflow.buildify.domain.admin.userManagement.service;

import com.wareflow.buildify.domain.admin.userManagement.mapper.AdminUserManagementMapper;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.vo.ProductVO;
import com.wareflow.buildify.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 관리자 - 회원 조회 서비스 구현체
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminUserManagementServiceImpl implements AdminUserManagementService {

    private final AdminUserManagementMapper adminUserManagementMapper;

    // 회원 조회
    @Override
    public List<UserDTO> getUserInfo() {

        List<UserVO> userVOList = adminUserManagementMapper.getUserInfo();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (UserVO userVO : userVOList) {
            UserDTO userDTO = UserDTO.builder()
                    .clientId(userVO.getClientId())
                    .userName(userVO.getUserName())
                    .userPhone(userVO.getUserPhone())
                    .businessNumber(userVO.getBusinessNumber())
                    .userEnterdate(userVO.getUserEnterdate())
                    .userStatus(userVO.getUserStatus())
                    .build();
            userDTOList.add(userDTO);
        }
        log.info("서비스 List : {}",userDTOList.size());
        return userDTOList;
    }

    @Override
    public List<UserDTO> search(String searchType, String keyword) {
        keyword = keyword.trim();

        List<UserVO> searchVOList = new ArrayList<>();
        List<UserDTO> userDTOList = new ArrayList<>();

        if (keyword.isEmpty()) {
            searchVOList = adminUserManagementMapper.getUserInfo();
        } else {
            String cleaned = keyword.replaceAll("-", "").replaceAll("\\s", "");
            searchVOList = adminUserManagementMapper.search(searchType, cleaned);
        }

        for (UserVO userVO : searchVOList) {
            UserDTO userDTO = UserDTO.builder()
                    .clientId(userVO.getClientId())
                    .userName(userVO.getUserName())
                    .userPhone(userVO.getUserPhone())
                    .businessNumber(userVO.getBusinessNumber())
                    .userEnterdate(userVO.getUserEnterdate())
                    .userStatus(userVO.getUserStatus())
                    .build();
            userDTOList.add(userDTO);
        }
        log.info("서비스 List : {}",userDTOList.size());
        return userDTOList;
    }
}

