package com.own.springproject.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.own.springproject.model.Sell;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SellExcell extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=sell.xls");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Sell> list = (List<Sell>) model.get("list");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("Sell");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}


	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Sell Name");
		row.createCell(2).setCellValue("Customer Name");
		row.createCell(3).setCellValue("Added Date");
		row.createCell(4).setCellValue("Quantity");
		row.createCell(6).setCellValue("Ammount");

	}
	
	private void setBody(Sheet sheet, List<Sell> list) {
		int rowNum = 1;
		for(Sell sell : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(sell.getSellid());
			row.createCell(1).setCellValue(sell.getSellname());
			row.createCell(2).setCellValue(sell.getCustname());
			row.createCell(3).setCellValue(sell.getAddedDate());
			row.createCell(4).setCellValue(sell.getSellquantity());
			row.createCell(6).setCellValue(sell.getSellamount());
		}
	}
}
