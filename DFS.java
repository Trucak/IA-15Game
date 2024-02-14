import java.util.LinkedList;

class DFS {
  // Cria uma pilha para armazenar os tabuleiros a serem visitados
  LinkedList<Board> stack = new LinkedList<>();
  // Cria uma lista temporária para armazenar os tabuleiros gerados pelos filhos
  LinkedList<Board> temp = new LinkedList<>();
  // Variável que indica se a solução foi encontrada
  boolean findSolution = false;
  // Número total de nós gerados
  int generatedNodes = 0;
  // Número de nós visitados
  int usedNodes = 0;
  // Profundidade da solução encontrada
  int solutionDepth = 0;

  // Construtor da classe DFS, que recebe o tabuleiro inicial, o tabuleiro final e o limite de profundidade
  DFS(Board board, Board finalBoard, int limit) {
    // Cria uma cópia do tabuleiro inicial
    Board newBoard = new Board(board.convertBoardToArray(), null, null, 0, "");
    // Adiciona o tabuleiro inicial à pilha
    stack.addLast(newBoard);
    // Chama o método DFS_visit para visitar os nós na pilha
    Board solution = DFS_visit(finalBoard, limit);
    // Se uma solução for encontrada, atualiza a profundidade da solução e marca que a solução foi encontrada
    if (solution != null) {
      solutionDepth = solution.depth;
      findSolution = true;
    }
    // Imprime a sequência de movimentos da solução encontrada
    while (solution != null) {
      System.out.print(solution.op);
      solution = solution.parent;
      if (solution != null)
        System.out.print("  ");
    }
    // Imprime o número de nós gerados, o número de nós visitados e a profundidade da solução, se uma solução for encontrada
    if (findSolution) {
      System.out.println();
      System.out.println("Número de nós gerados: " + generatedNodes);
      System.out.println("Número de nós utilizados: " + usedNodes);
      System.out.println("Profundidade da solução: " + solutionDepth);
    }
  }

  // Método DFS_visit que visita os nós na pilha
  Board DFS_visit(Board finalBoard, int limit) {
    while (stack.size() != 0) {
      // Remove o último tabuleiro adicionado à pilha
      Board newBoard = stack.pop();
      usedNodes++;
      // Verifica se o tabuleiro é a solução final
      if (GenericUtils.foundSolution(newBoard, finalBoard)) {
        return newBoard;
      }
      // Caso contrário, gera os filhos do tabuleiro atual e adiciona à lista temporária
      else {
        if (newBoard.depth < limit) {
          childrenGenerator(newBoard);
          insertInStack();
        }
      }
    }
    return null;
  }

  // Método para inserir os tabuleiros da lista temporária na pilha
  void insertInStack() {
    while (temp.size() != 0) {
      Board board = temp.pop();
      stack.addLast(board);
    }
  }
  
  // Método que gera os filhos de um tabuleiro e os adiciona à lista temporária
  void childrenGenerator(Board board){
    Board tabDown = board.moveDown();
    Board tabUp = board.moveUp();
    Board tabLeft = board.moveLeft();
    Board tabRight = board.moveRight();

    // Verifica se o movimento para baixo é válido e se o tabuleiro resultante não é repetido
    if(tabDown != null && !GenericUtils.checkRepeated(tabDown)){
      temp.addLast(tabDown);
      generatedNodes++;
    }
    // Verifica se o movimento para cima é válido e se o tabuleiro resultante não é repetido
    if(tabUp != null && !GenericUtils.checkRepeated(tabUp)){
      temp.addLast(tabUp);
      generatedNodes++;
    }
    // Verifica se o movimento para esquerda é válido e se o tabuleiro resultante não é repetido
    if(tabLeft != null && !GenericUtils.checkRepeated(tabLeft)){
      temp.addLast(tabLeft);
      generatedNodes++;
    }
    // Verifica se o movimento para direita é válido e se o tabuleiro resultante não é repetido
    if(tabRight != null && !GenericUtils.checkRepeated(tabRight)){
      temp.addLast(tabRight);
      generatedNodes++;
    }
  }
}
