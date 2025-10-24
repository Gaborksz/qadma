package com.practise.qadma.controller;

import com.practise.qadma.payload.ProductChangeNoteDTO;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import com.practise.qadma.service.ProductChangeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/product-change-note")
public class ProductChangeNoteController {

    private final ProductChangeNoteService productChangeNoteService;

    @Autowired
    public ProductChangeNoteController(ProductChangeNoteService productChangeNoteService) {

        this.productChangeNoteService = productChangeNoteService;
    }

    @PostMapping("/ids")
    public Set<ProductChangeNoteViewDTO> getProductChangeNotes(
            @RequestBody List<Long> ids) {

        return null;
    }

    @PostMapping()
    public ProductChangeNoteDTO save(@RequestBody ProductChangeNoteDTO dto) {
        return productChangeNoteService.save(dto);
    }
}
