/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.service;

import com.amitwaves.SimpleCrudJpa1.exception.InvalidRequestException;
import com.amitwaves.SimpleCrudJpa1.exception.ResourceNotFoundException;
import com.amitwaves.SimpleCrudJpa1.model.Employer;
import java.text.MessageFormat;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.amitwaves.SimpleCrudJpa1.repository.EmployerRespository;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author amit
 */
@Service
@Slf4j
public class EmployerService {

    @Autowired
    EmployerRespository employerRepository;

    public Employer getOne(Long id) {
        Employer employer = new Employer();
        employer.setId(id);
        Example<Employer> exmpl = Example.of(employer);
        Optional<Employer> result = employerRepository.findOne(exmpl);
        return result.orElseThrow(() -> new ResourceNotFoundException(
                MessageFormat.format("Resource not found for id {0}", id)));
    }

    public Employer addEmployer(Employer employer) {
        if (Objects.nonNull(employer.getId())) {
            throw new InvalidRequestException("Employer cannot be created with non null id.");
        }
        employer.getProjects().stream().forEach(project -> employer.addProject(project));
        return employerRepository.save(employer);
    }

    public Employer updateEmployer(Long id, Employer employer) {
        if (Objects.isNull(employer.getId()) || employer.getId() <= 0) {
            throw new InvalidRequestException("Invalid employee id.");
        }
        Employer empToUpdate = getOne(id);
        empToUpdate.setName(employer.getName());
        return employerRepository.save(employer);
    }

    public void deleteEmployer(Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new InvalidRequestException("Invalid employee id.");
        }
        employerRepository.deleteById(id);
    }

    public List<Employer> getAll() {
        return employerRepository.findAll();
    }
}
