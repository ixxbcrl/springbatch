package com.example.springbatch.api;

import com.example.springbatch.model.Person;
import com.example.springbatch.service.query.PersonQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping("/api/add-all-customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCustomers() {
        personQueryService.addAllPersons();
    }

}
