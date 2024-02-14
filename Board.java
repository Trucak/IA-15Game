class Board{
  int [][] matrix = new int[4][4]; // cria um array bidimensional 4x4 para representar o tabuleiro
  Board parent; // armazena o tabuleiro pai
  Board next; // armazena o próximo tabuleiro na sequência
  String op; // armazena a operação que gerou esse tabuleiro
  int depth; // armazena a profundidade do tabuleiro na árvore de busca
  int heuristic; // armazena o valor da heurística do tabuleiro

  // construtor que recebe um array de inteiros, um tabuleiro pai, o próximo tabuleiro, a profundidade e a operação que gerou o tabuleiro
  Board(int array[], Board parent, Board next, int depth, String op){
    int counter = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        matrix[i][j] = array[counter]; // preenche o array bidimensional com os valores do array recebido
        counter++;
      }
    }
    this.parent = parent;
    this.next = next;
    this.depth = depth;
    this.op = op;
    this.heuristic = 0; // define o valor da heurística como zero
  }

  // construtor que recebe um array de inteiros, um tabuleiro pai, o próximo tabuleiro, a profundidade, a operação que gerou o tabuleiro e a heurística do tabuleiro
  Board(int array[], Board parent, Board next, int depth, String op, int heuristic){
    int counter = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        matrix[i][j] = array[counter]; // preenche o array bidimensional com os valores do array recebido
        counter++;
      }
    }
    this.parent = parent;
    this.next = next;
    this.depth = depth;
    this.op = op;
    this.heuristic = heuristic; // define o valor da heurística como o valor recebido
  }

  // método que retorna a matriz do tabuleiro
  int[][] getValue(){
    return matrix;
  }

  // método que retorna o próximo tabuleiro na sequência
  Board getNext(){
    return next;
  }

  // método que define o próximo tabuleiro na sequência
  void setNext(Board board){
    this.next = board;
  }

  // método que retorna a posição da célula vazia na matriz
  int get0Position(){
    int pos = 0;
    for(int i = 0 ; i < 4 ; i++ ){
      for(int j = 0 ; j < 4 ; j++ ){
        if( matrix[i][j] == 0) // se a célula for vazia, retorna a posição atual
          return pos;
        else
          pos++; // caso contrário, incrementa a posição
      }
    }
    return pos;
  }

  // método que retorna a linha da célula vazia
  int get0line(){
    return (get0Position())/4;
  }

  // método que retorna a coluna da célula vazia
  int get0column(){
    return (get0Position())%4;
  }

// Esta função move o espaço vazio (0) uma posição para cima e retorna uma cópia do tabuleiro atualizado
  Board moveUp(){
    int line0 = get0line(); // Obtém a linha atual do espaço vazio
    int column0 = get0column(); // Obtém a coluna atual do espaço vazio
    if(line0 == 0) // Verifica se o espaço vazio já está na linha superior (não pode ser movido para cima)
      return null; // Se sim, retorna null indicando que o movimento não pode ser feito
    else{
      Board copy = this.copyBoard("U"); // Cria uma cópia do tabuleiro atual e adiciona a ação "U" (up) ao histórico de movimentos
      int temp = copy.matrix[line0-1][column0]; // Armazena o número que está na posição acima do espaço vazio
      copy.matrix[line0-1][column0] = 0; // Move o espaço vazio para cima, colocando o número armazenado em sua posição anterior
      copy.matrix[line0][column0] = temp; // Coloca o número armazenado na posição anterior do espaço vazio
      return copy; // Retorna a cópia atualizada do tabuleiro
    }
  }

// Esta função move o espaço vazio (0) uma posição para baixo e retorna uma cópia do tabuleiro atualizado
  Board moveDown(){
    int line0 = get0line(); // Obtém a linha atual do espaço vazio
    int column0 = get0column(); // Obtém a coluna atual do espaço vazio
    if(line0 == 3) // Verifica se o espaço vazio já está na linha inferior (não pode ser movido para baixo)
      return null; // Se sim, retorna null indicando que o movimento não pode ser feito
    else{
      Board copy = this.copyBoard("D"); // Cria uma cópia do tabuleiro atual e adiciona a ação "D" (down) ao histórico de movimentos
      int temp = copy.matrix[line0+1][column0]; // Armazena o número que está na posição abaixo do espaço vazio
      copy.matrix[line0+1][column0] = 0; // Move o espaço vazio para baixo, colocando o número armazenado em sua posição anterior
      copy.matrix[line0][column0] = temp; // Coloca o número armazenado na posição anterior do espaço vazio
      return copy; // Retorna a cópia atualizada do tabuleiro
    }
  }

// Esta função move o espaço vazio (0) uma posição para a esquerda e retorna uma cópia do tabuleiro atualizado
  Board moveLeft() {
    int line0 = get0line(); // Obtém a linha atual do espaço vazio
    int column0 = get0column(); // Obtém a coluna atual do espaço vazio
    if (column0 == 0) { // Verifica se o espaço vazio já está na coluna mais à esquerda (não pode ser movido para a esquerda)
      return null; // Se sim, retorna null indicando que o movimento não pode ser feito
    } else {
      Board copy = this.copyBoard("L"); // Cria uma cópia do tabuleiro atual e adiciona a ação "L" (left) ao histórico de movimentos
      int temp = copy.matrix[line0][column0 - 1]; // Armazena o número que está à esquerda do espaço vazio
      copy.matrix[line0][column0 - 1] = 0; // Move o espaço vazio para a esquerda, colocando o número armazenado em sua posição anterior
      copy.matrix[line0][column0] = temp; // Coloca o número armazenado na posição anterior do espaço vazio
      return copy; // Retorna a cópia atualizada do tabuleiro
    }
  }

// Esta função move o espaço vazio (0) uma posição para a direita e retorna uma cópia do tabuleiro atualizado
  Board moveRight() {
    int line0 = get0line(); // Obtém a linha atual do espaço vazio
    int column0 = get0column(); // Obtém a coluna atual do espaço vazio
    if (column0 == 3) { // Verifica se o espaço vazio já está na coluna mais à direita (não pode ser movido para a direita)
      return null; // Se sim, retorna null indicando que o movimento não pode ser feito
    } else {
      Board copy = this.copyBoard("R"); // Cria uma cópia do tabuleiro atual e adiciona a ação "R" (right) ao histórico de movimentos
      int temp = copy.matrix[line0][column0 + 1]; // Armazena o número que está à direita do espaço vazio
      copy.matrix[line0][column0 + 1] = 0; // Move o espaço vazio para a direita, colocando o número armazenado em sua posição anterior
      copy.matrix[line0][column0] = temp; // Coloca o número armazenado na posição anterior do espaço vazio
      return copy; // Retorna a cópia atualizada do tabuleiro
    }
  }

// Cria uma cópia do tabuleiro atual, adicionando a ação realizada como argumento.
  Board copyBoard(String op){
    int array[] = new int[16];
    int aux = 0;
    // Copia os valores do tabuleiro para um array unidimensional.
    for(int i = 0 ; i < 4 ; i++){
        for(int j = 0 ; j < 4 ; j++){
            array[aux] = this.matrix[i][j];
            aux++;
        }
    }
    // Retorna uma nova instância de Board com o array criado, o pai sendo o tabuleiro atual, 
    // a profundidade sendo a profundidade do pai + 1 e a ação sendo a string passada como argumento.
    return new Board(array,this,null,this.depth + 1,op);
  }

  // Converte o tabuleiro em um array unidimensional.
  int[] convertBoardToArray(){
    int array[] = new int[16];
    int aux = 0;
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            array[aux] = this.matrix[i][j];
            aux++;
        }
    }
    return array;
  }
}
