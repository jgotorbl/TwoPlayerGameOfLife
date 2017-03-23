import java.util.ArrayList;

/**
 * Created by Jaime on 3/20/2017.
 */
public class Game {
    /*
     * Fields of the game class
     */
    private Board previousState;
    private Board currentState;
    private int generation;
    private boolean ended;

    /*
     * Constructor
     * @param an arraylist that contains the dimensions and the initial data of the board
     * Initializes the fields of the class with the appropiate values
     */
    public Game(ArrayList<String> initialBoard){
        String[] dims = initialBoard.get(0).split(" ");
        int n1 = Integer.parseInt(dims[0]);
        int n2 = Integer.parseInt(dims[1]);
        currentState = new Board(initialBoard, n1, n2);
        previousState = new Board(n1, n2);
        generation = 0;
        ended = false;
    }
    /*
     * @return the current board
     */
    public Board getCurrentState(){ return currentState;}

    /*
     * @return the previous state of the board
     */
    public Board getPreviousState(){return previousState;}

    /*
     * Method
     * Executes a single sequence of the game
     */
    public void play(){
        printGeneration();
        int[] playerCells = countNumberOfCellsOfEachPlayer();
        currentState.printBoard(playerCells);
        previousState.updatePreviousBoard(currentState.getBoard());
        currentState.updateBoard(previousState.getBoard());
        this.boardsAreEqual();
    }

    /*
     * Updates the number of cells that each player owns in the current state of the game
     * Modifies the variable "ended" to true if any of the players owns zero cells.
     * @return a two-dimension array containing the number of cells that each player owns
     */
    public int[] countNumberOfCellsOfEachPlayer() {
        Cell[][] currentStateBoard = currentState.getBoard();
        int[] playerCells = new int[Cell.values().length-1];
        for(int i=1; i < currentStateBoard.length; i++){
            for(int j=0; j < currentStateBoard[0].length; j++){
                if(currentStateBoard[i][j] == Cell.CELL_ONE){ playerCells[0]++;
                }else if(currentStateBoard[i][j] == Cell.CELL_TWO){ playerCells[1]++;
                }else{}
            }
        }
        if(playerCells[0] == 0 || playerCells[1] == 0) ended = true;
        return playerCells;
    }

    /*
     * Prints the current generation and increments the generation variable for the next execution.
     */
    public void printGeneration() {
        System.out.println("Generation #" + generation + ":");
        generation++;
    }

    /*
     * @return true if the game has ended. It returns false otherwise.
     */
    public boolean gameIsOver(){
        return this.ended;
    }

    /*
     * Compares the current state with the previous.
     * Modifies the ended variable to true if the two boards are equal
     */
    private void boardsAreEqual() {
        Cell[][] currentBoard = currentState.getBoard();
        Cell[][] prevBoard = previousState.getBoard();
        for(int i=0; i < currentBoard.length; i++){
            for(int j=0; j < currentBoard[0].length; j++){
                if(currentBoard[i][j] != prevBoard[i][j]){ return;}
            }
        }
        ended = true;
    }

    /*
     * Prints the final result
     * Outputs a tie in the console or display the winner and how many cells are alive in the last execution.
     */

    public void printFinalResult(int[] playerCells){
        if(playerCells[0] == playerCells[1]){
            System.out.println("It's a tie at " + playerCells[0] + " cells alive");
        }else if(playerCells[0] > playerCells[1]){
            System.out.println("Player 1 wins with " + playerCells[0] + " cells alive");
        }else{
            System.out.println("Player 2 wins with " + playerCells[1] + " cells alive");
        }
    }

}
