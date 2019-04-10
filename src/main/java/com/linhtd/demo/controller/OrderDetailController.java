/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.repository.OrderDetailRepository;
import com.linhtd.demo.repository.OrderRepository;
import com.linhtd.demo.repository.ProductRepository;
import com.linhtd.demo.entity.Order;
import com.linhtd.demo.entity.OrderDetail;
import com.linhtd.demo.entity.OrderDetailIdentity;
import com.linhtd.demo.entity.Product;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // GET list items
    @GetMapping("")
    Iterable<OrderDetail> readAll() {
        return orderDetailRepository.findAll();
    }

    // GET single item
    @GetMapping(value = "/{orderid}/{productid}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    OrderDetail read(@PathVariable int orderid, @PathVariable int productid) {

        Optional<Order> optionalOrder = orderRepository.findById(orderid);
        Order order = optionalOrder.get();

        Optional<Product> optionalProduct = productRepository.findById(productid);
        Product product = optionalProduct.get();

        OrderDetailIdentity identity = new OrderDetailIdentity(order, product);

        return orderDetailRepository.findById(identity)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // POST create item
    @PostMapping(value = "")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    OrderDetail create(@RequestBody OrderDetail newOrderDetail) {
        return orderDetailRepository.save(newOrderDetail);
    }

    // PUT edit item
    @PutMapping(value = "/{orderid}/{productid}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    OrderDetail update(@RequestBody OrderDetail edittedOrderDetail, @PathVariable int orderid, @PathVariable int productid) {

        Optional<Order> optionalOrder = orderRepository.findById(orderid);
        Order order = optionalOrder.get();

        Optional<Product> optionalProduct = productRepository.findById(productid);
        Product product = optionalProduct.get();

        OrderDetailIdentity identity = new OrderDetailIdentity(order, product);

        return orderDetailRepository.findById(identity)
                .map(orderDetail -> {
                    orderDetail.setQuantity(edittedOrderDetail.getQuantity());
                    orderDetail.setDiscount(edittedOrderDetail.getDiscount());
                    orderDetail.setValid(edittedOrderDetail.isValid());
                    orderDetail.setModify(Calendar.getInstance().getTime());
                    return orderDetailRepository.save(orderDetail);
                })
                .orElseGet(() -> {
                    edittedOrderDetail.setOrderDetailIdentity(identity);
                    return orderDetailRepository.save(edittedOrderDetail);
                });
    }

    // DELETE remove item
    @DeleteMapping(value = "/{orderid}/{productid}")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    void delete(@PathVariable int orderid, @PathVariable int productid) {

        Optional<Order> optionalOrder = orderRepository.findById(orderid);
        Order order = optionalOrder.get();

        Optional<Product> optionalProduct = productRepository.findById(productid);
        Product product = optionalProduct.get();

        OrderDetailIdentity identity = new OrderDetailIdentity(order, product);

        orderDetailRepository.deleteById(identity);
    }

}
