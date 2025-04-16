package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO<T> {
    private int currentPage;
    private int totalPages;
    private List<T> list;
}
