import Game.Game;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(20, 20, 0.2);
        game.runGame();
    }
}
