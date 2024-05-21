package com.example.developer.demo.controller;

import com.example.developer.demo.entity.Developer;
import com.example.developer.demo.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRepository;


    @PostMapping("/save")
    public String saveDeveloper(@RequestBody Developer developer){
        developerRepository.save(developer);
        return "Saved successfully....";

    }

    @GetMapping("/get")
    public ResponseEntity<List<Developer>> getDeveloper(){
        List<Developer> developerList= new ArrayList<Developer>();
        developerRepository.findAll().forEach(developerList::add);
        return new ResponseEntity<List<Developer>>(developerList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDeveloper(@PathVariable("id") long id){
        developerRepository.deleteById(id);
        return "delete successfully by id";
    }
    @PutMapping("/put/{id}")
    public String updateDeveloperById(@PathVariable("id") long id,@RequestBody Developer developer) {
        Developer dev = developerRepository.findById(id).get();
        if (dev != null) {
            developer.setId(id);
            developerRepository.save(developer);
            return "Developer details with " + id + "updated successfully...";
        } else {
            return "Developer details with " + id + "not found..";
        }
    }

}
