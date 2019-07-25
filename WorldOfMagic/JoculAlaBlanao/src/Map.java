
import units.Unit;
import utilities.Coordinate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Map {
    private Cell [][] cellGrid = new Cell[10][20];
    private static final Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    Map() {
        initializeCellGrid();
        initializeObjectGrid();
    }

    private void initializeObjectGrid() {
        for(int i = 0; i < cellGrid.length; i++)
            for (int j = 0; j < cellGrid[i].length; j++){
                cellGrid[i][j].setUnit(null);
            }
    }
    private void initializeCellGrid() {
        for(int i = 0; i < cellGrid.length; i++){
            for(int j = 0; j < cellGrid[i].length; j++) {
                cellGrid[i][j] = new Cell();
                if (j <= 9) {
                    String value = j + i * 10 + " ";
                    if (j + i * 10 < 10) {
                        value += " ";
                    }
                    cellGrid[i][j].setValue(value);
                }
                else {
                    cellGrid[i][j].setValue("X  ");
                }
            }
        }
    }
    void showCellGrid() {
        doExteriorWalls(false);

        for(int i = 0; i < cellGrid.length; i++){
            System.out.print(ANSI_GREEN + "// " + ANSI_RESET);
            for(int j = 0; j < cellGrid[i].length; j++){
                if(cellGrid[i][j].getValue().contains("X")) {
                    System.out.print(ANSI_RED + cellGrid[i][j].getValue() + ANSI_RESET);
                }
                else
                    System.out.print(cellGrid[i][j].getValue());
            }
            System.out.println(ANSI_GREEN + "//" + ANSI_RESET);
        }

        doExteriorWalls(false);
    }
    void showObjectGrid(){
        doExteriorWalls(true);

        for(int i = 0; i < cellGrid.length; i++){
            System.out.print(ANSI_GREEN + "// " + ANSI_RESET);
            for(int j = 0; j < cellGrid[i].length; j++){
                if(cellGrid[i][j].getUnit() != null){
                    if(cellGrid[i][j].getUnit().getSide())
                        System.out.print(ANSI_BLUE + cellGrid[i][j].getUnit().getUnitName().substring(0,1) + " " + ANSI_RESET);
                    else
                        System.out.print(ANSI_RED + cellGrid[i][j].getUnit().getUnitName().substring(0,1) + " " + ANSI_RESET);
                }
                else
                    System.out.print(". ");
                if(j == 9)
                    System.out.print(ANSI_GREEN + "|| " + ANSI_RESET);
            }
            System.out.println(ANSI_GREEN + "//" + ANSI_RESET);
        }

        doExteriorWalls(true);
    }
    private void doExteriorWalls(boolean length){
        int n = (length) ? cellGrid[0].length + 4 : cellGrid[0].length + 12;
        for(int i = 0; i < n; i++){
            System.out.print(ANSI_GREEN + "//" + ANSI_RESET);
        }
        System.out.println();
    }
    void doAlliesPosition(Unit unit){
        System.out.println("Where do you want to put your " + unit.getUnitName() + "?");
        doAllyPosition(unit);
    }
    private void doAllyPosition(Unit unit){
        while (true) {
            int position;
            Coordinate pos = new Coordinate(0,0);
            while(true) {
                try {
                    position = scan.nextInt();
                    pos.setX(position / 10);
                    pos.setY(position % 10);
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Try again. (Incorrect input: a number in range 0 - 99 is required)");
                    scan.nextLine();
                }
            }
            if (position > 100) {
                System.out.println("Please type a number between 0 and 99");
                continue;
            }
            Cell cell = cellGrid[pos.getX()][pos.getY()];
            if (cell.getUnit() != null) {
                System.out.println("You already have a " + cell.getUnit().getUnitName() + " on this position");
            } else {
                cell.setUnit(unit);
                break;
            }
        }
        showCellGrid();
    }

    void moveUp(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);

        if(pos.getX() - 1 >= 0 && cellGrid[pos.getX() - 1][pos.getY()].getUnit() == null) {
            cellGrid[pos.getX() - 1][pos.getY()].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
        }
    }
    void moveDown(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getX() + 1 <= 9 && cellGrid[pos.getX() + 1][pos.getY()].getUnit() == null){
            cellGrid[pos.getX() + 1][pos.getY()].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
        }
    }

    void moveLeft(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getY() - 1 >= 0 && cellGrid[pos.getX()][pos.getY() - 1].getUnit() == null) {
            cellGrid[pos.getX()][pos.getY() - 1].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
        }
    }

    void moveRight(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getY() + 1 <= 19 && cellGrid[pos.getX()][pos.getY() + 1].getUnit() == null) {
            cellGrid[pos.getX()][pos.getY() + 1].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
        }
    }

    private void getUnitCoordinates(Coordinate pos, Unit unit){
        for(int i = 0; i < cellGrid.length; i++) {
            for(int j = 0; j < cellGrid[i].length; j++) {
                if(cellGrid[i][j].getUnit() != null && cellGrid[i][j].getUnit().getId() == unit.getId()) {
                    pos.setX(i);
                    pos.setY(j);
                    break;
                }
            }
        }
    }
    void doEnemiesPosition(int level, Unit unit) {
        if (level <= 15) {
            doEnemyPositionEasy(unit);
        }
        if(level > 15 && level <= 25){
            doEnemyPositionMedium("eM");       //MAGE
            doEnemyPositionMedium("eN");       //NINJA
            doEnemyPositionMedium("eW");       //WARRIOR
        }
        if(level > 25 && level <= 30) {
            doEnemyPositionHard("eM");       //MAGE
            doEnemyPositionHard("eN");       //NINJA
            doEnemyPositionHard("eW");       //WARRIOR
        }
    }
    private void doEnemyPositionEasy(Unit unit) {
        while (true) {
            Coordinate pos = new Coordinate(rand.nextInt(10), rand.nextInt(10) + 10);

            if (cellGrid[pos.getX()][pos.getY()].getValue().contains("X")) {
                cellGrid[pos.getX()][pos.getY()].setUnit(unit);
                break;
            }
        }
    }
    private void doEnemyPositionMedium(String unit) {
//        int position;
//        while(true) {
//            position = rand.nextInt(9) + 1;
//            int xPos = (position - 1) / 3;
//            int yPos = (position - 1) % 3;
//
//            if(unit.equals("eW") && ((yPos + 3) != 5) && objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[xPos][yPos+ 3] = unit;
//                break;
//            }
//            if(unit.equals("eM") && (yPos + 3) != 3 &&  objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[(position - 1) / 3][(position - 1) % 3 + 3] = unit;
//                break;
//            }
//            if(unit.equals("eN") && (yPos + 3) != 3 &&  objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[xPos][yPos + 3] = unit;
//                break;
//            }
//        }
    }
    private void doEnemyPositionHard(String unit) {
//        int position;
//        while(true) {
//            position = rand.nextInt(9) + 1;
//            int xPos = (position - 1) / 3;
//            int yPos = (position - 1) % 3;
//
//            if(unit.equals("eW") && ((yPos + 3) == 3) && objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[xPos][yPos + 3] = unit;
//                break;
//            }
//
//            if(unit.equals("eM") && (yPos + 3) == 5  && objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[xPos][yPos + 3] = unit;
//                break;
//            }
//
//            if(unit.equals("eN") && (yPos + 3) != 3 && objectGrid[xPos][yPos + 3].equals("X")) {
//                objectGrid[xPos][yPos + 3] = unit;
//                break;
//            }
//        }
    }
}
