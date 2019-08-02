package maputilities;

import units.Unit;

public class Cell {
    private String value;
    private Unit unit;

    public String getValue() {
        if (unit != null) {
            return unit.getUnitName().substring(0, 1) + "  ";
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}