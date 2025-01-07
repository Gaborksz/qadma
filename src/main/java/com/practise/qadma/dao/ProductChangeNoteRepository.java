package com.practise.qadma.dao;

import com.practise.qadma.entity.ProductChangeNote;

import java.util.Optional;

public interface ProductChangeNoteRepository {

    ProductChangeNote save(ProductChangeNote productChangeNote);

    Optional<ProductChangeNote> getProductChangeNote(long id);
}
