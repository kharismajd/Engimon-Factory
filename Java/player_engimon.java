public class player_engimon extends engimon{

    public player_engimon(String nm, engimon pr1, engimon pr2, String sp, int lvl, int exp) throws Exception
    {
        super(nm, pr1, pr2, sp, lvl, exp);
    }

    public player_engimon(wild_engimon e)
    {
        this.name = e.name;
        this.parent1 = e.parent1;
        this.parent2 = e.parent2;
        this.species = e.species;
        for (int i = 0; i < 4; i++) {
            this.moves[i] = e.moves[i];
        }
        this.element1 = e.element1;
        this.element2 = e.element2;
        this.level = e.level;
        this.experience = e.experience;
        this.cummulative_experience = e.cummulative_experience;
        this.active = e.active;
        this.life = 3;
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
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
