# IA-15Game

To run, execute:

  javac FifteenGame.java

Then execute:
    ```
    java FifteenGame "Strategy" < "TestBoard.txt"
    ``` 
Where "Strategy" corresponds to the desired strategy to use from the following options (presented without parentheses):

  - **BFS** ;
  - **DFS** ;
  - **IDFS** ;
  - **Greedy-Manhattan** ;
  - **Greedy-misplaced** ;
  - **A*-Manhattan** ;  
  - **A*-misplaced** ;
  
 To introduce different board options, simply modify the TestBoard.txt file or use your own file, ensuring that the initial board is entered first and the final board is entered second. It's also possible to introduce options later in the terminal if you only run the command as (java FifteenGame "Strategy").
 
 A concrete example of execution would be:
    ```
    javac FifteenGame.java 
    java FifteenGame A*-Manhattan < TestBoard.txt
    ```