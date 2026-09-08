# study

Personal Java learning project (single module, no tests).

## Commands

```bash
mvn compile                          # build
mvn exec:java -Dexec.mainClass="org.example.Main"  # run main
```

No `mvnw` — requires system Maven.

## Structure

All sources under `org.example` — no packages beyond that. Entry point: `Main.java`. No `src/test/` exists.

## Notable

- TestNG is declared as a `compile`-scope dependency but no tests exist — treat it as available if tests are added.
- Java 23 source/target in pom.xml.
- No formatter or linter config — follow existing conventions (camelCase, class-level javadoc in Chinese comments for learning notes).
