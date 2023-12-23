package FruttoFIle;

import Negozietti.Frutti;
import Negozietti.Frutto;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FruttoXml implements IFruttoFile {

    public void write(ArrayList<Frutto> frutti, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter fw = new PrintWriter(fileName);

        JAXBContext context = JAXBContext.newInstance(Frutti.class);   //factory method != costruttore
        //a differemza do costruttore non ritorna eccezione se c'è un errore ma magari ritorna null
        Marshaller marsh = context.createMarshaller();  //classe JAXBContext contiene factory method di Marshaller
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //classe Marshall contiene proprietà final -> classe non estensibile

        Frutti ret = new Frutti();
        ret.setFrutti(frutti);
        marsh.marshal(ret, fw);

        fw.close();
    }
}
