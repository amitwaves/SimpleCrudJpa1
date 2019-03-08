/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.web;

import com.amitwaves.SimpleCrudJpa1.model.Employer;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author amit
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        Employer expected = new Employer();
        expected.setId(id);
        String url = "http://localhost:" + port + "/employers/{id}";
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", id.toString());
        UriComponents uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(uriParams);
        Employer actual = testRestTemplate.getForObject(uri.toUri(), Employer.class);
        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "id");
    }

}
