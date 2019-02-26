package com.example.vavr.tutorial;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
    an Applicative Functor enables us to perform
    a sequence of actions while accumulating the results

    a program terminates as soon as an error is encountered.
    => Validation continues processing and accumulating the errors
        for the program to act on them as a batch.
 */
public class ValidationTest {

    // A valid value is contained in a Validation.Valid instance,
    // a list of validation errors is contained in a Validation.Invalid instance
    @Test
    public void  whenValidationWorks_thenCorrect() {
        PersonValidator personValidator = new PersonValidator();

        Validation<Seq<String>, Person> valid =
                personValidator.validatePerson("John Doe", 30);

        Validation<Seq<String>, Person> invalid =
                personValidator.validatePerson("John? Doe!4", -1);

        assertTrue(valid.toString().contains("Valid"));
        assertTrue(invalid.toString().contains("Invalid"));
    }



    /*
        we are registering users by name and age
        we want to take all input first and decide
        whether to create a Person instance or return a list of errors.
        !!! standard constructors, setters and getters, toString !!!
     */
    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
    }

    /*
        Each field will be validated by one method
        another method can be used to combine all the results into one Validation instance
     */
    static class PersonValidator {
        private static final String NAME_ERR = "Invalid characters in name: ";
        private static final String AGE_ERR = "Age must be at least 0";

        public Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            String invalidChars = name.replaceAll("[a-zA-Z ]", "");
            return invalidChars.isEmpty() ?
                    Validation.valid(name)
                    : Validation.invalid(NAME_ERR + invalidChars);
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < 0 ? Validation.invalid(AGE_ERR) : Validation.valid(age);
        }

    }
}
