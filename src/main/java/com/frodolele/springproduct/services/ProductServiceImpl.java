package com.frodolele.springproduct.services;

import com.frodolele.springproduct.entities.Product;
import com.frodolele.springproduct.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// Describes the business logic
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByNameAndBrand(String name, String brand) {
        return productRepository.findByNameAndBrand(name, brand);
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllLeftovers() {
        return productRepository.findByQuantityIsLessThan(5);
    }

//    @Override
//    public void create(Product product) {
//        final int productId = PRODUCT_ID_HOLDER.incrementAndGet();
//        product.setId(productId);
//        PRODUCT_REPOSITORY_MAP.put(productId, product);
//
//    }
//
//    @Override
//    public List<Product> findByNameOrBrand(String nameOrBrand) {
//        ArrayList<Product> result = new ArrayList<>();
//        for (Map.Entry<Integer, Product> entry : PRODUCT_REPOSITORY_MAP.entrySet()) {
//            Product value = entry.getValue();
//
//            if (value.getName() == nameOrBrand || value.getBrand() == nameOrBrand) {
//                result.add(value);
//            }
//
//        }
//        return result;
//    }
//
//    @Override
//    public boolean update(Product product, int id) {
//        if (PRODUCT_REPOSITORY_MAP.containsKey(id)) {
//            product.setId(id);
//            PRODUCT_REPOSITORY_MAP.put(id, product);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        return PRODUCT_REPOSITORY_MAP.remove(id) != null;
//    }
//
//    @Override
//    public List<Product> getAllLeftovers() {
//        ArrayList<Product> result = new ArrayList<>();
//        for (Map.Entry<Integer, Product> entry: PRODUCT_REPOSITORY_MAP.entrySet()) {
//            Product value = entry.getValue();
//
//            if (value.getQuantity() < 5) {
//                result.add(value);
//            }
//        }
//        return result;
//    }
}
