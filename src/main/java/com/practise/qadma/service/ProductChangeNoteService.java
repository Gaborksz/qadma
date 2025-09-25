package com.practise.qadma.service;

import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.ProductChangeNoteDTO;

import java.util.Set;

public interface ProductChangeNoteService {

    ProductChangeNoteDTO save(ProductChangeNoteDTO productChangeNote);

    Set<ProductChangeNote> getProductChangeNotes(Set<Long> ids);
}
