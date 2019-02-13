import java.util.*;

/**
 * Created by abhinav on 2019-02-09.
 */

class Spider {

    public Spider(){} //default constructor

    /**
     * given a position of the spider, it gives all the valid position of the spider within the board
     * @param startPosition of type BoardPosition
     * @return validCoordinates - an array list of tyoe BoardPosition
     */
    ArrayList<BoardPosition> validMovePositions(BoardPosition startPosition) {
        ArrayList<BoardPosition> validCoordinates = new ArrayList<>();

        if (BoardPosition.isValidCoordinate(startPosition.x - 2, startPosition.y - 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x - 2), (startPosition.y - 1))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x - 2, startPosition.y + 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x - 2), (startPosition.y + 1))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x - 1, startPosition.y - 2)) {
            validCoordinates.add((new BoardPosition((startPosition.x - 1), (startPosition.y - 2))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x - 1, startPosition.y + 2)) {
            validCoordinates.add((new BoardPosition((startPosition.x - 1), (startPosition.y + 2))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x, startPosition.y - 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x), (startPosition.y - 1))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x, startPosition.y + 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x), (startPosition.y + 1))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y - 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x + 1), (startPosition.y - 1))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y)) {
            validCoordinates.add((new BoardPosition((startPosition.x + 1), (startPosition.y))));
        }

        if (BoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y + 1)) {
            validCoordinates.add((new BoardPosition((startPosition.x + 1), (startPosition.y + 1))));
        }

        return validCoordinates;
    }


    public String nextMove(List<String> shortestPath, List<String> spiderValidMoves) {
        String toLocation = null;
        for (String path : shortestPath) {//go through each element of the shortest path calculated by bfs
            if (spiderValidMoves.contains(path)) { //if one of the spider valid moves is the path
                toLocation = path;
            }
        }
        if (null != toLocation) {
            return toLocation;
        } else {
            throw new RuntimeException("No valid move for spider along the shortest path to ant");
        }
    }

    public List<String> validMoves(BoardPosition startPosition) {
        List<BoardPosition> spiderMoves = this.validMovePositions(startPosition);
        List<String> spiderLegalMovesString = new ArrayList<>();
        System.out.print("Spiders legal valid moves = ");

        for (BoardPosition cc : spiderMoves) {
            spiderLegalMovesString.add(cc.toString()); // add all the valid moves to this arraylist
            System.out.print(cc.toString() + " "); // print out the spider valid moves
        }
        return spiderLegalMovesString;
    }

}