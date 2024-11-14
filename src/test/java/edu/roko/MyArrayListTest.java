package edu.roko;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAddAndGet() {
        list.add(10);
        list.add(20);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testAddAtIndex() {
        list.add(0, 10);
        list.add(1, 20);
        list.add(1, 15);
        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(20, list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testQuicksort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.quicksort(Comparator.naturalOrder());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }
}
