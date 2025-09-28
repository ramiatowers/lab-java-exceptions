package com.ramiro.exceptionslab;

import java.util.Objects;

public class Person {
    private final int id;
    private String name;        // "firstName lastName"
    private int age;            // must be >= 0
    private String occupation;

    public Person(int id, String name, int age, String occupation) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name");
        setAge(age); // validates >= 0
        this.occupation = Objects.requireNonNull(occupation, "occupation");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getOccupation() { return occupation; }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age must be >= 0");
        this.age = age;
    }

    /** Lab requirement: equals(Person) that ignores id */
    public boolean equals(Person other) {
        if (other == null) return false;
        return Objects.equals(this.name, other.name)
                && this.age == other.age
                && Objects.equals(this.occupation, other.occupation);
    }

    /** Proper Object.equals override delegating to equals(Person) */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;
        return equals(p);
    }

    @Override
    public int hashCode() {
        // ignore id to be consistent with equals
        return Objects.hash(name, age, occupation);
    }

    @Override
    public String toString() {
        return "Person{id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
