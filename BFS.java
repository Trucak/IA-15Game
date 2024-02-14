import java.util.LinkedList;

public class BFS {
    LinkedList<Board> queue = new LinkedList<>();
    LinkedList<Board> temp = new LinkedList<>();

    int generatedNodes = 0;
    int usedNodes = 0;
    int solutionDepth = 0;

    // Construtor da classe, recebe o tabuleiro inicial e o tabuleiro final
    BFS(Board board, Board finalBoard){
        // Cria um novo tabuleiro com base no tabuleiro inicial e adiciona-o à fila
        Board newBoard = new Board(board.convertBoardToArray(), null, null, 0, "");
        queue.addLast(newBoard);
        // Chama o método BFS_visit para iniciar a busca
        Board solution = BFS_visit(finalBoard);
        // Salva a profundidade da solução
        solutionDepth = solution.depth;
        // Imprime a solução
        while(solution != null){
            System.out.print(solution.op);
            solution = solution.parent;
            if(solution != null)
                System.out.print("  ");
        }
        // Imprime o número de nós gerados, o número de nós utilizados e a profundidade da solução
        System.out.println();
        System.out.println("Número de nós gerados: " + generatedNodes);
        System.out.println("Número de nós utilizados: " + usedNodes);
        System.out.println("Profundidade da solução: " + solutionDepth);
    }

    // Realiza a busca em largura
    Board BFS_visit(Board finalBoard){
        while(queue.size() != 0){
            // Remove o primeiro tabuleiro da fila
            Board newBoard = queue.pollFirst();
            // Incrementa o número de nós utilizados
            usedNodes++;
            // Verifica se o tabuleiro atual é a solução
            if(GenericUtils.foundSolution(newBoard,finalBoard)){
                return newBoard;
            }
            else{
                // Gera os filhos do tabuleiro atual e os adiciona à lista temporária
                childrenGenerator(newBoard);
                // Insere os tabuleiros da lista temporária na fila
                insertInQueue();
            }
        }
        return null;
    }

    // Insere os tabuleiros da lista temporária na fila
    void insertInQueue(){
        while(temp.size() != 0){
            Board board = temp.pollFirst();
            queue.addLast(board);
        }
    }

    // Gera os filhos do tabuleiro atual e os adiciona à lista temporária
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
        if(tabRight != null && !GenericUtils.checkRepeated(tabRight) ){
            temp.addLast(tabRight);
            generatedNodes++;
        }
    }
}
