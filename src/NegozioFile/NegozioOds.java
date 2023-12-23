package NegozioFile;

import Negozietti.Frutto;
import Negozietti.Negozio;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NegozioOds implements INegozioFile {

    public void write(ArrayList<Negozio> negozi, String fileName) throws IOException, JAXBException, DocumentException {

        DefaultTableModel model = new DefaultTableModel();  //crea un modello per come dovrà essere l'ods
        String[] cols = {"id", "nome", "sede"};

        for (String col : cols)
            model.addColumn(col);

        for (Negozio n : negozi)
            model.addRow(n.toRow());

        SpreadSheet.createEmpty(model).saveAs(new File(fileName));
    }
}
