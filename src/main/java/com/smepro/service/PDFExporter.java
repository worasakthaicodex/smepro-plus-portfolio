package com.smepro.service;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


import org.springframework.util.ResourceUtils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.smepro.models.datapdf;



public class PDFExporter {

    private List<datapdf> listData;
    private String title;
    private  String[] header;
    private BaseFont bf;
    public void setTitle(String title) {
        this.title = title;
    }
	
    
    
    public PDFExporter(List<datapdf> listData,String header,String title) throws DocumentException, IOException {
        this.listData = listData;
        this.header = header.split(",");
        this.title = title;
        File font = ResourceUtils.getFile("classpath:font/ANGSAZ.ttf");
        String fontPath = font.getCanonicalPath().replace("\\","/");
   
        //bf = BaseFont.createFont("C:/server/smepro/src/main/resources/font/ANGSAZ.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED); 
        bf = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H, BaseFont.EMBEDDED); 
    }
	

    
    
	private void writeTableHeader(PdfPTable table) {
		
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.lightGray);
        cell.setPadding(5);
         
        Font font = new Font(bf, 14);
        font.setColor(Color.black);
       
        for (String Head : header) {
	        cell.setPhrase(new Phrase(Head, font));   
	        table.addCell(cell);
        }
 
    }
     
    private void writeTableData(PdfPTable table) {
    	System.out.println(listData);    
    	PdfPCell cell = new PdfPCell();
       Font font = new Font(bf, 12);
       font.setColor(Color.black);
       
        for (datapdf data : listData) {
        	
           	
        	if(data.getData1() != null) { cell.setPhrase(new Phrase(data.getData1(), font)); table.addCell(cell); }
        	if(data.getData2() != null)  { cell.setPhrase(new Phrase(data.getData2(), font)); table.addCell(cell); }
        	if(data.getData3() != null)  { cell.setPhrase(new Phrase(data.getData3(), font)); table.addCell(cell); }
        	if(data.getData4() != null) { cell.setPhrase(new Phrase(data.getData4(), font)); table.addCell(cell); }
        	if(data.getData5() != null) { cell.setPhrase(new Phrase(data.getData5(), font)); table.addCell(cell); }
        	if(data.getData6() != null) { cell.setPhrase(new Phrase(data.getData6(), font)); table.addCell(cell); }
        	if(data.getData7() != null)  { cell.setPhrase(new Phrase(data.getData7(), font)); table.addCell(cell); }
        	if(data.getData8() != null)  { cell.setPhrase(new Phrase(data.getData8(), font)); table.addCell(cell); }
        	if(data.getData9() != null)  { cell.setPhrase(new Phrase(data.getData9(), font)); table.addCell(cell); }
        	if(data.getData10() != null)  { cell.setPhrase(new Phrase(data.getData10(), font)); table.addCell(cell); }
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        
        
        
        Font font = new Font(bf, 14);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph( this.title, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
    
        
    	System.out.println(this.header.length);  
        
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table); 
        document.add(table);
        document.close();
       
    }
	
	
}
