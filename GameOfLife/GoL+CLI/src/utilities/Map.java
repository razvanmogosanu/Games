package utilities;

public class Map {
    private Cell[][] Grid;

    protected Map(int length, int wide, double rate) {
        Grid = new Cell[length][wide];
        for(int i = 0; i < length; i++)
            for(int j = 0 ; j < wide; j++)
                Grid[i][j] = new Cell(rate);
    }

    protected void printMap() {

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[36m";

        /** TOP BORDER*/
        for(int k = 0; k < Grid[0].length + 4; k++)
            System.out.print(ANSI_RED + "\\");
        System.out.println(ANSI_RESET);

        /** MAP + SIDE BORDERS*/
        for(int i = 0; i < Grid.length; i++){
            System.out.print(ANSI_RED + "||" + ANSI_RESET);
            for(int j = 0; j < Grid[i].length; j++){
                if(Grid[i][j].isAlive())
                    System.out.print(ANSI_GREEN + "%" + ANSI_RESET);
                else
                    System.out.print(ANSI_BLUE + "." + ANSI_RESET);
            }
            System.out.print(ANSI_RED + "||" + ANSI_RESET);
            System.out.println();
        }

        /***BOTTOM BORDER*/
        for(int k = 0; k < Grid[0].length + 4; k++)
            System.out.print(ANSI_RED + "\\" + ANSI_RESET);
        System.out.println();

    }

    public Cell[][] getGrid() {
        return Grid;
    }
}
