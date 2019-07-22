package Players;

public class Priest extends Player {
    private int x, y;

    public Priest(int dim)
    {
        x = 0;
        y = dim - 1;
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
       if(x + 1 <= dim)
            x++;
       if(y - 1 >= 0)
            y--;
    }
}
