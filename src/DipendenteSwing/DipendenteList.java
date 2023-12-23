package DipendenteSwing;

import DipendenteFile.*;
import FruttoFIle.*;
import Negozietti.DAO.DipendenteDAO;
import Negozietti.DAO.GenericDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Dipendente;
import Negozietti.Frutto;
import Negozietti.Negozio;
import NegozioSwing.NegozioDetail;
import NegozioSwing.NegozioNew;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DipendenteList extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnNew = null;
    private JButton btnDelete = null;
    private JTable tblDipendenti = null;
    private ArrayList<Object> dipendenti = new ArrayList<>();
    private JMenuItem mniNew = null;
    private JMenuItem mniOpen = null;
    private JMenuItem mniSave = null;
    private JMenuItem mniExit = null;
    private JMenuItem mniAbout = null;
    public DipendenteList(){

        setSize(800,600);
        setTitle("lista dipendenti");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            dipendenti = DipendenteDAO.readAll();
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


        dipendenti.clear();
        String fileName = flc.getSelectedFile().getAbsolutePath();
        GenericDAO.setDbName(fileName);
        dipendenti = DipendenteDAO.readAll();
        populate();
    }

    private void save() throws SQLException{    //only for file

        try {
            JFileChooser flc = new JFileChooser("~");

            int result = flc.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION)
                return;

            String filePath = flc.getSelectedFile().getAbsolutePath();
            String fileExtension = null;
            String[] splitted = filePath.split("[.]");
            fileExtension = splitted[splitted.length - 1];

            ArrayList<Dipendente> dipendenteToFile = new ArrayList<>();
            for (Object obj : dipendenti)
                dipendenteToFile.add((Dipendente) obj);

            if (fileExtension.equals("csv")) {

                DipendenteCsv fruttoCsv = new DipendenteCsv();
                fruttoCsv.write(dipendenteToFile, filePath);
            }
            else if (fileExtension.equals("json")) {

                DipendenteJson fruttoJson = new DipendenteJson();
                fruttoJson.write(dipendenteToFile, filePath);
            }
            else if (fileExtension.equals("xml")) {

                DipendenteXml fruttoXml = new DipendenteXml();
                fruttoXml.write(dipendenteToFile, filePath);
            }
            else if (fileExtension.equals("xls")) {

                DipendenteXls fruttoXls = new DipendenteXls();
                fruttoXls.write(dipendenteToFile, filePath);
            }
            else if (fileExtension.equals("ods")) {

                DipendenteOds fruttoOds = new DipendenteOds();
                fruttoOds.write(dipendenteToFile, filePath);
            }
            else if (fileExtension.equals("pdf")) {

                DipendentePdf fruttoPdf = new DipendentePdf();
                fruttoPdf.write(dipendenteToFile, filePath);
            }
        }catch (JAXBException | IOException | DocumentException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (e.getSource() == btnDelete) {
                if (tblDipendenti.getSelectedRow() == -1)
                    return;

                Dipendente d = (Dipendente) dipendenti.get(tblDipendenti.getSelectedRow());
                dipendenti.remove(tblDipendenti.getSelectedRow());
                populate();
                try {
                    DipendenteDAO.delete(d.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == btnDetail) {

                if (tblDipendenti.getSelectedRow() == -1)
                    return ;
                Dipendente d = (Dipendente) dipendenti.get(tblDipendenti.getSelectedRow());
                new DipendenteDetail(d,this);
                dipendenti = DipendenteDAO.readAll();
                populate();
            }
            if (e.getSource() == btnNew) {

                new DipendenteNew(this);
                dipendenti = DipendenteDAO.readAll();
                populate();
            }
            if(e.getSource() == mniNew){

                dipendenti.clear();
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

        String[] cols = {"id" , "nome", "cognome", "cellulare", "idNegozio"};
        DefaultTableModel model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {        //non rende editabili le celle
                return false;
            }
        };
        for(String col: cols)
            model.addColumn(col);

        for(Object obj: dipendenti){
            Dipendente d = (Dipendente) obj;
            model.addRow(d.toRow());
        }

        tblDipendenti.setModel(model);
    }
    private void initUI(){

        JMenuBar mnbNorth = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
        mniNew = new JMenuItem("new");
        mniOpen = new JMenuItem("Open...");
        mniSave = new JMenuItem("Save in file...");
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

        tblDipendenti = new JTable();

        populate();

        JScrollPane pnlDipendenti = new JScrollPane(tblDipendenti);
        add(pnlDipendenti, BorderLayout.CENTER);

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
                new DipendenteList();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
