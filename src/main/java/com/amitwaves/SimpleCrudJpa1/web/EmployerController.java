/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.web;

import com.amitwaves.SimpleCrudJpa1.model.Employer;
import com.amitwaves.SimpleCrudJpa1.service.EmployerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author amit
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@Slf4j
public class EmployerController {

    @Autowired
    EmployerService employerService;

    @GetMapping("/employers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employer> getAll() {
        return employerService.getAll();
    }

    @GetMapping("/employers/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Employer get(@PathVariable Long id) {
        return employerService.getOne(id);
    }

    @PostMapping("/employers")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employer add(@RequestBody Employer employer) {
        return employerService.addEmployer(employer);
    }

    @PutMapping("/employers/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Employer update(@PathVariable Long id, @RequestBody Employer employer) {
        return employerService.updateEmployer(id, employer);
    }

    @DeleteMapping("/employers/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employerService.deleteEmployer(id);
    }

}
