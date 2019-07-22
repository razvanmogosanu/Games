
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{

    private JFrame frame = new JFrame("Blackjack");
    private JButton button1 = new JButton("Bet");
    private JButton button2 = new JButton("Quit");
    private JPanel pane = new JPanel();
    private JPanel pane2 = new JPanel();

    void doFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane2.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));

        frame.pack();
        frame.setSize(300,300);

        pane.add(button1);
        pane.add(button2);

        frame.add(pane, BorderLayout.NORTH);
        frame.add(pane2, BorderLayout.CENTER);
        frame.setVisible(true);

        button1.addActionListener(this);
        button2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button2){
            frame.dispose();
            System.exit(0);
        }


        JLabel label1 = new JLabel("        dealer has  KING");     //player's cards
        JLabel label2 = new JLabel("        other has a ... ");    // dealer's card


        pane2.add(label1);
        pane2.add(label2);

        frame.setVisible(true);
    }


}