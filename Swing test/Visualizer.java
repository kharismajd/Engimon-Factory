import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Visualizer {
    public JFrame frame;
    private JPanel panel;
    private JLabel label1, label2,  label3, label4;
//    Visualizer(){
//        f=new JFrame();//creating instance of JFrame
//
//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(400,500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible
//    }

    public Visualizer()
    {
        this.frame = new JFrame("Test");
        this.panel = new JPanel(new GridLayout(2, 2));
        this.frame.setContentPane(panel);

        this.frame.setVisible(true);
        this.label1 = new JLabel();
        panel.add(label1);
        this.label2 = new JLabel();
        panel.add(label2);
        this.label3 = new JLabel();
        panel.add(label3);
        this.label4 = new JLabel();
        panel.add(label4);

        try {
            BufferedImage myPicture = ImageIO.read(new File("medievalTile_01.png"));

            label1.setIcon(new ImageIcon(myPicture));
            label2.setIcon(new ImageIcon(myPicture));
            label3.setIcon(new ImageIcon(myPicture));
            label4.setIcon(new ImageIcon(myPicture));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.pack();
        this.frame.setMinimumSize(this.frame.getPreferredSize());
        this.frame.setVisible(true);
    }

    public void update1()
    {
        try {
            BufferedImage myPicture = ImageIO.read(new File("medievalTile_27.png"));

            label1.setIcon(new ImageIcon(myPicture));
            label2.setIcon(new ImageIcon(myPicture));
            label3.setIcon(new ImageIcon(myPicture));
            label4.setIcon(new ImageIcon(myPicture));
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
    }

    public void update2()
    {
        try {
            BufferedImage myPicture = ImageIO.read(new File("medievalTile_01.png"));

            label1.setIcon(new ImageIcon(myPicture));
            label2.setIcon(new ImageIcon(myPicture));
            label3.setIcon(new ImageIcon(myPicture));
            label4.setIcon(new ImageIcon(myPicture));
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
    }


    public static void main(String[] args) {
        Visualizer v = new Visualizer();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (userInput != "exit")
        {
            if ("1".equals(userInput))
            {
                if (!v.frame.isVisible())
                {
                    v.frame.setVisible(true);
                }
//                v.frame.setVisible(true);
//                v.frame.setEnabled(true);
                v.update1();
            }
            else if ("2".equals(userInput))
            {
                if (!v.frame.isVisible())
                {
                    v.frame.setVisible(true);
                }
//                v.frame.setVisible(true);
//                v.frame.setEnabled(true);
                v.update2();
            }
            else
            {
                v.frame.setVisible(false);
//                v.frame.setEnabled(false);
            }
            userInput = sc.nextLine();
        }
    }
}  