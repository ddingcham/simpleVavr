package com.example.vavr.tutorial;

import io.vavr.Lazy;
import org.junit.Test;

import static org.junit.Assert.*;

/*
    Lazy is a container
        which represents a value computed lazily
        ex] computation is deferred until the result is required.

    Furthermore : the evaluated value is cached or memorized (return again)
 */
public class LazyTest {

    // Lazy again lazily returns the initially computed value as the final assertion confirms.
    @Test
    public void givenFunction_whenEvaluatesWithLazy_thenCorrect() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());

        double val1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        double val2 = lazy.get();
        assertEquals(val1, val2, 0.1);
    }
}
