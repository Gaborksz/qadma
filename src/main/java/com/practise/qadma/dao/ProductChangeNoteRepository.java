package com.practise.qadma.dao;

import com.practise.qadma.entity.ProductChangeNote;

import java.util.Optional;
import java.util.Set;

public interface ProductChangeNoteRepository {

    ProductChangeNote save(ProductChangeNote productChangeNote);

    Set<ProductChangeNote> getProductChangeNotes(Set<Long> ids);
}
