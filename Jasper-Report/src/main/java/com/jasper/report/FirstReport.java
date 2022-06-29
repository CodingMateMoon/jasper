package com.jasper.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FirstReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String filePath = "C:\\Dev\\projects\\sts-workspace\\Jasper-Report\\src\\main\\resources\\FirstReport.jrxml";
			// Jasper의 경우 Key 타입은 String, Value 타입은 Object만 허용합니다
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
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
			
			JasperExportManager.exportReportToPdfFile(print, "C:\\Dev\\projects\\sts-workspace\\Jasper-Report\\src\\main\\resources\\data\\FirstReport.pdf");
			
			System.out.println("Report Created..");
			/*
			 
			 */
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception while creating report");
		}
	}

}
