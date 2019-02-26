package com.example.vavr.tutorial;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
    There is no direct equivalent of a tuple data structure in Java.

    Tuples are immutable and can hold multiple objects of different types in a type-safe manner.
 */
public class TupleTest {

    // A more obvious use case is returning more than one object from a function or a method in Java.
    // Tuple does not have "type parameter" ( first element )

    @Test
    public void whenCreatesTuple_thenCorrect1() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String element1 = java8._1;
        int element2 = java8._2();

        assertEquals("Java", element1);
        assertEquals(8, element2);
    }

    @Test
    public void whenCreatesTuple_thenCorrect2() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(1.8, element3, 0.1);
    }
}
