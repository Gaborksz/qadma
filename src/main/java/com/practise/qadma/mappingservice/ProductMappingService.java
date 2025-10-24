package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import com.practise.qadma.payload.view.ProductViewDTO;

import java.util.List;
import java.util.Set;

public interface ProductMappingService {

    ProductViewDTO findById(long id);

    ProductViewDTO save(ProductDTO productDTO);

    List<ProductViewDTO> findAll();
}
