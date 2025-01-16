package snakeladder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Game{

    // private Board board;
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();
    private final Queue<Player> players = new ArrayDeque<>();
    private String snakePairs = "";
    private String ladderPairs = "";

    public static void main(String[] args) {
       System.out.println("* Welcome *");
       new Game().init();
    }

    private void init(){

        // board = new Board(100);

        Properties config = new Properties();
        try(FileInputStream fis = new FileInputStream("/Users/kiran.rao/Documents/games/snakeladder/config.properties")){
            config.load(fis);
            snakePairs = config.getProperty("snakes");
            ladderPairs = config.getProperty("ladders");
        } catch(IOException ioe){
            System.err.println("Error: "+ ioe);
        }

        String[] sPairs = snakePairs.split(",");
        for(String pair: sPairs){
            String[] ht = pair.split(":");
            int head = Integer.parseInt(ht[0]);
            int tail = Integer.parseInt(ht[1]);
            snakes.put(head, tail);
        }

        if(!validateMap(snakes, "snake")){
            System.err.println("Invalid entries for snakes");
            return;
        }

        String[] lPairs = ladderPairs.split(",");
        for(String pair: lPairs){
            String[] ht = pair.split(":");
            int start = Integer.parseInt(ht[0]);
            int end = Integer.parseInt(ht[1]);
            ladders.put(start, end);
        }

        if(!validateMap(ladders, "ladder")){
            System.err.println("Invalid entries for ladders");
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("How many of you are playing?");
            int count = sc.nextInt();
            sc.nextLine();
            
            while(count --> 0){
                System.out.println("Enter player name:");
                String player = sc.nextLine();
                players.add(new Player(player, count, 0));
            }
        }

        start();
    }

    private boolean validateMap(Map<Integer, Integer> map, String type){
        if(type.equalsIgnoreCase("snake")){
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                Integer head = e.getKey();
                Integer tail = e.getValue();
                if(head < tail) return false;
            }
            return true;
        } else {
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                Integer start = e.getKey();
                Integer end = e.getValue();
                if(start > end) return false;
            }
            return true;
        }
    }

    private int rollDice(){
        Random random = new Random();
        int val = random.nextInt(6)+1;
        return val;
    }

    private void start(){
        while(true){
            Player currPlayer = players.remove();
            int move = rollDice();
            int pos = currPlayer.getPos();
            int totalMove = pos+move;
            String status = "";

            // check valid total move, should cross actual size 100
            if(totalMove > 100){
                System.out.println(currPlayer.getName()+" rolled a "+move+", cant move from "+pos);
                players.add(currPlayer);
                continue;
            }

            int finalPos = totalMove;

            // is snake ?
            if(snakes.containsKey(totalMove)){
                finalPos = snakes.get(totalMove);
                status = " - eaten by snake";
            }
            // is ladder ?
            if(ladders.containsKey(totalMove)){
                finalPos = ladders.get(totalMove);
                status = " + climed a ladder";
            }

            currPlayer.setPos(finalPos);

            System.out.println(currPlayer.getName()+" rolled a "+move+" and moved from "+pos+" to "+finalPos+status);

            // check if player won ?
            if(finalPos == 100){
                System.out.println(currPlayer.getName()+" wins the game");
                break;
            }

            players.add(currPlayer);
        }
    }

    
}