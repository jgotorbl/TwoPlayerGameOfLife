import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * Created by Jaime on 3/22/2017.
 */


public class ProjectTest {
    private static ArrayList<String> correctGrid= new ArrayList<>(
            Arrays.asList(
            "10 10",
            "..........",
            "..........",
            "........12",
            "..........",
            "....111..2",
            "...122....",
            "...22.....",
            "....2.....",
            ".....2....",
            "12........"
            ));
    int[] playerCells = {5,9};

    public Board getUpdatedBoard(){
        Game game = new Game(correctGrid);
        Board currentBoard = game.getCurrentState();
        Board previousBoard = game.getPreviousState();
        previousBoard.updatePreviousBoard(currentBoard.getBoard());
        currentBoard.printBoard(playerCells);
        currentBoard.updateBoard(previousBoard.getBoard());

        currentBoard.printBoard(playerCells);
        return currentBoard;
    }

    @Test
    public void deadCellWithLessThanThreeNeighborsRemainsDead(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[1][9], Cell.CELL_DEAD);
    }

    @Test
    public void liveCellwithLessThanTwoLiveNeighborsDies(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[2][9], Cell.CELL_DEAD);
    }

    @Test
    public void liveCellWithMoreThanThreeLiveNeighborsDies(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[6][5], Cell.CELL_DEAD);
    }

    @Test
    public void liveCellwithThreeNeighborsRemainsAlive(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[7][4], Cell.CELL_TWO);
    }

    @Test
    public void liveCellwithTwoNeighborsRemainsAlive(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[4][6], Cell.CELL_ONE);
    }

    @Test
    public void deadCellWithExactlyThreeNeighborsBecomesALivingCell1(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[4][3], Cell.CELL_ONE);
    }

    @Test
    public void deadCellWithExactlyThreeNeighborsBecomesALivingCell2(){
        Board currentBoard = getUpdatedBoard();
        assertEquals(currentBoard.getBoard()[3][9], Cell.CELL_TWO);
    }

    @Test
    public void fileNotValidBecauseItIsMissingDimensions(){
        ArrayList<String> initialBoard = new ArrayList<>(
                Arrays.asList(
                        "..........",
                        "..........",
                        "........12",
                        "..........",
                        "....111..2",
                        "...122....",
                        "...22.....",
                        "....2.....",
                        ".....2....",
                        "12........"
                ));
        assertEquals(DataParser.validateInitialBoard(initialBoard), false);
    }

    @Test
    public void fileNotValidBecauseItIsMissingALine(){
        ArrayList<String> initialBoard = new ArrayList<>(
                Arrays.asList(
                        "10 10",
                        "..........",
                        "........12",
                        "..........",
                        "....111..2",
                        "...122....",
                        "...22.....",
                        "....2.....",
                        ".....2....",
                        "12........"
                ));
        assertEquals(DataParser.validateInitialBoard(initialBoard), false);
    }

    @Test
    public void fileNotValidBecauseNotAllColumnsHaveSameLength(){
        ArrayList<String> initialBoard = new ArrayList<>(
                Arrays.asList(
                        "10 10",
                        "..........",
                        ".........",
                        "........12",
                        "..........",
                        "....111..2",
                        "...122....",
                        "...22.....",
                        "....2.....",
                        ".....2....",
                        "12........"
                ));
        assertEquals(DataParser.validateInitialBoard(initialBoard), false);
    }

    @Test
    public void fileNotValidBecauseSomeCharacterIsWrong(){
        ArrayList<String> initialBoard = new ArrayList<>(
                Arrays.asList(
                        "10 10",
                        "..........",
                        "..........",
                        "...3....12",
                        "..........",
                        "....111..2",
                        "...122....",
                        "...22.....",
                        "....2.....",
                        ".....2....",
                        "12........"
                ));
        assertEquals(DataParser.validateInitialBoard(initialBoard), false);
    }

}
