package FruttoSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import Negozietti.*;
import Negozietti.DAO.*;

public class FruttoList extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnNew = null;
    private JButton btnDelete = null;
    private JTable tblFrutto = null;
    private ArrayList<Object> frutti = new ArrayList<>();
    private JMenuItem mniNew = null;
    private JMenuItem mniOpen = null;
    private JMenuItem mniSave = null;
    private JMenuItem mniExit = null;
    private JMenuItem mniAbout;

    private FruttoList(ArrayList<Object> array){

        setSize(800,600);
        setTitle("lista frutti");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            frutti = FruttoDAO.readAll();
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


        frutti.clear();
        String fileName = flc.getSelectedFile().getAbsolutePath();
        GenericDAO.setDbName(fileName);
        frutti = FruttoDAO.readAll();
        populate();
    }

    private void save() throws SQLException{

        JFileChooser fleChooser = new JFileChooser("./");
        int result = fleChooser.showSaveDialog(this);

        if(result != JFileChooser.APPROVE_OPTION)
            return ;

        FruttoDAO.deleteAll();
        for(Object obj: frutti)
            FruttoDAO.create((Frutto) obj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (e.getSource() == btnDelete) {
                if (tblFrutto.getSelectedRow() == -1)
                    return;

                Frutto frutto = (Frutto) frutti.get(tblFrutto.getSelectedRow());
                frutti.remove(tblFrutto.getSelectedRow());
                populate();
                try {
                    FruttoDAO.delete(frutto.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == btnDetail) {

                if (tblFrutto.getSelectedRow() == -1)
                    return ;
                Frutto f = (Frutto) frutti.get(tblFrutto.getSelectedRow());
                new FruttoDetail(f, this);
                populate();
            }
            if (e.getSource() == btnNew) {

                new FruttoNew(frutti, this);
                populate();
            }

            if(e.getSource() == mniNew){

                frutti.clear();
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
        String[] cols = {"id" , "nome", "stagione", "costo"};
        DefaultTableModel model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {        //non rende editabili le celle
                return false;
            }
        };
        for(String col: cols)
            model.addColumn(col);

        for(Object obj: frutti){
            Frutto f = (Frutto) obj;
            model.addRow(f.toRow());
        }

        tblFrutto.setModel(model);
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

        tblFrutto = new JTable();

        populate();

        JScrollPane pnlFrutti = new JScrollPane(tblFrutto);
        add(pnlFrutti, BorderLayout.CENTER);

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
        try{
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new FruttoList(new ArrayList<Object>());
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
