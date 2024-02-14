public class GenericUtils{

  // Verifica se um estado inicial e final são iguais, retornando true se forem iguais, caso contrário retorna false.
  public static boolean foundSolution(Board initialBoard, Board finalBoard){
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(initialBoard.matrix[i][j] != finalBoard.matrix[i][j]){
          return false;
        }
      }
    }
    return true;
  }

  // Compara duas matrizes e verifica se são iguais. Retorna true se forem iguais, caso contrário retorna false.
  public static boolean compareMatrix(int[][] matrixA, int[][] matrixB){
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        if(matrixA[i][j] != matrixB[i][j])
          return false;
      }
    }
    return true;
  }

  // Verifica se um determinado estado já foi visitado em algum nível anterior, se sim, retorna true, caso contrário retorna false.
  public static boolean checkRepeated(Board board){
    Board auxBoard = board.parent;
    while(auxBoard != null){
      if(compareMatrix(board.getValue(),auxBoard.getValue()))
        return true;
      auxBoard = auxBoard.parent;
    }
    return false;
  }

  // Verifica a solubilidade do quebra-cabeça com base no número de inversões.
  // Retorna true se o quebra-cabeça for solúvel, caso contrário retorna false.
  public static boolean boardSolvability(int[] array){
    int parity = 0;
    int boardWidth = (int) Math.sqrt(array.length);
    int row = 0;
    int blankSpace = 0;

    // Calcula o número de inversões.
    for (int i = 0; i < array.length; i++){
      if (i % boardWidth == 0)
        row++;
      if (array[i] == 0)
        blankSpace = row;
      for (int j = i + 1; j < array.length; j++){
        if (array[i] > array[j] && array[j] != 0)
          parity++;
      }
    }

    // Verifica a solubilidade com base no número de inversões e na posição do espaço em branco.
    if (boardWidth % 2 == 0){
      if (blankSpace % 2 == 0)
        return parity % 2 == 0;
      else
        return parity % 2 != 0;
    }
    else
      return parity % 2 == 0;
  }
}
