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


    public map_visualizer(gameMap g)
    {
        this.mainFrame = new JFrame("Test");
        this.mapPanel = new JPanel(new GridLayout(g.getMapWidth(), g.getMapLength()));
        this.mainFrame.setContentPane(mapPanel);

        try
        {
            customLabel.mountainTile = ImageIO.read(new File("img/medievalTile_15.png"));
            customLabel.seaTile = ImageIO.read(new File("img/medievalTile_27.png"));
            customLabel.grasslandTile = ImageIO.read(new File("img/mapTile_022.png"));
            customLabel.tundraTile = ImageIO.read(new File("img/medievalTile_29.png"));
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

class customLabel extends JLabel implements tileListener{
    tile t;
    protected static BufferedImage mountainTile;
    protected static BufferedImage seaTile;
    protected static BufferedImage grasslandTile;
    protected static BufferedImage tundraTile;

    protected static BufferedImage playerTile;
    protected static BufferedImage activeEngimonTile;

    public customLabel(tile t)
    {
        super();
        this.tileChangedCharacter(t.getSymbol());
    }

    @Override
    public void tileChangedCharacter(char symbol) {
        if (symbol == '^')
        {
            this.setIcon(new ImageIcon(mountainTile));
        }
        else if (symbol == 'o')
        {
            this.setIcon(new ImageIcon(seaTile));
        }
        else if (symbol == '-')
        {
            this.setIcon(new ImageIcon(grasslandTile));
        }
        else if (symbol == '.')
        {
            this.setIcon(new ImageIcon(tundraTile));
        }

    }
}
