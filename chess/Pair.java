package chess;

public class Pair {
    private char x;
    private int y;
    public char getX() {
        return x;
    }
    public void setX(char x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Pair(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Pair getPair(String inp){
        char x = inp.charAt(0);
        int y = Integer.parseInt(inp.charAt(1)+"");
        return new Pair(x, y);
    }
}
