package com.example.springbatch.configuration;

import com.example.springbatch.model.Person;
import com.example.springbatch.repository.PersonRepository;
import com.example.springbatch.service.query.PersonQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class DatabaseRunner implements CommandLineRunner {
    private PersonRepository personRepository;
    private PersonQueryService personQueryService;

    public DatabaseRunner(PersonRepository personRepository, PersonQueryService personQueryService) {
        this.personRepository = personRepository;
        this.personQueryService = personQueryService;
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.save(new Person("Marley", "Bolivia"));
        personRepository.save(new Person("Bob", "Finland"));
        personRepository.save(new Person("Elvis", "Slovenia"));

        long start = System.currentTimeMillis();

        CompletableFuture<Person> person1 = personQueryService.findPersonByName("Bob");
        CompletableFuture<Person> person2 = personQueryService.findPersonByName("Elvis");
        CompletableFuture<Person> person3 = personQueryService.findPersonByName("Marley");

        CompletableFuture.allOf(person1, person2, person3).join();

        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info(" --> " + person1.get());
        log.info(" --> " + person2.get());
        log.info(" --> " + person3.get());
    }
}
