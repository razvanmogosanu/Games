import maputilities.Coordinate;
import maputilities.Map;
import units.Mage;
import units.Ninja;
import units.Unit;
import units.Warrior;

import java.util.*;

class WorldOfMagic {

    private final Scanner scan = new Scanner(System.in);
    private static Random random = new Random();
    private int getLevel(){
        System.out.println("Choose the level of your enemies - Range: 1 - 30");
        int level;
        while(true) {
                level = scan.nextInt();
            if (level < 1 || level > 30) {
                System.out.println("Wrong Level. Please type a level in range: 1 - 30");
            }
            else {
                return level;
            }
        }
    }

    private int NUMBEROFHEROES = 3;
    private int NUMBEROFENEMIES = 4;

    void doGame(){
        while (true)
        {
            try{
                doRound();
            } catch (InterruptedException e){}
        }
    }

    private void doRound() throws InterruptedException {
        Map mapGrid = new Map();
        int level = getLevel();
        getAlliesHero(mapGrid);

        boolean rollTheDice = (random.nextBoolean());
        boolean firstMove = false;

        System.out.println("The Gods will decide if you will make the first move, or your bloody enemies");
        Thread.sleep(3000);
        if (!rollTheDice) {
            System.out.println("It seems like you're a sinner");
        }
        else {
            System.out.println("The Gods blessed you, be thankful");
        }
        Thread.sleep(1000);
        spawnEnemies(mapGrid, level);

        while (true) {
            if(!firstMove) {
                if(!rollTheDice)
                    mapGrid.showObjectGrid(null);
                firstMove = true;
            }
            if (!rollTheDice) {
                moveEnemies(mapGrid);
                enemiesAttack(mapGrid);
            }
            Thread.sleep(1500);
            moveAllies(mapGrid);
            alliesAttack(mapGrid);
            rollTheDice = false;

            if(mapGrid.isEmpty()) {
                System.out.println("Game over");
                break;
            }
            mapGrid.cleanMap();
        }
    }

    private void enemiesAttack(Map mapGrid) {
        List<Unit> list = new ArrayList<>();
        for(int i = 0; i < mapGrid.getCellGrid().length; i++){
            for(int j = 0; j < mapGrid.getCellGrid()[i].length; j++){
                Unit actualUnit = mapGrid.getCellGrid()[i][j].getUnit();
                if(actualUnit != null && !actualUnit.getSide()){
                    if (!list.contains(actualUnit)) {
                        list.add(actualUnit);
                        List<Unit> listTemp = mapGrid.getUnitsInUnitRange(actualUnit);
                        if(!listTemp.isEmpty()) {
                            Unit randomUnit = listTemp.get(random.nextInt(listTemp.size()));
                            randomUnit.setRealHP(randomUnit.getRealHP() - 10);
                            mapGrid.cleanMap();
                        }
                    }
                }
            }
       }
    }

    private void alliesAttack(Map mapGrid) {
        List<Unit> list = new ArrayList<>();
        for(int i = 0; i < mapGrid.getCellGrid().length; i++)
            for(int j = 0; j < mapGrid.getCellGrid()[i].length; j++) {
                Unit actualUnit = mapGrid.getCellGrid()[i][j].getUnit();
                if(actualUnit != null && actualUnit.getSide()){
                    if (!list.contains(actualUnit)) {
                        list.add(actualUnit);
                        List<Unit> tempList = mapGrid.getUnitsInUnitRange(actualUnit);
                        if (tempList.size() > 0){
                            mapGrid.cleanMap();
                            mapGrid.showObjectGrid(actualUnit);
                            showTargets(mapGrid,tempList);
                            Unit willBeAttacked = choseTarget(mapGrid, actualUnit, tempList);
                            attackTarget(willBeAttacked);
                        }
                    }
                }
            }
    }

    private void showTargets(Map mapGrid, List<Unit> unitsList) {
        System.out.println("Type a number between 1 - " + unitsList.size() + " to chose your target or 0 if you want to chill");
        System.out.print("You can attack these: ");
        for(int it = 0; it < unitsList.size(); it++) {
            String tempElement = (it < unitsList.size() - 1) ? unitsList.get(it).getUnitName() + ", " : unitsList.get(it).getUnitName();
            System.out.println(tempElement);
        }
    }

    private Unit choseTarget(Map mapGrid, Unit unit, List<Unit> unitsList) {
        int input;
        while(true){
            input = scan.nextInt();
            if(input == 0)
                break;
            if(input > 0 && input <= unitsList.size())
                return unitsList.get(input - 1);
            else
                System.out.println("Please type a number between 1 and " + unitsList.size());
            mapGrid.cleanMap();
        }
        return null;
    }

    private void attackTarget(Unit unit) {
        if(unit == null)
            return;
        unit.setRealHP(unit.getRealHP() - 10);
        System.out.println("Enemy " + unit.getUnitName() + " got 10 DMG");
        //TODO 10 - hardcodat
    }

    private void moveAllies(Map mapGrid) {
        List<Unit> list = new ArrayList<>();
        for(int i = 0; i < mapGrid.getCellGrid().length; i++)
            for(int j = 0; j < mapGrid.getCellGrid()[i].length; j++){
                Unit actualUnit = mapGrid.getCellGrid()[i][j].getUnit();
                if(actualUnit != null && actualUnit.getSide()){
                    if (!list.contains(actualUnit)) {
                        System.out.println("      ~/////\\    < Your turn >    /\\\\\\\\\\~");
                        mapGrid.showObjectGrid(actualUnit);
                        list.add(actualUnit);
                        mapGrid.moveHero(mapGrid, actualUnit);
                    }
                }
            }
    }

    private void moveEnemies(Map mapGrid) {
        System.out.println("      ~/////\\    < Enemy turn >    /\\\\\\\\\\~");
        List<Unit> list = new ArrayList<>();
        for(int i = 0; i < mapGrid.getCellGrid().length; i++){
            for(int j = 0; j < mapGrid.getCellGrid()[i].length; j++){
                Unit actualUnit = mapGrid.getCellGrid()[i][j].getUnit();
                if(actualUnit != null && !actualUnit.getSide()){
                    Unit unit = mapGrid.getClosestEnemyUnit(actualUnit);
                    Coordinate pos1 = new Coordinate(0,0);
                    Coordinate pos2 = new Coordinate(0,0);
                    mapGrid.getUnitCoordinates(pos1, unit);
                    mapGrid.getUnitCoordinates(pos2, actualUnit);
                    if (!list.contains(actualUnit)) {
                        list.add(actualUnit);
                        if (pos1.getX() < pos2.getX()) {
                            mapGrid.moveUp(actualUnit);
                        } else if (pos1.getX() > pos2.getX()) {
                            mapGrid.moveDown(actualUnit);
                        } else if (pos1.getY() < pos2.getY()) {
                            mapGrid.moveLeft(actualUnit);
                        } else if (pos1.getY() > pos2.getY()) {
                            mapGrid.moveRight(actualUnit);
                        }
                    }
                }
            }
        }
        mapGrid.showObjectGrid(null);
    }

    private void spawnEnemies(Map mapGrid, int level) {
        int i = 0;
        while(i < NUMBEROFENEMIES){
            mapGrid.doEnemiesPosition(level,getRandomUnits(level));
            i++;
        }
    }

    private void getAlliesHero(Map mapGrid){
        System.out.println("Choose a position for each hero. Table:");
        mapGrid.showCellGrid();
        int i = 0;
        while (i < NUMBEROFHEROES){
            System.out.println("Type W if you want to put a War, N for a Ninja and M for a Mage");
            switch (scan.next().toUpperCase()){
                case "W":
                    mapGrid.doAlliesPosition(new Warrior());
                    break;
                case "M":
                    mapGrid.doAlliesPosition(new Mage());
                    break;
                case "N":
                    mapGrid.doAlliesPosition(new Ninja());
                    break;
                default:
                    System.out.println("Please type a valid Hero");
                    continue;
            }
            i++;
        }
    }

    private Unit getRandomUnits(int level){
        int DiceForEnemyHero = random.nextInt(3);
        return (DiceForEnemyHero == 0) ? new Warrior(level) : (DiceForEnemyHero == 1) ? new Mage(level) : new Ninja(level);
    }
}
//TODO de facut mai multe runde jucabile
//TODO de facut skillurile si itemele
//TODO FRONTEND ...
