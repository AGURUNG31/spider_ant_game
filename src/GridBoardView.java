import java.util.Random;

public class GridBoardView {

    private static String[][] grid = new String[8][8];
    private int antRow;
    private int antCol;

    private int spiderRow;
    private int spiderCol;
    private AntDirection antDirection;

    private enum AntDirection {UP, DOWN, LEFT, RIGHT}

    public GridBoardView(int antRow, int antCol, int spiderRow, int spiderCol) {
        this.antRow = antRow;
        this.antCol = antCol;
        this.spiderRow = spiderRow;
        this.spiderCol = spiderCol;
        updateGrid();
        this.antDirection = randomDirection();
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

    public void print() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print("| " + grid[row][col] + "  ");
            }
            System.out.println();
        }
    }

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

    public GridBoardPosition antNextMove() {
        GridBoardPosition nextMove = null;
        switch (antDirection) {
            case UP: {
                if (GridBoardPosition.isValidCoordinate(antRow - 1, antCol)) {
                    nextMove = new GridBoardPosition(antRow - 1, antCol);
                }
                break;
            }
            case DOWN: {
                if (GridBoardPosition.isValidCoordinate(antRow + 1, antCol)) {
                    nextMove = new GridBoardPosition(antRow + 1, antCol);
                }
                break;
            }
            case LEFT: {
                if (GridBoardPosition.isValidCoordinate(antRow, antCol - 1)) {
                    nextMove = new GridBoardPosition(antRow, antCol - 1);
                }
                break;
            }
            case RIGHT: {
                if (GridBoardPosition.isValidCoordinate(antRow, antCol + 1)) {
                    nextMove = new GridBoardPosition(antRow, antCol + 1);
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
