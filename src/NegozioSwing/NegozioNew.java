package NegozioSwing;

import Negozietti.DAO.FruttoDAO;
import Negozietti.DAO.NegozioDAO;
import Negozietti.Frutto;
import Negozietti.Negozio;
import Negozietti.Stagionalita;
import com.sun.tools.jconsole.JConsolePlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class NegozioNew extends JDialog implements ActionListener {

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtSede = null;
    private JButton btnNew = null;

    public NegozioNew(Component frmRelated){

        setTitle("Negozio new");
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
                    String sede = txtSede.getText();
                    NegozioDAO.create(new Negozio(nome, sede, null, null));
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

        JLabel lblName = new JLabel("name: ");
        this.txtName = new JTextField(20);
        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlName.add(lblName);
        pnlName.add(txtName);

        JLabel lblSede = new JLabel("sede: ");
        txtSede = new JTextField(20);
        JPanel pnlSede = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSede.add(lblSede);
        pnlSede.add(txtSede);

        JPanel pnlCenter = new JPanel(new GridLayout(3,1));

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlName);
        pnlCenter.add(pnlSede);

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

                new NegozioNew(null);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
