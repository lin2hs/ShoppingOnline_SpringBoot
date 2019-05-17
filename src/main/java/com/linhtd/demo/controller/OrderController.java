/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.OrderRepository;
import com.linhtd.demo.entity.Order;
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
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // GET list orders
    @GetMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Order> readAll() {
        return orderRepository.findAll();
    }

    //GET order by id
    @GetMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Order read(@PathVariable int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create order
    @PostMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Order create(@RequestBody Order newOrder) {
        newOrder.setOrderdate(Calendar.getInstance().getTime());
        return orderRepository.save(newOrder);
    }

    // PUT edit item
    @PutMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Order update(@RequestBody Order editedOrder, @PathVariable int id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setDescription(editedOrder.getDescription());
                    order.setUserid(editedOrder.getUserid());
                    order.setOrderdate(editedOrder.getOrderdate());
                    order.setShipped(editedOrder.isShipped());
                    order.setValid(editedOrder.isValid());
                    order.setVoucherid(editedOrder.getVoucherid());
                    order.setModify(Calendar.getInstance().getTime());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    editedOrder.setId(id);
                    return orderRepository.save(editedOrder);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{id}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int id) {
        orderRepository.deleteById(id);
    }

        // GET item by name and paging
    @GetMapping(value = "/search")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    Iterable<Order> findByName(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer page) {
        if(keyword == null) {
            keyword = "";
        }
        if (page == null) {
            return orderRepository.findByDescription(keyword);
        } else {
            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "description"));
            Pageable pageable = new PageRequest(page, 2, sort);
            return orderRepository.findAndPaging(keyword, pageable);
        }
    }
    
}
