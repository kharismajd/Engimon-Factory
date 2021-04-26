import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryGUI {
    private JFrame frame;
    private JButton engimonButton;
    private JButton skillButton;
    private JPanel main;
    private JPanel scrollpanel;
    private JScrollPane scroll;
    private JPanel sidePane;
    private player play;
    private String engimonOption[] = {"Breeding", "Ganti nama", "Buang", "Set active"};
    private String skillOption[] = {"Pakai", "Buang"};

    public JPanel getMain()
    {
        return main;
    }


    InventoryGUI(player p)
    {
        this.play = p;
        this.scrollpanel.setLayout(new GridLayout(10, 1));

        this.frame = new JFrame("Inventory");
        this.frame.setContentPane(this.main);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        this.sidePane.setBackground(Color.darkGray);
        this.scrollpanel.setBackground(Color.gray);
        this.main.setBackground(Color.darkGray);

        this.engimonButton.setFocusPainted(false);
        this.engimonButton.setBackground(Color.lightGray);

        this.skillButton.setFocusPainted(false);
        this.skillButton.setBackground(Color.lightGray);

        UIManager um = new UIManager();
        um.put("OptionPane.background", Color.black);
        um.put("OptionPane.messageForeground", Color.white);
        um.put("JPanel.background", Color.black);
        um.put("JPanel.messageForeground", Color.white);
        um.put("Panel.background", Color.black);
        um.put("Panel.messageForeground", Color.black);

        engimonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollpanel.removeAll();
                if (InventoryGUI.this.play.engimon_inventory.getContents().size() > 10)
                {
                    scrollpanel.setLayout(new GridLayout(InventoryGUI.this.play.engimon_inventory.getContents().size(), 1));
                }
                else
                {
                    scrollpanel.setLayout(new GridLayout(10, 1));
                }
                int i = 0;
                while (i < InventoryGUI.this.play.engimon_inventory.getContents().size())
                {
                    int pilihan = i;
                    String fill;
                    fill = (i + 1) + ". " + play.engimon_inventory.getContents().get(i).getName() + " (" + play.engimon_inventory.getContents().get(i).getSpecies() + "/" + play.engimon_inventory.getContents().get(i).getElmt1();
                    if (play.engimon_inventory.getContents().get(i).getElmt2() != null)
                    {
                        fill += "/" + play.engimon_inventory.getContents().get(i).getElmt2();
                    }
                    fill += "/lv " + play.engimon_inventory.getContents().get(i).getLevel().toString() + ")";
                    if (play.engimon_inventory.getContents().get(i).isActive())
                    {
                        fill +=" [active]";
                    }
                    JButton b = new JButton(fill);
                    b.setFont(Font.getFont("Fira Code Retina Regular"));
                    b.setBackground(Color.lightGray);
                    b.setFocusPainted(false);
                    b.setHorizontalAlignment(SwingConstants.LEFT);
                    b.setPreferredSize(new Dimension(100, 55));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).isActive())
                            {
                                engimonOption[3] = "Set inactive";
                            }
                            else
                            {
                                engimonOption[3] = "Set active";
                            }
                            JPanel optionPane = new JPanel();
                            String optionPaneFill = "";
                            optionPaneFill = "Engimon name: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).getName() + "<br/>";
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent1 != null)
                            {
                                optionPaneFill += "Parent 1: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent1.name + "<br/>";
                            }
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent2 != null)
                            {
                                optionPaneFill += ("Parent 2: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent2.name + "<br/>");
                            }
                            optionPaneFill += "Species: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).species + "<br/>";
                            optionPaneFill += "Element: "+ InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element1;

                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element2 != null)
                            {
                                optionPaneFill += "/" + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element2 + "<br/>";
                            }
                            else
                            {
                                optionPaneFill += "<br/>";
                            }

                            optionPaneFill += "Level: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).level + "<br/>";
                            optionPaneFill += "Experience: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).experience + "<br/>";
                            optionPaneFill += "Cummulative Experience: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).cummulative_experience;

                            //for (int skillIndex = 0; skillIndex < 4; skillIndex++)
                            //{
                            //    if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).moves[skillIndex] != null)
                            //    {
                            //        optionPaneFill += "- " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).moves[skillIndex].getSkillName() + "<br/>";
                            //    }
                            //}
                            //this.printMoves();
                            JLabel descLabel = new JLabel("<html>" + optionPaneFill + "</html>");
                            JLabel moves = new JLabel("Moves: ");
                            descLabel.setForeground(Color.white);
                            descLabel.setFont(Font.getFont("Fira Code Retina Regular"));
                            moves.setForeground(Color.white);
                            moves.setFont(Font.getFont("Fira Code Retina Regular"));
                            optionPane.add(descLabel);
                            optionPane.add(moves);
                            optionPane.add(skill_icons.showIcons(InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan)));
                            int n = JOptionPane.showOptionDialog(null,
                                    optionPane,
                                    "Engimon",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    engimonOption,
                                    engimonOption[0]);
                            if (n == 0) {
                                JPanel engimonSelectFill = new JPanel();
                                JDialog d = new JDialog() {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                engimonSelectFill.setLayout(new GridLayout(0, 1));
                                int j = 0;
                                while (j < InventoryGUI.this.play.engimon_inventory.getContents().size()) {
                                    int pilihan2 = j;
                                    String fill2;
                                    fill2 = (j + 1) + ". " + play.engimon_inventory.getContents().get(j).getName() + " (" + play.engimon_inventory.getContents().get(j).getSpecies() + "/" + play.engimon_inventory.getContents().get(j).getElmt1();
                                    if (play.engimon_inventory.getContents().get(j).getElmt2() != null) {
                                        fill2 += "/" + play.engimon_inventory.getContents().get(j).getElmt2();
                                    }
                                    fill2 += "/lv " + play.engimon_inventory.getContents().get(j).getLevel().toString() + ")";
                                    if (play.engimon_inventory.getContents().get(j).isActive()) {
                                        fill2 += " [active]";
                                    }
                                    JButton button = new JButton(fill2);
                                    button.setFont(Font.getFont("Fira Code Retina Regular"));
                                    button.setBackground(Color.lightGray);
                                    button.setFocusPainted(false);
                                    button.setHorizontalAlignment(SwingConstants.LEFT);
                                    button.setPreferredSize(new Dimension(100, 40));
                                    button.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                String engimonName = JOptionPane.showInputDialog(null,
                                                        "Masukkan nama engimon baru");

                                                if (engimonName.length() != 0)
                                                {
                                                    if (pilihan == pilihan2)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Tidak bisa breeding engimon yang sama", "Warning", JOptionPane.WARNING_MESSAGE);
                                                    }
                                                    else
                                                    {
                                                        InventoryGUI.this.play.breeding(pilihan, pilihan2, engimonName);
                                                        d.setVisible(false);
                                                    }
                                                }
                                                engimonButton.doClick();
                                            }
                                            catch(NullPointerException exception)
                                            {

                                            }
                                            catch(Exception exception)
                                            {
                                                JOptionPane.showMessageDialog(null, "Level engimon harus lebih dari 3", "Warning", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    });

                                    engimonSelectFill.add(button);
                                    j++;
                                }
                                JScrollPane engimonSelect = new JScrollPane(engimonSelectFill) {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                d.setTitle("Pilih pasangan untuk breeding");
                                d.add(new JLabel("Pilih engimon untuk breeding"));
                                d.setFont(Font.getFont("Fira Code Retina Regular"));
                                d.add(engimonSelect);
                                d.setLocationRelativeTo(frame);
                                d.setVisible(true);
                                d.pack();
                            }
                            if (n == 1)
                            {
                                String name = JOptionPane.showInputDialog(null,
                                            "Masukkan nama engimon baru");

                                if (name.length() != 0)
                                {
                                    InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).setName(name);
                                }
                            }
                            else if (n == 2)
                            {
                                int buangOption = JOptionPane.showConfirmDialog(
                                        null, "Apakah kamu yakin ingin membuang " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).getName() + "?",
                                        "Buang engimon",
                                        JOptionPane.YES_NO_OPTION);

                                if (buangOption == JOptionPane.YES_OPTION)
                                {
                                    InventoryGUI.this.play.deleteEngimonSelect(pilihan);
                                }
                            }
                            else if (n == 3)
                            {
                                if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).isActive())
                                {
                                    InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).setInactive();
                                }
                                else
                                {
                                    InventoryGUI.this.play.switchOutEngimon(pilihan);
                                }
                            }

                            engimonButton.doClick();
                        }
                    });
                    scrollpanel.add(b);
                    i++;
                }
                scrollpanel.revalidate();
                scrollpanel.repaint();
            }
        });

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollpanel.removeAll();
                if (InventoryGUI.this.play.skill_inventory.getContents().size() > 10)
                {
                    scrollpanel.setLayout(new GridLayout(InventoryGUI.this.play.skill_inventory.getContents().size(), 1));
                }
                else
                {
                    scrollpanel.setLayout(new GridLayout(10, 1));
                }
                int i = 0;
                while (i < InventoryGUI.this.play.skill_inventory.getContents().size())
                {
                    int pilihan = i;
                    String fill;
                    fill = (i + 1) + ". " + play.skill_inventory.getContents().get(i).getSkillName() + " (base power: " + Integer.toString(play.skill_inventory.getContents().get(i).getBasePower()) + ") [" + Integer.toString(play.skill_inventory.getContents().get(i).getAmountInInventory()) + "]";
                    JButton b = new JButton(fill);
                    b.setFont(Font.getFont("Fira Code Retina Regular"));
                    b.setBackground(Color.lightGray);
                    b.setFocusPainted(false);
                    b.setHorizontalAlignment(SwingConstants.LEFT);
                    b.setPreferredSize(new Dimension(100, 55));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel optionPane = new JPanel();
                            String optionPaneFill = "";
                            optionPaneFill += "Name: "+ InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getSkillName() + "<br/>";
                            optionPaneFill += "Base Power: " +InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getBasePower() + "<br/>";
                            optionPaneFill += "Mastery Level: "+InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getMasteryLv() + "<br/>";
                            optionPaneFill += "Elements: " + "<br/>";
                            for (String i:InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getElements()) {
                                optionPaneFill += "- " + i + "<br/>";
                            }

                            if (InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getUniqueEngimonName() != null)
                            {
                                optionPaneFill += "SKill unique to: " + InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getUniqueEngimonName();
                            }

                            JLabel skillDesc = new JLabel("<html>" + optionPaneFill + "</html>");
                            skillDesc.setForeground(Color.white);
                            skillDesc.setFont(Font.getFont("Fira Code Retina Regular"));
                            optionPane.add(skillDesc);
                            int n = JOptionPane.showOptionDialog(null,
                                    optionPane,
                                    "Skill item",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    skillOption,
                                    skillOption[0]);

                            if (n == 0) {
                                JPanel engimonSelectFill = new JPanel();
                                JDialog d = new JDialog() {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                engimonSelectFill.setLayout(new GridLayout(0, 1));
                                int j = 0;
                                while (j < InventoryGUI.this.play.engimon_inventory.getContents().size()) {
                                    int pilihan2 = j;
                                    String fill2;
                                    fill2 = (j + 1) + ". " + play.engimon_inventory.getContents().get(j).getName() + " (" + play.engimon_inventory.getContents().get(j).getSpecies() + "/" + play.engimon_inventory.getContents().get(j).getElmt1();
                                    if (play.engimon_inventory.getContents().get(j).getElmt2() != null) {
                                        fill2 += "/" + play.engimon_inventory.getContents().get(j).getElmt2();
                                    }
                                    fill2 += "/lv " + play.engimon_inventory.getContents().get(j).getLevel().toString() + ")";
                                    if (play.engimon_inventory.getContents().get(j).isActive()) {
                                        fill2 += " [active]";
                                    }
                                    JButton button = new JButton(fill2);
                                    button.setFont(Font.getFont("Fira Code Retina Regular"));
                                    button.setBackground(Color.lightGray);
                                    button.setFocusPainted(false);
                                    button.setHorizontalAlignment(SwingConstants.LEFT);
                                    button.setPreferredSize(new Dimension(100, 40));
                                    button.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                InventoryGUI.this.play.useSkillItem(pilihan, pilihan2);
                                                d.setVisible(false);
                                                skillButton.doClick();
                                            }
                                            catch(Exception exception)
                                            {
                                                JOptionPane.showMessageDialog(null, "Elemen engimon tidak cocok", "Warning", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    });

                                    engimonSelectFill.add(button);
                                    j++;
                                }
                                JScrollPane engimonSelect = new JScrollPane(engimonSelectFill) {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                d.setTitle("Pilih engimon untuk learn move");
                                d.add(new JLabel("Pilih engimon untuk learn move"));
                                d.setFont(Font.getFont("Fira Code Retina Regular"));
                                d.add(engimonSelect);
                                d.setLocationRelativeTo(frame);
                                d.setVisible(true);
                                d.pack();
                            }

                            if (n == 1)
                            {
                                int amount = Integer.parseInt( JOptionPane.showInputDialog(null,
                                        "Berapa banyak yang ingin dibuang?",
                                        "0"));

                                if (amount > 0)
                                {
                                    InventoryGUI.this.play.deleteInventoryContent(InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getSkillName(), amount);
                                }
                            }
                            skillButton.doClick();
                        }
                    });
                    scrollpanel.add(b);
                    i++;
                }
                scrollpanel.revalidate();
                scrollpanel.repaint();
            }
        });
    }

    public InventoryGUI(player p, JFrame frame)
    {
        this.play = p;
        this.scrollpanel.setLayout(new GridLayout(10, 1));

        this.frame = frame;

        engimonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollpanel.removeAll();
                if (InventoryGUI.this.play.engimon_inventory.getContents().size() > 10)
                {
                    scrollpanel.setLayout(new GridLayout(InventoryGUI.this.play.engimon_inventory.getContents().size(), 1));
                }
                else
                {
                    scrollpanel.setLayout(new GridLayout(10, 1));
                }
                int i = 0;
                while (i < InventoryGUI.this.play.engimon_inventory.getContents().size())
                {
                    int pilihan = i;
                    String fill;
                    fill = (i + 1) + ". " + play.engimon_inventory.getContents().get(i).getName() + " (" + play.engimon_inventory.getContents().get(i).getSpecies() + "/" + play.engimon_inventory.getContents().get(i).getElmt1();
                    if (play.engimon_inventory.getContents().get(i).getElmt2() != null)
                    {
                        fill += "/" + play.engimon_inventory.getContents().get(i).getElmt2();
                    }
                    fill += "/lv " + play.engimon_inventory.getContents().get(i).getLevel().toString() + ")";
                    if (play.engimon_inventory.getContents().get(i).isActive())
                    {
                        fill +=" [active]";
                    }
                    JButton b = new JButton(fill);
                    b.setFont(Font.getFont("Fira Code Retina Regular"));
                    b.setHorizontalAlignment(SwingConstants.LEFT);
                    b.setPreferredSize(new Dimension(100, 55));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).isActive())
                            {
                                engimonOption[3] = "Set inactive";
                            }
                            else
                            {
                                engimonOption[3] = "Set active";
                            }
                            JPanel optionPane = new JPanel();
                            String optionPaneFill = "";
                            optionPaneFill = "Engimon name: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).getName() + "<br/>";
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent1 != null)
                            {
                                optionPaneFill += "Parent 1: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent1.name + "<br/>";
                            }
                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent2 != null)
                            {
                                optionPaneFill += ("Parent 2: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).parent2.name + "<br/>");
                            }
                            optionPaneFill += "Species: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).species + "<br/>";
                            optionPaneFill += "Element: "+ InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element1;

                            if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element2 != null)
                            {
                                optionPaneFill += "/" + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).element2 + "<br/>";
                            }
                            else
                            {
                                optionPaneFill += "<br/>";
                            }

                            optionPaneFill += "Moves: <br/>";

                            for (int skillIndex = 0; skillIndex < 4; skillIndex++)
                            {
                                if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).moves[skillIndex] != null)
                                {
                                    optionPaneFill += "- " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).moves[skillIndex].getSkillName() + "<br/>";
                                }
                            }
                            //this.printMoves();

                            optionPaneFill += "Level: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).level + "<br/>";
                            optionPaneFill += "Experience: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).experience + "<br/>";
                            optionPaneFill += "Cummulative Experience: " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).cummulative_experience;

                            JLabel descLabel = new JLabel("<html>" + optionPaneFill + "</html>");
                            JLabel moves = new JLabel("Moves: ");
                            descLabel.setForeground(Color.white);
                            descLabel.setFont(Font.getFont("Fira Code Retina Regular"));
                            moves.setForeground(Color.white);
                            moves.setFont(Font.getFont("Fira Code Retina Regular"));
                            optionPane.add(descLabel);
                            optionPane.add(moves);
                            optionPane.add(skill_icons.showIcons(InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan)));
                            int n = JOptionPane.showOptionDialog(null,
                                    optionPane,
                                    "Engimon",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    engimonOption,
                                    engimonOption[0]);
                            if (n == 0) {
                                JPanel engimonSelectFill = new JPanel();
                                JDialog d = new JDialog() {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                engimonSelectFill.setLayout(new GridLayout(0, 1));
                                int j = 0;
                                while (j < InventoryGUI.this.play.engimon_inventory.getContents().size()) {
                                    int pilihan2 = j;
                                    String fill2;
                                    fill2 = (j + 1) + ". " + play.engimon_inventory.getContents().get(j).getName() + " (" + play.engimon_inventory.getContents().get(j).getSpecies() + "/" + play.engimon_inventory.getContents().get(j).getElmt1();
                                    if (play.engimon_inventory.getContents().get(j).getElmt2() != null) {
                                        fill2 += "/" + play.engimon_inventory.getContents().get(j).getElmt2();
                                    }
                                    fill2 += "/lv " + play.engimon_inventory.getContents().get(j).getLevel().toString() + ")";
                                    if (play.engimon_inventory.getContents().get(j).isActive()) {
                                        fill2 += " [active]";
                                    }
                                    JButton button = new JButton(fill2);
                                    button.setFont(Font.getFont("Fira Code Retina Regular"));
                                    button.setHorizontalAlignment(SwingConstants.LEFT);
                                    button.setPreferredSize(new Dimension(100, 40));
                                    button.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                String engimonName = JOptionPane.showInputDialog(null,
                                                        "Masukkan nama engimon baru");

                                                if (engimonName.length() != 0)
                                                {
                                                    if (pilihan == pilihan2)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Tidak bisa breeding engimon yang sama", "Warning", JOptionPane.WARNING_MESSAGE);
                                                    }
                                                    else
                                                    {
                                                        InventoryGUI.this.play.breeding(pilihan, pilihan2, engimonName);
                                                        d.setVisible(false);
                                                    }
                                                }
                                                engimonButton.doClick();
                                            }
                                            catch(Exception exception)
                                            {
                                                JOptionPane.showMessageDialog(null, "Level engimon harus diatas level 3", "Warning", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    });

                                    engimonSelectFill.add(button);
                                    j++;
                                }
                                JScrollPane engimonSelect = new JScrollPane(engimonSelectFill) {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                d.setTitle("Pilih pasangan untuk breeding");
                                d.add(new JLabel("Pilih engimon untuk breeding"));
                                d.setFont(Font.getFont("Fira Code Retina Regular"));
                                d.add(engimonSelect);
                                d.setLocationRelativeTo(frame);
                                d.setVisible(true);
                                d.pack();
                            }
                            if (n == 1)
                            {
                                String name = JOptionPane.showInputDialog(null,
                                        "Masukkan nama engimon baru");

                                if (name.length() != 0)
                                {
                                    InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).setName(name);
                                }
                            }
                            else if (n == 2)
                            {
                                int buangOption = JOptionPane.showConfirmDialog(
                                        null, "Apakah kamu yakin ingin membuang " + InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).getName() + "?",
                                        "Buang engimon",
                                        JOptionPane.YES_NO_OPTION);

                                if (buangOption == JOptionPane.YES_OPTION)
                                {
                                    InventoryGUI.this.play.deleteEngimonSelect(pilihan);
                                }
                            }
                            else if (n == 3)
                            {
                                if (InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).isActive())
                                {
                                    InventoryGUI.this.play.engimon_inventory.getContents().get(pilihan).setInactive();
                                }
                                else
                                {
                                    InventoryGUI.this.play.switchOutEngimon(pilihan);
                                }
                            }

                            engimonButton.doClick();
                        }
                    });
                    scrollpanel.add(b);
                    i++;
                }
                scrollpanel.revalidate();
                scrollpanel.repaint();
            }
        });

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollpanel.removeAll();
                if (InventoryGUI.this.play.skill_inventory.getContents().size() > 10)
                {
                    scrollpanel.setLayout(new GridLayout(InventoryGUI.this.play.skill_inventory.getContents().size(), 1));
                }
                else
                {
                    scrollpanel.setLayout(new GridLayout(10, 1));
                }
                int i = 0;
                while (i < InventoryGUI.this.play.skill_inventory.getContents().size())
                {
                    int pilihan = i;
                    String fill;
                    fill = (i + 1) + ". " + play.skill_inventory.getContents().get(i).getSkillName() + " (base power: " + Integer.toString(play.skill_inventory.getContents().get(i).getBasePower()) + ") [" + Integer.toString(play.skill_inventory.getContents().get(i).getAmountInInventory()) + "]";
                    JButton b = new JButton(fill);
                    b.setFont(Font.getFont("Fira Code Retina Regular"));
                    b.setHorizontalAlignment(SwingConstants.LEFT);
                    b.setPreferredSize(new Dimension(100, 55));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel optionPane = new JPanel();
                            String optionPaneFill = "";
                            optionPaneFill += "Name: "+ InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getSkillName() + "<br/>";
                            optionPaneFill += "Base Power: " +InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getBasePower() + "<br/>";
                            optionPaneFill += "Mastery Level: "+InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getMasteryLv() + "<br/>";
                            optionPaneFill += "Elements: " + "<br/>";
                            for (String i:InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getElements()) {
                                optionPaneFill += "- " + i + "<br/>";
                            }

                            if (InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getUniqueEngimonName() != null)
                            {
                                optionPaneFill += "SKill unique to: " + InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getUniqueEngimonName();
                            }

                            JLabel skillDesc = new JLabel("<html>" + optionPaneFill + "</html>");
                            skillDesc.setFont(Font.getFont("Fira Code Retina Regular"));
                            optionPane.add(skillDesc);
                            int n = JOptionPane.showOptionDialog(null,
                                    optionPane,
                                    "Skill item",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    skillOption,
                                    skillOption[0]);

                            if (n == 0) {
                                JPanel engimonSelectFill = new JPanel();
                                JDialog d = new JDialog() {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                engimonSelectFill.setLayout(new GridLayout(0, 1));
                                int j = 0;
                                while (j < InventoryGUI.this.play.engimon_inventory.getContents().size()) {
                                    int pilihan2 = j;
                                    String fill2;
                                    fill2 = (j + 1) + ". " + play.engimon_inventory.getContents().get(j).getName() + " (" + play.engimon_inventory.getContents().get(j).getSpecies() + "/" + play.engimon_inventory.getContents().get(j).getElmt1();
                                    if (play.engimon_inventory.getContents().get(j).getElmt2() != null) {
                                        fill2 += "/" + play.engimon_inventory.getContents().get(j).getElmt2();
                                    }
                                    fill2 += "/lv " + play.engimon_inventory.getContents().get(j).getLevel().toString() + ")";
                                    if (play.engimon_inventory.getContents().get(j).isActive()) {
                                        fill2 += " [active]";
                                    }
                                    JButton button = new JButton(fill2);
                                    button.setFont(Font.getFont("Fira Code Retina Regular"));
                                    button.setHorizontalAlignment(SwingConstants.LEFT);
                                    button.setPreferredSize(new Dimension(100, 40));
                                    button.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                InventoryGUI.this.play.useSkillItem(pilihan, pilihan2);
                                                d.setVisible(false);
                                                skillButton.doClick();
                                            }
                                            catch(Exception exception)
                                            {
                                                JOptionPane.showMessageDialog(null, "Elemen engimon tidak cocok", "Warning", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    });

                                    engimonSelectFill.add(button);
                                    j++;
                                }
                                JScrollPane engimonSelect = new JScrollPane(engimonSelectFill) {
                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(400, 200);
                                    }
                                };
                                d.setTitle("Pilih engimon untuk learn move");
                                d.add(new JLabel("Pilih engimon untuk learn move"));
                                d.setFont(Font.getFont("Fira Code Retina Regular"));
                                d.add(engimonSelect);
                                d.setLocationRelativeTo(frame);
                                d.setVisible(true);
                                d.pack();
                            }

                            if (n == 1)
                            {
                                int amount = Integer.parseInt( JOptionPane.showInputDialog(null,
                                        "Berapa banyak yang ingin dibuang?",
                                        "0"));

                                if (amount > 0)
                                {
                                    InventoryGUI.this.play.deleteInventoryContent(InventoryGUI.this.play.skill_inventory.getContents().get(pilihan).getSkillName(), amount);
                                }
                            }
                            skillButton.doClick();
                        }
                    });
                    scrollpanel.add(b);
                    i++;
                }
                scrollpanel.revalidate();
                scrollpanel.repaint();
            }
        });
    }



    public static void main(String[] args) {
        try {
            engimon null_engimon = new wild_engimon();
            player_engimon dad = new player_engimon("Dad", null_engimon, null_engimon, "Lapras", 32, 100);
            player_engimon test = new player_engimon("test1", null_engimon, null_engimon, "Vulpichu", 32, 100);
            player_engimon test2 = new player_engimon("test2", null_engimon, null_engimon, "Lapras", 40, 100);
            player_engimon test3 = new player_engimon("test3", null_engimon, null_engimon, "Lapras", 80, 100);
            player newp = new player("Test", dad, 50, 0, 0, 0, 1);

            newp.addInventoryContent(test);
            newp.addInventoryContent(test2);
            newp.addInventoryContent(test3);
            newp.showEngimonList();
            System.out.println(newp.getHighestLevelEngimon());

            newp.addInventoryContent("ember");
            newp.addInventoryContent("tackle");
            newp.addInventoryContent("bubble");
            newp.addInventoryContent("rock_smash");
            newp.addInventoryContent("ember");
            newp.showSkillItemList();

            InventoryGUI gui = new InventoryGUI(newp);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
