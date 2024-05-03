package com.jia.sweetshop.model.po;

import lombok.Data;

@Data
public class Material {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
