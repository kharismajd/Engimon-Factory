import java.util.Random;

public class wild_engimon extends engimon{

    public static int playerHighestPokemon = 30;
    private static species randSpecies = database_engimon.random();
    private static int randLevel = randomInteger(playerHighestPokemon,playerHighestPokemon+10) ;
    private static int extraMove = randomInteger(0,1);


    public static void setPlayerHighestPokemon(int playerHighestPokemon) {
        wild_engimon.playerHighestPokemon = playerHighestPokemon;
    }

    private static void randomize()
    {
        randSpecies = database_engimon.random();
        if (playerHighestPokemon + 10 < 100)
        {
            randLevel = randomInteger(playerHighestPokemon, playerHighestPokemon+10);
        }
        else
        {
            randLevel = randomInteger(playerHighestPokemon, 100);
        }
    }

    private static void randomize(String tiletype)
    {
        try
        {
            randSpecies = database_engimon.random(tiletype);
            if (playerHighestPokemon + 10 < 100)
            {
                randLevel = randomInteger(playerHighestPokemon, playerHighestPokemon+10);
            }
            else
            {
                randLevel = randomInteger(playerHighestPokemon, 100);
            }
        }
        catch (NullPointerException n)
        {
            n.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Return a random int between start <= x <= end
    private static int randomInteger(int start, int end)
    {
        Random rand = new Random();

        int rand_int = rand.nextInt(end-start+1);
        return rand_int + start;
    }

    public wild_engimon() throws Exception
    {
        //Bisa terjadi null
        wild_engimon.randomize();
        this.Initialize(randSpecies.speciesName, null, null, randSpecies.speciesName, randLevel,0);
        assert (this != null);
        assert (this.moves != null);
        if (wild_engimon.extraMove == 1)
        {
            this.learnMove(database_skill.random(randSpecies.element1,randSpecies.element2).getSkillName());
        }
        this.life = 1;
    }

    public wild_engimon(String species, int level, int extraMove) throws  Exception
    {
        this.Initialize(species, null, null, species, level,0);
        species s = database_engimon.find(species);
        assert (this != null);
        assert (this.moves != null);
        if (extraMove == 1)
        {
            String skillName = database_skill.random(s.element1,s.element2).getSkillName();
            this.learnMove(skillName);
        }
        this.life = 1;
    }


    public wild_engimon(String tiletype) throws Exception
    {
        wild_engimon.randomize(tiletype);
        this.Initialize(randSpecies.speciesName, null, null, randSpecies.speciesName, randLevel,0);
        assert (this != null);
        assert (this.moves != null);
        if (wild_engimon.extraMove == 1)
        {
            this.learnMove(database_skill.random(randSpecies.element1,randSpecies.element2).getSkillName());
        }
        this.life = 1;
    }

    public void gainExp(int exp)
    {
        this.experience = exp % 100;
        this.level = this.level + exp/100;
        this.cummulative_experience = this.cummulative_experience + exp;
    }
    public void setLevel(int lv)
    {
        this.level = lv;
        this.cummulative_experience = this.level + this.experience;
    }

    public void learnMove(String move)
    {
        if (database_skill.isValid(move,this))
        {
            int idx = -1;
            for (int i = 0; i < 4; i++) {

                if (moves[i] == null)
                {
                    idx = i;
                    break;
                }

                if (moves[i].getSkillName().equals(move))
                {
                    break;
                }


            }

            if (idx == 3)
            {

            }
            else if(idx == -1)
            {

            }
            else
            {
                moves[idx] = new skill(database_skill.find(move));
            }
        }
    }
}
