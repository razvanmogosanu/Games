import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.beans.Transient;
import java.net.URL;

public class GUI extends JPanel {
    private Game game = new Game(160, 160, 0.05);
    private int generationCounter;

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(game.getGrid().length * 4, game.getGrid()[0].length * 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < game.getGrid().length; i++) {
            for (int j = 0; j < game.getGrid()[i].length; j++) {
                if (game.getGrid()[i][j].isAlive()) {
                    g.setColor(Color.orange);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }
        g.setColor(gColor);
    }

    void doFrame() {
        GUI gameOfLife = new GUI();
        JFrame mainFrame = new JFrame();

        URL iconURL = getClass().getResource("./icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setTitle("GameOfLife");

        mainFrame.getContentPane().setBackground(Color.CYAN);
        mainFrame.getContentPane().add(gameOfLife);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationByPlatform(true);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        new Timer(100, e -> {
            gameOfLife.game.runGame();
            gameOfLife.repaint();
        }).start();
    }
}