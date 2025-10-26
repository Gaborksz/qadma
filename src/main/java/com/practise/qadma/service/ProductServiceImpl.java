package com.practise.qadma.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.ProductRepository;
import com.practise.qadma.entity.Product;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import com.practise.qadma.payload.view.ProductViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QadmaUserService qadmaUserService;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, QadmaUserService qadmaUserService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.qadmaUserService = qadmaUserService;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductViewDTO findById(long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ItemNotFoundException(id, "Product");}

        Set<Product> products = new HashSet<>();
        products.add(product.get());
        populateUserFields(products);

        Product  productWithUserData = products.stream().findFirst().get();

        return modelMapper.map(productWithUserData, ProductViewDTO.class);
    }


    @Override
    public Product findByInspectionPlanId(long inspectionPlanId) {
        return productRepository.findByInspectionPlanId(inspectionPlanId);
    }


    @Override
    public Set<Product> getProductsByInspectionTemplateId(long id) {
        return productRepository.getProductsByInspectionTemplateId(id);
    }


    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public Product update(Product updatedProduct) {
        return productRepository.update(updatedProduct);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Set<ProductViewDTO> search(ProductSearchCriteriaDTO searchCriteria) {

        Set<Product> products = productRepository.search(searchCriteria);
        Set<Product> productsWithUserData = populateUserFields(products);

        return productsWithUserData.stream()
                .map(product -> modelMapper.map(product, ProductViewDTO.class))
                .collect(Collectors.toSet());
    }

    public Set<Product> populateUserFields(Set<Product> products) {

        Set<Long> userIds = products.stream()
                .flatMap(p -> Stream.of(p.getCreatorId(), p.getModifierId()))
                .collect(Collectors.toSet());

        Map<Long, QadmaUser> userMap = qadmaUserService.findUsersByIds(userIds).stream()
                .collect(Collectors.toMap(QadmaUser::getId, user -> user));

        products.forEach(p -> {
            long creatorId = p.getCreatorId();
            long modifierId = p.getModifierId();
            p.setCreatedBy(userMap.get(creatorId));
            p.setModifiedBy(userMap.get(modifierId));
        });

        return products;
    }
}
