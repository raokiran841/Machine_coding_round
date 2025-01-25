package tiktactoe;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private char[][] board = new char[3][3];
    Queue<Player> queue = new ArrayDeque<>();
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Game().initGame();
    }

    private void initGame(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j] = (char) ('0' + (3*i + j + 1));
            }
        }
        System.out.println("Enter player for X:");
        String name = sc.nextLine();
        queue.add(new Player(name, 'X'));
        System.out.println("Enter player for O:");
        name = sc.nextLine();
        queue.add(new Player(name, 'O'));
        displayBoard();
        startGame();
    }

    private void startGame(){
        Player currPlayer = null;
        while(true){
            currPlayer = queue.remove();
            System.out.println("Enter position(1-9)");
            int val = sc.nextInt();
            val--;
            int i = val/3;
            int j = val%3;
            board[i][j] = currPlayer.getPiece();
            displayBoard();
            if(checkWin(currPlayer.getPiece())) break;
            queue.add(currPlayer);
        }
        System.out.println("Player: "+currPlayer.getName()+" WON");
    }

    private boolean checkWin(char ch){
        // horizonal and vertical check
        for(int i=0; i<3; i++){
            if(board[i][0] == ch && board[i][1] == ch && board[i][2] == ch) return true;
            if(board[0][i] ==ch && board[1][i] == ch && board[2][i] == ch) return true;
        }
        // diagonal check
        if(board[0][0] == ch && board[1][1] == ch && board[2][2] == ch) return true;
        if(board[0][2] == ch && board[1][1] == ch && board[2][0] == ch) return true;
        return false;
    }

    private void displayBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(board[i][j] +"|");
            }
            System.out.println();
        }
    }
}
