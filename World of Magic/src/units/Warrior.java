package units;

public class Warrior  extends Unit{

    public  String getUnitName() {
        return "Warrior";
    }

    public Warrior(){
        this(1);
        setThingsForConstructors();
        setSide(true);
    }
    public Warrior(int LEVEL) {
        super(LEVEL);
        setThingsForConstructors();
        setSide(false);
    }
    private void setThingsForConstructors(){
        realHP = 150;
        setBaseHP(150);

        setRange();
    }
    @Override
    void setRange() {
        range = 1;
    }

    @Override
    protected void setBaseHP(int itn) {
        baseHP = itn;
    }
}