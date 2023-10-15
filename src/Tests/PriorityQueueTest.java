package Tests;

import util.PriorityQueue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PriorityQueueTest {
    @Test
    void testIntInsert() {
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
    void testEmpty() {
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
    void testExtracting() {
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

    @Test
    void testRandomIntAdd() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max=0;
        int temp=0;
        for (int i = -50; i <= 50; i++) {
            temp=(int) (Math.random()*(73));
            if(temp>max) {
                max=temp;
            }
            queue.insert(temp);
        }
        assertFalse(queue.isEmpty());
        queue.heapSort();
        assertEquals(queue.back(), Integer.valueOf(max));
    }



}
