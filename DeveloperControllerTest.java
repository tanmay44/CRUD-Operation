package com.example.developer.demo.controllerTest;


import com.example.developer.demo.controller.DeveloperController;
import com.example.developer.demo.entity.Developer;
import com.example.developer.demo.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeveloperControllerTest {

    @InjectMocks
    private DeveloperController developerController;

    @Mock
    private DeveloperRepository developerRepository;


    @Test
    public void saveDeveloperTest(){
        Developer developer=new Developer();
        when(developerRepository.save(developer)).thenReturn(developer);
        String result = developerController.saveDeveloper(developer);
        verify(developerRepository, times(1)).save(developer);
        assertEquals("Saved successfully....",result);


    }

    @Test
    public void getDeveloperTest(){
        List<Developer> developer=new ArrayList<>();
        Mockito.when(developerRepository.findAll()).thenReturn(developer);
        ResponseEntity<List<Developer>> responseEntity = developerController.getDeveloper();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(developer, responseEntity.getBody());
    }

    @Test
    public void deleteDeveloperTest(){
        doNothing().when(developerRepository).deleteById(1L);
        String result = developerController.deleteDeveloper(1L);
        assertEquals("delete successfully by id", result);
        verify(developerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateDeveloperByIdTest(){
        long id = 1L;
        Developer developer = new Developer();
        when(developerRepository.findById(id)).thenReturn(java.util.Optional.of(developer));
        Developer developer1 = new Developer();
        String result = developerController.updateDeveloperById(id, developer1);
        verify(developerRepository, times(1)).findById(id);
        verify(developerRepository, times(1)).save(developer1);
        assertEquals("Developer details with " + id + " updated successfully...", result);

    }
    }


