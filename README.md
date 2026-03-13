# Fast File Search – Multithreaded File Search Utility

## Overview
Fast File Search is a multithreaded Java application designed to efficiently search for files within large directory structures. The program uses concurrency to improve performance compared to single-threaded file search implementations by distributing the workload across multiple threads.

This project demonstrates practical use of:
- Multithreading
- File system traversal
- Concurrency concepts
- Performance optimization

---

## Features
- Multithreaded directory search
- Recursive file traversal
- Search by filename
- Efficient handling of large directories
- Thread management using Java concurrency tools
- Clean console output

---

## Technologies Used
- **Java**
- **Java Threads / ExecutorService**
- **File I/O (java.io / java.nio)**
- **Object-Oriented Programming**

---

## How It Works
The program works by:

1. Taking a root directory as input
2. Creating search tasks for directories
3. Assigning tasks to multiple threads
4. Collecting matching files
5. Displaying results in the console

---

## Project Structure
FastFileSearch_Multithreading_Project3/
│── src/
│ ├── FastFileSearch.java
│ ├── FileSearchTask.java
│ ├── Main.java
│
│── README.md

---

## How to Run

### Clone repository

```bash
git clone https://github.com/liarturo01/FastFileSearch_Multithreading_Project3.git
Navigate to project
cd FastFileSearch_Multithreading_Project3
Compile
javac Main.java
Run
java Main
Example Usage
Example input:
Directory: /Users/arturo/Documents
File name: report.txt
Example output:
File found:
/Users/arturo/Documents/projects/report.txt
Concepts Demonstrated
This project demonstrates understanding of:
Thread creation and lifecycle
Task parallelism
Recursive algorithms
Synchronization basics
Performance-oriented design
Future Improvements
Possible enhancements:
Add GUI interface
Add regex search support
Add file content search
Add performance benchmarks
Add logging system
Add search filters (size, date, type)
Author
Arturo Li
Computer Science student focused on software engineering and concurrent programming.
