package NegozioFile;

import Negozietti.Negozio;
import com.itextpdf.text.DocumentException;
import jakarta.xml.bind.JAXBException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NegozioXls implements INegozioFile {

    public void write(ArrayList<Negozio> negozi, String fileName) throws IOException, JAXBException, DocumentException {

        Workbook wb = new HSSFWorkbook();   //HSSFWorkbook è l'implementazione della classe astrtatta Workbook
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("negozi"); //nome del foglio di calcolo

        int nRow = 0;
        for (Negozio n : negozi) {

            int nCol = 0;
            Row row = sheet.createRow(nRow);   //createRow() richiede come parametro numero della riga

            String id = n.getId() + "";
            String nome = n.getNome();
            String sede = n.getSede();

            Cell cell = row.createCell(nCol);
            cell.setCellValue(id);

            cell = row.createCell(++nCol);
            cell.setCellValue(nome);

            cell = row.createCell(++nCol);
            cell.setCellValue(sede);

            nRow++;
        }
        wb.write(new FileOutputStream(fileName));
    }
}
