package chess;

public enum Color {
    WHITE('W'), BLACK('B');
    private final char ch;
    Color(char ch){
        this.ch = ch;
    }
    public char getCh() {
        return ch;
    }
}
