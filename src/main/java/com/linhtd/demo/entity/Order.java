/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Linh
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;
    
    @Column(name = "description")
    @NotNull
    @Size(max = 150)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "userid")
    private User userid;
    
    @Column(name = "oderdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    
    @Column(name = "shipped")
    @NotNull
    private boolean shipped;
    
    @Column(name = "valid")
    @NotNull
    private boolean valid;
    
    @Column(name = "modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;

    public Order() {
    }

    public Order(int id, String description, User userid, Date orderdate, boolean shipped, boolean valid, Date modify) {
        this.id = id;
        this.description = description;
        this.userid = userid;
        this.orderdate = orderdate;
        this.shipped = shipped;
        this.valid = valid;
        this.modify = modify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
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
