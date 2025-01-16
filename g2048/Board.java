package g2048;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Board {
    private final int[][] cell;
    private final int size;
    private final Set<Integer> emptyCells;

    public Board(int size){
        this.size = size;
        this.cell = new int[size][size];
        this.emptyCells = new HashSet<>();
        for(int i=0; i<size*size; i++){
            emptyCells.add(i);
        }
    }

    public void displayBoard(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(" "+cell[i][j]+" |");
            }
            System.out.println();
        }
    }

    public void randomBox(){
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(cell[i][j] == 0){
                    list.add(i*4 + j);
                }
            }
        }
        int nextInt = random.nextInt(list.size());
        Integer val = list.get(nextInt);
        int i = val/size;
        int j = val%size;
        cell[i][j] = 2;
    }

    private void mergeDirUp(){
        for(int i=0; i<size; i++){
            int x = 0, y=0;
            while(x < size){
                if(cell[x][i] != 0){
                    cell[y][i] = cell[x][i];
                    if(x != y) cell[x][i] = 0;
                    y++;
                }
                x++;
            }
            
            int a=1, b=0;
            while(a<size){
                if(cell[b][i] == cell[a][i]){
                    cell[b][i] += cell[a][i];
                    cell[a][i] = 0;
                    b++;
                } else if(cell[b][i] == 0){
                    cell[b][i] += cell[a][i];
                    cell[a][i] = 0;
                } else {
                    b++;
                    cell[b][i] = cell[a][i];
                    if(a != b) cell[a][i] = 0;
                }
                a++;
            }

            int idx = 0;
            while(idx < size){
                if(cell[idx][i] == 0) emptyCells.add(i*4 + idx);
                else emptyCells.remove(i*4 + idx);
                idx++;
            }
        }
    }

    private void mergeDirDown(){
        for(int i=0; i<size; i++){
            int x = size-1, y=size-1;
            while(x >= 0){
                if(cell[x][i] != 0){
                    cell[y][i] = cell[x][i];
                    if(x != y) cell[x][i] = 0;
                    y--;
                }
                x--;
            }
            
            int a=size-2, b=size-1;
            while(a >= 0){
                if(cell[b][i] == cell[a][i]){
                    cell[b][i] += cell[a][i];
                    cell[a][i] = 0;
                    b--;
                } else if(cell[b][i] == 0){
                    cell[b][i] += cell[a][i];
                    cell[a][i] = 0;
                } else {
                    b--;
                    cell[b][i] = cell[a][i];
                    if(a != b) cell[a][i] = 0;
                }
                a--;
            }

            int idx = 0;
            while(idx < size){
                if(cell[idx][i] == 0) emptyCells.add(i*4 + idx);
                else emptyCells.remove(i*4 + idx);
                idx++;
            }
        }
    }

    private void mergeDirRight(){
        for(int i=0; i<size; i++){
            int x = size-1, y=size-1;
            while(x >= 0){
                if(cell[i][x] != 0){
                    cell[i][y] = cell[i][x];
                    if(x != y) cell[i][x] = 0;
                    y--;
                }
                x--;
            }
            
            int a=size-2, b=size-1;
            while(a >= 0){
                if(cell[i][b] == cell[i][a]){
                    cell[i][b] += cell[i][a];
                    cell[i][a] = 0;
                    b--;
                } else if(cell[i][b] == 0){
                    cell[i][b] += cell[i][a];
                    cell[i][a] = 0;
                } else {
                    b--;
                    cell[i][b] = cell[i][a];
                    if(a != b) cell[i][a] = 0;
                }
                a--;
            }

            int idx = 0;
            for(int num: cell[i]){
                if(num == 0) emptyCells.add(i*4 + idx);
                else emptyCells.remove(i*4 + idx);
                idx++;
            }
        }
    }

    private void mergeDirLeft(){
        for(int i=0; i<size; i++){
            int x = 0, y=0;
            while(x < size){
                if(cell[i][x] != 0){
                    cell[i][y] = cell[i][x];
                    if(x != y) cell[i][x] = 0;
                    y++;
                }
                x++;
            }
            
            int a=1, b=0;
            while(a<size){
                if(cell[i][b] == cell[i][a]){
                    cell[i][b] += cell[i][a];
                    cell[i][a] = 0;
                    b++;
                } else if(cell[i][b] == 0){
                    cell[i][b] += cell[i][a];
                    cell[i][a] = 0;
                } else {
                    b++;
                    cell[i][b] = cell[i][a];
                    if(a != b) cell[i][a] = 0;
                }
                a++;
            }

            int idx = 0;
            for(int num: cell[i]){
                if(num == 0) emptyCells.add(i*4 + idx);
                else emptyCells.remove(i*4 + idx);
                idx++;
            }
        }
    }

    public void merge(String ch) {
        switch (ch) {
            case "w" -> {// Up arrow
                mergeDirUp();
            }
            case "s" -> {// Down arrow
                mergeDirDown();
            }
            case "d" -> { // Right arrow 
                mergeDirRight();
            }
            case "a" -> { // Left arrow 
                mergeDirLeft();
            }
            default -> System.out.println("Unknown key was pressed");
        }
    }

    public boolean checkforwin() {
        for(int[] row: cell){
            for(int num: row){
                if(num == 2048){
                    System.out.println("Won");
                    displayBoard();
                    return true;
                }
            }
        }
        return false;
    }
}
