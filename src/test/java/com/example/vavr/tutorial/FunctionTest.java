package com.example.vavr.tutorial;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/*
    Java 8, functional interfaces are inbuilt and easier to use,
    specially when combined with lambdas.
    However, Java 8 provides only two basic functions.
 */
public class FunctionTest {

    // * takes only a single param and produces a result
    @Test
    public void givenJava8Function_whenWOrks_thenCorrect() {
        Function<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    // * only takes two parameters and produces a result
    @Test
    public void givenJava8BiFunction_whenWorks_thenCorrect() {
        BiFunction<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    /*
        Just like tuples,
        Function0, Function1, Function2 ... Function8
     */
    @Test
    public void givenVavrFunction_whenWorks_thenCorrect() {
        Function1<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    /*
        Just like tuples,
        Function0, Function1, Function2 ... Function8
     */
    @Test
    public void givenVavrBiFunction_whenWOrks_thenCorrect() {
        Function2<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    /*
        no param need an output
        * Java8 : we would need to use a Consumer
        * Vavr  : Function0
     */
    @Test
    public void whenCreatesFunction_thenCorrect0() {
        Function0<String> getClazzName = () -> this.getClass().getName();
        String clazzName = getClazzName.apply();

        assertEquals("com.example.vavr.tutorial.FunctionTest", clazzName);
    }

    /*
        with more params
     */
    @Test
    public void whenCreatesFunction_thenCorrect5() {
        Function5<String, String, String, String, String, String> concat =
                (a, b, c, d, e) -> a + b + c + d + e;
        String finalString = concat.apply("a", "b", "c", "d", "e!");
        assertEquals("abcde!", finalString);
    }

    /*
        static factory method Style
        FunctionN.of()
     */
    public int one() {
        return 1;
    }
    @Test
    public void whenCreatesFunctionFromMethodRef_thenCorrect() {
        Function0<Integer> one = Function0.of(this::one);
        int result = one.apply();

        assertEquals(1, result);
    }
}
