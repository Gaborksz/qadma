package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    void updateProduct(long id, Product product);
}
