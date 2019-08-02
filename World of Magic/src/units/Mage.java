package units;

public class Mage extends Unit {

    public  String getUnitName() {
        return "Mage";
    }
    public Mage(){
        this(1);
        setThingsForConstructors();
        setSide(true);
    }
    public Mage(int LEVEL) {
        super(LEVEL);
        setThingsForConstructors();
        setSide(false);
    }
    private void setThingsForConstructors(){
        realHP = 100;
        setBaseHP(100);
        setRange();
    }
    @Override
    void setRange() { range = 3; }

    @Override
    protected void setBaseHP(int itn) { baseHP = itn; }
}