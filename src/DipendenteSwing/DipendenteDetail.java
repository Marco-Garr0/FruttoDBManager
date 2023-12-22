package DipendenteSwing;

import Negozietti.DAO.DipendenteDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Dipendente;
import Negozietti.Negozio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DipendenteDetail extends JDialog implements ActionListener {

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtCognome = null;
    private JTextField txtCell = null;
    private JTextField txtIdNegozio = null;
    private JButton btnUpdate = null;
    private Dipendente dipendenteDefault = new Dipendente();

    public DipendenteDetail(Dipendente d, Component frmRelated){

        setTitle("Dipendente Detail");
        setSize(400, 300);      //se passo un solo parametro setta width e height allo stesso valore in pixel
        setLocationRelativeTo(frmRelated);       //null = centra in base al size, centro dello schermo

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dipendenteDefault = d;

        initUI();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnUpdate) {

                if (checkField() == true) {

                    int id = Integer.parseInt(txtId.getText());
                    String nome = txtName.getText();
                    String cognome = txtCognome.getText();
                    String cell = txtCell.getText();
                    int idNegozio = Integer.parseInt(txtIdNegozio.getText());
                    DipendenteDAO.update(new Dipendente(id, nome, cognome, cell, idNegozio));
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));       //close the detail page after the update
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void populate(){
        txtId.setText("" + dipendenteDefault.getId());
        txtName.setText("" + dipendenteDefault.getNome());
        txtCognome.setText("" + dipendenteDefault.getCognome());
        txtCell.setText("" + dipendenteDefault.getCell());
        txtIdNegozio.setText("" + dipendenteDefault.getIdNegozio());
    }

    public boolean checkField(){

        if(txtName.getText().isEmpty()){

            txtName.setBackground(Color.RED);
            txtName.setForeground(Color.WHITE);
            return false;
        }else{

            txtName.setBackground(Color.WHITE);
            txtName.setForeground(Color.BLACK);
        }
        return true;
    }
    public void initUI(){

        JLabel lblId = new JLabel("id: ");
        this.txtId = new JTextField(7);
        txtId.setEnabled(false);
        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlId.add(lblId);
        pnlId.add(txtId);

        JLabel lblName = new JLabel("nome: ");
        this.txtName = new JTextField(20);
        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlName.add(lblName);
        pnlName.add(txtName);

        JLabel lblCognome = new JLabel("cognome: ");
        this.txtCognome = new JTextField(20);
        JPanel pnlCognome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCognome.add(lblCognome);
        pnlCognome.add(txtCognome);

        JLabel lblCell = new JLabel("cellulare: ");
        this.txtCell = new JTextField(20);
        JPanel pnlCell = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCell.add(lblCell);
        pnlCell.add(txtCell);

        JLabel lblIdNegozio = new JLabel("idNegozio: ");
        this.txtIdNegozio = new JTextField(20);
        JPanel pnlIdNegozio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlIdNegozio.add(lblIdNegozio);
        pnlIdNegozio.add(txtIdNegozio);


        JPanel pnlCenter = new JPanel(new GridLayout(5,1));

        populate();

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlName);
        pnlCenter.add(pnlCognome);
        pnlCenter.add(pnlCell);
        pnlCenter.add(pnlIdNegozio);

        add(pnlCenter);     //add() ereditato da JFrame

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnUpdate = new JButton("update");
        btnUpdate.addActionListener(this);

        pnlSouth.add(btnUpdate);

        add(pnlSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {       //classe anonima
            @Override
            public void run() {

                new DipendenteDetail(new Dipendente("default", "default",null,0), null);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
