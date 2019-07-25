package units;

import utilities.Ability;

public class Ninja extends Unit{
    private  final String unitName = "Ninja";

    public  String getUnitName() {
        return unitName;
    }

    private int hpPerLevel, strPerLevel, dexPerLevel, intPerLevel;

    public Ninja(){
        this(1);
    }
    public Ninja(int LEVEL) {
        super(LEVEL);

        hpPerLevel = 20;
        strPerLevel = 4;
        dexPerLevel = 1;
        intPerLevel = 1;
        setBaseHP(150);

        setRange();
        setSkills();
    }

    @Override
    void setRange() { range = 1; }

    @Override
    void setSkills() {
        skillsArray.add(new Ability("Dansul sagetilor",2,5));
        skillsArray.add(new Ability("Sageata de foc",1,5));
        skillsArray.add(new Ability("Sageata otravitoare",1,10));
    }

    @Override
    protected void setRealHP(int level) { realHP = baseHP + hpPerLevel * level; }

    @Override
    protected void setRealSTR(int level) { realSTR = baseSTR + strPerLevel * level; }

    @Override
    protected void setRealDEX(int level) { realDEX = baseDEX + dexPerLevel * level;}

    @Override
    protected void setRealINT(int level) { realINT = baseINT + intPerLevel * level;}

    @Override
    protected void setBaseHP(int itn) { baseHP = itn; }
}