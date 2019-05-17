/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.entity.Order;
import com.linhtd.demo.entity.Voucher;
import com.linhtd.demo.repository.OrderRepository;
import com.linhtd.demo.repository.VoucherRepository;
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
@RequestMapping(value = "/voucher")
public class VoucherController {
    
    @Autowired
    private VoucherRepository voucherRepository;

    // GET list orders
    @GetMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Voucher> readAll() {
        return voucherRepository.findAll();
    }

    //GET order by id
    @GetMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Voucher read(@PathVariable int id) {
        return voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create order
    @PostMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Voucher create(@RequestBody Voucher newVoucher) {
        return voucherRepository.save(newVoucher);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Voucher update(@RequestBody Voucher editedVoucher, @PathVariable int id) {
        return voucherRepository.findById(id)
                .map(voucher -> {
                    voucher.setDiscount(editedVoucher.getDiscount());
                    voucher.setName(editedVoucher.getName());
                    return voucherRepository.save(voucher);
                })
                .orElseGet(() -> {
                    editedVoucher.setId(id);
                    return voucherRepository.save(editedVoucher);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        voucherRepository.deleteById(id);
    }

//        // GET item by name and paging
//    @GetMapping(value = "/search")
//    @CrossOrigin(value = "http://wwww.localhost:4200")
//    Iterable<Order> findByName(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer page) {
//        if(keyword == null) {
//            keyword = "";
//        }
//        if (page == null) {
//            return orderRepository.findByDescription(keyword);
//        } else {
//            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "description"));
//            Pageable pageable = new PageRequest(page, 2, sort);
//            return orderRepository.findAndPaging(keyword, pageable);
//        }
//    }
    
}
