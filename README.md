# Java Challenges

This repository contains Java practice tasks grouped by topic (operators, methods, switch, loops, exceptions).

## What Is In This Project

- Small standalone Java exercises.
- One entry file `App.java` for a basic test run.
- Topic folders with task files that can be run independently.

## Requirements

- JDK 17+ (JDK 25 LTS is also supported)
- VS Code with Java Extension Pack (recommended)

## Project Structure

```text
Java_Challenges/
|- src/
|  |- App.java
|  |- challenges_1_operators/
|  |- challenges_2_methods/
|  |- challenges_3_switch/
|  |- challenges_5_exceptions/
|  |- challenges_6_Classes/
|  |- challenges_...
|- bin/              (compiled classes)
|- lib/              (external libraries, if any)
|- .vscode/
|- .gitignore
`- README.md
```

## How To Run (VS Code)

1. Clone or download this repository.
2. Open the folder in VS Code.
3. Install Java extensions if prompted.
4. Open any class with a `main` method.
5. Click `Run` above the `main` method.

## How To Run (Terminal)

From the project root:

Run the sample app:

```bash
javac -d bin src/App.java
java -cp bin App
```

Run a specific challenge file (example):

```bash
javac -d bin src/challenges_3_switch/PhoneticAlphabet.java
java -cp bin challenge.task_3_switch.PhoneticAlphabet
```

Replace the file and class name with the task you want to run.

## Notes

- Most files are independent exercises for learning and practice.
- `bin/` stores compiled `.class` files.
