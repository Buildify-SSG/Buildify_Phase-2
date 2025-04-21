package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
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
