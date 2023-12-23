package FruttoFIle;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;

import Negozietti.Frutti;
import Negozietti.Frutto;
import com.google.gson.*;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.JAXBException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.jopendocument.dom.spreadsheet.SpreadSheet; 
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

interface IFruttoFile{     //'I' indica un interfaccia
  public void write(ArrayList<Frutto> frutti, String filename) throws IOException, JAXBException, DocumentException;
}

