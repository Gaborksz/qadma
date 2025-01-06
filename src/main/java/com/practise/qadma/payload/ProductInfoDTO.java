package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductInfoDTO {

    private long productId;
    private int partNumber;
    private String name;
    private int revision;
}
