package com.example.developer.demo.repository;

import com.example.developer.demo.entity.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeveloperRepository extends CrudRepository<Developer,Long> {
}
