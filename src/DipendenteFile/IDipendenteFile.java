package DipendenteFile;

import Negozietti.Dipendente;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.ArrayList;

interface IDipendenteFile {
  public void write(ArrayList<Dipendente> dipendenti, String filename) throws IOException, JAXBException, DocumentException;
}

