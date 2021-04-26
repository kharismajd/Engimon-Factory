import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.awt.GridLayout;

public class skill_icons {

    public static JPanel showIcons(engimon e) {
        skill_icons x = new skill_icons();

        // Create new panel
        JPanel a = new JPanel(new GridLayout(0, 4));
        a.setBounds(0, 0, 350, 150);

        // Add skills
        for (int i = 0; i < 4; i++) {
            if (e.getMove(i) != null) {
                JPanel panel1 = new JPanel();
                panel1.setBackground(Color.black);
                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.black);

                String path = x.getImagePath(e.getMove(i));
                ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                JLabel lbl = new JLabel(img);

                JLabel name = new JLabel(e.getMove(i).getSkillName());
                name.setForeground(Color.white);
                JLabel base = new JLabel(((Integer) e.getMove(i).getBasePower()).toString());
                base.setForeground(Color.white);

                panel1.add(lbl);
                panel2.add(name);
                panel2.add(base);
                a.add(panel1);
                a.add(panel2);
            }
        }
        return a;
    }

    public String getImagePath(skill s) {
        return "skills/" + s.getSkillName() + " (" + s.getMasteryLv() + ").png";
    }
}
