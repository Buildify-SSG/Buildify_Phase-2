package com.wareflow.buildify.dto;

import com.github.ckpoint.toexcel.annotation.ExcelHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @ExcelHeader(headerName = "고객 ID", priority = 0)
    private String clientId;
    @ExcelHeader(headerName = "이름", priority = 1)
    private String userName;
    @ExcelHeader(headerName = "연락처", priority = 2)
    private String userPhone;
    private String userEmail;
    private String userAddress;
    @ExcelHeader(headerName = "사업자 번호", priority = 3)
    private String businessNumber;
    @ExcelHeader(headerName = "가입일", priority = 4)
    private Date userEnterdate;
    private String userId;
    private String userPw;
    private int userStatus;

}
