/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.CategoryRepository;
import com.linhtd.demo.entity.Category;
import com.linhtd.demo.entity.Product;
import com.linhtd.demo.repository.ProductRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Linh
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // GET list categories
    @GetMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Category> readAll() {
        return categoryRepository.findAll();
    }

    //GET category by id
    @GetMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Category read(@PathVariable int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create category
    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Category create(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Category update(@RequestBody Category editedCategory, @PathVariable int id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(editedCategory.getName());
                    category.setValid(editedCategory.isValid());
                    category.setModify(Calendar.getInstance().getTime());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    editedCategory.setId(id);
                    return categoryRepository.save(editedCategory);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        List<Product> listProduct = productRepository.findAll();
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getCategory().getId() == id) {
                return;
            }
            categoryRepository.deleteById(id);
        }
    }

    // GET item by name and paging
    @GetMapping(value = "/search")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Category> findByName(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer page) {
        if (keyword == null) {
            keyword = "";
        }
        if (page == null) {
            return categoryRepository.findByName(keyword);
        } else {
            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
            Pageable pageable = new PageRequest(page, 2, sort);
            return categoryRepository.findAndPaging(keyword, pageable);
        }
    }

    // GET item by name & paging
//    @GetMapping(value = "/search/{keyword}")
//    @CrossOrigin(value = "http://wwww.localhost:4200")
//    Iterable<Category> findByName(@PathVariable String keyword) {
//        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
//        Pageable pageable = new PageRequest(page, 2, sort);
//        return categoryRepository.findAndPaging(keyword, pageable);
//    }
}
