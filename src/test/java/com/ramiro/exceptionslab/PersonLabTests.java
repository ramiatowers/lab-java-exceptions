package com.ramiro.exceptionslab;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PersonLabTests {

    @Test
    void setAge_throws_whenNegative() {
        Person p = new Person(1, "John Doe", 20, "Engineer");
        assertThrows(IllegalArgumentException.class, () -> p.setAge(-1));
    }

    @Test
    void findByName_returnsCorrectPerson_whenProperlyFormatted() {
        PersonsList list = new PersonsList();
        Person a = new Person(1, "Alice Smith", 30, "Designer");
        Person b = new Person(2, "Bob Jones", 25, "Developer");
        list.add(a); list.add(b);

        Person found = list.findByName("Bob Jones");
        assertTrue(found.equals(b));             // equals(Person) ignoring id
        assertEquals(b, found);                  // equals(Object) also works
    }

    @Test
    void findByName_throws_whenBadFormat() {
        PersonsList list = new PersonsList();
        list.add(new Person(1, "Alice Smith", 30, "Designer"));

        assertThrows(IllegalArgumentException.class, () -> list.findByName("Alice"));
        assertThrows(IllegalArgumentException.class, () -> list.findByName("  Alice   "));
        assertThrows(IllegalArgumentException.class, () -> list.findByName("Alice B C"));
        assertThrows(IllegalArgumentException.class, () -> list.findByName(null));
    }

    @Test
    void findByName_throws_whenNotFound() {
        PersonsList list = new PersonsList();
        list.add(new Person(1, "Alice Smith", 30, "Designer"));

        assertThrows(NoSuchElementException.class, () -> list.findByName("Bob Jones"));
    }

    @Test
    void clone_createsNewId_andSameProperties() {
        PersonsList list = new PersonsList();
        Person a = new Person(7, "Cara White", 40, "Teacher");
        list.add(a);

        Person c = list.clone(a);
        assertNotEquals(a.getId(), c.getId(), "clone must have a new id");
        assertTrue(c.equals(a), "clone must match all properties except id");
    }
}
