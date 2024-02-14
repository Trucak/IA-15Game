import java.util.PriorityQueue;
import java.util.Comparator;

// Classe que implementa o comparador utilizado para ordenar os boards na Priority Queue
class BoardComparator2 implements Comparator<Board>{
    public int compare(Board board1, Board board2){
        return (board1.heuristic + board1.depth) - (board2.heuristic + board2.depth);
        // Calcula o valor de f(n) para cada board (f(n) = g(n) + h(n))
        // g(n) é a profundidade do board na árvore de busca
        // h(n) é a heurística do board
    }
}


class AStar{
  // Comparador utilizado para ordenar a fila de prioridade
  Comparator<Board> comparator = new BoardComparator2();
  // Fila de prioridade que armazena os tabuleiros a serem avaliados
  PriorityQueue<Board> queue = new PriorityQueue<>(comparator);
  // Fila de prioridade utilizada para armazenar temporariamente os tabuleiros gerados pelos filhos
  PriorityQueue<Board> temp = new PriorityQueue<>(comparator);
  // Tabuleiro objetivo que se quer alcançar
  Board finalBoard;

  // Opção de heurística escolhida para o algoritmo A*
  int option;
  // Número de nós gerados durante a execução do algoritmo
  int generatedNodes = 0;
  // Número de nós utilizados durante a execução do algoritmo
  int usedNodes = 0;
  // Profundidade da solução encontrada
  int solutionDepth = 0;


  // Construtor da classe, que recebe o tabuleiro inicial, o tabuleiro objetivo e a opção de heurística escolhida
  AStar(Board board, Board finalBoard, int option){
      this.finalBoard = finalBoard;
      this.option = option;
      // Cria um novo tabuleiro a partir do tabuleiro inicial e adiciona-o à fila de prioridade
      Board newBoard = new Board(board.convertBoardToArray(),null,null,0, "", 0);
      queue.offer(newBoard);
      // Visita o tabuleiro objetivo para obter a solução
      Board solution = AStar_visit(finalBoard);
      // Armazena a profundidade da solução encontrada
      solutionDepth = solution.depth;
      // Imprime a sequência de movimentos necessários para chegar à solução
      while(solution != null){
        System.out.print(solution.op);
        solution = solution.parent;
        if(solution != null)
          System.out.print("  ");
      }
      // Imprime o número de nós gerados e utilizados durante a execução do algoritmo, bem como a profundidade da solução encontrada
      System.out.println();
      System.out.println("Número de nós gerados: " + generatedNodes);
      System.out.println("Número de nós utilizados: " + usedNodes);
      System.out.println("Profundidade da solução: " + solutionDepth);
  }

  // Função que visita os tabuleiros armazenados na fila de prioridade
  Board AStar_visit(Board finalBoard){
    while(queue.size()!=0){
      // Retira o tabuleiro com menor custo da fila de prioridade
      Board newTabuleiro = queue.poll();
      usedNodes++;
      // Verifica se o tabuleiro retirado é o tabuleiro objetivo
      if(GenericUtils.foundSolution(newTabuleiro,finalBoard)){
        return newTabuleiro;
      }
      else{
        // Gera os filhos do tabuleiro retirado e armazena-os na fila temporária
        childrenGenerator(newTabuleiro);
        insertInQueueAasterisk();
      }
    }
    return null;
  }
  // Função que insere o estado do tabuleiro na fila de prioridade
  void insertInQueueAasterisk(){
    queue.offer(temp.poll());
  }

  // Função que calcula a heurística misplaced tiles
  void misplacedPieces(Board board){
    int counter = 0;
    int array[] = board.convertBoardToArray();
    int arrayFinal[] = this.finalBoard.convertBoardToArray();
    for(int i = 0; i < 16 ; i++){
      if(array[i] != arrayFinal[i]){
        counter++;
      }
    }
    board.heuristic = counter;
  }

  // Função que calcula a heurística manhattan distance
  void manhattanDistance(Board board){
    int distance = 0;
    int value = 0;
    int initialPositionArray[] = board.convertBoardToArray();
    int finalPositionArray[] = finalBoard.convertBoardToArray();
    for(int i = 0; i < 16; i++){
      if(initialPositionArray[i] != finalPositionArray[i] && initialPositionArray[i] != 0 && initialPositionArray[i] != 0){
        value = initialPositionArray[i];
        for(int j = i; j < 16; j++)
          if(finalPositionArray[j] == value)
            break;
            distance++;
      }
    }
    board.heuristic = distance;
  }

  // Função que seleciona a heurística a ser utilizada pelo algoritmo A*
  void AStarHeuristic(Board board){
    if(option == 1)
      misplacedPieces(board);
    else
      manhattanDistance(board);
  }

  // Função que gera os filhos do estado do tabuleiro atual
  void childrenGenerator(Board board){
    Board tabDown = board.moveDown();
    Board tabUp = board.moveUp();
    Board tabLeft = board.moveLeft();
    Board tabRight = board.moveRight();

    // Verifica se o movimento para baixo é possível e se o estado gerado não é repetido
    if(tabDown != null && !GenericUtils.checkRepeated(tabDown)){
      AStarHeuristic(tabDown);
      temp.offer(tabDown);
      generatedNodes++;
    }
    // Verifica se o movimento para cima é possível e se o estado gerado não é repetido
    if(tabUp != null && !GenericUtils.checkRepeated(tabUp)){
      AStarHeuristic(tabUp);
      temp.offer(tabUp);
      generatedNodes++;
    }
    // Verifica se o movimento para esquerda é possível e se o estado gerado não é repetido
    if(tabLeft != null && !GenericUtils.checkRepeated(tabLeft)){
      AStarHeuristic(tabLeft);
      temp.offer(tabLeft);
      generatedNodes++;
    }
    // Verifica se o movimento para direita é possível e se o estado gerado não é repetido
    if(tabRight != null && !GenericUtils.checkRepeated(tabRight)){
      AStarHeuristic(tabRight);
      temp.offer(tabRight);
      generatedNodes++;
    }
  }
}
