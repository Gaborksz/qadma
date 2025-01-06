package com.practise.qadma.service;

import com.practise.qadma.dao.ProductChangeNoteRepository;
import com.practise.qadma.entity.ProductChangeNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductChangeNoteServiceImpl implements ProductChangeNoteService {

    private final ProductChangeNoteRepository productChangeNoteRepository;

    @Autowired
    public ProductChangeNoteServiceImpl(ProductChangeNoteRepository productChangeNoteRepository) {
        this.productChangeNoteRepository = productChangeNoteRepository;
    }


    @Override
    public ProductChangeNote save(ProductChangeNote productChangeNote) {
        return productChangeNoteRepository.save(productChangeNote);
    }
}
