package com.practise.qadma.service;

import com.practise.qadma.payload.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDTO> findAll();

    void updateProduct(long id, ProductDTO productDTO);
}
