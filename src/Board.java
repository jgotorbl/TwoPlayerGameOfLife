import java.util.ArrayList;

/**
 * Created by Jaime on 3/20/2017.
 */
public class Board {

    Cell [][] board;
    /*
     * Constructor.
     * Initialices a board with all the cells pointing to null.
     * @param n1,n2 the dimensions of the board
     */
    public Board(int n1, int n2){
        this.board = new Cell[n1][n2];
    }

    /*
     * Constructor.
     * Initialices a board with the initial board generation
     * @param n1,n2 the dimensions of the board
     * @param initialBoard list containing the initial data for the board
     */
    public Board(ArrayList<String> initialBoard, int n1, int n2) {
        board = new Cell[n1][n2];
        for(int i=0; i < n1; i++){
            String s = initialBoard.get(i+1);
            for(int j=0; j < n2; j++){
                board[i][j] = Cell.assignCell(String.valueOf(s.charAt(j)));
            };
        }
    }

    /*
     * Method.
     * Initialices a board with the initial board generation
     * @return board
     */
    public Cell[][] getBoard(){ return board;}

    /*
     * Method.
     * Prints the current board as well as the number of cells
     * that each player owns
     * @param playerCells bidimensional array containing the
     * number of cells that each player owns
     */
    public void printBoard(int[] playerCells){
        System.out.println("Player 1 cells: #" + playerCells[0]);
        System.out.println("Player 2 cells: #" + playerCells[1]);
        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[0].length; j++){
                System.out.print(board[i][j].getC());
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /*
     * Method.
     * Copies a board data into other board
     * @param otherBoard board to copy
     */
    public void updatePreviousBoard(Cell[][] otherBoard){
        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[0].length; j++){
                this.board[i][j] = otherBoard[i][j];
            }
        }
    }

    /*
     * Method
     * Generates the next state of the board based on its previous state
     * @param prevState board of the previous state.
     */
    public void updateBoard(Cell[][] prevState){
        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[0].length; j++){
                board[i][j] = updateCell(i,j, prevState);
            }
        }
    }

    /*
     * Method
     * For a certain position of the board, it determines which kind
     * of cell it will be in the next state
     * @params i,j position of the cell.
     * @param prevState board of the previous state.
     */
    private Cell updateCell(int i, int j, Cell[][] prevState){
        int[] neighbors = getNumberOfNeighbors(i,j, prevState);
        int livingNeighbors = neighbors[0] + neighbors [1];
        if(this.board[i][j] == Cell.CELL_DEAD){
            if(livingNeighbors == 3){
                    if(neighbors[0] > neighbors [1]){
                    return Cell.CELL_ONE;
                }else{
                    return Cell.CELL_TWO;
                }
            }
        }else{
            if(livingNeighbors < 2 || livingNeighbors > 3){
                return Cell.CELL_DEAD;
            }else{
                return board[i][j];
            }
        }
        return Cell.CELL_DEAD;
    }

    /*
     * Method
     * It returns a three-dimension array containing the neighbors of a specific cell.
     * @params i,j position of the cell.
     * @param prevState board of the previous state.
     * @return int array containing the number and the type of all the adjacent cells.
     */
    private int[] getNumberOfNeighbors(int i, int j, Cell[][] prevState){
        int[] neighbors = new int[Cell.values().length];
        for(int u = i-1; u<= i+1; u++){
            for(int v = j-1; v <= j+1; v++){
                if((u==i && v == j)){}
                else if(u < 0 || u >= board.length ||
                        v < 0 || v >= board[0].length){
                }else{
                    if(prevState[u][v] == Cell.CELL_ONE){
                        neighbors[0]++;
                    }else if(prevState[u][v] == Cell.CELL_TWO){
                        neighbors[1]++;
                    }else{
                        neighbors[2]++;
                    }
                }
            }
        }
        return neighbors;
    }
}
