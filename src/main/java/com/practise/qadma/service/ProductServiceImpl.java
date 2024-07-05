package com.practise.qadma.service;

import com.practise.qadma.dao.ProductRepository;
import com.practise.qadma.payload.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProductDTO> findAll() {

        return productRepository.findAll().stream()
                .map(p-> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateProduct(long id, ProductDTO productDTO) {

        //TODO
    }
}
