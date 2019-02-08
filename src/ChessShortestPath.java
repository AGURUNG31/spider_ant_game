import java.util.*;

//ChessShortestPath provides a solution to the shortest path between a starting position and end position for a knight chess piece
class ChessShortestPath {

    public ChessCoordinate startPos;
    public ChessCoordinate endPos;
    public ChessPiece pieceType;

    public ChessShortestPath(ChessCoordinate start, ChessCoordinate end, ChessPiece piece) {
        startPos = start;
        endPos = end;
        pieceType = piece;
    }

    //Finds the shortest path between a start node and end node for a unweighted graph using Breadth First Search
    //
    //For Chess Shortest Path, the chess board is an unweighted graph where the current position of the knight is the visited node in Breadth First Search and the positions of the possible next moves are the adjacent nodes
    //
    //Prints out the shortest path in terms of the steps of the shortest path, excluding the starting position
    public String bfs() {
        //Keep track of visited nodes and the parents of visited nodes (for finding the shortest path)
        HashMap<ChessCoordinate, ChessCoordinate> parentNode = new HashMap<ChessCoordinate, ChessCoordinate>();

        //Queue of nodes to visit
        Queue<ChessCoordinate> positionQueue = new LinkedList<ChessCoordinate>();

        //intially add the starting node
        parentNode.put(startPos, null);
        positionQueue.add(startPos);

        //Breadth first search
        while (positionQueue.peek() != null) //check if anymore nodes to visit
        {
            ChessCoordinate currentPosition = positionQueue.poll();

            if (currentPosition.equals(endPos)) {
                break; //we have reached the end position on the graph via the shortest path so stop searching
            }

            //otherwise get adjacent nodes (possible moves from current position for knight)
            ArrayList<ChessCoordinate> nextPositions = pieceType.validMoves(currentPosition);
            for (ChessCoordinate adjacentPosition : nextPositions) {
                //if this adjacent nodes is one that hasn't been visited add it to the queue
                //also keep track of the adjacent node's parent (the current node)
                if (!parentNode.containsKey(adjacentPosition)) {
                    parentNode.put(adjacentPosition, currentPosition);
                    positionQueue.add(adjacentPosition);
                }
            }
        }

        //traverse back from end position coordinate to start position using the parent map to get shortest path
        //build up string of shortest path at same time
        ChessCoordinate currentNode = endPos; //start at the end node
        String shortestPath = "";
        while (parentNode.get(currentNode) != null) //stop once we are at the start node
        {
            shortestPath = currentNode.toString() + " " + shortestPath;
            currentNode = parentNode.get(currentNode);
        }

        if (shortestPath.length() == 0) //When start position = end position
        {
            shortestPath = startPos.toString();
        }

        //Print out the shortest path found, excluding start position and including end position
//		System.out.println(shortestPath);
        return shortestPath.trim();
    }

    private static String spiderNextMove(List<String> sps, List<String> sms) {
        String prevMove = null;
        for (String sp : sps) {
            if (sms.contains(sp)) {
                prevMove = sp;
            }
        }
        if (null != prevMove) {
            return prevMove;
        } else {
            throw new RuntimeException("No valid move for spider along the shortest path to ant");
        }
    }

    public static void main(String[] args) {
        String spider = "E1";
//        System.out.println("Spider location = " + spider);
        String ant = "A7";
//        System.out.println("Ant location = " + ant);

        ChessCoordinate antPos = new ChessCoordinate(ant);
        ChessCoordinate spiderPos = new ChessCoordinate(spider);

        Board board = new Board(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        ChessPiece knight = new Knight();
        //do until the spider catches the ant
        while (!board.spiderCatchesAnt()) {
            System.out.println("=========================================");
            System.out.println("Spider location = " + spiderPos);
            System.out.println("Ant location = " + antPos);
            board.print();

            ChessShortestPath knightShortestPath = new ChessShortestPath(spiderPos, antPos, knight);
            String sp = knightShortestPath.bfs();
            List<String> shortestPaths = Arrays.asList(sp.split(" "));
            System.out.println("ShortestPath = " + sp);

            List<ChessCoordinate> spiderMoves = knight.validMoves(spiderPos);
            List<String> spiderLegalMovesString = new ArrayList<>();
            System.out.print("Spiders legal valid moves = ");

            for (ChessCoordinate cc : spiderMoves) {
                spiderLegalMovesString.add(cc.toString());
                System.out.print(cc.toString() + " ");
            }
            String spiderMove = spiderNextMove(shortestPaths, spiderLegalMovesString);
            System.out.println("\nSpiders next move = " + spiderMove);
            spiderPos = new ChessCoordinate(spiderMove);

            //get new board with new location of spider and ant
            antPos = board.antNextMove();
            board.update(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        }
        System.out.println("=========================================");
        System.out.println("Spider caught ANT");
        System.out.println("Spider location = " + spiderPos);
        System.out.println("Ant location = " + antPos);
    }

}