package com.frodolele.springproduct.services;

import com.frodolele.springproduct.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// Describes the business logic
@Service
public class ProductServiceImpl implements ProductService {

    // Storage products
    private static final Map<Integer, Product> PRODUCT_REPOSITORY_MAP = new HashMap<>();

    // generated id
    private static final AtomicInteger PRODUCT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Product product) {
        final int productId = PRODUCT_ID_HOLDER.incrementAndGet();
        product.setId(productId);
        PRODUCT_REPOSITORY_MAP.put(productId, product);

    }

    @Override
    public List<Product> findByNameOrBrand(String nameOrBrand) {
        ArrayList<Product> result = new ArrayList<>();
        for (Map.Entry<Integer, Product> entry : PRODUCT_REPOSITORY_MAP.entrySet()) {
            Product value = entry.getValue();

            if (value.getName() == nameOrBrand || value.getBrand() == nameOrBrand) {
                result.add(value);
            }

        }
        return result;
    }

    @Override
    public boolean update(Product product, int id) {
        if (PRODUCT_REPOSITORY_MAP.containsKey(id)) {
            product.setId(id);
            PRODUCT_REPOSITORY_MAP.put(id, product);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return PRODUCT_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public List<Product> getAllLeftovers() {
        ArrayList<Product> result = new ArrayList<>();
        for (Map.Entry<Integer, Product> entry: PRODUCT_REPOSITORY_MAP.entrySet()) {
            Product value = entry.getValue();

            if (value.getQuantity() < 5) {
                result.add(value);
            }
        }
        return result;
    }
}
