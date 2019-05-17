/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.OrderDetail;
import com.linhtd.demo.entity.OrderDetailIdentity;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Linh
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailIdentity> {

    // Paging
    @Query(value = "SELECT od FROM OrderDetail od")
    public List<OrderDetail> paging(Pageable pageable);
    
    //Paging and searching with order id
    @Query("SELECT od from OrderDetail od where LOWER(od.orderDetailIdentity.order.id) LIKE CONCAT('%',LOWER(:orderid),'%')")
    //old: public List<OrderDetail> findAndPaging(@Param("orderid") String orderid, Pageable pageable);
    public List<OrderDetail> findAndPaging(String orderid, Pageable pageable);
    
    // Searching
    @Query("SELECT od FROM OrderDetail od WHERE LOWER(od.orderDetailIdentity.order.id) = LOWER(:orderid)")
    public List<OrderDetail> search(@Param("orderid") String orderid);
    
}
