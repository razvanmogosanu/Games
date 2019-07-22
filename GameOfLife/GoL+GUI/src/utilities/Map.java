package utilities;

public class Map {
    private Cell[][] Grid;

    protected Map(int length, int wide, double rate) {
        Grid = new Cell[length][wide];
        for(int i = 0; i < length; i++)
            for(int j = 0 ; j < wide; j++)
                Grid[i][j] = new Cell(rate);
    }

    public Cell[][] getGrid() {
        return Grid;
    }
}