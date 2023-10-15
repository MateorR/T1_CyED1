package Tests;

import util.PriorityQueue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PriorityQueueTest {
    @Test
    public void testStandardCase() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.insert(2);
        queue.insert(1);
        queue.insert(3);
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.getHeapSize());
        queue.heapSort();
        assertEquals(queue.front(), Integer.valueOf(1));
        assertEquals(queue.back(), Integer.valueOf(3));
    }

    @Test
    public void testLimitCase() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertTrue(queue.isEmpty());
        assertNull(queue.front());
        assertNull(queue.back());
        queue.insert(1);
        assertEquals(queue.front(), Integer.valueOf(1));
        assertEquals(queue.back(), Integer.valueOf(1));
        queue.extractMax();
        assertTrue(queue.isEmpty());
        assertNull(queue.front());
        assertNull(queue.back());
    }

    @Test
    public void testRandomCase() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.insert("R");
        assertEquals(queue.front(), ("R"));
        queue.insert("P");
        queue.insert("S");
        queue.insert("A");
        queue.insert("U");
        queue.extractMax();
        queue.heapSort();
        assertEquals(queue.back(), ("S"));
    }

}
