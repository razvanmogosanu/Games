package utils;

public class Utils {
    public static boolean checkIfInt(String checkeble){
        try {
             Integer.parseInt(checkeble);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
