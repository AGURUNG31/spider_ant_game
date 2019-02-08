import java.util.*;

class Knight extends ChessPiece {

//    public ArrayList<ChessCoordinate> validMoves(ChessCoordinate startPosition) {
//        //Will build up list of valid co-ordinates for next move
//        ArrayList<ChessCoordinate> validCoordiantes = new ArrayList<ChessCoordinate>();
//
//        int[] moves = {-2, -1, 1, 2};
//
//        for (int xMove : moves) {
//            for (int yMove : moves) {
//                if (Math.abs(xMove) != Math.abs(yMove)) { //A Knight can only move 1 space in one direction and 2 in the other
//                    if (ChessCoordinate.isValidCoordinate(startPosition.x + xMove, startPosition.y + yMove)) {
//                        validCoordiantes.add((new ChessCoordinate((startPosition.x + xMove), (startPosition.y + yMove))));
//                    }
//                }
//            }
//        }
//
//        return validCoordiantes;
//    }

    public ArrayList<ChessCoordinate> validMoves(ChessCoordinate startPosition) {
        ArrayList<ChessCoordinate> validCoordinates = new ArrayList<>();

        if (ChessCoordinate.isValidCoordinate(startPosition.x - 2, startPosition.y - 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x - 2), (startPosition.y - 1))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x - 2, startPosition.y + 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x - 2), (startPosition.y + 1))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x - 1, startPosition.y - 2)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x - 1), (startPosition.y - 2))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x - 1, startPosition.y + 2)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x - 1), (startPosition.y + 2))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x, startPosition.y - 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x), (startPosition.y - 1))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x, startPosition.y + 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x), (startPosition.y + 1))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x + 1, startPosition.y - 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x + 1), (startPosition.y - 1))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x + 1, startPosition.y)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x + 1), (startPosition.y))));
        }

        if (ChessCoordinate.isValidCoordinate(startPosition.x + 1, startPosition.y + 1)) {
            validCoordinates.add((new ChessCoordinate((startPosition.x + 1), (startPosition.y + 1))));
        }

        return validCoordinates;
    }

}