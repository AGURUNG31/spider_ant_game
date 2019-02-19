import java.util.Random;

public class GameBoard {

    private static String[][] grid = new String[BoardPosition.BOARD_SIZE][BoardPosition.BOARD_SIZE];
    private int antRow;
    private int antCol;

    private int spiderRow;
    private int spiderCol;

    private AntDirection antDirection;

    private enum AntDirection {UP, DOWN, LEFT, RIGHT}

    public GameBoard(int antRow, int antCol, int spiderRow, int spiderCol) {
        this.antRow = antRow;
        this.antCol = antCol;
        this.spiderRow = spiderRow;
        this.spiderCol = spiderCol;
        updateGrid();
        this.antDirection = randomDirection();///change it to Antdirection.RIGHT TO TEST IT
        System.out.println("Ant created, it will move in `" + antDirection.name() + "` direction for this lifecycle");
    }

    void updateLocations(int antRow, int antCol, int spiderRow, int spiderCol) {
        System.out.println("Spider and Ant moved--updating locations");
        this.antRow = antRow;
        this.antCol = antCol;
        this.spiderRow = spiderRow;
        this.spiderCol = spiderCol;
        updateGrid();
    }

    public static AntDirection randomDirection() {
        return AntDirection.values()[new Random().nextInt(4)];
    }

    /**
     * update the grid elements
     */
    private void updateGrid() {
        for (int row = 0; row < BoardPosition.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardPosition.BOARD_SIZE; col++) {

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
        int max = BoardPosition.BOARD_SIZE;
        int min = 1;
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * print the grid contents to console
     */
    public void print() {

        for (int row = 0; row < BoardPosition.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardPosition.BOARD_SIZE; col++) {
                System.out.print("| " + grid[row][col] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * returns true if spider and ant location is same
     * @return a boolean
     */
    public boolean hasSpiderCaughtAnt() {
        if ((this.spiderRow == this.antRow) && (this.spiderCol == this.antCol)) {
            return true;
        }
        return false;
    }

    /**
     * gives a new ant with a new location
     */
    void resetAnt() {
        this.antRow = giveRandomCoordinate();
        this.antCol = giveRandomCoordinate();
        this.antDirection = randomDirection();//change it to Antdirection.RIGHT TO TEST IT
        System.out.println("Ant is reset, it will move in `" + antDirection.name() + "` direction.");
        System.out.println("Ant location Row = " + this.antRow);
        System.out.println("Ant location Col = " + this.antCol);
    }

    public BoardPosition antNextMove() {
        BoardPosition nextMove = null;
        switch (antDirection) {
            case UP: {
                if (BoardPosition.isValidCoordinate(antRow - 1, antCol)) {
                    nextMove = new BoardPosition(antRow - 1, antCol);
                }
                break;
            }
            case DOWN: {
                if (BoardPosition.isValidCoordinate(antRow + 1, antCol)) {
                    nextMove = new BoardPosition(antRow + 1, antCol);
                }
                break;
            }
            case LEFT: {
                if (BoardPosition.isValidCoordinate(antRow, antCol - 1)) {
                    nextMove = new BoardPosition(antRow, antCol - 1);
                }
                break;
            }
            case RIGHT: {
                if (BoardPosition.isValidCoordinate(antRow, antCol + 1)) {
                    nextMove = new BoardPosition(antRow, antCol + 1);
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
