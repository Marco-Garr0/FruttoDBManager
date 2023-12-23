package DipendenteFile;

import Negozietti.Dipendente;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DipendenteJson implements IDipendenteFile {

    public void write(ArrayList<Dipendente> dipendenti, String fileName) throws IOException, JAXBException, DocumentException {

        Gson gson = new Gson();

        String jsonStr = gson.toJson(dipendenti);

        PrintWriter printfWrite = new PrintWriter(fileName);
        printfWrite.println(jsonStr);
        printfWrite.close();
    }

}
