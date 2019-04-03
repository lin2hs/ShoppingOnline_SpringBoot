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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Linh
 */
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "description")
    private String description;
    
    @NotNull
    @Min(value = 0, message = "Must bigger than 0")
    @Max(value = Long.MAX_VALUE, message = "Must less than " + Long.MAX_VALUE)
    @Digits(integer = Integer.MAX_VALUE, fraction = 3)
    @Column(name = "price")
    private double price;
    
    @NotNull
    @Min(value = 0)
    @Max(value = Long.MAX_VALUE, message = "Mus less than " + Long.MAX_VALUE)
    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    @Column(name = "quantity")
    private int quantity;
    
    @NotNull
    @Column(name = "thumbnail")
    @Size(max = 150)
    private String thumbnail;
    
    @ManyToOne
    @JoinColumn(name = "cateId")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Supplier supplier;
    
    @Column(name = "valid")
    @NotNull
    private boolean valid;
    
    @Column(name = "modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;

    public Product() {
    }

    public Product(int id, String name, String description, double price, int quantity, String thumbnail, Category category, Supplier supplier, boolean valid, Date modify) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
        this.category = category;
        this.supplier = supplier;
        this.valid = valid;
        this.modify = modify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
