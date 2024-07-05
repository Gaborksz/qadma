package com.practise.qadma.controller;

import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {

        return productService.findAll();
    }

    @PutMapping("{id}")
    public void updateProduct (@PathVariable long id,
                                            @RequestBody ProductDTO productDTO) {

        productService.updateProduct(id, productDTO);
    }

}
