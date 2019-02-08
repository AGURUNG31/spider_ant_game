import java.util.Random;

public class Board {

    static String[][] grid = new String[8][8];
    private int antRow;
    private int antCol;

    private int spiderRow;
    private int spiderCol;
    private AntDirection antDirection;

//    public Board(String[][] grid, int antRow, int antCol, int spiderRow, int spiderCol) {
//        this.grid = grid;
//        this.antRow = antRow;
//        this.antCol = antCol;
//        this.spiderRow = spiderRow;
//        this.spiderCol = spiderCol;
//    }

    private enum AntDirection {UP, DOWN, LEFT, RIGHT}


    public Board(int antRow, int antCol, int spiderRow, int spiderCol) {
        this.antRow = antRow;
        this.antCol = antCol;
        this.spiderRow = spiderRow;
        this.spiderCol = spiderCol;
        updateGrid();
        this.antDirection = randomDirection();
//        this.antDirection = AntDirection.RIGHT; //for rest
        System.out.println("Ant created, it will move in `" + antDirection.name() + "` direction.");
    }

    void update(int antRow, int antCol, int spiderRow, int spiderCol) {
        System.out.println("Spider and Ant moved");
        this.antRow = antRow;
        this.antCol = antCol;
        this.spiderRow = spiderRow;
        this.spiderCol = spiderCol;
        updateGrid();
    }

    public static AntDirection randomDirection() {
        return AntDirection.values()[new Random().nextInt(4)];
    }

//    public static void main(String[] args) {
//        Board b = new Board(grid, giveRandomCoordinate(), giveRandomCoordinate(), giveRandomCoordinate(), giveRandomCoordinate());
//        while (b.spiderCatchesAnt()) {
//            b.antRow = giveRandomCoordinate();
//            b.antCol = giveRandomCoordinate();
//        }
//        grid = new String[8][8];
//
//        updateGrid(grid, b);
//        printGrid(grid);
//
//        //spider moves
//        //use bfs
////        int spiderNewCol = spiderCol + 1;
////        updateGrid(grid, b);
////        printGrid(grid);
//
//        //ant moves
////        //chose direction randomly
////        int antNewCol = antCol + 1;
////        updateGrid(grid, antRow, antNewCol, spiderRow, spiderNewCol);
////        printGrid(grid);
//
//
//    }//end of main

    private void updateGrid() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((row == antRow - 1) && (col == antCol - 1)) {
                    grid[row][col] = "A";
                } else if ((row == spiderRow - 1) && (col == spiderCol - 1)) {
                    grid[row][col] = "S";
                } else {
                    grid[row][col] = "E";
                }
            }
        }
    }

    //gives a random number between 0 and 7 inclusive
    static int giveRandomCoordinate() {
        int max = 7;
        int min = 0;
        return new Random().nextInt((max - min) + 1) + min;
    }

    static void printGrid(String[][] board) {

        for (int row = 0; row < 8; row++) {
            System.out.println("--------------------------------------");
            for (int col = 0; col < 8; col++) {
                System.out.print("| " + board[row][col] + "  ");
            }
            System.out.println();
        }
    }

    public void print() {

        for (int row = 0; row < 8; row++) {
//            System.out.println("--------------------------------------");
            for (int col = 0; col < 8; col++) {
                System.out.print("| " + grid[row][col] + "  ");
            }
            System.out.println();
        }
    }

//    //updates the grid with new spider position and ant position
//    static void updateGrid(String[][] board, Board b) {
//        System.out.println("--------------------------------------");
////        System.out.println("Location of ant:    [" + board.antRow + ", " + antCol + "]");
////        System.out.println("Location of spider: [" + spiderRow + ", " + spiderCol + "]");
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 8; col++) {
//
//                if ((row == b.antRow) && (col == b.antCol)) {
//                    board[row][col] = "A";
//                } else if ((row == b.spiderRow) && (col == b.spiderCol)) {
//                    board[row][col] = "S";
//                } else {
//                    board[row][col] = "E";
//                }
//            }
//        }
//
//    }

    public boolean spiderCatchesAnt() {
        if ((this.spiderRow == this.antRow) && (this.spiderCol == this.antCol)) {
            return true;
        }
        return false;
    }

    void resetAnt() {
        this.antRow = giveRandomCoordinate();
        this.antCol = giveRandomCoordinate();
        this.antDirection = randomDirection();
        System.out.println("Ant reset, it will move in `" + antDirection.name() + "` direction.");
        System.out.println("Ant location Row = " + this.antRow);
        System.out.println("Ant location Col = " + this.antCol);
    }

    public ChessCoordinate antNextMove() {
        ChessCoordinate nextMove = null;
        switch (antDirection) {
            case UP: {
                if (ChessCoordinate.isValidCoordinate(antRow - 1, antCol)) {
                    nextMove = new ChessCoordinate(antRow - 1, antCol);
                }
                break;
            }
            case DOWN: {
                if (ChessCoordinate.isValidCoordinate(antRow + 1, antCol)) {
                    nextMove = new ChessCoordinate(antRow + 1, antCol);
                }
                break;
            }
            case LEFT: {
                if (ChessCoordinate.isValidCoordinate(antRow, antCol - 1)) {
                    nextMove = new ChessCoordinate(antRow, antCol - 1);
                }
                break;
            }
            case RIGHT: {
                if (ChessCoordinate.isValidCoordinate(antRow, antCol + 1)) {
                    nextMove = new ChessCoordinate(antRow, antCol + 1);
                }
                break;
            }
            default:
                throw new RuntimeException("unknown direction");
        }
        if (null != nextMove) {
            return nextMove;
        } else {
            resetAnt();
            return antNextMove();
        }
    }
}//end of class
