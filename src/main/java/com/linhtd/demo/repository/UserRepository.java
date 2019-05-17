/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Linh
 */
public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User>{
    
    Optional<User> findByName(String name);
    
    static Specification<User> filterByName(String name){
        return (root, cq, cb) -> {
            return cb.like(root.get("name"),"%" + name + "%" );
        };
    }
    
    @Query("SELECT p FROM User p WHERE LOWER(p.name) LIKE  CONCAT ('%',LOWER(:name),'%')")
    public List<User> findUsersByName(@Param("name") String name);
    
}
