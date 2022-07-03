package com.jasper.report;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;

public class FirstReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String rootPath = System.getProperty("user.dir");
			
			// rootPath : C:\Users\abc95\git\jasper\Jasper-Report
			//System.out.println(rootPath);
			
			String filePath = rootPath + "\\src\\main\\resources\\FirstReport.jrxml";
			// Jasper의 경우 Key 타입은 String, Value 타입은 Object만 허용합니다
			/* Java application에서 Japser로 값 보내는 방법 
			 1. Parameter를 활용하여 값 입력
			 2. Field 활용
			 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "John");
			
			Student student1 = new Student(1L, "Raj", "Joshi", "Happy Street", "Delhi");
			Student student2 = new Student(2L, "Peter", "Smith", "Any Street", "Mumbai");
			
			List<Student> list = new ArrayList<Student>();
			list.add(student1);
			list.add(student2);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			
			// xml파일을 class파일로 빌드합니다
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			/*
			 Title 밴드에 textfield의 key : name(jasper에서 지정한 key의 이름 =id)을 활용하여 해당 요소를 가져옵니다.
			 */
			JRBaseTextField textField = (JRBaseTextField)
			report.getTitle().getElementByKey("name");
			textField.setForecolor(Color.RED);
			// HorizontalTextAlignEnum : CENTER, JUSTIFIED, LEFT, RIGHT
			textField.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
			
			JasperExportManager.exportReportToPdfFile(print, rootPath + "\\src\\main\\resources\\data\\FirstReport.pdf");
			
			System.out.println("Report Created..");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception while creating report");
		}
	}

}
