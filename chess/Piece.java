package chess;

public class Piece {
    private Color color;
    private Symbol symbol;
    public Piece(Color color, Symbol symbol) {
        this.color = color;
        this.symbol = symbol;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getPieceName(){
        return this.color.getCh()+""+this.symbol.getCh();
    }
}
