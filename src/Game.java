import java.util.List;

/**
 * Created by abhinav on 2019-02-09.
 */
public class Game {

    public static void main(String[] args) {
        
        BoardPosition antPos = new BoardPosition();
        BoardPosition spiderPos = new BoardPosition();

        GameBoard gameBoard = new GameBoard(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        Spider spider = new Spider();

        //do until the spider catches the ant
        while (!gameBoard.hasSpiderCaughtAnt()) {
            System.out.println("=========================================");
            System.out.println("Spider location = " + spiderPos);
            System.out.println("Ant location = " + antPos);
            gameBoard.print();

            ShortestPath shortestPath = new ShortestPath(spiderPos, antPos, spider);
            List<String> shortestPaths = shortestPath.bfs();

            List<String> spiderLegalMovesString = spider.validMoves(spiderPos);
            String spiderNextMove = spider.nextMove(shortestPaths, spiderLegalMovesString);
            System.out.println("\nSpiders next move = " + spiderNextMove);

            spiderPos = new BoardPosition(spiderNextMove);

            //get new boardView with new location of spider and ant
            antPos = gameBoard.antNextMove();
            gameBoard.updateLocations(antPos.x, antPos.y, spiderPos.x, spiderPos.y);

        }
        System.out.println("=========================================");
        System.out.println("Spider caught ANT");
        System.out.println("Spider location = " + spiderPos);
        System.out.println("Ant location = " + antPos);
    }

}
