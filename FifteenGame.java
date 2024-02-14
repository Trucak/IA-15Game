import java.util.Scanner; 

public class FifteenGame{
  public static void main(String args[]){

    // Lê o argumento da linha de comando que representa a estratégia de busca escolhida
    String strategy = args[0];

    Scanner in = new Scanner(System.in);

    // Cria o array que representa o estado inicial do jogo
    int array[] = new int[16];
    for(int i = 0; i < 16; i++){
      array[i] = in.nextInt();
    }

    // Cria o array que representa o estado final do jogo
    int finalArray[] = new int[16];
    for(int i = 0; i < 16; i++){
      finalArray[i] = in.nextInt();
    }

    // Fecha o scanner
    in.close();

    // Cria o objeto Board para o estado inicial e final do jogo
    Board initialBoard = new Board(array,null,null,0,"");
    Board finalBoard = new Board(finalArray,null,null,0,"");

    // Verifica se o tabuleiro é solucionável
    if(GenericUtils.boardSolvability(array)){
      switch(strategy) {
        case "BFS":
          // Executa a busca em largura
          long startTime = System.nanoTime();
          Runtime memory = Runtime.getRuntime();
          new BFS(initialBoard, finalBoard);
          long endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / 1024.0 / 1024.0);
          break;
        case "DFS":
          // Executa a busca em profundidade limitada
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          new DFS(initialBoard, finalBoard, Integer.MAX_VALUE);
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / 1024.0 / 1024.0);
          break;
        case "IDFS":
          // Executa a busca em profundidade iterativa
          int limite = 1;
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          DFS d = new DFS(initialBoard, finalBoard, limite);
          while(!d.findSolution){
            limite++;
            d = new DFS(initialBoard, finalBoard, limite);
          }
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / 1024.0 / 1024.0);
          break;
        case "A*-misplaced":
          // Executa a busca A* com a heurística de peças fora do lugar
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          new AStar(initialBoard, finalBoard, 1);
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / 1024.0 / 1024.0);
          break;
        case "A*-Manhattan":
          // Executa a busca A* com a heurística de distância de Manhattan
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          new AStar(initialBoard, finalBoard, 2);
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
          break;
        case "Greedy-misplaced":
          // Executa a busca Greedy com a heurística de peças fora do lugar 
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          new Greedy(initialBoard,finalBoard, 1); 
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
          break;
        case "Greedy-Manhattan": 
          // Executa a busca A* com a heurística de distância de Manhattan
          startTime = System.nanoTime();
          memory = Runtime.getRuntime();
          new Greedy(initialBoard,finalBoard, 2); 
          endTime = System.nanoTime();
          System.out.println("Tempo em nano segundos: " + (endTime - startTime));
          System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
          break;
        default:
          System.out.println("Strategy selected is not valid. Please check for spelling errors.");
          System.out.println("Options available for strategies are: ");
          System.out.println("  -BFS");
          System.out.println("  -DFS");
          System.out.println("  -IDFS");
          System.out.println("  -Greedy-Manhattan");
          System.out.println("  -Greedy-misplaced");
          System.out.println("  -A*-Manhattan");
          System.out.println("  -A*-misplaced");
      }
    }
    else{
      System.out.println("Board is not solvable.");
    }
  }
}
