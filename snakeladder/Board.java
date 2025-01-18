package snakeladder;

public class Board {
    private Cell[] cell;
    //private int size;

    public Cell[] getCell() {
        return cell;
    }

    public void setCell(Cell[] cell) {
        this.cell = cell;
    }

    public Board(int size) {
        this.cell = new Cell[size];
        for(int i=1; i<=size; i++){
            cell[i] = new Cell(i);
        }
    }
}
