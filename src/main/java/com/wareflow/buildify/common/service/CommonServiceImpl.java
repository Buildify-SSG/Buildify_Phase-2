package com.wareflow.buildify.common.service;

import com.wareflow.buildify.common.mapper.CommonMapper;
import com.wareflow.buildify.dto.AdminDTO;
import com.wareflow.buildify.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final CommonMapper commonMapper;

    @Override
    public List<AdminDTO> getAdminInfo() {
        List<AdminDTO> adminDTOList = new ArrayList<>();
        List<AdminVO> adminVOList = commonMapper.getAdminList();
        for (AdminVO adminVO : adminVOList){
            AdminDTO adminDTO = AdminDTO.builder()
                    .adminNumber(adminVO.getAdminNumber())
                    .adminName(adminVO.getAdminName())
                    .adminEmail(adminVO.getAdminEmail())
                    .adminPhone(adminVO.getAdminPhone())
                    .build();
            adminDTOList.add(adminDTO);
        }
        return adminDTOList;
    }
}
