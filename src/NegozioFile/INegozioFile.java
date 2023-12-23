package NegozioFile;

import Negozietti.Negozio;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.ArrayList;

interface INegozioFile {     //'I' indica un interfaccia
  public void write(ArrayList<Negozio> negozi, String filename) throws IOException, JAXBException, DocumentException;
}

