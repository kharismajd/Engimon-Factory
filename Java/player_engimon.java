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

//            if (idx == 3)
//            {
//                System.out.println("Sudah penuh");
//            }
            if(idx == -1)
            {
                System.out.println("Skill telah dipelajari atau sudah penuh");
            }
            else
            {
                moves[idx] = new skill(database_skill.find(move));
            }
        }
    }
}
