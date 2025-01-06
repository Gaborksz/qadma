package com.practise.qadma.service;


import com.practise.qadma.dao.ArchiveProductRepositoryImpl;
import com.practise.qadma.entity.ArchivedProduct;
import com.practise.qadma.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivedProductServiceImpl implements ArchivedProductService {

    private final ArchiveProductRepositoryImpl archivedProductRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArchivedProductServiceImpl(ArchiveProductRepositoryImpl archivedProductRepository, ModelMapper modelMapper) {
        this.archivedProductRepository = archivedProductRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArchivedProduct archiveProduct(Product product) {

        ArchivedProduct archivedProduct = modelMapper.map(product, ArchivedProduct.class);
        archivedProduct.setId(0);
        archivedProduct.setDateCreated(product.getDateModified());
        return archivedProductRepository.save(archivedProduct);
    }
}
