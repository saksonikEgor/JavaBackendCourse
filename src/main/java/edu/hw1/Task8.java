package edu.hw1;

public class Task8 {
    private Task8() {
    }

    /**
     * Checks whether the knights are placed on the chessboard so that no knight can cut down the other.
     *
     * @param board a two-dimensional array of integers representing a playing board (board size must be equals 8)
     * @return true if there are no knights on the board that can cut down, false otherwise
     * @throws NullPointerException     if the input two-dimensional array or if any of its nested arrays are null
     * @throws IllegalArgumentException if the input two-dimensional array is of invalid size
     */
    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != 8) {
            throw new IllegalArgumentException("\"board\" is invalid because "
                + "the \"board.length\" is not equals 8");
        }
        int boardSize = 8;

        for (int i = 0; i < boardSize; i++) {
            if (board[i].length != 8) {
                throw new IllegalArgumentException("\"board\" is invalid because "
                    + "the \"board[" + i + "].length\" is not equals 8");
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 1 && (knightExist(board, i + 2, j + 1, boardSize)
                    || knightExist(board, i + 2, j - 1, boardSize) || knightExist(board, i - 2, j + 1, boardSize)
                    || knightExist(board, i - 2, j - 1, boardSize) || knightExist(board, i + 1, j + 2, boardSize)
                    || knightExist(board, i - 1, j + 2, boardSize) || knightExist(board, i + 1, j - 2, boardSize)
                    || knightExist(board, i - 1, j - 2, boardSize))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks whether a knight with a given position exists on the board.
     *
     * @param board     a two-dimensional array of integers representing a playing board
     * @param i         is row index on the board
     * @param j         is column index on the board
     * @param boardSize is board size
     * @return true if a knight with a given position exists on the board, false otherwise
     * @throws NullPointerException if the input two-dimensional array or if any of its nested arrays are null
     */
    private static boolean knightExist(int[][] board, int i, int j, int boardSize) {
        if (i < 0 || i >= boardSize || j < 0 || j >= boardSize) {
            return false;
        }
        return board[i][j] == 1;
    }
}
