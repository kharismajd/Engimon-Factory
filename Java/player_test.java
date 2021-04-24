public class player_test {
    public static void main (String args[]) {
        try {
            engimon null_engimon = new wild_engimon();
            player_engimon dad = new player_engimon("Dad", null_engimon, null_engimon, "Lapras", 32, 100);
            player_engimon test = new player_engimon("test1", null_engimon, null_engimon, "Vulpichu", 32, 100);
            player_engimon test2 = new player_engimon("test2", null_engimon, null_engimon, "Lapras", 40, 100);
            player_engimon test3 = new player_engimon("test3", null_engimon, null_engimon, "Lapras", 80, 100);
            player newp = new player("Test", dad, 50, 0, 0, 0, 1);

            String[] sp = {"Fire"};
            skill test_skill = new skill("ember", 80, 0, sp);
            skill test_skill2 = new skill("tackle", 50, 0, sp);
            skill test_skill3 = new skill("bubble", 100, 0, sp);
            skill test_skill4 = new skill("rock_smash", 24, 0, sp);

            newp.addInventoryContent(test);
            newp.addInventoryContent(test2);
            newp.addInventoryContent(test3);
            newp.showEngimonList();
            newp.deleteActiveEngimon();
            newp.showEngimonList();

            newp.addInventoryContent(test_skill);
            newp.addInventoryContent(test_skill2);
            newp.addInventoryContent(test_skill3);
            newp.addInventoryContent(test_skill4);
            newp.showSkillItemList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}