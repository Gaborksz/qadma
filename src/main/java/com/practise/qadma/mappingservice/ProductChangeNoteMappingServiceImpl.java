package com.practise.qadma.mappingservice;

import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import com.practise.qadma.service.ProductChangeNoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductChangeNoteMappingServiceImpl implements ProductChangeNoteMappingService {

    private ModelMapper modelMapper;
    private ProductChangeNoteService productChangeNoteService;

    @Autowired
    public ProductChangeNoteMappingServiceImpl(ModelMapper modelMapper, ProductChangeNoteService productChangeNoteService) {
        this.modelMapper = modelMapper;
        this.productChangeNoteService = productChangeNoteService;
    }


    @Override
    public Set<ProductChangeNoteViewDTO> getProductChangeNotes(Set<Long> ids) {

        Set<ProductChangeNote> productChangeNotes = productChangeNoteService.getProductChangeNotes(ids);

        return productChangeNotes.stream()
                .map(productChangeNote -> modelMapper.map(productChangeNote, ProductChangeNoteViewDTO.class))
                .collect(Collectors.toSet());
    }
}
