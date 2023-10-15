package Tests;

import util.Queue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class QueueTest {

    @Test
    void testStringInsert() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("Luis");
        queue.enqueue("Manrique");
        queue.enqueue("Felipe");
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertEquals(queue.peek(), ("Luis"));
    }

    @Test
     void testEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
        queue.enqueue(13);
        assertEquals(queue.peek(), Integer.valueOf(13));
        queue.dequeue();
        queue.enqueue(31);
        queue.enqueue(17);
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testFunctionalities() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(2);
        assertEquals(1, queue.size());
        queue.enqueue(4);
        queue.enqueue(1);
        assertEquals(3, queue.size());
        assertEquals(queue.peek(), Integer.valueOf(2));
    }

}
