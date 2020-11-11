package com.frodolele.springproduct.services;

import com.frodolele.springproduct.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void create(Product product);

    List<Product> findByNameOrBrand(String nameOrBrand);

    boolean update(Product product, int id);

    boolean delete(int id);

    List<Product> getAllLeftovers();

}
