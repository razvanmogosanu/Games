package Players;

public class Ninja extends Player{
    private int x, y;
    public Ninja(int dim)
    {
        x = dim - 1;
        y = 0;
    }

    public int getx()
    {
        return x;
    }

    public int gety()
    {
        return y;
    }

    public void setx(int x)
    {
        this.x = x;
    }

    public void sety(int y)
    {
        this.y = y;
    }

    public void move(int dim)
    {
        if(x - 1 >= 0)
            x--;
        if(y + 1 <= dim)
            y++;
    }
}
