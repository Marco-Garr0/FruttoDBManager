package DipendenteSwing;

import Negozietti.DAO.DipendenteDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Dipendente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrmDipendente extends JFrame implements ActionListener {

    JTextField txtId = null;
    JTextField txtNome = null;
    JTextField txtCognome = null;
    JTextField txtCellulare = null;
    JComboBox cmbIdNegozio = null;
    JButton btnAdd = null;
    JButton btnDelete = null;
    JButton btnUpdate = null;
    private FrmDipendente(Dipendente d){
        setSize(300,400);
        setTitle("dipendenti");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initUI();
        populate(d);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnDelete) {
                if(txtId.getText().isEmpty()){
                    txtId.setBackground(Color.RED);
                    txtId.setForeground(Color.WHITE);
                    return ;

                }

                txtId.setBackground(Color.WHITE);
                txtId.setForeground(Color.BLACK);

                int id = Integer.parseInt(txtId.getText());
                System.out.println(id);
                DipendenteDAO.delete(id);
                txtId.setText("");
                txtNome.setText("");
                txtCognome.setText("");
                txtCellulare.setText("");
            }
            if (e.getSource() == btnUpdate) {

                if (checkField() == true) {

                    int id = Integer.parseInt(txtId.getText());
                    String nome = txtNome.getText();
                    String cognome = txtCognome.getText();
                    String cell = txtCellulare.getText();
                    int idNegozio = cmbIdNegozio.getSelectedIndex() + 1;
                    DipendenteDAO.update(new Dipendente(id, nome, cognome, cell, idNegozio));
                }
            }
            if (e.getSource() == btnAdd) {

                if (checkField() == true){

                    String nome = txtNome.getText();
                    String cognome = txtCognome.getText();
                    String cell = txtCellulare.getText();
                    int idNegozio = cmbIdNegozio.getSelectedIndex() + 1;
                    DipendenteDAO.create(new Dipendente(nome, cognome, cell, idNegozio));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    private void populate(Dipendente d){
        txtId.setText("" + d.getId());
        txtNome.setText(d.getNome());
        txtCognome.setText(d.getCognome());
        txtCellulare.setText(d.getCell());
    }

    public boolean checkField(){

        if(txtNome.getText().isEmpty()){
            txtNome.setBackground(Color.RED);
            txtNome.setForeground(Color.WHITE);
            return false;
        }else{

            txtNome.setForeground(Color.WHITE);
            txtNome.setForeground(Color.BLACK);
        }

        if(txtCognome.getText().isEmpty()){
            txtCognome.setBackground(Color.RED);
            txtCognome.setForeground(Color.WHITE);
            return false;
        }else{

            txtCognome.setForeground(Color.WHITE);
            txtCognome.setForeground(Color.BLACK);
        }

        if(cmbIdNegozio.getSelectedIndex() < 0){
            cmbIdNegozio.setBackground(Color.RED);
            cmbIdNegozio.setForeground(Color.WHITE);
            return false;
        }else{

            cmbIdNegozio.setForeground(Color.WHITE);
            cmbIdNegozio.setForeground(Color.BLACK);
        }

        if(txtCellulare.getText().isEmpty()){
            txtCognome.setBackground(Color.RED);
            txtCognome.setForeground(Color.WHITE);
            return false;
        }else{

            txtCognome.setForeground(Color.WHITE);
            txtCognome.setForeground(Color.BLACK);
        }
        return true;
    }
    private void initUI(){

        JPanel pnlCentral = new JPanel(new GridLayout(5,1));

        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));     //LEFT: direzione da cui parte a inserire i component
        JLabel lblId = new JLabel("id: ");
        txtId = new JTextField(10);
        //txtId.setEnabled(false);
        //aggiungo label e textFIeld e il panel id
        pnlId.add(lblId);
        pnlId.add(txtId);
        //aggiungo il panel id al pannello centrale che contiene tutti gli attributi
        pnlCentral.add(pnlId);

        JPanel pnlNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNome = new JLabel("nome :");
        txtNome = new JTextField(30);

        pnlNome.add(lblNome);
        pnlNome.add(txtNome);

        pnlCentral.add(pnlNome);

        JPanel pnlCognome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCognome = new JLabel("cognome :");
        txtCognome = new JTextField(30);

        pnlCognome.add(lblCognome);
        pnlCognome.add(txtCognome);

        pnlCentral.add(pnlCognome);

        JPanel pnlCellulare = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCellulare = new JLabel("cellulare :");
        txtCellulare = new JTextField(15);

        pnlCellulare.add(lblCellulare);
        pnlCellulare.add(txtCellulare);

        pnlCentral.add(pnlCellulare);

        JPanel pnlIdNegozio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblIdNegozio = new JLabel("idNegozio :");
        ArrayList<String> ids = new ArrayList<>();
        try {
            ids = NegozioDAO.readAllIdNegozio();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        cmbIdNegozio = new JComboBox<>(ids.toArray(new String[ids.size()]));

        pnlIdNegozio.add(lblIdNegozio);
        pnlIdNegozio.add(cmbIdNegozio);
        pnlCentral.add(pnlIdNegozio);


        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAdd = new JButton("add");
        btnAdd.addActionListener(this);

        btnDelete = new JButton("delete");
        btnDelete.addActionListener(this);

        btnUpdate = new JButton("update");
        btnUpdate.addActionListener(this);

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnDelete);
        pnlSouth.add(btnUpdate);

        add(pnlCentral,BorderLayout.CENTER);
        add(pnlSouth,BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        try{
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Runnable r = new Runnable() {       //classe anonima
            @Override
            public void run() {

                new FrmDipendente(new Dipendente(3,"cebby", "cebbaldo","3917674832", 2));
            }
       };
       SwingUtilities.invokeLater(r);

    }
}

