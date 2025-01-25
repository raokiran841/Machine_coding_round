package g2048;

import java.io.IOException;
import java.util.Scanner;

class Game{
    public static void main(String[] args) throws IOException {
        Board board = new Board(4);
        //board.displayBoard();
        Scanner sc = new Scanner(System.in);
        while(true){
            board.randomBox();
            board.displayBoard();
            System.out.println("Enter left(a), right(d), up(w) or down(s) arrow key to merge..");
            String ch = sc.next();
            board.merge(ch);

            if(board.checkforwin()) break;
        }

        sc.close();
        
    }
}