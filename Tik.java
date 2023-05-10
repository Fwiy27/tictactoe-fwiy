import java.util.Scanner;
public class Tik {
    //initializes the starting board, all empty
    int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    Scanner scan = new Scanner(System.in);

    //prints out the board using for loops
    private String printBoard() {
        StringBuilder ret = new StringBuilder();
        int row = 0;
        for (int[] ints : board) {
            row++;
            for (int c = 0; c < board[0].length; c++) {
                ret.append(getValue(ints[c]));
                if (!(c == board.length - 1)) {
                    ret.append("|");
                }
            }
            if (row!=board[0].length) {
                ret.append("\n");
            }
        }
        return ret.toString();
    }

    //return the string of the correct player given board position
    private String getValue(int v) {
        return switch (v) {
            case 1 -> "x";
            case 2 -> "o";
            default -> "-";
        };
    }

    public void play() {
        clear();
        int player = 1;
        while (checkWin() == 0) {
            System.out.println("PLAYER " + (player));
            System.out.println(printBoard());
            System.out.print("Choose row: ");
            int r = scan.nextInt();
            System.out.print("Choose col: ");
            int c = scan.nextInt();
            clear();
            try {
                if (board[r][c] == 0) {
                    board[r][c] = player;
                    player = 3 - player;
                } else {
                    System.out.println("Spot taken, choose another");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of grid, try again");
            }
        }
        endGame();
    }

    //will check all possible 8 wins and return true when win is found
    private int checkWin() {
        if (fullBoard()) {
            return 3;
        } //tie

        //horizontal rows
        for (int[] r : board) {
                if (r[0] == r[1] && r[0]==r[2]) {
                    return r[0];
                }
        }

        //vertical columns
        for (int c = 0 ; c < board[0].length ; c++) {
            if (board[0][c] == board[1][c] && board[0][c] == board[2][c]) {
                return board[0][c];
            }
        }

        //diagonal from top left to bottom right
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) { return board[0][0]; }

        //diagonal from top right to bottom left
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) { return board[0][2]; }

        return 0; //continues
    }

    private boolean fullBoard() {
        for (int[] row : board) {
            for (int val : row) {
                if (val==0) {
                    return false;
                }
            }
        }
        return true;
    }

    //send 50 blank lines to simulate clear screen
    private void clear() {
        for (int i = 0 ; i <= 50 ; i++) {
            System.out.println("\n");
        }
    }

    private void endGame() {

        System.out.println("GAME OVER");

        switch(checkWin()) {
            case 2 -> System.out.println("Player 2 Wins!");
            case 3 -> System.out.println("Tie!");
            default -> System.out.println("Player 1 Wins!");
        }
        System.out.println(printBoard());
    }
}
