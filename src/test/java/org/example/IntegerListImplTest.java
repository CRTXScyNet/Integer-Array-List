package org.example;

import org.example.integerListException.IllegalIndexException;
import org.example.integerListException.IntegerNotFoundException;
import org.example.integerListException.ItemIsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private IntegerListImpl list;

    @BeforeEach
    public void init() {
        list = new IntegerListImpl();
    }

    @Test
    void add() {
        IntegerListImpl list1 = new IntegerListImpl();
        list1.add(1);
        //given
        //do
        //then
        assertEquals(1, list.add(1));
        assertTrue(list.equals(list1));
    }

    @Test
    void addWithIndex() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();
        list1.add(1);
        list1.add(2);

        list.add(2);
        //do
        //then
        assertEquals(1, list.add(0, 1));
        assertTrue(list.equals(list1));
    }

    @ParameterizedTest
    @MethodSource(value = "illegalIndexArguments")
    void addWithIllegalIndexShouldThrowException(int i) {
        //given
        //do
        //then
        assertThrows(IllegalIndexException.class, () -> list.add(i, 1));
    }

    @Test
    void addNullShouldThrowException() {
        //given
        //do
        //then
        assertThrows(ItemIsNullException.class, () -> list.add(null));

    }

    @Test
    void addNullWithIndex() {
        //given
        list.add(1);
        //do
        //then
        assertThrows(ItemIsNullException.class, () -> list.add(0, null));
    }

    @Test
    void set() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();
        list1.add(1);
        //do
        list.add(2);
        //then
        assertEquals(2, list.set(0, 1));
        assertTrue(list.equals(list1));
    }

    @ParameterizedTest
    @MethodSource(value = "illegalIndexArguments")
    void setWithIllegalIndexShouldThrowException(int i) {
        //given
        //do
        //then
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(i, 1));
    }

    @Test
    void remove() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();

        //do
        list.add(1);
        //then
        assertEquals(1, list.remove((Integer) 1));
        assertTrue(list.equals(list1));
    }

    @Test
    void removeByIndex() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();

        //do
        list.add(1);
        //then
        assertEquals(1, list.remove(0));
        assertTrue(list.equals(list1));
    }

    @ParameterizedTest
    @MethodSource(value = "illegalIndexArguments")
    void removeByIllegalIndexShouldThrowException(int i) {
        //given
        //do
        //then
        assertThrows(IllegalIndexException.class, () -> list.remove(i));

    }

    static Stream<Arguments> illegalIndexArguments() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(0),
                Arguments.of(-1)
        );
    }

    @Test
    void removeNotExistedShouldThrowException() {
        //given
        //do
        list.add(1);
        //then
        assertThrows(IntegerNotFoundException.class, () -> list.remove((Integer) 4));
    }


    @Test
    void contains() {
        //given
        list.add(1);
        //do
        //then
        assertTrue(list.contains(1));
    }

    @Test
    void notContains() {
        //given
        list.add(1);
        //do
        //then
        assertFalse(list.contains(11));
    }

    @Test
    void containsWithNullParameter() {
        //given

        //do
        //then
        assertThrows(ItemIsNullException.class, () -> list.contains(null));
    }

    @Test
    void indexOf() {
        //given
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        //then
        assertEquals(1, list.indexOf(2));
    }

    @Test
    void lastIndexOf() {
        //given
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        //then
        assertEquals(2, list.lastIndexOf(2));
    }

    @Test
    void get() {
        //given
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        //then
        assertEquals(2, list.get(2));
    }

    @ParameterizedTest
    @MethodSource(value = "illegalIndexArguments")
    void getByIllegalIndexShouldThrowException(int i) {
        //given
        //do
        //then
        assertThrows(IllegalIndexException.class, () -> list.get(i));
    }

    @Test
    void testEquals() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();
        list1.add(1);
        list.add(1);
        //do
        //then
        assertTrue(list.equals(list1));
    }

    @Test
    void testNotEquals() {
        //given
        IntegerListImpl list1 = new IntegerListImpl();
        list1.add(1);

        //do
        //then
        assertFalse(list.equals(list1));
    }

    @Test
    void testEqualsWithNullShouldThrowException() {
        //given
        IntegerListImpl list1 = null;


        //do
        //then
        assertThrows(ItemIsNullException.class, () -> list.equals(list1));
    }

    @Test
    void size() {
        //given
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        //then
        assertEquals(3, list.size());
    }

    @Test
    void isEmpty() {
        //given
        //do
        //then
        assertTrue(list.isEmpty());
    }

    @Test
    void isNotEmpty() {
        //given
        list.add(2);
        //do
        //then
        assertFalse(list.isEmpty());
    }

    @Test
    void clear() {
        //given
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        list.clear();
        //then
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void toArray() {
        //given
        Integer[] strings = {1, 2, 2};
        list.add(1);
        list.add(2);
        list.add(2);
        //do
        //then
        assertTrue(list.toArray() instanceof Integer[]);
    }

//    @Test
//    void sortSelection() {
//        //given
//        IntegerListImpl list1 = new IntegerListImpl();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);
//        list1.add(23);
//        list1.add(34);
//        list.add(23);
//        list.add(4);
//        list.add(1);
//        list.add(34);
//        list.add(2);
//        //do
//        list.sortSelection();
//        //then
//        assertTrue(list.equals(list1));
//    }
//
//    @Test
//    void sortBubble() {
//        //given
//        IntegerListImpl list1 = new IntegerListImpl();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);
//        list1.add(23);
//        list1.add(34);
//        list.add(23);
//        list.add(4);
//        list.add(1);
//        list.add(34);
//        list.add(2);
//        //do
//        list.sortBubble();
//        //then
//        assertTrue(list.equals(list1));
//    }
//
//    @Test
//    void sortInsertion() {
//        //given
//        IntegerListImpl list1 = new IntegerListImpl();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);
//        list1.add(23);
//        list1.add(34);
//        list.add(23);
//        list.add(4);
//        list.add(1);
//        list.add(34);
//        list.add(2);
//        //do
//        list.sortInsertion();
//        //then
//        assertTrue(list.equals(list1));
//    }
}