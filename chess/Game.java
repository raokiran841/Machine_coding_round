package chess;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Game {
    private static Deque<Player> players = new ArrayDeque<>();
    private static Board board = null;
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
        game.start();
    }

    private void initGame(){
        System.out.println("==== Welcome to chess ====");
        board = new Board();
        System.out.println("Enter player(White) name:");
        String name = sc.nextLine();
        players.add(new Player(name, Color.WHITE, board.getBoard()));
        System.out.println("Enter player(Black) name:");
        name = sc.nextLine();
        players.add(new Player(name, Color.BLACK, board.getBoard()));
    }

    private void start(){
        System.out.println("------- lets start --------");
        while(true){
            board.displayBoard();
            Player currPlayer = players.remove();
            System.out.println(currPlayer.getName()+"'s turn, enter move(Ex: g0 f0)");
            String start_end = sc.nextLine();
            String[] arr = start_end.split(" ");
            Pair[] pairs = Move.getMove(arr);
            if(validateMove(pairs[0], pairs[1], currPlayer)){
                players.add(currPlayer);
            } else {
                players.addFirst(currPlayer);
                System.err.println("Invalid move, try again");
            }
        }
    }

    private boolean validateMove(Pair start, Pair end, Player currPlayer){
        int sx = start.getX() - 'a';
        int sy = start.getY();
        int ex = end.getX() - 'a';
        int ey = end.getY();
        // if start and end are same
        if(sx == ex && sy == ey) return false;
        // move out of board
        if(start.getX() > 'h' || end.getX() > 'h' || 
            sy > 7 || sy < 0 ||
            ey > 7 || ey < 0) return false;

        // piece is null -> start pos does not have a piece
        Piece pieceAtMove = board.getPieceAtPos(start);
        if(pieceAtMove == null) return false;

        // end pos has curr player piece
        if(board.getPieceAtPos(end) != null && board.getPieceAtPos(end).getColor() == currPlayer.getColor()) return false;
        
        // other piece in between start and end (piece-wise)
        int diffx = Math.abs(sx - ex);
        int diffy = Math.abs(sy - ey);
        switch (pieceAtMove.getSymbol()) {
            case PAWN ->  {
                // if 1st time move 2 pos or else one position
                if(pieceAtMove.getColor() == Color.WHITE){
                    if(start.getX() <= end.getX()) return false; // pawn moved backwords or on same level
                    else if(start.getX() - 'a' == 6){
                        // 2 moves or 1 move
                        if(diffx == 2 && diffy != 0) return false; // going forward by 2 but not straight
                        else if(diffy == 0 && diffx == 2 && board.getBoard()[sx - 1][sy] != null){
                            return false; // going fwd 2 steps but something is in b/w
                        }
                    } else {
                        if(diffy == 1 && board.getBoard()[ex][ey] == null) return false; // cant go diagonally on empty cell
                        if(diffx > 1) return false;
                        if(diffy > 1) return false;
                    }
                } else {
                    if(end.getX() <= start.getX()) return false; // pawn moved backwords or on same level
                    else if(start.getX() - 'a' == 1){
                        // 2 moves or 1 move
                        if(diffx == 2 && diffy != 0) return false; // going forward by 2 but not straight
                        else if(diffy == 0 && diffx == 2 && board.getBoard()[sx + 1][sy] != null){
                            return false; // going fwd 2 steps but something is in b/w
                        }
                    } else {
                        if(diffy == 1 && board.getBoard()[ex][ey] == null) return false; // cant go diagonally on empty cell
                        if(diffx > 1) return false;
                        if(diffy > 1) return false;
                    }
                }
            }
            case ROOK ->  {
                if(!validRook(diffx, diffy, sx, sy, ex, ey)) return false;
            }
            case BISHOP ->  {
                if(!validBishop(diffx, diffy, sx, sy, ex, ey)) return false;
            }
            case KNIGHT -> {
                if((diffx == 1 && diffy == 2) || (diffx == 2 && diffy == 1)){
                    // valid knight move
                } else return false;
            }
            case KING -> {
                // one move in any direction
                if( (diffx == 0 && diffy == 1) || // up or down
                    (diffx == 1 && diffy == 0) || // left or right
                    (diffx == diffy && diffx == 1) // diagonally
                    ){// valid king move
                        break;
                    } else return false;
            }
            case QUEEN -> {
                // both rook and bishop validations
                if(!validBishop(diffx, diffy, sx, sy, ex, ey) && !validRook(diffx, diffy, sx, sy, ex, ey)) return false;
            }
            default -> throw new AssertionError();
        }
        // king is at check and not clearning the check
        // not implementing as this will be out of scope + its a manual game not a computer bot.
        
        board.updateBoard(start, end);
        return true;
    }

    private boolean validRook(int diffx, int diffy, int sx, int sy, int ex, int ey){
        if((diffx == 0 && diffy != 0) || (diffy == 0 && diffx != 0)){
            // valid move straight path
            if(diffx == 0){
                // can move up or down
                boolean movingUp = sy < ey;
                for(int i=1; i<diffy; i++){
                    if(movingUp && board.getBoard()[sx][sy-i] != null){
                        return false;// but something is in b/w
                    } else if(!movingUp && board.getBoard()[sx][sy+i] != null){
                        return false;
                    }
                }
            } else if(diffy == 0){
                // can move left or right
                boolean movingRight = (sx) < (ex);
                for(int i=1; i<diffx; i++){
                    if(movingRight && board.getBoard()[sx + i][sy] != null){
                        return false;// but something is in b/w
                    } else if(!movingRight && board.getBoard()[sx - i][sy] != null){
                        return false;
                    }
                }
            }
        } else return false; // not a straight path
        return true;
    }

    private boolean validBishop(int diffx, int diffy, int sx, int sy, int ex, int ey){
        if(diffx != diffy) return false; // not a diaginal path
        else {
            if(sx < ex){
                // moving down
                boolean downRight = sy < ey;
                for(int i=1; i<diffx; i++){
                    if(downRight && board.getBoard()[sx + i][sy + i] != null){
                        return false;
                    } else if(!downRight && board.getBoard()[sx + i][sy - i] != null){
                        return false;
                    }
                }
            } else {
                // moving up
                boolean upRight = sy < ey;
                for(int i=1; i<diffx; i++){
                    if(upRight && board.getBoard()[sx - i][sy + i] != null){
                        return false;
                    } else if(!upRight && board.getBoard()[sx - i][sy - i] != null){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
