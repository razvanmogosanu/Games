package maputilities;

import units.Unit;

import java.util.*;

public class Map {
    private Cell[][] cellGrid = new Cell[10][20];
    private static final Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public Map() {
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

    public void showCellGrid() {
        doExteriorWalls(false);

        for(int i = 0; i < cellGrid.length; i++){
            System.out.print(ANSI_GREEN + "// " + ANSI_RESET);
            for(int j = 0; j < cellGrid[i].length; j++){
                if(j == cellGrid[i].length / 2)
                    System.out.print(ANSI_GREEN + "|| "  + ANSI_RESET);
                else
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
    public void showObjectGrid(Unit unit){
        doExteriorWalls(true);

        for(int i = 0; i < cellGrid.length; i++){
            System.out.print(ANSI_GREEN + "// " + ANSI_RESET);
            for(int j = 0; j < cellGrid[i].length; j++){
                if(cellGrid[i][j].getUnit() != null){
                    if(cellGrid[i][j].getUnit().getSide()){
                        if(unit != null && unit.equals(cellGrid[i][j].getUnit()))
                            System.out.print(ANSI_CYAN_BACKGROUND + cellGrid[i][j].getUnit().getUnitName().substring(0,1) + ANSI_RESET + " ");
                        else
                            System.out.print(ANSI_BLUE + cellGrid[i][j].getUnit().getUnitName().substring(0,1) + " " + ANSI_RESET);
                    }
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

    public void doAlliesPosition(Unit unit){
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

    public boolean moveUp(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);

        if(pos.getX() - 1 >= 0 && cellGrid[pos.getX() - 1][pos.getY()].getUnit() == null) {
            cellGrid[pos.getX() - 1][pos.getY()].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
            return true;
        }
        return false;
    }
    public boolean moveDown(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getX() + 1 <= 9 && cellGrid[pos.getX() + 1][pos.getY()].getUnit() == null){
            cellGrid[pos.getX() + 1][pos.getY()].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
            return true;
        }
        return false;
    }
    public boolean moveLeft(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getY() - 1 >= 0 && cellGrid[pos.getX()][pos.getY() - 1].getUnit() == null) {
            cellGrid[pos.getX()][pos.getY() - 1].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
            return true;
        }
        return false;
    }
    public boolean moveRight(Unit unit) {
        Coordinate pos = new Coordinate(0,0);
        getUnitCoordinates(pos, unit);
        if(pos.getY() + 1 <= 19 && cellGrid[pos.getX()][pos.getY() + 1].getUnit() == null) {
            cellGrid[pos.getX()][pos.getY() + 1].setUnit(unit);
            cellGrid[pos.getX()][pos.getY()].setUnit(null);
            return true;
        }
        return false;
    }

    public void moveHero(Map mapGrid, Unit unit) {
        System.out.println("Move the highlighted " + unit.getUnitName());
        System.out.println("             Type 1: UP\n" + "Type 2: LEFT" + "  Type 0: Stand  " + "Type 3: RIGHT\n" + "             Type 4: DOWN");
        System.out.println("Type 5: Heroes stats");
        loop:while (true) {
            String input = scan.next();
            boolean check = true;
            switch (input) {
                case "1": {
                    check = mapGrid.moveUp(unit);
                    if(check)
                        break loop;
                    break;
                }
                case "2": {
                    check = mapGrid.moveLeft(unit);
                    if(check)
                        break loop;
                    break;
                }
                case "3": {
                    check = mapGrid.moveRight(unit);
                    if(check)
                        break loop;
                    break;
                }
                case "4": {
                    check = mapGrid.moveDown(unit);
                    if(check)
                        break loop;
                    break;
                }
                case "5":
                    showUnitsStats();
                    break;
                case "0":
                    break loop;
                default:
                    System.out.println("Please type a number between 1 and 5");
                    break;
            }
            mapGrid.showObjectGrid(unit);
            if(!check)
                System.out.println("You can't go that way");
            System.out.println("Move the highlighted Hero");
            System.out.println("             Type 1: UP\n" + "Type 2: LEFT" + "  Type 0: Stand  " + "Type 3: RIGHT\n" + "             Type 4: DOWN");
            System.out.println("Type 5: Heroes stats");
        }
    }

    public void getUnitCoordinates(Coordinate pos, Unit unit){
        outerloop:
        for(int i = 0; i < cellGrid.length; i++) {
            for(int j = 0; j < cellGrid[i].length; j++) {
                if(cellGrid[i][j].getUnit() != null && unit != null && cellGrid[i][j].getUnit().getId() == unit.getId()) {
                    pos.setX(i);
                    pos.setY(j);
                    break outerloop;
                }
            }
        }
    }

    public void doEnemiesPosition(int level, Unit unit) {
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

    public Unit getClosestEnemyUnit(Unit unit){
        int dist = 999;
        Unit closest = null;
        for(int i = 0; i < cellGrid.length; i++){
            for(int j = 0; j < cellGrid[i].length; j++){
                if(cellGrid[i][j].getUnit() != null && cellGrid[i][j].getUnit().getSide()){
                    if(dist > getDistanceBetweenTwoUnits(unit, cellGrid[i][j].getUnit())) {
                        dist = getDistanceBetweenTwoUnits(unit, cellGrid[i][j].getUnit());
                        closest = cellGrid[i][j].getUnit();
                    }
                }
            }
        }
        return closest;
    }

    public int getDistanceBetweenTwoUnits(Unit unit1, Unit unit2){
        Coordinate pos1 = new Coordinate(0,0);
        Coordinate pos2 = new Coordinate(0,0);
        getUnitCoordinates(pos1, unit1);
        getUnitCoordinates(pos2, unit2);
        int xDif = Math.abs(pos1.getX() - pos2.getX());
        int yDif = Math.abs(pos1.getY() - pos2.getY());
        int pit = (int) Math.sqrt(xDif*xDif + yDif*yDif);
        return (xDif == 0) ? yDif : ( (yDif == 0) ? xDif : pit);
    }
    public boolean isEmpty() {
        boolean checkIsEmptyE = false;
        boolean checkIsEmptyA = false;
        for(int i = 0; i < cellGrid.length; i++){
            for(int j = 0; j < cellGrid[i].length; j++){
                if(cellGrid[i][j].getUnit() != null && !cellGrid[i][j].getUnit().getSide())
                    checkIsEmptyE = true;
                if(cellGrid[i][j].getUnit() != null && cellGrid[i][j].getUnit().getSide())
                    checkIsEmptyA = true;
            }
        }
        return ( (checkIsEmptyA && !checkIsEmptyE) || (!checkIsEmptyA && checkIsEmptyE) );
    }

    public LinkedList<Unit> getUnitsInUnitRange(Unit unit){
        LinkedList<Unit> units = new LinkedList<>();
        int range = unit.getRange();
        for(int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++){
                if((cellGrid[i][j].getUnit() != null)
                        && (cellGrid[i][j].getUnit().getSide() != unit.getSide())
                        && getDistanceBetweenTwoUnits(unit, cellGrid[i][j].getUnit()) <= range){
                    units.add(cellGrid[i][j].getUnit());
                }
            }
        }
        return units;
    }

    public void showUnitsStats(){
        for(int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                Unit actualUnit = cellGrid[i][j].getUnit();
                if(actualUnit != null){
                    System.out.println("The " + actualUnit.getUnitName() + " that's on " + i + " " + j + " position"
                    + " has " + actualUnit.getRealHP() + " HP");
                }
            }
        }
    }
    public void cleanMap(){
        for(int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                Unit actualUnit = cellGrid[i][j].getUnit();
                if(actualUnit != null){
                    if(actualUnit.getRealHP() <= 0)
                        cellGrid[i][j].setUnit(null);
                }
            }
        }
    }

    public Cell[][] getCellGrid() {
        return cellGrid;
    }
}