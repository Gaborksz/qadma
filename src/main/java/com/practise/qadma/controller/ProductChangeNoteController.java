package com.practise.qadma.controller;

import com.practise.qadma.mappingservice.ProductChangeNoteMappingService;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/product-change-note")
public class ProductChangeNoteController {

    private final ProductChangeNoteMappingService productChangeNoteMappingService;

    @Autowired
    public ProductChangeNoteController(ProductChangeNoteMappingService productChangeNoteMappingService) {
        this.productChangeNoteMappingService = productChangeNoteMappingService;
    }

    @PostMapping("/ids")
    public Set<ProductChangeNoteViewDTO> getProductChangeNotes(
            @RequestBody List<Long> ids) {

        Set<Long> idSet = new HashSet<>(ids);

        return  productChangeNoteMappingService.getProductChangeNotes(idSet);
    }
}
