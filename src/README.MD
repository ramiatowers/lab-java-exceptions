Exception-Ready People Registry

A concise Java 17 solution for the Ironhack Exceptions lab. It showcases clean exception handling, strict input validation, safe object cloning, and simple file I/O—fully covered by JUnit 5 tests. Minimal surface area, fast to read, easy to run, ready for review.

WHAT THIS PROJECT DELIVERS
• Person: id, name (“firstName lastName”), age, occupation. Validates non-negative age. Equality intentionally ignores id as required by the lab.
• PersonsList: in-memory registry with three core capabilities:
– findByName(String): exact match; validates the “firstName lastName” format; throws IllegalArgumentException on bad format and NoSuchElementException when not found.
– clone(Person): creates a new Person with the same properties and a new id.
– writePersonToFile(Person, path): writes Person.toString() using try-with-resources; handles I/O failures gracefully.
• Tests (JUnit 5): verify negative-age rejection, successful name lookup, invalid name format, “not found” behavior, and cloning with a new id.

PROJECT STRUCTURE (Maven standard)
src/main/java/com/ramiro/exceptionslab/Person.java
src/main/java/com/ramiro/exceptionslab/PersonsList.java
src/test/java/com/ramiro/exceptionslab/PersonLabTests.java
pom.xml (Java 17, JUnit 5, Surefire)

HOW TO RUN
• From project root: mvn test
• Run a specific class: mvn -Dtest=com.ramiro.exceptionslab.PersonLabTests test
• In IntelliJ IDEA: open the project, right-click PersonLabTests, Run.

WHY THIS MATTERS
• Clear, enforceable validation at construction and setters.
• Explicit, meaningful exceptions instead of silent failures.
• Deterministic cloning and file output that are easy to reason about and to test.
• Clean, reviewer-friendly code: small classes, zero unnecessary dependencies.

CONFIG NOTES
• Requires Java 17.
• JUnit 5 is test-scope only; production code has no runtime third-party dependencies.
• File writing uses the platform default charset; adjust if your environment requires a specific encoding.