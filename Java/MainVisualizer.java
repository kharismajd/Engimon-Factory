import javax.swing.*;
import java.awt.*;

public class MainVisualizer {

    map_visualizer mapGUI;
    InventoryGUI inventoryGUI;
    JFrame mainFrame;
    Integer state = 0;

    public static final Integer VISUALIZER_MAP = 0;
    public static final Integer VISUALIZER_INVENTORY = 1;

    public Integer getState() {
        return state;
    }

    public MainVisualizer(gameMap g, player p)
    {
        this.mainFrame = new JFrame("Engimon");
        this.inventoryGUI = new InventoryGUI(p, mainFrame);
        this.mapGUI = new map_visualizer(g, mainFrame);
        this.mainFrame.setVisible(true);
        this.state = VISUALIZER_MAP;
    }

    public void openInventory()
    {
        this.mainFrame.setContentPane(inventoryGUI.getMain());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setResizable(false);
        this.mainFrame.setVisible(true);
        this.state = VISUALIZER_INVENTORY;
    }

    public void openMap()
    {
        this.mainFrame.setContentPane(mapGUI.getMapPanel());
        this.mainFrame.pack();
        this.mainFrame.setMinimumSize(this.mainFrame.getPreferredSize());
        this.mainFrame.setResizable(false);
        this.state = VISUALIZER_MAP;
    }


}
