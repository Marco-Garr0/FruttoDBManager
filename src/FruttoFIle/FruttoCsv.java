package FruttoFIle;

import Negozietti.Frutto;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FruttoCsv implements IFruttoFile {   //implements può essere multiplo non come ereditarietà

    public void write(ArrayList<Frutto> frutti, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter pw = new PrintWriter(fileName);  //istanza di una delle classi per leggere da file

        for (Frutto f : frutti)
            pw.println(f.toLine());  //scrive linea su file e manda a capo

        pw.close();  //chiusura file in scrittura
    }
}
