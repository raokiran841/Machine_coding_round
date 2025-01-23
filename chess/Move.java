package chess;

public class Move {
    private Pair start;
    private Pair end;
    private Piece piece;
    public Move(Pair start, Pair end, Piece piece) {
        this.start = start;
        this.end = end;
        this.piece = piece;
    }

    public static Pair[] getMove(String[] moves){
        Pair start = Pair.getPair(moves[0]);
        Pair end = Pair.getPair(moves[1]);
        return new Pair[]{start, end};
    }

}
