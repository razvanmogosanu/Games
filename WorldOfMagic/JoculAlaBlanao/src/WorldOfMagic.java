import units.Mage;
import units.Ninja;
import units.Unit;
import units.Warrior;

import java.util.Random;
import java.util.Scanner;

public class WorldOfMagic {

    private final Scanner scan = new Scanner(System.in);
    private static Random random = new Random();
    private int getLevel(){
        System.out.println("Choose the level of your enemies - Range: 1 - 30");
        int level;
        while(true) {
            level = scan.nextInt();
            //todo pt level asta sa fie throw and catch
            if (level < 1 || level > 30) {
                System.out.println("Wrong Level. Please type a level in range: 1 - 30");
            }
            else {
                return level;
            }
        }
    }
    private void moveHero(Map mapGrid, Unit unit){
        System.out.println("             Type 1: UP\n" + "Type 2: LEFT" + "  Type 0: Stand  " + "Type 3: RIGHT\n" + "             Type 4: DOWN");
        int input = scan.nextInt();
        switch (input) {
            case 1: {
                mapGrid.moveUp(unit);
                break;
            }
            case 2: {
                mapGrid.moveLeft(unit);
                break;
            }
            case 3: {
                mapGrid.moveRight(unit);
                break;
            }
            case 4: {
                mapGrid.moveDown(unit);
                break;
            }
            default: break;
        }
        mapGrid.showObjectGrid();
    }
    private int NUMBEROFHEROES = 3;
    private int NUMBEROFENEMIES = 4;

    private void doRound() {
        Map mapGrid = new Map();
        int level = getLevel();
        getAlliesHero(mapGrid);
        while(true){
            boolean rollTheDice = (random.nextBoolean());
            mapGrid.doEnemiesPosition(level,getRandomUnits(level));
            mapGrid.doEnemiesPosition(level,getRandomUnits(level));
            mapGrid.doEnemiesPosition(level,getRandomUnits(level));
            mapGrid.showObjectGrid();
            break;
        }
    }

    private void getAlliesHero(Map mapGrid){
        System.out.println("Choose a position for each hero. Table:");
        mapGrid.showCellGrid();
        int i = 0;
        while (i < NUMBEROFHEROES){
            System.out.println("Type W if you want to put a War, N for a Ninja and M for a Mage");
            String choseUnit = scan.next();
            switch (choseUnit){
                case "W":
                    mapGrid.doAlliesPosition(new Warrior());
                    break;
                case "M":
                    mapGrid.doAlliesPosition(new Mage());
                    break;
                case "N":
                    mapGrid.doAlliesPosition(new Ninja());
                    break;
            }
            i++;
        }
        mapGrid.showObjectGrid();
    }
    private Unit getRandomUnits(int level){
        int DiceForEnemyHero = random.nextInt(2);
        return (DiceForEnemyHero == 0) ? new Warrior(level) : (DiceForEnemyHero == 1) ? new Mage(level) : new Ninja(level);
    }
    void doGame(){
        doRound();
    }

}
