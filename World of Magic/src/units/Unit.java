package units;

public abstract class Unit{
    private int id;
    private static int nextId = 0;
    int baseHP;
    int range;
    private int LEVEL;
    int realHP;
    private boolean side;

    Unit(int LEVEL) {
        this.id = nextId++;
        this.LEVEL = LEVEL;
    }

    public void setSide(boolean side) { this.side = side; }

    public int getRealHP() {
        return realHP;
    }

    public int getId() {
        return id;
    }

    public boolean getSide(){return side;}

    abstract void setRange();

    public abstract String getUnitName();

    public void setRealHP(int hp){
        this.realHP = hp;
    }

    protected  void setBaseHP(int itn){
    }
    public int getRange() {return range;};
}