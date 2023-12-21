package NegozioSwing;

import FruttoSwing.FruttoDetail;
import Negozietti.DAO.FruttoDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Frutto;
import Negozietti.Negozio;
import Negozietti.Stagionalita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class NegozioDetail extends JDialog implements ActionListener {

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtSede = null;
    private JButton btnUpdate = null;
    private Negozio negozioDefault = new Negozio();

    public NegozioDetail(Negozio n, Component frmRelated){

        setTitle("Frutto Detail");
        setSize(400, 300);      //se passo un solo parametro setta width e height allo stesso valore in pixel
        setLocationRelativeTo(frmRelated);       //null = centra in base al size, centro dello schermo

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        negozioDefault = n;

        initUI();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnUpdate) {

                if (checkField() == true) {

                    int id = Integer.parseInt(txtId.getText());
                    String nome = txtName.getText();
                    String sede = txtSede.getText();
                    NegozioDAO.update(new Negozio(id, nome, sede, null, null));
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));       //close the detail page after the update
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void populate(){
        txtId.setText("" + negozioDefault.getId());
        txtName.setText("" + negozioDefault.getNome());
        txtSede.setText("" + negozioDefault.getSede());
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

        JLabel lblName = new JLabel("name: ");
        this.txtName = new JTextField(20);
        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlName.add(lblName);
        pnlName.add(txtName);

        JLabel lblSede = new JLabel("sede: ");
        this.txtSede = new JTextField(20);
        JPanel pnlSede = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        pnlSede.add(lblSede);
        pnlSede.add(txtSede);
        JPanel pnlCenter = new JPanel(new GridLayout(3,1));

        populate();

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlName);
        pnlCenter.add(pnlSede);

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

                new NegozioDetail(new Negozio("default", "default",null,null), null);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
