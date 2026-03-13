Fast File Search – Multithreaded File Search Utility
Overview
Fast File Search is a multithreaded Java application designed to efficiently search for files within large directory structures. The program leverages concurrency to improve performance compared to single-threaded file search implementations by distributing the search workload across multiple threads.
This project demonstrates practical use of:
Multithreading
File system traversal
Concurrency concepts
Performance optimization techniques
Features
Multithreaded directory search for improved performance
Recursive file traversal
Search by filename
Efficient handling of large directory structures
Thread management using Java concurrency tools
Clean console output of search results
Technologies Used
Java
Multithreading (Thread / ExecutorService)
File I/O (java.io / java.nio)
Object-Oriented Programming
How It Works
The program works by:
Taking a root directory as input
Dividing search tasks among multiple threads
Each thread searches a portion of the directory structure
Matching files are collected and displayed
Using multiple threads can significantly reduce search time because modern CPUs can execute tasks concurrently.
Project Structure
FastFileSearch_Multithreading_Project3/
│── src/
│   ├── FastFileSearch.java
│   ├── FileSearchTask.java
│   ├── Main.java
│
│── README.md
How to Run
Clone repository
git clone https://github.com/liarturo01/FastFileSearch_Multithreading_Project3.git
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
Thread creation and management
Task parallelism
Recursive algorithms
Synchronization concepts
Software design for performance
Future Improvements
Possible enhancements:
Add GUI interface
Add regex search support
Add file content search
Add performance benchmarking
Add logging system
Support cancellation of search tasks
Author
Arturo Li
Computer Science student focused on software engineering and concurrent programming.
License
This project is for educational purposes.
