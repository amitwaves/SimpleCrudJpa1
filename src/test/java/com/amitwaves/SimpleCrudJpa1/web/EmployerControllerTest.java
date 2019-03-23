/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.web;

import com.amitwaves.SimpleCrudJpa1.model.Employer;
import com.amitwaves.SimpleCrudJpa1.service.EmployerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author amit
 * Instantiates only the web layer and not the server or application 
 * context. Test runs even faster. 
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployerController.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerServiceMock;

    @Test
    public void testGet() throws Exception {
        Employer expected = new Employer();
        expected.setId(Long.MIN_VALUE);

        doReturn(expected).when(employerServiceMock).getOne(any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/employers/1"))
                .andExpect(status().is2xxSuccessful());
    }
}
