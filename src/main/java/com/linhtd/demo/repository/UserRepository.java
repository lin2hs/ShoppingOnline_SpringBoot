/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.repository;

import com.linhtd.demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
    
}
