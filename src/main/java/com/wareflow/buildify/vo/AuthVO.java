package com.wareflow.buildify.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthVO {

    private String id;
    private String role;
}
