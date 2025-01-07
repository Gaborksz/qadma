package com.practise.qadma.service;

import com.practise.qadma.entity.ProductChangeNote;

public interface ProductChangeNoteService {

    ProductChangeNote save(ProductChangeNote productChangeNote);

    ProductChangeNote getProductChangeNote(long id);
}
