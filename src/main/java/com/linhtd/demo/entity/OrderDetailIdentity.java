/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Linh
 */
@Embeddable
public class OrderDetailIdentity implements Serializable {
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetailIdentity() {
    }

    public OrderDetailIdentity(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
