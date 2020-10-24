package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Itemstatus {
    REGISTERED(0,"등록","상품 등록상태"),
    UNREGISTERED(1,"해지", "상품 해지상태"),
    WATING(2, "검수(대기)", "상품 검수상태");

    private Integer id;
    private String title;
    private String description;
}
