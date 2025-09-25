package com.practise.qadma.service;

import com.practise.qadma.entity.ProductChangeNote;

import java.util.Set;

public interface ProductChangeNoteService {

    ProductChangeNote save(ProductChangeNote productChangeNote);

    Set<ProductChangeNote> getProductChangeNotes(Set<Long> ids);
}
