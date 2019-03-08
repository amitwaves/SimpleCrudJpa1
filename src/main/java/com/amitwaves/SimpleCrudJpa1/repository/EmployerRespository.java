/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.repository;

import com.amitwaves.SimpleCrudJpa1.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amit
 */
@Repository
public interface EmployerRespository extends JpaRepository<Employer, Long> {

}
