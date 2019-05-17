/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.Supplier;
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
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
    @Query("SELECT p from Supplier p where LOWER(p.name) LIKE CONCAT('%',LOWER(:name),'%')")
    public List<Supplier> findAndPaging(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Supplier p WHERE LOWER(p.name) LIKE  CONCAT ('%',LOWER(:name),'%')")
    public List<Supplier> findByName(@Param("name") String name);
    
}
