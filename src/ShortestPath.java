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
        //to hold <parent, child>
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

        System.out.println("ShortestPath BFS(next step) from  " + startPos + " to " + endPos + " = " + shortestPath.trim());
//        System.out.println("-------------------------------");
//        for(BoardPosition key:parentNode.keySet()){
//            System.out.println(key +"-->"+ parentNode.get(key) );
//        }
        // the shortest path found, excluding start position and including end position
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
                break;
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

    private double distance(BoardPosition spider, BoardPosition ant, int h) {
        switch (h) {
            case 1:
                return distance1(spider, ant);
            case 2:
                return distance2(spider, ant);
            case 3: {
                return ((distance1(spider, ant)) + (distance2(spider, ant))) / 2;
            }
            default:
                throw new RuntimeException("Invalid heuristic option : " + h);
        }
    }

    /**
     * computes Euclidian distance between two nodes
     *
     * @param spiderPos
     * @param antPos
     * @return
     */
    private double distance1(BoardPosition spiderPos, BoardPosition antPos) {
        int x = Math.abs(spiderPos.x - antPos.x);
        int y = Math.abs(spiderPos.y - antPos.y);
        return Math.sqrt((x * x) + (y * y));
    }

    private double distance2(BoardPosition spiderPos, BoardPosition antPos) {
        int x = Math.abs(spiderPos.x - antPos.x);
        int y = Math.abs(spiderPos.y - antPos.y);
        if (y > 0) {
            return x / y;
        }
        return x;
    }

    List<String> aStarSearch(int heuristic) {
        HashMap<BoardPosition, BoardPosition> parentNode = new HashMap<>();
        Set<BoardPosition> explored = new HashSet<>();

        //override compare method
        PriorityQueue<BoardPosition> queue = new PriorityQueue<>(20,
                (i, j) -> {
                    if (i.f_scores > j.f_scores) {
                        return 1;
                    } else if (i.f_scores < j.f_scores) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
        );

        //cost from start
        startPos.g_scores = 0;

        queue.add(startPos);

        boolean found = false;

        while ((!queue.isEmpty()) && (!found)) {

            //the node in having the lowest f_score value
            BoardPosition current = queue.poll();

            explored.add(current);

            //goal found
            if ((current.x == endPos.x) && (current.y == endPos.y)) {
                found = true;
            }

            //check every child of current node
            ArrayList<BoardPosition> nextPositions = spider.validMovePositions(current);
            for (BoardPosition child : nextPositions) {

                double temp_f_scores = depthOfNode(child, parentNode) + distance(child, endPos, heuristic);// COST + heuristic function

                /*if child node has been evaluated and the newer f_score is higher, skip*/
                if ((explored.contains(child))) {
                    continue;
                }

                /*else if child node is not in queue */

                else if ((!queue.contains(child))) {

                    parentNode.put(child, current);
                    //child.g_scores = temp_g_scores;
                    child.f_scores = temp_f_scores;

                    if (queue.contains(child)) {
                        queue.remove(child);
                    }

                    queue.add(child);

                }

            }

        }//end of while
        List<String> path = new ArrayList<>();
        for (BoardPosition node = endPos; node != null; node = parentNode.get(node)) {
            path.add(node.toString());
        }

        System.out.print("A* path = ");
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != (path.size() - 1)) {
                System.out.print(" -> ");
            }
        }
        path.remove(0);
        return path;

    }

    public int depthOfNode(BoardPosition child, HashMap<BoardPosition, BoardPosition> parentNode) {
        int depth = 0;
        while (parentNode.get(child) != null) {//if the parent is null top of the node
            depth++;
            child = parentNode.get(child);
        }

        return depth;
    }

}