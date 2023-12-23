package FruttoFIle;

import Negozietti.Frutto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.xml.bind.JAXBException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FruttoPdf implements IFruttoFile {   //pdf formato vettoriale, uguale per ogni formato, font

    public void write(ArrayList<Frutto> frutti, String filename) throws IOException, JAXBException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));  //singoletto->getInstance() ritorna sempre la stessa istanza di PdfWriter
        document.open();                                                      //solo un instanza per processo, fin quando non viene eliminata
        document.addTitle("lista frutti");
        document.addAuthor("Marco Garro AAAAAA");

        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph phTitle = new Paragraph("---LISTA FRUTTI---", fontTitle);
        phTitle.setSpacingAfter(30);   //in pixel
        document.add(phTitle);

        Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 13);

        for (Frutto f : frutti) {

            document.add(new Paragraph("nome: " + f.getNome(), fontText));
            document.add(new Paragraph("stagione: " + f.getStagionalita().toString(), fontText));
            document.add(new Paragraph("costo: " + f.getCosto(), fontText));
        }

        document.close();
    }
}
