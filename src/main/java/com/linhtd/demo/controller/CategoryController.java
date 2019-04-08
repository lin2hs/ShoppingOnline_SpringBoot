/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.CategoryRepository;
import com.linhtd.demo.entity.Category;
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
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

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
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Category create(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
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
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }

}
