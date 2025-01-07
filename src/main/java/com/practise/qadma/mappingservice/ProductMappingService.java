package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.view.ProductViewDTO;

public interface ProductMappingService {

    ProductViewDTO findById(long id);

    ProductViewDTO save(ProductDTO productDTO);
}
