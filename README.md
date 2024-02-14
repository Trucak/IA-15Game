## IA-15Game

**To run, follow these steps:**

1. Compile the Java code:

    ```bash
    javac FifteenGame.java
    ```

2. Then, execute the program specifying the desired strategy. Use a text file to provide the input board.

    ```bash
    java FifteenGame "Strategy" < "TestBoard.txt"
    ```

    Replace `"Strategy"` with the desired strategy from the following options:

    - BFS
    - DFS
    - IDFS
    - Greedy-Manhattan
    - Greedy-misplaced
    - A*-Manhattan
    - A*-misplaced

    You can modify the `TestBoard.txt` file with different input boards. Ensure that the initial board is entered first and the final board is entered second. It's also possible to input the options directly in the terminal if you only run the command like `java FifteenGame "Strategy"`.

**A concrete example of execution would be:**

    
    javac FifteenGame.java
    java FifteenGame A*-Manhattan < TestBoard.txt
    
