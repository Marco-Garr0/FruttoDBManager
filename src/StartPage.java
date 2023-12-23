import DipendenteSwing.DipendenteList;
import FruttoSwing.FruttoList;
import NegozioSwing.NegozioList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartPage extends JFrame implements ActionListener {

    JButton btnFrutto = null;
    JButton btnDipendente = null;
    JButton btnNegozio = null;

    public  StartPage(){
        setSize(800,600);
        setTitle("DB Manager");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnDipendente){

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            new DipendenteList();
        }
        if(e.getSource() == btnFrutto){

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            new FruttoList();
        }
        if(e.getSource() == btnNegozio){

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            new NegozioList();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel pnlNorth = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Frutti DB Manager", SwingConstants.CENTER);
        lblTitle.setFont(new Font("arial",Font.BOLD,30));
        pnlNorth.add(lblTitle, BorderLayout.SOUTH);

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnDipendente = new JButton("Dipendenti");
        btnFrutto = new JButton("Frutto");
        btnNegozio = new JButton("Negozio");
        btnNegozio.addActionListener(this);
        btnFrutto.addActionListener(this);
        btnDipendente.addActionListener(this);
        pnlSouth.add(btnDipendente);
        pnlSouth.add(btnFrutto);
        pnlSouth.add(btnNegozio);

        panel.add(pnlNorth);
        panel.add(pnlSouth);

        add(panel);
    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {

                new StartPage();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
