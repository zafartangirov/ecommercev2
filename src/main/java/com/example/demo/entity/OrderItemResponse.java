package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemResponse {
    private String name;
    private Integer price;
    private Integer amount;
    private Attachment attachment;
}
