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
@Table(name = "Suppliers")
public class Supplier implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;
    
    @Column(name = "name")
    @NotNull
    @Size(max = 50)
    private String name;
    
    @Column(name = "company")
    @NotNull
    @Size(max = 50)
    private String company;
    
    @Column(name = "address")
    @NotNull
    @Size(max = 150)
    private String address;
    
    @Column(name = "city")
    @NotNull
    @Size(max = 50)
    private String city;
    
    @Column(name = "phone")
    @NotNull
    @Size(max = 25)
    private String phone;
    
    @Column(name = "fax")
    @NotNull
    @Size(max = 25)
    private String fax;
    
    @Column(name = "valid")
    @NotNull
    private boolean valid;
            
    @Column(name = "modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;

    public Supplier() {
    }

    public Supplier(int id, String name, String company, String address, String city, String phone, String fax, boolean valid, Date modify) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.fax = fax;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
