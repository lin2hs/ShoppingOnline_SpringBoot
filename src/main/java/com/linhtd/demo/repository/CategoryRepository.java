/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.Category;
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
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    @Query("SELECT p FROM Category p WHERE LOWER(p.name) LIKE  CONCAT ('%',LOWER(:name),'%')")
    public List<Category> findByName(@Param("name") String name);
    
    @Query("SELECT p from Category p where LOWER(p.name) LIKE CONCAT('%',LOWER(:name),'%')")
    public List<Category> findAndPaging(@Param("name") String name, Pageable pageable);
}
