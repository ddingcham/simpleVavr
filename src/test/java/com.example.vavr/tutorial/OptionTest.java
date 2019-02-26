package com.example.vavr.tutorial;

import io.vavr.control.Option;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
    Main Goal : eliminate null checks
    vs. Optional : Vavr's option implements Serializable, Iterable, and has a richer API.
 */
public class OptionTest {

    /*
             the checks make the code verbose and not so readable
             end up being nested multiple times
     */
    @Test
    public void givenValue_whenNullCheckNeeded_thenCorrect() {
        Object possibleNullObj = null;
        if (possibleNullObj == null) {
            possibleNullObj = "someDefaultValue";
        }
        assertNotNull(possibleNullObj);
    }

    /*
             the checks make the code verbose and not so readable
             end up being nested multiple times
     */
    @Test(expected = NullPointerException.class)
    public void givenValue_whenNullCheckNeeded_thenCorrect2() {
        Object possibleNullObj = null;
        assertEquals("somevalue", possibleNullObj.toString());
    }

    /*
        instance of None & instance of Some
     */

    @Test
    public void givenValue_whenCreatesOption_thenCorrect() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());
    }

    /*
        it’s advisable to wrap them inside an Option instance as shown above.
     */
    @Test
    public void givenNull_whenCreatesOption_thenCorrect() {
        String name = null;
        Option<String> nameOption = Option.of(name);

        assertEquals("baeldung", nameOption.getOrElse("baeldung"));
    }

    /*
        it’s advisable to wrap them inside an Option instance as shown above.
    */
    @Test
    public void givenNonNull_whenCreatesOption_thenCorrect() {
        String name = "baeldung";
        Option<String> nameOption = Option.of(name);

        assertEquals("baeldung", nameOption.getOrElse("notbaeldung"));
    }
}
