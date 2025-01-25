package chess;

public enum Symbol {
    PAWN('P'), ROOK('R'), KNIGHT('N'), BISHOP('B'), KING('K'), QUEEN('Q');

    private final char ch;
    Symbol(char ch){
        this.ch = ch;
    }
    public char getCh() {
        return ch;
    }
}
