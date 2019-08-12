package com.example.springbatch.repository;

import com.example.springbatch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person findByName(String name);
}
