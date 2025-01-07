package com.practise.qadma.config;

import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityMappingConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(ProductChangeNote.class, ProductChangeNoteViewDTO.class)
                .addMappings(mapper ->
                        mapper.map(ProductChangeNote::getProduct, ProductChangeNoteViewDTO::setProductDTO));

        return modelMapper;
    }
}
