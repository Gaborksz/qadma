package com.practise.qadma.config;

import com.practise.qadma.entity.Product;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EntityMappingConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMethodAccessLevel(AccessLevel.PROTECTED);

        modelMapper.typeMap(ProductChangeNote.class, ProductChangeNoteViewDTO.class)
                .addMappings(mapper ->
                        mapper.map(ProductChangeNote::getProduct, ProductChangeNoteViewDTO::setProductDTO));

        modelMapper.typeMap(Product.class, ProductViewDTO.class)
                .addMappings(mapper -> mapper.map(
                        product -> product.getCreatedBy().getUsername(),
                        ProductViewDTO::setCreatorName))
                .addMappings(mapper -> mapper.map(
                        product -> product.getModifiedBy().getUsername(),
                        ProductViewDTO::setModifierName));

        return modelMapper;
    }
}
