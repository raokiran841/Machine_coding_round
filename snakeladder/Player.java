package snakeladder;

public class Player {
    private String name;
    private int id;
    private int pos;
    public Player(String name, int id, int pos) {
        this.name = name;
        this.id = id;
        this.pos = pos;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }
}
