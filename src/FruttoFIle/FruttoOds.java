package FruttoFIle;

import Negozietti.Frutto;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FruttoOds implements IFruttoFile {

    public void write(ArrayList<Frutto> frutti, String fileName) throws IOException, JAXBException, DocumentException {

        DefaultTableModel model = new DefaultTableModel();  //crea un modello per come dovrà essere l'ods
        String[] cols = {"nome", "stagionalita", "costo"};

        for (String col : cols)
            model.addColumn(col);

        for (Frutto f : frutti)
            model.addRow(f.toRow());

        SpreadSheet.createEmpty(model).saveAs(new File(fileName));
    }
}
