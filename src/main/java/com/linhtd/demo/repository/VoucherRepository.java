/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Linh
 */
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    
}
