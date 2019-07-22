public class Money {
    private int amountOfMoney;
    public Money(int money)
    {
        amountOfMoney = money;
    }
    int getMoney()
    {
        return amountOfMoney;
    }
    void setMoney(int money)
    {
        amountOfMoney += money;
    }
}
