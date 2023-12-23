package NegozioSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import Negozietti.*;
import Negozietti.DAO.*;

public class NegozioList extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnNew = null;
    private JButton btnDelete = null;
    private JTable tblNegozio = null;
    private ArrayList<Object> negozi = new ArrayList<>();
    private JMenuItem mniNew = null;
    private JMenuItem mniOpen = null;
    private JMenuItem mniSave = null;
    private JMenuItem mniExit = null;
    private JMenuItem mniAbout;

    public NegozioList(){

        setSize(800,600);
        setTitle("lista negozi");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            negozi = NegozioDAO.readAll();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        initUI();
        setVisible(true);
    }

    private void open() throws SQLException{

        JFileChooser flc = new JFileChooser("./");

        int result = flc.showOpenDialog(this);
        if(result != JFileChooser.APPROVE_OPTION)      //utente non ha premuto OK
            return;

        negozi.clear();
        String fileName = flc.getSelectedFile().getAbsolutePath();
        GenericDAO.setDbName(fileName);
        negozi = NegozioDAO.readAll();
        populate();
    }

    private void save() throws SQLException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnDelete) {

                if (tblNegozio.getSelectedRow() == -1)
                    return;

                Negozio n = (Negozio) negozi.get(tblNegozio.getSelectedRow());
                negozi.remove(tblNegozio.getSelectedRow());
                populate();
                NegozioDAO.delete(n.getId());
            }

            if (e.getSource() == btnDetail) {

                if (tblNegozio.getSelectedRow() == -1)
                    return ;
                Negozio n = (Negozio) negozi.get(tblNegozio.getSelectedRow());
                new NegozioDetail(n, this);
                negozi = NegozioDAO.readAll();
                populate();
            }

            if (e.getSource() == btnNew) {

                new NegozioNew(this);
                negozi = NegozioDAO.readAll();
                populate();
            }

            if(e.getSource() == mniNew){

                negozi.clear();
                populate();
            }

            if(e.getSource() == mniOpen)
                open();

            if(e.getSource() == mniSave)
                save();

            if(e.getSource() == mniExit)
                System.exit(0);

            if(e.getSource() == mniAbout)
                JOptionPane.showMessageDialog(this, "Marco Garro, applicazione senza asciugamani","about", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void populate(){
        String[] cols = {"id" , "nome", "sede"};
        DefaultTableModel model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {        //non rende editabili le celle
                return false;
            }
        };
        for(String col: cols)
            model.addColumn(col);

        for(Object obj: negozi){
            Negozio n = (Negozio) obj;
            model.addRow(n.toRow());
        }

        tblNegozio.setModel(model);
    }
    private void initUI(){

        JMenuBar mnbNorth = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
        mniNew = new JMenuItem("new");
        mniOpen = new JMenuItem("Open...");
        mniSave = new JMenuItem("Save...");
        mniExit = new JMenuItem("Exit");
        mnuFile.add(mniNew);
        mnuFile.add(mniOpen);
        mnuFile.add(mniSave);
        mnuFile.addSeparator();
        mnuFile.add(mniExit);

        JMenu mnuHelp = new JMenu("Help");
        mniAbout = new JMenuItem("about...");
        mnuHelp.add(mniAbout);

        mniNew.addActionListener(this);
        mniOpen.addActionListener(this);
        mniSave.addActionListener(this);
        mniExit.addActionListener(this);
        mniAbout.addActionListener(this);

        mnbNorth.add(mnuFile);
        mnbNorth.add(mnuHelp);

        add(mnbNorth, BorderLayout.NORTH);


        tblNegozio = new JTable();

        populate();

        JScrollPane pnlNegozio = new JScrollPane(tblNegozio);
        add(pnlNegozio, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNew = new JButton("new...");
        btnDelete = new JButton("delete");
        btnDetail = new JButton("detail...");

        btnNew.addActionListener(this);
        btnDelete.addActionListener(this);
        btnDetail.addActionListener(this);

        pnlSouth.add(btnDelete);
        pnlSouth.add(btnNew);
        pnlSouth.add(btnDetail);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new NegozioList();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
