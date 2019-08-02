package units;

public class Ninja extends Unit{

    public  String getUnitName() {
        return "Ninja";
    }

    public Ninja(){
        this(1);
        setThingsForConstructors();
        setSide(true);
    }
    public Ninja(int LEVEL) {
        super(LEVEL);
        setThingsForConstructors();
        setSide(false);
    }
    private void setThingsForConstructors(){
        realHP = 125;
        setBaseHP(150);

        setRange();
    }
    @Override
    void setRange() { range = 3; }


}