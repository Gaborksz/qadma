package com.practise.qadma.mappingservice;

import com.practise.qadma.entity.Product;
import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import com.practise.qadma.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductMappingServiceImpl implements ProductMappingService {

    private final ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductMappingServiceImpl(ProductService productService, ModelMapper modelMapper) {
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

    @Override
    public List<ProductViewDTO> findAll() {

        return productService.findAll().stream()
                .map( product->
                        modelMapper.map(product, ProductViewDTO.class))
                .toList();
    }

    @Override
    public Set<ProductViewDTO> search(ProductSearchCriteriaDTO searchCriteria) {
        return productService.search(searchCriteria).stream()
                .map(product ->
                        modelMapper.map(product, ProductViewDTO.class)).collect(Collectors.toSet());
    }
}
