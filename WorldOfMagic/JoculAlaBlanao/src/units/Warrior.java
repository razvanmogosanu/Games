package units;

import utilities.Ability;
public class Warrior  extends Unit{
    private  final String unitName = "Warrior";

    public  String getUnitName() {
        return unitName;
    }

    private int hpPerLevel, strPerLevel, dexPerLevel, intPerLevel;
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
        hpPerLevel = 20;
        strPerLevel = 4;
        dexPerLevel = 1;
        intPerLevel = 1;
        setBaseHP(150);

        setRange();
        setSkills();
    }
    @Override
    void setRange() {
        range = 1;
    }
    @Override
    protected void setSkills() {
        skillsArray.add(new Ability("VARTEJUL SABIEI",2,5));
        skillsArray.add(new Ability("INCIZIE TRIPLA",1,5));
        skillsArray.add(new Ability("AURA SABIEI",0,10));
    }

    @Override
    protected void setRealHP(int level) { realHP = baseHP + hpPerLevel * level; }

    @Override
    protected void setRealSTR(int level) { realSTR = baseSTR + strPerLevel * level; }

    @Override
    protected void setRealDEX(int level) { realDEX = baseDEX + dexPerLevel * level; }

    @Override
    protected void setRealINT(int level) { realINT = baseINT + intPerLevel * level; }

    @Override
    protected void setBaseHP(int itn) {
        baseHP = itn;
    }
}