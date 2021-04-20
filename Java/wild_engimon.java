import java.util.Random;

public class wild_engimon extends engimon{

    private static int playerHighestPokemon = 80;
    private static String randSpecies = database_engimon.random().speciesName;
    private static int randLevel = randomInteger(playerHighestPokemon,80) ;


    private static void randomize()
    {
        randSpecies = database_engimon.random().speciesName;
        randLevel = randomInteger(playerHighestPokemon,100);
    }

    private static void randomize(String tiletype)
    {
        try
        {
            randSpecies = database_engimon.random(tiletype).speciesName;
            randLevel = randomInteger(playerHighestPokemon,100);
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
        wild_engimon.randomize();
        this.Initialize(randSpecies, null, null, randSpecies, randLevel,0);
        this.life = 1;
    }

    public wild_engimon(String tiletype) throws Exception
    {
        wild_engimon.randomize(tiletype);
        this.Initialize(randSpecies, null, null, randSpecies, randLevel,0);
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
}
