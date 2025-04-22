package com.wareflow.buildify.dto;

import lombok.*;

import java.util.List;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequestDTO {
    private String userId;
    private List<String > prodIds;
    private List<Integer> quantities;
}
