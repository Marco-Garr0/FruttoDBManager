package DipendenteFile;

import Negozietti.Dipendente;
import Negozietti.Frutto;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DipendenteCsv implements IDipendenteFile {   //implements può essere multiplo non come ereditarietà

    public void write(ArrayList<Dipendente> dipendenti, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter pw = new PrintWriter(fileName);

        for (Dipendente d : dipendenti)
            pw.println(d.toLine());

        pw.close();
    }
}
