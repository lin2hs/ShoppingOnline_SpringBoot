/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Linh
 */
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
    
    @EmbeddedId
    private OrderDetailIdentity orderDetailIdentity;

    @NotNull
    @Column(name = "quantity")
    private int quantity;
    
    @NotNull
    @Column(name = "discount")
    private double discount;
    
    @NotNull
    @Column(name = "valid")
    private boolean valid;
    
    @Column(name = "modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailIdentity orderDetailIdentity, int quantity, double discount, boolean valid, Date modify) {
        this.orderDetailIdentity = orderDetailIdentity;
        this.quantity = quantity;
        this.discount = discount;
        this.valid = valid;
        this.modify = modify;
    }

    public OrderDetailIdentity getOrderDetailIdentity() {
        return orderDetailIdentity;
    }

    public void setOrderDetailIdentity(OrderDetailIdentity orderDetailIdentity) {
        this.orderDetailIdentity = orderDetailIdentity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }
    
}
