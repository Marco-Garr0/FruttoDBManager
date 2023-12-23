package DipendenteFile;

import Negozietti.Dipendente;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DipendenteOds implements IDipendenteFile {

    public void write(ArrayList<Dipendente> dipendenti, String fileName) throws IOException, JAXBException, DocumentException {

        DefaultTableModel model = new DefaultTableModel();  //crea un modello per come dovrà essere l'ods
        String[] cols = {"id", "nome","cognome","cell","idNegozio"};

        for (String col : cols)
            model.addColumn(col);

        for (Dipendente d : dipendenti)
            model.addRow(d.toRow());

        SpreadSheet.createEmpty(model).saveAs(new File(fileName));
    }
}
