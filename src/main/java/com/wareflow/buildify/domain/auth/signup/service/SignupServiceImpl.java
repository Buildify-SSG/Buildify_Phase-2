package com.wareflow.buildify.domain.auth.signup.service;

import com.wareflow.buildify.constants.IdPrefix;
import com.wareflow.buildify.domain.auth.signup.mapper.SignupMapper;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.util.CustomIdGenerator;
import com.wareflow.buildify.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final SignupMapper signupMapper;
    private final ModelMapper modelMapper;

    @Override
    public boolean signUp(UserDTO userDTO) {
        // 아이디 중복 검사
        if (signupMapper.existCheckId(userDTO.getUserId()) > 0) {
            return false;
        }

        // 기본값 세팅: client_id, 가입일자, 상태값
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);

        // 서버에서 세팅해야 하는 필드들
        userVO = UserVO.builder().clientId(
                (CustomIdGenerator.generateId(IdPrefix.USR)))
                .userName(userVO.getUserName())
                .userPhone(userVO.getUserPhone())
                .userEmail(userVO.getUserEmail())
                .userAddress(userVO.getUserAddress())
                .businessNumber(userVO.getBusinessNumber())
                .userId(userVO.getUserId())
                .userPw(userVO.getUserPw()) // 암호화 생략 상태
                .userEnterdate(new java.util.Date())
                .userStatus(0) // 미승인 상태
                .build();

        return signupMapper.signUp(userVO) == 1;
    }

    @Override
    public boolean isUserIdExist(String userId) {
        return signupMapper.existCheckId(userId) > 0;
    }


}
