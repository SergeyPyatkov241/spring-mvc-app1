package ru.pyatkov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pyatkov.springcourse.dao.PersonDAO;
import ru.pyatkov.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        // посмотреть, есть ли человек с таким же email в БД
        if(personDAO.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Этот email уже используется");
        }
    }
}