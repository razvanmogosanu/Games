package Game;

import utilities.Map;

public class Game extends Map {

    public Game(int x, int y, double rate) {
        super(x, y, rate);
    }

    public void runGame() throws InterruptedException {
        while (true) {
            Thread.sleep(2000);
            printMap();
            eachTick();
        }
    }

    private void eachTick() {
        for (int i = 0; i < getGrid().length; i++)
            for (int j = 0; j < getGrid()[i].length; j++) {

                int aliveNeighbours = 0;
                int N = getGrid().length;

                /** TOP */
                aliveNeighbours += ((getGrid()[getIndex(i - 1, N)][getIndex(j - 1, N)].isAlive()) ? 1 : 0);
                aliveNeighbours += ((getGrid()[getIndex(i - 1, N)][getIndex(j, N)].isAlive()) ? 1 : 0);
                aliveNeighbours += ((getGrid()[getIndex(i - 1, N)][getIndex(j + 1, N)].isAlive()) ? 1 : 0);

                /** LEFT & RIGHT */
                aliveNeighbours += ((getGrid()[getIndex(i, N)][getIndex(j - 1, N)].isAlive()) ? 1 : 0);
                aliveNeighbours += ((getGrid()[getIndex(i, N)][getIndex(j + 1, N)].isAlive()) ? 1 : 0);

                /** BOTTOM */
                aliveNeighbours += ((getGrid()[getIndex(i + 1, N)][getIndex(j - 1, N)].isAlive()) ? 1 : 0);
                aliveNeighbours += ((getGrid()[getIndex(i + 1, N)][getIndex(j, N)].isAlive()) ? 1 : 0);
                aliveNeighbours += ((getGrid()[getIndex(i + 1, N)][getIndex(j + 1, N)].isAlive()) ? 1 : 0);

                applyRules(i, j, aliveNeighbours);
            }
    }

    private void applyRules(int i, int j, int aliveNeighbours) {
        if (getGrid()[i][j].isAlive() && ((aliveNeighbours < 2) || (aliveNeighbours > 3))) {
            getGrid()[i][j].setStatus(false);
        }

        if ((!getGrid()[i][j].isAlive()) && aliveNeighbours == 3) {
            getGrid()[i][j].setStatus(true);
        }
    }

    private int getIndex(int x, int N) {
        return (x + N) % N;
    }
}
