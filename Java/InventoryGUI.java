import javax.swing.*;
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
    private player play;
    private String engimonOption[] = {"Breeding", "Ganti nama", "Buang", "Set active"};
    private String skillOption[] = {"Pakai", "Buang"};
    private int i = 0;

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
                            int n = JOptionPane.showOptionDialog(null,
                                    "Pilih aksi",
                                    "Engimon",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    engimonOption,
                                    engimonOption[0]);

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
                    b.setHorizontalAlignment(SwingConstants.LEFT);
                    b.setPreferredSize(new Dimension(100, 55));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int n = JOptionPane.showOptionDialog(null,
                                    "Pilih aksi",
                                    "Skill item",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    skillOption,
                                    skillOption[0]);

                            if (n == 1)
                            {
                                int amount = Integer.parseInt( JOptionPane.showInputDialog(null,
                                        "Berapa banyak yang ingin kamu buang?",
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
