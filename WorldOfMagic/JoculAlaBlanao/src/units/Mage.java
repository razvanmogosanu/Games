package units;

import utilities.Ability;

public class Mage extends Unit {
    private final String unitName = "Mage";
    private int hpPerLevel, strPerLevel, dexPerLevel, intPerLevel;

    public  String getUnitName() {
        return unitName;
    }
    public Mage(){
        this(1);
    }
    public Mage(int LEVEL) {
        super(LEVEL);

        hpPerLevel = 10;
        strPerLevel = 2;
        dexPerLevel = 1;
        intPerLevel = 1;
        setBaseHP(100);
        setRange();
        setSkills();

    }

    @Override
    void setRange() { range = 1; }

    @Override
    void setSkills() {
        skillsArray.add(new Ability("Vindecarea Sufletului",1,5));
        skillsArray.add(new Ability("Furtuna de meteoriti", 3,10));
        skillsArray.add(new Ability("Scut de gheata",1,15));
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
    protected void setBaseHP(int itn) { baseHP = itn; }
}
