import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public class map_visualizer {
    public JFrame mainFrame;
    private JPanel mapPanel;
    private JLabel label1, label2,  label3, label4;

    private Vector<Vector<customLabel>> customLabelMap;

    public JPanel getMapPanel() {
        return mapPanel;
    }

    public map_visualizer(gameMap g)
    {
        this.mainFrame = new JFrame("Test");
        this.mapPanel = new JPanel(new GridLayout(g.getMapWidth(), g.getMapLength()));
        this.mainFrame.setContentPane(mapPanel);

        try
        {
            customLabel.mountainTile = ImageIO.read(new File("img/mountain.png"));
            customLabel.seaTile = ImageIO.read(new File("img/sea.png"));
            customLabel.grasslandTile = ImageIO.read(new File("img/grassland.png"));
            customLabel.tundraTile = ImageIO.read(new File("img/tundra.png"));
            customLabel.playerTile = ImageIO.read(new File("img/player.png"));
            customLabel.activeEngimonTile = ImageIO.read(new File("img/activeEngimon.png"));

            customLabel.charmander_fire_player_1 = ImageIO.read(new File("img/charmander_fire_player_1.png"));
            customLabel.geodude_ground_player_1 = ImageIO.read(new File("img/geodude_ground_player_1.png"));
            customLabel.glastrier_ice_player_1 = ImageIO.read(new File("img/glastrier_ice_player_1.png"));
            customLabel.lapras_water_player_1 = ImageIO.read(new File("img/lapras_water_player_1.png"));
            customLabel.magikarp_water_player_1 = ImageIO.read(new File("img/magikarp_water_player_1.png"));
            customLabel.pikachu_electric_player_1 = ImageIO.read(new File("img/pikachu_electric_player_1.png"));
            customLabel.vulpichu_fire_player_1 = ImageIO.read(new File("img/vulpichu_fire_player_1.png"));
            customLabel.wooper_water_player_1 = ImageIO.read(new File("img/wooper_water_player_1.png"));

            customLabel.charmander_fire_wild_1 = ImageIO.read(new File("img/charmander_fire_wild_1.png"));
            customLabel.geodude_ground_wild_1 = ImageIO.read(new File("img/geodude_ground_wild_1.png"));
            customLabel.glastrier_ice_wild_1 = ImageIO.read(new File("img/glastrier_ice_wild_1.png"));
            customLabel.lapras_water_wild_1 = ImageIO.read(new File("img/lapras_water_wild_1.png"));
            customLabel.magikarp_water_wild_1 = ImageIO.read(new File("img/magikarp_water_wild_1.png"));
            customLabel.pikachu_electric_wild_1 = ImageIO.read(new File("img/pikachu_electric_wild_1.png"));
            customLabel.vulpichu_fire_wild_1 = ImageIO.read(new File("img/vulpichu_fire_wild_1.png"));
            customLabel.wooper_water_wild_1 = ImageIO.read(new File("img/wooper_water_wild_1.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        this.customLabelMap = new Vector<Vector<customLabel>>();
        for (int i = 0; i < g.getMapWidth(); i++) {
            Vector<customLabel> r = new Vector<customLabel>();
            for (int j = 0; j < g.getMapLength(); j++) {
                customLabel l = new customLabel(g.tile_map.elementAt(i).elementAt(j));
                this.mapPanel.add(l);
                g.tile_map.elementAt(i).elementAt(j).setListener(l);
                r.add(l);
            }

            this.customLabelMap.add(r);
        }

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.pack();
        this.mainFrame.setMinimumSize(this.mainFrame.getPreferredSize());
        this.mainFrame.setResizable(false);
//        this.mainFrame.setVisible(true);
    }

    public map_visualizer(gameMap g, JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        this.mapPanel = new JPanel(new GridLayout(g.getMapWidth(), g.getMapLength()));
        this.mainFrame.setContentPane(mapPanel);

        try
        {
            customLabel.mountainTile = ImageIO.read(new File("img/mountain.png"));
            customLabel.seaTile = ImageIO.read(new File("img/sea.png"));
            customLabel.grasslandTile = ImageIO.read(new File("img/grassland.png"));
            customLabel.tundraTile = ImageIO.read(new File("img/tundra.png"));
            customLabel.playerTile = ImageIO.read(new File("img/player.png"));
            customLabel.activeEngimonTile = ImageIO.read(new File("img/activeEngimon.png"));

            customLabel.charmander_fire_player_1 = ImageIO.read(new File("img/charmander_fire_player_1.png"));
            customLabel.geodude_ground_player_1 = ImageIO.read(new File("img/geodude_ground_player_1.png"));
            customLabel.glastrier_ice_player_1 = ImageIO.read(new File("img/glastrier_ice_player_1.png"));
            customLabel.lapras_water_player_1 = ImageIO.read(new File("img/lapras_water_player_1.png"));
            customLabel.magikarp_water_player_1 = ImageIO.read(new File("img/magikarp_water_player_1.png"));
            customLabel.pikachu_electric_player_1 = ImageIO.read(new File("img/pikachu_electric_player_1.png"));
            customLabel.vulpichu_fire_player_1 = ImageIO.read(new File("img/vulpichu_fire_player_1.png"));
            customLabel.wooper_water_player_1 = ImageIO.read(new File("img/wooper_water_player_1.png"));

            customLabel.charmander_fire_wild_1 = ImageIO.read(new File("img/charmander_fire_wild_1.png"));
            customLabel.geodude_ground_wild_1 = ImageIO.read(new File("img/geodude_ground_wild_1.png"));
            customLabel.glastrier_ice_wild_1 = ImageIO.read(new File("img/glastrier_ice_wild_1.png"));
            customLabel.lapras_water_wild_1 = ImageIO.read(new File("img/lapras_water_wild_1.png"));
            customLabel.magikarp_water_wild_1 = ImageIO.read(new File("img/magikarp_water_wild_1.png"));
            customLabel.pikachu_electric_wild_1 = ImageIO.read(new File("img/pikachu_electric_wild_1.png"));
            customLabel.vulpichu_fire_wild_1 = ImageIO.read(new File("img/vulpichu_fire_wild_1.png"));
            customLabel.wooper_water_wild_1 = ImageIO.read(new File("img/wooper_water_wild_1.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        this.customLabelMap = new Vector<Vector<customLabel>>();
        for (int i = 0; i < g.getMapWidth(); i++) {
            Vector<customLabel> r = new Vector<customLabel>();
            for (int j = 0; j < g.getMapLength(); j++) {
                customLabel l = new customLabel(g.tile_map.elementAt(i).elementAt(j));
                this.mapPanel.add(l);
                g.tile_map.elementAt(i).elementAt(j).setListener(l);
                r.add(l);
            }

            this.customLabelMap.add(r);
        }

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.pack();
        this.mainFrame.setMinimumSize(this.mainFrame.getPreferredSize());
        this.mainFrame.setResizable(false);
        this.mainFrame.setVisible(true);
    }
}


class customLabel extends JPanel implements tileListener{
    tile t;
    player p;
    engimon e;
    JLabel baseLayer;

    private BufferedImage background;

    protected static BufferedImage mountainTile;
    protected static BufferedImage seaTile;
    protected static BufferedImage grasslandTile;
    protected static BufferedImage tundraTile;

    protected static BufferedImage playerTile;
    protected static BufferedImage activeEngimonTile;

    protected static BufferedImage charmander_fire_player_1;
    protected static BufferedImage geodude_ground_player_1;
    protected static BufferedImage glastrier_ice_player_1;
    protected static BufferedImage lapras_water_player_1;
    protected static BufferedImage magikarp_water_player_1;
    protected static BufferedImage pikachu_electric_player_1;
    protected static BufferedImage vulpichu_fire_player_1;
    protected static BufferedImage wooper_water_player_1;

    protected static BufferedImage charmander_fire_wild_1;
    protected static BufferedImage geodude_ground_wild_1;
    protected static BufferedImage glastrier_ice_wild_1;
    protected static BufferedImage lapras_water_wild_1;
    protected static BufferedImage magikarp_water_wild_1;
    protected static BufferedImage pikachu_electric_wild_1;
    protected static BufferedImage vulpichu_fire_wild_1;
    protected static BufferedImage wooper_water_wild_1;

    public customLabel(tile t)
    {
        super();
        baseLayer = new JLabel();
        this.setBaseIcon(t.getTile_type());
        this.add(baseLayer);

//        this.setBaseColor(t.getTile_type());
//        this.setOpaque(true);
        this.tileChangedCharacter(t.getSymbol(), e);

    }

    @Override
    public Dimension getPreferredSize() {
        return background == null ? new Dimension(0, 0) : new Dimension(background.getWidth(this), background.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (background != null) {
            Insets insets = getInsets();

            int width = getWidth() - 1 - (insets.left + insets.right);
            int height = getHeight() - 1 - (insets.top + insets.bottom);

            int x = (width - background.getWidth(this)) / 2;
            int y = (height - background.getHeight(this)) / 2;

            g.drawImage(background, x, y, this);
        }

    }

    private void setBaseIcon(String tile_type)
    {
        if (tile_type.equals("mountains"))
        {
            this.background = mountainTile;
        }
        else if (tile_type.equals("sea"))
        {
            this.background = seaTile;
        }
        else if (tile_type.equals("grassland"))
        {
            this.background = grasslandTile;
        }
        else if (tile_type.equals("tundra"))
        {
            this.background = tundraTile;
        }
    }

    @Override
    public void tileChangedCharacter(char symbol, engimon e) {
        if (symbol == 'P')
        {
            this.baseLayer.setIcon(new ImageIcon(playerTile));
//            if (!this.isOpaque())
//            {
//                this.setOpaque(true);
//                this.repaint();
//            }

        }
        else if (symbol == 'X')
        {
            if(e != null) {
                if (e.species == "Charmander") {
                    this.baseLayer.setIcon(new ImageIcon(charmander_fire_player_1));
                } else if (e.species == "Magikarp") {
                    this.baseLayer.setIcon(new ImageIcon(magikarp_water_wild_1));
                } else if (e.species == "Pikachu") {
                    this.baseLayer.setIcon(new ImageIcon(pikachu_electric_wild_1));
                } else if (e.species == "Geodude") {
                    this.baseLayer.setIcon(new ImageIcon(geodude_ground_wild_1));
                } else if (e.species == "Glastrier") {
                    this.baseLayer.setIcon(new ImageIcon(glastrier_ice_wild_1));
                } else if (e.species == "Vulpichu") {
                    this.baseLayer.setIcon(new ImageIcon(vulpichu_fire_wild_1));
                } else if (e.species == "Lapras") {
                    this.baseLayer.setIcon(new ImageIcon(lapras_water_wild_1));
                } else if (e.species == "Wooper") {
                    this.baseLayer.setIcon(new ImageIcon(wooper_water_wild_1));
                }
            }
            else{
                this.baseLayer.setIcon(new ImageIcon(activeEngimonTile));
            }

        }
        else
        {
            if(e != null) {
                if (e.getSpecies() == "Charmander") {
                    this.baseLayer.setIcon(new ImageIcon(charmander_fire_wild_1));
                } else if (e.getSpecies() == "Magikarp") {
                    this.baseLayer.setIcon(new ImageIcon(magikarp_water_wild_1));
                } else if (e.getSpecies() == "Pikachu") {
                    this.baseLayer.setIcon(new ImageIcon(pikachu_electric_wild_1));
                } else if (e.getSpecies() == "Geodude") {
                    this.baseLayer.setIcon(new ImageIcon(geodude_ground_wild_1));
                } else if (e.getSpecies() == "Glastrier") {
                    this.baseLayer.setIcon(new ImageIcon(glastrier_ice_wild_1));
                } else if (e.getSpecies() == "Vulpichu") {
                    this.baseLayer.setIcon(new ImageIcon(vulpichu_fire_wild_1));
                } else if (e.getSpecies() == "Lapras") {
                    this.baseLayer.setIcon(new ImageIcon(lapras_water_wild_1));
                } else if (e.getSpecies() == "Wooper") {
                    this.baseLayer.setIcon(new ImageIcon(wooper_water_wild_1));
                }
            }
            else {
                this.baseLayer.setIcon(null);
            }
        }
    }
}
