package DipendenteFile;

import Negozietti.Dipendente;
import Negozietti.Frutto;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DipendenteXls implements IDipendenteFile {

    public void write(ArrayList<Dipendente> Dipendenti, String fileName) throws IOException, JAXBException, DocumentException {

        Workbook wb = new HSSFWorkbook();   //HSSFWorkbook è l'implementazione della classe astrtatta Workbook
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("Dipendenti"); //nome del foglio di calcolo

        int nRow = 0;
        for (Dipendente d: Dipendenti) {

            int nCol = 0;
            Row row = sheet.createRow(nRow);

            String id = d.getId() + "";
            String nome = d.getNome();
            String cognome = d.getCognome();
            String cellulare = d.getCell();
            String idNegozio = d.getIdNegozio() + "";

            Cell cell = row.createCell(nCol);
            cell.setCellValue(id);

            cell = row.createCell(++nCol);
            cell.setCellValue(nome);

            cell = row.createCell(++nCol);
            cell.setCellValue(cognome);

            cell = row.createCell(++nCol);
            cell.setCellValue(cellulare);

            cell = row.createCell(++nCol);
            cell.setCellValue(idNegozio);

            nRow++;
        }
        wb.write(new FileOutputStream(fileName));
    }
}
