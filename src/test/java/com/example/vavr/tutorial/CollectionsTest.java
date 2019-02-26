package com.example.vavr.tutorial;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/*
    Java collections are mutable,
    => making them a great source of program failure
    => // especially in the presence of concurrency.

    With immutability, we get thread-safety for free
    : 도형님 글 [thread safe 보장 작업]
    ( http://egloos.zum.com/aploit/v/5724750 )
 */
public class CollectionsTest {

    /*
        to add immutability to collections in Java
        still create more problems, namely, exceptions
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenImmutableCollectionThrows_thenCorrect() {
        java.util.List<String> sourceList = Arrays.asList("abc");
        java.util.List<String> unmodifiableList = Collections.unmodifiableList(sourceList);
        unmodifiableList.add("add please");
    }

    /*
        All the above(Java collections) problems are non-existent in Vavr collections.
     */
    @Test
    public void whenCreatesVavrList_thenCorrect() {
        io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.of(1, 2, 3);

        assertEquals(3, vavrList.length());
        assertEquals(Integer.valueOf(1), vavrList.get(0));
        assertEquals(Integer.valueOf(2), vavrList.get(1));
        assertEquals(Integer.valueOf(3), vavrList.get(2));
    }

    /*
        APIs are also available to perform computations on the list in place
     */
    @Test
    public void whenSumsVavrList_thenCorrect() {
        int sum = io.vavr.collection.List.of(1, 2, 3).sum().intValue();

        assertEquals(6, sum);
    }

    /*
        <Conclusion> // compared to Java's collection operations

        The takeaway is

        * immutability
        * removal of void return types
        * side-effect producing APIs

        a richer set of

        * functions to operate on the underlying elements
        * very short
        * robust and compact code
     */
}
