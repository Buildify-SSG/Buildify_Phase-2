package com.wareflow.buildify.dto;

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

    private String clientId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String businessNumber;
    private Date userEnterdate;
    private String userId;
    private String userPw;
    private int userStatus;

}
