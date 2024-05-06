package com.jia.sweetshop.model.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseDTO  implements Serializable {


    private int pageNum=1;

    private int pageSize=3;
}
