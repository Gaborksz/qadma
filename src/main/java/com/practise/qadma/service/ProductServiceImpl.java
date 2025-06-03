package com.practise.qadma.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.ProductRepository;
import com.practise.qadma.entity.Product;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QadmaUserService qadmaUserService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, QadmaUserService qadmaUserService) {
        this.productRepository = productRepository;
        this.qadmaUserService = qadmaUserService;
    }


    @Override
    public Product findById(long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id, "Product"));
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
    public Set<Product> search(ProductSearchCriteriaDTO searchCriteria) {

        Set<Product> products = productRepository.search(searchCriteria);

        Set<Long> userIds = products.stream()
                .map(Product::getCreatorId)
                .collect(Collectors.toSet());

        userIds.addAll(products.stream().map(Product::getModifierId).collect(Collectors.toSet()));

        Map<Long, QadmaUser> users = qadmaUserService.findUsersByIds(userIds).stream()
                .collect(Collectors.toMap(QadmaUser::getId, user -> user));

        products.forEach(p -> {
            long creatorId = p.getCreatorId();
            long modifierId = p.getModifierId();
            p.setCreatedBy(users.get(creatorId));
            p.setModifiedBy(users.get(modifierId));
        });

        return products;
    }
}
