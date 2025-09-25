package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;

import java.util.Set;

public interface ProductChangeNoteMappingService {

    Set<ProductChangeNoteViewDTO> getProductChangeNotes(Set<Long> ids);
}
