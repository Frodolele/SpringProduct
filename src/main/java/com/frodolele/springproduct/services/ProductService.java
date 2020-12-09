package com.frodolele.springproduct.services;

import com.frodolele.springproduct.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void addOrUpdate(Product product);

    List<Product> findAll();

    List<Product> findByName (String name);

    List<Product> findByBrand (String brand);

    List<Product> findByNameAndBrand(String name, String brand);

    void delete(int id);

    List<Product> getAllLeftovers();

}
