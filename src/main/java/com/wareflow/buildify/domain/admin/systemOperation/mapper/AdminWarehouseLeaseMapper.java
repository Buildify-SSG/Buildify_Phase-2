package com.wareflow.buildify.domain.admin.systemOperation.mapper;

import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.dto.WarehouseLeaseDTO;
import com.wareflow.buildify.vo.UserVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 관리자 - 창고 계약 관리 Mapper
@Mapper
public interface AdminWarehouseLeaseMapper {

    // 유저 계약정보 가져오기
    List<WarehouseLeaseDTO> getUserLeaseInfo();

    int insertDummyUser(UserDTO userDTO);

    int modifyUserLeaseInfo(@Param("list") List<UserWareHouseVO> userWareHouseVO);

    List<WarehouseLeaseDTO> search(@Param("searchType") String searchType,
                                   @Param("keyword")String keyword);
}
