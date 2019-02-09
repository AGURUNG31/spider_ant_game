import java.util.*;

class Spider {

    public ArrayList<GridBoardPosition> validMoves(GridBoardPosition startPosition) {
        ArrayList<GridBoardPosition> validCoordinates = new ArrayList<>();

        if (GridBoardPosition.isValidCoordinate(startPosition.x - 2, startPosition.y - 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x - 2), (startPosition.y - 1))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x - 2, startPosition.y + 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x - 2), (startPosition.y + 1))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x - 1, startPosition.y - 2)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x - 1), (startPosition.y - 2))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x - 1, startPosition.y + 2)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x - 1), (startPosition.y + 2))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x, startPosition.y - 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x), (startPosition.y - 1))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x, startPosition.y + 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x), (startPosition.y + 1))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y - 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x + 1), (startPosition.y - 1))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x + 1), (startPosition.y))));
        }

        if (GridBoardPosition.isValidCoordinate(startPosition.x + 1, startPosition.y + 1)) {
            validCoordinates.add((new GridBoardPosition((startPosition.x + 1), (startPosition.y + 1))));
        }

        return validCoordinates;
    }

}