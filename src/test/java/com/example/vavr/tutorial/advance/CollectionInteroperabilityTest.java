package com.example.vavr.tutorial.advance;

import io.vavr.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/*
    The collection API (io.vavr.collection.*)
    is fully interoperable with Java's collection framework.

    Vavr Collection 이 좋긴 함 ...
    그치만 두 가지 타입 선언이 모두 필요할 경우
    둘 중 하나는 FQN으로 표현 됨 -> 극혐
    -> 내 코드가 좋은 API 구조면 그럴 일이 많진 않겠지만 ... 신경쓸 게 늘어나는 느낌임
 */
public class CollectionInteroperabilityTest {

//  <Java To Vavr Conversion>

    /*
        Each collection implementation in Vavr has a static factory method ofAll()
        that takes a java.util.Iterable.

        isImmutable? => CollectionsTest.whenImmutableCollectionThrgit ows_thenCorrect()
     */
    @Test
    public void convertJavaListToImmutableList() {
        java.util.List<Integer> javaList = java.util.Arrays.asList(1, 2, 3, 4);
        io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.ofAll(javaList);

        java.util.stream.Stream<Integer> javaStream = javaList.stream();
        // ordered
        io.vavr.collection.Set<Integer> vavrSet = io.vavr.collection.LinkedHashSet.ofAll(javaStream);

        // see also asJava(), toJavaArray()
        assertArrayEquals(javaList.toArray(), vavrList.toJavaArray());
        assertArrayEquals(javaList.toArray(), vavrSet.toJavaArray());
    }

    /*
        collector()
        that can be used in conjunction with
        Stream.collect() to obtain a Vavr collection
     */
    @Test
    public void StreamCollect_withVavrCollector() {
        // Java Stream : range(int startInclusive, !!! int endExclusive)
        io.vavr.collection.List<Integer> vavrList = java.util.stream.IntStream.range(1, 10)
                .boxed()
                .filter(i -> i % 2 == 0)
                .collect(io.vavr.collection.List.collector());

        assertEquals(4, vavrList.size());
        assertEquals(2, vavrList.head().intValue());
    }

//  <Vavr To Java Conversation>

    /*
        Value interface has many methods to convert a Vavr type to a Java type.
        // toJavaXXX() || asJava()
     */
    @Test
    public void toJavaXXX() {
        Integer[] array = io.vavr.collection.List.of(1, 2, 3)
                .toJavaArray(Integer.class);
        assertEquals(3, array.length);

        java.util.Map<String, Integer> map = io.vavr.collection.List.of("1", "2", "3")
                .toJavaMap(item -> Tuple.of(item, Integer.valueOf(item)));
        assertEquals(2, map.get("2").intValue());
    }

    @Test
    public void fromVavrCollections_collectElements_withJava8Collectors() {
        Integer[] source = new Integer[]{1, 2, 3};
        java.util.Set<Integer> javaSet = io.vavr.collection.List.of(1, 2, 3)
                .collect(java.util.stream.Collectors.toSet());

        assertEquals(source.length, javaSet.size());
        assertArrayEquals(source, javaSet.toArray());
    }

    //  <Java Collection Views>
    @Test(expected = UnsupportedOperationException.class)
    public void givenVavrList_whenViewConverted_thenException() {
        java.util.List<Integer> javaList = io.vavr.collection.List.of(1, 2, 3)
                .asJava();

        assertEquals(3, javaList.get(2).intValue());
        // is immutable
        javaList.add(4);
    }

    @Test
    public void givenVavrList_whenViewConverted_isMutable() {
        java.util.List<Integer> javaList = io.vavr.collection.List.of(1,2,3)
                .asJavaMutable();
        javaList.add(4);

        assertEquals(4, javaList.size());
    }
}
