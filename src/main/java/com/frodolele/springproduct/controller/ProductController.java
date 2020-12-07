package com.frodolele.springproduct.controller;

import com.frodolele.springproduct.model.Product;
import com.frodolele.springproduct.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
// make dependency injection
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestBody Product product){
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // use http://localhost:8080/products?name=enterName
    @RequestMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<List<Product>> findByName(@RequestParam(value = "name") String name) {
        final List<Product> productsAfterCheck = productService.findByNameOrBrand(name);

        return productsAfterCheck != null && !productsAfterCheck.isEmpty()
                ? new ResponseEntity<>(productsAfterCheck, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // use http://localhost:8080/products?brand=enterName
    @RequestMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<List<Product>> findByBrand(@RequestParam(value = "brand") String brand) {
        final List<Product> productsAfterCheck = productService.findByNameOrBrand(brand);

        return productsAfterCheck != null && !productsAfterCheck.isEmpty()
                ? new ResponseEntity<>(productsAfterCheck, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        final boolean updated = productService.update(product, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = productService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // returned all elements where quantity < 5
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> read(){
        final List<Product> productsLeftovers = productService.getAllLeftovers();

        return productsLeftovers != null && !productsLeftovers.isEmpty()
                ? new ResponseEntity<>(productsLeftovers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
}
