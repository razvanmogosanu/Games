package Players;

public class Warrior extends Player {
    private int x, y;
    public Warrior(int dim)
    {
        x = 0;
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

    private boolean line = false;
    public void move(int dim)
    {
        if(line == false)
        {
            y++;
            if(y == dim + 1)
            {
                y--;
                line = true;
                if(x < dim)
                    x++;
            }
        }
        else
            if(line == true)
            {
                y--;
                if(y == 0)
                {
                    line = false;
                    if(x < dim)
                        x++;
                }

            }
    }
}
