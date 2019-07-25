package utils;


import units.Mage;
import units.Ninja;
import units.Unit;
import units.Warrior;

public class Utils {
    public static boolean checkIfInt(String checkeble){
        try {
             Integer.parseInt(checkeble);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    public static Unit getUnitForName(String name){
        switch (name){
            case "W":
                return new Warrior();
            case "M":
                return new Mage(1);
            case "N":
                return new Ninja(1);
        }
        return null;
    }

}
