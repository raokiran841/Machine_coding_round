package chess;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Board() {
        initBoard();
    }

    private void displayBoard(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(board[i][j] != null){
                    System.out.print(board[i][j].getPieceName()+" ");
                } else {
                    System.out.print("-- ");
                }
            }
            System.out.println();
        }
    }

    private void initBoard(){
        // PAWNS
        for(int i=0; i<8; i++){
            board[6][i] = new Piece(Color.WHITE, Symbol.PAWN);
            board[1][i] = new Piece(Color.BLACK, Symbol.PAWN);
        }
        // ROOK
        board[0][0] = new Piece(Color.BLACK, Symbol.ROOK);
        board[0][7] = new Piece(Color.BLACK, Symbol.ROOK);
        board[7][0] = new Piece(Color.WHITE, Symbol.ROOK);
        board[7][7] = new Piece(Color.WHITE, Symbol.ROOK);

        // KNIGHT
        board[0][1] = new Piece(Color.BLACK, Symbol.KNIGHT);
        board[0][6] = new Piece(Color.BLACK, Symbol.KNIGHT);
        board[7][1] = new Piece(Color.WHITE, Symbol.KNIGHT);
        board[7][6] = new Piece(Color.WHITE, Symbol.KNIGHT);

        // BISHOP
        board[0][2] = new Piece(Color.BLACK, Symbol.BISHOP);
        board[0][5] = new Piece(Color.BLACK, Symbol.BISHOP);
        board[7][2] = new Piece(Color.WHITE, Symbol.BISHOP);
        board[7][5] = new Piece(Color.WHITE, Symbol.BISHOP);

        // QUEEN and KING
        board[0][3] = new Piece(Color.BLACK, Symbol.QUEEN);
        board[0][4] = new Piece(Color.BLACK, Symbol.KING);
        board[7][3] = new Piece(Color.WHITE, Symbol.QUEEN);
        board[7][4] = new Piece(Color.WHITE, Symbol.KING);
        displayBoard();
    }
    

}
