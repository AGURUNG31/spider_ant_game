import java.util.*;

/**
 * Created by abhinav on 2019-02-09.
 */
class ShortestPath {

    private BoardPosition startPos;
    private BoardPosition endPos;
    private Spider spider;

    ShortestPath(BoardPosition start, BoardPosition end, Spider spider) {
        startPos = start;
        endPos = end;
        this.spider = spider;
    }

    List<String> bfs() {
        //Keep track of visited nodes and the parents of visited nodes (for finding the shortest path)
        HashMap<BoardPosition, BoardPosition> parentNode = new HashMap<>();

        //Queue of nodes to visit
        Queue<BoardPosition> queue = new LinkedList<>();

        //initially add the starting node with parent null
        parentNode.put(startPos, null);
        queue.add(startPos);// add the node to queue

        //Breadth first search
        //check if anymore nodes to visit
        while (queue.peek() != null) {
            BoardPosition currentPosition = queue.poll();// return and remove the element at the front

            if (currentPosition.equals(endPos)) {
                break; //we have reached the end position on the graph via the shortest path so break out of while loop
            }

            //otherwise get adjacent nodes (possible moves from current position for spider
            //possible moves of a spider at a particular location is stored in this arraylist
            ArrayList<BoardPosition> nextPositions = spider.validMovePositions(currentPosition);

            //iterate through each valid position of the spider
            //nextPositions is the arraylist we just created
            for (BoardPosition adjacentPosition : nextPositions) {
                //if this adjacent nodes is one that hasn't been visited add it to the queue
                //also keep track of the adjacent node's parent (the current node)
                if (!parentNode.containsKey(adjacentPosition)) {
                    parentNode.put(adjacentPosition, currentPosition);//currentPoiition is parent and adjacentPosition is child
                    queue.add(adjacentPosition);
                }
            }
        }

        //traverse back from end position coordinate to start position using the parent map to get shortest path
        //build up string of shortest path at same time
        BoardPosition currentNode = endPos; //start at the end node
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

        System.out.println("ShortestPath BFS = " + shortestPath.trim());
//        System.out.println("-------------------------------");
//        for(BoardPosition key:parentNode.keySet()){
//            System.out.println(key +"-->"+ parentNode.get(key) );
//        }
        //Print out the shortest path found, excluding start position and including end position
        return Arrays.asList(shortestPath.trim().split(" "));


    }

    List<String> dfs() {
        //Keep track of visited nodes and the parents of visited nodes
        HashMap<BoardPosition, BoardPosition> parentNode = new HashMap<>();


        Stack<BoardPosition> stack = new Stack<>();

        //initially add the starting node
        parentNode.put(startPos, null);
        stack.add(startPos);


        while (!stack.isEmpty()) //check if anymore nodes to visit
        {
            BoardPosition currentPosition = stack.pop();

            if (currentPosition.equals(endPos)) {
                break; //we have reached the end position on the graph via the shortest path so stop searching
            }


            ArrayList<BoardPosition> nextPositions = spider.validMovePositions(currentPosition);
            for (BoardPosition adjacentPosition : nextPositions) {
                //if this adjacent nodes is one that hasn't been visited add it to the queue
                //also keep track of the adjacent node's parent (the current node)
                if (!parentNode.containsKey(adjacentPosition)) {
                    parentNode.put(adjacentPosition, currentPosition);
                    stack.add(adjacentPosition);
                }
            }
        }


        BoardPosition currentNode = endPos; //start at the end node
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

        System.out.println("ShortestPath DFS = " + shortestPath.trim());
        //Print out the shortest path found, excluding start position and including end position
        return Arrays.asList(shortestPath.trim().split(" "));
    }

}