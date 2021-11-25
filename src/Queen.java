public class Queen {
    private int[][] board;
    private int size;

    public Queen(int size) {
        board = new int[size][size];
        this.size = size;
    }

    private boolean solveRecursive(int row) {
        printBoard();
        // Exit condition
        // All queens are on teh board
        if(row >= size) {
            return true;
        }

        for(int col = 0; col < size; col++) {
            // Kollar om vi får stå där
            if(checkBoard(row, col)) {
                // Place
                board[row][col] = 1;
                if(solveRecursive(row + 1)) {
                    return true;
                }
                //Backtrack
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean checkBoard(int row, int col) {
        // We don't need to check if there is a queen on this row
        // as we work on a row by row basis

        // Check this column
        for(int i = 0; i < size; i++) {
            if(board[i][col] == 1) {
                return false;
            }
        }

        int i, j;
        // Check diagonal up and left
        for(i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 1) {
                return false;
            }
        }

        // Check diagonal up and right
        for(i = row, j = col; i >= 0 && j < size; i--, j++) {
            if(board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public void solve() {
        solveRecursive(0);
        printBoard();
    }

    public void printBoard() {
        for(int[] row : board) {
            for(int col : row) {
                String marker = col == 1 ? " Q " : " . ";

//                if(col == 1) {
//                    marker = " Q ";
//                }
//                else {
//                    marker = " . ";
//                }

                System.out.print(marker);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println();
    }
}
