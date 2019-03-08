/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.service;

import com.amitwaves.SimpleCrudJpa1.exception.ResourceNotFoundException;
import com.amitwaves.SimpleCrudJpa1.model.Employer;
import com.amitwaves.SimpleCrudJpa1.repository.EmployerRespository;
import java.text.MessageFormat;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

/**
 *
 * @author amit
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployerServiceTest {

    @Mock
    EmployerRespository respositoryMock;

    @InjectMocks
    EmployerService service;

    @Test
    public void testGetOne() {
        Employer expected = new Employer();
        expected.setId(Long.MIN_VALUE);
        doReturn(Optional.of(expected)).when(respositoryMock).findOne(any(Example.class));
//        given(respositoryMock.findOne(any(Example.class))).willReturn(Optional.of(expected));
        Employer actual = service.getOne(Long.MIN_VALUE);
        assertThat(actual).isEqualToIgnoringNullFields(expected);
    }

    @Test
    public void testGetOne_ThrowsException() {
        String message = MessageFormat.format("Resource not found for id {0}", Long.MAX_VALUE);
        doReturn(Optional.empty()).when(respositoryMock).findOne(any(Example.class));
        Throwable thrown = catchThrowable(() -> {
            service.getOne(Long.MAX_VALUE);
            throw new ResourceNotFoundException(message);
        });

        assertThat(thrown).isInstanceOf(ResourceNotFoundException.class).hasMessage(message);
    }

}
