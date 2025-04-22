package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.dto.WarehouseLeaseDTO;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

// 관리자 - 창고 계약 관리 서비스 인터페이스
public interface AdminWarehouseLeaseService {

    // 유저 계약정보 가져오기
    List<WarehouseLeaseDTO> getUserLeaseInfo();


    // db에서 꺼내오기 테스트
    List<WarehouseLeaseDTO> getUserLeaseDbInfo();

    // 계약 수정
    int modifyLeaseRequests(List<String> clientIds,
                            List<String> endDates,
                            List<String> wareIds,
                            List<String> wareCoords);

    // 검색
    List<WarehouseLeaseDTO> search(String searchType, String keyword);

}
