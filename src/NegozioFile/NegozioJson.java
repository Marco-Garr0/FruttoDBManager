package NegozioFile;

import Negozietti.Negozio;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NegozioJson implements INegozioFile {

    public void write(ArrayList<Negozio> negozi, String fileName) throws IOException, JAXBException, DocumentException {

        Gson gson = new Gson(); //Gson = creata da Google

        String jsonStr = gson.toJson(negozi);   //crea json basandosi automaticamente sull'oggetto passato per parametro

        PrintWriter printfWrite = new PrintWriter(fileName);
        printfWrite.println(jsonStr);
        printfWrite.close();
    }

}
