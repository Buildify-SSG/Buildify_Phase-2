package com.wareflow.buildify.vo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO {
    private String categoryId;
    private String categoryLevel1; // 대분류
    private String categoryLevel2; // 중분류
    private String categoryLevel3; // 소분류
}

