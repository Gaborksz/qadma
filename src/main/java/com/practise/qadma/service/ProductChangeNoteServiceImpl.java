package com.practise.qadma.service;

import com.practise.qadma.dao.ProductChangeNoteRepository;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public ProductChangeNote getProductChangeNote(long id) {

        Optional<ProductChangeNote> productChangeNote = productChangeNoteRepository.getProductChangeNote(id);

        if (productChangeNote.isEmpty()) throw  new ItemNotFoundException(id, "ProductChangeNote");

        return productChangeNote.get();
    }
}
