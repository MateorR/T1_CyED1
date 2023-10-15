package Tests;

import util.Stack;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
public class StackTest {
    @Test
    public void testPushPop() {
        Stack<String> stack = new Stack<>();
        stack.push("Felony");
        stack.push("Misdemeanor");
        stack.push("Infraction");
        assertFalse(stack.isEmpty());
        assertEquals(stack.pop(), ("Infraction"));
        assertEquals(stack.pop(), ("Misdemeanor"));
        assertEquals(stack.pop(), ("Felony"));
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testEmpty() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertNull(stack.top());
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(stack.top(), Integer.valueOf(1));
        stack.pop();
        assertTrue(stack.isEmpty());
        assertNull(stack.top());
    }

    @Test
    public void testTop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        assertEquals(stack.top(), Integer.valueOf(4));
        stack.pop();
        assertEquals(stack.top(), Integer.valueOf(2));
        stack.push(3);
        stack.push(5);
        assertEquals(stack.top(), Integer.valueOf(5));
        stack.pop();
        assertEquals(stack.top(), Integer.valueOf(3));
    }

    @Test
    public void testClean() {
        Stack<Integer> stack = new Stack<>();
        for (int i = -50; i <= 50; i++) {
            stack.push(i);
        }
        assertFalse(stack.isEmpty());
        assertEquals(stack.top(), Integer.valueOf(50));
        for (int i = 50; i >= 0; i--) {
            assertEquals(stack.pop(), Integer.valueOf(i));
        }
        assertEquals(stack.top(), Integer.valueOf(-1));
    }

}
