package pro.sky.java.homework;

import pro.sky.java.homework.exceptions.ElementNotFoundException;
import pro.sky.java.homework.exceptions.IndexNotFoundException;
import pro.sky.java.homework.exceptions.ParamNullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.homework.ConstantsStringForTest.*;
import static pro.sky.java.homework.ConstantsIntegerForTest.*;
import static pro.sky.java.homework.ConstantsSort.*;

class SimpleListImpTest {
    public static Stream<Arguments> getParamsForAddIndexTest() {
        return Stream.of(
                Arguments.of(INDEX_TWO, BETWEEN, ARRAY_STRING_WITH_BETWEEN_ELEMENT, ARRAY_STRING),
                Arguments.of(INDEX_FIRST, FIRST, ARRAY_STRING_WITH_FIRST_ELEMENT, ARRAY_STRING),
                Arguments.of(INDEX_LAST, LAST, ARRAY_STRING_WITH_LAST_ELEMENT, ARRAY_STRING),
                Arguments.of(INDEX_TWO, BETWEEN, ARRAY_STRING_ALL_BETWEEN, ARRAY_STRING_ALL)
        );
    }
    @ParameterizedTest
    @MethodSource("getParamsForAddIndexTest")
    void whenAddIndexThenEqualsArrays(int index, String item, String[] expect, String[] arr) {
        SimpleList<String> listImp = new SimpleListImp<>(arr);
        listImp.add(index, item);
        assertArrayEquals(listImp.toArray(), expect);
    }

    @Test
    public void whenFindIndexThenIndex() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_TWO, listImp.indexOf(BETWEEN));
    }

    @Test
    public void whenFindIndexThenNotIndex() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_NOT, listImp.indexOf(FIVE));
    }

    @Test
    public void whenFindLastIndexThenIndex() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_LAST, listImp.lastIndexOf(BETWEEN));
    }

    @Test
    public void whenAddElementThenAddSize() {
        SimpleList<String> listImp = new SimpleListImp<>();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertEquals(SIZE_FiVE, listImp.size());
    }

    @Test
    public void whenAddElementThenEqualArray() {
        SimpleList<String> listImp = new SimpleListImp<>();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertTrue(listImp.equals(new SimpleListImp<>(ARRAY_STRING)));
    }

    @Test
    public void whenGetForIndexThenElement() {
        SimpleList<String> listImp = new SimpleListImp<>();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertEquals(THREE, listImp.get(INDEX_TWO));
    }

    @Test
    public void whenRemoveForIndexThenElement() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING);
        assertEquals(THREE, listImp.remove(INDEX_TWO));
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE, listImp.toArray());
    }

    @Test
    public void whenRemoveForNameThenElement() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING);
        assertEquals(THREE, listImp.remove(THREE));
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE, listImp.toArray());
    }

    @Test
    public void whenAddNullThenException() {
        SimpleList<String> listImp = new SimpleListImp<>();
        assertThrows(ParamNullException.class, () -> listImp.add(null));
    }

    @Test
    public void whenInsertElementThenException() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING);
        assertThrows(IndexNotFoundException.class, () -> listImp.add(INDEX_NOT, BETWEEN));
    }

    @Test
    public void whenRemoveLastIndex() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING);
        listImp.remove(INDEX_LAST);
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE_LAST_INDEX, listImp.toArray());
    }

    @Test
    public void whenRemoveItemThenException() {
        SimpleList<String> listImp = new SimpleListImp<>(ARRAY_STRING);
        assertThrows(ElementNotFoundException.class, () -> listImp.remove(ELEMENT1));
    }

    @Test
    public void whenAddElementThenGetLastElement() {
        SimpleList<Integer> simpleList = new SimpleListImp<>(NUMS);
        simpleList.add(ELEVEN_N);
        assertEquals(ELEVEN_N, simpleList.get(simpleList.size() - 1));
    }

    @Test
    public void whenEqualsNumsThen() {
        SimpleList<Integer> simpleList = new SimpleListImp<>(NUMS);
        assertArrayEquals(NUMS, simpleList.toArray());
    }

    @Test
    public void whenSortBubbleThenEqualSortArray() {
        SimpleListImp<Integer> simpleList = new SimpleListImp<>(TEST_ARRAY, BUBBLE_SORT);
        simpleList.sort();
        assertArrayEquals(SORT_ARRAY, simpleList.toArray());
    }

    @Test
    public void whenSortSelectThenEqualSortArray() {
        SimpleListImp<Integer> simpleList = new SimpleListImp<>(TEST_ARRAY, SELECT_SORT);
        simpleList.sort();
        assertArrayEquals(SORT_ARRAY, simpleList.toArray());
    }

    @Test
    public void whenSortInsertThenEqualSortArray() {
        SimpleListImp<Integer> simpleList = new SimpleListImp<>(TEST_ARRAY, INSERT_SORT);
        simpleList.sort();
        assertArrayEquals(SORT_ARRAY, simpleList.toArray());
    }

    @Test
    public void whenContainElement() {
        SimpleListImp<Integer> simpleList = new SimpleListImp<>(ARRAY, INSERT_SORT, BINARY_COMPARATOR);
        assertTrue(simpleList.contains(34));
    }
}