import java.util.Random;

/**
 * // 
 */



class BoardPosition {

    public int x;
    public int y;
    public static int BOARD_SIZE = 8;

    /**
     * Assumes the x and y are generated randomly
     */
    BoardPosition(){
        x = getRandom();
        y = getRandom();
    }

    //Maps the integer horizontal co-ordinates to constant letter representations
    private enum AlgebraicXCoordinate {
        A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

        private int xCoordinate;

        //Map each enum value to an integer representing the x-coordinate
        AlgebraicXCoordinate(final int coordinate) {
            this.xCoordinate = coordinate;
        }

        public int getValue() {
            return this.xCoordinate;
        }

        public static AlgebraicXCoordinate fromInt(int coordinate) {
            for (AlgebraicXCoordinate algebraCoordinate : AlgebraicXCoordinate.values()) {
                if (coordinate == algebraCoordinate.getValue()) {
                    return algebraCoordinate;
                }
            }

            throw new IllegalArgumentException("Invalid position ");
        }
    }

    //Throws IllegalArgumentException if invalid co-ordinate range
    public BoardPosition(int xCoordinate, int yCoordinate) throws IllegalArgumentException {
        if (isValidCoordinate(xCoordinate, yCoordinate)) {
            x = xCoordinate;
            y = yCoordinate;
        } else {
            throw new IllegalArgumentException("Invalid position co-ordinate. BoardView size is: ");
        }
    }

    public BoardPosition(String algebraCoordinate) throws IllegalArgumentException {
        if (algebraCoordinate.length() == 2) {
            try {
                x = AlgebraicXCoordinate.valueOf(algebraCoordinate.substring(0, 1).toUpperCase()).getValue(); //Use AlgebraicXCoordinate enum
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid x co-ordinate." );
            }

            try {
                y = Integer.parseInt(algebraCoordinate.substring(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid y co-ordinate. " );
            }

            if (!isValidCoordinate(x, y)) {
                throw new IllegalArgumentException("Invalid position co-ordinate." );
            }

        } else {
            throw new IllegalArgumentException("Invalid co-ordinate");
        }
    }

    //is the coordinate a valid position on the defined board size
    //returns false if the coordinate lies outside the board boundary
    public static boolean isValidCoordinate(int xCoordinate, int yCoordinate) {
        return ((xCoordinate >= 1) && (xCoordinate <= BOARD_SIZE) && (yCoordinate >= 1) && (yCoordinate <= BOARD_SIZE)); // evaluate this expressiona and return boolean

    }

    //When does one BoardPosition equal another BoardPosition
    //Required method to override for use with 'contains' in collections
    //
    //Returns boolean
    public boolean equals(Object other) {

        if(other == this){
            return true;
        }
        if(!(other instanceof BoardPosition)){
            return false;
        }
        BoardPosition another = (BoardPosition) other;
        return another.x == this.x && another.y == this.y;
    }

    //Allows for storage of class in Hash based collections
    //Builds a unique int as the hashcode based on concatenating the string representation of each part of the coordinate
    //Returns int that is unique for each BoardPosition
    public int hashCode() {
        String xString = String.valueOf(this.x);
        String yString = String.valueOf(this.y);
        String stringCoordinate = xString + yString;
        return Integer.parseInt(stringCoordinate);
    }


    public String toString() {
        String xCoordinate = AlgebraicXCoordinate.fromInt(this.x).name();
        String yCoordinate = String.valueOf(this.y);
        return (xCoordinate + yCoordinate);
    }

    static int getRandom() {
        int max = 8;
        int min = 1;
        return new Random().nextInt((max - min) + 1) + min;
    }

}
