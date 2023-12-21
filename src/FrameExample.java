import javax.management.JMException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameExample extends JFrame implements ActionListener {

    private JMenuItem mniNew = null;

    public FrameExample(){

        setSize(800,600);
        setTitle("prova");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {

        JMenuBar barNorth = new JMenuBar();
        JMenu mnuFile = new JMenu("file");

        mniNew = new JMenuItem("new...");
        mnuFile.add(mniNew);
        mniNew.addActionListener(this);

        JMenuItem mniOpen = new JMenuItem("open...");
        mnuFile.add(mniOpen);

        JMenuItem mniSaveAS = new JMenuItem("save as...");
        mnuFile.add(mniSaveAS);

        mnuFile.addSeparator();

        JMenuItem mniExit = new JMenuItem("exit");
        mnuFile.add(mniExit);

        JMenu mnuEdit = new JMenu("edit");
        JMenu mnuView = new JMenu("view");
        JMenu mnuHelp = new JMenu("help");

        barNorth.add(mnuFile);
        barNorth.add(mnuEdit);
        barNorth.add(mnuView);
        barNorth.add(mnuHelp);

        add(barNorth, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == mniNew){
            System.out.println("lwieutjkqwerpoigh");
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new FrameExample();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
