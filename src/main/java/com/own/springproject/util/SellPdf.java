package com.own.springproject.util;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.own.springproject.model.Sell;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SellPdf extends AbstractPdfView {
	@Override
	protected void buildPdfMetadata(
			Map<String, Object> model, 
			Document document, HttpServletRequest request)
	{
		HeaderFooter header = new HeaderFooter(new Phrase("Sell PDF VIEW"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" (C) bway, Page # "), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		//download PDF with a given filename
		response.addHeader("Content-Disposition", "attachment;filename=Sell.pdf");

		//read data from controller
		@SuppressWarnings("unchecked")
		List<Sell> list = (List<Sell>) model.get("list");
		
		//create element
		//Font (Family, Size, Style, Color)
		Font titleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
		Paragraph title = new Paragraph("Doctor DATA",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingAfter(25.0f);
		//add to document
		document.add(title);
		
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.ORANGE);
		PdfPTable table = new PdfPTable(7);// no.of columns
		table.addCell(new Phrase("id",tableHead));
		table.addCell(new Phrase("Sell Name",tableHead));
		table.addCell(new Phrase("Customer Name",tableHead));
		table.addCell(new Phrase("Added Date",tableHead));
		table.addCell(new Phrase("Quantity",tableHead));
		table.addCell(new Phrase("Inventory",tableHead));
		table.addCell(new Phrase("Ammount",tableHead));
		
		for(Sell sell : list ) {
			table.addCell(String.valueOf(sell.getSellid()));
			table.addCell(sell.getSellname());
			table.addCell(sell.getCustname());
			table.addCell(sell.getAddedDate());
			table.addCell(String.valueOf(sell.getSellquantity()));
	        table.addCell(sell.getInventory().getInvname());
			table.addCell(String.valueOf(sell.getSellamount()));
		}
		//add to document
		document.add(table);
	}


}
