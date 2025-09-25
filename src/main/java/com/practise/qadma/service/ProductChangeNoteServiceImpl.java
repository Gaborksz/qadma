package com.practise.qadma.service;

import com.practise.qadma.auth.dao.QadmaUserRepository;
import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.ProductChangeNoteRepository;
import com.practise.qadma.entity.Product;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductChangeNoteServiceImpl implements ProductChangeNoteService {

    private final ProductChangeNoteRepository productChangeNoteRepository;
    private final QadmaUserService qadmaUserService;
    private final ProductService productService;

    @Autowired
    public ProductChangeNoteServiceImpl(ProductChangeNoteRepository productChangeNoteRepository, QadmaUserService qadmaUserService, ProductService productService) {
        this.productChangeNoteRepository = productChangeNoteRepository;
        this.qadmaUserService = qadmaUserService;
        this.productService = productService;
    }


    @Override
    public ProductChangeNote save(ProductChangeNote productChangeNote) {

        return productChangeNoteRepository.save(productChangeNote);
    }

    @Override
    public Set<ProductChangeNote> getProductChangeNotes(Set<Long> ids) {

        Set<ProductChangeNote> productChangeNotes = productChangeNoteRepository.getProductChangeNotes(ids);

        populateUserFields(productChangeNotes);

        Set<Product> products = productChangeNotes.stream()
                .map(ProductChangeNote::getProduct)
                .collect(Collectors.toSet());

        productService.populateUserFields(products);

        return productChangeNotes;
    }

    public Set<ProductChangeNote> populateUserFields(Set<ProductChangeNote> productChangeNotes) {

        Set<Long> userIds = productChangeNotes.stream()
                .flatMap(p -> Stream.of(p.getCreatorId()))
                .collect(Collectors.toSet());

        Map<Long, QadmaUser> userMap = qadmaUserService.findUsersByIds(userIds).stream()
                .collect(Collectors.toMap(QadmaUser::getId, user -> user));

        productChangeNotes.forEach(p -> {
            long creatorId = p.getCreatorId();
            p.setCreatedBy(userMap.get(creatorId));
        });

        return productChangeNotes;
    }
}
