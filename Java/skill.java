import java.util.ArrayList;
import java.util.List;

public class skill {
    private String skillName;
    private int base_power;
    private int mastery_level;
    private int amount_in_inventory;
    private String engimon_unique = null;
    private List<String> elements;

    public skill()
    {
        this.skillName = "null";
        this.base_power = 0;
        this.mastery_level = 0;
        this.amount_in_inventory = 0;
    }

    public skill (skill s)
    {
        this.skillName = s.skillName;
        this.base_power= s.base_power;
        this.mastery_level = s.mastery_level;
        this.elements = new ArrayList<>(s.elements);
        this.amount_in_inventory = s.amount_in_inventory;
        this.engimon_unique = s.engimon_unique;
    }

    public skill(String nm, int bp, int mastery_lvl, String[] elem)
    {
        this.skillName = nm;
        this.base_power = bp;
        if (this.mastery_level > 3){
            this.mastery_level = 3;
        }
        else if (this.mastery_level < 0)
        {
            this.mastery_level = 0;
        }
        else
        {
            this.mastery_level = mastery_lvl;
        }

        this.amount_in_inventory = 0;
        this.elements = new ArrayList<>();

        //Elements yang dapat mempelajari skill ini
        //Hanya bisa berupa Fire, Water, Electric, Ground, Ice

        int i;
        for (i = 0; i < elem.length; ++i)
        {
            if (elem[i].equals("Fire") || elem[i].equals("Water") || elem[i].equals("Electric") || elem[i].equals("Ground") || elem[i].equals("Ice"))
            {
                this.elements.add(elem[i]);
            }
        }
    }

    public skill(String nm, int bp, int mastery_lvl, String[] elem, String engimon_name /*Skill unik engimon engimon_name*/)
    {
        this(nm, bp, mastery_lvl, elem);
        //JIka ada value nama engimon, maka hanya bisa dipelajari engimon tersebut
        //Jika tidak unique, isi 0
        this.engimon_unique = engimon_name;
    }

    public String getSkillName()
    {
        return this.skillName;
    }
    public String getEngimonSpecies()
    {
        return this.engimon_unique;
    }
    public int getAmountInInventory()
    {
        return this.amount_in_inventory;
    }

    public void setAmountInInventory(int amount_in_inventory) {
        this.amount_in_inventory = amount_in_inventory;
    }

    public List<String> getElements()
    {
        return this.elements;
    }

    public int getBasePower() {
        return base_power;
    }

    public String getUniqueEngimonName() {
        return engimon_unique;
    }

    public int getMasteryLv() {
        if (this.mastery_level > 3)
        {
            this.mastery_level = 3;
        }
        return mastery_level;
    }

    public void setMasteryLv(int mastery_level) {
        if (mastery_level > 3)
        {
            this.mastery_level = 3;
        }
        else if (mastery_level < 0)
        {
            this.mastery_level = 0;
        }
        else
        {
            this.mastery_level = mastery_level;
        }
    }

    public void printAll()
    {
        System.out.println("Name: "+this.skillName);
        System.out.println("Base Power: "+this.base_power);
        System.out.println("Mastery Level: "+this.mastery_level);
        System.out.print("Elements: ");
        for (String i:elements) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println("");

        if (engimon_unique != null)
        {
            System.out.println("SKill unique to: "+this.engimon_unique);
        }
    }
}
