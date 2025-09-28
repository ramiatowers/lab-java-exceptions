package com.ramiro.exceptionslab;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PersonsList {
    private final List<Person> people = new ArrayList<>();

    public void add(Person p) {
        people.add(Objects.requireNonNull(p));
    }

    /** Exact match; throws if name is not "firstName lastName" */
    public Person findByName(String name) {
        String validated = validateFullName(name); // may throw IllegalArgumentException
        for (Person p : people) {
            if (p.getName().equals(validated)) return p;
        }
        throw new NoSuchElementException("No person found with name: " + validated);
    }

    /** Returns a new Person with same props, but a new id */
    public Person clone(Person original) {
        Objects.requireNonNull(original, "original");
        int newId = nextId();
        return new Person(newId, original.getName(), original.getAge(), original.getOccupation());
    }

    /** Writes Person.toString() to file; handles IO errors internally and returns success flag */
    public boolean writePersonToFile(Person p, String filePath) {
        Objects.requireNonNull(p, "person");
        Objects.requireNonNull(filePath, "filePath");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            bw.write(p.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Failed to write person to file: " + e.getMessage());
            return false;
        }
    }

    // --- helpers ---
    private String validateFullName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        String trimmed = name.trim();
        String[] parts = trimmed.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Name must be formatted as 'firstName lastName'");
        }
        return parts[0] + " " + parts[1];
    }

    private int nextId() {
        int max = 0;
        for (Person p : people) max = Math.max(max, p.getId());
        return max + 1;
    }

    public List<Person> getAll() { return Collections.unmodifiableList(people); }
}