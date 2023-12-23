package NegozioFile;

import Negozietti.Negozio;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NegozioCsv implements INegozioFile {   //implements può essere multiplo non come ereditarietà

    public void write(ArrayList<Negozio> negozi, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter pw = new PrintWriter(fileName);  //istanza di una delle classi per leggere da file

        for (Negozio n : negozi)
            pw.println(n.toLine());  //scrive linea su file e manda a capo

        pw.close();  //chiusura file in scrittura
    }
}
