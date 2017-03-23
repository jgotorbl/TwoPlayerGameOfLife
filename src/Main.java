import java.util.ArrayList;

/**
 * Created by Jaime on 3/20/2017.
 */
public class Main {
    static Game game;
    /*
     * Main method
     * Executes the program
     * @param args input of the program that contains the initial board
     */
    public static void main(String[] args){
        String filePath = args[0];
        ArrayList<String> initialBoard = DataParser.initializeBoard(filePath);
        if(DataParser.validateInitialBoard(initialBoard)){
            game = new Game(initialBoard);
            while(!game.gameIsOver()){
                game.play();
            }
            game.printFinalResult(game.countNumberOfCellsOfEachPlayer());
        }else{
            System.out.println("Board not valid. Please use a valid one");
        }

    }
}
