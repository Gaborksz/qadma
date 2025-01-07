package com.practise.qadma.conversion;

import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.view.ProductViewDTO;

public interface ProductConversionService {

    ProductViewDTO findById(long id);

    ProductViewDTO save(ProductDTO productDTO);
}
