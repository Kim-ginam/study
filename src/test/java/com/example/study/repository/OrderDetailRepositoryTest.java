package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();


        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        // 어떤한 장바구니에
        orderDetail.setOrderGroupId(1L);

        // 어떤 상품?
        orderDetail.setItemId(1L);


        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assertions.assertNotNull(newOrderDetail);
    }

}