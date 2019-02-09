import java.util.*;

class ShortestPath {

    public GridBoardPosition startPos;
    public GridBoardPosition endPos;
    public Spider pieceType;

    public ShortestPath(GridBoardPosition start, GridBoardPosition end, Spider piece) {
        startPos = start;
        endPos = end;
        pieceType = piece;
    }

    //Prints out the shortest path in terms of the steps of the shortest path, excluding the starting position
    public String bfs() {
        //Keep track of visited nodes and the parents of visited nodes (for finding the shortest path)
        HashMap<GridBoardPosition, GridBoardPosition> parentNode = new HashMap<GridBoardPosition, GridBoardPosition>();

        //Queue of nodes to visit
        Queue<GridBoardPosition> positionQueue = new LinkedList<GridBoardPosition>();

        //intially add the starting node
        parentNode.put(startPos, null);
        positionQueue.add(startPos);

        //Breadth first search
        while (positionQueue.peek() != null) //check if anymore nodes to visit
        {
            GridBoardPosition currentPosition = positionQueue.poll();

            if (currentPosition.equals(endPos)) {
                break; //we have reached the end position on the graph via the shortest path so stop searching
            }

            //otherwise get adjacent nodes (possible moves from current position for knight)
            ArrayList<GridBoardPosition> nextPositions = pieceType.validMoves(currentPosition);
            for (GridBoardPosition adjacentPosition : nextPositions) {
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
        GridBoardPosition currentNode = endPos; //start at the end node
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
        String spiderLoc = "E1";
        String antLoc = "A7";

        GridBoardPosition antPos = new GridBoardPosition(antLoc);
        GridBoardPosition spiderPos = new GridBoardPosition(spiderLoc);

        GridBoardView gridBoardView = new GridBoardView(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        Spider spider = new Spider();
        //do until the spider catches the ant
        while (!gridBoardView.spiderCatchesAnt()) {
            System.out.println("=========================================");
            System.out.println("Spider location = " + spiderPos);
            System.out.println("Ant location = " + antPos);
            gridBoardView.print();

            ShortestPath knightShortestPath = new ShortestPath(spiderPos, antPos, spider);
            String sp = knightShortestPath.bfs();
            List<String> shortestPaths = Arrays.asList(sp.split(" "));
            System.out.println("ShortestPath = " + sp);

            List<GridBoardPosition> spiderMoves = spider.validMoves(spiderPos);
            List<String> spiderLegalMovesString = new ArrayList<>();
            System.out.print("Spiders legal valid moves = ");

            for (GridBoardPosition cc : spiderMoves) {
                spiderLegalMovesString.add(cc.toString());
                System.out.print(cc.toString() + " ");
            }
            String spiderMove = spiderNextMove(shortestPaths, spiderLegalMovesString);
            System.out.println("\nSpiders next move = " + spiderMove);
            spiderPos = new GridBoardPosition(spiderMove);

            //get new gridBoardView with new location of spider and ant
            antPos = gridBoardView.antNextMove();
            gridBoardView.update(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        }
        System.out.println("=========================================");
        System.out.println("Spider caught ANT");
        System.out.println("Spider location = " + spiderPos);
        System.out.println("Ant location = " + antPos);
    }

}