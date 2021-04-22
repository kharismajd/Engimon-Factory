import java.util.*;

public class database_engimon implements Database <species>{

    Set<species> all_species = new HashSet<species>();
    Map<String,String> engimon_cry = new HashMap<>();

    public database_engimon()
    {
        this.IntializeDatabase();
    }

    public Boolean isExist(String species)
    {
        for (species s:all_species) {
            if (s.speciesName.equals(species))
            {
                return true;
            }
        }
        return false;
    }

    public species find(String name)
    {
        for (species s:all_species) {
            if (s.speciesName.equals(name))
            {
                return s;
            }
        }
        return null;
    }

    public species findByElement(String elem1, String elem2) {
        for (species s:all_species) {
            if ((s.element1.equals(elem1) && s.element2.equals(elem2)) || (s.element1.equals(elem2) && s.element2.equals(elem1))) {
                return s;
            }
        }
        return null;
    }

    public species random()
    {
        Random rand = new Random();

        int rand_int = rand.nextInt(all_species.size());
        species[] tempArray = all_species.toArray(new species[all_species.size()]);
        return tempArray[rand_int];
    }

    public species random(String tiletype) throws Exception
    {
        Random rand = new Random();

        List<String> elements = new ArrayList<>();

        if (tiletype.equals("mountains") )
        {
            elements.add("Fire");
        }
        else if (tiletype.equals("sea"))
        {
            elements.add("Water");
        }
        else if (tiletype.equals("grassland"))
        {
            elements.add("Ground");
            elements.add("Electric");
        }
        else if (tiletype.equals("tundra"))
        {
            elements.add("Ice");
        }
        else
        {
            throw new Exception("Tile type tidak valid");
        }

//        System.out.println(elements);

        int rand_int = rand.nextInt(all_species.size()) + 1;
        species[] tempArray = all_species.toArray(new species[all_species.size()]);

        while( rand_int > 0)
        {
            for (int i = 0; i < tempArray.length; i++) {
                if (elements.contains(tempArray[i].element1))
                {
                    rand_int--;
//                    System.out.println(rand_int);
//                    System.out.println(tempArray[i].speciesName);
                    if (rand_int <= 0)
                    {
                        return tempArray[i];
                    }
                }
                else if (tempArray[i].element2 != null)
                {
                    if (elements.contains(tempArray[i].element2))
                    {
                        rand_int--;
                        if (rand_int <= 0)
                        {
                            return tempArray[i];
                        }
                    }
                }
            }
        }
        return null;
    }

    public String cry(String species)
    {
        return engimon_cry.get(species);
    }

    public void IntializeDatabase()
    {
        all_species.add(new species("Charmander", "Fire", engimon.element_null));
        all_species.add(new species("Magikarp", "Water", engimon.element_null));
        all_species.add(new species("Pikachu", "Electric", engimon.element_null));
        all_species.add(new species("Geodude", "Ground", engimon.element_null));
        all_species.add(new species("Glastrier", "Ice", engimon.element_null));
        all_species.add(new species("Vulpichu", "Fire", "Electric"));
        all_species.add(new species("Lapras", "Water", "Ice"));
        all_species.add(new species("Wooper", "Water", "Ground"));

        engimon_cry.putIfAbsent("Charmander","Char!");
        engimon_cry.putIfAbsent("Magikarp","Splash");
        engimon_cry.putIfAbsent("Pikachu","Pika Pika");
        engimon_cry.putIfAbsent("Geodude","Grunt");
        engimon_cry.putIfAbsent("Glastrier","Neigh");
        engimon_cry.putIfAbsent("Vulpichu","Vulka!");
        engimon_cry.putIfAbsent("Lapras","Phwargh");
        engimon_cry.putIfAbsent("Wooper","Chomp!");
    }
}

