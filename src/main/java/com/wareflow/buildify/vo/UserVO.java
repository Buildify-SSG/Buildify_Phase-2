package com.wareflow.buildify.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
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
