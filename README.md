# TwoPlayerGameOfLife
Java Application implementing the two-player version of the Conway's Game Of Life

The Game of Life is played on a grid of cells, where each cell is either dead or alive. 
For each “turn” or “generation” of the game,the game iterates over all of the cells and
determines whether a cell is alive or dead based on a set of rules.

The rules for the two-player version are as follows:

- Any life cell with fewer than two life neighbors dies (under population).
- Any life cell with more than three neighbors dies (over population).
- Any life cell with two or three neighbors remains alive.
- Any dead cell with exactly three neighbors becomes a living cell, owned by the 
player who has the majority of neighbor living cells.

The game ends either when one player has no living cells left, or when two consecutive states 
or the game are equal.

The game starts by parsing the contents of a txt file that is passed as input from the console. 
The format of the file is as follows:
- The first row contains two numbers that determine the number of rows and columns of the board
- The rest of the rows are a grid of "." (denoting dead cells), "1" (denoting living cells owned
by player 1) and  "2" (living cells denoted by player 2).
Below is an example of a file:
10 10
..........
..1..11...
...1..22..
..111..21.
...22.....
.......22.
......2112
.......22.
....11....
12........







