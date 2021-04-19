public class Driver {
    public static void main(String[] args) {
        database_skill db_skill = new database_skill();
        String[] elem = {"Fire", "Water", "Electric", "Ice"};
        String[] lol = {"Fire"};
        skill a = new skill("a", 50, 0, lol);
        skill[] s;
        s = new skill[4];
        skill nullSkill = new skill();
        //s[0].printAll();
        s[1] = a;
        s[1].printAll();

        System.out.println("\n");
        try
        {
         s[0] = db_skill.random("Fire","Electric");
         s[0].printAll();
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println(db_skill.isExist("splash"));

    }
}
