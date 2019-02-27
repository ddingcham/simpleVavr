package com.example.vavr.tutorial;

import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static org.junit.Assert.assertEquals;

/*
    Pattern matching is a native concept in almost all functional programming languages.

    We can see the code spanning multiple lines while just checking N cases.
    Each check is taking up N lines of code.

    !!! If (hundred cases) => would be about 300 lines.
        => generally, 3 lines per check.
 */
public class MatchTest {

    /*
        A lot of confusion and potential for bugs.
        ex] Forgetting a break clause is not an issue at compile time
            but can result in hard-to-detect bugs later on.

        !!! Atomic Patterns like $() replace the condition which then evaluates an expression or value.
     */
    @Test
    public void whenMatchworks_thenCorrect() {
        assertEquals("two", simpleMatch(2));
        assertEquals("default", simpleMatch(-1));
    }

    private static String simpleMatch(int input) {
        return Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "default"));
    }

    /*
        We can replace the atomic expressions with a predicate.
        io.vavr.Predicates

        (예외 날리는 경우에 딱 저 포맷만 지원함)
        => 이 용도는 설명에 비해서 불편하고, 더러움
        => 좀 더 찾아봐야할 듯
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenMatchworks_withPredicate_thenThrowing() {
        String arg = "";
        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );
    }

    private void displayVersion() {
    }

    private void displayHelp() {
    }

}
