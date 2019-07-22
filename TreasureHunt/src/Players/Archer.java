package Players;

public class Archer extends Player
{
    private int x, y;
    public Archer(int dim)
    {
        x = dim - 1;
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
    boolean line = false;
    public void move(int dim)
    {
        if(line == false){
            y--;
            if(y == -1)
            {
                line = true;
                if(x < 0)
                    x--;
            }
        }
        else if(line == true){
            y++;
            if(y > dim)
            {
                line = false;
                if(x < 0)
                    x--;
            }
        }

    }
}
