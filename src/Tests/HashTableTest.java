package Tests;


import util.HashTable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
public class HashTableTest {
    @Test
    public void addTest() {
        HashTable<String, Integer> table = new HashTable<>();
        assertTrue(table.isEmpty());
        table.add("key", 1);
        assertEquals(1, table.length());
        table.add("key", 2);
        assertEquals(2, table.length());
        table.add("key2", 2);
        assertEquals(3, table.length());
    }

    @Test
    public void removeTest() {
        HashTable<String, Integer> table = new HashTable<>();
        table.add("key", 1);
        assertEquals(1, table.length());
        table.remove("key");
        assertEquals(0, table.length());
        table.remove("key");
        assertEquals(0, table.length());
    }

    @Test
    public void getTest() {
        HashTable<String, Integer> table = new HashTable<>();
        table.add("key", 1);
        assertEquals(1, table.get("key"));
        assertNull(table.get("key2"));
    }

    @Test
    public void isEmptyTest() {
        HashTable<String, Integer> table = new HashTable<>();
        assertTrue(table.isEmpty());
        table.add("key", 1);
        assertFalse(table.isEmpty());
    }

    @Test
    public void sizeTest() {
        HashTable<String, Integer> table = new HashTable<>();
        assertEquals(0, table.length());
        table.add("key", 1);
        assertEquals(1, table.length());
        table.add("key", 2);
        assertEquals(2, table.length());
        table.add("key2", 2);
        assertEquals(3, table.length());
        table.remove("key");
        assertEquals(2, table.length());
        table.remove("key");
        assertEquals(1, table.length());
        table.remove("key2");
        assertEquals(0, table.length());
    }
}
