import java.util.Random;

public class wild_engimon extends engimon{

    private static String randSpecies = database_engimon.random().speciesName;
    private static int randLevel = randomInteger(80,80) ;

    private static void randomize()
    {
        randSpecies = database_engimon.random().speciesName;
        randLevel = randomInteger(80,100);
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
        super(this.randSpecies, null, null, this.randSpecies, randLevel,0);
        wild_engimon.randomize();
    }

//    public wild_engimon(String tiletype) throws Exception
//    {
//
//    }

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
