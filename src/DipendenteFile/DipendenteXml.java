package DipendenteFile;

import Negozietti.Dipendente;
import Negozietti.Dipendenti;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DipendenteXml implements IDipendenteFile {

    public void write(ArrayList<Dipendente> dipendenti, String fileName) throws IOException, JAXBException, DocumentException {

        PrintWriter fw = new PrintWriter(fileName);

        JAXBContext context = JAXBContext.newInstance(Dipendenti.class);
        Marshaller marsh = context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Dipendenti ret = new Dipendenti();
        ret.setDipendenti(dipendenti);
        marsh.marshal(ret, fw);

        fw.close();
    }
}
