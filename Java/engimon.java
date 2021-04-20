abstract public class engimon {
    protected String name;
    protected engimon parent1;
    protected engimon parent2;
    protected String species;
    protected skill[] moves;
    protected String element1;
    protected String element2;
    protected Integer level;
    protected Integer experience;
    protected Integer cummulative_experience;
    protected Boolean active;
    protected Integer life;
    public static database_engimon database_engimon = new database_engimon();
    public static database_skill database_skill = new database_skill();

    //Constant:
    public static final String name_null = "null";
    public static final String element_null = null;

    public engimon()
    {
        name = name_null;
        parent1 = this;
        parent2 = this;
        species = "null";
        moves = new skill[4];
        skill nullSkill = new skill();
        for (int i = 0; i < 4; ++i)
        {
            moves[i] = nullSkill;
        }
        element1 = element_null;
        element2 = element_null;
        level = 0;
        experience = 0;
        cummulative_experience = 0;
        active = false;
        life = 0;
    }

    /* @param pr1,pr2 can be null */
    public engimon(String nm, engimon pr1, engimon pr2, String sp, int lvl, int exp) throws Exception
    {
        this.Initialize(nm, pr1, pr2, sp, lvl, exp);
    }

    protected void Initialize(String nm, engimon pr1, engimon pr2, String sp, int lvl, int exp) throws Exception
    {
        name = nm;
        parent1 = pr1;
        parent2 = pr2;

        species dbspecies = database_engimon.find(sp);
        this.species = sp;


        if (database_engimon.isExist(this.species))
        {
            throw new Exception("Error, tidak ketemu species");
        }

        moves = new skill[4];

        if (database_skill.skillUniqueEngimon(sp) == null)
        {
            throw new Exception("Skill unik engimon tidak ditemukan");
        }

        moves[0] = database_skill.skillUniqueEngimon(sp);

        for (int j = 1; j < 4; ++j)
        {
            moves[j] = null;
        }

        this.element1 = dbspecies.element1;
        this.element2 = dbspecies.element2;
        level = lvl;
        experience = exp;
        cummulative_experience = (lvl-1)*100 + exp;
        active = false;
    }

    public void showAttributes()
    {
        System.out.println("Engimon name: " + this.name);
        if (parent1 != null)
        {
            System.out.println("Parent 1: " + parent1.name);
        }
        if (parent2 != null)
        {
            System.out.println("Parent 2: " + parent2.name);
        }
        System.out.println("Species: " + this.species);
        System.out.print("Element: "+this.element1);

        if (this.element2 != null)
        {
            System.out.println(this.element2);
        }
        else
        {
            System.out.println();
        }

        this.printMoves();

        System.out.println("Level: " + this.level);
        System.out.println("Experience: " + this.experience);
        System.out.println("Cummulative Experience: " + this.cummulative_experience);
    }
    public void printMoves()
    {
        System.out.println("Moves: ");

        for (int i = 0; i < 4; i++) {
            if (moves[i] != null)
            {
                System.out.println("- " + moves[i].getSkillName());
            }
        }
    }
    public abstract void gainExp(int exp);

    public void learnMove(String move)
    {
        if (database_skill.isValid(move,this))
        {
            int idx = -1;
            for (int i = 0; i < 4; i++) {

                if (moves[i].getSkillName().equals(move))
                {
                    break;
                }

                if (moves[i] != null)
                {
                    idx = i;
                    break;
                }
            }

            if (idx == 3)
            {
                System.out.println("Sudah penuh");
            }
            else if(idx == -1)
            {
                System.out.println("Skill telah dipelajari");
            }
            else
            {
                moves[idx] = new skill(database_skill.find(move));
            }
        }
    }
    public void setSkill (skill x, int idx, int mastery_level)
    {
        x.setMasteryLv(mastery_level);
        this.moves[idx] = x;
    }
    public void cry()
    {
        System.out.println(  this.name+ ": " + database_engimon.cry(this.species));
    }


    public void setActive()
    {
        this.active = true;
    }
    public void setInactive()
    {
        this.active = false;
    }
    public Boolean isActive()
    {
        return this.active;
    }
    public String getName()
    {
        return name;
    }
    public String getSpecies()
    {
        return species;
    }
    public int getLevel()
    {
        return this.level;
    }
    public abstract void setLevel(int lv);

    public int getCummulativeExp()
    {
        return this.cummulative_experience;
    }

    public String getElmt1()
    {
        return this.element1;
    }
    public String getElmt2()
    {
        return this.element2;
    }
    public skill getMove(int idx)
    {
        return this.moves[idx];
    }


}
