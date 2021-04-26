import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainVisualizer implements KeyListener {

    map_visualizer mapGUI;
    InventoryGUI inventoryGUI;
    JFrame mainFrame;
    Integer state = 0;

    Main caller;

    public static final Integer VISUALIZER_MAP = 0;
    public static final Integer VISUALIZER_INVENTORY = 1;

    public Integer getState() {
        return state;
    }

    public MainVisualizer(Main mainObject)
    {
        caller = mainObject;
        gameMap g = mainObject.g;
        player p = mainObject.P;
        this.mainFrame = new JFrame("Engimon");
        mainFrame.addKeyListener(this);
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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            if (this.getState() == VISUALIZER_MAP)
            {
                caller.moveLeft();
            }
        }

        if (key == KeyEvent.VK_D) {
            if (this.getState() == VISUALIZER_MAP)
            {
                caller.moveRight();
            }
        }

        if (key == KeyEvent.VK_W) {
            if (this.getState() == VISUALIZER_MAP)
            {
                caller.moveUp();
            }
        }

        if (key == KeyEvent.VK_S) {
            if (this.getState() == VISUALIZER_MAP)
            {
                caller.moveDown();
            }
        }

        if (key == KeyEvent.VK_X) {
            caller.openMenu();
        }

//        try
//        {
//            Thread.sleep(500);;
//        }
//        catch (Exception exception)
//        {
//            exception.printStackTrace();
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
