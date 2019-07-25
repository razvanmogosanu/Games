package units;

import java.util.ArrayList;

public abstract class Unit{
    private int id;
    private static int nextId = 0;
    protected int baseHP, baseSTR, baseDEX, baseINT;
    protected int range;
    private int LEVEL;
    private int XP;
    protected ArrayList skillsArray;
    private int slotsForYourHeroes;
    protected int realHP, realSTR, realDEX, realINT;
    private boolean side;

    public void setSide(boolean side) {
        this.side = side;
    }

    Unit(){}
    Unit(int LEVEL) {
        this.id = nextId++;
        this.LEVEL = LEVEL;
        XP = 0;
        skillsArray = new ArrayList(3);
        setSlotsForYourHeroes();
    }


    public int getId() {
        return id;
    }
    public void setSlotsForYourHeroes() {
            this.slotsForYourHeroes = 3 + LEVEL%10;
    }
    public boolean getSide(){return side;}
    abstract void setRange();
    abstract void setSkills();
    public abstract String getUnitName();
    protected abstract void setRealHP(int level);
    protected abstract void setRealSTR(int level);
    protected abstract void setRealDEX(int level);
    protected abstract void setRealINT(int level);
    protected abstract void setBaseHP(int itn);
    public int getRange() {return range;};
}