import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 2019-02-09.
 */
public class Game {

    public static void main(String[] args) {

        //get value of userInput from user
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.print("Enter a number:\n 1= BFS,\n 2 = DFS, \n 3 = A* Search,\n Quit: anything else:  ");
        int userInput = reader.nextInt();
        //reader.close();

        int userInputH = 0;
        if (3 == userInput) {
           // Scanner readerH = new Scanner(System.in);  // Reading from System.in
            System.out.print("Enter a h number:\n 1 - Euclidean,\n 2 - Custom , \n 3 - Average,\n Quit: anything else:  ");
            userInputH = reader.nextInt();
            reader.close();
        }

        //uncomment these two lines for testing
        //next 4 lines are only for testing
//        String spiderLoc = "11";
//        String antLoc = "81";
//        BoardPosition antPos = new BoardPosition(antLoc);
//        BoardPosition spiderPos = new BoardPosition(spiderLoc);

        //Ant and Spider get initiated at random positions
        //comment these two lines for testing
        BoardPosition antPos = new BoardPosition(); //antPos gets x,y as their attributes
        BoardPosition spiderPos = new BoardPosition();

        GameBoard gameBoard = new GameBoard(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        Spider spider = new Spider();
        List<BoardPosition> finalPath = new ArrayList<>(); //this holds the final path that will lead to catching the ant

        //do until the spider catches the ant
        while (!gameBoard.hasSpiderCaughtAnt()) {
            System.out.println("=========================================");
            System.out.println("Spider location = " + spiderPos);
            System.out.println("Ant location = " + antPos);
            gameBoard.print();

            ShortestPath shortestPath = new ShortestPath(spiderPos, antPos, spider);
            List<String> shortestPaths;

            switch (userInput) {
                case 1: {
                    System.out.println("Doing BFS");
                    shortestPaths = shortestPath.bfs();
                    break;
                }
                case 2: {
                    System.out.println("Doing DFS");
                    shortestPaths = shortestPath.dfs();
                    break;
                }
                case 3: {
                    System.out.println("Doing A* search");

                    shortestPaths = shortestPath.aStarSearch(userInputH);
                    break;
                }
                default:
                    throw new RuntimeException("A* not implemented");
            }

            //List<String> spiderLegalMovesString = spider.validMoves(spiderPos); //get all legal moves of spider
            //String spiderNextMove = spider.nextMove(shortestPaths, spiderLegalMovesString);

            //System.out.println("\n"+shortestPaths);//for debug purpose
            //System.out.println("\nSpiders next move = " + spiderNextMove);
//
//            spiderPos = new BoardPosition(spiderNextMove);//debug
//            System.out.println(spiderPos);//debug
            spiderPos = new BoardPosition(shortestPaths.get(0));
            finalPath.add(spiderPos);
            System.out.println("\nSpider next move = " + spiderPos);
            gameBoard.updateLocations(antPos.x, antPos.y, spiderPos.x, spiderPos.y);
            //get new boardView with new location of spider and ant
            if (!gameBoard.hasSpiderCaughtAnt()) {
                antPos = gameBoard.antNextMove();

            }

            gameBoard.updateLocations(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        }
        System.out.println("=========================================");
        System.out.println("Spider caught ANT");
        System.out.println("Spider location = " + spiderPos);
        System.out.println("Ant location = " + antPos);


        System.out.print("final path from source is:  ");

        StringBuilder pathString = new StringBuilder();
        pathString.append(finalPath.get(0));
        finalPath.remove(0);
        for (BoardPosition position : finalPath) {
            pathString.append(" -> ").append(position);
        }
        System.out.println(pathString);
    }//end of main

}//end of class Game
