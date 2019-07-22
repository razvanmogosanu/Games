import java.util.Random;
import Players.*;

public class Map {

    int empty = 0, chest = 5;
    int MAX = 5;
    int[][] map = new int[MAX][MAX];
    Warrior war = new Warrior(MAX);
    Priest priest =  new Priest(MAX);
    Archer archer = new Archer(MAX);
    Ninja ninja = new Ninja(MAX);

    public Map() {
        //initializing the map
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++)
                map[i][j] = empty;

        //setting the chests
        Random rand = new Random();
        int ok = 0;
        map[war.getx()][war.gety()] = 1;
        map[priest.getx()][priest.gety()] = 2;
        map[ninja.getx()][ninja.gety()] = 3;
        map[archer.getx()][archer.gety()] = 4;
        while (ok < 3) {
            int n = rand.nextInt(MAX - 1) + 1;
            int m = rand.nextInt(MAX - 1) + 1;
            if (map[n][m] == 0) {
                map[n][m] = chest;
                ok++;
            }
        }
    }

    public void Play()
    {
        int treasures = 3;
        while(treasures != 0){
           treasures = Turn(treasures);
            printMap();
            System.out.println();
        }

    }
    public void swap(int a, int b)
    {
        int aux = a;
        a = b;
        b = aux;
    }

    public int Turn(int treasures)
    {
        boolean check1 = false, check2 = false, check3 = false, check4 = false;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < MAX; j++)
            {
                //war moving
                if(map[i][j] == 1 && check1 == false) {
                    check1 = true;
                    war.move(MAX - 1);
                   // System.out.println(war.getx() + " " + war.gety());
                    if (map[war.getx()][war.gety()] == 5) {
                        System.out.println("The warrior has found a chest");
                        map[war.getx()][war.gety()] = 0;
                        map[i][j] = 0;
                        treasures--;
                    }
                    else {
                        if (map[war.getx()][war.gety()] == 0) {
                            map[war.getx()][war.gety()] = 1;
                            map[i][j] = 0;
                        }
                        if (map[war.getx()][war.gety()] == 2 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = priest.getx();
                            aux2 = priest.gety();
                            priest.setx(war.getx());
                            priest.sety(war.gety());
                            war.setx(aux1);
                            war.sety(aux2);
                            swap(map[i][j],map[war.getx()][war.gety()]);
                        }
                        if (map[war.getx()][war.gety()] == 3 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = ninja.getx();
                            aux2 = ninja.gety();
                            ninja.setx(war.getx());
                            ninja.sety(war.gety());
                            war.setx(aux1);
                            war.sety(aux2);
                            swap(map[i][j],map[war.getx()][war.gety()]);
                        }
                        if (map[war.getx()][war.gety()] == 4 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = archer.getx();
                            aux2 = archer.gety();
                            archer.setx(war.getx());
                            archer.sety(war.gety());
                            war.setx(aux1);
                            war.sety(aux2);
                            swap(map[i][j],map[war.getx()][war.gety()]);
                        }
                    }
                }

//                priest moving
                if(map[i][j] == 2 && check2 == false) {
                    check2 = true;
                    priest.move(MAX - 1);
                    if(map[priest.getx()][priest.gety()] == 5) {
                        System.out.println("The priest has found a chest");
                        map[priest.getx()][priest.gety()] = 0;
                        map[i][j] = 0;
                        treasures--;
                    }
                    else {
                        if (map[priest.getx()][priest.gety()] == 0) {
                            map[priest.getx()][priest.gety()] = 2;
                            map[i][j] = 0;
                        }
                        if (map[priest.getx()][priest.gety()] == 1 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = war.getx();
                            aux2 = war.gety();
                            war.setx(priest.getx());
                            war.sety(priest.gety());
                            priest.setx(aux1);
                            priest.sety(aux2);
                            swap(map[i][j],map[priest.getx()][priest.gety()]);
                        }
                        if (map[priest.getx()][priest.gety()] == 3 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = ninja.getx();
                            aux2 = ninja.gety();
                            ninja.setx(priest.getx());
                            ninja.sety(priest.gety());
                            priest.setx(aux1);
                            priest.sety(aux2);
                            swap(map[i][j],map[priest.getx()][priest.gety()]);
                        }
                        if (map[priest.getx()][priest.gety()] == 4 && Battle() == 1) {
                            int aux1, aux2;
                            aux1 = archer.getx();
                            aux2 = archer.gety();
                            archer.setx(priest.getx());
                            archer.sety(priest.gety());
                            priest.setx(aux1);
                            priest.sety(aux2);
                            swap(map[i][j],map[priest.getx()][priest.gety()]);
                        }
                    }
                }

                //ninja moving
                if(map[i][j] == 3) {
                    ninja.move(MAX - 1);
                    if(map[ninja.getx()][ninja.gety()] == 5) {
                        System.out.println("The ninja has found a chest");
                        map[ninja.getx()][ninja.gety()] = 0;
                        map[i][j] = 0;
                        treasures--;
                    }
                    else
                        if(map[ninja.getx()][ninja.gety()] == 0) {
                            map[ninja.getx()][ninja.gety()] = 3;
                            map[i][j] = 0;
                        }
                }
//
                //archer moving
                if(map[i][j] == 4) {
                    archer.move(MAX - 1);
                    if(map[archer.getx()][archer.gety()] == 5) {
                        System.out.println("The archer has found a chest");
                        map[archer.getx()][archer.gety()] = 0;
                        map[i][j] = 0;
                        treasures--;
                    }
                    else
                        if(map[archer.getx()][archer.gety()] == 0) {
                            map[archer.getx()][archer.gety()] = 4;
                            map[i][j] = 0;
                    }
                }
            }
        return treasures;
    }

    public int Battle()
    {
        int atacker, defender;
        Random rand = new Random();
        atacker = rand.nextInt(100);
        defender = rand.nextInt(100);
        if(atacker > defender)
            return 1;
        return 0;
    }

    public void printMap()
    {
        for(int i = 0; i < MAX ; i++)
        {
            for(int j = 0; j < MAX ; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }
}
