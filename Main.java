import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
public class Main extends JPanel implements MouseListener, ActionListener 
{
    int x = 0, y = 0, counter = 0;
    static JFrame fr = new JFrame("Rainbow Paint");
    javax.swing.Timer timer = new javax.swing.Timer(50, this);
    ArrayList < RainbowPaint > Glitters = new ArrayList < RainbowPaint > ();
    ArrayList < Integer > xCoOrds = new ArrayList < Integer > ();
    ArrayList < Integer > yCoOrds = new ArrayList < Integer > ();
    ArrayList < Integer > ErPatterns = new ArrayList < Integer > ();
    static JMenuBar bar = new JMenuBar();
    static JMenu file = new JMenu("     File          ");
    static JMenuItem save = new JMenuItem("    Save          ");
    static JMenuItem load = new JMenuItem("    Load          ");
    static JMenuItem neu = new JMenuItem("    New           ");
    static JMenuItem del = new JMenuItem("    Delete           ");
    static JMenuItem exit = new JMenuItem("    Exit          ");
    static JMenu fhelp = new JMenu("     Help          ");
    static JMenuItem help = new JMenuItem("    Help          ");
    static JMenuItem about = new JMenuItem("    About          ");
    public Main() 
    {
        setBackground(Color.BLACK);
        addMouseListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        neu.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);
        del.addActionListener(this);
        exit.addActionListener(this);
        timer.start();
    }
    public void paintComponent(Graphics g) 
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        int ran = 0;
        for (RainbowPaint obj2: Glitters) 
        {
            obj2.paintComponent(g, xCoOrds.get(ran), yCoOrds.get(ran));
            ran++;
        }
    }
    public static void main(String args[]) 
    {
        fr.setSize(600, 600);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Main obj = new Main();
        fr.add(obj);
        fr.setJMenuBar(bar);
        bar.add(file);
        file.add(neu);
        file.add(new JSeparator());
        file.add(load);
        file.add(new JSeparator());
        file.add(save);
        file.add(new JSeparator());
        file.add(del);
        file.add(new JSeparator());
        file.add(exit);
        bar.add(fhelp);
        fhelp.add(help);
        fhelp.add(new JSeparator());
        fhelp.add(about);
        fr.setVisible(true);
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) 
    {
        if (e.isMetaDown()) 
        {
            Eraser obj = new Eraser();
            Glitters.add(obj);
            x = e.getX();
            y = e.getY();
            xCoOrds.add(x);
            yCoOrds.add(y);
            ErPatterns.add(counter);
            counter++;
        } 
        else if (!(e.isAltDown())) 
        {
            ColorCircle obj = new ColorCircle();
            Glitters.add(obj);
            x = e.getX();
            y = e.getY();
            xCoOrds.add(x);
            yCoOrds.add(y);
            counter++;
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == save) 
        {
            String input = JOptionPane.showInputDialog(null, "Save as: ", "Save As", JOptionPane.INFORMATION_MESSAGE);
            if (input != null) 
            {
                boolean alreadyPresent = false;
                String str2 = "", str3 = "", str4 = "";
                try 
                {
                    BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
                    String names = reader.readLine();
                    reader.close();
                    String name = "";
                    for (int a = 0; a < names.length(); a++) 
                    {
                        if ((names.charAt(a) == ' ')) 
                        {
                            if (name.equals(input)) 
                            {
                                alreadyPresent = true;
                            }
                            name = "";
                        } 
                        else 
                        {
                            name += names.charAt(a);
                        }
                    }
                    if (alreadyPresent) 
                    {
                        Object options[] = {"Yes", "No"};
                        int choice = JOptionPane.showOptionDialog(null, "File already exists. Do you want to overwrite?", "Confirm", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (choice == 0) 
                        {
                            return;
                        }
                    } 
                    else 
                    {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt"));
                        writer.write(names + input + " ");
                        writer.close();
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(input + "x.txt"));
                    for (Integer obj: xCoOrds) 
                    {
                        str2 += obj + " ";
                    }
                    writer.write(str2);
                    writer.close();
                    writer = new BufferedWriter(new FileWriter(input + "y.txt"));
                    for (Integer obj: yCoOrds) 
                    {
                        str3 += obj + " ";
                    }
                    writer.write(str3);
                    writer.close();
                    writer = new BufferedWriter(new FileWriter(input + "ar.txt"));
                    for (Integer obj: ErPatterns) 
                    {
                        str4 += obj + " ";
                    }
                    writer.write(str4);
                    writer.close();
                    JOptionPane.showMessageDialog(null, "The file has been successfully saved");
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "The file could not be successfully saved", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        } 
        else if (e.getSource() == load) 
        {
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
                String options = reader.readLine();
                reader.close();
                int count = 0;
                for (int a = 0; a < options.length(); a++) 
                {
                    if (options.charAt(a) == ' ') 
                    {
                        count++;
                    }
                }
                Object opList[] = new Object[count];
                String option = "";
                int count2 = 0;
                for (int a = 0; a < options.length(); a++) 
                {
                    if (options.charAt(a) == ' ') 
                    {
                        opList[count2] = option;
                        option = "";
                        count2++;
                    } 
                    else 
                    {
                        option += options.charAt(a);
                    }
                }
                String input = (String)(JOptionPane.showInputDialog(null, "Choose", "Load", JOptionPane.INFORMATION_MESSAGE, null, opList, opList[0]));
                if (input != null) 
                {
                    reader = new BufferedReader(new FileReader(input + "x.txt"));
                    String savedX = reader.readLine();
                    reader.close();
                    reader = new BufferedReader(new FileReader(input + "y.txt"));
                    String savedY = reader.readLine();
                    reader.close();
                    xCoOrds.clear();
                    yCoOrds.clear();
                    String coOrd = "";
                    for (int a = 0; a < savedX.length(); a++) 
                    {
                        if (savedX.charAt(a) == ' ') 
                        {
                            xCoOrds.add(Integer.parseInt(coOrd));
                            coOrd = "";
                        } 
                        else 
                        {
                            coOrd += savedX.charAt(a);
                        }
                    }
                    for (int a = 0; a < savedY.length(); a++) 
                    {
                        if (savedY.charAt(a) == ' ') 
                        {
                            yCoOrds.add(Integer.parseInt(coOrd));
                            coOrd = "";
                        } 
                        else 
                        {
                            coOrd += savedY.charAt(a);
                        }
                    }
                    Glitters = new ArrayList < RainbowPaint > ();
                    ErPatterns.clear();
                    reader = new BufferedReader(new FileReader(input + "ar.txt"));
                    String ErNos = reader.readLine();
                    reader.close();
                    String ErNo = "";
                    if (ErNos != null) 
                    {
                        for (int a = 0; a < ErNos.length(); a++) 
                        {
                            if (ErNos.charAt(a) == ' ') 
                            {
                                ErPatterns.add(Integer.parseInt(ErNo));
                                ErNo = "";
                            } 
                            else 
                            {
                                ErNo += ErNos.charAt(a);
                            }
                        }
                    }
                    outer: 
                    for (int a = 0; a < xCoOrds.size(); a++) 
                    {
                        for (Integer obj: ErPatterns) 
                        {
                            if (obj == a) 
                            {
                                Glitters.add(new Eraser());
                                continue outer;
                            }
                        }
                        Glitters.add(new ColorCircle());
                    }
                    counter = xCoOrds.size();
                }
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(null, "There is no file to be loaded !", "Load", JOptionPane.WARNING_MESSAGE);
            }
        } 
        else if (e.getSource() == neu) 
        {
            Glitters = new ArrayList < RainbowPaint > ();
            xCoOrds.clear();
            yCoOrds.clear();
            ErPatterns.clear();
            counter = 0;
        } 
        else if (e.getSource() == help) 
        {
            JOptionPane.showMessageDialog(null, "                                          Create your very own Rainbow Paintings!!" + "\n" + "Click on the left mouse button to draw a rainbow pattern and the right mouse button to erase." + "\n" + "                                                      Don't forget to save you work!" + "\n" + "                                Want inspiration? Check out the samples in the load folder.", "Help", JOptionPane.INFORMATION_MESSAGE);
        } 
        else if (e.getSource() == about) 
        {
            JOptionPane.showMessageDialog(null, "Created by Sourish Banerjee. " + "\n" + "        Achieved with java. ", "About", JOptionPane.INFORMATION_MESSAGE);
        } 
        else if (e.getSource() == del) 
        {
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
                String names = reader.readLine();
                reader.close();
                int count = 0;
                for (int a = 0; a < names.length(); a++) 
                {
                    if (names.charAt(a) == ' ') 
                    {
                        count++;
                    }
                }
                String name = "";
                Object ant[] = new Object[count];
                int count2 = 0;
                for (int a = 0; a < names.length(); a++) 
                {
                    if (names.charAt(a) == ' ') 
                    {
                        ant[count2] = name;
                        name = "";
                        count2++;
                    } 
                    else 
                    {
                        name += names.charAt(a);
                    }
                }
                String input = (String)(JOptionPane.showInputDialog(null, "Choose", "Delete", JOptionPane.INFORMATION_MESSAGE, null, ant, ant[0]));
                if (input != null) 
                {
                    String modNames = names.replace((input + " "), "");
                    BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt"));
                    writer.write(modNames);
                    writer.close();
                    new File(input + "x.txt").delete();
                    new File(input + "y.txt").delete();
                    new File(input + "ar.txt").delete();
                    JOptionPane.showMessageDialog(null, "File has been successfully deleted");
                }
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(null, "There are no files to be deleted !", "Delete", JOptionPane.WARNING_MESSAGE);
            }
        } 
        else if (e.getSource() == exit) 
        {
            System.exit(0);
        } 
        else 
        {
            repaint();
        }
    }
}