package com.wareflow.buildify.vo;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminVO {
    private String adminNumber;
    private String adminRole;
    private String adminName;
    private String adminEmail;
    private Date adminEnterDate;
    private String adminAddress;
    private String adminPhone;
    private String adminId;
    private String adminPassword;
}
