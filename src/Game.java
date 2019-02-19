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

        //Ant and Spider get initiated at random positions
        BoardPosition antPos = new BoardPosition(); //antPos gets x,y as their attributes
        BoardPosition spiderPos = new BoardPosition();

        GameBoard gameBoard = new GameBoard(antPos.x, antPos.y, spiderPos.x, spiderPos.y);
        gameBoard.print();
        Spider spider = new Spider();
        List<BoardPosition> finalPath = new ArrayList<>(); //this holds the final path that will lead to catching the ant

        //do until the spider catches the ant
        while (!gameBoard.hasSpiderCaughtAnt()) {
            System.out.println("=========================================");
            System.out.println("Spider location = " + spiderPos + ", Ant location = " + antPos);

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

            spiderPos = new BoardPosition(shortestPaths.get(0));
            finalPath.add(spiderPos);
            System.out.println("\nSpider next move = " + spiderPos);
            gameBoard.updateLocations(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

            //get new boardView with new location of spider and ant
            if (!gameBoard.hasSpiderCaughtAnt()) {
                antPos = gameBoard.antNextMove();

            }
            gameBoard.print();
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
