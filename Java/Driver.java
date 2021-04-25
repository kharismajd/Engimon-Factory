public class Driver {
    public static void main(String[] args) {
        database_skill database_skill = new database_skill();
        database_engimon database_engimon = new database_engimon();
//        String[] elem = {"Fire", "Water", "Electric", "Ice"};
//        String[] lol = {"Fire"};
//        skill a = new skill("a", 50, 0, lol);
//        skill[] s;
//        s = new skill[4];
//        skill nullSkill = new skill();
//        //s[0].printAll();
//        s[1] = a;
//        s[1].printAll();
//
//        System.out.println("\n");
//        try
//        {
//         s[0] = database_skill.random("Fire","Electric");
//         s[0].printAll();
//        }
//
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }

        // System.out.println(database_skill.isExist("splash"));

        // System.out.println(database_skill.skillUniqueEngimon("Magikarp") == null);
//        System.out.println(database_engimon.isExist("Magikarp"));
//        System.out.println("");
//        player_engimon Gab;
//        try
//        {
//            Gab = new player_engimon("Gab", null, null, "Magikarp", 4, 0);
//            engimon test;
//            test = Gab;
//            Gab.showAttributes();
//            // System.out.println(Gab == null);
//            Gab.learnMove("tackle");
//            Gab.learnMove("bubble");
//            System.out.println();
//            test.showAttributes();
//            Gab.cry();
//
//            System.out.println("");
//            wild_engimon we = new wild_engimon("mountains");
//            we.showAttributes();;
//            we.cry();
//        }
//        catch (NullPointerException n)
//        {
//            n.printStackTrace();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

        gameMap g;
        try {
            g = new gameMap("inputMapFile.txt");
            g.updateMap(g.getMapLength()/2, g.getMapWidth()/2,
                    g.getMapLength()/2, g.getMapWidth()/2 + 1);
            g.printMap();
            map_visualizer m = new map_visualizer(g);

            System.out.println("x: " + g.tile_map.elementAt(9).elementAt(11).x);
            System.out.println("y: " + g.tile_map.elementAt(9).elementAt(11).y);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        System.out.println('h');

//
//        try
//        {
//            g.generateEngimon();
//            g.updateMap(g.getMapLength()/2, g.getMapWidth()/2,
//                    g.getMapLength()/2, g.getMapWidth()/2 + 1);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//
//        g.printMap();

    }
}
