package com.practise.qadma.service;

import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.ProductChangeNoteRepository;
import com.practise.qadma.entity.ArchivedProduct;
import com.practise.qadma.entity.Product;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.ProductChangeNoteDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductChangeNoteServiceImpl implements ProductChangeNoteService {

    private final ProductChangeNoteRepository productChangeNoteRepository;
    private final QadmaUserService qadmaUserService;
    private final ProductService productService;
    private final ArchivedProductService archivedProductService;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductChangeNoteServiceImpl(ProductChangeNoteRepository productChangeNoteRepository, QadmaUserService qadmaUserService, ProductService productService, ArchivedProductService archivedProductService, ModelMapper modelMapper) {
        this.productChangeNoteRepository = productChangeNoteRepository;
        this.qadmaUserService = qadmaUserService;
        this.productService = productService;
        this.archivedProductService = archivedProductService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @Override
    public ProductChangeNoteDTO save(ProductChangeNoteDTO dto) {

        ProductChangeNote changeNote = modelMapper.map(dto, ProductChangeNote.class);

        Product managedProduct = productService.save(changeNote.getProduct());
        ArchivedProduct archivedProduct = archivedProductService.archiveProduct(managedProduct);

        changeNote.setProduct(managedProduct);
        changeNote.setArchivedProduct(archivedProduct);

        productChangeNoteRepository.save(changeNote);
        return modelMapper.map(changeNote, ProductChangeNoteDTO.class);
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

//        Set<Long> userIds = productChangeNotes.stream()
//                .flatMap(p -> Stream.of(p.getCreatorId()))
//                .collect(Collectors.toSet());
//
//        Map<Long, QadmaUser> userMap = qadmaUserService.findUsersByIds(userIds).stream()
//                .collect(Collectors.toMap(QadmaUser::getId, user -> user));
//
//        productChangeNotes.forEach(p -> {
//            long creatorId = p.getCreatorId();
//            p.setCreatedBy(userMap.get(creatorId));
//        });

        return productChangeNotes;
    }
}
