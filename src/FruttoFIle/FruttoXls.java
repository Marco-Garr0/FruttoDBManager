package FruttoFIle;

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

public class FruttoXls implements IFruttoFile {

    public void write(ArrayList<Frutto> frutti, String fileName) throws IOException, JAXBException, DocumentException {

        Workbook wb = new HSSFWorkbook();   //HSSFWorkbook è l'implementazione della classe astrtatta Workbook
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("frutti"); //nome del foglio di calcolo

        int nRow = 0;
        for (Frutto f : frutti) {

            int nCol = 0;
            Row row = sheet.createRow(nRow);   //createRow() richiede come parametro numero della riga

            String nome = f.getNome();
            String stagionalita = f.getStagionalita().toString();
            String costo = f.getCosto() + "";

            Cell cell = row.createCell(nCol);
            cell.setCellValue(nome);

            cell = row.createCell(++nCol);
            cell.setCellValue(stagionalita);

            cell = row.createCell(++nCol);
            cell.setCellValue(costo);

            nRow++;
        }
        sheet.setColumnWidth(1, (256 * 15));//colonna 1 prende dimensione di 15 caratteri
        //per qualche motivo la funzione richiede il 256esimo di un carattere come unità
        wb.write(new FileOutputStream(fileName));
    }
}
