import java.util.Random;

public class Board {

    public static void main(String[] args) {
        String[][] grid;
        int antRow = giveRandomCoordinate();
        int antCol = giveRandomCoordinate();

        int spiderRow = giveRandomCoordinate();
        int spiderCol = giveRandomCoordinate();

        //if they are on same location at beginning
        while ((antRow == spiderRow) && (antCol == spiderCol)) {

            spiderRow = giveRandomCoordinate();
            spiderCol = giveRandomCoordinate();
        }


        grid = new String[8][8];

        updateGrid(grid, antRow, antCol, spiderRow, spiderCol);
        printGrid(grid);

        //spider moves
        int spiderNewCol = spiderCol + 1;
        updateGrid(grid, antRow, antCol, spiderRow, spiderNewCol);
        printGrid(grid);

        //ant moves
        int antNewCol = antCol + 1;
        updateGrid(grid, antRow, antNewCol, spiderRow, spiderNewCol);
        printGrid(grid);


    }//end of main

    static int giveRandomCoordinate() {
        int max = 7;
        int min = 0;
        return new Random().nextInt((max - min) + 1) + min;
    }

    static void printGrid(String[][] board) {

        for (int row = 0; row < 8; row++) {
            System.out.println("--------------------------------------");
            for (int col = 0; col < 8; col++) {
                System.out.print("| "+board[row][col] + "  ");
            }
            System.out.println();
        }
    }

    static void updateGrid(String[][] board, int antRow, int antCol, int spiderRow, int spiderCol) {
        System.out.println("--------------------------------------");
        System.out.println("Location of ant:    [" + antRow + ", " + antCol + "]");
        System.out.println("Location of spider: [" + spiderRow + ", " + spiderCol + "]");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((row == antRow) && (col == antCol)) {
                    board[row][col] = "A";
                } else if ((row == spiderRow) && (col == spiderCol)) {
                    board[row][col] = "S";
                } else {
                    board[row][col] = "E";
                }
            }
        }

    }
}
