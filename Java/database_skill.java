import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class database_skill implements Database <skill>{

    List<skill> skill_database = new ArrayList<skill>();

    public database_skill()
    {
        this.IntializeDatabase();
    }

    //See if a skill exist
    public Boolean isExist(String name)
    {
        for (skill s: skill_database) {
            if (s.getSkillName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    //See if a skill is valid with the following parameters
    public Boolean isValid(String move, String species, String elem1, String elem2)
    {
        skill s;
        s = this.find(move);

        if (s.getEngimonSpecies() != null)
        {
            return s.getEngimonSpecies().equals(species);
        }

        for (String elem:s.getElements()) {
            if (elem.equals(elem1))
            {
                return true;
            }
            if (elem.equals(elem2))
            {
                return true;
            }
        }
        return false;
    }

    public Boolean isValid(String move, engimon e)
    {
        return isValid(move, e.species, e.element1, e.element2);
    }

    //Find the skill with name @param name
    public skill find(String skillName)
    {
        for (skill s: skill_database){
            if (s.getSkillName().equals(skillName))
            {
                return new skill(s);
            }
        }
        return null;
    }

    //Pure random, can be unique, all elements
    public skill random()
    {
        Random rand = new Random();

        int rand_int = rand.nextInt(skill_database.size());
        return skill_database.get(rand_int);
    }

    //random will only give non-unique skill
    public skill random(String elem) throws Exception
    {
        Random rand = new Random();
        int rand_int = rand.nextInt(skill_database.size()) + 1; /*+1 agar tidak keluar null*/
        int whileCounter = 50;

        while (rand_int > 0 && whileCounter > 0)
        {
            for (skill s:skill_database)
            {
                if (s.getElements().contains(elem) && s.getUniqueEngimonName() == null)
                {
//                    System.out.println(s.getSkillName());
//                    System.out.println(rand_int);
                    rand_int--;
                    if (rand_int <= 0)
                    {
                        return new skill(s);
                    }
                }
            }
            whileCounter--;
        }
//        throw new Exception("null");
        return null;
    }

    //Random 2 element
    public skill random(String elem1, String elem2) throws Exception
    {
        Random rand = new Random();
        int rand_int = rand.nextInt(2);

        if (rand_int == 1 && elem2 != null)
        {
//            try
//            {
//                return random(elem1);
//            }
//            catch (Exception e)
//            {
//                throw e;
//            }
            return random(elem2);
        }
        else
        {
//            try
//            {
//                return random(elem2);
//            }
//            catch (Exception e)
//            {
//                throw e;
//            }
            return random(elem1);
        }
    }

    //Find a unique skill for a certain engimon
    public skill skillUniqueEngimon(String speciesEngimon)
    {
        for (skill s: skill_database) {
            if (s.getEngimonSpecies() != null)
                {
                if (s.getEngimonSpecies().equals(speciesEngimon)) {
                    return s;
                }
            }
        }

        return null;
    }

    //Overloading for skillUniqueEngimon
    public skill skillUniqueEngimon(species speciesEngimon)
    {
        return this.skillUniqueEngimon(speciesEngimon.speciesName);
    }


    public void IntializeDatabase()
    {
        String[] element_tackle = {"Fire", "Water", "Electric", "Ground", "Ice"};
        skill tackle = new skill("tackle", 50, 1, element_tackle);
        skill_database.add(tackle);

        String[] element_ember = {"Fire"};
        skill ember = new skill("ember", 80, 1, element_ember);
        skill_database.add(ember);

        String[] element_bubble = {"Water"};
        skill bubble = new skill("bubble", 80, 1, element_bubble);
        skill_database.add(bubble);

        String[] element_rock_smash = {"Ground"};
        skill rock_smash = new skill("rock_smash", 80, 1, element_rock_smash);
        skill_database.add(rock_smash);

        String[] element_thunderbolt = {"Electric"};
        skill thunderbolt = new skill("thunderbolt", 80, 1, element_thunderbolt);
        skill_database.add(thunderbolt);

        String[] element_ice_beam = {"Ice"};
        skill ice_beam = new skill("ice_beam", 80, 1, element_ice_beam);
        skill_database.add(ice_beam);

        String[] element_charred = {"Fire"};
        skill charred = new skill("charred", 80, 1, element_charred, "Charmander");
        skill_database.add(charred);

        String[] element_splash = {"Water"};
        skill splash = new skill("splash", 5, 1, element_splash, "Magikarp");
        skill_database.add(splash);

        String[] element_electrified_tail = {"Electric"};
        skill electrified_tail = new skill("electrified_tail", 80, 1, element_electrified_tail, "Pikachu");
        skill_database.add(electrified_tail);

        String[] element_rock_punch = {"Ground"};
        skill rock_punch = new skill("rock_punch", 80, 1, element_rock_punch, "Geodude");
        skill_database.add(rock_punch);

        String[] element_snowball = {"Ice"};
        skill snowball = new skill("snowball", 80, 1, element_snowball, "Glastrier");
        skill_database.add(snowball);

        String[] element_overload = {"Fire", "Electric"};
        skill overload = new skill("overload", 80, 1, element_overload, "Vulpichu");
        skill_database.add(overload);

        String[] element_ice_breath = {"Water", "Ice"};
        skill ice_breath = new skill("ice_breath", 80, 1, element_ice_breath, "Lapras");
        skill_database.add(ice_breath);

        String[] element_mudball = {"Water", "Ground"};
        skill mudball = new skill("mudball", 80, 1, element_mudball, "Wooper");
        skill_database.add(mudball);
    }
}
