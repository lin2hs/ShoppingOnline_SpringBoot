/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.ProductRepository;
import com.linhtd.demo.entity.Product;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Linh
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // GET list products
    @GetMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Product> readAll() {
        return productRepository.findAll();
    }

    //GET product by id
    @GetMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Product read(@PathVariable int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create product
    @PostMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Product create(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Product update(@RequestBody Product editedProduct, @PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(editedProduct.getName());
                    product.setDescription(editedProduct.getDescription());
                    product.setPrice(editedProduct.getPrice());
                    product.setQuantity(editedProduct.getQuantity());
                    product.setSupplier(editedProduct.getSupplier());
                    product.setThumbnail(editedProduct.getThumbnail());
                    product.setValid(editedProduct.isValid());
                    product.setModify(Calendar.getInstance().getTime());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    editedProduct.setId(id);
                    return productRepository.save(editedProduct);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        productRepository.deleteById(id);
    }

}
