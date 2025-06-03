package com.practise.qadma.controller;

import com.practise.qadma.mappingservice.ProductMappingService;
import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductMappingService productMappingService;


    @Autowired
    public ProductController(ProductMappingService productMappingService) {
        this.productMappingService = productMappingService;

    }

    @GetMapping("/{id}")
    public ProductViewDTO findById(@PathVariable long id) {

        return productMappingService.findById(id);
    }

    @PostMapping()
    public ProductViewDTO save(@RequestBody ProductDTO productDTO) {
        return productMappingService.save(productDTO);
    }


    @PostMapping("search")
    public Set<ProductViewDTO> search(@RequestBody ProductSearchCriteriaDTO searchCriteria) {

        Set<ProductViewDTO> search = productMappingService.search(searchCriteria);

        return search;
    }

    @GetMapping()
    public List<ProductViewDTO> findAll() {
        return productMappingService.findAll();
    }
}
