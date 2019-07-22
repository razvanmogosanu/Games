package utilities;

public class Cell {
    private boolean alive;

    Cell(double rate) {
        alive = getStatus(rate);
    }

    private boolean getStatus(double rate) {
        return Math.random() < rate;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setStatus(boolean status) {
        this.alive = status;
    }
}