package DipendenteSwing;

import Negozietti.DAO.DipendenteDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Dipendente;
import Negozietti.Negozio;
import NegozioSwing.NegozioDetail;
import NegozioSwing.NegozioNew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class DipendenteList extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnNew = null;
    private JButton btnDelete = null;
    private JTable tblDipendenti = null;
    private ArrayList<Object> dipendenti = new ArrayList<>();

    private DipendenteList(){

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

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void populate(){

        String[] cols = {"id" , "nome", "cognome", "cellulare", "idNegozio"};
        DefaultTableModel model = new DefaultTableModel();
        for(String col: cols)
            model.addColumn(col);

        for(Object obj: dipendenti){
            Dipendente d = (Dipendente) obj;
            model.addRow(d.toRow());
        }

        tblDipendenti.setModel(model);
    }
    private void initUI(){

        tblDipendenti = new JTable();

        populate();

        JScrollPane pnlNegozio = new JScrollPane(tblDipendenti);
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
                new DipendenteList();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
