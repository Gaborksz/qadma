package com.practise.qadma.controller;

import com.practise.qadma.conversion.ProductConversionService;
import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductConversionService productConversionService;

    @Autowired
    public ProductController(ProductConversionService productConversionService) {
        this.productConversionService = productConversionService;
    }

    @GetMapping("/{id}")
    public ProductViewDTO findById(@PathVariable long id) {

        return productConversionService.findById(id);
    }

    @PostMapping()
    public ProductViewDTO save(@RequestBody ProductDTO productDTO) {

        return productConversionService.save(productDTO);
    }
}
