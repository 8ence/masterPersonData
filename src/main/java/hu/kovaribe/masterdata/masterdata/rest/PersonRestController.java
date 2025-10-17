package hu.kovaribe.masterdata.masterdata.rest;

import hu.kovaribe.masterdata.masterdata.entity.Person;
import hu.kovaribe.masterdata.masterdata.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    private PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/find/{personId}")
    public Person findPerson(@PathVariable int personId) {
        Person person = personService.findById(personId);
        if(person == null) {
            throw new RuntimeException("Person is not found with this id : " + personId);
        }
        return person;
    }

    @GetMapping("/find/allPerson")
    public List<Person> findAllPerson() {return personService.findAll();}

    @PostMapping("/create")
    public Person addPerson(@RequestBody Person person) {

        person.setId(0);
        Person dbPerson = personService.save(person);
        return dbPerson;
    }

    @PutMapping("/update/person")
    public Person updatePerson(@RequestBody Person person) {

        Person dbPerson = personService.save(person);
        return dbPerson;
    }

    @DeleteMapping("/delete/{personId}")
    public String deletePerson(@PathVariable int personId) {

        Person person = personService.findById(personId);
        if(person == null) {
            throw new RuntimeException("Person not found with id : " + personId);
        }
        personService.deleteById(personId);
        return "Deleted person with id : " + personId;
    }
}
