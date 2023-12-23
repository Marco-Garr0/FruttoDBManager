package NegozioFile;

import Negozietti.Frutti;
import Negozietti.Negozi;
import Negozietti.Negozio;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NegozioXml implements INegozioFile {

    public void write(ArrayList<Negozio> negozi, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter fw = new PrintWriter(fileName);

        JAXBContext context = JAXBContext.newInstance(Negozi.class);
        Marshaller marsh = context.createMarshaller();  //classe JAXBContext contiene factory method di Marshaller
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //classe Marshall contiene proprietà final -> classe non estensibile

        Negozi ret = new Negozi();
        ret.setNegozi(negozi);
        marsh.marshal(ret, fw);

        fw.close();
    }
}
