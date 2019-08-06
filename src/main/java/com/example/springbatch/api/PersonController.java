package com.example.springbatch.api;

import com.example.springbatch.model.Person;
import com.example.springbatch.service.query.PersonQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private PersonQueryService personQueryService;

    public PersonController(PersonQueryService personQueryService) {
        this.personQueryService = personQueryService;
    }

    @GetMapping("/api/customers")
    public List<Person> getCustomers() {
        return personQueryService.getAllPersons();
    }
}
