package com.sda.website.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProductEntity {

    @Id
    private Integer orderId;

    private Integer productId;

    private Double productQuantity;

}
