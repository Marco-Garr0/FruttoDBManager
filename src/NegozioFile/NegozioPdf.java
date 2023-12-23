package NegozioFile;

import Negozietti.Frutto;
import Negozietti.Negozio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.xml.bind.JAXBException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NegozioPdf implements INegozioFile {
    public void write(ArrayList<Negozio> negozi, String filename) throws IOException, JAXBException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));  //singoletto->getInstance() ritorna sempre la stessa istanza di PdfWriter
        document.open();                                                      //solo un instanza per processo, fin quando non viene eliminata
        document.addTitle("lista negozi");
        document.addAuthor("Marco Garro");

        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph phTitle = new Paragraph("---LISTA NEGOZI---", fontTitle);
        phTitle.setSpacingAfter(30);   //in pixel
        document.add(phTitle);

        Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 13);

        for (Negozio n: negozi) {

            document.add(new Paragraph("id: " + n.getId(), fontText));
            document.add(new Paragraph("nome: " + n.getNome(), fontText));
            document.add(new Paragraph("sede: " + n.getSede() + "\n", fontText));
        }

        document.close();
    }
}
