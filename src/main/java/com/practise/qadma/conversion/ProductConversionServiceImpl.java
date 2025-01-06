package com.practise.qadma.conversion;

import com.practise.qadma.entity.Product;
import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import com.practise.qadma.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConversionServiceImpl implements ProductConversionService {

    private final ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductConversionServiceImpl(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductViewDTO findById(long id) {

        return modelMapper.map(productService.findById(id), ProductViewDTO.class);
    }


    @Override
    public ProductViewDTO save(ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        return modelMapper.map(productService.save(product), ProductViewDTO.class);
    }
}
