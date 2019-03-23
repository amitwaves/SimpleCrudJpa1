/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.web;

import com.amitwaves.SimpleCrudJpa1.model.Employer;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author amit Starts the application with full spring context. Will make a
 * HTTP request without starting server.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployerControllerApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        Employer expected = new Employer();
        expected.setId(id);
        String url = "http://localhost:8080/employers/{id}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url, id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        
        String actual = result.getResponse().getContentAsString();
        assertThat(actual).contains("1");
    }
}
