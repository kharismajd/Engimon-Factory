public class database_test {
    public database_test()
    {
        System.out.println("E Database test 1:");
        engimon_database_test1();
        System.out.println("E Database test 2:");
        engimon_database_test2();
        System.out.println("E Database test 3:");
        engimon_database_test3();
        System.out.println("S Database test 1:");
        skill_database_test1();
    }

    public void engimon_database_test1()
    {
        database_engimon database_engimon = new database_engimon();
        assert database_engimon.isExist("Vulpichu");
        assert database_engimon.isExist("Bulbasuar") == false;
        System.out.println("Success");
    }

    public void engimon_database_test2()
    {
        database_engimon database_engimon = new database_engimon();
        species Vulpichu = database_engimon.find("Vulpichu");
        species Pikachu = database_engimon.find("Pikachu");
        species Bulbasaur = database_engimon.find("Bulbasaur");
        assert Vulpichu.element1 == "Fire";
        assert Vulpichu.element2 == "Electric";
        assert Pikachu.element1 == "Electric";
        assert Pikachu.element2 == null;
        assert Bulbasaur == null;
        try
        {
            System.out.println(Bulbasaur.element1);
            assert false;
        }
        catch (NullPointerException n)
        {
            assert true;
        }
        System.out.println("Success");
    }

    public void engimon_database_test3()
    {
        database_engimon database_engimon = new database_engimon();
        species randMountain;
        species randSea;
        species randGrass;
        species randTundra;
        try
        {
            randMountain = database_engimon.random("mountains");
            System.out.println(randMountain.speciesName);
            assert randMountain != null;
            System.out.println("Mountain Success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            randSea = database_engimon.random("sea");
            System.out.println(randSea.speciesName);
            assert randSea != null;
            System.out.println("Sea Success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            randGrass = database_engimon.random("grassland");
            System.out.println(randGrass.speciesName);
            assert randGrass != null;
            System.out.println("Grass Success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            randTundra = database_engimon.random("tundra");
            System.out.println(randTundra.speciesName);
            assert randTundra != null;
            System.out.println("Tundra Success");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void skill_database_test1()
    {
        database_skill database_skill = new database_skill();
        skill fire;
        skill water;
        skill ice;
        skill ground;
        skill electric;

        try
        {
            fire = database_skill.random("Fire");
            System.out.println(fire.getSkillName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            water = database_skill.random("Water");
            System.out.println(water.getSkillName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            ice = database_skill.random("Ice");
            System.out.println(ice.getSkillName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            ground = database_skill.random("Ground");
            System.out.println(ground.getSkillName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            electric = database_skill.random("Electric");
            System.out.println(electric.getSkillName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
