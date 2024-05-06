package com.jia.sweetshop.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Material implements Serializable {

    private static final long serialVersionUID = -10356785423868312L;

    private Long id;
    private String name;
    private double price;
    private int quantity;
}
