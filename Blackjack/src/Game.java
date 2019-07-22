import utilities.Hand;

import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private Money money = new Money(500);

    private int checkMoney(int foo, String s){
        System.out.println("How much?");

        while (true) {
            s = scanner.next();
            try {
                foo = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid amount of money");
            }

            if (foo != -1)
                if (foo > money.getMoney())
                    System.out.println("You don't own that amount of money, you have only " + money.getMoney() + "$");
                else
                    break;
        }
        return foo;
    }

    boolean ifBlackjack(Hand player, int foo){
        if (player.getSum() == 21 && player.getHand().size() == 2) {
            System.out.println("BLACKJACK");
            money.setMoney(foo);
            return true;
        }
        return false;
    }

    void doGame()
    {
        String s;
        while (true) {
            int foo = -1;
            Hand player = new Hand();
            Hand dealer = new Hand();
            System.out.println("If you want to deal press (d), if you want to quit press (q)");
            s = scanner.next();
            if (s.equals("q"))
                break;
            if (s.equals("d")) {
                foo =  checkMoney(foo, s);
                while (true) {
                    System.out.println("You got : ");
                    player.printCards();
                    System.out.println("Your sum is : " + player.getSum());
                    if(ifBlackjack(player, foo))
                        break;
                    System.out.println("Our dealer got: " + dealer.getHand().get(0).getCardType());
                    System.out.println("If you want to Hit, press(h), if you want to Stand, press(s) ");
                    /**
                     * IMPLEMENTEAZA DOUBLE SI SPLIT
                     */
                    s = scanner.next();

                    if (s.equals("h")) {
                        player.addCard();
                        player.printCards();
                    }

                    if (player.getSum() > 21) {
                        System.out.println("Dealer won");
                        money.setMoney((-1) * foo);
                        break;
                    }
                    if (s.equals("s")) {
                        System.out.println("You got " + player.getSum() + " our dealer got " + dealer.getSum());
                        while (true) {
                            if (player.getSum() > dealer.getSum()) {
                                dealer.addCard();
                                dealer.getSum();
                            } else break;
                        }
                        dealer.printCards();

                        if (dealer.getSum() > player.getSum() && dealer.getSum() < 22) {
                            System.out.println("Dealer won");
                            money.setMoney((-1) * foo);
                        }

                        if (dealer.getSum() == player.getSum())
                            System.out.println("It's a draw");

                        if ((dealer.getSum() < player.getSum() && dealer.getSum() < 22) || dealer.getSum() >= 22) {
                            System.out.println("You won");
                            money.setMoney(foo);
                        }
                        System.out.println("You have " + money.getMoney() + "$");
                        break;
                    }
                }
            }
            if (money.getMoney() == 0) {
                System.out.println("You lost");
                break;
            }
        }
        System.out.println("The game is finished, thanks for playing!");
        scanner.close();
    }
}
