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
    private String productName;
    private int revision;
}
