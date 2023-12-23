package DipendenteFile;

import Negozietti.Dipendente;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.xml.bind.JAXBException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DipendentePdf implements IDipendenteFile {   //pdf formato vettoriale, uguale per ogni formato, font

    public void write(ArrayList<Dipendente> dipendenti, String filename) throws IOException, JAXBException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));  //singoletto->getInstance() ritorna sempre la stessa istanza di PdfWriter
        document.open();                                                      //solo un instanza per processo, fin quando non viene eliminata
        document.addTitle("lista dipendenti");
        document.addAuthor("Marco Garro");

        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph phTitle = new Paragraph("---LISTA DIPENDENTI---", fontTitle);
        phTitle.setSpacingAfter(30);   //in pixel
        document.add(phTitle);

        Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 13);

        for (Dipendente d : dipendenti) {

            document.add(new Paragraph("id: " + d.getId(), fontText));
            document.add(new Paragraph("nome: " + d.getNome(), fontText));
            document.add(new Paragraph("cognome: " + d.getCognome(), fontText));
            document.add(new Paragraph("cell: " + d.getCell(), fontText));
            document.add(new Paragraph("idNegozio: " + d.getIdNegozio(), fontText));
        }

        document.close();
    }
}
