package com.frodolele.springproduct.controller;

import com.frodolele.springproduct.entities.Product;
import com.frodolele.springproduct.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // add product
    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestBody Product product){
        productService.addOrUpdate(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // use http://localhost:8189/test/products?name=enterName
    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<List<Product>> findByName(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value="brand", required = false) String brand) {
        List<Product> productsAfterCheck = new ArrayList<>();
        // use specification in future
        if (name == null && brand == null) {
            productsAfterCheck = productService.findAll();
        }
        if (name != null && brand == null) {
            productsAfterCheck = productService.findByName(name);
        }
        if (name == null && brand != null) {
            productsAfterCheck = productService.findByBrand(brand);
        }
        if (name != null && brand != null) {
            productsAfterCheck = productService.findByNameAndBrand(name, brand);
        }

        return productsAfterCheck != null && !productsAfterCheck.isEmpty()
                ? new ResponseEntity<>(productsAfterCheck, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    // use http://localhost:8189/test/products?brand=enterName
//    @RequestMapping(value = "/products")
//    @ResponseBody
//    public ResponseEntity<List<Product>> findByBrand(@RequestParam(value = "brand") String brand) {
//        final List<Product> productsAfterCheck = productService.findByBrand(brand);
//
//        return productsAfterCheck != null && !productsAfterCheck.isEmpty()
//                ? new ResponseEntity<>(productsAfterCheck, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        productService.addOrUpdate(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // returned all elements where quantity < 5
    @GetMapping(value = "/products/remains")
    public ResponseEntity<List<Product>> read(){
        final List<Product> productsLeftovers = productService.getAllLeftovers();

        return productsLeftovers != null && !productsLeftovers.isEmpty()
                ? new ResponseEntity<>(productsLeftovers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
