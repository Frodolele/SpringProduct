package com.frodolele.springproduct.repositories;

import com.frodolele.springproduct.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, JpaRepository<Product, Integer> {
    public List<Product> findByName(String name);

    public List<Product> findByBrand(String brand);

    public List<Product> findByQuantityIsLessThan(int quantity);

    public List<Product> findByNameAndBrand(String name, String brand);

}
