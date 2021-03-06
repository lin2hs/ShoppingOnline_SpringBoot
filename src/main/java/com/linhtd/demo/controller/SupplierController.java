/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.SupplierRepository;
import com.linhtd.demo.entity.Supplier;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    // GET list suppliers
    @GetMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Supplier> readAll() {
        return supplierRepository.findAll();
    }

    //GET supplier by id
    @GetMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Supplier read(@PathVariable int id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create supplier
    @PostMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Supplier create(@RequestBody Supplier newSupplier) {
        return supplierRepository.save(newSupplier);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Supplier update(@RequestBody Supplier editedSupplier, @PathVariable int id) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setName(editedSupplier.getName());
                    supplier.setCompany((editedSupplier.getCompany()));
                    supplier.setAddress(editedSupplier.getAddress());
                    supplier.setCity(editedSupplier.getCity());
                    supplier.setPhone(editedSupplier.getPhone());
                    supplier.setFax(editedSupplier.getFax());
                    supplier.setValid(editedSupplier.isValid());
                    supplier.setModify(Calendar.getInstance().getTime());
                    return supplierRepository.save(supplier);
                })
                .orElseGet(() -> {
                    editedSupplier.setId(id);
                    return supplierRepository.save(editedSupplier);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        supplierRepository.deleteById(id);
    }

    // GET item by name and paging
    @GetMapping(value = "/search")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Supplier> findByName(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer page) {
        if (keyword == null) {
            keyword = "";
        }
        if (page == null) {
            return supplierRepository.findByName(keyword);
        } else {
            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
            Pageable pageable = new PageRequest(page, 2, sort);
            return supplierRepository.findAndPaging(keyword, pageable);
        }
    }

}
