import java.util.Vector;

public class gameMap_test {
    public gameMap_test()
    {
        System.out.println("Map test 1:");
        gameMap_test1();
        System.out.println("Map test 2:");
        gameMap_test2();
        System.out.println("Map test 3:");
        gameMap_test3();
        System.out.println("Map test 4:");
        gameMap_test4();
        System.out.println("Map test 5:");
        gameMap_test5();
    }

    public void gameMap_test1()
    {
        gameMap g = new gameMap();
        try
        {
            g.updateMap(6, 7,
                    6, 8);
            assert (g.tile_map.elementAt(7).elementAt(6).isPlayerHere());
            assert (g.tile_map.elementAt(8).elementAt(6).isactiveEngimonHere());
            assert (g.tile_map.elementAt(7).elementAt(6).x == 6);
            assert (g.tile_map.elementAt(7).elementAt(6).y == 7);
            assert (g.tile_map.elementAt(8).elementAt(6).x == 6);
            assert (g.tile_map.elementAt(8).elementAt(6).y == 8);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void gameMap_test2()
    {
        //Random Generate test
        gameMap g;

        for (int i = 0; i < 10; i++) {
            g = new gameMap();
            int count_engimon = 0;
            g.generateEngimon();
            try
            {
                g.updateMap(0,0,0,1);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
//                e.printStackTrace();
            }
            for (Vector<tile> v : g.tile_map) {
                for (tile j:v)
                {
                    if (j.haveWildEngimon())
                    {
                        count_engimon = count_engimon + 1;
                    }
                }
            }

            g.printMap();
            System.out.println("Amount of engimon: " + count_engimon);
            assert (count_engimon == gameMap.MAP_ENGIMON_COUNT);
        }
    }
    public void gameMap_test3()
    {
        /*Move tile engimon*/
        gameMap g = new gameMap();
        wild_engimon we = null;
        try {
            we = new wild_engimon("mountains");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tile tile1 = new tile(0,0, we,"mountains");
        tile tile2 = new tile(0,0,"mountains");

        g.moveTileEngimon(tile1, tile2);
        assert (tile2.haveWildEngimon());
        assert (tile2.getEngimon() == we) ;
        assert (tile1.haveWildEngimon() == false);

        tile tile3 = new tile(0,0, we,"mountains");
        tile tile4 = new tile(0,0,"sea");

        g.moveTileEngimon(tile3, tile4);
        assert (tile3.haveWildEngimon());
        assert (tile3.getEngimon() == we) ;
        assert (tile4.haveWildEngimon() == false);

        System.out.println("moveTileEngimon berhasil!");

    }

    public void gameMap_test4()
    {
        /*Move Wild engimon test*/
        gameMap g = new gameMap();
        g.generateEngimon();
        try
        {
            g.updateMap(0,0,0,1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        g.printMap();
        System.out.println("Move 50 times");
        for (int i = 0; i < 50; i++) {
            g.moveWildEngimon();
            try
            {
                g.updateMap(0,0,0,1);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
//            System.out.println("Move: "+i);
//            g.printMap();
        }
        g.printMap();
    }

    public void gameMap_test5()
    {
        gameMap g = new gameMap();
        g.generateEngimon();


        tile temp = null;
        for (Vector<tile> i :g.tile_map){
            for (tile j:i) {
                if (j.haveWildEngimon())
                {
                    temp = j;
                    break;
                }
            }
        }

        g.deleteTileEngimon(temp.x,temp.y);
        System.out.println("Engimon count should be: "+(gameMap.MAP_ENGIMON_COUNT - 1));
        System.out.println("Engimon count: "+ g.engimon_count);
        assert (g.engimon_count == gameMap.MAP_ENGIMON_COUNT - 1);
        try
        {
            g.updateMap(0,0,0,1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        g.printMap();
    }
}
