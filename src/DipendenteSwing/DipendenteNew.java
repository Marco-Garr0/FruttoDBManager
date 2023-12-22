package DipendenteSwing;

import Negozietti.DAO.DipendenteDAO;
import Negozietti.DAO.FruttoDAO;
import Negozietti.Dipendente;
import Negozietti.Frutto;
import Negozietti.Stagionalita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DipendenteNew extends JDialog implements ActionListener{

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtCognome = null;
    private JTextField txtCell = null;
    private JTextField txtIdNegozio = null;
    private JButton btnNew = null;

    public DipendenteNew(Component frmRelated){

        setTitle("Dipendente new");
        setSize(400, 300);      //se passo un solo parametro setta width e height allo stesso valore in pixel
        setLocationRelativeTo(frmRelated);       //null = centra in base al size, centro dello schermo

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnNew) {

                if (checkField() == true) {

                    String nome = txtName.getText();
                    String cognome = txtCognome.getText();
                    String cell = txtCell.getText();
                    int idNegozio = Integer.parseInt(txtIdNegozio.getText());
                    DipendenteDAO.create(new Dipendente(nome, cognome, cell, idNegozio));
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));       //close the detail page after the update
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
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

        JLabel lblCell = new JLabel("cell: ");
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

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlName);
        pnlCenter.add(pnlCognome);
        pnlCenter.add(pnlCell);
        pnlCenter.add(pnlIdNegozio);
        add(pnlCenter);     //add() ereditato da JFrame

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnNew = new JButton("new");
        btnNew.addActionListener(this);

        pnlSouth.add(btnNew);

        add(pnlSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {       //classe anonima
            @Override
            public void run() {

                new DipendenteNew( null);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
