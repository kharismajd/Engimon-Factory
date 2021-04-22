public class engimon_test {

    public static void main(String[] args) {
        database_test d = new database_test();
        engimon_test e = new engimon_test();
    }

    public engimon_test()
    {
        System.out.println("Test 1:");
        engimon_test1();
        System.out.println("\n\n\nTest 2:");
        engimon_test2();
        System.out.println("\n\n\nTest 3:");
        engimon_test3();
        System.out.println("\n\n\nTest pe4:");
        player_engimon_test1();
        System.out.println("\n\n\nTest we 1: ");
        wild_engimon_test1();
        System.out.println("\n\n\nTest we 2: ");
        wild_engimon_test2();
    }

    public void engimon_test1()
    {
        engimon Gab;
        try
        {
            Gab = new player_engimon("Gab", null, null, "Magikarp", 4, 0);
            engimon test;
            test = Gab;
            Gab.showAttributes();

//            assert(Gab.name == "Gab");
//            assert(Gab.parent1 == null);
//            assert(Gab.parent2 == null);
//            assert(Gab.species == "Magikarp");
//            assert(Gab.moves[0] == database_skill.skillUniqueEngimon("Magikarp"));
//            assert(Gab.element1 == dbspecies).element1;
//            assert(Gab.element2 == dbspecies).element2;
//            assert(Gab.level == lvl);
//            assert(Gab.experience == exp);
//            assert(Gab.cummulative_experience == (lvl)-1)*100 + exp;
//            assert(Gab.active == false);


            // System.out.println(Gab == null);
            Gab.learnMove("tackle");
            Gab.learnMove("bubble");
            System.out.println();
            test.showAttributes();
            Gab.cry();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }
    }

    public void engimon_test2()
    {
        engimon Gab;
        try
        {
            Gab = new player_engimon("Gab", null, null, "Magikarp", 4, 0);
            engimon test;
            test = Gab;
            Gab.showAttributes();
            // System.out.println(Gab == null);
            Gab.learnMove("tackle");
            Gab.learnMove("bubble");
            System.out.println();
            test.Initialize("test", null, null, "Charmander", 80, 0);
            test.showAttributes();
            Gab.cry();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }
    }

    public void engimon_test3()
    {
        engimon Gab;
        engimon parent1;
        engimon parent2;
        try
        {
            parent1 = new player_engimon("Parent1", null, null, "Magikarp", 80, 0);
            parent2 = new player_engimon("Parent2", null, null, "Magikarp", 80, 0);
            Gab = new player_engimon("Gab", parent1, parent2, "Magikarp", 4, 0);

            // System.out.println(Gab == null);
            Gab.learnMove("tackle");
            Gab.learnMove("bubble");
            System.out.println();
            Gab.showAttributes();
            Gab.cry();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }
    }

    public void player_engimon_test1()
    {
        player_engimon Gab;
        try
        {
            Gab = new player_engimon("Gab", null, null, "Magikarp", 4, 0);
            engimon test;
            test = Gab;
            test.learnMove("tackle");
            test.learnMove("bubble");
            System.out.println();
            test.cry();
            Integer tempLevel = test.getLevel();
            Integer tempCummulativExp = test.getCummulativeExp();
            test.gainExp(750);
            assert (test.getLevel() == tempLevel + 7);
            assert (test.getCummulativeExp() == tempCummulativExp + 750);
            test.setLevel(tempLevel);
            assert (test.getLevel() == tempLevel);
            assert (test.getCummulativeExp() == tempCummulativExp + 750);
            Gab.showAttributes();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            assert false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }
    }

    public void wild_engimon_test1()
    {
        wild_engimon we;
        try
        {
            we = new wild_engimon("Magikarp", 80, 1);
            we.showAttributes();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }

        try
        {
            we = new wild_engimon("Charmander", 5, 0);
            we.showAttributes();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            assert false;
        }
    }

    public void wild_engimon_test2()
    {
        wild_engimon we;

        for (int i = 0; i < 10; i++) {
            try
            {
                we = new wild_engimon();
                we.showAttributes();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                assert false;
            }
        }

        for (int i = 0; i < 5; i++) {
            try
            {
                we = new wild_engimon("mountains");
                we.showAttributes();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                assert false;
            }

            try
            {
                we = new wild_engimon("sea");
                we.showAttributes();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                assert false;
            }

            try
            {
                we = new wild_engimon("grassland");
                we.showAttributes();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                assert false;
            }

            try
            {
                we = new wild_engimon("tundra");
                we.showAttributes();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                assert false;
            }
        }
    }
}
