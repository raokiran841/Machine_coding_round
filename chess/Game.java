package chess;

import java.awt.Paint;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private static Queue<Player> players = new ArrayDeque<>();
    private static Board board = null;
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
        game.start();
    }

    private void initGame(){
        System.out.println("==== Welcome to chess ====");
        System.out.println("Enter player(White) name:");
        String name = sc.nextLine();
        players.add(new Player(name, Color.WHITE));
        System.out.println("Enter player(Black) name:");
        name = sc.nextLine();
        players.add(new Player(name, Color.BLACK));
        board = new Board();
    }

    private void start(){
        while(true){
            Player currPlayer = players.remove();
            String start_end = sc.nextLine();
            String[] arr = start_end.split(" ");
            Pair[] pairs = Move.getMove(arr);
        }
    }
}
