package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // N:1

    // LAZY = "지연 로딩" , EAGER = "즉시 로딩"

    // LAZY = SELECT * FROM ITEM WHERE ID = ?

    // EAGER = 1:1
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // 연관관계가 설정된 모든 테이블에 대해서 조인이 일어남
    // 데이터가 많으면 해당 쿼리가 실행될 때 성능의 저하, 가지고 오지 못할 위험이 있다.
    // JOIN

}
