package snakeladder;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int snakeHead;
    private int snakeTail;
    private int ladderStart;
    private int ladderEnd;
    private List<Player> players = new ArrayList<>();
    private int number;
    
    public Cell(int number) {
        this.number = number;
    }

    public int getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(int snakeHead) {
        this.snakeHead = snakeHead;
    }

    public int getSnakeTail() {
        return snakeTail;
    }

    public void setSnakeTail(int snakeTail) {
        this.snakeTail = snakeTail;
    }

    public int getLadderStart() {
        return ladderStart;
    }

    public void setLadderStart(int ladderStart) {
        this.ladderStart = ladderStart;
    }

    public int getLadderEnd() {
        return ladderEnd;
    }

    public void setLadderEnd(int ladderEnd) {
        this.ladderEnd = ladderEnd;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    
    
}
