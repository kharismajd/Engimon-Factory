import javax.swing.*;

public class skill_icons_test {
    public static void main (String args[]) {
        try {
            engimon e = new player_engimon("HelloWorld", null, null, "Vulpichu", 2, 0);
            e.learnMove("tackle");
            e.learnMove("ember");
            e.learnMove("thunderbolt");
            e.showAttributes();

            JFrame f = new JFrame("Panel");
            JPanel skills = skill_icons.showIcons(e);
            f.add(skills);
            f.setSize(365, 190);
            f.setLayout(null);
            f.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}