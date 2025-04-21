package com.wareflow.buildify.dto;



import lombok.Data;

@Data
public class CategoryDTO {
    private String categoryLevel1; // 대분류
    private String categoryLevel2; // 중분류
    private String categoryLevel3; // 소분류
}

