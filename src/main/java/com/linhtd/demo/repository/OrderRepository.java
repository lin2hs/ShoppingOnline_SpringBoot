/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.Order;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Linh
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT p FROM Order p WHERE LOWER(p.description) LIKE  CONCAT ('%',LOWER(:description),'%')")
    public List<Order> findByDescription(@Param("description") String description);

    @Query("SELECT p FROM Order p WHERE LOWER(p.description) LIKE  CONCAT ('%',LOWER(:description),'%')")
    public List<Order> findAndPaging(@Param("description") String description, Pageable pageable);
}
