package com.jasper.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomReport {

	public static void main(String[] args) {
		try {
			
			String rootPath = System.getProperty("user.dir");
			
			// rootPath : C:\Users\abc95\git\jasper\Jasper-Report
			//System.out.println(rootPath);
			
			String filePath = rootPath + "\\src\\main\\resources\\Student.jrxml";
			
			Subject subject1 = new Subject("Java", 80);
			Subject subject2 = new Subject("MySQL", 70);
			Subject subject3 = new Subject("PHP", 50);
			Subject subject4 = new Subject("MongoDB", 40);
			Subject subject5 = new Subject("C++", 60);
			
			List<Subject> list = new ArrayList<Subject>();
			list.add(subject1);
			list.add(subject2);
			list.add(subject3);
			list.add(subject4);
			list.add(subject5);
			
			JRBeanCollectionDataSource dataSource = 
					new JRBeanCollectionDataSource(list);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("tableData", dataSource);
			parameters.put("studentName", "Moon");
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			
			
			System.out.println("CustomReport Created...");
			
		} catch(Exception e) {
			System.out.println("Exception while creating report");
		}
	}

}
