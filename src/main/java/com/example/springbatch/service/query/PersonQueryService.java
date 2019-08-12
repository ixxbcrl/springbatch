package com.example.springbatch.service.query;

import com.example.springbatch.model.Person;
import com.example.springbatch.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PersonQueryService {
    private PersonRepository personRepository;

    public PersonQueryService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Async
    public CompletableFuture<Person> findPersonByName(String name) throws InterruptedException {
        Person person = personRepository.findByName(name);

        log.info(Thread.currentThread().getName() + " <--> " + Thread.currentThread().getId());
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(person);
    }
}
