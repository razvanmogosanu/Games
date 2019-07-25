package utilities;


public class Ability{
    private String skillName;
    private int numberOfTargets;
    private int manaCost;
    private int damagePerLevel;

    public Ability(String skillName, int numberOfTargets, int manaCost){
        this.numberOfTargets = numberOfTargets;
        this.manaCost = manaCost;
        this.skillName = skillName;
    }
}
