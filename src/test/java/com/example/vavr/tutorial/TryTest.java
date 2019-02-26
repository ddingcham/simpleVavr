package com.example.vavr.tutorial;

import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
    !!! In Vavr
    Try is a container for a computation which may result in an exception.
 */
public class TryTest {

    /*
        Try wraps a computation
        so that we don't have to explicitly take care of exceptions with try-catch blocks.
     */
    @Test(expected =  ArithmeticException.class)
    public void givenBadCode_whenThrowsException_thenCorrect() {
        int i = 1 / 0;
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }

    /*
        We have chosen to simply check for success or failure.
        We can also choose to return a default value.
     */
    @Test
    public void givenBadCode_whenTryHandles_thenCorrect2() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        int errorSentinel = result.getOrElse(-1);

        assertEquals(-1, errorSentinel);
    }

    /*
        We can also choose to throw an exception our choice.
        test case => [ExpectedException, UnexpectedException]
     */
    @Test(expected = ChosenException.class)
    public void givenBadCode_whenTryHandles_thenCorrect3() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        result.getOrElseThrow(ChosenException::new);
    }

    static class ChosenException extends RuntimeException {}

}
