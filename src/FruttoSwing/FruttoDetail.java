package FruttoSwing;

import Negozietti.Frutto;
import Negozietti.Stagionalita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class FruttoDetail extends JDialog implements ActionListener {

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JComboBox<Stagionalita> cmbStagionalita = null;
    private JTextField txtCosto = null;
    private final String patternFloat = "^(([0-9]*)|(([0-9]*)\\.([0-9]*)))$";
    private JButton btnUpdate = null;
    private Frutto fruttoDefault = new Frutto();

    public FruttoDetail(Frutto f, Component frmRelated){

        setTitle("Frutto Detail");
        setSize(400, 300);      //se passo un solo parametro setta width e height allo stesso valore in pixel
        setLocationRelativeTo(frmRelated);       //null = centra in base al size, centro dello schermo

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.fruttoDefault = f;

        initUI();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpdate) {

            if (checkField() == true) {

                fruttoDefault.setId(Integer.parseInt(txtId.getText()));
                fruttoDefault.setNome(txtName.getText());
                fruttoDefault.setStagionalita((Stagionalita) cmbStagionalita.getSelectedItem());
                fruttoDefault.setCosto((int)Float.parseFloat(txtCosto.getText()));
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));       //close the detail page after the update
            }
        }
    }

    private void populate(){
        txtId.setText("" + fruttoDefault.getId());
        txtCosto.setText(""  + fruttoDefault.getCosto()/1.0);
        txtName.setText("" + fruttoDefault.getNome());
        cmbStagionalita .setSelectedItem(fruttoDefault.getStagionalita());
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

        if(cmbStagionalita.getSelectedIndex() < 0){

            cmbStagionalita.setBackground(Color.RED);
            cmbStagionalita.setForeground(Color.WHITE);
            return false;
        }else{

            cmbStagionalita.setBackground(Color.WHITE);
            cmbStagionalita.setForeground(Color.BLACK);
        }

        if(!isPriceValid() || txtCosto.getText().isEmpty()){

            txtCosto.setBackground(Color.RED);
            txtCosto.setForeground(Color.WHITE);
            return false;
        }else{

            txtCosto.setBackground(Color.WHITE);
            txtCosto.setForeground(Color.BLACK);
        }
        return true;
    }
    private boolean isPriceValid(){
        String price = txtCosto.getText();
        return price.matches(patternFloat);
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

        JLabel lblStagionalita = new JLabel("stagionalita: ");
        this.cmbStagionalita = new JComboBox<>(Stagionalita.values());

        JPanel pnlStagionalita = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStagionalita.add(lblStagionalita);
        pnlStagionalita.add(cmbStagionalita);

        JLabel lblCosto = new JLabel("costo: ");
        this.txtCosto = new JTextField(10);
        JPanel pnlCosto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCosto.add(lblCosto);
        pnlCosto.add(txtCosto);
        JPanel pnlCenter = new JPanel(new GridLayout(4,1));

        populate();

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlName);
        pnlCenter.add(pnlStagionalita);
        pnlCenter.add(pnlCosto);

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

                new FruttoDetail(new Frutto(6, "mela", Stagionalita.AUTUNNALE, 3), null);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
