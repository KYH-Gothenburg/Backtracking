public class SudokuSolver {
    private int[][] board;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    private boolean solveSudoku(int row, int col) {
        // Exercise - Write this method
//        printBoard();
//        System.out.println();

        // Exit condition
        if(row == 8 && col == 9) {
            return true;
        }

        if(col == 9) {
            row++;
            col = 0;
        }
        if(board[row][col] != 0) {
            return solveSudoku(row, col + 1);
        }

        for(int num = 1; num < 10; num++) {
            if(canPlace(row, col, num)) {
                board[row][col] = num;
                if(solveSudoku(row, col + 1)) {
                    return true;
                }
            }
            // Backtracking
            board[row][col] = 0;
        }
        return false;
    }


    private boolean canPlace(int row, int col, int value) {
        // Check row
        for(int cell : board[row]) {
            if(cell == value) {
                return false;
            }
        }

        // Check column
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == value) {
                return false;
            }
        }

        // Check current 3 x 3 square

        // row = 4 col = 8
        // I want startRow = 3 startCol = 6
        // row = 5 col = 6
        // I want startRow = 3 startCol = 6
        // startRow and startCol will indicate the top left cell in this square
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[startRow + i][startCol + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for(int i = 0; i < 9; i++) {
            if(i % 3 == 0 && i != 0) {
                System.out.println("-----------------------");
            }
            for(int j = 0; j < 9; j++) {
                if(j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                    System.out.print(board[i][j] + " ");
                }
                else if(j == 8) {
                    System.out.println(board[i][j]);
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
            }
        }
    }

    public void solve() {
        solveSudoku(0, 0);
    }
}
