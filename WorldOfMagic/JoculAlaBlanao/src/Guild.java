//import units.Mage;
//import units.Ninja;
//import units.Warrior;
//import utilities.Coordinate;
//
//public class Guild {
//
//    private Mage heroMage;
//    protected Ninja heroNinja;
//    protected Warrior heroWarrior;
//
//    void moveUp(String unitName) {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, unitName);
//
//        if(pos.getX() - 1 >= 0 && !Map.mapGrid[pos.getX() - 1][pos.getY()].contains("M") &&
//          !Map.mapGrid[pos.getX() - 1][pos.getY()].contains("N") &&
//          !Map.mapGrid[pos.getX() - 1][pos.getY()].contains("W") ) {
//            Map.mapGrid[pos.getX() - 1][pos.getY()] = unitName;
//            Map.mapGrid[pos.getX()][pos.getY()] = ". ";
//        }
//    }
//    void moveDown(String unitName) {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, unitName);
//        if(pos.getX() + 1 <= 2 && !Map.mapGrid[pos.getX() + 1][pos.getY()].contains("M") &&
//                !Map.mapGrid[pos.getX() + 1][pos.getY()].contains("N") &&
//                !Map.mapGrid[pos.getX() + 1][pos.getY()].contains("W") ) {
//            Map.mapGrid[pos.getX() + 1][pos.getY()] = unitName;
//            Map.mapGrid[pos.getX()][pos.getY()] = ". ";
//        }
//    }
//    void moveLeft(String unitName) {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, unitName);
//        if(pos.getY() - 1 >= 0 && !Map.mapGrid[pos.getX()][pos.getY() - 1].contains("M") &&
//                !Map.mapGrid[pos.getX()][pos.getY() - 1].contains("N") &&
//                !Map.mapGrid[pos.getX()][pos.getY() - 1].contains("W") ) {
//            Map.mapGrid[pos.getX()][pos.getY() - 1] = unitName;
//            Map.mapGrid[pos.getX()][pos.getY()] = ". ";
//        }
//    }
//    void moveRight(String unitName) {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, unitName);
//        if(pos.getY() + 1 <= 5 && !Map.mapGrid[pos.getX()][pos.getY() + 1].contains("M") &&
//                !Map.mapGrid[pos.getX()][pos.getY() + 1].contains("N") &&
//                !Map.mapGrid[pos.getX()][pos.getY() + 1].contains("W") ) {
//            Map.mapGrid[pos.getX()][pos.getY() + 1] = unitName;
//            Map.mapGrid[pos.getX()][pos.getY()] = ". ";
//        }
//    }
//
//    Guild(int level) {
//        heroMage = new Mage(100, 30, 30, 30, level);
//        heroNinja = new Ninja(100, 30, 30, 30, level);
//        heroWarrior = new Warrior(100, 30, 30, 30, level);
//    }
//
//    private void getUnitCoordinates(Coordinate pos, String unitName){
//        for(int i = 0; i < Map.mapGrid.length; i++) {
//            for(int j = 0; j < Map.mapGrid[i].length; j++) {
//                if(Map.mapGrid[i][j].contains(unitName)) {
//                    pos.setX(i);
//                    pos.setY(j);
//                    break;
//                }
//            }
//        }
//    }
//
//    void attackEnemy(String unitName) {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, unitName);
//    }
//
//    private void moveEnemyWarrior() {
//        Coordinate pos = new Coordinate(0,0);
//        getUnitCoordinates(pos, "eW");
//    }
//
//    private void moveEnemyNinja() {
//    }
//
//    private void moveEnemyMage() {
//    }
//
//
//}
